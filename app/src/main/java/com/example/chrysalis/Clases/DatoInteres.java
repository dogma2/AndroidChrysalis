package com.example.chrysalis.Clases;


import android.content.Context;
import android.util.Log;

import com.example.chrysalis.GetInfo;
import com.example.chrysalis.Public_Methods;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.File;
import java.util.ArrayList;
import static com.example.chrysalis.FixedData.DIR_XTRAS;


public class DatoInteres {

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - attributes
    private int m_id;
    private String m_nombre;
    private String m_descripcion;
    private String m_direccion;
    private String m_ciudad;
    private String m_cp;
    private int m_idprovincia;
    private int m_idccaa;
    private String m_telefono;
    private String m_email;
    private String m_contacto;
    private int m_iddelegacion;

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - contructor
    public DatoInteres() { }
    public DatoInteres( int id, String nombre, String descripcion, String direccion, String ciudad,
            String cp, int idprovincia, int idccaa, String telefono, String email, String contacto, int m_iddelegacion ) {
        this.m_id = id;
        this.m_nombre = nombre;
        this.m_descripcion = descripcion;
        this.m_direccion = direccion;
        this.m_ciudad = ciudad;
        this.m_cp = cp;
        this.m_idprovincia = idprovincia;
        this.m_idccaa = idccaa;
        this.m_telefono = telefono;
        this.m_email = email;
        this.m_contacto = contacto;
        this.m_iddelegacion = m_iddelegacion;
    }
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - getters
    public int getId() { return this.m_id; }
    public String getNombre() { return this.m_nombre; }
    public String getDescripcion() { return this.m_descripcion; }
    public String getDireccion() { return this.m_direccion; }
    public String getCiudad() { return this.m_ciudad; }
    public String getCP() { return this.m_cp; }
    public int getIdProvincia() { return this.m_idprovincia; }
    public int getIdCCAA() { return this.m_idccaa; }
    public String getTelefono() { return this.m_telefono; }
    public String getEmail() { return this.m_email; }
    public String getContacto() { return this.m_contacto; }

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    public String toString() { return this.m_id+": "+this.m_nombre+"\n"; }

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    public static void listaPrint(ArrayList<Provincia> olist) {
        String content = "";
        for(Provincia o : olist){
            content += o.getId()+": "+o.getNombre()+"\n";
        }
        Log.wtf("MAGOMO", "olist: \n" + content);
    }

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - getDatosInteres ->
    public static ArrayList<DatoInteres> getDatosInteres(Context thecontext) {
        ArrayList<DatoInteres> objeto = new ArrayList<>();

        if (new File(DIR_XTRAS + "/DatosInteres.json").exists()) {

            String jsonString = Public_Methods.loadFileContent(DIR_XTRAS + "/DatosInteres.json", thecontext);

            if (!jsonString.equals("")) {

                try {

                    jsonString.replaceAll("[","");

                    JSONObject JSobj = new JSONObject(jsonString);
                    //Fumada padre is coming


                    if(JSobj!=null) {

                        for (int i = 0; i < JSobj.length(); i++) {

                            objeto.add(new DatoInteres( JSobj.getInt("id"), JSobj.getString("nombre"),
                                    JSobj.getString("descripcion"), JSobj.getString("direccion"),
                                    JSobj.getString("ciudad"), JSobj.getString("cp"),
                                    JSobj.getInt("idprovincia"), JSobj.getInt("idccaa"),
                                    JSobj.getString("telefono"), JSobj.getString("email"),
                                    JSobj.getString("contacto") ,  JSobj.getInt("iddelegacion") ));
                        }
                    }


                }
                catch (JSONException ex) { Log.wtf("MAGOMO", "getDatosInteres -> Error JSONObject: " + ex.toString()); }
            }
        }

        return objeto;
    }
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - getProvincias //

}
