package com.example.chrysalis.Adaptadores_ItemsDecorations;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.chrysalis.Evento;
import com.example.chrysalis.GetInfo;
import com.example.chrysalis.R;

import java.util.ArrayList;



public class Adaptador_eventos extends RecyclerView.Adapter<Adaptador_eventos.ViewHolderDatos> {

    ArrayList<Evento> eventos;
    View.OnClickListener Lisener;
    Boolean UserOnlyDelegation = GetInfo.GetIsOnlyUserDelegacion();


    public Adaptador_eventos(ArrayList<Evento> eventos, View.OnClickListener lisener) {
        this.eventos = eventos;
        this.Lisener = lisener;
    }



    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(UserOnlyDelegation)
        {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_card_evento_v2s, parent,false);
            return new ViewHolderDatos(view);

        }
        else
        {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_card_eventos, parent,false);
            return new ViewHolderDatos(view);
        }


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


        public ViewHolderDatos(@NonNull final View itemView) {
            super(itemView);
            if(UserOnlyDelegation)
            {

            }
            img = itemView.findViewById(R.id.media_image);
            NombreEvento = itemView.findViewById(R.id.primary_text);
            DelegacionEvento = itemView.findViewById(R.id.sub_text);
            DescripcionEvento = itemView.findViewById(R.id.supporting_text);
            imageButton = itemView.findViewById(R.id.expand_button);

            Apuntarse = itemView.findViewById(R.id.action_button_1);
            MasDetalles = itemView.findViewById(R.id.action_button_2);
            final int tiempo = itemView.getResources().getInteger(android.R.integer.config_shortAnimTime);
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
            NombreEvento.setText(evento.getNombre());
            DescripcionEvento.setText(evento.getDescripcion());
            img.setImageResource(evento.getImg());
        }


    }
}
