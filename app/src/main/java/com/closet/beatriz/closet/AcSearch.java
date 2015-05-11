package com.closet.beatriz.closet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
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
    private ArrayList<Item> lista = new ArrayList<Item>();
    private ArrayList<Item> backupLista = new ArrayList<Item>();
    private ArrayList<Item> ALShirts = new ArrayList<Item>();
    private ArrayList<Item> ALPants = new ArrayList<Item>();
    private ArrayList<Item> ALUnderWear = new ArrayList<Item>();
    private ArrayList<Item> ALCoats = new ArrayList<Item>();
    private ArrayList<Item> ALShoes = new ArrayList<Item>();
    private ArrayList<Item> ALJumper = new ArrayList<Item>();
    private ArrayList<Item> ALPijamas = new ArrayList<Item>();
    private ArrayList<Item> ALDress = new ArrayList<Item>();
    private ArrayList<Item> ALAccesories = new ArrayList<Item>();

    ItemSQLiteHelper usdbh;
    int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);


        Bundle extras = getIntent().getExtras();
        category = extras.getString("Category");

        TextView title = (TextView) findViewById(R.id.txtTitle);
        title.setText(category);

        //base de datos
        usdbh = new ItemSQLiteHelper(this, "Closet",
                null, 1);

        //cargamos la lista a mostrar
        selectList();

        // creamos el adaptador
        adaptador = new AdapterItem(this.getBaseContext(), lista);
        // creamos el adaptador
        lv = (GridView) findViewById(R.id.gridSearch);
        // asociar menu contextual
        registerForContextMenu(lv);
        lv.setAdapter(adaptador);


        //usdbh.cargarLista(this, ALShirts, ALPants, ALUnderWear, ALCoats, ALShoes, ALJumper, ALPijamas, ALDress, ALAccesories);


        // definir el listener del listview
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                pos = position;
                //category =String.valueOf( R.string.catShirts);
                //MAYBE PROBLEM!!!

                Item i = null;
                i = (Item) lv.getItemAtPosition(position);
                //Log.e("TAG", "listener del gridview");
                // Toast.makeText(AcCategory.this, "Item seleccionado", Toast.LENGTH_LONG).show();
                Intent intentVer = new Intent(AcSearch.this, AcItemDetail.class);
                Bundle mBundle = new Bundle();
                //intentVer.putExtras("cat",category);
                mBundle.putSerializable("item", (Serializable) i);
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

    }

    private void selectList() {


        usdbh.cargarLista(this, ALShirts, ALPants, ALUnderWear, ALCoats, ALShoes, ALJumper, ALPijamas, ALDress, ALAccesories);

        // Log.e("TAG--------", "crear adaptador");
        if (category.equals(getString(R.string.catShirts))) {

            lista = ALShirts;

        } else if (category.equals(getString(R.string.catPants))) {

            lista = ALPants;

        } else if (category.equals(getString(R.string.catUnderWear))) {

            lista = ALUnderWear;

        } else if (category.equals(getString(R.string.catCoats))) {

            lista = ALCoats;

        } else if (category.equals(getString(R.string.catShoes))) {

            lista = ALShoes;

        } else if (category.equals(getString(R.string.catJumper))) {

            lista = ALJumper;

        } else if (category.equals(getString(R.string.catPijamas))) {

            lista = ALPijamas;

        } else if (category.equals(getString(R.string.catDress))) {

            lista = ALDress;

        } else if (category.equals(getString(R.string.catAccesories))) {

            lista = ALAccesories;

        }

        backupLista = lista;
        Log.e("TAG -----", "lista al cargar" + lista.size());


    }

    private void filter() {

        lista = backupLista;
        adaptador.notifyDataSetChanged();

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

        /*
        for (int a = 0; a < lista.size(); a++) {

            Log.e("TAG", "primer bucle " + a);

        }*/

        for (int t = 0; t < lista.size(); t++) {

            Item item = (Item) lv.getItemAtPosition(t);

            Log.e("TAG--------", "ITEM Nº: " + t);
            Log.e("item--------", "DESCRIPTION: " + item.getDescription());
            Log.e("item--------", "SEASON: " + item.getSeason());

            if (!item.getSeason().equals(season)) {

                // Log.e("TAG","borrar item");

                lista.remove(t);

                Log.e("TAG----", "REMOVE ITEM");
                Log.e("item--------", "DESCRIPTION: " + item.getDescription());

            }

        }

        adaptador.notifyDataSetChanged();
        Log.e("TAG----", "fin del for");
    }

    private void filter2() {

        lista = backupLista;

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

        for (int i = 0; i < lista.size(); i++) {
            Item item = (Item) lv.getItemAtPosition(i);
            Log.e("item--------", item.getDescription());

            if (item.getSeason().equals(season) == false) {

                //if (!item.getColor().contains(color)) {

                if (!item.getDescription().contains(desc)) {

                    //peta
                    // ALShirts.remove(item);
                    lista.remove(i);
                    adaptador.notifyDataSetChanged();
                    Log.e("TAG----", "remove item");

                }

                //}

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