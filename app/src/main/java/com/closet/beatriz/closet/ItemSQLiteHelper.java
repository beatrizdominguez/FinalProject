package com.closet.beatriz.closet;

/**
 * Created by Beatriz.
 */

import java.util.ArrayList;
import java.util.HashMap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.Toast;

public class ItemSQLiteHelper extends SQLiteOpenHelper {

    // Sentencia SQL para crear la tabla de Usuarios
    String sqlCreateItems = "CREATE TABLE Items (id INTEGER PRIMARY KEY AUTOINCREMENT, description VARCHAR(30), category VARCHAR(20), i_size VARCHAR(6), s_date VARCHAR(20), price FLOAT, shop VARCHAR(10))";

    public ItemSQLiteHelper(Context contexto, String nombre,
                            CursorFactory factory, int version) {
        super(contexto, nombre, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Se ejecuta la sentencia SQL de creación de la tabla
        db.execSQL(sqlCreateItems);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnterior,
                          int versionNueva) {

        // Se elimina la versión anterior de la tabla
        db.execSQL("DROP TABLE IF EXISTS Items");

        // Se crea la nueva versión de la tabla
        db.execSQL(sqlCreateItems);
    }

    public void cargarLista(ArrayList<Item> ALCamisetas, ArrayList<Item> ALPantalones, ArrayList<Item> ALRopainterior, ArrayList<Item> ALAbrigos) {


        Log.e("TAG-----------", "en el helper cargarlista");

        Item i;

        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM Items";
        Cursor c = db.rawQuery(selectQuery, null);


        int idIdx = c.getColumnIndex("id");
        // int fotoIdx = c.getColumnIndex("foto");
        int descriptionIdx = c.getColumnIndex("description");
        int categoryIdx = c.getColumnIndex("category");
        int sizeIdx = c.getColumnIndex("i_size");
        int dateIdx = c.getColumnIndex("s_date");
        int priceIdx = c.getColumnIndex("price");
        int shopIdx = c.getColumnIndex("shop");

        //Log.e("TAG", "id" + idIdx);

        if (c.moveToFirst()) {
            do {

                // // cargamos la información en el objeto
                int cId = c.getInt(idIdx);
                // String cFoto = c.getString(fotoIdx);
                String cDescription = c.getString(descriptionIdx);
                String cCategory = c.getString(categoryIdx);
                String cSize = c.getString(sizeIdx);
                String cDate = c.getString(dateIdx);
                Float cPrice = c.getFloat(priceIdx);
                String cShop = c.getString(shopIdx);
                //Log.e("foto-helper-cargar", cFoto);


                i = new Item(cId, cDescription, cCategory, cDate, cSize,
                        cPrice, cShop);

                // cargamos la informavión en la lista


                if (cCategory.equals("Camisetas")) {
                    ALCamisetas.add(i);
                } else if (cCategory.equals("Pantalones")) {
                    ALPantalones.add(i);
                } else if (cCategory.equals("Ropa interior")) {
                    ALRopainterior.add(i);
                }
                //AÑADIR LAS QUE QUEDARN!!!!!!!!!!!!!!!

            } while (c.moveToNext());
        }
        c.close();
        db.close();

    }

    public void guardarItem(Item i) {


        SQLiteDatabase db = this.getReadableDatabase();

        String sqlInertItem = "INSERT INTO Items ( description, category, i_size, s_date, price, shop ) VALUES ('" +
                i.getDescription() +
                "', '" +
                i.getCategory() + "', '" + i.getSize() + "', '" + i.getS_date() + "', " + i.getPrize() + ", '" + i.getShop() + "');";
        db.execSQL(sqlInertItem);

    }

    /*
    public void guardarTodosItem(ArrayList<Vestido> lista) {
        for (int i = 0; i < lista.size(); i++) {
            //  guardarItem(lista[i]);
        }
    }
*/


    ////not changed
    public int updateVestido(HashMap<String, String> queryValues) {

        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        //values.put("id", queryValues.get("id"));
        Log.e("ID--", queryValues.get("id"));
        values.put("foto", queryValues.get("foto"));
        values.put("tienda", queryValues.get("tienda"));
        values.put("disenador", queryValues.get("disenador"));
        values.put("color", queryValues.get("color"));
        values.put("corte", queryValues.get("corte"));
        values.put("escote", queryValues.get("escote"));
        values.put("valoracion", queryValues.get("valoracion"));
        values.put("precio", queryValues.get("precio"));

        //  return 1;
        Log.e("MODIFICAR", queryValues.get("id"));

        return database.update("Vestidos", values, "id" + " = ?",
                new String[]{queryValues.get("id")});
    }

    public void deleteItem(int id) {
        SQLiteDatabase database = this.getWritableDatabase();
        String deleteQuery = "DELETE FROM Items where id='" + id + "'";
        // Log.d("query", deleteQuery);
        database.execSQL(deleteQuery);
    }

    public void deleteAll() {

        SQLiteDatabase database = this.getWritableDatabase();
        String borrarTodas = "DELETE FROM Items";
        database.execSQL(borrarTodas);

    }

}