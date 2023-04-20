package co.edu.unipiloto.proyecto_3r;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import co.edu.unipiloto.proyecto_3r.db.DbProductos;
import co.edu.unipiloto.proyecto_3r.entidades.Productos;

public class EditarActivity extends AppCompatActivity {

    public EditText txtNombre,txtDescripcion,txtValor;
    Button btnGuardar,btnEditar;

    Productos producto;

    boolean correcto = false;
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

        DbProductos dbProductos = new DbProductos(EditarActivity.this);
        producto = dbProductos.verProducto(id);

        if(producto != null){
            txtNombre.setText(producto.getNombre());
            txtDescripcion.setText(producto.getDescripcion());
            txtValor.setText(producto.getValor());
            btnEditar.setVisibility(View.INVISIBLE);

        }
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!txtNombre.getText().toString().equals("") && !txtDescripcion.getText().toString().equals("") && !txtValor.getText().toString().equals("")){
                    correcto = dbProductos.editarProducto(id,txtNombre.getText().toString(),txtDescripcion.getText().toString(),txtValor.getText().toString());

                    if (correcto){
                        Toast.makeText(EditarActivity.this, "Edicion Correcta", Toast.LENGTH_SHORT).show();
                        verRegistro();
                    }else{
                        Toast.makeText(EditarActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(EditarActivity.this, "LLena todos los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void verRegistro(){
        Intent intent = new Intent(this,VerActivity.class);
        intent.putExtra("ID",id);
        startActivity(intent);
    }
}

