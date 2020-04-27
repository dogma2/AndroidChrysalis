package com.example.chrysalis;

import android.content.Context;
import android.net.Uri;
import android.os.NetworkOnMainThreadException;
import android.os.StrictMode;
import android.util.Log;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static com.example.chrysalis.FixedData.*;
import static com.example.chrysalis.Activities.Load_Activity.*;

public class Public_Methods {

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - READ FILE TO STRING ->
    public static String loadFileContent(String filename, Context thecontext) {

        String retu = "";

        Log.wtf("MAGOMO", "loadFileContent -> filename: " + filename);

        File thepath = thecontext.getExternalFilesDir(filename);

        try {
            FileReader fr = new FileReader( filename );
            BufferedReader br = new BufferedReader(fr);
            String linia = br.readLine();
            while(linia != null)
            {
                retu += linia;
                linia = br.readLine();
            }
            fr.close();
        } catch(Exception e) { Log.wtf("MAGOMO", "loadFileContent -> Error: " + e.toString()); }
        return retu;
    }
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - READ FILE TO STRING //

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - ejecucion de instalacion de datos por APPCTIVE ->

    /*
    public static boolean callFilesWork(boolean IsAActivation)
    {

        boolean isOK = false;

        Log.wtf("MAGOMO", "callFilesWork -> calling unpackZip");

        // - - - - - Ejecuta descompresion de archivo ZIP
        if (  )
        {
            Log.wtf("MaGoMo >> ", "unpackZip activacion -> DATA SAVE OK" );
        }
        else
        {
            Log.wtf("MaGoMo >> ", "unpackZip activacion -> DATA SAVE ERROR" );
        }

        // - - - - / Ejecuta descompresion de archivo ZIP

        Log.wtf("MAGOMO", "callFilesWork -> calling ActivateFileCopy");

        // - - - - - Si descompresion OK, ejecuta copia de archivos
        if (isOK) {
            if(!option) {
                if ( ActivateFileCopy() ) {
//                    isOK = UpdateFileCopy();
                }
            } else  {
//                isOK = UpdateFileCopy();
            }
        }
        // - - - - - Si descompresion OK, ejecuta copia de archivos
        return isOK;
    }*/
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - ejecucion de instalacion de datos por APPCTIVE //

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - copia de archivos de activaciom ->
    private static boolean DeactivateFileDelete() {
        boolean retu = true;
        File tmpFile;
        // - - - - - - - - - - config.json
        tmpFile = new File(CONFIG_JSON);
        if (tmpFile.exists()) {
            if (!tmpFile.delete()) {
                retu = false; Log.wtf("MaGoMo >> ","DeactivateFileDelete -> Eliminando " + CONFIG_JSON);
            }
        }
        // - - - - - - - - - - appdata.json
        tmpFile = new File(APPDATA_JSON);
        if (tmpFile.exists()) {
            if (!tmpFile.delete()) {
                retu = false; Log.wtf("MaGoMo >> ","DeactivateFileDelete -> Eliminando " + CONFIG_JSON);
            }
        }
        // - - - - - - - - - - datas.json
        tmpFile = new File(DATAS_JSON);
        if (tmpFile.exists()) {
            if (!tmpFile.delete()) {
                retu = false; Log.wtf("MaGoMo >> ","DeactivateFileDelete -> Eliminando " + CONFIG_JSON);
            }
        }
        // - - - - - - - - - - idiomas.json
        tmpFile = new File(IDIOMAS_JSON);
        if (tmpFile.exists()) {
            if (!tmpFile.delete()) {
                retu = false; Log.wtf("MaGoMo >> ","DeactivateFileDelete -> Eliminando " + CONFIG_JSON);
            }
        }
        // - - - - - - - - - - delegaciones.json
        tmpFile = new File(DELEGACIONES_JSON);
        if (tmpFile.exists()) {
            if (!tmpFile.delete()) {
                retu = false; Log.wtf("MaGoMo >> ","DeactivateFileDelete -> Eliminando " + CONFIG_JSON);
            }
        }
        // - - - - - - - - - - comunidades.json
        tmpFile = new File(COMUNIDADES_JSON);
        if (tmpFile.exists()) {
            if (!tmpFile.delete()) {
                retu = false; Log.wtf("MaGoMo >> ","DeactivateFileDelete -> Eliminando " + CONFIG_JSON);
            }
        }
        // - - - - - - - - - - provincias.json
        tmpFile = new File(PROVINCIAS_JSON);
        if (tmpFile.exists()) {
            if (!tmpFile.delete()) {
                retu = false; Log.wtf("MaGoMo >> ","DeactivateFileDelete -> Eliminando " + CONFIG_JSON);
            }
        }
        // - - - - - - - - - -
        return retu;
    }
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - copia de archivos de activaciom //

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - copia de archivos de activaciom ->
    public static boolean ActivateFileCopy() {
        boolean retu = true;
        File tmpFile, tmpFileZip;
        // - - - - - - - - - - config.json
        tmpFile = new File(CONFIG_JSON); tmpFileZip = new File(CONFIG_ZIP);
        if (!tmpFile.exists()) {
            if (!copyFile(tmpFileZip, tmpFile)) {
                retu = false;
                Log.wtf("MaGoMo >> ","ActivateFileCopy -> Error copia " + CONFIG_JSON);
            }
        }
        // - - - - - - - - - - appdata.json
        tmpFile = new File(APPDATA_JSON); tmpFileZip = new File(APPDATA_ZIP);
        if (!copyFile(tmpFileZip, tmpFile)) {
            retu = false;
            Log.wtf("MaGoMo >> ","ActivateFileCopy -> Error copia " + APPDATA_JSON);
        } else {
            Log.wtf("MaGoMo >> ","ActivateFileCopy -> Copiado" + tmpFileZip.toString() +" a "+ tmpFile.toString());
        }
        // - - - - - - - - - - datas.json
        tmpFile = new File(DATAS_JSON); tmpFileZip = new File(DATAS_ZIP);
        if (!copyFile(tmpFileZip, tmpFile)) { retu = false; Log.wtf("MaGoMo >> ","ActivateFileCopy -> Error copia " + DATAS_JSON); }
        // - - - - - - - - - - idiomas.json
        tmpFile = new File(IDIOMAS_JSON); tmpFileZip = new File(IDIOMAS_ZIP);
        if (!copyFile(tmpFileZip, tmpFile)) { retu = false; Log.wtf("MaGoMo >> ","ActivateFileCopy -> Error copia " + IDIOMAS_JSON); }
        // - - - - - - - - - - delegaciones.json
        tmpFile = new File(DELEGACIONES_JSON); tmpFileZip = new File(DELEGACIONES_ZIP);
        if (!copyFile(tmpFileZip, tmpFile)) { retu = false; Log.wtf("MaGoMo >> ","ActivateFileCopy -> Error copia " + DELEGACIONES_JSON); }
        // - - - - - - - - - - comunidades.json
        tmpFile = new File(COMUNIDADES_JSON); tmpFileZip = new File(COMUNIDADES_ZIP);
        if (!copyFile(tmpFileZip, tmpFile)) { retu = false; Log.wtf("MaGoMo >> ","ActivateFileCopy -> Error copia " + COMUNIDADES_JSON); }
        // - - - - - - - - - - provincias.json
        tmpFile = new File(PROVINCIAS_JSON); tmpFileZip = new File(PROVINCIAS_ZIP);
        if (!copyFile(tmpFileZip, tmpFile)) { retu = false; Log.wtf("MaGoMo >> ","ActivateFileCopy -> Error copia " + PROVINCIAS_JSON); }



        return retu;
    }
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - copia de archivos de activaciom //

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - copia de archivos de actualizacion ->
    private static boolean UpdateFileCopy() {
        boolean retu = true;
        File tmpFile, tmpFileZip;
        // - - - - - - - - - - eventos.json
        tmpFile = new File(EVENTOS_JSON); tmpFileZip = new File(EVENTOS_ZIP);
        if (!copyFile(tmpFileZip, tmpFile)) { retu = false; Log.wtf("MaGoMo >> ","ActivateFileCopy -> Error copia " + EVENTOS_JSON); }
        // - - - - - - - - - - datoisinteres.json
        tmpFile = new File(DATOSINTERES_JSON); tmpFileZip = new File(DATOSINTERES_ZIP);
        if (!copyFile(tmpFileZip, tmpFile)) { retu = false; Log.wtf("MaGoMo >> ","ActivateFileCopy -> Error copia " + DATOSINTERES_JSON); }
        return retu;
    }
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - copia de archivos de actualizacion //

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - copiar archivos ->
    public static boolean copyFile(File from, File to) {
        boolean retu = true;
        Log.wtf("MaGoMo >> ","copyFile -> start copy " + from.toString() +" a "+ to.toString());
        try {
            InputStream in = new FileInputStream(from);
            OutputStream out = new FileOutputStream(to);
            // Copy the bits from instream to outstream
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) { out.write(buf, 0, len); }
            in.close();
            out.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
            retu = false;
            Log.wtf("MaGoMo >> ","copyFile -> FileNotFoundException: " + e.getMessage() );
        }
        catch (IOException e) {
            e.printStackTrace();
            retu = false;
            Log.wtf("MaGoMo >> ","copyFile -> IOException: " + e.getMessage() );
        }

        return retu;
    }
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - copiar archivos //

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - metodo de DESCOMPRESION de archivo ZIP ->
    private  static void CrearDirectorios()
    {
        if ( new File(DIR_MASTER).mkdir() ) { Log.wtf("MaGoMo >> ", "unpackZip -> crear directorio " + DIR_MASTER + " OK !" ); }
        else { Log.wtf("MaGoMo >> ", "unpackZip -> crear directorio " + DIR_MASTER + " ERROR !" ); }

        if ( new File( DIR_CONFIG ).mkdir() ) { Log.wtf("MaGoMo >> ", "unpackZip -> crear directorio " + DIR_CONFIG + " OK !" ); }
        else { Log.wtf("MaGoMo >> ", "unpackZip -> crear directorio " + DIR_CONFIG + " ERROR !" ); }

        if ( new File (DIR_XTRAS).mkdirs() ) { Log.wtf("MaGoMo >> ", "unpackZip -> crear directorio " + DIR_XTRAS + " OK !" ); }
        else { Log.wtf("MaGoMo >> ", "unpackZip -> crear directorio " + DIR_XTRAS + " ERROR !" ); }

        if ( new File (DIR_DATAS).mkdirs() ) { Log.wtf("MaGoMo >> ", "unpackZip -> crear directorio " + DIR_DATAS + " OK !" ); }
        else { Log.wtf("MaGoMo >> ", "unpackZip -> crear directorio " + DIR_DATAS + " ERROR !" ); }

        if ( new File (DIR_IMAGES).mkdirs() ) { Log.wtf("MaGoMo >> ", "unpackZip -> crear directorio " + DIR_IMAGES + " OK !" ); }
        else { Log.wtf("MaGoMo >> ", "unpackZip -> crear directorio " + DIR_IMAGES + " ERROR !" ); }

        if ( new File (DIR_WORK).mkdirs() ) { Log.wtf("MaGoMo >> ", "unpackZip -> crear directorio " + DIR_WORK + " OK !" ); }
        else { Log.wtf("MaGoMo >> ", "unpackZip -> crear directorio " + DIR_WORK + " ERROR !" ); }

        if ( new File (DIR_ZIPPED).mkdirs() ) { Log.wtf("MaGoMo >> ", "unpackZip -> crear directorio " + DIR_ZIPPED + " OK !" ); }
        else { Log.wtf("MaGoMo >> ", "unpackZip -> crear directorio " + DIR_ZIPPED + " ERROR !" ); }

        if ( new File (DIR_UNZIPPED).mkdirs() ) { Log.wtf("MaGoMo >> ", "unpackZip -> crear directorio " + DIR_UNZIPPED + " OK !" ); }
        else { Log.wtf("MaGoMo >> ", "unpackZip -> crear directorio " + DIR_UNZIPPED + " ERROR !" ); }
    }

    public static boolean unZipInstalation(String correo, String imei)
    {
        boolean isOK = true;

        Log.wtf("MaGoMo >> ", "unpackZip -> Start");

        // option true = instalacion

        Log.wtf("MaGoMo >> ", "unpackZip -> instalacion");
        String NombreZip = "appctivate.zip";

        if ( !(new File(DIR_MASTER).exists()))
        {
        //Log.wtf("MaGoMo >> ", "unpackZip -> DIR_MASTER NOT exists");
        CrearDirectorios();

        }
        //else { Log.wtf("MaGoMo >> ", "unpackZip -> DIR_MASTER NO exists"); }

        // - - - - - - - - - - CALL URL
        isOK = callingAPIToActivation(correo,imei);

        //Log.wtf("MaGoMo >> ", "unpackZip -> callingAPI (1): " + isOK);
        unpackZipGeneral(isOK, NombreZip);


        return isOK;
    }
    public static boolean unZipUpdate(String update)
    {
        boolean retu = true;
        Boolean isOK = true;
        //Log.wtf("MaGoMo >> ", "unpackZip -> Start");
        //Log.wtf("MaGoMo >> ", "unpackZip -> actualizacion");

        String NombreZip = "appdate.zip";

            // - - - - - elimina contenidos de directorios
        delDirContents( DIR_MASTER, 0 );

            // - - - - - - - - - - CALL URL
        isOK = callingAPIToUpdate(update);

        Log.wtf("MaGoMo >> ", "unpackZip -> callingAPI (2): " + isOK);

        unpackZipGeneral(isOK, NombreZip);
        return isOK;
    }

    private static void unpackZipGeneral(boolean isOk, String NombreZip)
    {

        if (isOk) {


            Uri fileUri = Uri.fromFile( new File(DIR_ZIPPED + NombreZip));

            File fmd;
            String filename;
            String mensaje;
            // control try - catch
            try {

                InputStream inputStream = mainContext.getContentResolver().openInputStream( fileUri );
                ZipInputStream bufferedInputStream = new ZipInputStream( new BufferedInputStream( inputStream ) );

                Log.wtf("MaGoMo >> ", "unpackZip -> inputStream start" );

                ZipEntry zipEntry;
                byte[] buffer = new byte[1024];
                int count;

                // ciclo por cada elemento del zip
                while ( ( zipEntry = bufferedInputStream.getNextEntry() ) != null )
                {
                    // lee entrada en archivo comprimido
                    filename = zipEntry.getName();

                    Log.wtf("MaGoMo >> ", "unpackZip -> entry name = " + DIR_UNZIPPED + NombreZip );

                    if ( zipEntry.isDirectory() )
                    {
                        fmd = new File(DIR_UNZIPPED + "/" + filename );
                        if ( fmd.mkdirs() ) { mensaje = "unpackZip -> SUCCESSFUL directory creating " + fmd.getPath(); }
                        else { mensaje = "unpackZip -> ERROR creating directory " + fmd.getPath(); }
                        Log.wtf("MaGoMo >> ", mensaje + fmd.getName() );
                    }
                    else
                    {
                        // - - - - - Creacion de directorios - split the array using '/' as a delimiter
                        String[] thedirs = filename.split("/"); String pathname = "";
                        for ( int i = 0; i < ( thedirs.length - 1); i++) { if (i>0) { pathname += "/"; } pathname += thedirs[i]; }
                        Log.wtf("MaGoMo >> ", "unpackZip -> CREATING DIRECTORY " + DIR_UNZIPPED + "/" + pathname );
                        new File(DIR_UNZIPPED + "/" + pathname ).mkdirs();

                        Log.wtf("MaGoMo >> ", "unpackZip -> CREATING FILE " + DIR_UNZIPPED + "/" + filename );
                        // - - - - - Graba archivo
                        FileOutputStream fout = new FileOutputStream( DIR_UNZIPPED + "/" + filename );
                        while ( ( count = bufferedInputStream.read(buffer)) > 0 ) { fout.write(buffer, 0, count); }
                        fout.close();
                        bufferedInputStream.closeEntry();
                    }

                }

                bufferedInputStream.close();

            }
            catch(IOException e) {
                e.printStackTrace(); isOk = false;
                Log.wtf("MaGoMo >> ", "unpackZip -> IOException: " + e.getMessage() );
            }

            Log.wtf("MaGoMo >> ", "unpackZip -> salida" );

        }
    }
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - metodo de DESCOMPRESION de archivo ZIP ->

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - metodo de ELIMINACION de DIRECTORIO ->
    private static void delDirContents( String delFolder, int thecicle )
    {
        Log.wtf("MaGoMo >> ", "deleteZipDir (" + thecicle + ") -> delFolder = " + delFolder );

        if (!delFolder.equals(DIR_CONFIG)) {

            File dir = new File(delFolder);
            String mensaje = "";
            // - - - - si existe y es directorio
            if (dir.isDirectory() && dir.exists()) {
                // - - - - - recoge vector de string con nombres de contenido de directorio
                String[] children = dir.list();
                // - - - - - ciclo de eliminacion de archivos
                for (int i = 0; i < children.length; i++) {
                    File elementDir = new File(dir, children[i]);
                    if (elementDir.isDirectory() && elementDir.exists()) {
                        delDirContents(elementDir.toString(), thecicle + 1);
                    } else {
                        mensaje = "delDirContents (" + thecicle + ") item " + elementDir.getName();
                        if (elementDir.exists()) {
                            mensaje += " Existe";
                            if (elementDir.canWrite()) { mensaje += " & Writable"; } else { mensaje += " & UNWritable"; }
                            if (elementDir.canRead()) { mensaje += " & Readable"; } else { mensaje += " & UNReadable"; }
                            if (elementDir.delete()) { mensaje += " & ELIMINADO !"; } else { mensaje += " & DEL_ERROR !"; }
                        } else { mensaje += " NO Existe !!!"; }
                        Log.wtf("MaGoMo >> ", mensaje);
                    }
                }
                // mensaje = "deleteZipDir (" + thecicle + ") -> " + dir.getName();
                // if (dir.delete()) { mensaje += " & ELIMINADO !"; } else { mensaje += " & DEL_ERROR !"; }
                Log.wtf("MaGoMo >> ", mensaje);
            }
            Log.wtf("MaGoMo >> ", "deleteZipDir -> endprocess (" + thecicle + ")");
        }
    }
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - metodo de ELIMINACION de DIRECTORIO ->

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - metodo de LLAMADA URL ->

    private static boolean callingAPIToActivation(String email, String Imei) {
        boolean retu = true;

        String ZipFile = "";
        URL urlapi = null;
        HttpURLConnection urlConnection;

        Log.wtf("MaGoMo >> ", "callingAPI -> start" );

        try {

             // activacion
            urlapi = new URL(URL_ACTIVATE + email + "/" + Imei);
            ZipFile = "appctivate.zip";


            // agregado MaGoMo 2020.04.19 ->
            if (android.os.Build.VERSION.SDK_INT > 9) {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);

            }
            // agregado MaGoMo 2020.04.19 //

            byte[] buffer = null;
            try {

                System.out.println("Entra 1");
                URLConnection conn = urlapi.openConnection();
                int contentLength = conn.getContentLength();

                System.out.println("Entra 2");
                DataInputStream stream = new DataInputStream(urlapi.openStream());

                System.out.println("Entra 3");
                buffer = new byte[contentLength];
                stream.readFully(buffer);
                stream.close();

            } catch(NetworkOnMainThreadException ex) {
                Log.wtf("MaGoMo >> ", "callingAPI -> Error URLConnection: " + ex.getMessage() );
            }

            System.out.println("Entra 4");
            DataOutputStream fos = new DataOutputStream(new FileOutputStream(DIR_ZIPPED + ZipFile));
            fos.write(buffer);
            fos.flush();
            fos.close();



        }
        catch(FileNotFoundException e)
        {
            retu = false;
            Log.wtf("MaGoMo >> ", "Try -> Error FileNotFoundException:" + e.toString()); /*swallow a 404*/ }
        catch (IOException e) {
            retu = false;
            Log.wtf("MaGoMo >> ", "Try -> Error IOException:" + e.toString() ); /*swallow a 404*/ }

        Log.wtf("MaGoMo >> ", "callingAPI -> exit" );

        return retu;

    }
    private static boolean callingAPIToUpdate(String Imei) {
        boolean retu = true;
        String ZipFile = "";
        URL urlapi = null;
        HttpURLConnection urlConnection;

        Log.wtf("MaGoMo >> ", "callingAPI -> start" );

        try {
                //actualizacion

                urlapi = new URL(URL_UPDATE + "2030"+ "/" + Imei +"/nada");

            ZipFile = "appdate.zip";
            //Log.wtf("MaGoMo >> ", "Try -> urlapi: " + urlapi + " / ZipFile: " +  ZipFile );

            // agregado MaGoMo 2020.04.19 ->
            if (android.os.Build.VERSION.SDK_INT > 9) {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
            }
            // agregado MaGoMo 2020.04.19 //

            byte[] buffer = null;
            try {
                URLConnection conn = urlapi.openConnection();
                int contentLength = conn.getContentLength();

                DataInputStream stream = new DataInputStream(urlapi.openStream());

                buffer = new byte[contentLength];
                stream.readFully(buffer);
                stream.close();

            } catch(NetworkOnMainThreadException ex) {
                Log.wtf("MaGoMo >> ", "callingAPI -> Error URLConnection: " + ex.getMessage() );
            }

            DataOutputStream fos = new DataOutputStream(new FileOutputStream(DIR_ZIPPED + ZipFile));
            fos.write(buffer);
            fos.flush();
            fos.close();


            /*
            // https://es.stackoverflow.com/questions/215350/urlconnection-error-en-android-networkonmainthreadexception
            String data = "";
            String result = null;
            try
            {
                urlConnection = (HttpURLConnection) urlapi.openConnection();
                urlConnection.setDoOutput(true);
                urlConnection.setRequestProperty("Content-Type", "application/json");
                urlConnection.setRequestProperty("Accept", "application/json");
                urlConnection.setRequestMethod("POST");
                urlConnection.connect();

                OutputStream outputStream = urlConnection.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                writer.write(data);
                writer.close();
                outputStream.close();

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
                String line = null; StringBuilder sb = new StringBuilder();
                while ((line = bufferedReader.readLine()) != null) {
                    sb.append(line);
                }
                bufferedReader.close();
                result = sb.toString();
            }
            catch (UnsupportedEncodingException e) { e.printStackTrace(); }
            catch (IOException e) { e.printStackTrace(); }
            return result;
            */


        }
        catch(FileNotFoundException e) { retu = false; Log.wtf("MaGoMo >> ", "Try -> Error FileNotFoundException:" + e.toString() ); /*swallow a 404*/ }
        catch (IOException e) { retu = false; Log.wtf("MaGoMo >> ", "Try -> Error IOException:" + e.toString() ); /*swallow a 404*/ }

        Log.wtf("MaGoMo >> ", "callingAPI -> exit" );

        return retu;

    }
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - metodo de LLAMADA URL //

    // - - - - - - - - - -  - - - - - - - - - - - - - - - - - - - -
    // - - - - - - - - - -  - - - - - - - - - - - - - - - - - - - -
}
