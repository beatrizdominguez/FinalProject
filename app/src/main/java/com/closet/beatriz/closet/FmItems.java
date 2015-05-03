package com.closet.beatriz.closet;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Beatriz on 22/04/2015.
 */
public class FmItems extends Fragment {

    View rootview;
    // GridView camisetas;
    private ArrayList<Item> ALCamisetas = new ArrayList<Item>();
    private ArrayList<Item> ALPantalones = new ArrayList<Item>();
    private ArrayList<Item> ALRopaInterior = new ArrayList<Item>();
    private ArrayList<Item> ALAbrigos = new ArrayList<Item>();
    String category;
    ItemSQLiteHelper usdbh;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootview = inflater.inflate(R.layout.fm_items, container, false);
        return rootview;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);

        //base de datos
        usdbh = new ItemSQLiteHelper(getActivity(), "Closet",
                null, 1);

        // loadImages(lista);
        cargarLista();

        ImageButton btnCat = (ImageButton) rootview.findViewById(R.id.imgCamisetas);
        btnCat.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // Toast.makeText(getActivity(), "Categoría", Toast.LENGTH_SHORT).show();

                //Log.e("TAG-----------item", String.valueOf(R.string.txt_shirts));
                /// -------------whould be string from @string
                category(getString(R.string.catShirts));
            }
        });

        ImageButton btnAdd = (ImageButton) rootview.findViewById(R.id.imgAddCamisetas);
        btnAdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // Toast.makeText(getActivity(), "add camisetas", Toast.LENGTH_SHORT).show();

                addItem(getString(R.string.catShirts));

            }
        });

        ImageButton btnSearch = (ImageButton) rootview.findViewById(R.id.imgSearchCamisetas);
        btnSearch.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //Toast.makeText(getActivity(), "searcth camisetas", Toast.LENGTH_SHORT).show();

                search(getString(R.string.catShirts));

            }
        });

        ImageButton btnCatPantalones = (ImageButton) rootview.findViewById(R.id.imgPantalones);
        btnCatPantalones.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //  Toast.makeText(getActivity(), "Categoría",  Toast.LENGTH_SHORT).show();

                // Log.e("TAG-----------item", String.valueOf(R.string.txt_shirts));
                /// -------------whould be string from @string
                category(getString(R.string.catPants));
            }
        });


    }

    private void cargarLista() {

        //cargamos los ArrayList con información
        usdbh.cargarLista(ALCamisetas, ALPantalones, ALRopaInterior, ALAbrigos);

        //definimos un arrayList vacio y un LinearLAyout
        ArrayList<Item> lista = new ArrayList<Item>();
        LinearLayout layout = null;


        for (int i = 0; i < 2; i++) {

            switch (i) {

                case 0:
                    layout = (LinearLayout) rootview.findViewById(R.id.linearCamisetas);
                    lista = ALCamisetas;
                    break;

                case 1:
                    layout = (LinearLayout) rootview.findViewById(R.id.linearPantalones);
                    lista = ALPantalones;
                    break;
            }


            for (int j = 0; j < lista.size(); j++) {
                Item item = (Item) lista.get(j);
                // Log.e("TAG--", "valor de j" + String.valueOf(j));

                Button btnTag = new Button(getActivity());
                btnTag.setText(item.getDescription());

/*
                ImageView image = new ImageView(getActivity());
                Bitmap b = BitmapFactory.decodeResource(getActivity().getResources(), R.drawable.no_image);
                image.setImageBitmap(b);
                //image.setImageBitmap(i.getImage());
                image.setMaxWidth(5);
                image.setMaxHeight(70);
                image.setClickable(true);*/

                layout.addView(btnTag);
               // layout.addView(image);

            }
        }


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

    }*/

    public void category(String cat) {

        //Toast.makeText(getActivity(), "Cat->" + cat,  Toast.LENGTH_SHORT).show();

        Intent intentCat = new Intent(getActivity(), AcCategory.class);
        intentCat.putExtra("Category", cat);
        startActivity(intentCat);

    }

    public void addItem(String cat) {


        //  Toast.makeText(getActivity(), "Cat->" + cat, Toast.LENGTH_SHORT).show();

        Intent intentAdd = new Intent(getActivity(), AcAddItem.class);
        //intent.putExtra("Category", cat);
        startActivity(intentAdd);


    }

    public void search(String cat) {


        //  Toast.makeText(getActivity(), "Cat->" + cat,  Toast.LENGTH_SHORT).show();

        Intent intentSearch = new Intent(getActivity(), AcSearch.class);
        intentSearch.putExtra("Category", cat);
        //intentSearch.putParcelableArrayListExtra()
        startActivity(intentSearch);

    }
}
