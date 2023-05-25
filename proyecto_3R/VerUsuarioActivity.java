package co.edu.unipiloto.proyecto_3r;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

import co.edu.unipiloto.proyecto_3r.db.DbUsuarios;

public class VerUsuarioActivity extends AppCompatActivity {

    private EditText email;
    Button btnEditar;
    boolean correcto = false;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_usuario);

        email = (EditText) findViewById(R.id.correoTxt);

        btnEditar = findViewById(R.id.cambioBtn);
        mAuth = FirebaseAuth.getInstance();

        DbUsuarios dbUsuarios = new DbUsuarios(VerUsuarioActivity.this);

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!email.getText().toString().equals("") ) {
                    resetPassword(email.getText().toString());
                } else {
                    Toast.makeText(VerUsuarioActivity.this, "LLena todos los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });




    }
    public void resetPassword(String email){
        mAuth.setLanguageCode("es");
        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    AlertDialog.Builder alerta = new AlertDialog.Builder(VerUsuarioActivity.this);
                    alerta.setMessage("Se envio un correo para que confirmes tu cambio de contraseña, es importante que ingreses la misma que ingresaste anteriormente");
                    alerta.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            salir();
                            dialog.cancel();
                        }
                    });
                    AlertDialog alert = alerta.create();
                    alert.show();
                    Toast.makeText(VerUsuarioActivity.this, "Se envio un correo para restrablecer tu contraseña", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(VerUsuarioActivity.this, "No se pudo enviar el correo", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void salir(){
            Intent intent = new Intent(this,HomeActivity.class);
            startActivity(intent);
    }
}