package co.edu.unipiloto.proyecto_3r;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import java.util.Random;

public class OdometerServices extends Service {


    private final IBinder binder = new OdometerBinder();

    private final Random aleatorio = new Random();
    private LocationListener listener;

    private static double distanciaMetros;

    private static Location ultimaLocalizacion = null;

    private LocationManager locManager;

    public OdometerServices() {
    }

    public class OdometerBinder extends Binder {
        OdometerServices getOdometer() {
            return OdometerServices.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return binder;
    }

    public double getDistancia() {
        return this.distanciaMetros/1000;
    }

    @Override
    public void onCreate() {
        listener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                if (ultimaLocalizacion == null) {
                    ultimaLocalizacion.setLatitude(4.5147764);
                    ultimaLocalizacion.setLongitude(-74.1165862);
                }

                distanciaMetros = location.distanceTo(ultimaLocalizacion);

            }

            @Override
            public void onProviderDisabled(String arg0) {

            }

            @Override
            public void onProviderEnabled(String arg0) {

            }

            @Override
            public void onStatusChanged(String arg0, int arg1, Bundle bundle) {

            }
        };

        locManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);



            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                String proveedor = locManager.getBestProvider(new Criteria(), true);
                if (proveedor != null) {
                    locManager.requestLocationUpdates(proveedor, 2000, 1, listener);


                }

            }else {
                return;
            }

    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        if(locManager != null && listener != null){
            locManager.removeUpdates(listener);
        }
        locManager=null;
        listener=null;
    }


}