package co.edu.unipiloto.proyecto_3r;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import co.edu.unipiloto.proyecto_3r.db.DbHelper;

public class HomeActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


    }

    public void irRegistrarse (View view){
        Intent i = new Intent(this, RegistrarseActivity.class);
        startActivity(i);



    }
}