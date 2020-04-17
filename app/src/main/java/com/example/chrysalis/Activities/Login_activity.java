package com.example.chrysalis.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.chrysalis.GetInfo;
import com.example.chrysalis.R;
import com.example.chrysalis.Fragments.TerminosDeUsoFragment;
import com.example.chrysalis.Fragments.VerificarCodigoFragment;

public class Login_activity extends AppCompatActivity {
    private VerificarCodigoFragment f;
    private TerminosDeUsoFragment f2;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login_activity);


        Button boton = findViewById(R.id.buttonActivar);


        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Verificar que el correo este en la base de datos.

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
        if(f.GetInputText().equals(GetInfo.GetHashLCode()))
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
            getSupportFragmentManager().beginTransaction().remove(f2).commit();
            Intent i = new Intent(Login_activity.this, Inicio_activity.class);
            startActivity(i);
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
