package com.closet.beatriz.closet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;


public class AcSearch extends Activity {

    String category;

    GridView lv;

    AdapterItem adaptador = null;
    private ArrayList<Item> lista = new ArrayList<Item>();
    private ArrayList<Item> ALCamisetas = new ArrayList<Item>();
    private ArrayList<Item> ALPantalones = new ArrayList<Item>();
    private ArrayList<Item> ALRopaInterior = new ArrayList<Item>();
    private ArrayList<Item> ALAbrigos = new ArrayList<Item>();

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


        // Log.e("TAG--------", "crear adaptador");

        if (category.equals(getString(R.string.catShirts))) {
            Log.e("TAG--------------adaptador", "camisetas");
            // creamos el adaptador
            adaptador = new AdapterItem(this.getBaseContext(), ALCamisetas);

        } else if (category.equals(getString(R.string.catPants))) {
            // Log.e("TAG--------------adaptador", "pantalones");
            // creamos el adaptador
            adaptador = new AdapterItem(this.getBaseContext(), ALPantalones);

        } else if (category.equals(getString(R.string.catUnderWear))) {
            //  Log.e("TAG--------------adaptador", "ropa interior");
            // creamos el adaptador
            adaptador = new AdapterItem(this.getBaseContext(), ALRopaInterior);

        } else if (category.equals(getString(R.string.catCoats))) {
            //  Log.e("TAG--------------adaptador", "abrigos");
            // creamos el adaptador
            adaptador = new AdapterItem(this.getBaseContext(), ALAbrigos);

        }

        // creamos el adaptador
        //adaptador = new AdapterItem(this.getBaseContext(), lista);
        // creamos el adaptador
        lv = (GridView) findViewById(R.id.gridSearch);
        // asociar menu contextual
        registerForContextMenu(lv);
        lv.setAdapter(adaptador);

        usdbh.cargarLista(ALCamisetas, ALPantalones, ALRopaInterior, ALAbrigos);


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
