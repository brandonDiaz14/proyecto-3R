package co.edu.unipiloto.proyecto_3r;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import co.edu.unipiloto.proyecto_3r.db.DbHelper;

public class MainAdmin extends AppCompatActivity {

    private Toolbar toolbar1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_admin);
        toolbar1 = findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar1);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflate =  getMenuInflater();
        inflate.inflate(R.menu.menu1, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.opcion1:
                nuevoRegistro();
                return true;
            case R.id.opcion2:
                verProductoOpcion ();
                return true;
            case R.id.opcion3:
                cerrarSesionOpcion ();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void nuevoRegistro(){
        Intent intent = new Intent(this,HomeActivity.class);
        startActivity(intent);
    }

    private void verProductoOpcion (){
        Intent intent = new Intent(this,ListaProductosActivity.class);
        startActivity(intent);
    }

    private void cerrarSesionOpcion (){
        Intent i = new Intent(this, IniciarSesionActivity.class);
        startActivity(i);
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