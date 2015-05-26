package com.closet.beatriz.closet;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Beatriz on 22/04/2015.
 */
public class FmItems extends Fragment {

    View rootview;
    // GridView camisetas;

    //count textViews
    TextView shirtCount;
    TextView pantsCount;
    TextView underWearCount;
    TextView coatsCount;
    TextView shoesCount;
    TextView jumperCount;
    TextView pijamasCount;
    TextView dressCount;
    TextView accessoriesCount;


    //arraylist
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

    public final static int REQUEST_ADD = 1;
    public final static int REQUEST_CATEGORY = 2;

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

        initializeTextView();

        // loadImages(lista);
        // cargarLista();
        cargarListas();

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

    private void initializeTextView() {

        shirtCount = (TextView) rootview.findViewById(R.id.txtShirtCount);
        pantsCount = (TextView) rootview.findViewById(R.id.txtPantsCount);
        underWearCount = (TextView) rootview.findViewById(R.id.txtUnderWearCount);
        coatsCount = (TextView) rootview.findViewById(R.id.txtCoatsCount);
        shoesCount = (TextView) rootview.findViewById(R.id.txtShoesCount);
        jumperCount = (TextView) rootview.findViewById(R.id.txtJumperCount);
        pijamasCount = (TextView) rootview.findViewById(R.id.txtPijamasCount);
        dressCount = (TextView) rootview.findViewById(R.id.txtDressCount);
        accessoriesCount = (TextView) rootview.findViewById(R.id.txtAccessoriesCount);

    }

    private void addShirt(Item item) {

        // Log.e("TAG--", "valor de j" + String.valueOf(j));

        LinearLayout layout = (LinearLayout) rootview.findViewById(R.id.linearCamisetas);

        ImageView image = new ImageView(getActivity());
        Bitmap b = StringToBitmap(item.getImage());
        image.setImageBitmap(b);
        //image.setImageBitmap(i.getImage());
        // image.setMaxWidth(5);
        //image.setMaxHeight(70);
        //image.setClickable(true);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(150, 75);
        image.setLayoutParams(layoutParams);


        layout.addView(image);


        layout.getChildCount();
        Log.e("TAG", "child count: " + layout.getChildCount());
        //update count
        shirtCount.setText("(" + layout.getChildCount() + ")");

    }

    private void addPants(Item item) {

        // Log.e("TAG--", "valor de j" + String.valueOf(j));

        LinearLayout layout = (LinearLayout) rootview.findViewById(R.id.linearPantalones);

        ImageView image = new ImageView(getActivity());
        Bitmap b = StringToBitmap(item.getImage());
        image.setImageBitmap(b);
        //image.setImageBitmap(i.getImage());
        // image.setMaxWidth(5);
        //image.setMaxHeight(70);
        //image.setClickable(true);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(150, 75);
        image.setLayoutParams(layoutParams);


        layout.addView(image);

        //update count
        pantsCount.setText("(" + layout.getChildCount() + ")");

    }

    private void addUnderWear(Item item) {

        // Log.e("TAG--", "valor de j" + String.valueOf(j));

        LinearLayout layout = (LinearLayout) rootview.findViewById(R.id.linearUnderWear);

        ImageView image = new ImageView(getActivity());
        Bitmap b = StringToBitmap(item.getImage());
        image.setImageBitmap(b);
        //image.setImageBitmap(i.getImage());
        // image.setMaxWidth(5);
        //image.setMaxHeight(70);
        //image.setClickable(true);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(150, 75);
        image.setLayoutParams(layoutParams);


        layout.addView(image);

        //update count
        underWearCount.setText("(" + layout.getChildCount() + ")");

    }

    private void addCoats(Item item) {

        // Log.e("TAG--", "valor de j" + String.valueOf(j));

        LinearLayout layout = (LinearLayout) rootview.findViewById(R.id.linearCoats);

        ImageView image = new ImageView(getActivity());
        Bitmap b = StringToBitmap(item.getImage());
        image.setImageBitmap(b);
        //image.setImageBitmap(i.getImage());
        // image.setMaxWidth(5);
        //image.setMaxHeight(70);
        //image.setClickable(true);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(150, 75);
        image.setLayoutParams(layoutParams);


        layout.addView(image);

        //update count
        coatsCount.setText("(" + layout.getChildCount() + ")");

    }

    private void addShoes(Item item) {

        // Log.e("TAG--", "valor de j" + String.valueOf(j));

        LinearLayout layout = (LinearLayout) rootview.findViewById(R.id.linearShoes);

        ImageView image = new ImageView(getActivity());
        Bitmap b = StringToBitmap(item.getImage());
        image.setImageBitmap(b);
        //image.setImageBitmap(i.getImage());
        // image.setMaxWidth(5);
        //image.setMaxHeight(70);
        //image.setClickable(true);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(150, 75);
        image.setLayoutParams(layoutParams);


        layout.addView(image);

        //update count
        shoesCount.setText("(" + layout.getChildCount() + ")");

    }

    private void addJumper(Item item) {

        // Log.e("TAG--", "valor de j" + String.valueOf(j));

        LinearLayout layout = (LinearLayout) rootview.findViewById(R.id.linearJumper);

        ImageView image = new ImageView(getActivity());
        Bitmap b = StringToBitmap(item.getImage());
        image.setImageBitmap(b);
        //image.setImageBitmap(i.getImage());
        // image.setMaxWidth(5);
        //image.setMaxHeight(70);
        //image.setClickable(true);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(150, 75);
        image.setLayoutParams(layoutParams);


        layout.addView(image);

        //update count
        jumperCount.setText("(" + layout.getChildCount() + ")");

    }

    private void addPijamas(Item item) {

        // Log.e("TAG--", "valor de j" + String.valueOf(j));

        LinearLayout layout = (LinearLayout) rootview.findViewById(R.id.linearPijamas);

        ImageView image = new ImageView(getActivity());
        Bitmap b = StringToBitmap(item.getImage());
        image.setImageBitmap(b);
        //image.setImageBitmap(i.getImage());
        // image.setMaxWidth(5);
        //image.setMaxHeight(70);
        //image.setClickable(true);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(150, 75);
        image.setLayoutParams(layoutParams);


        layout.addView(image);

        //update count
        pijamasCount.setText("(" + layout.getChildCount() + ")");

    }

    private void addDress(Item item) {

        // Log.e("TAG--", "valor de j" + String.valueOf(j));

        LinearLayout layout = (LinearLayout) rootview.findViewById(R.id.linearDress);

        ImageView image = new ImageView(getActivity());
        Bitmap b = StringToBitmap(item.getImage());
        image.setImageBitmap(b);
        //image.setImageBitmap(i.getImage());
        // image.setMaxWidth(5);
        //image.setMaxHeight(70);
        //image.setClickable(true);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(150, 75);
        image.setLayoutParams(layoutParams);


        layout.addView(image);

        //update count
        dressCount.setText("(" + layout.getChildCount() + ")");

    }


    private void addAccessories(Item item) {

        // Log.e("TAG--", "valor de j" + String.valueOf(j));

        LinearLayout layout = (LinearLayout) rootview.findViewById(R.id.linearAccesories);

        ImageView image = new ImageView(getActivity());
        Bitmap b = StringToBitmap(item.getImage());
        image.setImageBitmap(b);
        //image.setImageBitmap(i.getImage());
        // image.setMaxWidth(5);
        //image.setMaxHeight(70);
        //image.setClickable(true);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(150, 75);
        image.setLayoutParams(layoutParams);


        layout.addView(image);

        //update count
        accessoriesCount.setText("(" + layout.getChildCount() + ")");

    }

    private void cargarListas() {

        loadShirts();
        loadPants();
        loadUnderWear();
        loadCoats();
        loadShoes();
        loadJumpers();
        loadPijamas();
        loadDresses();
        loadAccessories();


    }

    private void loadAccessories() {
        ArrayList<Item> lista;
        //*********** accessoriesCount *********//
        lista = usdbh.getAcessories(getActivity());
        //load all items
        for (int i = 0; i < lista.size(); i++) {
            Item item = (Item) lista.get(i);
            addAccessories(item);
        }
        //counter
        accessoriesCount.setText("(" + lista.size() + ")");
    }

    private void loadDresses() {
        ArrayList<Item> lista;
        //*********** dressCount *********//
        lista = usdbh.getDress(getActivity());
        //load all items
        for (int i = 0; i < lista.size(); i++) {
            Item item = (Item) lista.get(i);
            addDress(item);
        }
        //counter
        dressCount.setText("(" + lista.size() + ")");
    }

    private void loadPijamas() {
        ArrayList<Item> lista;
        //*********** pijamasCount *********//
        lista = usdbh.getPijamas(getActivity());
        //load all items
        for (int i = 0; i < lista.size(); i++) {
            Item item = (Item) lista.get(i);
            addPijamas(item);
        }
        //counter
        pijamasCount.setText("(" + lista.size() + ")");
    }

    private void loadJumpers() {
        ArrayList<Item> lista;
        //*********** jumperCount *********//
        lista = usdbh.getJumper(getActivity());
        //load all items
        for (int i = 0; i < lista.size(); i++) {
            Item item = (Item) lista.get(i);
            addJumper(item);
        }
        //counter
        jumperCount.setText("(" + lista.size() + ")");
    }

    private void loadShoes() {
        ArrayList<Item> lista;
        //*********** shoesCount *********//
        lista = usdbh.getShoes(getActivity());
        //load all items
        for (int i = 0; i < lista.size(); i++) {
            Item item = (Item) lista.get(i);
            addShoes(item);
        }
        //counter
        shoesCount.setText("(" + lista.size() + ")");
    }

    private void loadCoats() {
        ArrayList<Item> lista;
        //*********** coatsCount *********//
        lista = usdbh.getCoats(getActivity());
        //load all items
        for (int i = 0; i < lista.size(); i++) {
            Item item = (Item) lista.get(i);
            addCoats(item);
        }
        //counter
        coatsCount.setText("(" + lista.size() + ")");
    }

    private void loadUnderWear() {
        ArrayList<Item> lista;
        //*********** underWear *********//
        lista = usdbh.getUnderWear(getActivity());
        //load all items
        for (int i = 0; i < lista.size(); i++) {
            Item item = (Item) lista.get(i);
            addUnderWear(item);
        }
        //counter
        underWearCount.setText("(" + lista.size() + ")");
    }

    private void loadPants() {
        ArrayList<Item> lista;
        //*********** pants *********//
        lista = usdbh.getPants(getActivity());
        //load all items
        for (int i = 0; i < lista.size(); i++) {
            Item item = (Item) lista.get(i);
            addPants(item);
        }
        //counter
        pantsCount.setText("(" + lista.size() + ")");
    }

    private void loadShirts() {

        ArrayList<Item> lista;

        //*********** shirts *********//
        lista = usdbh.getShirts(getActivity());
        //load all items
        for (int i = 0; i < lista.size(); i++) {
            Item item = (Item) lista.get(i);
            addShirt(item);
        }
        //counter
        shirtCount.setText("(" + lista.size() + ")");

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
        startActivityForResult(intentCat, REQUEST_CATEGORY);

    }

    public void addItem(String cat) {

        //  Toast.makeText(getActivity(), "Cat->" + cat, Toast.LENGTH_SHORT).show();

        Intent intentAdd = new Intent(getActivity(), AcAddItem.class);
        intentAdd.putExtra("Category", cat);
        startActivityForResult(intentAdd, REQUEST_ADD);

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


        ImageButton btnCategory = (ImageButton) rootview.findViewById(R.id.imgUnderWear);
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // super.onActivityResult(requestCode, resultCode, data);
        getActivity();
        Log.e("TAG-------FmItems", "onActivityResult");
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_ADD) {

                Item i = (Item) data.getSerializableExtra("item");

                addItemToCategory(i);

            } else if (requestCode == REQUEST_CATEGORY) {

                String cat = data.getStringExtra("Category");

                updateLayout(cat);
                Toast.makeText(getActivity(), "on activity result category", Toast.LENGTH_SHORT).show();

            }
        }
    }

    private void updateLayout(String cat) {

        if (cat.equals(rootview.getResources().getString(R.string.catShirts))) {
            LinearLayout layout = (LinearLayout) rootview.findViewById(R.id.linearCamisetas);
            layout.removeAllViews();
            loadShirts();

        } else if (cat.equals(rootview.getResources().getString(R.string.catPants))) {

            LinearLayout layout = (LinearLayout) rootview.findViewById(R.id.linearPantalones);
            layout.removeAllViews();
            loadPants();

        } else if (cat.equals(rootview.getResources().getString(R.string.catUnderWear))) {

            LinearLayout layout = (LinearLayout) rootview.findViewById(R.id.linearUnderWear);
            layout.removeAllViews();
            loadUnderWear();

        } else if (cat.equals(rootview.getResources().getString(R.string.catCoats))) {

            LinearLayout layout = (LinearLayout) rootview.findViewById(R.id.linearCoats);
            layout.removeAllViews();
            loadCoats();

        } else if (cat.equals(rootview.getResources().getString(R.string.catShoes))) {

            LinearLayout layout = (LinearLayout) rootview.findViewById(R.id.linearShoes);
            layout.removeAllViews();
            loadShoes();

        } else if (cat.equals(rootview.getResources().getString(R.string.catJumper))) {

            LinearLayout layout = (LinearLayout) rootview.findViewById(R.id.linearJumper);
            layout.removeAllViews();
            loadJumpers();

        } else if (cat.equals(rootview.getResources().getString(R.string.catPijamas))) {

            LinearLayout layout = (LinearLayout) rootview.findViewById(R.id.linearPijamas);
            layout.removeAllViews();
            loadPijamas();

        } else if (cat.equals(rootview.getResources().getString(R.string.catDress))) {

            LinearLayout layout = (LinearLayout) rootview.findViewById(R.id.linearDress);
            layout.removeAllViews();
            loadDresses();

        } else if (cat.equals(rootview.getResources().getString(R.string.catAccesories))) {

            LinearLayout layout = (LinearLayout) rootview.findViewById(R.id.linearAccesories);
            layout.removeAllViews();
            loadAccessories();

        }

    }

    private void addItemToCategory(Item i) {

        // cargamos la informavión en la lista
        if (i.getCategory().equals(rootview.getResources().getString(R.string.catShirts))) {

            addShirt(i);

        } else if (i.getCategory().equals(rootview.getResources().getString(R.string.catPants))) {

            addPants(i);

        } else if (i.getCategory().equals(rootview.getResources().getString(R.string.catUnderWear))) {

            addUnderWear(i);

        } else if (i.getCategory().equals(rootview.getResources().getString(R.string.catCoats))) {

            addCoats(i);

        } else if (i.getCategory().equals(rootview.getResources().getString(R.string.catShoes))) {

            addShoes(i);

        } else if (i.getCategory().equals(rootview.getResources().getString(R.string.catJumper))) {

            addJumper(i);

        } else if (i.getCategory().equals(rootview.getResources().getString(R.string.catPijamas))) {

            addPijamas(i);

        } else if (i.getCategory().equals(rootview.getResources().getString(R.string.catDress))) {

            addDress(i);

        } else if (i.getCategory().equals(rootview.getResources().getString(R.string.catAccesories))) {

            addAccessories(i);
        }

    }


}
