package co.edu.unipiloto.proyecto_3r.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;


public class DbUsuarios extends DbHelper{

    Context context;
    public DbUsuarios(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertaUsuario(String nombre,String usuario,String email,String password,String direccion,int edad,String genero ){
        long id = 0;
        try{
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("nombre",nombre);
            values.put("usuario",usuario);
            values.put("email",email);
            values.put("password",password);
            values.put("direccion",direccion);
            values.put("edad",edad);
            values.put("genero",genero);
            id = db.insert(TABLE_USUARIO,null,values);

        }catch (Exception ex){
            ex.toString();
        }
        return id;
    }

    public boolean editarPassword(String email,String password,String passwordN ){
        boolean correcto = false;
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try{

            db.execSQL("UPDATE "+ TABLE_USUARIO + " SET password = '"+passwordN+"' WHERE password =  '" + password +"'" );
            correcto = true;

        }catch (Exception ex){
            ex.toString();
            correcto = false;
        }finally {
            db.close();
        }
        return correcto;
    }
}
