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
    String sqlCreateItems = "CREATE TABLE Items (id INTEGER PRIMARY KEY AUTOINCREMENT, image BLOB, description VARCHAR(30), category VARCHAR(20),season VARCHAR(15), colors VARCHAR(150), i_size VARCHAR(6), s_date VARCHAR(20), price FLOAT, shop VARCHAR(10))";

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

    public void cargarLista(Context context, ArrayList<Item> ALShirts, ArrayList<Item> ALPants, ArrayList<Item> ALUnderWear, ArrayList<Item> ALCoats, ArrayList<Item> ALShoes, ArrayList<Item> ALJumper, ArrayList<Item> ALPijamas, ArrayList<Item> ALDress, ArrayList<Item> ALAccesories) {


        Log.e("TAG-----------", "en el helper cargarlista");

        Item i;

        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM Items";
        Cursor c = db.rawQuery(selectQuery, null);


        int idIdx = c.getColumnIndex("id");
        int fotoIdx = c.getColumnIndex("image");
        int descriptionIdx = c.getColumnIndex("description");
        int categoryIdx = c.getColumnIndex("category");
        int seasonIdx = c.getColumnIndex("season");
        int colorsIdx = c.getColumnIndex("colors");
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
                String cSeason = c.getString(seasonIdx);
                String cColors = c.getString(colorsIdx);
                String cSize = c.getString(sizeIdx);
                String cDate = c.getString(dateIdx);
                Float cPrice = c.getFloat(priceIdx);
                String cShop = c.getString(shopIdx);
                //Log.e("foto-helper-cargar", cFoto);


                i = new Item(cId, cFoto, cDescription, cCategory, cSeason, cColors, cDate, cSize,
                        cPrice, cShop);


                // cargamos la informavión en la lista
                if (cCategory.equals(context.getResources().getString(R.string.catShirts))) {

                    ALShirts.add(i);
                } else if (cCategory.equals(context.getResources().getString(R.string.catPants))) {

                    ALPants.add(i);

                } else if (cCategory.equals(context.getResources().getString(R.string.catUnderWear))) {

                    ALUnderWear.add(i);

                } else if (cCategory.equals(context.getResources().getString(R.string.catCoats))) {
                    ALCoats.add(i);

                } else if (cCategory.equals(context.getResources().getString(R.string.catShoes))) {
                    ALShoes.add(i);


                } else if (cCategory.equals(context.getResources().getString(R.string.catJumper))) {
                    ALJumper.add(i);


                } else if (cCategory.equals(context.getResources().getString(R.string.catPijamas))) {

                    ALPijamas.add(i);
                } else if (cCategory.equals(context.getResources().getString(R.string.catDress))) {

                    ALDress.add(i);
                } else if (cCategory.equals(context.getResources().getString(R.string.catAccesories))) {

                    ALAccesories.add(i);
                }


            } while (c.moveToNext());
        }
        c.close();
        db.close();

    }

    public void guardarItem(Item i) {

        // Log.e("-----guardar item helper", i.getImage());
        SQLiteDatabase db = this.getReadableDatabase();

        String sqlInertItem = "INSERT INTO Items (image, description, category, season, colors, i_size, s_date, price, shop ) VALUES ('" +
                i.getImage() + "', '" +
                i.getDescription() + "', '" +
                i.getCategory() + "', '" +
                i.getSeason() + "', '" +
                i.getColours() + "', '" +
                i.getSize() + "', '" +
                i.getS_date() + "', " +
                i.getPrize() + ", '" +
                i.getShop() + "');";
        Log.e("insert item", sqlInertItem);
        db.execSQL(sqlInertItem);

    }


    ////not changed
    public int updateItem(Item i) {

        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        //values.put("id", queryValues.get("id"));
        Log.e("ID--MODIFICAT", String.valueOf(i.getId()));
        //values.put("id", i.getId());
        values.put("image", i.getImage());
        values.put("description", i.getDescription());
        values.put("category", i.getCategory());
        values.put("season", i.getSeason());
        values.put("colors", i.getColours());
        values.put("i_size", i.getSize());
        values.put("s_date", i.getS_date());
        values.put("price", i.getPrize());
        values.put("shop", i.getShop());


        return database.update("Items", values, "id" + " = ?",
                new String[]{String.valueOf(i.getId())});
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

        String[] categoryName = context.getResources().getStringArray(R.array.categoriesArrays);
        Float[] pricesArray = new Float[categoryName.length];

        //inicializar todos a 0
        for (int i = 0; i < pricesArray.length; i++) {
            pricesArray[i] = Float.valueOf(0);
        }

        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM Items";
        Cursor c = db.rawQuery(selectQuery, null);


        int idIdx = c.getColumnIndex("id");
        int categoryIdx = c.getColumnIndex("category");
        int priceIdx = c.getColumnIndex("price");

        if (c.moveToFirst()) {
            do {

                // // cargamos la información en el objeto
                int cId = c.getInt(idIdx);

                String cCategory = c.getString(categoryIdx);
                Float cPrice = c.getFloat(priceIdx);

                //lo sumamos a la categoría corresponeidnte
                for (int i = 0; i < categoryName.length; i++) {

                    if (categoryName[i].equals(cCategory)) {

                        pricesArray[i] += cPrice;
                        break;
                    }

                }

            } while (c.moveToNext());
        }


        c.close();
        db.close();

        return pricesArray;
    }



    public int[] countCategoryStatistics(Context context) {

        String[] categoryName = context.getResources().getStringArray(R.array.categoriesArrays);
        int[] countArray = new int[categoryName.length];

        //inicializar todos a 0
        for (int i = 0; i < countArray.length; i++) {
            countArray[i] = 0;
        }

        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM Items";
        Cursor c = db.rawQuery(selectQuery, null);


        int idIdx = c.getColumnIndex("id");
        int categoryIdx = c.getColumnIndex("category");
       // int priceIdx = c.getColumnIndex("price");

        if (c.moveToFirst()) {
            do {

                // // cargamos la información en el objeto
                int cId = c.getInt(idIdx);

                String cCategory = c.getString(categoryIdx);
               // Float cPrice = c.getFloat(priceIdx);

                //lo sumamos a la categoría corresponeidnte
                for (int i = 0; i < categoryName.length; i++) {

                    if (categoryName[i].equals(cCategory)) {

                        countArray[i] ++;
                        break;
                    }

                }

            } while (c.moveToNext());
        }


        c.close();
        db.close();

        return countArray;
    }


    public int[] countSeasonsStatistics(Context context) {

        String[] seasonName = context.getResources().getStringArray(R.array.seasonsArrays);
        int[] countArray = new int[seasonName.length];

        //inicializar todos a 0
        for (int i = 0; i < countArray.length; i++) {
            countArray[i] = 0;
        }

        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM Items";
        Cursor c = db.rawQuery(selectQuery, null);


        int idIdx = c.getColumnIndex("id");
        int seeasonIdx = c.getColumnIndex("season");
        // int priceIdx = c.getColumnIndex("price");

        if (c.moveToFirst()) {
            do {

                // // cargamos la información en el objeto
                int cId = c.getInt(idIdx);

                String cSeason = c.getString(seeasonIdx);
                // Float cPrice = c.getFloat(priceIdx);

                //lo sumamos a la categoría corresponeidnte
                for (int i = 0; i < seasonName.length; i++) {

                    if (seasonName[i].equals(cSeason)) {

                        countArray[i] ++;
                        break;
                    }

                }

            } while (c.moveToNext());
        }


        c.close();
        db.close();

        return countArray;
    }

    public int[] colorStatistics(Context context) {


        String[] colors = context.getResources().getStringArray(R.array.colorArrays);
        int colorNumber = colors.length;
        // Log.e("TAG", "how many colors are in the array:  " + colorNumber);

        //string of num of colors + 1 for the total
        int[] ItemCount = new int[colorNumber + 1];


        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM Items";
        Cursor c = db.rawQuery(selectQuery, null);


        int idIdx = c.getColumnIndex("id");
        int colorsIdx = c.getColumnIndex("colors");

        //Log.e("TAG", "id" + idIdx);

        if (c.moveToFirst()) {
            do {

                // // cargamos la información
                int cId = c.getInt(idIdx);
                String cColors = c.getString(colorsIdx);

                for (int i = 0; i < colorNumber; i++) {
                    if (cColors.contains(colors[i])) {

                        ItemCount[i]++;
                    }
                }


            } while (c.moveToNext());
        }


        c.close();
        db.close();

        return ItemCount;
    }

    public int[] shopStatistics(Context context) {

        Log.e("TAG", "dentro de shopStatistics");

        String[] shops = context.getResources().getStringArray(R.array.shopsArrays);
        int shopNumber = shops.length;
        // Log.e("TAG", "how many colors are in the array:  " + colorNumber);

        //string of num of colors + 1 for the total
        int[] shopCount = new int[shopNumber];


        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM Items";
        Cursor c = db.rawQuery(selectQuery, null);


        int idIdx = c.getColumnIndex("id");
        int shopIdx = c.getColumnIndex("shop");

        //Log.e("TAG", "id" + idIdx);

        if (c.moveToFirst()) {
            do {

                // // cargamos la información en el objeto
                int cId = c.getInt(idIdx);
                String cShop = c.getString(shopIdx);


                // totalCount++;

                for (int i = 0; i < shopNumber; i++) {
                    if (cShop.equals(shops[i])) {

                        shopCount[i]++;
                        Log.e("TAG", "----prenda de esa tienda------" + cShop);
                    }
                }


            } while (c.moveToNext());
        }


        c.close();
        db.close();

        return shopCount;
    }

}