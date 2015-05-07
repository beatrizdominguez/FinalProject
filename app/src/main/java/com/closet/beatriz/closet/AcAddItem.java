package com.closet.beatriz.closet;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;


public class AcAddItem extends Activity {


    //controls
    EditText etxtDesc;
    Spinner spnCat;
    Spinner spnSeason;
    //colores almacenarlos en un array
    EditText etxtSize;
    EditText etxtPrice;
    DatePicker datePicker;
    Spinner spnShop;

    int spinnerIndex;


    ImageButton btnImg;
    Button btnAddColor;
    Button btnSave;
    public final static int REQUEST_CAMERA = 1;
    public final static int SELECT_FILE = 2;
    Bitmap imageBitmap = null;
    String[] colorArray = new String[20];
    int colorCount = 0;


    ItemSQLiteHelper usdbh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_add_item);


        //controls
        etxtDesc = (EditText) findViewById(R.id.etxtDesc);
        spnCat = (Spinner) findViewById(R.id.spnCat);
        spnSeason = (Spinner) findViewById(R.id.spnSeason);
        //colores almacenarlos en un array
        etxtSize = (EditText) findViewById(R.id.etxtSize);
        etxtPrice = (EditText) findViewById(R.id.etxtPrice);
        datePicker = (DatePicker) findViewById(R.id.datePicker);
        spnShop = (Spinner) findViewById(R.id.spnShop);

        //base de datos
        usdbh = new ItemSQLiteHelper(this, "Closet",
                null, 1);


        // vemos si recibe informaciÃ³n a travÃ©s de un intent (modificar)
        Bundle extras = getIntent().getExtras();
        String cat = extras.getString("Category");
        int categoryIndex = 0;
        categoryIndex = getCategoryIndex(cat);

        spnCat.setSelection(categoryIndex);

        btnImg = (ImageButton) findViewById(R.id.imageButton);
        btnImg.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                selectImage();

            }
        });

        btnAddColor = (Button) findViewById(R.id.btnAddColor);
        btnAddColor.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                addColour();

            }
        });


        btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                save();


            }
        });
    }

    private int getCategoryIndex(String cat) {

        int index = 0;

        // cargamos la informavión en la lista
        if (cat.equals(getResources().getString(R.string.catShirts))) {

            index = 0;

        } else if (cat.equals(getResources().getString(R.string.catPants))) {

            index = 1;
        } else if (cat.equals(getResources().getString(R.string.catUnderWear))) {

            index = 2;
        } else if (cat.equals(getResources().getString(R.string.catCoats))) {

            index = 3;
        } else if (cat.equals(getResources().getString(R.string.catShoes))) {

            index = 4;
        } else if (cat.equals(getResources().getString(R.string.catJumper))) {

            index = 5;
        } else if (cat.equals(getResources().getString(R.string.catPijamas))) {

            index = 6;
        } else if (cat.equals(getResources().getString(R.string.catDress))) {

            index = 7;
        } else if (cat.equals(getResources().getString(R.string.catAccesories))) {

            index = 8;
        }


        return index;
    }

    private void addColour() {

        Toast.makeText(this, "add colour", Toast.LENGTH_SHORT).show();

        String color;
        TextView txtColors = (TextView) findViewById(R.id.txtColors);
        Spinner spnColor = (Spinner) findViewById(R.id.spnCol);

        color = spnColor.getSelectedItem().toString();

        txtColors.setText(txtColors.getText() + ", " + color);

        colorArray[colorCount] = color;
        colorCount++;

    }

    private void save() {


        Log.e("TAG------------ color count", String.valueOf(colorCount));
        for (int i = 0; i < colorArray.length; i++) {
            //  Log.e("TAG----------color-", colorArray[i]);
        }


        int id;
        Bitmap image;
        //String image;
        String description;
        String category;
        String colour;
        String season;
        Date s_date;
        //String s_date;
        String size;
        float prize;
        String shop;

        Log.e("TAG ------------", "image1");
        // btnImg.buildDrawingCache();
        // image =  btnImg.getDrawingCache();

        //get bitmap from ImageButton
        BitmapDrawable drawable = (BitmapDrawable) btnImg.getDrawable();
        Bitmap bitmap = drawable.getBitmap();
        Log.e("bitmap ----size--", String.valueOf(bitmap.getByteCount()));

        //convert bitmap to string
        String str = BitmapToString(bitmap);

        description = etxtDesc.getText().toString();
        category = spnCat.getSelectedItem().toString();
        Log.e("TAG--season", "before season------------");
        Log.e("TAG--season", spnSeason.getSelectedItem().toString());
        season = spnSeason.getSelectedItem().toString();
        s_date = getDateFromDatePicker(datePicker);
        size = etxtSize.getText().toString();
        prize = Float.valueOf(etxtPrice.getText().toString());
        shop = spnShop.getSelectedItem().toString();

        //Log.e("TAG----------", s_date.toString());
        // Log.e("TAG----------BTM byte count", String.valueOf(imageBitmap.getByteCount()));
        Item i = new Item(str, description, category, season, s_date.toString(), size, prize, shop);
        // Item i = new Item(description, colorArray, category, s_date.toString(), size, prize, shop);


        //save on data base
        usdbh.guardarItem(i);
        //create intent
        Intent intentSave = new Intent();
        Bundle mBundle = new Bundle();
        mBundle.putSerializable("item", (Serializable) i);
        intentSave.putExtras(mBundle);
        setResult(RESULT_OK, intentSave);

        // close the activity
        finish();


    }

    public static java.util.Date getDateFromDatePicker(DatePicker datePicker) {
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year = datePicker.getYear();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        return calendar.getTime();
    }

    private void selectImage() {
        final CharSequence[] items = {"Take Photo", "Choose from Library",
                "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Take Photo")) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, REQUEST_CAMERA);
                } else if (items[item].equals("Choose from Library")) {
                    Intent intent = new Intent(
                            Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    startActivityForResult(
                            Intent.createChooser(intent, "Select File"),
                            SELECT_FILE);
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Bundle extras = null;
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CAMERA) {

                extras = data.getExtras();
                imageBitmap = (Bitmap) extras.get("data");

                if (imageBitmap != null) {
                    btnImg.setImageBitmap(imageBitmap);

                }

            } else if (requestCode == SELECT_FILE) {

                Uri selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};

                Cursor cursor = getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String picturePath = cursor.getString(columnIndex);
                cursor.close();

                imageBitmap = BitmapFactory.decodeFile(picturePath);
                btnImg.setImageBitmap(imageBitmap);


            }
        }
    }

    private String BitmapToString(Bitmap imageBitmap) {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();

        String encoded = Base64.encodeToString(byteArray, Base64.DEFAULT);
        return encoded;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case (R.id.action_close):

                this.finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}
