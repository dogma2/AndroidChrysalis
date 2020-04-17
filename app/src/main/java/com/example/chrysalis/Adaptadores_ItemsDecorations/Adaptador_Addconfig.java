package com.example.chrysalis.Adaptadores_ItemsDecorations;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.chrysalis.R;

import java.util.ArrayList;

public class Adaptador_Addconfig extends RecyclerView.Adapter<Adaptador_Addconfig.ViewHolderDatos> {

    private ArrayList<String> ListaLoQueSea;
    private ArrayList<String> ListaLoQueSeaTodo;

    public ArrayList<String> getActualListaUser() {
        return ListaLoQueSea;
    }

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
    CompoundButton.OnCheckedChangeListener ChekedChange = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if(isChecked)
            {
                ListaLoQueSea.add(buttonView.getText().toString());
                System.out.println(buttonView.getText().toString() + " He cambiado a true");
            }
            else
            {
                //System.out.println(ListaLoQueSeaTodo.get(actualHolderPosition) + " He cambiado a false");
                ListaLoQueSea.remove(buttonView.getText().toString());
            }
        }
    };

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
            botone.setOnCheckedChangeListener(null);
            botone.setText(s);

            if(ListaLoQueSea.contains(s))
            {
                botone.setChecked(true);
            }
            else
            {
                botone.setChecked(false);
            }
            botone.setOnCheckedChangeListener(ChekedChange);
        }



    }
}
