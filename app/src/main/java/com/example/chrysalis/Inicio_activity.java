package com.example.chrysalis;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;


import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.navigation.NavigationView;
import com.lcodecore.tkrefreshlayout.IBottomView;
import com.lcodecore.tkrefreshlayout.OnGestureListener;
import com.lcodecore.tkrefreshlayout.PullListener;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.header.progresslayout.ProgressLayout;
import com.lcodecore.tkrefreshlayout.processor.IDecorator;

import java.util.ArrayList;
import java.util.List;



public class Inicio_activity extends AppCompatActivity implements ApuntarseFragment.OnFragmentInteractionListener{ //Load_Activitys.OnFragmentInteractionListener{

  private RecyclerView recyclerView;
  private ApuntarseFragment apuntarseFragment;

  private Toolbar myToolbar;
  private AppBarLayout appBarLayout;
  private ArrayList<Evento> Eventos;

  private int AlturaBarra;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.layout_inicio_activity);


    myToolbar = (Toolbar) findViewById(R.id.materialToolbar);
    appBarLayout = findViewById(R.id.Appbarlayout);
    recyclerView = findViewById(R.id.recycler_view);

    TwinklingRefreshLayout layout = findViewById(R.id.refreshLayout);

    setSupportActionBar(myToolbar);


    myToolbar.inflateMenu(R.menu.bottom_navigation_menu);

    myToolbar.setOnMenuItemClickListener(ItemLisener);




    recyclerView.setHasFixedSize(true);
    final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

    recyclerView.setLayoutManager(layoutManager);
    DecoreItem_Adaptador_eventos view_decoreItem = new DecoreItem_Adaptador_eventos(20);
    recyclerView.addItemDecoration(view_decoreItem);




    ProgressLayout headerView = new ProgressLayout(this);
    layout.setHeaderView(headerView);


    layout.setOnRefreshListener(RefreshLisener);



    //appBarLayout.setExpanded(true);

    System.out.println(appBarLayout.getMeasuredHeight());
    appBarLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
      @Override
      public void onGlobalLayout() {
        AlturaBarra = appBarLayout.getHeight();
        appBarLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        System.out.println("AQUI ESTIC ");
        System.out.println(appBarLayout.getHeight());

        recyclerView.addItemDecoration(new ItemDecorationRe(appBarLayout.getHeight()));
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
          case R.id.nav_configuracion:
            Intent i = new Intent(Inicio_activity.this, Ajustes_activity.class);
            startActivity(i);
            break;

          case R.id.nav_datosInteres:

            break;

          case R.id.nav_sort:

            break;
        }
        return true;
      }
    };


}
