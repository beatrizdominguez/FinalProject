package com.closet.beatriz.closet;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Beatriz on 22/04/2015.
 */
public class FmItems extends Fragment {

    View rootview;
    GridView camisetas;
    AdapterItem adaptador;
    private ArrayList<Item> lista = new ArrayList<Item>();
    String category;


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

        // creamos el adaptador
        adaptador = new AdapterItem(getActivity().getBaseContext(), lista);
        // creamos el adaptador
        camisetas = (GridView) rootview.findViewById(R.id.gridViewCamisetas);
        // asociar menu contextual
        registerForContextMenu(camisetas);
        camisetas.setAdapter(adaptador);

        Log.e("TAG", "before loadImages()");
        loadImages(lista);


        ImageButton btnCat = (ImageButton) rootview.findViewById(R.id.imgCamisetas);
        btnCat.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Toast.makeText(getActivity(), "Categoría",
                        Toast.LENGTH_SHORT).show();

                Log.e("TAG-----------item",  String.valueOf(R.string.txt_shirts));
                /// -------------whould be string from @string
                category("Camisetas");
            }
        });

        ImageButton btnAdd = (ImageButton) rootview.findViewById(R.id.imgAddCamisetas);
        btnAdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Toast.makeText(getActivity(), "add camisetas",
                        Toast.LENGTH_SHORT).show();

                addItem("camisetas");

            }
        });

        ImageButton btnSearch = (ImageButton) rootview.findViewById(R.id.imgSearchCamisetas);
        btnSearch.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Toast.makeText(getActivity(), "searcth camisetas",
                        Toast.LENGTH_SHORT).show();

                search("camisetas");

            }
        });
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

    }

    public void category(String cat) {

        Toast.makeText(getActivity(), "Cat->" + cat,
                Toast.LENGTH_SHORT).show();

        Intent intentCat = new Intent(getActivity(), AcCategory.class);
        intentCat.putExtra("Category", cat);
        startActivity(intentCat);

    }

    public void addItem(String cat) {


        Toast.makeText(getActivity(), "Cat->" + cat,
                Toast.LENGTH_SHORT).show();

        Intent intentAdd = new Intent(getActivity(), AcAddItem.class);

        //intent.putExtra("Category", cat);
        startActivity(intentAdd);


    }

    public void search(String cat) {


        Toast.makeText(getActivity(), "Cat->" + cat,
                Toast.LENGTH_SHORT).show();

        Intent intentSearch = new Intent(getActivity(), AcSearch.class);

        //intent.putExtra("Category", cat);
        startActivity(intentSearch);

    }
}