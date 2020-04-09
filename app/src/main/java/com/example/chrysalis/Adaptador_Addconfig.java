package com.example.chrysalis;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Adaptador_Addconfig extends RecyclerView.Adapter<Adaptador_Addconfig.ViewHolderDatos> {

    ArrayList<String> ListaLoQueSea;
    ArrayList<String> ListaLoQueSeaTodo;
    Context context;

    public Adaptador_Addconfig(ArrayList<String> listaLoQueSea, ArrayList<String> listaLoQueSeaTodo) {
        ListaLoQueSea = listaLoQueSea;
        ListaLoQueSeaTodo = listaLoQueSeaTodo;
    }


    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            context = parent.getContext();
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_addconfig_inf1,parent,false);
            return new ViewHolderDatos(v);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
        holder.asignarDatos(ListaLoQueSeaTodo.get(position));

    }

    @Override
    public int getItemCount() {
        return ListaLoQueSeaTodo.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {

        Switch botone;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            botone = itemView.findViewById(R.id.switchConfigElementsLista);

        }

        public void asignarDatos(String s) {
            botone.setText(s);
            if(ListaLoQueSea.contains(s))
            {
                botone.setChecked(true);
            }
        }



    }
}
