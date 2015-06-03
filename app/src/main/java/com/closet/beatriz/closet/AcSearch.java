package com.closet.beatriz.closet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;


public class AcSearch extends Activity {

    String category;

    GridView lv;

    AdapterItem adaptador = null;
    private ArrayList<MyItem> lista = new ArrayList<MyItem>();
    private ArrayList<MyItem> backupLista = new ArrayList<MyItem>();

    SQLiteHelper usdbh;
    int pos;
    String[] arrayColors;
    String[] arraySeasons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);


        Bundle extras = getIntent().getExtras();
        category = extras.getString("Category");

        TextView title = (TextView) findViewById(R.id.txtTitle);
        title.setText(category);


        //spinners
        String[] getC = getResources().getStringArray(R.array.colorArrays);
        arrayColors = new String[getC.length + 1];
        // Log.e("TAG----- ", "season lengthe " + arraySeasons.length);
        // int seasons = arrayColors.length;
        System.arraycopy(getC, 0, arrayColors, 0, getC.length);
        // Log.e("TAG-------------color count", "nº: " + arrayColors.length);
        arrayColors[arrayColors.length - 1] = getResources().getString(R.string.txt_all);
        // arrayColors[arrayColors.length] = "Prueba";
        Spinner spinnerColors = (Spinner) findViewById(R.id.spnCol);

        // Application of the Array to the Spinner
        ArrayAdapter<String> spinnerArrayAdapterC = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayColors);
        spinnerArrayAdapterC.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        spinnerColors.setAdapter(spinnerArrayAdapterC);

        //default to all
        spinnerColors.setSelection(arrayColors.length - 1);


        //set the default to the last
//spinnerColors.setSelection(colors);

        Log.e("TAG----- ", "already set the color array ");

        //spinners
        String[] getS = getResources().getStringArray(R.array.seasonsArrays);
        arraySeasons = new String[getS.length + 1];
        Log.e("TAG----- ", "season lengthe " + arraySeasons.length);
        // int seasons = arrayColors.length;
        System.arraycopy(getS, 0, arraySeasons, 0, getS.length);
        Log.e("TAG-------------color count", "nº: " + arrayColors.length);
        arraySeasons[arraySeasons.length - 1] = getResources().getString(R.string.txt_all);
        // arrayColors[arrayColors.length] = "Prueba";
        Spinner spinnerSeason = (Spinner) findViewById(R.id.spnSeason);

        // Application of the Array to the Spinner
        ArrayAdapter<String> spinnerArrayAdapterS = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arraySeasons);
        spinnerArrayAdapterS.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        spinnerSeason.setAdapter(spinnerArrayAdapterS);

        //default to all
        spinnerSeason.setSelection(arraySeasons.length - 1);


        //base de datos
        usdbh = new SQLiteHelper(this, "Closet",
                null, 1);

        //cargamos la lista a mostrar
        selectList();

        Log.e("TAG-----------", "espues de cargar la lista");
        // creamos el adaptador
        adaptador = new AdapterItem(this.getBaseContext(), lista);
        // creamos el adaptador
        lv = (GridView) findViewById(R.id.gridSearch);
        // asociar menu contextual
        registerForContextMenu(lv);
        lv.setAdapter(adaptador);
        Log.e("TAG-----------", "espues de crear el adaptador");

        // definir el listener del listview
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                pos = position;
                //category =String.valueOf( R.string.catShirts);
                //MAYBE PROBLEM!!!

                MyItem myItem = null;
                myItem = (MyItem) lv.getItemAtPosition(position);
                //Log.e("TAG", "listener del gridview");
                // Toast.makeText(AcCategory.this, "Item seleccionado", Toast.LENGTH_LONG).show();
                Intent intentVer = new Intent(AcSearch.this, AcItemDetail.class);
                Bundle mBundle = new Bundle();
                //intentVer.putExtras("cat",category);
                mBundle.putSerializable("item", (Serializable) myItem);
                intentVer.putExtras(mBundle);
                startActivity(intentVer);

            }
        });

        Button btnCategory = (Button) findViewById(R.id.btnSearch);
        btnCategory.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                filter();
                //filter2();
                Toast.makeText(AcSearch.this, "filtrar info", Toast.LENGTH_SHORT).show();
            }
        });
        Log.e("TAG-----------", "final del on create");
    }

    private void selectList() {

        // usdbh.cargarLista(this, ALShirts, ALPants, ALUnderWear, ALCoats, ALShoes, ALJumper, ALPijamas, ALDress, ALAccesories);

        // Log.e("TAG--------", "crear adaptador");
        // Log.e("TAG--------", "crear adaptador");
        if (category.equals(getString(R.string.catShirts))) {

            lista = usdbh.getShirts(this);

        } else if (category.equals(getString(R.string.catPants))) {

            lista = usdbh.getPants(this);

        } else if (category.equals(getString(R.string.catUnderWear))) {

            lista = usdbh.getUnderWear(this);

        } else if (category.equals(getString(R.string.catCoats))) {

            lista = usdbh.getCoats(this);

        } else if (category.equals(getString(R.string.catShoes))) {

            lista = usdbh.getShoes(this);

        } else if (category.equals(getString(R.string.catJumper))) {

            lista = usdbh.getJumper(this);

        } else if (category.equals(getString(R.string.catPijamas))) {

            lista = usdbh.getPijamas(this);

        } else if (category.equals(getString(R.string.catDress))) {

            lista = usdbh.getDress(this);

        } else if (category.equals(getString(R.string.catAccesories))) {

            lista = usdbh.getAcessories(this);

        }

        //es lo mismo (copia) o se rellena con los datos de la lista??
        backupLista.addAll(lista);
        //backupLista();
        Log.e("TAG -----", "lista al cargar" + lista.size());

    }

    /*

    private void filter2() {

        lista.clear();
        lista.addAll(backupLista);
        adaptador.notifyDataSetChanged();
        int lstSearch = lista.size();
        int lstBack = backupLista.size();
        Log.e("----------------lista search", String.valueOf(lstSearch));
        Log.e("----------------lista backup", String.valueOf(lstBack));


        Spinner spn = (Spinner) findViewById(R.id.spnSeason);
        String season = spn.getSelectedItem().toString();
        //lista.clear();

        Log.e("TAG--------", "FILTRAMOS LA INFO DE ESTA ESTACIÓN");
        Log.e("SEASON--------", season);
        Log.e("SEASON--------", "-------------------------------");

        int size = lista.size();

        //Log.e("TAG--------", "lista size: " + lista.size());
        Log.e("TAG--------", "lista size: " + size);
        Log.e("TAG--------", "adapt size: " + adaptador.getCount());

        for (int t = 0; t < lista.size(); t++) {

            Item item = (Item) lv.getItemAtPosition(t);

            Log.e("TAG--------", "ITEM Nº: " + t);
            Log.e("item--------", "DESCRIPTION: " + item.getDescription());
            Log.e("item--------", "SEASON: " + item.getSeason());

            if (!item.getSeason().equals(season)) {

                // Log.e("TAG","borrar item");

                lista.remove(t);
                t--;

                Log.e("TAG----", "REMOVE ITEM");
                Log.e("item--------", "DESCRIPTION: " + item.getDescription());

            }

        }
        Log.e("TAG----", "lista size al final:   " + lista.size());

        adaptador.notifyDataSetChanged();
        Log.e("TAG----", "fin del for");
    }
*/
    private void filter() {

        lista.clear();
        lista.addAll(backupLista);
        adaptador.notifyDataSetChanged();

        Spinner spnSeason = (Spinner) findViewById(R.id.spnSeason);
        String season = spnSeason.getSelectedItem().toString();
        //lista.clear

        Spinner spnColur = (Spinner) findViewById(R.id.spnCol);
        String color = spnColur.getSelectedItem().toString();
        //CharSequence  colour = spnColur.getSelectedItem().toString();

        EditText txtDesc = (EditText) findViewById(R.id.etxtDesc);
        String desc = txtDesc.getText().toString();
        //String  desc = txtDesc.gettText().toString();


        Log.e("SEASON--------", season);
        Log.e("COLOR--------", color);
        Log.e("DESCRIPTION--------", desc);

        for (int i = 0; i < lista.size(); i++) {

            MyItem myItem = (MyItem) lv.getItemAtPosition(i);

            Log.e("item ------- check", "--------------------");
            Log.e("item ------- check", "position in list:   " + i);
            Log.e("item ------- check", "--------------------");
            Log.e("item--------season", myItem.getSeason());
            Log.e("item--------color", myItem.getColours());
            Log.e("item--------desc", myItem.getDescription());
            Log.e("item ------- check", "--------------------");

            if (!myItem.getSeason().equals(season) && !myItem.getSeason().equals(getResources().getString(R.string.txt_all))) {


                lista.remove(i);
                adaptador.notifyDataSetChanged();
                Log.e("TAG----", "remove item");

                i--;

                // Log.e("TAG -- remove", "No season, " + item.getSeason());

            } else if (!myItem.getColours().contains(color) && !myItem.getColours().equals(getResources().getString(R.string.txt_all))) {

                // Log.e("TAG -- remove", "No colors, " + item.getColours());

                lista.remove(i);
                adaptador.notifyDataSetChanged();
                Log.e("TAG----", "remove item");

                i--;

            } else if (desc.length() > 0 && !myItem.getDescription().contains(desc)) {

                // Log.e("TAG -- remove", "No description, " + item.getDescription());
                lista.remove(i);
                adaptador.notifyDataSetChanged();
                Log.e("TAG----", "remove item");

                i--;

            }


        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
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