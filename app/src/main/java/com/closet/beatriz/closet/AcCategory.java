package com.closet.beatriz.closet;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;


public class AcCategory extends Activity {

    public final static int REQUEST_ADD = 1;
    public final static int REQUEST_MODIFY = 2;
    GridView lv;
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
    String category;
    ItemSQLiteHelper usdbh;

    int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ac_category);

        Bundle extras = getIntent().getExtras();
        category = extras.getString("Category");

        //base de datos
        usdbh = new ItemSQLiteHelper(this, "Closet",
                null, 1);

        TextView title = (TextView) findViewById(R.id.txtTitle);
        title.setText(category);

        // Log.e("TAG--------", "crear adaptador");
        if (category.equals(getString(R.string.catShirts))) {
            Log.e("TAG--------------adaptador", "camisetas");
            // creamos el adaptador
            adaptador = new AdapterItem(this.getBaseContext(), ALShirts);

        } else if (category.equals(getString(R.string.catPants))) {
            // Log.e("TAG--------------adaptador", "pantalones");
            // creamos el adaptador
            adaptador = new AdapterItem(this.getBaseContext(), ALPants);

        } else if (category.equals(getString(R.string.catUnderWear))) {
            //  Log.e("TAG--------------adaptador", "ropa interior");
            // creamos el adaptador
            adaptador = new AdapterItem(this.getBaseContext(), ALUnderWear);

        } else if (category.equals(getString(R.string.catCoats))) {
            //  Log.e("TAG--------------adaptador", "abrigos");
            // creamos el adaptador
            adaptador = new AdapterItem(this.getBaseContext(), ALCoats);

        } else if (category.equals(getString(R.string.catShoes))) {
            //  Log.e("TAG--------------adaptador", "abrigos");
            // creamos el adaptador
            adaptador = new AdapterItem(this.getBaseContext(), ALShoes);

        } else if (category.equals(getString(R.string.catJumper))) {
            //  Log.e("TAG--------------adaptador", "abrigos");
            // creamos el adaptador
            adaptador = new AdapterItem(this.getBaseContext(), ALJumper);

        } else if (category.equals(getString(R.string.catPijamas))) {
            //  Log.e("TAG--------------adaptador", "abrigos");
            // creamos el adaptador
            adaptador = new AdapterItem(this.getBaseContext(), ALPijamas);

        } else if (category.equals(getString(R.string.catDress))) {
            //  Log.e("TAG--------------adaptador", "abrigos");
            // creamos el adaptador
            adaptador = new AdapterItem(this.getBaseContext(), ALDress);

        } else if (category.equals(getString(R.string.catAccesories))) {
            //  Log.e("TAG--------------adaptador", "abrigos");
            // creamos el adaptador
            adaptador = new AdapterItem(this.getBaseContext(), ALAccesories);

        }


        // creamos el adaptador
        //adaptador = new AdapterItem(this.getBaseContext(), lista);
        // creamos el adaptador
        lv = (GridView) findViewById(R.id.gridOneCategory);
        // asociar menu contextual
        registerForContextMenu(lv);
        lv.setAdapter(adaptador);

        // asociar menu contextual
        // registerForContextMenu(lv);
        // lv.setAdapter(adaptador);

        // Log.e("TAG", "before loadImages()");
        //loadImages(lista);

        //usdbh.cargarLista(lista);
        usdbh.cargarLista(this, ALShirts, ALPants, ALUnderWear, ALCoats, ALShoes, ALJumper, ALPijamas, ALDress, ALAccesories);


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
                Intent intentVer = new Intent(AcCategory.this, AcItemDetail.class);
                Bundle mBundle = new Bundle();
                //intentVer.putExtras("cat",category);
                mBundle.putSerializable("item", (Serializable) i);
                intentVer.putExtras(mBundle);
                startActivity(intentVer);

            }
        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.e("TAG-------", "onActivityResult");
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_ADD) {

                Item i = (Item) data.getSerializableExtra("item");

                // añadimos la nueva información a la lista
                String category = i.getCategory();

                addToList(category, i);


            } else if (requestCode == REQUEST_MODIFY) {

                deleteItem(pos);

                Item i = (Item) data.getSerializableExtra("item");

                String category = i.getCategory();

                addToList(category, i);


            }
        }
    }

    private void addToList(String category, Item i) {

        if (category.equals(getString(R.string.catShirts))) {
            ALShirts.add(i);

        } else if (category.equals(getString(R.string.catPants))) {

            ALPants.add(i);

        } else if (category.equals(getString(R.string.catUnderWear))) {
            ALUnderWear.add(i);

        } else if (category.equals(getString(R.string.catCoats))) {
            ALCoats.add(i);

        } else if (category.equals(getString(R.string.catShoes))) {
            ALShoes.add(i);

        } else if (category.equals(getString(R.string.catJumper))) {
            ALJumper.add(i);

        } else if (category.equals(getString(R.string.catPijamas))) {
            ALPijamas.add(i);

        } else if (category.equals(getString(R.string.catDress))) {
            ALDress.add(i);

        } else if (category.equals(getString(R.string.catAccesories))) {
            ALAccesories.add(i);
        }

        adaptador.notifyDataSetChanged();

    }

    /*
    private void loadImages(ArrayList<Item> lista) {


        Item i = new Item("camiseta1", "camisetas", "12/2/2015", "M", 23.45f, "Tesco");
        Log.e("TAG", i.description);
        lista.add(i);
        adaptador.notifyDataSetChanged();
        lista.add(i);
        adaptador.notifyDataSetChanged();
        lista.add(i);
        adaptador.notifyDataSetChanged();
        lista.add(i);
        adaptador.notifyDataSetChanged();
        i = new Item("camiseta5", "camisetas", "12/2/2015", "M", 3.02f, "Zara");
        lista.add(i);
        adaptador.notifyDataSetChanged();
        lista.add(i);
        adaptador.notifyDataSetChanged();
        lista.add(i);
        adaptador.notifyDataSetChanged();
        lista.add(i);
        adaptador.notifyDataSetChanged();
        lista.add(i);
        adaptador.notifyDataSetChanged();
        lista.add(i);
        adaptador.notifyDataSetChanged();
        lista.add(i);
        adaptador.notifyDataSetChanged();
        lista.add(i);
        adaptador.notifyDataSetChanged();

    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ac_category, menu);
        return true;
    }

    // menu contextual
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_ctx, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case (R.id.action_back):

                this.finish();
                break;

            case (R.id.action_add):


                Intent intentAdd = new Intent(this, AcAddItem.class);
                intentAdd.putExtra("Category", category);
                startActivityForResult(intentAdd, REQUEST_ADD);
                break;

            case (R.id.action_search):

                Intent intentSearch = new Intent(this, AcSearch.class);
                intentSearch.putExtra("Category", category);
                startActivity(intentSearch);

                break;
        }


        return super.onOptionsItemSelected(item);
    }


    // menuu contextual
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item
                .getMenuInfo();


        // guardamos la posición del item
        pos = info.position;
        Log.e("ID-click", String.valueOf(pos));

        Item i = null;

        i = (Item) lv.getItemAtPosition(pos);

        // segÃºn la opción seleccionada hacemos una cosa y otra
        switch (item.getItemId()) {
            case R.id.CtxLblModificar:


                // modificar(pos);
                Toast.makeText(this, "Modificar item", Toast.LENGTH_SHORT).show();
                modify(i);

                return true;

            // borrar
            case R.id.CtxLblBorrar:

                //delete from the DB
                usdbh.deleteItem(i.getId());

                deleteItem(pos);

                adaptador.notifyDataSetChanged();

                //user mesage
                Toast.makeText(this, R.string.txt_remove, Toast.LENGTH_SHORT).show();

                return true;

            default:
                return super.onContextItemSelected(item);
        }
    }

    // metodo modificar
    public void modify(Item i) {

        Intent intentModify = new Intent(AcCategory.this, AcModifyItem.class);
        Bundle mBundle = new Bundle();
        mBundle.putSerializable("item", (Serializable) i);
        intentModify.putExtras(mBundle);
        setResult(RESULT_OK, intentModify);
        startActivityForResult(intentModify, REQUEST_MODIFY);

    }

    public void deleteItem(int pos) {

        //remove on ArrayList dependeing on the category
        if (category.equals(getString(R.string.catShirts))) {
            ALShirts.remove(pos);

        } else if (category.equals(getString(R.string.catPants))) {

            ALPants.remove(pos);

        } else if (category.equals(getString(R.string.catUnderWear))) {
            ALUnderWear.remove(pos);

        } else if (category.equals(getString(R.string.catCoats))) {
            ALCoats.remove(pos);

        } else if (category.equals(getString(R.string.catShoes))) {
            ALShoes.remove(pos);

        } else if (category.equals(getString(R.string.catJumper))) {
            ALJumper.remove(pos);

        } else if (category.equals(getString(R.string.catPijamas))) {
            ALPijamas.remove(pos);

        } else if (category.equals(getString(R.string.catDress))) {
            ALDress.remove(pos);

        } else if (category.equals(getString(R.string.catAccesories))) {
            ALAccesories.remove(pos);
        }

        adaptador.notifyDataSetChanged();

    }

}
