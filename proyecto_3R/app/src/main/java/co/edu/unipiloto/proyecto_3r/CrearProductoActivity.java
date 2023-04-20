package co.edu.unipiloto.proyecto_3r;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import co.edu.unipiloto.proyecto_3r.db.DbProductos;

public class CrearProductoActivity extends AppCompatActivity {

    private EditText nombre;

    private EditText descripcion;
    private EditText valor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_producto);

        nombre = (EditText) findViewById(R.id.nombreProducto);
        descripcion = (EditText) findViewById(R.id.descripcionProducto);
        valor = (EditText) findViewById(R.id.valorProducto);

    }

    public void registrarProducto (View view){
        String nombreP = nombre.getText().toString();
        String descripcionP = descripcion.getText().toString();
        String valorP = valor.getText().toString();
        DbProductos productosDb = new DbProductos(CrearProductoActivity.this);
        long id = productosDb.insertaProducto(nombreP,descripcionP,valorP);
        Intent i = new Intent(CrearProductoActivity.this, MainAdmin.class);
        startActivity(i);
        if(id>0){
            Toast.makeText(CrearProductoActivity.this, "se registro correctamente", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(CrearProductoActivity.this, "No fue posible Realizar la accion", Toast.LENGTH_SHORT).show();
        }
    }
}