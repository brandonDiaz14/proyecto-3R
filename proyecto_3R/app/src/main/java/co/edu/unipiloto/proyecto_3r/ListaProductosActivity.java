package co.edu.unipiloto.proyecto_3r;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

import co.edu.unipiloto.proyecto_3r.adaptadpres.ListaProductosAdapter;
import co.edu.unipiloto.proyecto_3r.db.DbProductos;
import co.edu.unipiloto.proyecto_3r.entidades.Productos;

public class ListaProductosActivity extends AppCompatActivity {

    RecyclerView listaProductos;
    ArrayList<Productos> listaArrayProductos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_productos);
        listaProductos = findViewById(R.id.listaProductos);

        listaProductos.setLayoutManager(new LinearLayoutManager(this));

        DbProductos productosDb = new DbProductos(ListaProductosActivity.this);
        listaArrayProductos = new ArrayList<>();
        ListaProductosAdapter adapter = new ListaProductosAdapter(productosDb.mostrarProducto());
        listaProductos.setAdapter(adapter);


    }

    public void atras (View view){
        Intent i = new Intent(this, MainAdmin.class);
        startActivity(i);
    }

}