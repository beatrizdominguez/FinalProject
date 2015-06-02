package com.closet.beatriz.closet;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;


public class AcSelecttItem extends Activity {

    MyExpandableHeightGridView gridShirts;
    MyExpandableHeightGridView gridPants;
    MyExpandableHeightGridView gridUnderWear;
    MyExpandableHeightGridView gridCoats;
    MyExpandableHeightGridView gridShoes;
    MyExpandableHeightGridView gridJumper;
    MyExpandableHeightGridView gridPijamas;
    MyExpandableHeightGridView gridDress;
    MyExpandableHeightGridView gridAccesories;
    AdapterItem adaptador = null;
    private ArrayList<MyItem> lista = new ArrayList<MyItem>();
    private ArrayList<MyItem> ALShirts = new ArrayList<MyItem>();
    private ArrayList<MyItem> ALPants = new ArrayList<MyItem>();
    private ArrayList<MyItem> ALUnderWear = new ArrayList<MyItem>();
    private ArrayList<MyItem> ALCoats = new ArrayList<MyItem>();
    private ArrayList<MyItem> ALShoes = new ArrayList<MyItem>();
    private ArrayList<MyItem> ALJumper = new ArrayList<MyItem>();
    private ArrayList<MyItem> ALPijamas = new ArrayList<MyItem>();
    private ArrayList<MyItem> ALDress = new ArrayList<MyItem>();
    private ArrayList<MyItem> ALAccesories = new ArrayList<MyItem>();

    SQLiteHelper usdbh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ac_selectt_item);

        //base de datos
        usdbh = new SQLiteHelper(this, "Closet",
                null, 1);

        loadLists();
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

                MyItem myItem;
                myItem = (MyItem) gridShirts.getItemAtPosition(position);

                selectItem(myItem);

            }
        });


        // definir el listener del listview
        gridPants.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                //pos = position;

                MyItem myItem;
                myItem = (MyItem) gridPants.getItemAtPosition(position);

                selectItem(myItem);

            }
        });


        // definir el listener del listview
        gridUnderWear.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                //pos = position;

                MyItem myItem;
                myItem = (MyItem) gridUnderWear.getItemAtPosition(position);

                selectItem(myItem);
            }
        });

        // definir el listener del listview
        gridCoats.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                //pos = position;

                MyItem myItem;
                myItem = (MyItem) gridCoats.getItemAtPosition(position);

                selectItem(myItem);

            }
        });


        // definir el listener del listview
        gridShoes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                //pos = position;

                MyItem myItem;
                myItem = (MyItem) gridShoes.getItemAtPosition(position);

                selectItem(myItem);
            }
        });

        // definir el listener del listview
        gridJumper.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                //pos = position;

                MyItem myItem;
                myItem = (MyItem) gridJumper.getItemAtPosition(position);

                selectItem(myItem);

            }
        });

        // definir el listener del listview
        gridPijamas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                //pos = position;

                MyItem myItem;
                myItem = (MyItem) gridPijamas.getItemAtPosition(position);

                selectItem(myItem);

            }
        });

        // definir el listener del listview
        gridDress.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                //pos = position;

                MyItem myItem;
                myItem = (MyItem) gridDress.getItemAtPosition(position);

                selectItem(myItem);

            }
        });

        // definir el listener del listview
        gridAccesories.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                //pos = position;

                MyItem myItem;
                myItem = (MyItem) gridAccesories.getItemAtPosition(position);

                selectItem(myItem);

            }
        });


    }

    private ArrayList<MyItem> selectItem(MyItem i) {

        Toast.makeText(this, R.string.txt_outfit_added, Toast.LENGTH_SHORT).show();

        lista.add(i);
        return lista;
    }

    public void loadLists() {
        ALShirts = usdbh.getShirts(this);
        ALPants = usdbh.getPants(this);
        ALUnderWear = usdbh.getUnderWear(this);
        ALCoats = usdbh.getCoats(this);
        ALShoes = usdbh.getShoes(this);
        ALJumper = usdbh.getJumper(this);
        ALPijamas = usdbh.getPijamas(this);
        ALDress = usdbh.getDress(this);
        ALAccesories = usdbh.getAcessories(this);

    }

    public void showItems() {

        //*********** shirts *************//
        adaptador = new AdapterItem(this.getBaseContext(), ALShirts);
        gridShirts = (MyExpandableHeightGridView) findViewById(R.id.gridViewshirts);
        gridShirts.setExpanded(true);
        // asociar menu contextual
        registerForContextMenu(gridShirts);
        gridShirts.setAdapter(adaptador);

        //*********** pants *************//
        adaptador = new AdapterItem(this.getBaseContext(), ALPants);
        gridPants = (MyExpandableHeightGridView) findViewById(R.id.gridViewPants);
        gridPants.setExpanded(true);
        // asociar menu contextual
        registerForContextMenu(gridPants);
        gridPants.setAdapter(adaptador);

        //*********** Under wear *************//
        adaptador = new AdapterItem(this.getBaseContext(), ALUnderWear);
        gridUnderWear = (MyExpandableHeightGridView) findViewById(R.id.gridViewUnderWear);
        gridUnderWear.setExpanded(true);
        // asociar menu contextual
        registerForContextMenu(gridUnderWear);
        gridUnderWear.setAdapter(adaptador);

        //*********** Coats *************//
        adaptador = new AdapterItem(this.getBaseContext(), ALCoats);
        gridCoats = (MyExpandableHeightGridView) findViewById(R.id.gridViewCoats);
        gridCoats.setExpanded(true);
        // asociar menu contextual
        registerForContextMenu(gridCoats);
        gridCoats.setAdapter(adaptador);

        //*********** shoes *************//
        adaptador = new AdapterItem(this.getBaseContext(), ALShoes);
        gridShoes = (MyExpandableHeightGridView) findViewById(R.id.gridViewShoes);
        gridShoes.setExpanded(true);
        // asociar menu contextual
        registerForContextMenu(gridShoes);
        gridShoes.setAdapter(adaptador);

        //*********** Jumper *************//
        adaptador = new AdapterItem(this.getBaseContext(), ALJumper);
        gridJumper = (MyExpandableHeightGridView) findViewById(R.id.gridViewJumper);
        gridJumper.setExpanded(true);
        // asociar menu contextual
        registerForContextMenu(gridJumper);
        gridJumper.setAdapter(adaptador);

        //*********** Pijamas *************//
        adaptador = new AdapterItem(this.getBaseContext(), ALPijamas);
        gridPijamas = (MyExpandableHeightGridView) findViewById(R.id.gridViewPijamas);
        gridPijamas.setExpanded(true);
        // asociar menu contextual
        registerForContextMenu(gridPijamas);
        gridPijamas.setAdapter(adaptador);


        //*********** Dresses *************//
        adaptador = new AdapterItem(this.getBaseContext(), ALDress);
        gridDress = (MyExpandableHeightGridView) findViewById(R.id.gridViewDress);
        gridDress.setExpanded(true);
        // asociar menu contextual
        registerForContextMenu(gridDress);
        gridDress.setAdapter(adaptador);

        //*********** Accesories *************//
        adaptador = new AdapterItem(this.getBaseContext(), ALAccesories);
        gridAccesories = (MyExpandableHeightGridView) findViewById(R.id.gridViewAccesories);
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

                MyItem myItem;
                myItem = (MyItem) gridPants.getItemAtPosition(info.position);

                Intent intentVer = new Intent(AcSelecttItem.this, AcItemDetail.class);
                Bundle mBundle = new Bundle();
                mBundle.putSerializable("item", (Serializable) myItem);
                intentVer.putExtras(mBundle);
                startActivity(intentVer);

                return true;

            default:
                return super.onContextItemSelected(item);
        }
    }

}
