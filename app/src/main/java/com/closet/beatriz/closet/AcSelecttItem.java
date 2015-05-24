package com.closet.beatriz.closet;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;


public class AcSelecttItem extends Activity {

    ExpandableHeightGridView gridShirts;
    ExpandableHeightGridView gridPants;
    ExpandableHeightGridView gridUnderWear;
    ExpandableHeightGridView gridCoats;
    ExpandableHeightGridView gridShoes;
    ExpandableHeightGridView gridJumper;
    ExpandableHeightGridView gridPijamas;
    ExpandableHeightGridView gridDress;
    ExpandableHeightGridView gridAccesories;
    AdapterItem adaptador = null;
    private ArrayList<Item> lista = new ArrayList<Item>();
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ac_selectt_item);

        //base de datos
        usdbh = new ItemSQLiteHelper(this, "Closet",
                null, 1);

        showItems();
        gridListeners();

    }


    private void gridListeners() {

        // definir el listener del listview
        gridShirts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                //pos = position;

                Item i;
                i = (Item) gridShirts.getItemAtPosition(position);

                selectItem(i);

            }
        });


        // definir el listener del listview
        gridPants.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                //pos = position;

                Item i;
                i = (Item) gridPants.getItemAtPosition(position);

                selectItem(i);

            }
        });


        // definir el listener del listview
        gridUnderWear.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                //pos = position;

                Item i;
                i = (Item) gridUnderWear.getItemAtPosition(position);

                selectItem(i);
            }
        });

        // definir el listener del listview
        gridCoats.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                //pos = position;

                Item i;
                i = (Item) gridCoats.getItemAtPosition(position);

                selectItem(i);

            }
        });


        // definir el listener del listview
        gridShoes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                //pos = position;

                Item i;
                i = (Item) gridShoes.getItemAtPosition(position);

                selectItem(i);
            }
        });

        // definir el listener del listview
        gridJumper.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                //pos = position;

                Item i;
                i = (Item) gridJumper.getItemAtPosition(position);

                selectItem(i);

            }
        });

        // definir el listener del listview
        gridPijamas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                //pos = position;

                Item i;
                i = (Item) gridPijamas.getItemAtPosition(position);

                selectItem(i);

            }
        });

        // definir el listener del listview
        gridDress.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                //pos = position;

                Item i;
                i = (Item) gridDress.getItemAtPosition(position);

                selectItem(i);

            }
        });

        // definir el listener del listview
        gridAccesories.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                //pos = position;

                Item i;
                i = (Item) gridAccesories.getItemAtPosition(position);

                selectItem(i);

            }
        });


        gridAccesories.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Toast.makeText(AcSelecttItem.this, "long toast", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private ArrayList<Item> selectItem(Item i) {

        Toast.makeText(this, R.string.txt_outfit_added, Toast.LENGTH_SHORT).show();


        lista.add(i);
        return lista;
    }

    public void showItems() {

        usdbh.cargarLista(this, ALShirts, ALPants, ALUnderWear, ALCoats, ALShoes, ALJumper, ALPijamas,
                ALDress, ALAccesories);

        //shirts
        adaptador = new AdapterItem(this.getBaseContext(), ALShirts);
        gridShirts = (ExpandableHeightGridView) findViewById(R.id.gridViewshirts);
        gridShirts.setExpanded(true);
        // asociar menu contextual
        registerForContextMenu(gridShirts);
        gridShirts.setAdapter(adaptador);

        //pants
        adaptador = new AdapterItem(this.getBaseContext(), ALPants);
        gridPants = (ExpandableHeightGridView) findViewById(R.id.gridViewPants);
        gridPants.setExpanded(true);
        // asociar menu contextual
        registerForContextMenu(gridPants);
        gridPants.setAdapter(adaptador);

        //Under wear
        adaptador = new AdapterItem(this.getBaseContext(), ALUnderWear);
        gridUnderWear = (ExpandableHeightGridView) findViewById(R.id.gridViewUnderWear);
        gridUnderWear.setExpanded(true);
        // asociar menu contextual
        registerForContextMenu(gridUnderWear);
        gridUnderWear.setAdapter(adaptador);

        //Coats
        adaptador = new AdapterItem(this.getBaseContext(), ALCoats);
        gridCoats = (ExpandableHeightGridView) findViewById(R.id.gridViewCoats);
        gridCoats.setExpanded(true);
        // asociar menu contextual
        registerForContextMenu(gridCoats);
        gridCoats.setAdapter(adaptador);

        //shoes
        adaptador = new AdapterItem(this.getBaseContext(), ALShoes);
        gridShoes = (ExpandableHeightGridView) findViewById(R.id.gridViewShoes);
        gridShoes.setExpanded(true);
        // asociar menu contextual
        registerForContextMenu(gridShoes);
        gridShoes.setAdapter(adaptador);

        //Jumper
        adaptador = new AdapterItem(this.getBaseContext(), ALJumper);
        gridJumper = (ExpandableHeightGridView) findViewById(R.id.gridViewJumper);
        gridJumper.setExpanded(true);
        // asociar menu contextual
        registerForContextMenu(gridJumper);
        gridJumper.setAdapter(adaptador);

        //Pijamas
        adaptador = new AdapterItem(this.getBaseContext(), ALPijamas);
        gridPijamas = (ExpandableHeightGridView) findViewById(R.id.gridViewPijamas);
        gridPijamas.setExpanded(true);
        // asociar menu contextual
        registerForContextMenu(gridPijamas);
        gridPijamas.setAdapter(adaptador);


        //Dresses
        adaptador = new AdapterItem(this.getBaseContext(), ALDress);
        gridDress = (ExpandableHeightGridView) findViewById(R.id.gridViewDress);
        gridDress.setExpanded(true);
        // asociar menu contextual
        registerForContextMenu(gridDress);
        gridDress.setAdapter(adaptador);

        //Accesories
        adaptador = new AdapterItem(this.getBaseContext(), ALAccesories);
        gridAccesories = (ExpandableHeightGridView) findViewById(R.id.gridViewAccesories);
        gridAccesories.setExpanded(true);
        // asociar menu contextual
        registerForContextMenu(gridAccesories);
        gridAccesories.setAdapter(adaptador);

    }

    public void save() {

        Toast.makeText(this, "guardar", Toast.LENGTH_SHORT).show();
        //create intent
        Intent intentSave = new Intent();
        intentSave.putExtra("list", lista);
        setResult(RESULT_OK, intentSave);

        // close the activity
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ac_selectt_item, menu);
        return true;
    }

    // menu contextual
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_ctx_see, menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case (R.id.action_close):

                this.finish();
                break;

            case (R.id.action_done):
                save();

                break;


        }


        return super.onOptionsItemSelected(item);
    }

    // menuu contextual
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item
                .getMenuInfo();

        switch (item.getItemId()) {

            case R.id.CtxLblSee:

                Item i;
                i = (Item) gridPants.getItemAtPosition(info.position);

                Intent intentVer = new Intent(AcSelecttItem.this, AcItemDetail.class);
                Bundle mBundle = new Bundle();
                mBundle.putSerializable("item", (Serializable) i);
                intentVer.putExtras(mBundle);
                startActivity(intentVer);

                return true;

            default:
                return super.onContextItemSelected(item);
        }
    }

}
