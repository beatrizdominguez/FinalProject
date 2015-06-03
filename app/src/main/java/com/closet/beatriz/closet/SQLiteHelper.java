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
import android.widget.Toast;

public class SQLiteHelper extends SQLiteOpenHelper {

    //*********** ITEMS *************//
    String sqlCreateItems = "CREATE TABLE Items (id INTEGER PRIMARY KEY AUTOINCREMENT, image BLOB, description VARCHAR(30), category VARCHAR(20),season VARCHAR(15), colors VARCHAR(150), i_size VARCHAR(6), s_date VARCHAR(20), price FLOAT, shop VARCHAR(10))";
    String sqlCreateColors = "CREATE TABLE Colors (color VARCHAR(30) PRIMARY KEY, code VARCHAR(10))";

    //*********** OUTFITS *************//
    String sqlCreateOutfits = "CREATE TABLE Outfits (id INTEGER PRIMARY KEY AUTOINCREMENT,  name VARCHAR(30))";
    String sqlCreateItemOutfit = "CREATE TABLE ItemOutfit (id INTEGER NOT NULL, itemId INTEGER NOT NULL, PRIMARY KEY (id, itemId))";

    //*********** CALENDAR *************//
    String sqlCreateCalendar = "CREATE TABLE NoteCalendar (date VARCHAR(10) NOT NULL, notes VARCHAR(50) NOT NULL, PRIMARY KEY (date, notes))";
    String sqlCreateItemCalendar = "CREATE TABLE ItemCalendar (date VARCHAR(10) NOT NULL, itemId INTEGER NOT NULL, PRIMARY KEY (date, itemId))";


    Context ctx = null;

    public SQLiteHelper(Context contexto, String nombre,
                        CursorFactory factory, int version) {
        super(contexto, nombre, factory, version);
        ctx = contexto;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //*********** ITEMS *************//
        db.execSQL(sqlCreateItems);
        db.execSQL(sqlCreateColors);
        //*********** OUTFITS *************//
        db.execSQL(sqlCreateOutfits);
        db.execSQL(sqlCreateItemOutfit);
        //*********** CALENDAR *************//
        db.execSQL(sqlCreateCalendar);
        db.execSQL(sqlCreateItemCalendar);


        //Add defult colors
        String[] colorNames = ctx.getResources().getStringArray(R.array.colorArrays);
        String[] colorCodes = ctx.getResources().getStringArray(R.array.colors);

        //insert colors
        for (int i = 0; i < colorNames.length; i++) {


            ContentValues cv = new ContentValues();
            cv.put("color", colorNames[i]);
            cv.put("code", colorCodes[i]);
            db.insert("Colors", null, cv);


            // MyColor color = new MyColor(colorNames[i], colorCodes[i]);


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

    public ArrayList<MyItem> getShirts(Context context) {

        Log.e("TAG-----------", "en el helper cargarlista");
        ArrayList<MyItem> list = new ArrayList<MyItem>();

        MyItem item;

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

                item = new MyItem(cId, cFoto, cDescription, cCategory, cSeason, cColors, cDate, cSize,
                        cPrice, cShop);


                // cargamos la informavión en la lista
                if (cCategory.equals(context.getResources().getString(R.string.catShirts))) {

                    list.add(item);
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

    public void saveItem(MyItem item) {

        // Log.e("-----guardar item helper", i.getImage());
        SQLiteDatabase db = this.getReadableDatabase();

        String sqlInertItem = "INSERT INTO Items (image, description, category, season, colors, i_size, s_date, price, shop ) VALUES ('" +
                item.getImage() + "', '" +
                item.getDescription() + "', '" +
                item.getCategory() + "', '" +
                item.getSeason() + "', '" +
                item.getColours() + "', '" +
                item.getSize() + "', '" +
                item.getS_date() + "', " +
                item.getPrize() + ", '" +
                item.getShop() + "');";
        // Log.e("insert item", sqlInertItem);
        db.execSQL(sqlInertItem);

    }

    //select all where id es id// cambiar sentencia sql
    public MyItem getItem(int ID) {


        //Log.e("TAG-----------", "en el helper cargarlista");

        MyItem item = null;

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
                    item = new MyItem(cId, cFoto, cDescription, cCategory, cSeason, cColors, cDate, cSize, cPrice, cShop);

                }


            } while (c.moveToNext());
        }
        c.close();
        db.close();

        return item;
    }

    public void addColor(Context context, MyColor color) {

        // Log.e("-----guardar item helper", i.getImage());
        SQLiteDatabase db = this.getWritableDatabase();

        String sqlAddColor = "Insert into Colors (color, code) VALUES('" + color.getColor() + "', '" + color.getCode() + "')";
        try {
            db.execSQL(sqlAddColor);

        } catch (Exception e) {
            Toast.makeText(context, R.string.msg_duplicate_error, Toast.LENGTH_SHORT).show();
        }

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

    public ArrayList<MyColor> getColors() {
        Log.e("TAG--------", "en el helper en getColors()");

        ArrayList<MyColor> colorList = new ArrayList<MyColor>();

        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM Colors";
        Cursor c = db.rawQuery(selectQuery, null);


        if (c.moveToFirst()) {
            do {

                // // cargamos la información en el objeto
                String cColor = c.getString(0);
                String cCode = c.getString(1);

                MyColor color = new MyColor(cColor, cCode);

                Log.e("TAG2-------", "color: " + color.getColor() + "   code:   " + color.getCode());

                colorList.add(color);

            } while (c.moveToNext());
        }

        c.close();
        db.close();

        return colorList;
    }

    //******* OUTFITS *******//

    public void saveOutfit(Context context, MyOutfit myOutfit) {

        SQLiteDatabase db = this.getWritableDatabase();

        //************* OUTFIT ID AND NAME ***************//
        String sqlInert = "INSERT INTO Outfits (name) VALUES ('" + myOutfit.getName() + "');";
        // Log.e("insert item", sqlInertItem);
        // db.execSQL(sqlInert);
        try {
            db.execSQL(sqlInert);


            //************* OUTFIT ID AND ITEMS ***************//
            ArrayList<MyItem> lista = myOutfit.getItemList();

            int outfitID = getLastID();

            for (int i = 0; i < lista.size(); i++) {

                String sqlInertItems = "INSERT INTO ItemOutfit (id, itemId) VALUES (" + outfitID + ", " + lista.get(i).getId() + ");";

                try {
                    db.execSQL(sqlInertItems);
                } catch (Exception e) {
                    Toast.makeText(context, R.string.msg_duplicate_error, Toast.LENGTH_SHORT).show();
                }

            }

        } catch (Exception e) {
            Toast.makeText(context, R.string.msg_duplicate_error, Toast.LENGTH_SHORT).show();
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

    //******* CALENDAR *******//

    public void addCalendarNote(Context context, String date, String note) {

        SQLiteDatabase db = this.getWritableDatabase();

        String sqlInertNote = "INSERT INTO NoteCalendar (date, notes) VALUES ('" + date + "', '" + note + "');";

        try {
            db.execSQL(sqlInertNote);
        } catch (Exception e) {
            Toast.makeText(context, R.string.msg_duplicate_error, Toast.LENGTH_SHORT).show();
        }

    }

    public void addCalendarItem(Context context, String date, MyItem item) {

        Log.e("TAG--------", "en el helper en addCalendarItem()");

        SQLiteDatabase db = this.getWritableDatabase();

        String sqlInertItem = "INSERT INTO ItemCalendar (date, itemId) VALUES ('" + date + "', " + item.getId() + ");";
        Log.e("TAG--------", sqlInertItem);
        try {
            db.execSQL(sqlInertItem);
        } catch (Exception e) {
            Toast.makeText(context, R.string.msg_duplicate_error, Toast.LENGTH_SHORT).show();
        }


    }

    public ArrayList<String> getCalendarNotes(String date) {
        Log.e("TAG--------", "en el helper en getCalendarNotes()");

        ArrayList<String> noteList = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM NoteCalendar WHERE date ='" + date + "'";
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            do {

                // // cargamos la información en el objeto
                String cDate = c.getString(0);
                String cNote = c.getString(1);

                Log.e("TAG-----------", "date: " + cDate);
                Log.e("TAG-----------", "note: " + cNote);
                noteList.add(cNote);

            } while (c.moveToNext());
        }

        c.close();
        db.close();

        return noteList;


    }

    public ArrayList<MyItem> getCalendarItems(String date) {
        Log.e("TAG--------", "en el helper en getCalendarItems()");

        ArrayList<MyItem> itemList = new ArrayList<MyItem>();

        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM ItemCalendar WHERE date ='" + date + "'";
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            do {
                Log.e("TAG--------", "recorriendo los resultados items:  ");

                // // cargamos la información en el objeto
                String cDate = c.getString(0);
                int cItemId = c.getInt(1);
                Log.e("TAG--------", "date: " + cDate);
                Log.e("TAG--------", "item id: " + cItemId);

                itemList.add(getItem(cItemId));


            } while (c.moveToNext());
        }

        c.close();
        db.close();

        return itemList;

    }

    public void removeCalendarItems(String date) {

        SQLiteDatabase db = this.getWritableDatabase();

        String sqlDelete = "DELETE FROM ItemCalendar WHERE date= ('" + date + "');";
        Log.e("TAG--------", sqlDelete);
        db.execSQL(sqlDelete);

    }

    public void removeCalendarNotes(String date) {
        SQLiteDatabase db = this.getWritableDatabase();

        String sqlDelete = "DELETE FROM NoteCalendar WHERE date= ('" + date + "');";
        Log.e("TAG--------", sqlDelete);
        db.execSQL(sqlDelete);

    }
}