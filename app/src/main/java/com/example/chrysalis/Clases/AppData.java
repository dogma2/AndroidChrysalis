package com.example.chrysalis.Clases;

import android.content.Context;
import android.util.Log;

import com.example.chrysalis.Public_Methods;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.File;
import static com.example.chrysalis.FixedData.DIR_CONFIG;

public class AppData {

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - attributes
    private String m_cidapp;
    private String m_code;
    private int m_state;
    private int m_date;
    private int m_updated;
    private int m_result;
    private int m_next;
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - contructor
    public AppData() { }
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - getters
    public String getCidApp() { return m_cidapp; }
    public String getCode() { return m_code; }
    public int getState() { return m_state; }
    public int getDate() { return m_date; }
    public int getUpdate() { return m_updated; }
    public int getResult() { return m_result; }
    public int getNext() { return m_next; }
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - setters
    public void setState(int state) { this.m_state = state; }
    public void setDate(int date) { this.m_date = date; }
    public void setUpdate(int updated) { this.m_updated = updated; }
    public void setResult(int result) { this.m_result = result; }
    public void setNext(int next) { this.m_next = next; }
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - getAppData ->
    public static AppData getAppData(Context thecontext) {
        AppData objeto = new AppData();

        if (new File(DIR_CONFIG + "appData.json.json").exists()) {

            String jsonString = Public_Methods.loadFileContent(DIR_CONFIG + "appData.json", thecontext);

            if (!jsonString.equals("")) {

                try {
                    JSONObject JSobj = new JSONObject(jsonString);
                    if(JSobj!=null) {
                        objeto.m_state = JSobj.getInt("m_state");
                        objeto.m_date = JSobj.getInt("m_date");
                        objeto.m_updated = JSobj.getInt("m_updated");
                        objeto.m_result = JSobj.getInt("m_result");
                        objeto.m_next = JSobj.getInt("m_next");
                    } else {
                        objeto.m_state = 0;
                        objeto.m_date = 0;
                        objeto.m_updated = 0;
                        objeto.m_result = 0;
                        objeto.m_next = 0;
                    }
                }
                catch (JSONException ex) { Log.wtf("MAGOMO", "getDelegaciones -> Error JSONObject: " + ex.toString()); }
            }
        }

        return objeto;
    }
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - getAppData //
}

