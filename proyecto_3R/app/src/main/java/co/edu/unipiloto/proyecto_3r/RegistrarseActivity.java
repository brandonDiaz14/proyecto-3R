package co.edu.unipiloto.proyecto_3r;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import co.edu.unipiloto.proyecto_3r.db.DbUsuarios;

public class RegistrarseActivity extends AppCompatActivity {

    private EditText nombre;

    private EditText usuario;
    private EditText email;
    private EditText password;
    private EditText passwordV;
    private EditText direccion;
    private EditText edad;
    private RadioGroup generoId;

    private RadioButton  rb1,rb2;



    private FirebaseAuth firebaseAuth;
    private  FirebaseAuth.AuthStateListener authStateListener;



    // ...
    // Initialize Firebase Auth

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);

        nombre = (EditText) findViewById(R.id.nombreProducto);
        usuario = (EditText) findViewById(R.id.usuario);
        email = (EditText) findViewById(R.id.correo);
        password = (EditText) findViewById(R.id.contrasena);
        passwordV = (EditText) findViewById(R.id.contrasenaConfirmacion);
        direccion = (EditText) findViewById(R.id.direccion);
        edad = (EditText) findViewById(R.id.edad);

        rb1 = (RadioButton) findViewById(R.id.femeninoRbtn);
        rb2 = (RadioButton) findViewById(R.id.masculinoRbtn);






        firebaseAuth =  FirebaseAuth.getInstance();

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null){
                    Toast.makeText(RegistrarseActivity.this, "El usuario fue registrado", Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(RegistrarseActivity.this, "El usuario salio de la sesion", Toast.LENGTH_SHORT).show();
                }
            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(authStateListener != null){
            firebaseAuth.removeAuthStateListener(authStateListener);
        }
    }


    public void registrarUsuario (View view){
        String nombreU = nombre.getText().toString();
        String usuarioU = usuario.getText().toString();
        String emailU = email.getText().toString();
        String passwordU = password.getText().toString();
        String passwordVUser = passwordV.getText().toString();
        String direccionU = direccion.getText().toString();
        String edadS = edad.getText().toString();
        int edadU = Integer.parseInt(edadS);
        String generoU = "";
        if(rb1.isChecked()){
            generoU = "Femenino";
        } else if (rb2.isChecked()) {
            generoU = "Masculino";
        }


        if(passwordU.equals(passwordVUser) ){
            if(edadU >= 18){
                firebaseAuth.createUserWithEmailAndPassword(emailU,passwordU).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!task.isSuccessful()){
                            Toast.makeText(RegistrarseActivity.this, "No fue posible Realizar la accion", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                DbUsuarios usuarioDb = new DbUsuarios(RegistrarseActivity.this);
                long id = usuarioDb.insertaUsuario(nombreU,usuarioU,emailU,passwordU,direccionU,edadU,generoU);
                Intent i = new Intent(RegistrarseActivity.this, HomeActivity.class);
                startActivity(i);
                if(id>0){
                    Toast.makeText(RegistrarseActivity.this, "se registro correctamente", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(RegistrarseActivity.this, "No fue posible Realizar la accion", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(this, "Error, Eres menor de edad", Toast.LENGTH_SHORT).show();
            }

        }else{
            Toast.makeText(this, "Error, Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
        }

    }


}