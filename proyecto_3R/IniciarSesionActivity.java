package co.edu.unipiloto.proyecto_3r;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class IniciarSesionActivity extends AppCompatActivity {

    private EditText email;
    private EditText password;


    private FirebaseAuth firebaseAuth;
    private  FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar_sesion);

        email = (EditText) findViewById(R.id.correo);
        password = (EditText) findViewById(R.id.contrasena);

        firebaseAuth =  FirebaseAuth.getInstance();

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null){
                    Toast.makeText(IniciarSesionActivity.this, "El usuario inicio sesion", Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(IniciarSesionActivity.this, "El usuario salio de la sesion", Toast.LENGTH_SHORT).show();
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
    public void login (View view){
        String emailUser = email.getText().toString();
        String passwordU = password.getText().toString();
        if(emailUser.equals("admin") && passwordU.equals("hola123")){
            Intent i = new Intent(IniciarSesionActivity.this, MainAdmin.class);
            startActivity(i);
        }else{
            firebaseAuth.signInWithEmailAndPassword(emailUser,passwordU).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (!task.isSuccessful()){
                        Toast.makeText(IniciarSesionActivity.this,"Error al iniciar sesion", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(IniciarSesionActivity.this, IniciarSesionActivity.class);
                        startActivity(i);
                    }else{
                        Intent i = new Intent(IniciarSesionActivity.this, HomeActivity.class);
                        startActivity(i);
                    }
                }
            });
        }

    }

    public void cambiarPassword (View view){
        Intent i = new Intent(this, VerUsuarioActivity.class);
        startActivity(i);
    }
}