package com.closet.beatriz.closet;

import android.app.Activity;
import android.content.Intent;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class AcCategory extends Activity {

    public final static int REQUEST_ADD = 1;
    public final static int REQUEST_MODIFY = 2;
    GridView lv;
    AdapterItem adaptador = null;
    private ArrayList<MyItem> lista = new ArrayList<MyItem>();

    String category;
    SQLiteHelper usdbh;

    int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ac_category);

        Bundle extras = getIntent().getExtras();
        category = extras.getString("Category");

        //base de datos
        usdbh = new SQLiteHelper(this, "Closet",
                null, 1);

        TextView title = (TextView) findViewById(R.id.txtTitle);
        title.setText(category);

        selectList();

        adaptador = new AdapterItem(this.getBaseContext(), lista);

        // creamos el adaptador
        //adaptador = new AdapterItem(this.getBaseContext(), lista);
        // creamos el adaptador
        lv = (GridView) findViewById(R.id.gridOneCategory);
        // asociar menu contextual
        registerForContextMenu(lv);
        lv.setAdapter(adaptador);

        // definir el listener del listview
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                pos = position;
                //category =String.valueOf( R.string.catShirts);
                //MAYBE PROBLEM!!!

                MyItem item;
                item = (MyItem) lv.getItemAtPosition(position);
                //Log.e("TAG", "listener del gridview");
                // Toast.makeText(AcCategory.this, "Item seleccionado", Toast.LENGTH_LONG).show();
                Intent intentVer = new Intent(AcCategory.this, AcItemDetail.class);
                Bundle mBundle = new Bundle();
                //intentVer.putExtras("cat",category);
                mBundle.putSerializable("item", (Serializable) item);
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

                MyItem item = (MyItem) data.getSerializableExtra("item");

                if (item.getCategory().equals(category)) {

                    addToList(item);

                }


            } else if (requestCode == REQUEST_MODIFY) {

                deleteItem(pos);

                MyItem item = (MyItem) data.getSerializableExtra("item");

                if (item.getCategory().equals(category)) {

                    addToList(item);

                }


            }
        }
    }

    private void addToList(MyItem item) {

        lista.add(item);
        adaptador.notifyDataSetChanged();

    }

    private void selectList() {


        //usdbh.cargarLista(this, ALShirts, ALPants, ALUnderWear, ALCoats, ALShoes, ALJumper, ALPijamas, ALDress, ALAccesories);

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

    }


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

                Intent intentBack = new Intent();
                intentBack.putExtra("Category", category);
                setResult(RESULT_OK, intentBack);

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

        MyItem myItem;

        myItem = (MyItem) lv.getItemAtPosition(pos);

        // segÃºn la opción seleccionada hacemos una cosa y otra
        switch (item.getItemId()) {

            case R.id.CtxLblToday:

                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyy");
                String date = dateFormat.format(System.currentTimeMillis());
                // Toast.makeText(this, "date of today formated:  " + date2, Toast.LENGTH_SHORT).show();

                usdbh.addCalendarItem(this, date, myItem);
                Toast.makeText(this, R.string.msg_today, Toast.LENGTH_SHORT).show();

                return true;
            case R.id.CtxLblModificar:


                // Toast.makeText(this, "Modificar item", Toast.LENGTH_SHORT).show();
                modify(myItem);

                return true;

            // borrar
            case R.id.CtxLblBorrar:

                //delete from the DB
                usdbh.deleteItem(myItem.getId());

                deleteItem(pos);

                adaptador.notifyDataSetChanged();

                //user mesage
                Toast.makeText(this, R.string.msg_remove, Toast.LENGTH_SHORT).show();

                return true;

            default:
                return super.onContextItemSelected(item);
        }
    }

    // metodo modificar
    public void modify(MyItem item) {

        Intent intentModify = new Intent(AcCategory.this, AcModifyItem.class);
        Bundle mBundle = new Bundle();
        mBundle.putSerializable("item", (Serializable) item);
        intentModify.putExtras(mBundle);
        setResult(RESULT_OK, intentModify);
        startActivityForResult(intentModify, REQUEST_MODIFY);

    }

    public void deleteItem(int pos) {

        lista.remove(pos);
        adaptador.notifyDataSetChanged();

    }

}
