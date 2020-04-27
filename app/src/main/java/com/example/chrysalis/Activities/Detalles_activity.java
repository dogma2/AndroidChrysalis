package com.example.chrysalis.Activities;

import android.animation.ObjectAnimator;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;

import com.example.chrysalis.Clases.Evento;
import com.example.chrysalis.Fragments.ApuntarseFragment;
import com.example.chrysalis.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import org.w3c.dom.Text;

import java.io.File;

import static com.example.chrysalis.FixedData.ZIP_DATAS;

public class Detalles_activity extends AppCompatActivity  implements  ApuntarseFragment.OnFragmentInteractionListener {

    private ApuntarseFragment apuntarseFragment;
    private NestedScrollView scroll;
    private int actualScroll;
    private ObjectAnimator animacion;
    private ExtendedFloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_detalles_activity);

        ImageView imagen = findViewById(R.id.imageViewEntrada);
        //final BottomNavigationView Menu = findViewById(R.id.MenuBar);

        floatingActionButton = findViewById(R.id.FlotingButtonMasDetalles);


        TextView textViewTitulo = findViewById(R.id.MD_Titulo);
        TextView textViewNombreDelegacion = findViewById(R.id.MD_delegacion);

        TextView textviewResumen = findViewById(R.id.MD_introducion);

        TextView textViewActivDescripcion = findViewById(R.id.MD_descripcionview);
        TextView textViewActivFechas = findViewById(R.id.MD_fechahoraviwer);
        TextView textViewActivUbicacion = findViewById(R.id.MD_ubicacionview);

        TextView textViewDescripcion = findViewById(R.id.MD_descripcion);
        TextView textViewFecha = findViewById(R.id.MD_fechayhora);
        TextView textViewUbicacion = findViewById(R.id.MD_ubicacion);

        TextView textViewActivNotas = findViewById(R.id.MD_notasactiv);
        TextView textviewnotas = findViewById(R.id.MD_notas);


        textViewActivDescripcion.setOnClickListener(textos);
        textViewActivFechas.setOnClickListener(textos);
        textViewActivUbicacion.setOnClickListener(textos);
        textViewActivNotas.setOnClickListener(textos);

        Bundle extras = getIntent().getExtras();
        int num = extras.getInt("KEY");
        Evento e = Evento.getEvento(getApplicationContext(),num);


        if(new File(ZIP_DATAS + "/" + "Evento"+e.getId()+".json").exists())
        {
            Bitmap imagne = BitmapFactory.decodeFile(ZIP_DATAS + "/" + "Images"+ "/" + "Evento"+e.getId()+".jpg");
            imagen.setImageBitmap(imagne);
        }


        textViewTitulo.setText(e.getTitulo());
        textviewResumen.setText(e.getIntro());
        textViewDescripcion.setText(e.getDescripcion());
        StringBuilder fechas = new StringBuilder();
        fechas.append("Fecha Evento : " + e.getFechaInicio() + "\n");
        fechas.append("Hora inicio: " + e.getHoraInicio() + "\n");
        if(!e.getFechaInicio().equals(e.getHoraFin()))
        {
            fechas.append("Fecha fin:"+e.getFechaFin()+"\n");
        }
        else
        {
            fechas.append("Acaba a las: " + e.getHoraFin()+"\n");
        }
        textViewFecha.setText(fechas.toString());

        StringBuilder ubicacion = new StringBuilder();
        ubicacion.append("Ciudad: "+e.getCiudad()+"\n");
        ubicacion.append(""+ e.getCoordGPS()+"\n");
        textViewUbicacion.setText(ubicacion.toString());

        StringBuilder Notas = new StringBuilder();
        Notas.append("Notas adicionales : \n");
        Notas.append(e.getNotaEvento() + "\n");
        if(!e.getNotaTransporte().equals(""))
        {
            Notas.append("Notas de transporte : \n");
            Notas.append(e.getNotaTransporte()+ "\n");
        }
       textviewnotas.setText(Notas.toString());
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
                apuntarseFragment = new ApuntarseFragment(DisAtachLisener);
                getSupportFragmentManager().beginTransaction().add(R.id.FrameLayoutFragmentApuntarse2,apuntarseFragment).commit();
            }
        });
}

    private View.OnClickListener textos = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            TextView t = (TextView) v;

            View texto = findViewById(v.getLabelFor());
            if(texto.getVisibility() == View.VISIBLE)
            {
                texto.setVisibility(View.GONE);

                t.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_expand_more_black_36dp, 0);
            }
            else
            {
                texto.setVisibility(View.VISIBLE);
                t.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_expand_less_black_36dp, 0);
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

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
