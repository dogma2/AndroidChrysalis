package com.example.chrysalis.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.chrysalis.R;
import com.example.chrysalis.Fragments.SelectSpectsFragment;
import com.google.android.material.appbar.MaterialToolbar;

public class Ajustes_activity extends AppCompatActivity //Falta implementar el onfragmentinteraction
{


    SelectSpectsFragment SelectedFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_configuracion_activity);

        MaterialToolbar toolbar = findViewById(R.id.ToolBarConfig);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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