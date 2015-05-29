package com.closet.beatriz.closet;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.LinearLayout;

import java.util.ArrayList;

/**
 * Created by Beatriz on 22/04/2015.
 */
public class FmOutfit extends Fragment {

    View rootview;
    public static final String IMAGE_RESOURCE_ID = "iconResourceID";
    public static final String ITEM_NAME = "itemName";
    Button addOutfit;
    String outName = "";
    public final static int REQUEST_ADD_OUTFIT = 1;

    ItemSQLiteHelper usdbh;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootview = inflater.inflate(R.layout.fm_outfit, container, false);

        //DB
        usdbh = new ItemSQLiteHelper(getActivity(), "Closet",
                null, 1);

        //cargar los outfits de la DB
        loadOutfits();


        addOutfit = (Button) rootview.findViewById(R.id.btnAddOutfit);
        addOutfit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                newOutfit();

            }
        });

        return rootview;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
    }

    public void newOutfit() {
        final EditText input = new EditText(getActivity());

        new AlertDialog.Builder(getActivity())
                .setTitle(R.string.txt_add_outfit)
                .setMessage(R.string.txt_outfit_name)
                .setView(input)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        Editable value = input.getText();
                        outName = String.valueOf(input.getText());
                        if (outName.length() > 0) {
                            Intent intent = new Intent(getActivity(), AcSelecttItem.class);
                            startActivityForResult(intent, REQUEST_ADD_OUTFIT);

                            // Intent intent = new Intent(getActivity(), Grid3.class);
                            // startActivity(intent);
                        }
                        // Toast.makeText(getActivity(), "ADD OUTFIT", Toast.LENGTH_SHORT).show();
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // Do nothing.
            }
        }).show();

    }

    public void loadOutfits() {

        ArrayList<Outfit> lista;
        lista = usdbh.getOutfits();

        for (int i = 0; i < lista.size(); i++) {
            showOutfit(lista.get(i));
        }

    }

    public void showOutfit(Outfit outfit) {

        LinearLayout layout = (LinearLayout) rootview.findViewById(R.id.layoutOutfits);
        //outfit name
        TextView cat = new TextView(getActivity());

        cat.setText(outfit.getName());
        layout.addView(cat);
        //outfit items
        HorizontalScrollView scroll = new HorizontalScrollView(getActivity());
        layout.addView(scroll);
        LinearLayout items = new LinearLayout(getActivity());
        items.setOrientation(LinearLayout.HORIZONTAL);
        scroll.addView(items);

        ArrayList<Item> lista = outfit.getItemList();

        //add items
        for (int i = 0; i < lista.size(); i++) {

            ImageView image = new ImageView(getActivity());
            //  image.setMaxHeight(10);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(100, 100);
            image.setLayoutParams(layoutParams);
            //image.setLayoutParams().height = 20;
            // image.getLayoutParams().width = 10;
            Item item = lista.get(i);
            Bitmap b = StringToBitmap(item.getImage());
            image.setImageBitmap(b);
            items.addView(image);
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Bundle extras = null;
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_ADD_OUTFIT) {

                extras = data.getExtras();
                ArrayList<Item> lista = (ArrayList<Item>) extras.get("list");

                //********* creamos el objeto *********//
                Outfit outfit = new Outfit(outName, lista);

                //********* add to DB *********//
                usdbh.saveOutfit(outfit);

                //********* show on screen *********//
                showOutfit(outfit);

            }
        }
    }

    private Bitmap StringToBitmap(String image) {

        byte[] decodedString = null;
        decodedString = Base64.decode(image, Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        return bitmap;
    }

}