package com.example.chrysalis;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;


import java.util.ArrayList;
import java.util.List;

public class Inicio_activity extends AppCompatActivity implements ApuntarseFragment.OnFragmentInteractionListener{ //Load_Activitys.OnFragmentInteractionListener{

  RecyclerView recyclerView;
  ApuntarseFragment apuntarseFragment;



  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.layout_inicio_activity);


    //Instanciamos el fragment, en un futuro pasaremos el evento como parametro para hacer saber al fragment a que evento se quiere apuntar el usuario


    //Aqui Abrimos el fragment en la pantalla para que se muestre.


    recyclerView = findViewById(R.id.recycler_view);
    recyclerView.setHasFixedSize(true);
    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
    recyclerView.setLayoutManager(layoutManager);
    DecoreItem_Adaptador_eventos view_decoreItem = new DecoreItem_Adaptador_eventos(20);
    recyclerView.addItemDecoration(view_decoreItem);




    Toolbar myToolbar = (Toolbar) findViewById(R.id.materialToolbar);
    setSupportActionBar(myToolbar);


    Evento e = new Evento("Dia de Playa","Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed feugiat ante nunc, at tincidunt ex fermentum eu. Cras quis ultricies erat. Vestibulum in elementum lacus. Donec lacinia eros ac mi feugiat, sit amet egestas ipsum maximus. Nullam blandit vestibulum semper. Quisque sapien lorem, eleifend ut vestibulum vitae, iaculis vitae magna. Phasellus a varius mauris. In et neque semper quam euismod vulputate. Aliquam aliquet est vel lectus dignissim tempus. Praesent egestas malesuada nulla ut maximus. Morbi id fringilla odio, a mattis risus.",R.drawable.calabazas);
    Evento e2 = new Evento("Excursion a la Montaña", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed feugiat ante nunc, at tincidunt ex fermentum eu. Cras quis ultricies erat. Vestibulum in elementum lacus. Donec lacinia eros ac mi feugiat, sit amet egestas ipsum maximus. Nullam blandit vestibulum semper. Quisque sapien lorem, eleifend ut vestibulum vitae, iaculis vitae magna. Phasellus a varius mauris. In et neque semper quam euismod vulputate. Aliquam aliquet est vel lectus dignissim tempus. Praesent egestas malesuada nulla ut maximus. Morbi id fringilla odio, a mattis risus.",R.drawable.calabazas);
    Evento e3 = new Evento("Excursion a la Nieve","Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed feugiat ante nunc, at tincidunt ex fermentum eu. Cras quis ultricies erat. Vestibulum in elementum lacus. Donec lacinia eros ac mi feugiat, sit amet egestas ipsum maximus. Nullam blandit vestibulum semper. Quisque sapien lorem, eleifend ut vestibulum vitae, iaculis vitae magna. Phasellus a varius mauris. In et neque semper quam euismod vulputate. Aliquam aliquet est vel lectus dignissim tempus. Praesent egestas malesuada nulla ut maximus. Morbi id fringilla odio, a mattis risus.",R.drawable.calabazas);
    Evento e4 = new Evento("Feria de Calabazas","Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed feugiat ante nunc, at tincidunt ex fermentum eu. Cras quis ultricies erat. Vestibulum in elementum lacus. Donec lacinia eros ac mi feugiat, sit amet egestas ipsum maximus. Nullam blandit vestibulum semper. Quisque sapien lorem, eleifend ut vestibulum vitae, iaculis vitae magna. Phasellus a varius mauris. In et neque semper quam euismod vulputate. Aliquam aliquet est vel lectus dignissim tempus. Praesent egestas malesuada nulla ut maximus. Morbi id fringilla odio, a mattis risus.",R.drawable.calabazas);

    ArrayList<Evento> eventos = new ArrayList<>();
    eventos.add(e);
    eventos.add(e2);
    eventos.add(e3);
    eventos.add(e4);

  Adaptador_eventos adaptador_eventos = new Adaptador_eventos(eventos, RElistener);

  recyclerView.setAdapter(adaptador_eventos);




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


}
