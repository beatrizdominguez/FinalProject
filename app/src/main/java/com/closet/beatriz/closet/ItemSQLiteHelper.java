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
    String sqlCreateItems = "CREATE TABLE Items (id INTEGER PRIMARY KEY AUTOINCREMENT, image BLOB, description VARCHAR(30), category VARCHAR(20), i_size VARCHAR(6), s_date VARCHAR(20), price FLOAT, shop VARCHAR(10))";

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
        int fotoIdx = c.getColumnIndex("image");
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
                String cFoto = c.getString(fotoIdx);
                String cDescription = c.getString(descriptionIdx);
                String cCategory = c.getString(categoryIdx);
                String cSize = c.getString(sizeIdx);
                String cDate = c.getString(dateIdx);
                Float cPrice = c.getFloat(priceIdx);
                String cShop = c.getString(shopIdx);
                //Log.e("foto-helper-cargar", cFoto);


                i = new Item(cId, cFoto, cDescription, cCategory, cDate, cSize,
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

        // Log.e("-----guardar item helper", i.getImage());
        SQLiteDatabase db = this.getReadableDatabase();

        String sqlInertItem = "INSERT INTO Items (image, description, category, i_size, s_date, price, shop ) VALUES ('" +
                i.getImage() + "', '" +
                i.getDescription() + "', '" +
                i.getCategory() + "', '" +
                i.getSize() + "', '" +
                i.getS_date() + "', " +
                i.getPrize() + ", '" +
                i.getShop() + "');";

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

    public Float[] priceStatistics(Context context) {


        //Log.e("TAG-----------", "en el helper cargarlista");

        Item i;

        float totalValue = 0;
        float valueShirts = 0;
        float valuePants = 0;
        float valueUnderWear = 0;
        float valueShoes = 0;
        float valueJumper = 0;
        float valuePijamas = 0;
        float valueDress = 0;
        float valueCoats = 0;
        float valueAccessories = 0;

        //string of num of categories +1
        Float[] ItemValue = new Float[10];

        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM Items";
        Cursor c = db.rawQuery(selectQuery, null);


        int idIdx = c.getColumnIndex("id");
        int fotoIdx = c.getColumnIndex("image");
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
                String cFoto = c.getString(fotoIdx);
                String cDescription = c.getString(descriptionIdx);
                String cCategory = c.getString(categoryIdx);
                String cSize = c.getString(sizeIdx);
                String cDate = c.getString(dateIdx);
                Float cPrice = c.getFloat(priceIdx);
                String cShop = c.getString(shopIdx);
                //Log.e("foto-helper-cargar", cFoto);


                i = new Item(cId, cFoto, cDescription, cCategory, cDate, cSize,
                        cPrice, cShop);
                totalValue += i.getPrize();

                Log.e("--------cat", context.getResources().getString(R.string.catCoats));
                // cargamos la informavión en la lista
                if (cCategory.equals(context.getResources().getString(R.string.catShirts))) {
                    valueShirts += i.getPrize();
                } else if (cCategory.equals(context.getResources().getString(R.string.catPants))) {
                    valuePants += i.getPrize();
                } else if (cCategory.equals(context.getResources().getString(R.string.catUnderWear))) {
                    valueUnderWear += i.getPrize();
                } else if (cCategory.equals(context.getResources().getString(R.string.catCoats))) {
                    valueCoats += i.getPrize();
                } else if (cCategory.equals(context.getResources().getString(R.string.catShoes))) {
                    valueShoes += i.getPrize();
                } else if (cCategory.equals(context.getResources().getString(R.string.catJumper))) {
                    valueJumper += i.getPrize();
                } else if (cCategory.equals(context.getResources().getString(R.string.catPijamas))) {
                    valuePijamas += i.getPrize();
                } else if (cCategory.equals(context.getResources().getString(R.string.catDress))) {
                    valueDress += i.getPrize();
                } else if (cCategory.equals(context.getResources().getString(R.string.catAccesories))) {
                    valueAccessories += i.getPrize();
                }
                //AÑADIR LAS QUE QUEDARN!!!!!!!!!!!!!!!

            } while (c.moveToNext());
        }

        ItemValue[0] = totalValue;
        ItemValue[1] = valueShirts;
        ItemValue[2] = valuePants;
        ItemValue[3] = valueUnderWear;
        ItemValue[4] = valueShoes;
        ItemValue[5] = valueJumper;
        ItemValue[6] = valuePijamas;
        ItemValue[7] = valueDress;
        ItemValue[8] = valueCoats;
        ItemValue[9] = valueAccessories;
        //etc add all categories

        c.close();
        db.close();

        return ItemValue;
    }

    public Integer[] countStatistics(Context context) {


        //Log.e("TAG-----------", "en el helper cargarlista");

        Item i;

        int totalCount = 0;
        int countShirts = 0;
        int countPants = 0;
        int countUnderWear = 0;
        int countShoes = 0;
        int countJumper = 0;
        int countPijamas = 0;
        int countDress = 0;
        int counteCoats = 0;
        int countAccessories = 0;

        //string of num of categories +1
        Integer[] ItemCount = new Integer[10];

        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM Items";
        Cursor c = db.rawQuery(selectQuery, null);


        int idIdx = c.getColumnIndex("id");
        int fotoIdx = c.getColumnIndex("image");
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
                String cFoto = c.getString(fotoIdx);
                String cDescription = c.getString(descriptionIdx);
                String cCategory = c.getString(categoryIdx);
                String cSize = c.getString(sizeIdx);
                String cDate = c.getString(dateIdx);
                Float cPrice = c.getFloat(priceIdx);
                String cShop = c.getString(shopIdx);
                //Log.e("foto-helper-cargar", cFoto);


                i = new Item(cId, cFoto, cDescription, cCategory, cDate, cSize,
                        cPrice, cShop);
                totalCount++;

                Log.e("--------cat", context.getResources().getString(R.string.catCoats));
                // cargamos la informavión en la lista
                if (cCategory.equals(context.getResources().getString(R.string.catShirts))) {

                    countShirts++;
                } else if (cCategory.equals(context.getResources().getString(R.string.catPants))) {

                    countPants++;
                } else if (cCategory.equals(context.getResources().getString(R.string.catUnderWear))) {

                    countUnderWear++;
                } else if (cCategory.equals(context.getResources().getString(R.string.catCoats))) {

                    counteCoats++;
                } else if (cCategory.equals(context.getResources().getString(R.string.catShoes))) {

                    countShoes++;
                } else if (cCategory.equals(context.getResources().getString(R.string.catJumper))) {

                    countJumper++;
                } else if (cCategory.equals(context.getResources().getString(R.string.catPijamas))) {

                    countPijamas++;
                } else if (cCategory.equals(context.getResources().getString(R.string.catDress))) {

                    countDress++;
                } else if (cCategory.equals(context.getResources().getString(R.string.catAccesories))) {

                    countAccessories++;
                }
                //AÑADIR LAS QUE QUEDARN!!!!!!!!!!!!!!!

            } while (c.moveToNext());
        }

        ItemCount[0] = totalCount;
        ItemCount[1] = countShirts;
        ItemCount[2] = countPants;
        ItemCount[3] = countUnderWear;
        ItemCount[4] = countShoes;
        ItemCount[5] = countJumper;
        ItemCount[6] = countPijamas;
        ItemCount[7] = countDress;
        ItemCount[8] = counteCoats;
        ItemCount[9] = countAccessories;
        //etc add all categories
        Log.e("total number of items", String.valueOf(totalCount));

        c.close();
        db.close();

        return ItemCount;
    }

}