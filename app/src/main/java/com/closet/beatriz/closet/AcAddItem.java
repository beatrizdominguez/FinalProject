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
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;


public class AcAddItem extends Activity {

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

        //base de datos
        usdbh = new ItemSQLiteHelper(this, "Closet",
                null, 1);

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

        Toast.makeText(this, "save", Toast.LENGTH_SHORT).show();
        Log.e("TAG------------ color count", String.valueOf(colorCount));
        for (int i = 0; i < colorArray.length; i++) {
          //  Log.e("TAG----------color-", colorArray[i]);
        }


        //controls
        EditText etxtDesc = (EditText) findViewById(R.id.etxtDesc);
        Spinner spnCat = (Spinner) findViewById(R.id.spnCat);
//colores almacenarlos en un array
        Spinner spnSize = (Spinner) findViewById(R.id.spnSize);
        EditText etxtPrice = (EditText) findViewById(R.id.etxtPrice);
        DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker);
        Spinner spnShop = (Spinner) findViewById(R.id.spnShop);


        int id;
        Bitmap image;
        //String image;
        String description;
        String category;
        Date s_date;
        //String s_date;
        String size;
        float prize;
        String shop;

        Log.e("TAG ------------", "image1");
        // btnImg.buildDrawingCache();
        // image =  btnImg.getDrawingCache();

        // BitmapDrawable drawable = (BitmapDrawable) btnImg.getDrawable();
        //Bitmap bitmap = drawable.getBitmap();


//        Log.e("TAG ------------",image.toString());
        description = etxtDesc.getText().toString();
        category = spnCat.getSelectedItem().toString();
        s_date = getDateFromDatePicker(datePicker);
        size = spnSize.getSelectedItem().toString();
        prize = Float.valueOf(etxtPrice.getText().toString());
        shop = spnShop.getSelectedItem().toString();
        Log.e("TAG----------", s_date.toString());
//        Log.e("TAG----------BTM byte count", String.valueOf(imageBitmap.getByteCount()));
        // Item i = new Item(imageBitmap, description, category, s_date.toString(), size, prize, shop);
        Item i = new Item( description, colorArray, category, s_date.toString(), size, prize, shop);

        Log.e("TAG--------", "item created");
        Log.e("TAG--------", i.getDescription());

        Intent intentSave = new Intent();
        Bundle mBundle = new Bundle();
        mBundle.putSerializable("item", (Serializable) i);
        intentSave.putExtras(mBundle);
        setResult(RESULT_OK, intentSave);

        Log.e("TAG--------", "intent con info");


        //add to data base

        // usdbh.guardarItem(i);


        // cerramos la actividad
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