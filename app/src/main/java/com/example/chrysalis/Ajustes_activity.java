package com.example.chrysalis;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Switch;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Ajustes_activity extends AppCompatActivity //Falta implementar el onfragmentinteraction
{

    SelectSpectsFragment SelectedFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_configuracion_activity);

        Switch switchProvincias = findViewById(R.id.SwitchConfigProvincia);
        Switch switchAutonomias = findViewById(R.id.SwitchConfigAutonomia);
        Switch switchDelegacion = findViewById(R.id.SwitchConfigDelegacion);


        switchProvincias.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    SelectedFragment = new SelectSpectsFragment(SelectSpectsFragment.ISPROVINCIA, lisenerBotonGuardar);
                    getSupportFragmentManager().beginTransaction().add(R.id.FrameLayoutFragmentSelectSpects, SelectedFragment).commit();
                }

            }
        });
        switchAutonomias.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    SelectedFragment = new SelectSpectsFragment(SelectSpectsFragment.ISAUTONOMIA,lisenerBotonGuardar);
                    getSupportFragmentManager().beginTransaction().add(R.id.FrameLayoutFragmentSelectSpects, SelectedFragment).commit();
                }
            }
        });

        switchDelegacion.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    SelectedFragment = new SelectSpectsFragment(SelectSpectsFragment.ISDELEGACION,lisenerBotonGuardar);
                    getSupportFragmentManager().beginTransaction().add(R.id.FrameLayoutFragmentSelectSpects, SelectedFragment).commit();
                }

            }
        });


    }
    View.OnClickListener lisenerBotonGuardar = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            getSupportFragmentManager().beginTransaction().remove(SelectedFragment).commit();
        }
    };

}