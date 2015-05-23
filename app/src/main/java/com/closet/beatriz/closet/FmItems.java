package com.closet.beatriz.closet;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

/**
 * Created by Beatriz on 22/04/2015.
 */
public class FmItems extends Fragment {

    View rootview;
    // GridView camisetas;
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


    public static final String IMAGE_RESOURCE_ID = "iconResourceID";
    public static final String ITEM_NAME = "itemName";

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


        //inicialize buttons
        ShirtButtons();
        PantsButtons();
        UnderWearButtons();
        CoatsButtons();
        ShoesButtons();
        JumperButtons();
        PijamasButtons();
        DressButtons();
        AccessoriesButtons();


    }

    @Override
    public void onResume() {

        //cargarLista();

        super.onResume();
    }


    private void cargarLista() {

        //cargamos los ArrayList con información
        usdbh.cargarLista(getActivity(), ALShirts, ALPants, ALUnderWear, ALCoats, ALShoes, ALJumper, ALPijamas, ALDress, ALAccesories);

        //definimos un arrayList vacio y un LinearLAyout
        ArrayList<Item> lista = new ArrayList<Item>();
        LinearLayout layout = null;

        //emptyLayouts();

        //9 as categories
        for (int i = 0; i < 9; i++) {

            switch (i) {

                case 0:
                    layout = (LinearLayout) rootview.findViewById(R.id.linearCamisetas);
                    layout.setVisibility(LinearLayout.GONE);
                    lista = ALShirts;
                    break;

                case 1:
                    layout = (LinearLayout) rootview.findViewById(R.id.linearPantalones);
                    lista = ALPants;
                    break;
                case 2:
                    layout = (LinearLayout) rootview.findViewById(R.id.linearUnderWear);
                    lista = ALUnderWear;
                    break;
                case 3:
                    layout = (LinearLayout) rootview.findViewById(R.id.linearCoats);
                    lista = ALCoats;
                    break;
                case 4:
                    layout = (LinearLayout) rootview.findViewById(R.id.linearShoes);
                    lista = ALShoes;
                    break;
                case 5:
                    layout = (LinearLayout) rootview.findViewById(R.id.linearJumper);
                    lista = ALJumper;
                    break;
                case 6:
                    layout = (LinearLayout) rootview.findViewById(R.id.linearPijamas);
                    lista = ALPijamas;
                    break;
                case 7:
                    layout = (LinearLayout) rootview.findViewById(R.id.linearDress);
                    lista = ALDress;
                    break;
                case 8:
                    layout = (LinearLayout) rootview.findViewById(R.id.linearAccesories);
                    lista = ALAccesories;
                    break;
            }


            for (int j = 0; j < lista.size(); j++) {
                Item item = (Item) lista.get(j);
                // Log.e("TAG--", "valor de j" + String.valueOf(j));


                ImageView image = new ImageView(getActivity());
                Bitmap b = StringToBitmap(item.getImage());
                image.setImageBitmap(b);
                //image.setImageBitmap(i.getImage());
                // image.setMaxWidth(5);
                //image.setMaxHeight(70);
                //image.setClickable(true);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(150, 75);
                image.setLayoutParams(layoutParams);


                // image.setLayoutParams();
                // image.setBackgroundColor(R.color.red);


                //layout.addView(btnTag);
                layout.addView(image);

            }
        }


    }

    private void emptyLayouts() {

        LinearLayout layout = (LinearLayout) rootview.findViewById(R.id.linearCamisetas);
        layout.removeAllViews();

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
        intentAdd.putExtra("Category", cat);
        startActivity(intentAdd);


    }

    public void search(String cat) {


        //  Toast.makeText(getActivity(), "Cat->" + cat,  Toast.LENGTH_SHORT).show();

        Intent intentSearch = new Intent(getActivity(), AcSearch.class);
        intentSearch.putExtra("Category", cat);
        startActivity(intentSearch);

    }

    private Bitmap StringToBitmap(String image) {

        byte[] decodedString = null;
        decodedString = Base64.decode(image, Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        return bitmap;
    }

    private void ShirtButtons() {


        ImageButton btnCategory = (ImageButton) rootview.findViewById(R.id.imgCamisetas);
        btnCategory.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                category(getString(R.string.catShirts));
            }
        });

        ImageButton btnAdd = (ImageButton) rootview.findViewById(R.id.imgAddCamisetas);
        btnAdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                addItem(getString(R.string.catShirts));

            }
        });

        ImageButton btnSearch = (ImageButton) rootview.findViewById(R.id.imgSearchCamisetas);
        btnSearch.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                search(getString(R.string.catShirts));

            }
        });


    }

    private void PantsButtons() {

        ImageButton btnCategory = (ImageButton) rootview.findViewById(R.id.imgPantalones);
        btnCategory.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                category(getString(R.string.catPants));
            }
        });

        ImageButton btnAdd = (ImageButton) rootview.findViewById(R.id.imgAddPantalones);
        btnAdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                addItem(getString(R.string.catPants));

            }
        });

        ImageButton btnSearch = (ImageButton) rootview.findViewById(R.id.imgSearchPantalones);
        btnSearch.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                search(getString(R.string.catPants));

            }
        });


    }

    private void UnderWearButtons() {


        ImageButton btnCategory = (ImageButton) rootview.findViewById(R.id.imgUnderWar);
        btnCategory.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                category(getString(R.string.catUnderWear));
            }
        });

        ImageButton btnAdd = (ImageButton) rootview.findViewById(R.id.imgAddUnderWear);
        btnAdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                addItem(getString(R.string.catUnderWear));

            }
        });

        ImageButton btnSearch = (ImageButton) rootview.findViewById(R.id.imgSearchUnderWear);
        btnSearch.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                search(getString(R.string.catUnderWear));

            }
        });


    }

    private void CoatsButtons() {


        ImageButton btnCategory = (ImageButton) rootview.findViewById(R.id.imgCoats);
        btnCategory.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                category(getString(R.string.catCoats));
            }
        });

        ImageButton btnAdd = (ImageButton) rootview.findViewById(R.id.imgAddCoats);
        btnAdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                addItem(getString(R.string.catCoats));

            }
        });

        ImageButton btnSearch = (ImageButton) rootview.findViewById(R.id.imgSearchCoats);
        btnSearch.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                search(getString(R.string.catCoats));

            }
        });


    }

    private void ShoesButtons() {


        ImageButton btnCategory = (ImageButton) rootview.findViewById(R.id.imgShoes);
        btnCategory.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                category(getString(R.string.catShoes));
            }
        });

        ImageButton btnAdd = (ImageButton) rootview.findViewById(R.id.imgAddShoes);
        btnAdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                addItem(getString(R.string.catShoes));

            }
        });

        ImageButton btnSearch = (ImageButton) rootview.findViewById(R.id.imgSearchShoes);
        btnSearch.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                search(getString(R.string.catShoes));

            }
        });

    }

    private void JumperButtons() {


        ImageButton btnCategory = (ImageButton) rootview.findViewById(R.id.imgJumper);
        btnCategory.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                category(getString(R.string.catJumper));
            }
        });

        ImageButton btnAdd = (ImageButton) rootview.findViewById(R.id.imgAddJumper);
        btnAdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                addItem(getString(R.string.catJumper));

            }
        });

        ImageButton btnSearch = (ImageButton) rootview.findViewById(R.id.imgSearchJumper);
        btnSearch.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                search(getString(R.string.catJumper));

            }
        });

    }

    private void PijamasButtons() {


        ImageButton btnCategory = (ImageButton) rootview.findViewById(R.id.imgPijamas);
        btnCategory.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                category(getString(R.string.catPijamas));
            }
        });

        ImageButton btnAdd = (ImageButton) rootview.findViewById(R.id.imgAddPijamas);
        btnAdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                addItem(getString(R.string.catPijamas));

            }
        });

        ImageButton btnSearch = (ImageButton) rootview.findViewById(R.id.imgSearchPijamas);
        btnSearch.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                search(getString(R.string.catPijamas));

            }
        });

    }

    private void DressButtons() {


        ImageButton btnCategory = (ImageButton) rootview.findViewById(R.id.imgDress);
        btnCategory.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                category(getString(R.string.catDress));
            }
        });

        ImageButton btnAdd = (ImageButton) rootview.findViewById(R.id.imgAddDress);
        btnAdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                addItem(getString(R.string.catDress));

            }
        });

        ImageButton btnSearch = (ImageButton) rootview.findViewById(R.id.imgSearchDress);
        btnSearch.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                search(getString(R.string.catDress));

            }
        });

    }

    private void AccessoriesButtons() {


        ImageButton btnCategory = (ImageButton) rootview.findViewById(R.id.imgAccesories);
        btnCategory.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                category(getString(R.string.catAccesories));
            }
        });

        ImageButton btnAdd = (ImageButton) rootview.findViewById(R.id.imgAddAccesories);
        btnAdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                addItem(getString(R.string.catAccesories));

            }
        });

        ImageButton btnSearch = (ImageButton) rootview.findViewById(R.id.imgSearchAccesories);
        btnSearch.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                search(getString(R.string.catAccesories));

            }
        });

    }


}
