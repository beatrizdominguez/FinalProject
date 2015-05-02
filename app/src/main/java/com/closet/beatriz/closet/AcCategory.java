package com.closet.beatriz.closet;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
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
    GridView camisetas;
    AdapterItem adaptador;
    private ArrayList<Item> lista = new ArrayList<Item>();
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


        // creamos el adaptador
        adaptador = new AdapterItem(this.getBaseContext(), lista);
        // creamos el adaptador
        camisetas = (GridView) findViewById(R.id.gridOneCategory);
        // asociar menu contextual
        registerForContextMenu(camisetas);
        camisetas.setAdapter(adaptador);

        Log.e("TAG", "before loadImages()");
        loadImages(lista);

        usdbh.cargarLista(lista);



        // definir el listener del listview
        camisetas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                pos = position;
                category = "Camisetas";

                Item i = null;
                i = (Item) camisetas.getItemAtPosition(position);
                Log.e("TAG", "listener del gridview");
                Toast.makeText(AcCategory.this, "Item seleccionado", Toast.LENGTH_LONG).show();
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
                lista.add(i);
                adaptador.notifyDataSetChanged();

                //añadir a la base de datos
                usdbh.guardarItem(i);


            }
        }
    }

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

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ac_category, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case (R.id.action_back):

                this.finish();
                break;

            case (R.id.action_add):


                Intent intentAdd = new Intent(this, AcAddItem.class);
                //intent.putExtra("Category", cat);
                startActivityForResult(intentAdd, REQUEST_ADD);
                break;

            case (R.id.action_search):

                Intent intentSearch = new Intent(this, AcSearch.class);
                //intent.putExtra("Category", cat);
                startActivity(intentSearch);

                break;
        }


        return super.onOptionsItemSelected(item);
    }
}
