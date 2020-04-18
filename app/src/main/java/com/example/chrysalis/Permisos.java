package com.example.chrysalis;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.telephony.TelephonyManager;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import java.util.ArrayList;

public class Permisos implements ActivityCompat.OnRequestPermissionsResultCallback {
    private Activity contexto;
    private final String PERMISO_LECTURA = Manifest.permission.READ_EXTERNAL_STORAGE;
    private final String PERMISO_ESCRITURA = Manifest.permission.WRITE_EXTERNAL_STORAGE;
    private final String PERMISO_ACCESOTEL = Manifest.permission.READ_PHONE_STATE;

    private final int REQUESTCODE = 1;
    private final int REQUESTCODE2 = 2;
    public Permisos(Activity contexto) {
        this.contexto = contexto;
    }

    public void SolicitarTodosLosPerimisos()
    {
        if(contexto != null && !HasAllPermisos())
        {
            ArrayList<String> ListaPermisos = new ArrayList<>();
            if(!HasPermisosLecutra())
            {
                ListaPermisos.add(PERMISO_LECTURA);
            }
            if(!HasPermisosEscritura())
            {
                ListaPermisos.add(PERMISO_ESCRITURA);
            }
            if(!HasPermisosIMEI())
            {
                ListaPermisos.add(PERMISO_ACCESOTEL);
            }
            String[] ListaParam = new String[ListaPermisos.size()];
            for(int cnt = 0; cnt < ListaParam.length; cnt++)
            {
                ListaParam[cnt] = ListaPermisos.get(cnt);
            }
            ActivityCompat.requestPermissions(contexto, ListaParam, REQUESTCODE);

        }
    }

    public boolean HasAllPermisos()
    {
        boolean ret = false;
        if (HasPermisosIMEI() && HasPermisosEscritura() && HasPermisosLecutra())
        {
            ret = true;
        }
        return ret;
    }
    private boolean HasPermisosLecutra()
    {
        boolean ret = false;
        if (ActivityCompat.checkSelfPermission(contexto, PERMISO_LECTURA) == PackageManager.PERMISSION_GRANTED)
        {
            ret = true;
        }
        return ret;
    }
    private boolean HasPermisosEscritura()
    {
        boolean ret = false;
        if (ActivityCompat.checkSelfPermission(contexto, PERMISO_ESCRITURA) == PackageManager.PERMISSION_GRANTED)
        {
            ret = true;
        }
        return ret;
    }
    private boolean HasPermisosIMEI()
    {
        boolean ret = false;
        if (ActivityCompat.checkSelfPermission(contexto, PERMISO_ACCESOTEL) == PackageManager.PERMISSION_GRANTED)
        {
            ret = true;
        }
        return ret;
    }

    public void Imei()
    {
        if (ActivityCompat.checkSelfPermission(contexto, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED)
        {
            TelephonyManager telephonyManager = (TelephonyManager)contexto.getSystemService(Context.TELEPHONY_SERVICE);
            String e = telephonyManager.getDeviceId();
            System.out.println("IMEI: "+e);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode == REQUESTCODE)
        {
            System.out.println("pues la verdad esto cada vez es mas intuil...");
        }
    }
}
