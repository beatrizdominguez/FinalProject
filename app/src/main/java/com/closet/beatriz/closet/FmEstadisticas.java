package com.closet.beatriz.closet;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by Beatriz on 22/04/2015.
 */
public class FmEstadisticas extends Fragment {

    View rootview;
    ItemSQLiteHelper usdbh;

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
        displayCount();


    }

    private void displayPrices() {

        Float[] ItemValue = new Float[10];
        ItemValue = usdbh.priceStatistics(getActivity());
        Log.e("TAG-----total value", String.valueOf(ItemValue[0]));

        //total
        TextView txtTotal = (TextView) rootview.findViewById(R.id.txtTotalValue);
        //categories
        TextView shirtValue = (TextView) rootview.findViewById(R.id.txtShirtValue);
        TextView pantsValue = (TextView) rootview.findViewById(R.id.txtPantstValue);
        TextView UnderWearValue = (TextView) rootview.findViewById(R.id.txtUnderWearValue);
        TextView ShoesValue = (TextView) rootview.findViewById(R.id.txtShoesValue);
        TextView JumperValue = (TextView) rootview.findViewById(R.id.txtJumperValue);
        TextView PijamasValue = (TextView) rootview.findViewById(R.id.txtPijamasValue);
        TextView DressValue = (TextView) rootview.findViewById(R.id.txtDressValue);
        TextView CoatsValue = (TextView) rootview.findViewById(R.id.txtCoatsValue);
        TextView AccessoriesValue = (TextView) rootview.findViewById(R.id.txtAccesoriesValue);


        Float total;
        float totalValue = 0;
        float valueShirts = 0;
        float valuePants = 0;
        float valueUnderWear = 0;
        float valueShoes = 0;
        float valueJumper = 0;
        float valuePijamas = 0;
        float valueDress = 0;
        float valueCoats = 0;
        float valueAccessories = 0;

        total = ItemValue[0];
        valueShirts = ItemValue[1];
        valuePants = ItemValue[2];
        valueUnderWear = ItemValue[3];
        valueShoes = ItemValue[4];
        valueJumper = ItemValue[5];
        valuePijamas = ItemValue[6];
        valueDress = ItemValue[7];
        valueCoats = ItemValue[8];
        valueAccessories = ItemValue[9];

        txtTotal.setText(txtTotal.getText() + String.valueOf(total) + " €");
        shirtValue.setText(String.valueOf(valueShirts) + " €");
        pantsValue.setText(String.valueOf(valuePants) + " €");
        UnderWearValue.setText(String.valueOf(valueUnderWear) + " €");
        ShoesValue.setText(String.valueOf(valueShoes) + " €");
        JumperValue.setText(String.valueOf(valueJumper) + " €");
        PijamasValue.setText(String.valueOf(valuePijamas) + " €");
        DressValue.setText(String.valueOf(valueDress) + " €");
        CoatsValue.setText(String.valueOf(valueCoats) + " €");
        AccessoriesValue.setText(String.valueOf(valueAccessories) + " €");


    }

    private void displayCount() {

        Integer[] ItemCount = new Integer[14];
        ItemCount = usdbh.countStatistics(getActivity());
        Log.e("TAG-----total value", String.valueOf(ItemCount[0]));

//total
        TextView totalCount = (TextView) rootview.findViewById(R.id.txtTotalCount);
        //categories
        TextView shirtCount = (TextView) rootview.findViewById(R.id.txtShirtCount);
        TextView pantsCount = (TextView) rootview.findViewById(R.id.txtPantsCount);
        TextView UnderWearCount = (TextView) rootview.findViewById(R.id.txtUnderWearCount);
        TextView ShoesCount = (TextView) rootview.findViewById(R.id.txtShoesCount);
        TextView JumperCount = (TextView) rootview.findViewById(R.id.txtJumperCount);
        TextView PijamasCount = (TextView) rootview.findViewById(R.id.txtPijamasCount);
        TextView DressCount = (TextView) rootview.findViewById(R.id.txtDressCount);
        TextView CoatsCount = (TextView) rootview.findViewById(R.id.txtCoatsCount);
        TextView AccessoriesCount = (TextView) rootview.findViewById(R.id.txtAccesoriesCount);
        //seasons
        TextView srpingCount = (TextView) rootview.findViewById(R.id.txtSpringCount);
        TextView summerCount = (TextView) rootview.findViewById(R.id.txtSummerCount);
        TextView automnCount = (TextView) rootview.findViewById(R.id.txtAutomnCount);
        TextView winterCount = (TextView) rootview.findViewById(R.id.txtWinterCount);

        int total;
        int totalValue = 0;
        //categories
        int countShirts = 0;
        int countPants = 0;
        int countUnderWear = 0;
        int valueShoes = 0;
        int countJumper = 0;
        int countPijamas = 0;
        int countDress = 0;
        int countCoats = 0;
        int countAccessories = 0;
        //seasons
        int countspring = 0;
        int countWinter = 0;
        int countSummer = 0;
        int countAutumn = 0;

        total = ItemCount[0];
        //categories
        countShirts = ItemCount[1];
        countPants = ItemCount[2];
        countUnderWear = ItemCount[3];
        valueShoes = ItemCount[4];
        countJumper = ItemCount[5];
        countPijamas = ItemCount[6];
        countDress = ItemCount[7];
        countCoats = ItemCount[8];
        countAccessories = ItemCount[9];
        //seasons
         countspring = ItemCount[10];
        countSummer = ItemCount[11];
        countAutumn = ItemCount[12];
        countWinter = ItemCount[13];

        totalCount.setText(totalCount.getText() + String.valueOf(total));
        shirtCount.setText(String.valueOf(countShirts));
        pantsCount.setText(String.valueOf(countPants));
        UnderWearCount.setText(String.valueOf(countUnderWear));
        ShoesCount.setText(String.valueOf(valueShoes));
        JumperCount.setText(String.valueOf(countJumper));
        PijamasCount.setText(String.valueOf(countPijamas));
        DressCount.setText(String.valueOf(countDress));
        CoatsCount.setText(String.valueOf(countCoats));
        AccessoriesCount.setText(String.valueOf(countAccessories));
        //seasons
        srpingCount.setText(String.valueOf(countspring));
        summerCount.setText(String.valueOf(countSummer));
        automnCount.setText(String.valueOf(countAutumn));
        winterCount.setText(String.valueOf(countWinter));
        //primasvera- ii
        //in - ii


    }

}
