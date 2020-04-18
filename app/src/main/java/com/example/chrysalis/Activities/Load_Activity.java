package com.example.chrysalis.Activities;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.chrysalis.Permisos;
import com.example.chrysalis.R;

public class Load_Activity extends AppCompatActivity {

    private Permisos p;
    boolean isUserInteracting;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_load);
        p = new Permisos(this);


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
}
