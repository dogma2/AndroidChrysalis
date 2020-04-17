package com.example.chrysalis.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.ActivityCompat;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Toast;


import com.example.chrysalis.Adaptadores_ItemsDecorations.Adaptador_eventos;
import com.example.chrysalis.Fragments.ApuntarseFragment;
import com.example.chrysalis.Adaptadores_ItemsDecorations.DecoreItem_Adaptador_eventos;
import com.example.chrysalis.Evento;
import com.example.chrysalis.GetInfo;
import com.example.chrysalis.Adaptadores_ItemsDecorations.ItemDecorationRe;
import com.example.chrysalis.R;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.header.progresslayout.ProgressLayout;

import java.util.ArrayList;
import java.util.zip.InflaterInputStream;


public class Inicio_activity extends AppCompatActivity implements ApuntarseFragment.OnFragmentInteractionListener { //Load_Activitys.OnFragmentInteractionListener{

  private RecyclerView recyclerView;
  private ApuntarseFragment apuntarseFragment;

  private Toolbar myToolbar;
  private AppBarLayout appBarLayout;
  private ArrayList<Evento> Eventos;

  private FloatingActionButton configuracion;
  private FloatingActionButton datosInteres;
  private boolean FloatingButtonsDesplegados;
  private MenuItem ItemImg;

  private int cnt = 0;


  private int AlturaBarra;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.layout_inicio_activity);


    myToolbar = findViewById(R.id.materialToolbar);
    appBarLayout = findViewById(R.id.Appbarlayout);
    recyclerView = findViewById(R.id.recycler_view);

    TwinklingRefreshLayout layout = findViewById(R.id.refreshLayout);

    setSupportActionBar(myToolbar);
    myToolbar.setOnMenuItemClickListener(ItemLisener);


    configuracion = findViewById(R.id.floatingActionButton);
    datosInteres = findViewById(R.id.floatingActionButton2);

    configuracion.setVisibility(View.GONE);
    datosInteres.setVisibility(View.GONE);
    FloatingButtonsDesplegados = false;


    configuracion.setOnClickListener(FloatingButtomsListener);
    datosInteres.setOnClickListener(FloatingButtomsListener);





    recyclerView.setHasFixedSize(true);
    final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

    recyclerView.setLayoutManager(layoutManager);
    DecoreItem_Adaptador_eventos view_decoreItem = new DecoreItem_Adaptador_eventos(20);
    recyclerView.addItemDecoration(view_decoreItem);


    ProgressLayout headerView = new ProgressLayout(this);
    layout.setHeaderView(headerView);
    layout.setOnRefreshListener(RefreshLisener);





    appBarLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
      @Override
      public void onGlobalLayout() {
        AlturaBarra = appBarLayout.getHeight();
        appBarLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        System.out.println("AQUI ESTIC ");
        System.out.println(appBarLayout.getHeight());


      }
    });

    recyclerView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
      @Override
      public void onScrollChanged() {
        if(FloatingButtonsDesplegados)
        {
          configuracion.hide();
          datosInteres.hide();
          FloatingButtonsDesplegados = false;
          ItemImg.setIcon(R.drawable.ic_add_black_24dp);
        }



      }
    });


    Eventos = GetInfo.GetEventos();


  Adaptador_eventos adaptador_eventos = new Adaptador_eventos(Eventos, RElistener);

  recyclerView.setAdapter(adaptador_eventos);




  }


  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.bottom_navigation_menu, menu);
    ItemImg = menu.getItem(1);

    recyclerView.addItemDecoration(new ItemDecorationRe(appBarLayout.getHeight()));
    return true;
  }


  @Override
  public void onFragmentInteraction(Uri uri) {

  }


  View.OnClickListener RElistener = new View.OnClickListener() {
    @Override
    public void onClick(View v) {

      switch (v.getId())
      {
        case R.id.action_button_1:

          apuntarseFragment = new ApuntarseFragment(DisAtachLisener);
          getSupportFragmentManager().beginTransaction().add(R.id.FrameLayoutFragmentApuntarse,apuntarseFragment).commit();
          break;

        case R.id.action_button_2:

          Intent i = new Intent(Inicio_activity.this, Detalles_activity.class);
          startActivity(i);
          break;
      }
    }
  };

  View.OnClickListener DisAtachLisener = new View.OnClickListener() {
    @Override
    public void onClick(View v) {

      if(v.getId()==R.id.BotonApuntarse)
      {
        //Fer algo aqui
      }
      getSupportFragmentManager().beginTransaction().remove(apuntarseFragment).commit();

    }
  };

    RefreshListenerAdapter RefreshLisener = new RefreshListenerAdapter() {
      @Override
      public void onPullingDown(TwinklingRefreshLayout refreshLayout, float fraction) {
        super.onPullingDown(refreshLayout, fraction);
        BarSet(fraction);
      }

      @Override
      public void onPullDownReleasing(TwinklingRefreshLayout refreshLayout, float fraction) {
        super.onPullDownReleasing(refreshLayout, fraction);
        BarSet(fraction);
      }

      @Override
      public void onRefresh(final TwinklingRefreshLayout refreshLayout) {
        new Handler().postDelayed(new Runnable() {
          @Override
          public void run() {
            refreshLayout.finishRefreshing();

          }
        },2000);
      }

      @Override
      public void onLoadMore(final TwinklingRefreshLayout refreshLayout) {
        new Handler().postDelayed(new Runnable() {
          @Override
          public void run() {
            refreshLayout.finishLoadmore();
            Eventos.addAll(GetInfo.GetEventos());
            recyclerView.getAdapter().notifyDataSetChanged();
            //Lo que hace para cargar mas eventos
          }
        },2000);
      }

      @Override
      public void onFinishRefresh() {
        super.onFinishRefresh();
        appBarLayout.setExpanded(true);
      }

      @Override
      public void onFinishLoadMore() {
        super.onFinishLoadMore();
        //Cuando acabe de cargar mas eventos
      }
    };

    private void BarSet(float num)
    {
      if(num < 0.20f)
      {
        appBarLayout.setExpanded(true);
      }
      else
      {
        appBarLayout.setExpanded(false);
      }
    }

    Toolbar.OnMenuItemClickListener ItemLisener = new Toolbar.OnMenuItemClickListener() {
      @Override
      public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId())
        {
          case R.id.nav_more:
            if(FloatingButtonsDesplegados)
            {
              item.setIcon(R.drawable.ic_add_black_24dp);
              FloatingButtonsDesplegados = false;
              configuracion.hide();
              datosInteres.hide();

            }
            else
            {
              item.setIcon(R.drawable.ic_remove_black_24dp);
              FloatingButtonsDesplegados = true;
              configuracion.show();
              datosInteres.show();
            }
            break;

          case R.id.nav_sort:

            break;
        }
        return true;
      }
    };
  View.OnClickListener FloatingButtomsListener = new View.OnClickListener() {
    @Override
    public void onClick(View v) {
      switch (v.getId())
      {
        case R.id.floatingActionButton:

          break;

        case R.id.floatingActionButton2: //Configuracion
          Intent i = new Intent(Inicio_activity.this, Ajustes_activity.class);
          startActivity(i);
          break;
      }
    }
  };

  @Override
  public void onBackPressed() {
    if(cnt == 0)
    {
      Toast.makeText(getApplicationContext(), "Presione de Nuevo para Salir",Toast.LENGTH_SHORT).show();
      CountDownTimer c = countDownTimer;
      c.start();
      cnt++;
    }
    else if(cnt == 1)
    {
      //finish();
      //System.exit(0);
      Intent intent = new Intent(getApplicationContext(), Load_Activity.class);
      intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
      intent.putExtra("EXIT", true);
      startActivity(intent);
    }
  }

  private CountDownTimer countDownTimer = new CountDownTimer(3000,1000) {
    @Override
    public void onTick(long millisUntilFinished) {
      System.out.println(millisUntilFinished);
    }

    @Override
    public void onFinish() {
      cnt = 0;
    }
  };

}
