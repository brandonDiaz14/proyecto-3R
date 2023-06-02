package co.edu.unipiloto.proyecto_3r;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.TextView;

import java.util.Locale;

public class OdometerActivity extends AppCompatActivity {


    private OdometerServices odometro;
    private  boolean enlazado = false;



    private ServiceConnection connection = new ServiceConnection() {


        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            OdometerServices.OdometerBinder odometerBinder = (OdometerServices.OdometerBinder) iBinder;
            odometro = odometerBinder.getOdometer();
            enlazado = true;
        }



        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            enlazado = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_odometer);
        mostrarDistancia();
        if (ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this,
                        android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            Log.v("DB","No aceptados");
        } else {
            Log.v("DB", "PERMISSION GRANTED");
        }



    }

    @Override
    public void onStart(){
        super.onStart();
        Intent intent = new Intent (this, OdometerServices.class);
        bindService(intent,connection, Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onStop(){
        super.onStop();
        if(enlazado){
            unbindService(connection);
            enlazado = false;
        }
    }

    private void mostrarDistancia(){
        TextView verDistancia = (TextView) findViewById(R.id.distancia);
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                double distancia = 0.0;
                if(enlazado && odometro != null){
                    distancia = odometro.getDistancia();

                }
                String distanciaStr = String.format(Locale.getDefault(),"%1$,.2f Kilometros recorridos",distancia);
                verDistancia.setText(distanciaStr);
                handler.postDelayed(this,200);
            }
        });
    }

}