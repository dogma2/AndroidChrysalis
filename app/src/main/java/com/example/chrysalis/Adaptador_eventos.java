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
    View.OnClickListener Lisener;



    public Adaptador_eventos(ArrayList<Evento> eventos, View.OnClickListener lisener) {
        this.eventos = eventos;
        this.Lisener = lisener;
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

         holder.Apuntarse.setOnClickListener(Lisener);
         holder.MasDetalles.setOnClickListener(Lisener);

    }

    @Override
    public int getItemCount() {
        return eventos.size();
    }





    public class ViewHolderDatos extends RecyclerView.ViewHolder{
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




        public void asignarDatos(Evento evento) {
            NombreEvento.setText(evento.Nombre);
            DescripcionEvento.setText(evento.Descripcion);
            img.setImageResource(evento.Img);
        }


    }
}
