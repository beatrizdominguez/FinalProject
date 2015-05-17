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


public class AcModifyItem extends Activity {


    //controls
    EditText etxtDesc;
    Spinner spnCat;
    Spinner spnSeason;
    TextView txtColors;
    EditText etxtSize;
    EditText etxtPrice;
    DatePicker datePicker;
    Spinner spnShop;
    int spinnerIndex;

    ImageButton btnImg;


    Button btnAddColor;
    Button btnRmvColor;
    Button btnSave;
    public final static int REQUEST_CAMERA = 1;
    public final static int SELECT_FILE = 2;
    Bitmap imageBitmap = null;
    String[] colorArray = new String[20];
    int colorCount = 0;

    Item i;


    ItemSQLiteHelper usdbh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_add_item);


        //controls
        btnImg = (ImageButton) findViewById(R.id.imageButton);
        etxtDesc = (EditText) findViewById(R.id.etxtDesc);
        spnCat = (Spinner) findViewById(R.id.spnCat);
        spnSeason = (Spinner) findViewById(R.id.spnSeason);
        txtColors = (TextView) findViewById(R.id.txtColors);
        //colores almacenarlos en un array
        etxtSize = (EditText) findViewById(R.id.etxtSize);
        etxtPrice = (EditText) findViewById(R.id.etxtPrice);
        datePicker = (DatePicker) findViewById(R.id.datePicker);
        spnShop = (Spinner) findViewById(R.id.spnShop);

        //base de datos
        usdbh = new ItemSQLiteHelper(this, "Closet",
                null, 1);


        // vemos si recibe informaciÃ³n a travÃ©s de un intent (modificar)
        // Bundle extras = getIntent().getExtras();
        i = (Item) getIntent().getSerializableExtra("item");
        show(i);


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


        btnRmvColor = (Button) findViewById(R.id.btnRMVColor);
        btnRmvColor.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                removeColour();

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

        String[] categoy = getResources().getStringArray(R.array.categoriesArrays);

        for (int i = 0; i < categoy.length; i++) {
            if (categoy[i].equals(cat)) {
                index = i;
                break;
            }
        }

        return index;
    }

    private int getSeasonIndex(String season) {

        int index = 0;

        String[] seasons = getResources().getStringArray(R.array.seasonsArrays);

        for (int i = 0; i < seasons.length; i++) {
            if (seasons[i].equals(season)) {
                index = i;
                break;
            }
        }

        return index;
    }

    private void addColour() {

        //  Toast.makeText(this, "add colour", Toast.LENGTH_SHORT).show();

        String color;
        TextView txtColors = (TextView) findViewById(R.id.txtColors);
        Spinner spnColor = (Spinner) findViewById(R.id.spnCol);

        color = spnColor.getSelectedItem().toString();

        //si está vació que no meta la coma
        if (txtColors.getText().length() == 0) {

            txtColors.setText(color);

        } else {

            txtColors.setText(txtColors.getText() + ", " + color);

        }

        colorArray[colorCount] = color;
        colorCount++;

    }

    private void removeColour() {

        //cojer el texto del txt color y quitarle hasta la última ,

        TextView txtColors = (TextView) findViewById(R.id.txtColors);
        String allColors = txtColors.getText().toString();


        //remove last color
        if (allColors.indexOf(",") != -1) {
            txtColors.setText(allColors.substring(0, allColors.lastIndexOf(",")) + "");
        } else {
            txtColors.setText("");
        }


    }

    private void save() {


        //get bitmap from ImageButton
        BitmapDrawable drawable = (BitmapDrawable) btnImg.getDrawable();
        Bitmap bitmap = drawable.getBitmap();
        Log.e("bitmap ----size--", String.valueOf(bitmap.getByteCount()));

        //convert bitmap to string
        //String str = BitmapToString(bitmap);
        i.setImage(BitmapToString(bitmap));
        i.setDescription(etxtDesc.getText().toString());
        i.setCategory(spnCat.getSelectedItem().toString());
        i.setSeason(spnSeason.getSelectedItem().toString());
        i.setColours(txtColors.getText().toString());
        i.setS_date(getDateFromDatePicker(datePicker).toString());
        i.setSize(etxtSize.getText().toString());
        i.setPrize(Float.valueOf(etxtPrice.getText().toString()));
        i.setShop(spnShop.getSelectedItem().toString());


        //if all data is introduced
        if (validItem(i)) {

            //save on data base
            usdbh.updateItem(i);

            //create intent
            Intent intentSave = new Intent();
            Bundle mBundle = new Bundle();
            mBundle.putSerializable("item", (Serializable) i);
            intentSave.putExtras(mBundle);
            setResult(RESULT_OK, intentSave);

            // close the activity
            finish();
        }

    }

    public static Date getDateFromDatePicker(DatePicker datePicker) {
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
                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
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


    private void show(Item i) {

        btnImg.setImageBitmap(StringToBitmap(i.getImage()));
        etxtDesc.setText(i.getDescription());
        spnCat.setSelection(getCategoryIndex(i.getCategory()));
        spnSeason.setSelection(getSeasonIndex(i.getSeason()));
        txtColors.setText(i.getColours());
        //date
        //s_date = getDateFromDatePicker(datePicker);
        etxtSize.setText(i.getSize());
        etxtPrice.setText(String.valueOf(i.getPrize()));
        //shop
        // spnShop.setSelection(getShopIndex(i.getShop()));

    }

    private Bitmap StringToBitmap(String image) {

        byte[] decodedString = null;
        decodedString = Base64.decode(image, Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        return bitmap;
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


    private boolean validItem(Item item) {


        if (item.getDescription().length() > 0) {


            if (item.getColours().length() > 0) {


                if (item.getSize().length() > 0) {


                    return true;


                } else {
                    Toast.makeText(this, R.string.txt_invalid_size, Toast.LENGTH_SHORT).show();
                    return false;
                }


            } else {
                Toast.makeText(this, R.string.txt_invalid_colors, Toast.LENGTH_SHORT).show();
                return false;
            }


        } else {
            Toast.makeText(this, R.string.txt_invalid_description, Toast.LENGTH_SHORT).show();
            // Log.e("TAG-----validation", "description false");
            return false;
        }

    }

}
