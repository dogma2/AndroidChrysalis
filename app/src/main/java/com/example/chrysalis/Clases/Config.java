package com.example.chrysalis.Clases;

import android.content.Context;
import android.util.Log;

import com.example.chrysalis.Public_Methods;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.File;
import static com.example.chrysalis.FixedData.DIR_CONFIG;

public class Config {

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - attributes
    private int m_gps;
    private int m_sound;
    private int m_lang;
    private int m_update;
    private int m_longalertact;
    private int m_longalert;
    private int m_shortalertact;
    private int m_shortalert;
    private int m_alertfromhh;
    private int m_alertfrommm;
    private int m_alerttohh;
    private int m_alerttomm;
    private int[] m_delegaciones;
    private int[] m_idccaas;
    private int[] m_idprovincias;
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - contructor
    public Config() { }
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - getters
    public int getGPS() { return this.m_gps; }
    public int getSound() { return this.m_sound; }
    public int getLang() { return this.m_lang; }
    public int getUpdate() { return this.m_update; }
    public int getLongAlertAct() { return this.m_longalertact; }
    public int getLongAlert() { return this.m_longalert; }
    public int getShortAlertAct() { return this.m_shortalertact; }
    public int getShortAlert() { return this.m_shortalert; }
    public int getAlertFromHH() { return this.m_alertfromhh; }
    public int getAlertFromMM() { return this.m_alertfrommm; }
    public int getAlertToHH() { return this.m_alerttohh; }
    public int getAlertToMM() { return this.m_alerttomm; }
    public int[] getDelegaciones() { return this.m_delegaciones; }
    public int[] getidCCAAs() { return this.m_idccaas; }
    public int[] getidProvincias() { return this.m_idprovincias; }
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - setters
    public void setGPS(int gps) { this.m_gps = gps; }
    public void setSound(int sound) { this.m_sound = sound; }
    public void setLang(int lang) { this.m_lang = lang; }
    public void setUpdate(int update) { this.m_update = update; }
    public void setLongAlertAct(int longalertact) { this.m_longalertact = longalertact; }
    public void setLongAlert(int longalert) { this.m_longalert = longalert; }
    public void setShortAlertAct(int shortalertact) { this.m_shortalertact = shortalertact; }
    public void setShortAlert(int shortalert) { this.m_shortalert = shortalert; }
    public void setAlertFromHH(int alertfromhh) { this.m_alertfromhh = alertfromhh; }
    public void setAlertFromMM(int alertfrommm) { this.m_alertfrommm = alertfrommm; }
    public void setAlertToHH(int alerttohh) { this.m_alerttohh = alerttohh; }
    public void setAlertToMM(int alerttomm) { this.m_alerttomm = alerttomm; }
    public void setDelegaciones(int[] delegaciones) { this.m_delegaciones = delegaciones; }
    public void setidCCAAs(int[] idccaas) { this.m_idccaas = idccaas; }
    public void setidProvincias(int[] idprovincias) { this.m_idprovincias = idprovincias; }
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - getConfig ->
    public static Config getConfig(Context thecontext) {

        Config objeto = new Config();

        if (new File(DIR_CONFIG + "appData.json.json").exists()) {

            String jsonString = Public_Methods.loadFileContent(DIR_CONFIG + "config.json", thecontext);

            if (!jsonString.equals("")) {

                try {
                    JSONObject JSobj = new JSONObject(jsonString);
                    if(JSobj!=null) {
                        objeto.m_gps = JSobj.getInt("m_gps");
                        objeto.m_sound = JSobj.getInt("m_sound");
                        objeto.m_lang = JSobj.getInt("m_lang");
                        objeto.m_update = JSobj.getInt("m_update");
                        objeto.m_longalertact = JSobj.getInt("m_longalertact");
                        objeto.m_longalert = JSobj.getInt("m_longalert");
                        objeto.m_shortalertact = JSobj.getInt("m_shortalertact");
                        objeto.m_shortalert = JSobj.getInt("m_shortalert");
                        objeto.m_alertfromhh = JSobj.getInt("m_alertfromhh");
                        objeto.m_alertfrommm = JSobj.getInt("m_alertfrommm");
                        objeto.m_alerttohh = JSobj.getInt("m_alerttohh");
                        objeto.m_alerttomm = JSobj.getInt("m_alerttomm");
                        for (int i = 0; i < JSobj.getJSONArray("m_delegaciones").length(); i++) {
                            objeto.m_delegaciones[i] = JSobj.getJSONArray("m_delegaciones").optInt(i);
                        }
                        for (int i = 0; i < JSobj.getJSONArray("m_idccaas").length(); i++) {
                            objeto.m_idccaas[i] = JSobj.getJSONArray("m_idccaas").optInt(i);
                        }
                        for (int i = 0; i < JSobj.getJSONArray("m_idprovincias").length(); i++) {
                            objeto.m_idprovincias[i] = JSobj.getJSONArray("m_idprovincias").optInt(i);
                        }
                    } else {
                        objeto.m_gps = 0;
                        objeto.m_sound = 0;
                        objeto.m_lang = 0;
                        objeto.m_update = 0;
                        objeto.m_longalertact = 0;
                        objeto.m_longalert = 0;
                        objeto.m_shortalertact = 0;
                        objeto.m_shortalert = 0;
                        objeto.m_alertfromhh = 0;
                        objeto.m_alertfrommm = 0;
                        objeto.m_alerttohh = 0;
                        objeto.m_alerttomm = 0;
                        objeto.m_delegaciones = null;
                        objeto.m_idccaas = null;
                        objeto.m_idprovincias = null;
                    }
                }
                catch (JSONException ex) { Log.wtf("MAGOMO", "getDelegaciones -> Error JSONObject: " + ex.toString()); }
            }
        }

        return objeto;
    }
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - getConfig //
}
