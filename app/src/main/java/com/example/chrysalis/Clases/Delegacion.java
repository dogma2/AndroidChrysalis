package com.example.chrysalis.Clases;

import android.content.Context;
import android.util.Log;

import com.example.chrysalis.Public_Methods;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.File;
import java.util.ArrayList;
import static com.example.chrysalis.FixedData.*;

public class Delegacion {

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - attributes
    private int m_id;
    private String m_nombre;
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - contructor
    public Delegacion() { }
    public Delegacion(int id, String nombre) {
        this.m_id = id;
        this.m_nombre = nombre;
    }
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - getters
    public int getId() { return this.m_id; }
    public String getNombre() { return this.m_nombre; }
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    public String toString() { return this.m_id+": "+this.m_nombre+"\n"; }
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    public static void listaPrint(ArrayList<Delegacion> olist) {
        String content = "";
        for(Delegacion o : olist){
            content += o.getId()+": "+o.getNombre()+"\n";
        }
        Log.wtf("MAGOMO", "olist: \n" + content);
    }
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - getDelegaciones ->
    public static ArrayList<Delegacion> getDelegaciones(Context thecontext) {
        ArrayList<Delegacion> objeto = new ArrayList<>();

        if (new File(DIR_XTRAS + "Delegaciones.json").exists()) {

            String jsonString = Public_Methods.loadFileContent(DIR_XTRAS + "Delegaciones.json", thecontext);

            if (!jsonString.equals("")) {

                try {

                    JSONObject JSobj = new JSONObject(jsonString);

                    if(JSobj!=null) {

                        for (int i = 0; i < JSobj.length(); i++) {

                            objeto.add(new Delegacion(JSobj.getInt("id"), JSobj.getString("nombre")));
                        }
                    }
                }
                catch (JSONException ex) { Log.wtf("MAGOMO", "getDelegaciones -> Error JSONObject: " + ex.toString()); }
            }
        }

        return objeto;
    }
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - getDelegaciones //
/*  // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - getDELEGACIONES ->
    public static ArrayList<Delegacion> getDelegaciones(Context thecontext) {
        ArrayList<Delegacion> listOb = new ArrayList<>();
        String jsonString = Public_Methods.loadFileContent(DIR_XTRAS + "Delegaciones.json",thecontext);
        if (!jsonString.equals("")) {
            String gsoniano = "[{\"id\":12,\"nombre\":\"Catalunya\"},{\"id\":13,\"nombre\":\"Centro (CastillaLeon)\"},{\"id\":15,\"nombre\":\"Extremadura\"},{\"id\":14,\"nombre\":\"Norte (Galicia)\"}]";
            try {
                JSONArray jsonArr = new JSONArray(gsoniano);
                for (int i = 0; i < jsonArr.length(); i++) {
                    JSONObject jsonObj = jsonArr.getJSONObject(i);
                    listOb.add(new Delegacion(jsonObj.getInt("id"), jsonObj.getString("nombre")));
                }
            } catch (JSONException ex) { Log.wtf("MAGOMO", "getDelegaciones -> Error JSONObject: " + ex.toString()); }
        }
        return listOb;
    }
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - getDELEGACIONES //  */
}

