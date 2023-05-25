package co.edu.unipiloto.proyecto_3r;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import co.edu.unipiloto.proyecto_3r.db.DbProductos;
import co.edu.unipiloto.proyecto_3r.entidades.Productos;

public class VerActivity extends AppCompatActivity {

    public EditText txtNombre,txtDescripcion,txtValor;
    Button btnGuardar,btnEditar, btnEliminar;

    Productos producto;
    int id =0;


    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver);

        txtNombre =  findViewById(R.id.nombreProducto);
        txtDescripcion =  findViewById(R.id.descripcionProducto);
        txtValor =  findViewById(R.id.valorProducto);
        btnGuardar = findViewById(R.id.buttonP);
        btnEditar = findViewById(R.id.btnEditar);
        btnEliminar = findViewById(R.id.eliminarBtn);

        if(savedInstanceState == null){
             Bundle extras = getIntent().getExtras();
             if(extras==null){
                 id = Integer.parseInt(null);
             }else{
                 id = extras.getInt("ID");
             }
        }else{
            id = (int) savedInstanceState.getSerializable("ID");
        }

        DbProductos dbProductos = new DbProductos(VerActivity.this);
        producto = dbProductos.verProducto(id);

        if(producto != null){
            txtNombre.setText(producto.getNombre());
            txtDescripcion.setText(producto.getDescripcion());
            txtValor.setText(producto.getValor());
            btnGuardar.setVisibility(View.INVISIBLE);
            txtNombre.setInputType(InputType.TYPE_NULL);
            txtDescripcion.setInputType(InputType.TYPE_NULL);
            txtValor.setInputType(InputType.TYPE_NULL);
        }
        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VerActivity.this,EditarActivity.class);
                intent.putExtra("ID",id);
                startActivity(intent);
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(VerActivity.this);
                builder.setMessage("Desea elminar este producto?")
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                if(dbProductos.eliminarProducto(id)){
                                    lista();
                                }
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).show();
            }
        });
    }

    private void lista(){
        Intent intent = new Intent(this,MainAdmin.class);
        startActivity(intent);
    }
}