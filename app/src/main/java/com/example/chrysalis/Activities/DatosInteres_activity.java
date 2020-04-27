package com.example.chrysalis.Activities;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chrysalis.Adaptadores_ItemsDecorations.Adaptador_datosInteres;
import com.example.chrysalis.Clases.DatoInteres;
import com.example.chrysalis.R;

public class DatosInteres_activity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_datosinteres_activity);

        RecyclerView recyclerView = findViewById(R.id.recyclerview_datosInteres);
        Adaptador_datosInteres adaptador = new Adaptador_datosInteres(DatoInteres.getDatosInteres(getApplicationContext()));
        recyclerView.setAdapter(adaptador);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}
