package com.example.chrysalis;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Login_activity extends AppCompatActivity {
    VerificarCodigoFragment f;
    TerminosDeUsoFragment f2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login_activity);


        Button boton = findViewById(R.id.buttonActivar);


        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Verificar que el correo este en la base de datos.

                f = new VerificarCodigoFragment(CodigoCorrecto);
                getSupportFragmentManager().beginTransaction().add(R.id.FrameLayoutFragmentLogin,f).commit();




            }
        });

    }
    View.OnClickListener CodigoCorrecto = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            getSupportFragmentManager().beginTransaction().remove(f).commit();

            f2 = new TerminosDeUsoFragment(TerminosDeUsoAcceptados);
            getSupportFragmentManager().beginTransaction().add(R.id.FrameLayoutFragmentLogin,f2).commit();
        }
    };
    View.OnClickListener TerminosDeUsoAcceptados = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            getSupportFragmentManager().beginTransaction().remove(f2).commit();
            Intent i = new Intent(Login_activity.this, Inicio_activity.class);
            startActivity(i);
        }
    };
}
