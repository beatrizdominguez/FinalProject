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
import android.widget.AdapterView;
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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class AcAddItem extends Activity {


    //controls
    EditText etxtDesc;
    Spinner spnCat;
    Spinner spnSeason;
    TextView txtColors;
    //colores almacenarlos en un array
    EditText etxtSize;
    EditText etxtPrice;
    DatePicker datePicker;
    Spinner spnShop;

    public final static int SPINNER_COLOR = 1;
    public final static int SPINNER_CATEGORY = 2;

    Spinner spnColor;


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

    String color = "";
    String category = "";

    ItemSQLiteHelper usdbh;

    //Custom spinner
    /**
     * ***********  Intialize Variables ************
     */
    // public ArrayList<SpinnerModel> CustomListViewValuesArr = new ArrayList<SpinnerModel>();
    SpinnerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_add_item);


        //controls
        etxtDesc = (EditText) findViewById(R.id.etxtDesc);
        spnCat = (Spinner) findViewById(R.id.spnCat);
        initializeCategorySpinner();
        spnSeason = (Spinner) findViewById(R.id.spnSeason);
        //colors
        txtColors = (TextView) findViewById(R.id.txtColors);
        spnColor = (Spinner) findViewById(R.id.spnCol);
        initializeColorSpinner();

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


        btnRmvColor = (Button) findViewById(R.id.btnRMVColor);
        btnRmvColor.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                removeColour();

            }
        });

    }

    private void initializeColorSpinner() {

        ArrayList<SpinnerModel> CustomListViewValuesArr = new ArrayList<SpinnerModel>();
        // Now i have taken static values by loop.
        // For further inhancement we can take data by webservice / json / xml;
        String[] colorNames = getResources().getStringArray(R.array.colorArrays);
        String[] colorCodes = getResources().getStringArray(R.array.colors);

        for (int i = 0; i < colorNames.length; i++) {

            final SpinnerModel sched = new SpinnerModel();

            /******* Firstly take data in model object ******/
            sched.setType(SPINNER_COLOR);
            sched.setName(colorNames[i]);
            sched.setImage(colorCodes[i]);


            /******** Take Model Object in ArrayList **********/
            CustomListViewValuesArr.add(sched);
        }

        // Create custom adapter object ( see below CustomAdapter.java )
        adapter = new SpinnerAdapter(this, R.layout.custom_spinner, CustomListViewValuesArr, getResources());

        // Set adapter to spinner
        spnColor.setAdapter(adapter);


        // Listener called when spinner item selected
        spnColor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View v, int position, long id) {
                // your code here

                // Get selected row data to show on screen
                color = ((TextView) v.findViewById(R.id.name)).getText().toString();

                Toast.makeText(
                        getApplicationContext(), color, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

    }

    private void initializeCategorySpinner() {
        ArrayList<SpinnerModel> CustomListViewValuesArr = new ArrayList<SpinnerModel>();

        // Now i have taken static values by loop.
        // For further inhancement we can take data by webservice / json / xml;
        String[] catNames = getResources().getStringArray(R.array.categoriesArrays);
        String[] catIcons = getResources().getStringArray(R.array.categoriesImagesArrays);

        for (int i = 0; i < catNames.length; i++) {

            final SpinnerModel sched = new SpinnerModel();

            /******* Firstly take data in model object ******/
            sched.setType(SPINNER_CATEGORY);
            sched.setName(catNames[i]);
            sched.setImage(catIcons[i]);
            // Log.e("TAG------image", "image array--- " + catIcons[i]);
            // Log.e("TAG------image", "image object--- " + sched.getImage());

            /******** Take Model Object in ArrayList **********/
            CustomListViewValuesArr.add(sched);
        }

        // Create custom adapter object ( see below CustomAdapter.java )
        adapter = new SpinnerAdapter(this, R.layout.custom_spinner, CustomListViewValuesArr, getResources());

        // Set adapter to spinner
        spnCat.setAdapter(adapter);

        // Listener called when spinner item selected
        spnCat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View v, int position, long id) {
                // your code here

                // Get selected row data to show on screen
                category = ((TextView) v.findViewById(R.id.name)).getText().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

    }

    private void removeColour() {

        //cojer el texto del txt color y quitarle hasta la última ,

        TextView txtColors = (TextView) findViewById(R.id.txtColors);

        String allColors = txtColors.getText().toString();

        //String ultimo = allColors.substring(allColors.lastIndexOf(',') + 1);

        if (allColors.indexOf(",") != -1) {
            txtColors.setText(allColors.substring(0, allColors.lastIndexOf(",")) + "");
        } else {
            txtColors.setText("");
        }

    }

    private int getCategoryIndex(String cat) {

        category = cat;
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

        //si está vació que no meta la coma
        if (txtColors.getText().length() == 0) {

            txtColors.setText(color);

        } else {

            txtColors.setText(txtColors.getText() + ", " + color);

        }

        colorArray[colorCount] = color;
        colorCount++;

    }

    private void save() {

        Log.e("TAG", "justo dentro del metodo save");
        //int id;
        String image;
        String description;
        //  String category;
        String colors;
        String season;
        Date s_date;
        //String s_date;
        String size;
        float prize;
        String shop;

        // btnImg.buildDrawingCache();
        // image =  btnImg.getDrawingCache();

        //get bitmap from ImageButton
        BitmapDrawable drawable = (BitmapDrawable) btnImg.getDrawable();
        Bitmap bitmap = drawable.getBitmap();
//        Log.e("bitmap ----size--", String.valueOf(bitmap.getByteCount()));

        //convert bitmap to string
        image = BitmapToString(bitmap);
        description = etxtDesc.getText().toString();
        // category = spnCat.getSelectedItem().toString();
        // Toast.makeText(this, "category del item: " + category, Toast.LENGTH_SHORT).show();
        //  Log.e("TAG--season", "before season------------");
        // Log.e("TAG--season", spnSeason.getSelectedItem().toString());
        season = spnSeason.getSelectedItem().toString();
        colors = txtColors.getText().toString();
        s_date = getDateFromDatePicker(datePicker);
        size = etxtSize.getText().toString();
        prize = Float.valueOf(etxtPrice.getText().toString());
        shop = spnShop.getSelectedItem().toString();

        //Log.e("TAG----------", s_date.toString());
        // Log.e("TAG----------BTM byte count", String.valueOf(imageBitmap.getByteCount()));
        Item i = new Item(image, description, category, season, colors, s_date.toString(), size, prize, shop);
        // Item i = new Item(description, colorArray, category, s_date.toString(), size, prize, shop);

        if (validItem(i)) {

            Log.e("TAG_____________add", "guardar item y cerrar");


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

            case (R.id.action_save):

                Log.e("TAG", "le he dado al botón");
                save();

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
