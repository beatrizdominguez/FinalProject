package com.closet.beatriz.closet;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Beatriz on 22/04/2015.
 */
public class FmCalendar extends Fragment {

    View rootview;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootview = inflater.inflate(R.layout.fm_calendar, container, false);

        Button btn = (Button) rootview.findViewById(R.id.btnCalendar);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getActivity(), "hola",
                        Toast.LENGTH_SHORT).show();

            }
        });


int vut;

        return rootview;
    }
}