import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.telephony.TelephonyManager;

import androidx.core.app.ActivityCompat;

public class Permisos {
    Context contexto;

    public Permisos(Context contexto) {
        this.contexto = contexto;
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
}
