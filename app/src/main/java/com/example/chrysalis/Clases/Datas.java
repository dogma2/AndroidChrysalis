package com.example.chrysalis.Clases;

import android.content.Context;
import android.util.Log;

import com.example.chrysalis.Public_Methods;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.File;
import static com.example.chrysalis.FixedData.DIR_CONFIG;

public class Datas {

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - attributes
    private String m_nombre;
    private String m_cif;
    private String m_telefono;
    private String m_direccion;
    private String m_ciudad;
    private String m_codigopostal;
    private int m_idprovincia;
    private int m_idcomunidad;
    private String m_email;
    private String m_web;
    private String m_rgpd;
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - contructor
    public Datas() { }
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - getters
    public String getNombre() { return m_nombre; }
    public String getCIF() { return m_cif; }
    public String getTelefono() { return m_telefono; }
    public String getDireccion() { return m_direccion; }
    public String getCiudad() { return m_ciudad; }
    public String getCodigoPostal() { return m_codigopostal; }
    public int getIdProvincia() { return m_idprovincia; }
    public int getIdComunidad() { return m_idcomunidad; }
    public String getEmail() { return m_email; }
    public String getWeb() { return m_web; }
    public String getRGPD() { return m_rgpd; }
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - getDatas ->
    public static Datas getDatas(Context thecontext) {
        Datas objeto = new Datas();

        if (new File(DIR_CONFIG + "appData.json").exists()) {

            String jsonString = Public_Methods.loadFileContent(DIR_CONFIG + "datas.json", thecontext);

            if (!jsonString.equals("")) {

                try {
                    JSONObject JSobj = new JSONObject(jsonString);
                    if(JSobj!=null) {
                        objeto.m_nombre = JSobj.getString("m_nombre");
                        objeto.m_cif = JSobj.getString("m_cif");
                        objeto.m_telefono = JSobj.getString("m_telefono");
                        objeto.m_direccion = JSobj.getString("m_direccion");
                        objeto.m_ciudad = JSobj.getString("m_ciudad");
                        objeto.m_codigopostal = JSobj.getString("m_codigopostal");
                        objeto.m_idprovincia = JSobj.getInt("m_idprovincia");
                        objeto.m_idcomunidad = JSobj.getInt("m_idcomunidad");
                        objeto.m_email = JSobj.getString("m_email");
                        objeto.m_web = JSobj.getString("m_web");
                        objeto.m_rgpd = JSobj.getString("m_rgpd");
                    } else {
                        objeto.m_nombre = "";
                        objeto.m_cif = "";
                        objeto.m_telefono = "";
                        objeto.m_direccion = "";
                        objeto.m_ciudad = "";
                        objeto.m_codigopostal = "";
                        objeto.m_idprovincia = 0;
                        objeto.m_idcomunidad = 0;
                        objeto.m_email = "";
                        objeto.m_web = "";
                        objeto.m_rgpd = "";
                    }
                }
                catch (JSONException ex) { Log.wtf("MAGOMO", "getDatas -> Error JSONObject: " + ex.toString()); }
            }
        }

        return objeto;
    }
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - getDatas //
}

