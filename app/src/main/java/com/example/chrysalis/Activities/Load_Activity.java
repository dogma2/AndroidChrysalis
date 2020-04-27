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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.chrysalis.Clases.*;
import com.example.chrysalis.FixedData;
import com.example.chrysalis.Permisos;
import com.example.chrysalis.Public_Methods;
import com.example.chrysalis.R;

import java.io.File;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Date;

import static com.example.chrysalis.Clases.AppData.getAppData;
import static com.example.chrysalis.Clases.CCAA.getCCAA;
import static com.example.chrysalis.Clases.Config.getConfig;
import static com.example.chrysalis.Clases.Datas.getDatas;
import static com.example.chrysalis.Clases.Delegacion.getDelegaciones;
import static com.example.chrysalis.Clases.Idioma.getIdiomas;
import static com.example.chrysalis.Clases.Provincia.getProvincias;


public class Load_Activity extends AppCompatActivity {

    private Permisos p;
    boolean isUserInteracting;

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - MaGoMo atributos para permisos ->
    public static int writeExternalStoragePermission;
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - MaGoMo atributos para carga de jsons config ->
    public static File AppBaseDir;
    public static Context mainContext;

    public static ArrayList<Delegacion> delegaciones;
    public static ArrayList<CCAA> comunidades;
    public static ArrayList<Idioma> idiomas;
    public static ArrayList<Provincia> provincias;
    public static Evento evento;
    public static ArrayList<EventosLista> eventos;
    public static ArrayList<DatoInteres> datosinteres;
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - MaGoMo atributos para carga de jsons config //

    public static final int ESNUEVO = 1;
    public static final int NOESTAACTIVADO = 2;
    public static final String KEY = "STATE";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_load);
        p = new Permisos(this);

        if (getIntent().getBooleanExtra("EXIT", false)) {
            finish();
        }

        if(!p.HasAllPermisos())
        {
            p.SolicitarTodosLosPerimisos();
        }
        mainContext = getApplicationContext(); // context
        AppBaseDir = getExternalFilesDir(null); // external access



        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - MaGoMo 2020.04.20



        //imai = Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID); // imei phone
        //myemail = ""; // email introducido por usuario
        //mycode = ""; // codigo introducido por usuario

        Log.wtf("MaGoMo >> ", "MainActivity onCreate -> AppBaseDir " + AppBaseDir.getPath() );

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //Comprobar si existe el directorio
                if (new File(FixedData.DIR_MASTER).exists()) //Existe el directiorio
                {
                    Log.wtf("Oriol >> ", "El directorio existe" );
                    //Leer los ficheros de configuracion.
                    //Determinar si esta activado.
                    // -->Si esta activado, llamar a la API para que lo reconfirme.

                    // --> Si existe, comprobar si esta activado
                    // ---->Esta activado Ir a la pagina de inicio
                    // ----> No esta activado: ir a la pagina de login
                    // solicitar correo
                    //
            /*
            Log.wtf("MAGOMO", "Main -> no instalado !");
            if ( callFilesWork(false)) {
                Log.wtf("MAGOMO", "Main -> INSTALADO !");
                // - - - - - - - - - - carga datos de archivos json
                getJsonConfig(mainContext);
                getJsonData(mainContext);
            }*/

                    //appddata.setState(1);
                   // if(AppData.SetData(appddata))
                    //{
                   //     System.out.println("Ha habido un error");
                    //}
                    AppData appdata = AppData.getAppData(getApplicationContext());


                    if(appdata.getState() == 1)
                    {
                        Log.wtf("Oriol >> ", "El usuario esta activado" );
                        //Esta Activado
                        //Preguntamos a la api se de verdad esta activado

                        //Comprobamos si hay alguna actualizacion.


                        //Falta pulir esto comentar de momento para uqe no actualzie todoo el rato
                        if(appdata.getUpdate() >= appdata.getNext())
                        {

                            Public_Methods.unZipUpdate(Permisos.getImei(getApplicationContext()));
                            Public_Methods.ActivateFileCopy();
                            ;
                            Calendar c = Calendar.getInstance();
                            appdata.setUpdate((int) c.getTime().getTime());
                        }

                        Intent i = new Intent(Load_Activity.this, Inicio_activity.class);
                        startActivity(i);
                    }
                    else
                    {
                        Log.wtf("Oriol >> ", "El usuario esta desactivado" );
                        //estas desactivado
                        //llamamos a la actividad de login i enviamos como blunndle el estado
                        Bundle b = new Bundle();
                        b.putInt(KEY, NOESTAACTIVADO);
                        Intent i = new Intent(Load_Activity.this, Login_activity.class);
                        i.putExtras(b);
                        startActivity(i);

                    }


                }
                else
                {
                    Log.wtf("Oriol >> ", "El directorio no existe" );
                    Intent i = new Intent(Load_Activity.this, Login_activity.class);
                    Bundle b = new Bundle();
                    b.putInt(KEY,ESNUEVO);
                    i.putExtras(b);
                    startActivity(i);

                    // --> No existe el directorio -> Ir a la pagina de Login
                    // En la pagina de login
                    // Solicitar correo electronico
                    // Llamar a la API con el Correo junto al IMEI
                    // Crear estructura de carpetas.
                    // Recibir el Pack de instalacion
                    // Descomprimir i leer Los JSON necesarios
                    // Solicitar el codigo de verificacion
                    // Compararlo con el codigo que hemos cargado previamente en el momento de leer los json.
                    //
                }

            }
        }, 4000);

        // - - - - - - - - - - carga datos de archivos json


        // - - - - - - - - - - control de estado






    }

        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - MaGoMo carga de jsons config //



/*
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

*/

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




    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        p.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(p.HasAllPermisos())
        {
            //Intent i = new Intent(Load_Activity.this, Login_activity.class);
            //startActivity(i);
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
    public void getJsonConfig(Context contexto, Config config, AppData appddata, Datas datas ) {
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
    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(),"No puedes tirar hacia atras ahora.",Toast.LENGTH_SHORT).show();
    }
}
