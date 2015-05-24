package com.closet.beatriz.closet;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Beatriz on 22/04/2015.
 */
public class FmCalendar extends Fragment {

    View rootview;
    CalendarView calendar;
    Long date;

    public static final String IMAGE_RESOURCE_ID = "iconResourceID";
    public static final String ITEM_NAME = "itemName";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootview = inflater.inflate(R.layout.fm_calendar, container, false);


        calendar = (CalendarView) rootview.findViewById(R.id.calendarView);
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {
                Toast.makeText(getActivity(), "" + dayOfMonth + " of " + monthName(month), Toast.LENGTH_SHORT).show();

            }
        });
        return rootview;
    }

    public String monthName(int month) {
        String name;
        String[] monthNames = getActivity().getResources().getStringArray(R.array.monthsArray);
        name = monthNames[month];

        return name;
    }
}
