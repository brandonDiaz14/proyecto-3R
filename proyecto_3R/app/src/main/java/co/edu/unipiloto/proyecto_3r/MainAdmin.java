package co.edu.unipiloto.proyecto_3r;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import co.edu.unipiloto.proyecto_3r.db.DbHelper;

public class MainAdmin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_admin);
    }

    public void crearDataBase (View view){
        DbHelper dbHelper = new DbHelper(MainAdmin.this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Toast.makeText(MainAdmin.this, "se creo la base de datos", Toast.LENGTH_SHORT).show();
        if(db != null){
            Toast.makeText(MainAdmin.this, "se creo la base de datos", Toast.LENGTH_SHORT).show();

            Intent i = new Intent(MainAdmin.this, MainAdmin.class);
            startActivity(i);
        }else{
            Toast.makeText(MainAdmin.this, "no se creo la base de datos", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(MainAdmin.this, MainAdmin.class);
            startActivity(i);
        }


    }
    public void crearDataBaseProducto (View view){
        Intent i = new Intent(this, CrearProductoActivity.class);
        startActivity(i);
    }
    public void verProducto (View view){
        Intent i = new Intent(this, ListaProductosActivity.class);
        startActivity(i);
    }



    public void cerrarSesion (View view){
        Intent i = new Intent(this, IniciarSesionActivity.class);
        startActivity(i);
    }

}