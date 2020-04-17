package com.example.chrysalis.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.chrysalis.R;

public class Load_Activity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_load);

        if (getIntent().getBooleanExtra("EXIT", false)) {
            finish();
        }
        else
        {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    //Pregunta a la base de datos el IMEI
                    //Compara con el IMEI ACTUAL

                    Intent i = new Intent(Load_Activity.this, Login_activity.class);
                    startActivity(i);
                }
            }, 4000);

        }



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Intent i = new Intent(Load_Activity.this, Login_activity.class);
    }
}
