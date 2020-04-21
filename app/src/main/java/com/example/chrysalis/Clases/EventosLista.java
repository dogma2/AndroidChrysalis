package com.example.chrysalis.Clases;

import android.content.Context;
import android.util.Log;

import com.example.chrysalis.Public_Methods;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;

import static com.example.chrysalis.FixedData.DIR_XTRAS;

public class EventosLista {

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - attributes
    private int m_id;
    private String m_titulo;
    private String m_intro;
    private String m_fechainicio;
    private String m_horainicio;
    private int m_asist;

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - contructor
    public EventosLista() { }
    public EventosLista( int id, String titulo, String intro, String fechainicio, String horainicio, int asist ) {
        this.m_id = id;
        this.m_titulo = titulo;
        this.m_intro = intro;
        this.m_fechainicio = fechainicio;
        this.m_horainicio = horainicio;
        this.m_asist = asist;
    }

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - getters
    public int getId() { return this.m_id; }
    public String getTitulo() { return this.m_titulo; }
    public String getIntro() { return this.m_intro; }
    public String getFechaInicio() { return this.m_fechainicio; }
    public String getHoraInicio() { return this.m_horainicio; }
    public int getIdAsistente() { return this.m_asist; }

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    public String toString() { return this.m_id+": "+this.m_titulo+"\n"; }

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    public static void listaPrint(ArrayList<EventosLista> olist) {
        String content = "";
        for(EventosLista o : olist){
            content += o.getId()+": "+o.getTitulo()+"\n";
        }
        Log.wtf("MAGOMO", "olist: \n" + content);
    }

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - getEventosLista ->
    public static ArrayList<EventosLista> getEventosLista(Context thecontext, int idEvento) {
        ArrayList<EventosLista> objeto = new ArrayList<>();

        if (new File(DIR_XTRAS + "Eventos.json").exists()) {

            String jsonString = Public_Methods.loadFileContent(DIR_XTRAS + "Eventos.json", thecontext);

            if (!jsonString.equals("")) {

                try {

                    JSONObject JSobj = new JSONObject(jsonString);

                    if(JSobj!=null) {

                        for (int i = 0; i < JSobj.length(); i++) {

                            objeto.add(new EventosLista( JSobj.getInt("id"), JSobj.getString("titulo"),
                                    JSobj.getString("intro"), JSobj.getString("fechainicio"),
                                    JSobj.getString("horainicio"), JSobj.getInt("asist")
                            ));
                        }
                    }
                }
                catch (JSONException ex) { Log.wtf("MAGOMO", "getDelegaciones -> Error JSONObject: " + ex.toString()); }
            }
        }

        return objeto;
    }
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - getProvincias //

}
