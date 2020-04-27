package com.example.chrysalis.Adaptadores_ItemsDecorations;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chrysalis.Clases.DatoInteres;
import com.example.chrysalis.R;

import java.util.ArrayList;

public class Adaptador_datosInteres extends RecyclerView.Adapter<Adaptador_datosInteres.ViewHolderDatosInteres> {

    private ArrayList<DatoInteres> datosinteres;

    public Adaptador_datosInteres(ArrayList<DatoInteres> datosinteres) {
        this.datosinteres = datosinteres;
    }

    @NonNull
    @Override
    public ViewHolderDatosInteres onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_card_datosdeinteres, parent, false);
        return new ViewHolderDatosInteres(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatosInteres holder, int position) {
        holder.asignarDatos(datosinteres.get(position));
    }

    @Override
    public int getItemCount() {
        return datosinteres.size();
    }

    public class ViewHolderDatosInteres extends RecyclerView.ViewHolder {
        TextView titulo;
        TextView descripcion;
        TextView codigopostal;
        TextView ciudad;
        TextView MASDATOS;
        TextView personadecontacto;
        TextView telefono;
        TextView email;
        TextView direccion;
        View constraintLayout;

        public ViewHolderDatosInteres(@NonNull View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.DI_nombredato);
            descripcion = itemView.findViewById(R.id.DI_descricpion);
            codigopostal = itemView.findViewById(R.id.DI_codigopostal);
            ciudad = itemView.findViewById(R.id.DI_ciudad);
            MASDATOS = itemView.findViewById(R.id.DI_informacionextra);
            personadecontacto = itemView.findViewById(R.id.DI_personadecontacto);
            telefono = itemView.findViewById(R.id.DI_telefono);
            email = itemView.findViewById(R.id.DI_email);
            direccion = itemView.findViewById(R.id.DI_direccion);
            constraintLayout = itemView.findViewById(R.id.constraintLayout);

            constraintLayout.setVisibility(View.GONE);
            MASDATOS.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(constraintLayout.getVisibility() == View.VISIBLE)
                    {
                        constraintLayout.setVisibility(View.GONE);

                        MASDATOS.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_expand_more_black_36dp, 0);
                    }
                    else
                    {
                        constraintLayout.setVisibility(View.VISIBLE);
                        MASDATOS.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_expand_less_black_36dp, 0);
                    }
                }
            });
        }

        public void asignarDatos(DatoInteres datoInteres) {
            titulo.setText(datoInteres.getNombre());
            descripcion.setText(datoInteres.getDescripcion());
            codigopostal.setText(datoInteres.getCP());
            ciudad.setText(datoInteres.getCiudad());
            personadecontacto.setText("Nombre Contacto: "+datoInteres.getContacto());
            telefono.setText("Tel: " +datoInteres.getTelefono());
            email.setText("Email: "+ datoInteres.getEmail());
            direccion.setText("Direccion: "+datoInteres.getDireccion());

        }
    }
}
