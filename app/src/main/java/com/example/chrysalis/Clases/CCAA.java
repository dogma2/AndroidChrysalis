package com.example.chrysalis.Clases;

import android.content.Context;
import android.util.Log;

import com.example.chrysalis.Public_Methods;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.File;
import java.util.ArrayList;
import static com.example.chrysalis.FixedData.DIR_XTRAS;

public class CCAA {

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - attributes
    private int m_id;
    private String m_nombre;
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - contructor
    public CCAA() { }
    public CCAA(int id, String nombre) {
        this.m_id = id;
        this.m_nombre = nombre;
    }
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - getters
    public int getId() { return this.m_id; }
    public String getNombre() { return this.m_nombre; }
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    public String toString() { return this.m_id+": "+this.m_nombre+"\n"; }
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    public static void listaPrint(ArrayList<CCAA> olist) {
        String content = "";
        for(CCAA o : olist){
            content += o.getId()+": "+o.getNombre()+"\n";
        }
        Log.wtf("MAGOMO", "olist: \n" + content);
    }
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - getCCAA ->
    public static ArrayList<CCAA> getCCAA(Context thecontext) {
        ArrayList<CCAA> objeto = new ArrayList<>();

        if (new File(DIR_XTRAS + "Comunidades.json").exists()) {

            String jsonString = Public_Methods.loadFileContent(DIR_XTRAS + "Comunidades.json", thecontext);

            if (!jsonString.equals("")) {

                try {

                    JSONObject JSobj = new JSONObject(jsonString);

                    if(JSobj!=null) {

                        for (int i = 0; i < JSobj.length(); i++) {

                            objeto.add(new CCAA(JSobj.getInt("id"), JSobj.getString("nombre")));
                        }
                    }
                }
                catch (JSONException ex) { Log.wtf("MAGOMO", "getDelegaciones -> Error JSONObject: " + ex.toString()); }
            }
        }

        return objeto;
    }
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - getCCAA //

}
