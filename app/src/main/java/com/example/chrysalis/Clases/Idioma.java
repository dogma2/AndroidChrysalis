package com.example.chrysalis.Clases;

import android.content.Context;
import android.util.Log;

import com.example.chrysalis.Public_Methods;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.File;
import java.util.ArrayList;
import static com.example.chrysalis.FixedData.DIR_XTRAS;

public class Idioma {

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - attributes
    private int m_id;
    private String m_nombre;
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - contructor
    public Idioma() { }
    public Idioma(int id, String nombre) {
        this.m_id = id;
        this.m_nombre = nombre;
    }
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - getters
    public int getId() { return this.m_id; }
    public String getNombre() { return this.m_nombre; }
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    public String toString() { return this.m_id+": "+this.m_nombre+"\n"; }
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    public static void listaPrint(ArrayList<Idioma> olist) {
        String content = "";
        for(Idioma o : olist){
            content += o.getId()+": "+o.getNombre()+"\n";
        }
        Log.wtf("MAGOMO", "olist: \n" + content);
    }
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - getIdiomas ->
    public static ArrayList<Idioma> getIdiomas(Context thecontext) {
        ArrayList<Idioma> objeto = new ArrayList<>();

        if (new File(DIR_XTRAS + "Idiomas.json").exists()) {

            String jsonString = Public_Methods.loadFileContent(DIR_XTRAS + "Idiomas.json", thecontext);

            if (!jsonString.equals("")) {

                try {

                    JSONObject JSobj = new JSONObject(jsonString);

                    if(JSobj!=null) {

                        for (int i = 0; i < JSobj.length(); i++) {

                            objeto.add(new Idioma(JSobj.getInt("id"), JSobj.getString("nombre")));
                        }
                    }
                }
                catch (JSONException ex) { Log.wtf("MAGOMO", "getIdioma -> Error JSONObject: " + ex.toString()); }
            }
        }

        return objeto;
    }
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - getIdiomas //

}
