package co.edu.unipiloto.proyecto_3r;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import co.edu.unipiloto.proyecto_3r.db.DbHelper;

public class HomeActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


    }



    public void verProducto (View view){
        Intent i = new Intent(this, Catalogo.class);
        startActivity(i);
    }



    public void cerrarSesion (View view){
        Intent i = new Intent(this, IniciarSesionActivity.class);
        startActivity(i);
    }

    public void verMapa(View view){
        Intent i = new Intent(this, MapaVista.class);
        startActivity(i);
    }



}