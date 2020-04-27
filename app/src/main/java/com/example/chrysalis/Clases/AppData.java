package com.example.chrysalis.Clases;

import android.content.Context;
import android.util.JsonWriter;
import android.util.Log;

import com.example.chrysalis.Public_Methods;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

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
    public AppData() {
    }

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - getters
    public String getCidApp() {
        return m_cidapp;
    }

    public String getCode() {
        return m_code;
    }

    public int getState() {
        return m_state;
    }

    public int getDate() {
        return m_date;
    }

    public int getUpdate() {
        return m_updated;
    }

    public int getResult() {
        return m_result;
    }

    public int getNext() {
        return m_next;
    }

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - setters
    public void setState(int state) {
        this.m_state = state;
    }

    public void setDate(int date) {
        this.m_date = date;
    }

    public void setUpdate(int updated) {
        this.m_updated = updated;
    }

    public void setResult(int result) {
        this.m_result = result;
    }

    public void setNext(int next) {
        this.m_next = next;
    }

    @Override
    public String toString() {
        return "AppData{" +
                "m_cidapp='" + m_cidapp + '\'' +
                ", m_code='" + m_code + '\'' +
                ", m_state=" + m_state +
                ", m_date=" + m_date +
                ", m_updated=" + m_updated +
                ", m_result=" + m_result +
                ", m_next=" + m_next +
                '}';
    }

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - getAppData ->
    public static AppData getAppData(Context thecontext) {
        AppData objeto = new AppData();
        System.out.println("Entra en la funcion....");
        String Path = DIR_CONFIG + "/" + "AppData.json";
        if (new File(Path).exists()) {
            System.out.println("El directorio existe");
            String jsonString = Public_Methods.loadFileContent(Path, thecontext);

            if (!jsonString.equals("")) {
                System.out.println("El JSon no esta vacio");
                try {
                    JSONObject JSobj = new JSONObject(jsonString);
                    System.out.println("El objetoJSon se crea correctamente");
                    if (JSobj != null) {
                        System.out.println("El objecto JSON no es null");
                        objeto.m_state = JSobj.getInt("m_state");
                        objeto.m_date = JSobj.getInt("m_date");
                        objeto.m_updated = JSobj.getInt("m_updated");
                        objeto.m_result = JSobj.getInt("m_result");
                        objeto.m_next = JSobj.getInt("m_next");
                        objeto.m_code = JSobj.getString("m_code");
                        objeto.m_cidapp = JSobj.getString("m_cidapp");
                    } else {
                        System.out.println("El Json no es null");
                        objeto.m_state = 0;
                        objeto.m_date = 0;
                        objeto.m_updated = 0;
                        objeto.m_result = 0;
                        objeto.m_next = 0;
                    }
                } catch (JSONException ex) {
                    Log.wtf("MAGOMO", "getDelegaciones -> Error JSONObject: " + ex.toString());
                }
            }
        }

        return objeto;
    }

    public static boolean SetData(AppData datas) {
        boolean ret = true;

        Log.wtf("Oriol >> ", "Entramos en el proceso" );

        String Path = DIR_CONFIG + "/" + "AppData.json";

        Log.wtf("Oriol >> ", "Establecemos al ruta del archivo que es : "+Path );
        if(new File(Path).exists())
        {
            Log.wtf("Oriol >> ", "Si el fichero existe entraremos aqui" );
            Gson gson = new Gson();
            try {
                Log.wtf("Oriol >> ", "Entramos en el try" );
                FileWriter fileW = new FileWriter(Path);
                Log.wtf("Oriol >> ", "instanciamos FileReader i BufferReader" );
                String json = gson.toJson(datas);
                Log.wtf("Oriol >> ", "Prodedemos a serializar el JSON : "+ json  );
                Log.wtf("Oriol >> ", "Procedemos a guardar el JSON en el fichero" );
                fileW.write(json);
                fileW.close();
                Log.wtf("Oriol >> ", "Si todo ha ido bien se mostrara este mensaje" );

            } catch (FileNotFoundException fil)
            {
                System.out.println("FileNotFoundException");
                Log.wtf("Oriol >> ", "si el fichero no existe se mostrara este mensajee. " + fil.getMessage() );

            } catch (IOException e) {
                Log.wtf("Oriol >> ", "Si tenemos un IOException se mostrara este mensaje "+e.getMessage() );

                ret = false;
                System.out.println("ERROR");
                System.out.println(e.getMessage());
            }
        }
        else
        {
            System.out.println("FILE DONT EXIST");
            System.out.println(Path);
        }



        return ret;
    }


        /*
        try {
            FileWriter w = new FileWriter(Path);

            w.write(json);
        } catch (IOException e) {
            ret = false;
            System.out.println(e.getMessage());
        }
        /*
        System.out.println(json);
        return ret;
    }
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - getAppData //*/
}

