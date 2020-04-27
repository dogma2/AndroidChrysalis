package com.example.chrysalis.Activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.chrysalis.Clases.AppData;
import com.example.chrysalis.Clases.Datas;
import com.example.chrysalis.GetInfo;
import com.example.chrysalis.Permisos;
import com.example.chrysalis.Public_Methods;
import com.example.chrysalis.R;
import com.example.chrysalis.Fragments.TerminosDeUsoFragment;
import com.example.chrysalis.Fragments.VerificarCodigoFragment;
import com.google.android.material.textfield.TextInputEditText;

import static com.example.chrysalis.Clases.AppData.getAppData;

public class Login_activity extends AppCompatActivity {
    private VerificarCodigoFragment f;
    private TerminosDeUsoFragment f2;
    private TextInputEditText texto;
    private AppData data;

    private Boolean IsNewUser;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login_activity);

        texto = findViewById(R.id.EditTextLogin);

        Button boton = findViewById(R.id.buttonActivar);

        Bundle b = getIntent().getExtras();
        int code = b.getInt(Load_Activity.KEY);

        if(code == Load_Activity.ESNUEVO)
        {
            IsNewUser = true;
        }
        else if(code == Load_Activity.NOESTAACTIVADO)
        {
            IsNewUser = false;
        }

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Verificar que el correo este en la base de datos.

                // Solicitar correo electronico

                //Ja ho se tota una contradicio,
               if(IsNewUser || !IsNewUser)
               {
                   //Llamamos a la api con el correo electronico i el imei
                   String correo = texto.getText().toString();
                   String Imei = Permisos.getImei(getApplicationContext());
                   Log.wtf("Oriol >> ", "Llamando a la api" );
                   Boolean state = Public_Methods.unZipInstalation(correo, Imei);
                   Log.wtf("Oriol >> ", "Ha ido todo bien ="+state );
                   if(state)
                   {
                       Public_Methods.ActivateFileCopy();
                       data = getAppData(getApplicationContext());
                   }
                   //Descargamos i descomprimimos el fichero
                   //Copiamos los archivos
                   //Leemos el JSON i obtenemos el codigo de verificacion

               }

                // --> No existe el directorio -> Ir a la pagina de Login
                // En la pagina de login

                // Llamar a la API con el Correo junto al IMEI
                // Crear estructura de carpetas.
                // Recibir el Pack de instalacion
                // Descomprimir i leer Los JSON necesarios
                // Solicitar el codigo de verificacion
                // Compararlo con el codigo que hemos cargado previamente en el momento de leer los json.


                f = new VerificarCodigoFragment(IntroducirCodigo);
                getSupportFragmentManager().beginTransaction().add(R.id.FrameLayoutFragmentLogin,f).commit();
            }
        });

    }
    View.OnClickListener IntroducirCodigo = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId())
            {
                case R.id.buttonVerificarCodigo:
                    ClickVerificarCodigo();
                    break;

                default:
                    getSupportFragmentManager().beginTransaction().remove(f).commit();
                    break;
            }


        }
    };
    private void ClickVerificarCodigo()
    {
        data = getAppData(getApplicationContext());
        if(f.GetInputText().equals(data.getCode()))
        {

            getSupportFragmentManager().beginTransaction().remove(f).commit();
            f2 = new TerminosDeUsoFragment(TerminosDeUsoAcceptados);
            getSupportFragmentManager().beginTransaction().add(R.id.FrameLayoutFragmentLogin,f2).commit();
        }
        else
        {
            f.SetCodeIsWrongText();
        }
    }
    View.OnClickListener TerminosDeUsoAcceptados = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            data.setState(1);
            AppData.SetData(data);
            getSupportFragmentManager().beginTransaction().remove(f2).commit();
            Intent i = new Intent(Login_activity.this, Load_Activity.class);
            startActivity(i);
            finish();
        }
    };


    @Override
    public void onBackPressed() {
        if(!f.isAdded() && !f2.isAdded())
        {
            super.onBackPressed();
        }
        else if(f.isAdded())
        {
            getSupportFragmentManager().beginTransaction().remove(f).commit();
        }
        else if(f2.isAdded())
        {
            Toast.makeText(getApplicationContext(),"Ya no hay vuelta atras.",Toast.LENGTH_SHORT).show();
        }
    }
}
