package com.example.chrysalis;

import android.app.admin.DeviceAdminInfo;
import android.app.admin.DevicePolicyManager;
import android.bluetooth.BluetoothClass;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationMenu;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

public class Detalles_activity extends AppCompatActivity  {

    ScrollView scroll;
    int actualScroll;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_detalles_activity);

        ImageView imagen = findViewById(R.id.imageViewEntrada);
        //final BottomNavigationView Menu = findViewById(R.id.MenuBar);
        TextView info = findViewById(R.id.textViewInfo);
        final ExtendedFloatingActionButton floatingActionButton = findViewById(R.id.FlotingButtonMasDetalles);

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
                    floatingActionButton.setVisibility(View.GONE);
                }
                else
                    {
                        floatingActionButton.setVisibility(View.VISIBLE);
                }

                actualScroll = lastScroll;
            }
        });


}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.bottom_navigation_menu, menu);
        return true;
    }

    }
