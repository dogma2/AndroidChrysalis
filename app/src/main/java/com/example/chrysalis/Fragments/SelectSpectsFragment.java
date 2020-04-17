package com.example.chrysalis.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import com.example.chrysalis.Adaptadores_ItemsDecorations.Adaptador_Addconfig;
import com.example.chrysalis.GetInfo;
import com.example.chrysalis.R;

import java.util.ArrayList;


public class SelectSpectsFragment extends Fragment {

    public static final int ISPROVINCIA = 0;
    public static final int ISAUTONOMIA = 1;
    public static final int ISDELEGACION = 2;

    // TODO: Rename and change types of parameters
    private RecyclerView recyclerView;
    private int ObjectType;
    private View.OnClickListener Lisener;

    public int getObjectType() {
        return ObjectType;
    }

    public SelectSpectsFragment(int objectType, View.OnClickListener lisener) {
        // Required empty public constructor
        ObjectType = objectType;
        Lisener = lisener;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("Me destruyoo");
        ArrayList<String> Lista = ((Adaptador_Addconfig) recyclerView.getAdapter()).getActualListaUser();
        switch(ObjectType)
        {
            case ISPROVINCIA:
                GetInfo.setProvinciasUser(Lista);
                break;
            case ISAUTONOMIA:
                GetInfo.setAutonomiasUser(Lista); //
                break;
            case ISDELEGACION:
                GetInfo.setDelegacionesUser(Lista);
                break;
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_select_spects, container, false);
        Button botonGuardar = v.findViewById(R.id.BotonAcceptarconfig);
        TextView texto = v.findViewById(R.id.textViewElementoFragmentSelectSpects);

        recyclerView = v.findViewById(R.id.RecyclerViewSpects);
        recyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));
        Adaptador_Addconfig adaptador = null;

        switch (ObjectType)
        {
            case ISPROVINCIA:
                texto.setText("Provincias Disponibles: ");
                adaptador = new Adaptador_Addconfig(GetInfo.GetProvinciasUser(), GetInfo.GetProvincias() );

                break;
            case ISAUTONOMIA:
                texto.setText("Comunidades Autonomas Disponibles: ");
                adaptador = new Adaptador_Addconfig(GetInfo.GetComunidadesUser(),GetInfo.GetComunidadesAutonomas());
                break;
            case ISDELEGACION:
                texto.setText("Delegaciones Disponibles: ");
                adaptador = new Adaptador_Addconfig(GetInfo.GetDelegacionesUser(), GetInfo.GetDelegaciones());
                break;
        }
        recyclerView.setAdapter(adaptador);

        botonGuardar.setOnClickListener(Lisener);
        return v;
    }
}
