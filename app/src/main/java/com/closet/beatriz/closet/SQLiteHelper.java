////////////VERSION TRABAJO


package com.closet.beatriz.closet;

/**
 * Created by Beatriz.
 */

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SQLiteHelper extends SQLiteOpenHelper {

    // Sentencia SQL para crear la tabla de Usuarios
    String sqlCreateItems = "CREATE TABLE Items (id INTEGER PRIMARY KEY AUTOINCREMENT, image BLOB, description VARCHAR(30), category VARCHAR(20),season VARCHAR(15), colors VARCHAR(150), i_size VARCHAR(6), s_date VARCHAR(20), price FLOAT, shop VARCHAR(10))";
    String sqlCreateOutfits = "CREATE TABLE Outfits (id INTEGER PRIMARY KEY AUTOINCREMENT,  name VARCHAR(30))";
    String sqlCreateItemOutfit = "CREATE TABLE ItemOutfit (id INTEGER NOT NULL, itemId INTEGER NOT NULL, PRIMARY KEY (id, itemId))";

    String sqlCreateColors = "CREATE TABLE Colors (color VARCHAR(30) PRIMARY KEY, code VARCHAR(10))";


    Context ctx = null;

    public SQLiteHelper(Context contexto, String nombre,
                        CursorFactory factory, int version) {
        super(contexto, nombre, factory, version);
        ctx = contexto;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Se ejecuta la sentencia SQL de creación de la tabla
        db.execSQL(sqlCreateItems);
        db.execSQL(sqlCreateColors);
        db.execSQL(sqlCreateOutfits);
        db.execSQL(sqlCreateItemOutfit);

        //Add defult colors

        String[] colorNames = ctx.getResources().getStringArray(R.array.colorArrays);
        String[] colorCodes = ctx.getResources().getStringArray(R.array.colors);

        //insert colors
        for (int i = 0; i < colorNames.length; i++) {

            MyColor color = new MyColor(colorNames[i], colorCodes[i]);
            Log.e("color inserted-----", "color: " + color.getColor());
            Log.e("color inserted-----", "code: " + color.getCode());


            addColor(color);
            //Log.e("TAG", "array length:  " + colorNames.length);
            // String sqlFillColrs = "Insert Into Colors (color,code) VALUES('" + colorNames[i] + "', '" + colorCodes[i] + "');";
            //db.execSQL(sqlFillColrs);
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnterior,
                          int versionNueva) {

        // Se elimina la versión anterior de la tabla
        db.execSQL("DROP TABLE IF EXISTS Items");

        // Se crea la nueva versión de la tabla
        db.execSQL(sqlCreateItems);
    }

    public void cargarLista(Context context, ArrayList<MyItem> ALShirts, ArrayList<MyItem> ALPants, ArrayList<MyItem> ALUnderWear, ArrayList<MyItem> ALCoats, ArrayList<MyItem> ALShoes, ArrayList<MyItem> ALJumper, ArrayList<MyItem> ALPijamas, ArrayList<MyItem> ALDress, ArrayList<MyItem> ALAccesories) {


        Log.e("TAG-----------", "en el helper cargarlista");

        MyItem i;

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


                i = new MyItem(cId, cFoto, cDescription, cCategory, cSeason, cColors, cDate, cSize,
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

    public ArrayList<MyItem> getShirts(Context context) {

        Log.e("TAG-----------", "en el helper cargarlista");
        ArrayList<MyItem> list = new ArrayList<MyItem>();

        MyItem i;

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

                i = new MyItem(cId, cFoto, cDescription, cCategory, cSeason, cColors, cDate, cSize,
                        cPrice, cShop);


                // cargamos la informavión en la lista
                if (cCategory.equals(context.getResources().getString(R.string.catShirts))) {

                    list.add(i);
                }

            } while (c.moveToNext());
        }
        c.close();
        db.close();

        return list;

    }

    public ArrayList<MyItem> getPants(Context context) {

        Log.e("TAG-----------", "en el helper cargarlista");
        ArrayList<MyItem> list = new ArrayList<MyItem>();

        MyItem i;

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

                i = new MyItem(cId, cFoto, cDescription, cCategory, cSeason, cColors, cDate, cSize,
                        cPrice, cShop);


                // cargamos la informavión en la lista
                if (cCategory.equals(context.getResources().getString(R.string.catPants))) {

                    list.add(i);
                }

            } while (c.moveToNext());
        }
        c.close();
        db.close();

        return list;

    }

    public ArrayList<MyItem> getUnderWear(Context context) {

        Log.e("TAG-----------", "en el helper cargarlista");
        ArrayList<MyItem> list = new ArrayList<MyItem>();
        MyItem i;

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

                i = new MyItem(cId, cFoto, cDescription, cCategory, cSeason, cColors, cDate, cSize,
                        cPrice, cShop);


                // cargamos la informavión en la lista
                if (cCategory.equals(context.getResources().getString(R.string.catUnderWear))) {

                    list.add(i);
                }

            } while (c.moveToNext());
        }
        c.close();
        db.close();

        return list;

    }

    public ArrayList<MyItem> getCoats(Context context) {

        Log.e("TAG-----------", "en el helper cargarlista");
        ArrayList<MyItem> list = new ArrayList<MyItem>();

        MyItem i;

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

                i = new MyItem(cId, cFoto, cDescription, cCategory, cSeason, cColors, cDate, cSize,
                        cPrice, cShop);


                // cargamos la informavión en la lista
                if (cCategory.equals(context.getResources().getString(R.string.catCoats))) {

                    list.add(i);
                }

            } while (c.moveToNext());
        }
        c.close();
        db.close();

        return list;

    }

    public ArrayList<MyItem> getShoes(Context context) {

        ArrayList<MyItem> list = new ArrayList<MyItem>();

        Log.e("TAG-----------", "en el helper cargarlista");

        MyItem i;

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

                i = new MyItem(cId, cFoto, cDescription, cCategory, cSeason, cColors, cDate, cSize,
                        cPrice, cShop);


                // cargamos la informavión en la lista
                if (cCategory.equals(context.getResources().getString(R.string.catShoes))) {

                    list.add(i);
                }

            } while (c.moveToNext());
        }
        c.close();
        db.close();

        return list;

    }

    public ArrayList<MyItem> getJumper(Context context) {

        Log.e("TAG-----------", "en el helper cargarlista");
        ArrayList<MyItem> list = new ArrayList<MyItem>();

        MyItem i;

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

                i = new MyItem(cId, cFoto, cDescription, cCategory, cSeason, cColors, cDate, cSize,
                        cPrice, cShop);


                // cargamos la informavión en la lista
                if (cCategory.equals(context.getResources().getString(R.string.catJumper))) {

                    list.add(i);
                }

            } while (c.moveToNext());
        }
        c.close();
        db.close();

        return list;

    }

    public ArrayList<MyItem> getPijamas(Context context) {

        Log.e("TAG-----------", "en el helper cargarlista");
        ArrayList<MyItem> list = new ArrayList<MyItem>();

        MyItem i;

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

                i = new MyItem(cId, cFoto, cDescription, cCategory, cSeason, cColors, cDate, cSize,
                        cPrice, cShop);


                // cargamos la informavión en la lista
                if (cCategory.equals(context.getResources().getString(R.string.catPijamas))) {

                    list.add(i);
                }

            } while (c.moveToNext());
        }
        c.close();
        db.close();

        return list;

    }

    public ArrayList<MyItem> getDress(Context context) {

        Log.e("TAG-----------", "en el helper cargarlista");
        ArrayList<MyItem> list = new ArrayList<MyItem>();

        MyItem i;

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

                i = new MyItem(cId, cFoto, cDescription, cCategory, cSeason, cColors, cDate, cSize,
                        cPrice, cShop);


                // cargamos la informavión en la lista
                if (cCategory.equals(context.getResources().getString(R.string.catDress))) {

                    list.add(i);
                }

            } while (c.moveToNext());
        }
        c.close();
        db.close();

        return list;

    }

    public ArrayList<MyItem> getAcessories(Context context) {

        Log.e("TAG-----------", "en el helper cargarlista");
        ArrayList<MyItem> list = new ArrayList<MyItem>();

        MyItem i;

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

                i = new MyItem(cId, cFoto, cDescription, cCategory, cSeason, cColors, cDate, cSize,
                        cPrice, cShop);


                // cargamos la informavión en la lista
                if (cCategory.equals(context.getResources().getString(R.string.catAccesories))) {

                    list.add(i);
                }

            } while (c.moveToNext());
        }
        c.close();
        db.close();

        return list;

    }

    public void guardarItem(MyItem i) {

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
        // Log.e("insert item", sqlInertItem);
        db.execSQL(sqlInertItem);

    }

    //select all where id es id// cambiar sentencia sql
    public MyItem getItem(int ID) {


        Log.e("TAG-----------", "en el helper cargarlista");

        MyItem i = null;

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

                if (cId == ID) {
                    i = new MyItem(cId, cFoto, cDescription, cCategory, cSeason, cColors, cDate, cSize, cPrice, cShop);

                }


            } while (c.moveToNext());
        }
        c.close();
        db.close();

        return i;
    }

    public void addColor(MyColor color) {

        // Log.e("-----guardar item helper", i.getImage());
        SQLiteDatabase db = this.getReadableDatabase();

        String sqlAddColor = "Insert into Colors (color, code) VALUES('" + color.getColor() + "', '" + color.getCode() + "')";
        db.execSQL(sqlAddColor);

        Log.e("TAG -------- color", "name: " + color.getColor() + "   code:" + color.getCode());

    }

    ////not changed
    public int updateItem(MyItem item) {

        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        //values.put("id", queryValues.get("id"));
        Log.e("ID--MODIFICAT", String.valueOf(item.getId()));
        //values.put("id", i.getId());
        values.put("image", item.getImage());
        values.put("description", item.getDescription());
        values.put("category", item.getCategory());
        values.put("season", item.getSeason());
        values.put("colors", item.getColours());
        values.put("i_size", item.getSize());
        values.put("s_date", item.getS_date());
        values.put("price", item.getPrize());
        values.put("shop", item.getShop());


        return database.update("Items", values, "id" + " = ?",
                new String[]{String.valueOf(item.getId())});
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

    //******* STATISTICS *******//

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

                        countArray[i]++;
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

                        countArray[i]++;
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

    //not working
    public ArrayList<MyColor> getColors2() {
        Log.e("TAG--------", "en el helper en getColors()");

        ArrayList<MyColor> colors = new ArrayList<MyColor>();

        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM Colors";
        Cursor c = db.rawQuery(selectQuery, null);


        // int colorIdx = c.getColumnIndex("color");
        // int codeIdx = c.getColumnIndex("color");


        //Log.e("TAG", "id" + idIdx);

        if (c.moveToFirst()) {
            do {

                // // cargamos la información en el objeto
                String cColor = c.getString(0);
                String cCode = c.getString(1);

                MyColor color = new MyColor(cColor, cCode);

                Log.e("TAG2-------", "color: " + color.getColor() + "   code:   " + color.getCode());

                colors.add(color);

            } while (c.moveToNext());
        }

        c.close();
        db.close();

        return colors;
    }

    //******* OUTFITS *******//

    public void saveOutfit(MyOutfit myOutfit) {

        SQLiteDatabase db = this.getReadableDatabase();

        //************* OUTFIT ID AND NAME ***************//
        String sqlInert = "INSERT INTO Outfits (name) VALUES ('" + myOutfit.getName() + "');";
        // Log.e("insert item", sqlInertItem);
        db.execSQL(sqlInert);

        //************* OUTFIT ID AND ITEMS ***************//
        ArrayList<MyItem> lista = myOutfit.getItemList();

        int outfitID = getLastID();

        for (int i = 0; i < lista.size(); i++) {

            String sqlInertItems = "INSERT INTO ItemOutfit (id, itemId) VALUES (" + outfitID + ", " + lista.get(i).getId() + ");";

            db.execSQL(sqlInertItems);

        }

    }

    public int getLastID() {

        SQLiteDatabase db = this.getReadableDatabase();

        final String MY_QUERY = "SELECT last_insert_rowid();";
        Cursor cur = db.rawQuery(MY_QUERY, null);
        cur.moveToFirst();
        int ID = cur.getInt(0);
        cur.close();
        Log.e("TAG--------------", "last id introduced: " + ID);
        return ID;
    }

    public ArrayList<MyOutfit> getOutfits() {


        ArrayList<MyOutfit> myOutfitList = new ArrayList<MyOutfit>();

        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM Outfits";
        Cursor c = db.rawQuery(selectQuery, null);

        int idIdx = c.getColumnIndex("id");
        int nameIdx = c.getColumnIndex("name");

        //Log.e("TAG", "id" + idIdx);

        if (c.moveToFirst()) {
            do {
                // Outfit outfit = new Outfit();

                // // cargamos la información en el objeto
                int cId = c.getInt(idIdx);
                String cName = c.getString(nameIdx);
                //  Log.e("OUTFIT----------- id",String.valueOf(cId));
                // Log.e("OUTFIT----------- name",cName);

                // outfit.setId(cId);
                // outfit.setName(cName);
                // outfit.setItemList(getOutfitItems(cId));

                MyOutfit myOutfit = new MyOutfit(cId, cName, getOutfitItems(cId));

                myOutfitList.add(myOutfit);

            } while (c.moveToNext());

        }

        c.close();
        db.close();

        return myOutfitList;

    }

    public ArrayList<MyItem> getOutfitItems(int id) {

        ArrayList<MyItem> itemList = new ArrayList<MyItem>();

        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM ItemOutfit WHERE id= " + id + ";";
        Cursor c = db.rawQuery(selectQuery, null);

        int idIdx = c.getColumnIndex("id");
        int itemIdIdx = c.getColumnIndex("itemId");

        //Log.e("TAG", "id" + idIdx);

        if (c.moveToFirst()) {
            do {

                // cargamos la información en el objeto
                int cId = c.getInt(idIdx);
                int cItemId = c.getInt(itemIdIdx);

                itemList.add(getItem(cItemId));

            } while (c.moveToNext());

        }

        c.close();
        db.close();

        return itemList;
    }

}