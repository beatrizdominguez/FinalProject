package com.closet.beatriz.closet;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.achartengine.GraphicalView;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;


/**
 * Created by Beatriz on 22/04/2015.
 */
public class FmEstadisticas extends Fragment {

    View rootview;
    SQLiteHelper usdbh;

    //buttons
    Button btnvalue;
    Button btnCount;
    Button btnCountCategory;
    Button btnCountSeasons;
    Button btnCountColors;
    Button btnCountShops;
    Button btnColors;
    Button btnShops;

    String[] colorName;
    int[] colorCount;

    String[] shopName;
    int[] shopCount;

    int total = 0;
    public static final String IMAGE_RESOURCE_ID = "iconResourceID";
    public static final String ITEM_NAME = "itemName";

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
        usdbh = new SQLiteHelper(getActivity(), "Closet",
                null, 1);

        displayPrices();
        displayCountCategory();
        displayCountSeason();
        displayCountColors();
        displayCountShops();
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

        btnColors = (Button) rootview.findViewById(R.id.btnColor);
        btnColors.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                showColors();

            }
        });

        btnShops = (Button) rootview.findViewById(R.id.btnShops);
        btnShops.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                showShops();
            }
        });

        Button btnColorGraph = (Button) rootview.findViewById(R.id.btnGraphColor);
        btnColorGraph.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //   String[] colorName = getActivity().getResources().getStringArray(R.array.colorArrays);
                //  int[] colorCount = usdbh.colorStatistics(getActivity());


                Intent intentCat = new Intent(getActivity(), AcPieChart.class);
                intentCat.putExtra("title", R.string.statistics_colors);
                intentCat.putExtra("name", colorName);
                intentCat.putExtra("value", colorCount);
                startActivity(intentCat);
            }
        });

        Button btnShopGraph = (Button) rootview.findViewById(R.id.btnGraphShop);
        btnShopGraph.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                Intent intentCat = new Intent(getActivity(), AcPieChart.class);
                intentCat.putExtra("title", R.string.statistics_shops);
                intentCat.putExtra("name", shopName);
                intentCat.putExtra("value", shopCount);
                startActivity(intentCat);
            }
        });

    }

    //show or hide value layout and information
    private void showValue() {

        LinearLayout precio = (LinearLayout) rootview.findViewById(R.id.layoutPrecio);
        // Toast.makeText(getActivity(), "visibility: " + precio.getVisibility(), Toast.LENGTH_SHORT).show();

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

    //show or hide count layout and information
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

    //show or hide count by category layout and information
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

    //show or hide count by seasons layout and information
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

    //show or hide count by colors layout and information
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

    //show or hide count by shops layout and information
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

    //show or hide colors layout and information
    private void showColors() {

        LinearLayout layout = (LinearLayout) rootview.findViewById(R.id.layoutColors);

        if (layout.getVisibility() != LinearLayout.GONE) {
            layout.setVisibility(LinearLayout.GONE);
            //Toast.makeText(getActivity(), "hide", Toast.LENGTH_SHORT).show();
            //btnvalue.setBackground(R.drawable.arrow_down_float);
            btnColors.setBackground(getResources().getDrawable(R.drawable.down_arrow));

        } else {
            layout.setVisibility(LinearLayout.VISIBLE);
            //Toast.makeText(getActivity(), "show", Toast.LENGTH_SHORT).show();
            btnColors.setBackground(getResources().getDrawable(R.drawable.up_arrow));
        }

    }

    //show or hide count by shops layout and information
    private void showShops() {

        LinearLayout layout = (LinearLayout) rootview.findViewById(R.id.layoutShops);

        if (layout.getVisibility() != LinearLayout.GONE) {
            layout.setVisibility(LinearLayout.GONE);
            //Toast.makeText(getActivity(), "hide", Toast.LENGTH_SHORT).show();
            //btnvalue.setBackground(R.drawable.arrow_down_float);
            btnShops.setBackground(getResources().getDrawable(R.drawable.down_arrow));

        } else {
            layout.setVisibility(LinearLayout.VISIBLE);
            //Toast.makeText(getActivity(), "show", Toast.LENGTH_SHORT).show();
            btnShops.setBackground(getResources().getDrawable(R.drawable.up_arrow));
        }

    }

    //display the price information
    private void displayPrices() {

        LinearLayout layout = (LinearLayout) rootview.findViewById(R.id.layoutPrecio);

        String[] categoryName = getActivity().getResources().getStringArray(R.array.categoriesArrays);
        Float[] pricesArray = usdbh.priceStatistics(getActivity());
        float total = 0;


        // int shopNo = categoryName.length;

        for (int i = 0; i < pricesArray.length; i++) {

            total += pricesArray[i];

            //añadimos el nombre
            TextView cat = new TextView(getActivity());
            cat.setTextAppearance(getActivity().getApplicationContext(), R.style.text_color);
            cat.setText(categoryName[i] + ": " + pricesArray[i] + getActivity().getResources().getString(R.string.txt_currency));
            //add style
            // Log.e("TAG", "color to add:  " + colorName[i] + " : " + colorCount[i]);
            layout.addView(cat);
        }

        //total
        TextView txtTotal = (TextView) rootview.findViewById(R.id.txtTotalValue);

        txtTotal.setText(getActivity().getResources().getString(R.string.statistics_total) + String.valueOf(total) + getActivity().getResources().getString(R.string.txt_currency));

    }

    //display the count by category information
    private void displayCountCategory() {

        LinearLayout layout = (LinearLayout) rootview.findViewById(R.id.layoutCountCategory);

        String[] categoryName = getActivity().getResources().getStringArray(R.array.categoriesArrays);
        int[] countArray = usdbh.countCategoryStatistics(getActivity());


        for (int i = 0; i < countArray.length; i++) {

            total += countArray[i];

            //añadimos el nombre
            TextView cat = new TextView(getActivity());
            cat.setText(categoryName[i] + ": " + countArray[i]);
            cat.setTextAppearance(getActivity().getApplicationContext(), R.style.text_color);
            //add style
            // Log.e("TAG", "color to add:  " + colorName[i] + " : " + colorCount[i]);
            layout.addView(cat);
        }

        //total
        TextView txtTotal = (TextView) rootview.findViewById(R.id.txtTotalCount);
        txtTotal.setText(getActivity().getResources().getString(R.string.statistics_total) + String.valueOf(total));

    }

    //display the count by season information
    private void displayCountSeason() {
        LinearLayout layout = (LinearLayout) rootview.findViewById(R.id.layoutCountSeasons);

        String[] seasonName = getActivity().getResources().getStringArray(R.array.seasonsArrays);
        int[] countArray = usdbh.countSeasonsStatistics(getActivity());
        float total = 0;


        //  int shopNo = seasonName.length;

        for (int i = 0; i < countArray.length; i++) {

            // total += countArray[i];

            //añadimos el nombre
            TextView cat = new TextView(getActivity());
            cat.setTextAppearance(getActivity().getApplicationContext(), R.style.text_color);
            cat.setText(seasonName[i] + ": " + countArray[i]);
            //add style
            // Log.e("TAG", "color to add:  " + colorName[i] + " : " + colorCount[i]);
            layout.addView(cat);
        }

        //total
        //  TextView txtTotal = (TextView) rootview.findViewById(R.id.txtTotalValue);

        //   txtTotal.setText(getActivity().getResources().getString(R.string.statistics_total) + String.valueOf(total) + getActivity().getResources().getString(R.string.txt_currency));

    }

    //display the count by color information
    private void displayCountColors() {

        LinearLayout layout = (LinearLayout) rootview.findViewById(R.id.layoutCountColors);

        String[] colorName = getActivity().getResources().getStringArray(R.array.colorArrays);
        int[] colorCount = usdbh.colorStatistics(getActivity());


        // int colorNo = colorName.length;

        for (int i = 0; i < colorName.length; i++) {

            //añadimos el nombre
            TextView color = new TextView(getActivity());
            color.setTextAppearance(getActivity().getApplicationContext(), R.style.text_color);
            color.setText(colorName[i] + ": " + colorCount[i]);
            //add style
            // Log.e("TAG", "color to add:  " + colorName[i] + " : " + colorCount[i]);
            layout.addView(color);
        }


    }

    //display the count by shop information
    private void displayCountShops() {

        LinearLayout layout = (LinearLayout) rootview.findViewById(R.id.layoutCountShops);

        String[] shopName = getActivity().getResources().getStringArray(R.array.shopsArrays);
        int[] shopCount = usdbh.shopStatistics(getActivity());


        // int shopNo = shopName.length;

        for (int i = 0; i < shopName.length; i++) {

            //añadimos el nombre
            TextView shop = new TextView(getActivity());
            shop.setTextAppearance(getActivity().getApplicationContext(), R.style.text_color);
            shop.setText(shopName[i] + ": " + shopCount[i]);
            //add style
            // Log.e("TAG", "color to add:  " + colorName[i] + " : " + colorCount[i]);
            layout.addView(shop);
        }

    }

    //display the color information
    private void displayColors() {

        LinearLayout layout = (LinearLayout) rootview.findViewById(R.id.layoutColors);

        colorName = getActivity().getResources().getStringArray(R.array.colorArrays);
        colorCount = usdbh.colorStatistics(getActivity());


        int colorNo = colorName.length;

        for (int i = 0; i < colorNo; i++) {

            if (colorCount[i] != 0) {

                Log.e("TAG", "count" + colorCount[i]);
                Log.e("TAG", "total---" + total);
                int cCount = colorCount[i];
                Log.e("TAG", "color count: " + cCount);


                float percentage = (float) colorCount[i] / total * 100;

                //añadimos el nombre
                TextView color = new TextView(getActivity());
                color.setTextAppearance(getActivity().getApplicationContext(), R.style.text_color);
                color.setText(colorName[i] + ": " + Math.abs(percentage) + "%");
                //add style
                // Log.e("TAG", "color to add:  " + colorName[i] + " : " + colorCount[i]);
                layout.addView(color);
            }

        }


    }

    //display the shop information
    private void displayShops() {

        LinearLayout layout = (LinearLayout) rootview.findViewById(R.id.layoutShops);

        shopName = getActivity().getResources().getStringArray(R.array.shopsArrays);
        shopCount = usdbh.shopStatistics(getActivity());

        for (int i = 0; i < shopCount.length; i++) {

            Log.e("TAG", "count loop:  " + i);
            if (shopCount[i] != 0) {


                float percentage = (float) shopCount[i] / total * 100;

                //añadimos el nombre
                TextView color = new TextView(getActivity());
                color.setTextAppearance(getActivity().getApplicationContext(), R.style.text_color);
                color.setText(shopName[i] + ": " + Math.abs(percentage) + "%");
                //add style
                // Log.e("TAG", "color to add:  " + colorName[i] + " : " + colorCount[i]);
                layout.addView(color);
            }

        }

    }

}
