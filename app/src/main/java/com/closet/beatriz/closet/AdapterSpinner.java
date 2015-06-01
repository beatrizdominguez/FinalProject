package com.closet.beatriz.closet;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Beatriz on 25/05/2015.
 */

public class AdapterSpinner extends ArrayAdapter<String> {

    private Activity activity;
    private ArrayList data;
    public Resources res;
    MySpinner tempValues = null;
    LayoutInflater inflater;

    /**
     * **********  SpinnerAdapter Constructor ****************
     */
    public AdapterSpinner(
            AcAddItem activitySpinner,
            int textViewResourceId,
            ArrayList objects,
            Resources resLocal
    ) {
        super(activitySpinner, textViewResourceId, objects);

        /********** Take passed values **********/
        activity = activitySpinner;
        data = objects;
        res = resLocal;

        /***********  Layout inflator to call external xml layout () **********************/
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    // This funtion called for each row ( Called data.size() times )
    public View getCustomView(int position, View convertView, ViewGroup parent) {

        /********** Inflate spinner_rows.xml file for each row ( Defined below ) ************/
        View row = inflater.inflate(R.layout.custom_spinner, parent, false);

        /***** Get each Model object from Arraylist ********/
        tempValues = null;
        tempValues = (MySpinner) data.get(position);

        TextView label = (TextView) row.findViewById(R.id.name);
        ImageView image = (ImageView) row.findViewById(R.id.image);

        label.setText(tempValues.getName());

        if (tempValues.getType() == AcAddItem.SPINNER_COLOR) {

            image.setBackgroundColor(Color.parseColor(tempValues.getImage()));

        } else {

            image.setImageResource(res.getIdentifier
                    ///////change package name
                    ("com.closet.beatriz.closet:drawable/"
                            + tempValues.getImage(), null, null));
        }

        return row;
    }


}
