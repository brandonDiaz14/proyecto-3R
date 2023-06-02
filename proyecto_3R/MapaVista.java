package co.edu.unipiloto.proyecto_3r;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MapaVista extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener, GoogleMap.OnMapLongClickListener {

    EditText txtLatitud, txtLongitud;
    GoogleMap mMap;
    private static final int REQUEST_LOCATION_PERMISSION = 1;

    private FusedLocationProviderClient fusedLocationClient;
    private LocationCallback locationCallback;

    private Button btnGetLocation;

    private GoogleMap googleMap;
    private List<LatLng> routePoints;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);

        txtLatitud = findViewById(R.id.txtLatitud);
        txtLongitud = findViewById(R.id.txtLongitud);
        btnGetLocation = (Button) findViewById(R.id.btnGetLocation);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        btnGetLocation = findViewById(R.id.btnGetLocation);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        btnGetLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                obtenerLocalizacion();
                Intent intent = new Intent(MapaVista.this,MessageService.class);
                intent.putExtra(MessageService.EXTRA_MESSAGE,"Solicitando repartidor");
                startService(intent);
            }
        });

    }

    private void  obtenerLocalizacion(){
        if(ActivityCompat.checkSelfPermission(MapaVista.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            fusedLocationClient.getLastLocation().addOnCompleteListener(
                    new OnCompleteListener<Location>() {
                        @Override
                        public void onComplete(@NonNull Task<Location> task) {
                            Location location = task.getResult();
                            if(location != null){
                                Geocoder geo = new Geocoder(MapaVista.this, Locale.getDefault());
                                try {
                                    List<Address> listaDirecciones= geo.getFromLocation(location.getLatitude(),location.getLongitude(),1);
                                    txtLatitud.setText(""+listaDirecciones.get(0).getLatitude());
                                    txtLongitud.setText(""+listaDirecciones.get(0).getLongitude());
                                    int lat = (int) listaDirecciones.get(0).getLatitude();
                                    int longi = (int) listaDirecciones.get(0).getLongitude();
                                    puntoActual(lat,longi);

                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        }
                    });
        }else {
            ActivityCompat.requestPermissions(MapaVista.this,new String [] {
                    Manifest.permission.ACCESS_FINE_LOCATION
                    },
                    44);

        }

    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        this.mMap.setOnMapClickListener(this);
        this.mMap.setOnMapLongClickListener(this);

        puntos();
    }
    public void puntos() {
        LatLng colombia = new LatLng(4.5147764,-74.1165862);
        BitmapDescriptor icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE);
        mMap.addMarker(new MarkerOptions().position(colombia)
                .title("Deposito de chatarra").icon(icon));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(colombia));

    }
    public void puntoActual(int latitud,int longitud) {
        LatLng colombia = new LatLng(latitud, longitud);
        mMap.addMarker(new MarkerOptions().position(colombia).title("Aqui estas tu"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(colombia));
    }

    @Override
    public void onMapClick(@NonNull LatLng latLng) {
        txtLatitud.setText(""+latLng.latitude);
        txtLongitud.setText(""+latLng.longitude);
    }

    @Override
    public void onMapLongClick(@NonNull LatLng latLng) {
        txtLatitud.setText(""+latLng.latitude);
        txtLongitud.setText(""+latLng.longitude);
    }
    private void drawRouteOnMap(List<LatLng> routePoints) {
        if (googleMap != null && routePoints.size() >= 2) {
            PolylineOptions polylineOptions = new PolylineOptions();
            polylineOptions.color(Color.BLUE);
            polylineOptions.width(5);

            for (LatLng point : routePoints) {
                polylineOptions.add(point);
            }

            Polyline polyline = googleMap.addPolyline(polylineOptions);
        }
    }

    public void verDistancia (View view){
        Intent i = new Intent(this, OdometerActivity.class);
        startActivity(i);
    }

    public void onSendMaps(View view) {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https")
                .authority("www.google.com")
                .appendPath("maps")
                .appendPath("dir")
                .appendPath("")
                .appendQueryParameter("api", "1")
                .appendQueryParameter("destination", 4.5147764 + "," + -74.1165862);
        String url = builder.build().toString();
        Log.d("Directions", url);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }
}