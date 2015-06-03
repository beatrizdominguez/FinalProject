package com.closet.beatriz.closet;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Beatriz on 22/04/2015.
 */


public class FmCalendar extends Fragment {

    View rootview;
    CalendarView calendar;
    String date;
    TextView txtNotes;
    Button btnAddItem;
    Button btnAddNote;

    private ArrayList<MyItem> lista = new ArrayList<MyItem>();
    GridView grid;
    AdapterItem adaptador = null;

    SQLiteHelper usdbh;

    public static final String IMAGE_RESOURCE_ID = "iconResourceID";
    public static final String ITEM_NAME = "itemName";
    public final static int REQUEST_ADD_ITEM = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootview = inflater.inflate(R.layout.fm_calendar, container, false);

        //base de datos
        usdbh = new SQLiteHelper(getActivity(), "Closet",
                null, 1);

        grid = (GridView) rootview.findViewById(R.id.gridCalendar);
        adaptador = new AdapterItem(getActivity(), lista);
        grid.setAdapter(adaptador);

        txtNotes = (TextView) rootview.findViewById(R.id.txtNotes);


        calendar = (CalendarView) rootview.findViewById(R.id.calendarView);
        calendar.setDate(System.currentTimeMillis(), false, true);
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {
                date = dayOfMonth + "/" + monthName(month) + "/" + year;
                Toast.makeText(getActivity(), date, Toast.LENGTH_SHORT).show();
                Toast.makeText(getActivity(), "" + dayOfMonth + " of " + monthName(month) + " of " + year, Toast.LENGTH_SHORT).show();

                deleteDateInfo();
                loadDateInfo(date);
            }
        });
        btnAddItem = (Button) rootview.findViewById(R.id.btnAddItem);
        btnAddItem.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), AcSelecttItem.class);
                startActivityForResult(intent, REQUEST_ADD_ITEM);

            }
        });

        btnAddNote = (Button) rootview.findViewById(R.id.btnAddNote);
        btnAddNote.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                addNote();

            }
        });



        return rootview;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Bundle extras = null;
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_ADD_ITEM) {

                extras = data.getExtras();

                ArrayList<MyItem> itemList = new ArrayList<MyItem>();

                itemList = (ArrayList<MyItem>) extras.get("list");
                Log.e("TAG------", "tamaño de la lista devuelta: " + itemList.size());

                for (int i = 0; i < itemList.size(); i++) {
                    Log.e("TAG------", "i del loop: " + i);

                    lista.add(itemList.get(i));
                    usdbh.addCalendarItem(date, itemList.get(i));
                    adaptador.notifyDataSetChanged();

                }

                //adaptador.notifyDataSetChanged();

            }
        }
    }

    private void deleteDateInfo() {

        //notes
        txtNotes.setText(R.string.txt_notes);

        //items
        lista.clear();

    }

    private void loadDateInfo(String date) {


        //cargar notas de DB
        ArrayList<String> noteList;

        noteList = usdbh.getCalendarNotes(date);
        for (int i = 0; i < noteList.size(); i++) {

            txtNotes.append("\n" + noteList.get(i));

        }

        //cargar items de DB
        lista = usdbh.getCalendarItems(date);
        adaptador.notifyDataSetChanged();

    }

    private void addNote() {
        //dialog with text
        final EditText input = new EditText(getActivity());

        new AlertDialog.Builder(getActivity())
                .setTitle(R.string.txt_add_note)
                        //.setMessage(R.string.txt_color_name)
                .setView(input)
                .setPositiveButton(R.string.action_next, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                        if (input.getText().length() > 0) {

                            usdbh.addCalendarNote(date, String.valueOf(input.getText()));
                            txtNotes.append("\n" + input.getText());
                        }

                    }
                }).setNegativeButton(R.string.action_cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // Do nothing.
            }
        }).show();

    }

    public String monthName(int month) {
        String name;
        String[] monthNames = getActivity().getResources().getStringArray(R.array.monthsArray);
        name = monthNames[month];

        return name;
    }
}
