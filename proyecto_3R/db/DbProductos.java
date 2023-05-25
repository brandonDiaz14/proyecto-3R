package co.edu.unipiloto.proyecto_3r.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import co.edu.unipiloto.proyecto_3r.entidades.Productos;

public class DbProductos extends DbHelper{

    Context context;

    public DbProductos(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertaProducto(String nombre,String descripcion,String valor ){
        long id = 0;
        try{
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("nombre",nombre);
            values.put("descripcion",descripcion);
            values.put("valor",valor);

            id = db.insert(TABLE_PRODUCTO,null,values);

        }catch (Exception ex){
            ex.toString();
        }
        return id;
    }

    public ArrayList<Productos> mostrarProducto(){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Productos> listaProductos = new ArrayList<>();
        Productos produ = null;
        Cursor cursorProductos = null;

        cursorProductos = db.rawQuery("SELECT * FROM " + TABLE_PRODUCTO,null);

        if(cursorProductos.moveToFirst()){
            do {
                produ = new Productos();
                produ.setId(cursorProductos.getInt(0));
                produ.setNombre(cursorProductos.getString(1));
                produ.setDescripcion(cursorProductos.getString(2));
                produ.setValor(cursorProductos.getString(3));
                listaProductos.add(produ);
            }while (cursorProductos.moveToNext());
        }

        cursorProductos.close();

        return listaProductos;


    }

    public Productos verProducto(int id){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();


        Productos produ = null;
        Cursor cursorProductos = null;

        cursorProductos = db.rawQuery("SELECT * FROM " + TABLE_PRODUCTO + " WHERE id = " + id + " LIMIT 1",null);

        if(cursorProductos.moveToFirst()){

                produ = new Productos();
                produ.setId(cursorProductos.getInt(0));
                produ.setNombre(cursorProductos.getString(1));
                produ.setDescripcion(cursorProductos.getString(2));
                produ.setValor(cursorProductos.getString(3));


        }

        cursorProductos.close();

        return produ;


    }

    public boolean editarProducto(int id,String nombre,String descripcion,String valor ){
        boolean correcto = false;
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try{

            db.execSQL("UPDATE "+ TABLE_PRODUCTO + " SET nombre = '"+nombre+"', descripcion = '"+descripcion+"', valor = '"+valor+"' WHERE id =  '" + id +"'" );
            correcto = true;

        }catch (Exception ex){
            ex.toString();
            correcto = false;
        }finally {
            db.close();
        }
        return correcto;
    }

    public boolean eliminarProducto(int id){
        boolean correcto = false;
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try{

            db.execSQL("DELETE FROM " + TABLE_PRODUCTO + " WHERE id = '"+id+"'");
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
