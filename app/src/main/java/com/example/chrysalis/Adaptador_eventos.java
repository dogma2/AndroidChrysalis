package com.example.chrysalis;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



import java.util.ArrayList;



public class Adaptador_eventos extends RecyclerView.Adapter<Adaptador_eventos.ViewHolderDatos> {

    ArrayList<Evento> eventos;
    Context contexto;



    public Adaptador_eventos(ArrayList<Evento> eventos) {
        this.eventos = eventos;
    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_card_eventos, parent,false);
        contexto = view.getContext();
        return new ViewHolderDatos(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
        holder.asignarDatos(eventos.get(position));
        holder.setOnClickLiseners();
    }

    @Override
    public int getItemCount() {
        return eventos.size();
    }



    public class ViewHolderDatos extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView img;
        TextView NombreEvento;
        TextView DelegacionEvento;
        TextView DescripcionEvento;
        ImageButton imageButton;

        Button Apuntarse;
        Button MasDetalles;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.media_image);
            NombreEvento = itemView.findViewById(R.id.primary_text);
            DelegacionEvento = itemView.findViewById(R.id.sub_text);
            DescripcionEvento = itemView.findViewById(R.id.supporting_text);
            imageButton = itemView.findViewById(R.id.expand_button);

            Apuntarse = itemView.findViewById(R.id.action_button_1);
            MasDetalles = itemView.findViewById(R.id.action_button_2);

            DescripcionEvento.setVisibility(View.GONE);
            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(DescripcionEvento.getVisibility() == View.VISIBLE)
                    {
                        imageButton.setImageResource(R.drawable.ic_expand_more_black_36dp);
                        DescripcionEvento.setVisibility(View.GONE);
                    }
                    else
                    {
                        imageButton.setImageResource(R.drawable.ic_expand_less_black_36dp);
                        DescripcionEvento.setVisibility(View.VISIBLE);
                    }
                }
            });
        }
        @Override
        public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.action_button_1:
                ApuntarseFragment.vista.setVisibility(View.VISIBLE);
                break;
            case R.id.action_button_2:

                Intent intento = new Intent(contexto, Detalles_activity.class);
                contexto.startActivity(intento);
                break;

        }

        // ApuntarseFragment.vista.setVisibility(View.VISIBLE);
        }

        public void asignarDatos(Evento evento) {
            NombreEvento.setText(evento.Nombre);
            DescripcionEvento.setText(evento.Descripcion);
            img.setImageResource(evento.Img);
        }

        public void setOnClickLiseners() {

            Apuntarse.setOnClickListener(this);
            MasDetalles.setOnClickListener(this);
        }
    }
}
