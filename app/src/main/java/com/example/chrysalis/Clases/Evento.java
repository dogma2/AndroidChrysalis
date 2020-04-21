package com.example.chrysalis.Clases;

import android.content.Context;
import android.util.Log;

import com.example.chrysalis.Public_Methods;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;

import static com.example.chrysalis.FixedData.DIR_XTRAS;

public class Evento {

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - attributes
    private int m_id;
    private String m_cidevento;
    private String m_titulo;
    private String m_intro;
    private String m_descripcion;
    private String m_fechainicio;
    private String m_horainicio;
    private String m_fechafin;
    private String m_horafin;
    private String m_notasevento;
    private String m_notastransporte;
    private int m_idccaa;
    private int m_idprovincia;
    private String m_ciudad;
    private String m_coordgps;
    private int m_ctrlglobal;
    private int m_iddelegacion;
    private int m_asist;

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - contructor
    public Evento() { }
    public Evento( int id, String cidevento, String titulo, String intro, String descripcion,
                  String fechainicio, String horainicio, String fechafin, String horafin, String notasevento,
                  String notastransporte, int idccaa, int idprovincia, String ciudad, String coordgps,
                  int ctrlglobal, int iddelegacion, int asist ) {
        this.m_id = id;
        this.m_cidevento = cidevento;
        this.m_titulo = titulo;
        this.m_intro = intro;
        this.m_descripcion = descripcion;
        this.m_fechainicio = fechainicio;
        this.m_horainicio = horainicio;
        this.m_fechafin = fechafin;
        this.m_horafin = horafin;
        this.m_notasevento = notasevento;
        this.m_notastransporte = notastransporte;
        this.m_idccaa = idccaa;
        this.m_idprovincia = idprovincia;
        this.m_ciudad = ciudad;
        this.m_coordgps = coordgps;
        this.m_ctrlglobal = ctrlglobal;
        this.m_iddelegacion = iddelegacion;
        this.m_asist = asist;
    }

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - getters
    public int getId() { return this.m_id; }
    public String getCidEvento() { return this.m_cidevento; }
    public String getTitulo() { return this.m_titulo; }
    public String getIntro() { return this.m_intro; }
    public String getDescripcion() { return this.m_descripcion; }
    public String getFechaInicio() { return this.m_fechainicio; }
    public String getHoraInicio() { return this.m_horainicio; }
    public String getFechaFin() { return this.m_fechafin; }
    public String getHoraFin() { return this.m_horafin; }
    public String getNotaEvento() { return this.m_notasevento; }
    public String getNotaTransporte() { return this.m_notastransporte; }
    public int getIdCCAA() { return this.m_idccaa; }
    public int getIdProvincia() { return this.m_idprovincia; }
    public String getCiudad() { return this.m_ciudad; }
    public String getCoordGPS() { return this.m_coordgps; }
    public int getCtrlGlobal() { return this.m_ctrlglobal; }
    public int getIdDelegacion() { return this.m_iddelegacion; }
    public int getIdAsistente() { return this.m_asist; }

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    public String toString() { return this.m_id+": "+this.m_titulo+"\n"; }

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - getEvento ->
    public static Evento getEvento(Context thecontext, int idEvento) {
        Evento objeto = new Evento();

        if (new File(DIR_XTRAS + "Evento"+idEvento+".json").exists()) {

            String jsonString = Public_Methods.loadFileContent(DIR_XTRAS + "Evento"+idEvento+".json", thecontext);

            if (!jsonString.equals("")) {

                try {
                    JSONObject JSobj = new JSONObject(jsonString);
                    if(JSobj!=null) {
                        objeto.m_id = JSobj.getInt("id");
                        objeto.m_cidevento = JSobj.getString("cidevento");
                        objeto.m_titulo = JSobj.getString("titulo");
                        objeto.m_intro = JSobj.getString("intro");
                        objeto.m_descripcion = JSobj.getString("descripcion");
                        objeto.m_fechainicio = JSobj.getString("fechainicio");
                        objeto.m_horainicio = JSobj.getString("horainicio");
                        objeto.m_fechafin = JSobj.getString("fechafin");
                        objeto.m_horafin = JSobj.getString("horafin");
                        objeto.m_notasevento = JSobj.getString("notasevento");
                        objeto.m_notastransporte = JSobj.getString("notastransporte");
                        objeto.m_idccaa = JSobj.getInt("idccaa");
                        objeto.m_idprovincia = JSobj.getInt("idprovincia");
                        objeto.m_ciudad = JSobj.getString("ciudad");
                        objeto.m_coordgps = JSobj.getString("coordgps");
                        objeto.m_ctrlglobal = JSobj.getInt("ctrlglobal");
                        objeto.m_iddelegacion = JSobj.getInt("iddelegacion");
                        objeto.m_asist = JSobj.getInt("asist");
                    } else {
                        objeto.m_id = 0;
                        objeto.m_cidevento = "";
                        objeto.m_titulo = "";
                        objeto.m_intro = "";
                        objeto.m_descripcion = "";
                        objeto.m_fechainicio = "";
                        objeto.m_horainicio = "";
                        objeto.m_fechafin = "";
                        objeto.m_horafin = "";
                        objeto.m_notasevento = "";
                        objeto.m_notastransporte = "";
                        objeto.m_idccaa = 0;
                        objeto.m_idprovincia = 0;
                        objeto.m_ciudad = "";
                        objeto.m_coordgps = "";
                        objeto.m_ctrlglobal = 0;
                        objeto.m_iddelegacion = 0;
                        objeto.m_asist = 0;
                    }
                }
                catch (JSONException ex) { Log.wtf("MAGOMO", "getDatas -> Error JSONObject: " + ex.toString()); }
            }
        }

        return objeto;
    }
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - getProvincias //

}
