package co.edu.unipiloto.proyecto_3r;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class Catalogo extends AppCompatActivity {

    private RecyclerViewAdaptador adaptadorProducto ;
    private RecyclerView recyclerViewProducto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalogo);
        recyclerViewProducto=(RecyclerView) findViewById(R.id.reciclerProducto);
        recyclerViewProducto.setLayoutManager(new LinearLayoutManager(this));
        adaptadorProducto = new RecyclerViewAdaptador(obtenerProductos());
        recyclerViewProducto.setAdapter(adaptadorProducto);
    }

    public List<CatalogoProducto> obtenerProductos(){
        List<CatalogoProducto> producto = new ArrayList<>();
        producto.add(new CatalogoProducto("Carton","Carton de cajas o productos a base de carton","500",R.drawable.p2));
        producto.add(new CatalogoProducto("Papel","papel de cuadernos,papel periodico o de revistas","500",R.drawable.p1));
        producto.add(new CatalogoProducto("plastico","botellas de plastico de todo tipo","600",R.drawable.p5));

        return producto;
    }
}