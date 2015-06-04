package com.closet.beatriz.closet;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by Beatriz on 22/04/2015.
 */
public class FmConfig extends Fragment {

    View rootview;
    //Button btnSave;
    Button btnAdd;
    Button btnAddColor;
    Button btnRename;

    MyColor color;


    SQLiteHelper usdbh;

    public static final String IMAGE_RESOURCE_ID = "iconResourceID";
    public static final String ITEM_NAME = "itemName";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootview = inflater.inflate(R.layout.fm_configuration, container, false);


        return rootview;

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);


        //base de datos
        usdbh = new SQLiteHelper(getActivity(), "Closet",
                null, 1);

        color = new MyColor();

        Button btnSave = (Button) getActivity().findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getActivity(), "save",
                        Toast.LENGTH_SHORT).show();
                save();
            }
        });
        btnAdd = (Button) getActivity().findViewById(R.id.btnAddCat);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                add();
                Toast.makeText(getActivity(), "ADD",
                        Toast.LENGTH_SHORT).show();

            }
        });
        btnAddColor = (Button) getActivity().findViewById(R.id.btnAddColor);
        btnAddColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                newColor();


            }
        });

        btnRename = (Button) getActivity().findViewById(R.id.btnRenameCat);
        btnRename.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                rename();
                Toast.makeText(getActivity(), "RENAME",
                        Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void newColor() {

        final EditText input = new EditText(getActivity());

        new AlertDialog.Builder(getActivity())
                .setTitle(R.string.txt_add_color)
                .setMessage(R.string.txt_color_name)
                .setView(input)
                .setPositiveButton(R.string.action_next, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // Editable value = input.getText();
                        color.setColor(String.valueOf(input.getText()));
                        //outName = String.valueOf(input.getText());
                        if (color.getColor().length() > 0) {

                            colorCode(color.getColor());

                            // Intent intent = new Intent(getActivity(), Grid3.class);
                            // startActivity(intent);
                        }
                        // Toast.makeText(getActivity(), "ADD OUTFIT", Toast.LENGTH_SHORT).show();
                    }
                }).setNegativeButton(R.string.action_cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // Do nothing.
            }
        }).show();

    }


    private void colorCode(final String colorName) {

        ColorPickerDialog colorPickerDialog = new ColorPickerDialog(getActivity(), Color.BLUE, new ColorPickerDialog.OnColorSelectedListener() {

            @Override
            public void onColorSelected(int color) {

                //convert color to hex code string
                String strColor = "#" + Integer.toHexString(color);

                final MyColor newColor = new MyColor(colorName, strColor);
                usdbh.addColor(getActivity(), newColor);

                Toast.makeText(getActivity(), R.string.txt_msg_color, Toast.LENGTH_SHORT).show();

            }

        });

        colorPickerDialog.show();

    }


    private void save() {


        Spinner spnDfault = (Spinner) getActivity().findViewById(R.id.SpnDefault);
        String defaultCloset;
        defaultCloset = spnDfault.getSelectedItem().toString();
        Toast.makeText(getActivity(), "Default closet-> " + defaultCloset, Toast.LENGTH_SHORT).show();
    }

    private void add() {

        EditText txtAddCat = (EditText) getActivity().findViewById(R.id.EditAddCat);
        String cat;
        cat = txtAddCat.getText().toString();
        Toast.makeText(getActivity(), "add CAT-> " + cat, Toast.LENGTH_SHORT).show();
    }

    private void rename() {

        Spinner spnCat = (Spinner) getActivity().findViewById(R.id.SpnCat);
        String renameCat;
        renameCat = spnCat.getSelectedItem().toString();

        EditText txtAddCat = (EditText) getActivity().findViewById(R.id.EditRenameCat);
        String cat;
        cat = txtAddCat.getText().toString();
        Toast.makeText(getActivity(), "Rename CAT-> " + renameCat + " --- " + cat, Toast.LENGTH_SHORT).show();

    }

}
