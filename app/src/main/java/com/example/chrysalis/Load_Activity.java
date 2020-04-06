package com.example.chrysalis;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Load_Activity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_load);

       new Handler().postDelayed(new Runnable() {
           @Override
           public void run() {
               Intent i = new Intent(Load_Activity.this, Login_activity.class);
               startActivity(i);
           }
       }, 4000);


    }


}
