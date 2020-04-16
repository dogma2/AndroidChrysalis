package com.example.chrysalis;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.admin.DeviceAdminInfo;
import android.app.admin.DevicePolicyManager;
import android.bluetooth.BluetoothClass;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationMenu;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.util.ArrayList;

public class Detalles_activity extends AppCompatActivity  {

    NestedScrollView scroll;
    int actualScroll;
    private ObjectAnimator animacion;
    ExtendedFloatingActionButton floatingActionButton;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_detalles_activity);

        ImageView imagen = findViewById(R.id.imageViewEntrada);
        //final BottomNavigationView Menu = findViewById(R.id.MenuBar);
        TextView info = findViewById(R.id.textViewInfo);
        floatingActionButton = findViewById(R.id.FlotingButtonMasDetalles);

        MaterialToolbar barra = findViewById(R.id.materialToolbarMasDetalles);


        setSupportActionBar(barra);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);




        barra.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                System.out.println("click");
                System.out.println(item.getItemId());
                return true;
            }
        });

        String SuperString="";
        for(int cnt = 0; cnt < 100; cnt++)
        {
            if(cnt > 0)
            {
                SuperString += (cnt+1) + " Cacatuas" +"\n";
            }
            else
            {
                SuperString += (cnt+1) + " Cacatua" +"\n";
            }



        }
        info.setText(SuperString);

        scroll = findViewById(R.id.scrollViewMasDetalles);


        scroll.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                int lastScroll = scroll.getScrollY();
                System.out.println(lastScroll);
                if(actualScroll < lastScroll)
                {
                    floatingActionButton.hide();

                }
                else
                    {
                        floatingActionButton.show();
                        //floatingActionButton.setVisibility(View.VISIBLE);
                }


                actualScroll = lastScroll;
            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
}



    public void SetAnimationDesaparecer()
    {
        animacion = ObjectAnimator.ofFloat(floatingActionButton, View.ALPHA, 1.0f, 0.0f);
        animacion.setDuration(200);
        AnimatorSet animatorSetAlpha = new AnimatorSet();
        animatorSetAlpha.play(animacion);
        animatorSetAlpha.start();

        floatingActionButton.setVisibility(View.GONE);
    }
    public void SetAnimationAparecer()
    {
        floatingActionButton.setVisibility(View.VISIBLE);
        animacion = ObjectAnimator.ofFloat(floatingActionButton, View.ALPHA, 0.0f, 1.0f);
        animacion.setDuration(200);

        AnimatorSet animatorSetAlpha = new AnimatorSet();
        animatorSetAlpha.play(animacion);
        animatorSetAlpha.start();
    }

    }
