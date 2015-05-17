package com.closet.beatriz.closet;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created by Beatriz on 22/04/2015.
 */
public class FmEstadisticas extends Fragment {

    View rootview;
    ItemSQLiteHelper usdbh;

    //buttons
    Button btnvalue;
    Button btnCount;
    Button btnCountCategory;
    Button btnCountSeasons;
    Button btnCountColors;
    Button btnCountShops;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootview = inflater.inflate(R.layout.fm_estadisticas, container, false);
        return rootview;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);

        //base de datos
        usdbh = new ItemSQLiteHelper(getActivity(), "Closet",
                null, 1);

        displayPrices();
        //displayCount();
        displayCountCategory();
        displayCountSeason();
        displayColors();
        displayShops();

        btnvalue = (Button) rootview.findViewById(R.id.btnValue);
        btnvalue.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                showValue();
            }
        });

        btnCount = (Button) rootview.findViewById(R.id.btnCount);
        btnCount.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                showCount();
            }
        });

        btnCountCategory = (Button) rootview.findViewById(R.id.btnCountCategory);
        btnCountCategory.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                showCountCategory();
            }
        });
        btnCountSeasons = (Button) rootview.findViewById(R.id.btnCountSeasons);
        btnCountSeasons.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                showCountSeasons();
            }
        });


        btnCountColors = (Button) rootview.findViewById(R.id.btnCountColors);
        btnCountColors.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                showCountColors();
            }
        });

        btnCountShops = (Button) rootview.findViewById(R.id.btnCountShops);
        btnCountShops.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                showCountShops();
            }
        });
    }

    private void showValue() {

        LinearLayout precio = (LinearLayout) rootview.findViewById(R.id.layoutPrecio);
        Toast.makeText(getActivity(), "visibility: " + precio.getVisibility(), Toast.LENGTH_SHORT).show();

        if (precio.getVisibility() != LinearLayout.GONE) {
            precio.setVisibility(LinearLayout.GONE);
            // Toast.makeText(getActivity(), "hide", Toast.LENGTH_SHORT).show();
            //btnvalue.setBackground(R.drawable.arrow_down_float);
            btnvalue.setBackground(getResources().getDrawable(R.drawable.down_arrow));

        } else {
            precio.setVisibility(LinearLayout.VISIBLE);
            // Toast.makeText(getActivity(), "show", Toast.LENGTH_SHORT).show();
            btnvalue.setBackground(getResources().getDrawable(R.drawable.up_arrow));
        }

    }

    private void showCount() {

        LinearLayout count = (LinearLayout) rootview.findViewById(R.id.layoutCount);

        if (count.getVisibility() != LinearLayout.GONE) {
            count.setVisibility(LinearLayout.GONE);
            // Toast.makeText(getActivity(), "hide", Toast.LENGTH_SHORT).show();
            //btnvalue.setBackground(R.drawable.arrow_down_float);
            btnCount.setBackground(getResources().getDrawable(R.drawable.down_arrow));

        } else {
            count.setVisibility(LinearLayout.VISIBLE);
            // Toast.makeText(getActivity(), "show", Toast.LENGTH_SHORT).show();
            btnCount.setBackground(getResources().getDrawable(R.drawable.up_arrow));
        }

    }

    private void showCountCategory() {

        LinearLayout layout = (LinearLayout) rootview.findViewById(R.id.layoutCountCategory);

        if (layout.getVisibility() != LinearLayout.GONE) {
            layout.setVisibility(LinearLayout.GONE);
            // Toast.makeText(getActivity(), "hide", Toast.LENGTH_SHORT).show();
            //btnvalue.setBackground(R.drawable.arrow_down_float);
            btnCountCategory.setBackground(getResources().getDrawable(R.drawable.down_arrow));

        } else {
            layout.setVisibility(LinearLayout.VISIBLE);
            // Toast.makeText(getActivity(), "show", Toast.LENGTH_SHORT).show();
            btnCountCategory.setBackground(getResources().getDrawable(R.drawable.up_arrow));
        }

    }

    private void showCountSeasons() {

        LinearLayout layout = (LinearLayout) rootview.findViewById(R.id.layoutCountSeasons);

        if (layout.getVisibility() != LinearLayout.GONE) {
            layout.setVisibility(LinearLayout.GONE);
            // Toast.makeText(getActivity(), "hide", Toast.LENGTH_SHORT).show();
            //btnvalue.setBackground(R.drawable.arrow_down_float);
            btnCountSeasons.setBackground(getResources().getDrawable(R.drawable.down_arrow));

        } else {
            layout.setVisibility(LinearLayout.VISIBLE);
            // Toast.makeText(getActivity(), "show", Toast.LENGTH_SHORT).show();
            btnCountSeasons.setBackground(getResources().getDrawable(R.drawable.up_arrow));
        }

    }

    private void showCountColors() {

        LinearLayout layout = (LinearLayout) rootview.findViewById(R.id.layoutCountColors);

        if (layout.getVisibility() != LinearLayout.GONE) {
            layout.setVisibility(LinearLayout.GONE);
            //Toast.makeText(getActivity(), "hide", Toast.LENGTH_SHORT).show();
            //btnvalue.setBackground(R.drawable.arrow_down_float);
            btnCountColors.setBackground(getResources().getDrawable(R.drawable.down_arrow));

        } else {
            layout.setVisibility(LinearLayout.VISIBLE);
            //Toast.makeText(getActivity(), "show", Toast.LENGTH_SHORT).show();
            btnCountColors.setBackground(getResources().getDrawable(R.drawable.up_arrow));
        }

    }

    private void showCountShops() {

        LinearLayout layout = (LinearLayout) rootview.findViewById(R.id.layoutCountShops);

        if (layout.getVisibility() != LinearLayout.GONE) {
            layout.setVisibility(LinearLayout.GONE);
            //Toast.makeText(getActivity(), "hide", Toast.LENGTH_SHORT).show();
            //btnvalue.setBackground(R.drawable.arrow_down_float);
            btnCountShops.setBackground(getResources().getDrawable(R.drawable.down_arrow));

        } else {
            layout.setVisibility(LinearLayout.VISIBLE);
            //Toast.makeText(getActivity(), "show", Toast.LENGTH_SHORT).show();
            btnCountShops.setBackground(getResources().getDrawable(R.drawable.up_arrow));
        }

    }


    private void displayPrices() {

        LinearLayout layout = (LinearLayout) rootview.findViewById(R.id.layoutPrecio);

        String[] categoryName = getActivity().getResources().getStringArray(R.array.categoriesArrays);
        Float[] pricesArray = usdbh.priceStatistics(getActivity());
        float total = 0;


        int shopNo = categoryName.length;

        for (int i = 0; i < pricesArray.length; i++) {

            total += pricesArray[i];

            //añadimos el nombre
            TextView cat = new TextView(getActivity());
            cat.setText(categoryName[i] + ": " + pricesArray[i] + getActivity().getResources().getString(R.string.txt_currency));
            //add style
            // Log.e("TAG", "color to add:  " + colorName[i] + " : " + colorCount[i]);
            layout.addView(cat);
        }

        //total
        TextView txtTotal = (TextView) rootview.findViewById(R.id.txtTotalValue);

        txtTotal.setText(getActivity().getResources().getString(R.string.statistics_total) + String.valueOf(total) + getActivity().getResources().getString(R.string.txt_currency));

    }


    private void displayCountCategory() {

        LinearLayout layout = (LinearLayout) rootview.findViewById(R.id.layoutCountCategory);

        String[] categoryName = getActivity().getResources().getStringArray(R.array.categoriesArrays);
        int[] countArray = usdbh.countCategoryStatistics(getActivity());
        int total = 0;

        for (int i = 0; i < countArray.length; i++) {

            total += countArray[i];

            //añadimos el nombre
            TextView cat = new TextView(getActivity());
            cat.setText(categoryName[i] + ": " + countArray[i]);
            //add style
            // Log.e("TAG", "color to add:  " + colorName[i] + " : " + colorCount[i]);
            layout.addView(cat);
        }

        //total
        TextView txtTotal = (TextView) rootview.findViewById(R.id.txtTotalCount);

        txtTotal.setText(getActivity().getResources().getString(R.string.statistics_total) + String.valueOf(total));

    }

    private void displayCountSeason() {
        LinearLayout layout = (LinearLayout) rootview.findViewById(R.id.layoutCountSeasons);

        String[] seasonName = getActivity().getResources().getStringArray(R.array.seasonsArrays);
        int[] countArray = usdbh.countSeasonsStatistics(getActivity());
        float total = 0;


        int shopNo = seasonName.length;

        for (int i = 0; i < countArray.length; i++) {

            // total += countArray[i];

            //añadimos el nombre
            TextView cat = new TextView(getActivity());
            cat.setText(seasonName[i] + ": " + countArray[i]);
            //add style
            // Log.e("TAG", "color to add:  " + colorName[i] + " : " + colorCount[i]);
            layout.addView(cat);
        }

        //total
        //  TextView txtTotal = (TextView) rootview.findViewById(R.id.txtTotalValue);

        //   txtTotal.setText(getActivity().getResources().getString(R.string.statistics_total) + String.valueOf(total) + getActivity().getResources().getString(R.string.txt_currency));

    }


    private void displayColors() {

        LinearLayout layout = (LinearLayout) rootview.findViewById(R.id.layoutCountColors);

        String[] colorName = getActivity().getResources().getStringArray(R.array.colorArrays);
        int[] colorCount = usdbh.colorStatistics(getActivity());


        int colorNo = colorName.length;

        for (int i = 0; i < colorNo; i++) {

            //añadimos el nombre
            TextView color = new TextView(getActivity());
            color.setText(colorName[i] + ": " + colorCount[i]);
            //add style
            // Log.e("TAG", "color to add:  " + colorName[i] + " : " + colorCount[i]);
            layout.addView(color);
        }


    }

    private void displayShops() {

        LinearLayout layout = (LinearLayout) rootview.findViewById(R.id.layoutCountShops);

        String[] shopName = getActivity().getResources().getStringArray(R.array.shopsArrays);
        int[] shopCount = usdbh.shopStatistics(getActivity());


        int shopNo = shopName.length;

        for (int i = 0; i < shopNo; i++) {

            //añadimos el nombre
            TextView shop = new TextView(getActivity());
            shop.setText(shopName[i] + ": " + shopCount[i]);
            //add style
            // Log.e("TAG", "color to add:  " + colorName[i] + " : " + colorCount[i]);
            layout.addView(shop);
        }

    }

}
