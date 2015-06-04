package com.closet.beatriz.closet;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
    public final static int REQUEST_ADD_NOTE = 2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootview = inflater.inflate(R.layout.fm_calendar, container, false);

        //base de datos
        usdbh = new SQLiteHelper(getActivity(), "Closet",
                null, 1);

        grid = (GridView) rootview.findViewById(R.id.gridCalendar);
        adaptador = new AdapterItem(getActivity().getBaseContext(), lista);
        grid.setAdapter(adaptador);

        // definir el listener del gridView
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                //category =String.valueOf( R.string.catShirts);
                //MAYBE PROBLEM!!!

                MyItem item;
                item = (MyItem) grid.getItemAtPosition(position);
                //Log.e("TAG", "listener del gridview");
                // Toast.makeText(AcCategory.this, "Item seleccionado", Toast.LENGTH_LONG).show();
                Intent intentVer = new Intent(getActivity(), AcItemDetail.class);
                Bundle mBundle = new Bundle();
                //intentVer.putExtras("cat",category);
                mBundle.putSerializable("item", (Serializable) item);
                intentVer.putExtras(mBundle);
                startActivity(intentVer);

            }
        });


        txtNotes = (TextView) rootview.findViewById(R.id.txtNotes);


        calendar = (CalendarView) rootview.findViewById(R.id.calendarView);
        calendar.setDate(System.currentTimeMillis(), false, true);
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {
                date = dayCode(dayOfMonth) + "/" + monthCode(month + 1) + "/" + year;
                //Toast.makeText(getActivity(), date, Toast.LENGTH_SHORT).show();
                //Toast.makeText(getActivity(), "" + dayOfMonth + " of " + monthName(month) + " of " + year, Toast.LENGTH_SHORT).show();

                //remove info and add the one related to that date
                deleteDateInfo();
                loadDateInfo(date);
            }
        });
        btnAddItem = (Button) rootview.findViewById(R.id.btnAddItem);
        btnAddItem.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                more(REQUEST_ADD_ITEM);

            }
        });

        btnAddNote = (Button) rootview.findViewById(R.id.btnAddNote);
        btnAddNote.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                more(REQUEST_ADD_NOTE);

            }
        });

        //load today's info
        loadDateInfo(today());

        return rootview;
    }

    private String today() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyy");
        String date = dateFormat.format(System.currentTimeMillis());
        // Toast.makeText(this, "date of today formated:  " + date2, Toast.LENGTH_SHORT).show();


        return date;
    }

    private String dayCode(int day) {

        if (day < 10)
            return "0" + day;

        else return String.valueOf(day);

    }

    private String monthCode(int month) {

        if (month < 10)
            return "0" + month;

        else return String.valueOf(month);

    }

    private void more(final int requestAction) {

        final CharSequence[] items = {getString(R.string.action_add), getString(R.string.action_empty),
                getString(R.string.action_cancel)};

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.txt_more);
        builder.setItems(items, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int item) {
                // Toast.makeText(getActivity(), "item selected: " + item, Toast.LENGTH_SHORT).show();

                //add
                if (item == 0) {

                    if (requestAction == REQUEST_ADD_NOTE) {

                        addNote();

                    } else if (requestAction == REQUEST_ADD_ITEM) {

                        Intent intent = new Intent(getActivity(), AcSelecttItem.class);
                        startActivityForResult(intent, REQUEST_ADD_ITEM);
                    }

                    //empty
                } else if (item == 1) {
                    if (requestAction == REQUEST_ADD_NOTE) {

                        //notes
                        txtNotes.setText(R.string.txt_notes);

                        //delete from DB
                        usdbh.removeCalendarNotes(date);

                    } else if (requestAction == REQUEST_ADD_ITEM) {

                        //items
                        lista.clear();
                        adaptador.notifyDataSetChanged();

                        //delete from DB
                        usdbh.removeCalendarItems(date);
                    }

                    //cancel
                } else if (item == 2) {

                    dialog.dismiss();
                }
            }
        });
        builder.show();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Bundle extras = null;
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_ADD_ITEM) {

                extras = data.getExtras();

                ArrayList<MyItem> itemList = (ArrayList<MyItem>) extras.get("list");

                Log.e("TAG------", "tamaño de la lista devuelta: " + itemList.size());

                Log.e("TAG------", "tamaño de la lista antes: " + lista.size());

                for (int i = 0; i < itemList.size(); i++) {
                    Log.e("TAG------", "i del loop: " + i);

                    MyItem item = itemList.get(i);

                    Log.e("TAG------", "item: " + item.getId() + ";  " + item.getDescription());

                    // lista.add(itemList.get(i));
                    lista.add(item);
                    //usdbh.addCalendarItem(getActivity(), date, itemList.get(i));
                    usdbh.addCalendarItem(getActivity(), date, item);
                    // adaptador.notifyDataSetChanged();


                }

                Log.e("TAG------", "tamaño de la lista después: " + lista.size());
                adaptador.notifyDataSetChanged();

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
        adaptador.refresh(lista);
        //adaptador.notifyDataSetChanged();
        // Log.e("TAG_________________", "adaptador:   " + adaptador.toString());
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

                            usdbh.addCalendarNote(getActivity(), date, String.valueOf(input.getText()));
                            txtNotes.append("\n" + input.getText());
                        }

                    }
                }).setNegativeButton(R.string.action_cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // Do nothing.
            }
        }).show();

    }

    /*
    public String monthName(int month) {
        String name;
        String[] monthNames = getActivity().getResources().getStringArray(R.array.monthsArray);
        name = monthNames[month];

        return name;
    }*/
}
