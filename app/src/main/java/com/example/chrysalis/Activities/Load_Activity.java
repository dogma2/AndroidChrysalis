package com.example.chrysalis.Activities;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.chrysalis.Clases.*;
import com.example.chrysalis.Permisos;
import com.example.chrysalis.R;

import java.io.File;
import java.util.ArrayList;

import static com.example.chrysalis.Clases.AppData.getAppData;
import static com.example.chrysalis.Clases.CCAA.getCCAA;
import static com.example.chrysalis.Clases.Config.getConfig;
import static com.example.chrysalis.Clases.Datas.getDatas;
import static com.example.chrysalis.Clases.Delegacion.getDelegaciones;
import static com.example.chrysalis.Clases.Idioma.getIdiomas;
import static com.example.chrysalis.Clases.Provincia.getProvincias;
import static com.example.chrysalis.Public_Methods.callFilesWork;

public class Load_Activity extends AppCompatActivity {

    private Permisos p;
    boolean isUserInteracting;

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - MaGoMo atributos para permisos ->
    public static int writeExternalStoragePermission;
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - MaGoMo atributos para carga de jsons config ->
    public static File AppBaseDir;
    public static Context mainContext;
    public static String myemail;
    public static String myimai;
    public static String mycode;
    public static AppData appddata;
    public static Config config;
    public static Datas datas;
    public static ArrayList<Delegacion> delegaciones;
    public static ArrayList<CCAA> comunidades;
    public static ArrayList<Idioma> idiomas;
    public static ArrayList<Provincia> provincias;
    public static Evento evento;
    public static ArrayList<EventosLista> eventos;
    public static ArrayList<DatoInteres> datosinteres;
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - MaGoMo atributos para carga de jsons config //

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_load);
        p = new Permisos(this);



/*
carga de json
	- existen
		controla que este activado con
		appddata.getState()!=0
		activado = false
	- no existen
		activado = false

Si activado == false
	>>> pide email
	>>> con email lanza proceso activacion (carga archivos y envia email)
	>>> cuando cargue archivos, muestra pantalla de codigo con datos RGPD chrysallis
	>>> pide codigo
	>>> compara codigo con "m_code":"2427C0",
	>>> acepta terminos de uso si es == cambia "m_state":1 y almacena timestamp "m_date":1587421358,

Si activado == true

	>>> controlar actualizacion "_next":1587421358 <= HOY >>> ejecuta actualizar

    >>> SI CONTROL DESACTIVADO EN ACTUALIZACION ejecuta DeactivateFileDelete() {y vuelve a iniciar aplicacion)

INICIA APLICACION (con listra EVENTOS)
*/

        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - MaGoMo 2020.04.20
        mainContext = getApplicationContext(); // context
        AppBaseDir = getExternalFilesDir(null); // external access
        myimai = Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID); // imei phone
        myemail = ""; // email introducido por usuario
        mycode = ""; // codigo introducido por usuario

        Log.wtf("MaGoMo >> ", "MainActivity onCreate -> AppBaseDir " + AppBaseDir.getPath() );

        // - - - - - - - - - - permisos de aplicacion
        Permisos(mainContext); // estable ce permisos de la aplicacion

        // - - - - - - - - - - carga datos de archivos json
        getJsonData(mainContext);

        // - - - - - - - - - - control de estado
        if (appddata.getCidApp() == null || appddata.getCidApp().equals("")) { /*no instalado*/

            Log.wtf("MAGOMO", "Main -> no instalado !");
            if ( callFilesWork(false)) {
                Log.wtf("MAGOMO", "Main -> INSTALADO !");
                // - - - - - - - - - - carga datos de archivos json
                getJsonConfig(mainContext);
                getJsonData(mainContext);
            }

        }
        else if (appddata.getState()==0) { /*desactivado*/

            Log.wtf("MAGOMO", "Main -> no instalado !");
            if ( callFilesWork(false)) {
                Log.wtf("MAGOMO", "Main -> INSTALADO !");
                // - - - - - - - - - - carga datos de archivos json
                getJsonData(mainContext);
                getJsonData(mainContext);
            }

        }
        else { /*estado activo y funcional*/

            Log.wtf("MAGOMO", "Main -> estado activo y funcional !");

            // CONTROLAR SI DEBE ACTUALIZAR

            getJsonData(mainContext);
        }
        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - MaGoMo carga de jsons config //



        if (getIntent().getBooleanExtra("EXIT", false)) {
            finish();
        }
        if(p.HasAllPermisos())
        {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(Load_Activity.this, Login_activity.class);
                    startActivity(i);


                }
            }, 4000);
        }
        else
        {
            p.SolicitarTodosLosPerimisos();
        }



        /*
        else
        {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    //Pregunta a la base de datos el IMEI
                    //Compara con el IMEI ACTUAL


                }
            }, 4000);

        }*/



    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        p.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(p.HasAllPermisos())
        {
            Intent i = new Intent(Load_Activity.this, Login_activity.class);
            startActivity(i);
        }
        else
        {
            NoAcceptaPermisos();
        }
        isUserInteracting = false;
    }
    DialogInterface.OnClickListener CloseListenerr = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which)
            {
                case AlertDialog.BUTTON_NEGATIVE:
                    finish();
                    break;
            }

        }
    };

    private void NoAcceptaPermisos()
    {
        String mensaje = "Necesitamos que acceptes los permisos, para que la aplicacion funcione de forma correcta";
        AlertDialog.Builder dialog = new AlertDialog.Builder(Load_Activity.this).setTitle("Lo siento.").setMessage(mensaje).setIcon(R.drawable.ic_error_black_24dp).setNegativeButton(R.string.cancelar ,CloseListenerr);
        dialog.create().show();


        /*
        System.out.println("Aqui entra");
        AlertDialog.Builder builder = new AlertDialog.Builder(Load_Activity.this);
        builder.setMessage("dialog_message").setTitle("dialog_title");
        builder.create().show();
*/
    }




    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - metodo get JSONCONFIG ->
    public void getJsonConfig(Context contexto) {
        // - - - - - - - - - - carga datos de archivos json
        config = getConfig(contexto);
        appddata = getAppData(contexto);
        datas = getDatas(contexto);
    }
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - metodo get JSONCONFIG //

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - metodo get JSONDATA ->
    public void getJsonData(Context contexto) {
        // - - - - - - - - - - carga datos de archivos json
        delegaciones = getDelegaciones(contexto);
        comunidades = getCCAA(contexto);
        idiomas = getIdiomas(contexto);
        provincias = getProvincias(contexto);

        // eventos = getEventos(contexto);
        // datosinteres = getdatosInteres(contexto);
    }
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - metodo get JSONDATA ->

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - metodo de PERMISOS ->
    public void Permisos(Context contexto) {

        String permisoLectura = Manifest.permission.READ_EXTERNAL_STORAGE;
        String permisoEscritura = Manifest.permission.WRITE_EXTERNAL_STORAGE;
        String permisoTelefono = Manifest.permission.READ_PHONE_STATE;
        String permisoInternet = Manifest.permission.INTERNET;

        int RequestCode = 1;
        int RequestCode2 = 2;

        ArrayList<String> ListaPermisos = new ArrayList<>();
        if(!tienePermiso(contexto, permisoLectura)) { ListaPermisos.add(permisoLectura); }
        if(!tienePermiso(contexto, permisoEscritura)) { ListaPermisos.add(permisoEscritura); }
        if(!tienePermiso(contexto, permisoTelefono)) { ListaPermisos.add(permisoTelefono); }
        if(!tienePermiso(contexto, permisoInternet)) { ListaPermisos.add(permisoInternet); }
        String[] ListaParam = new String[ListaPermisos.size()];
        for(int cnt = 0; cnt < ListaParam.length; cnt++) { ListaParam[cnt] = ListaPermisos.get(cnt); }
        if(ListaParam.length > 0) { ActivityCompat.requestPermissions(this, ListaParam, RequestCode); }
    }

    private boolean tienePermiso(Context contexto, String permiso)
    {
        return ActivityCompat.checkSelfPermission(this, permiso) == PackageManager.PERMISSION_GRANTED;
    }
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - metodo de PERMISOS //
}
