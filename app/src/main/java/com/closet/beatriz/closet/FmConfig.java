package com.closet.beatriz.closet;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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

    ItemSQLiteHelper usdbh;

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
        usdbh = new ItemSQLiteHelper(getActivity(), "Closet",
                null, 1);

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

                //check if work
               // usdbh.getColors();

                EditText txtColor = (EditText) rootview.findViewById(R.id.eTxtColor);
                Log.e("TAG----", "color added: " + txtColor.getText().toString());
                usdbh.addColor(txtColor.getText().toString());
                //user message
                Toast.makeText(getActivity(), R.string.txt_msg_color,
                        Toast.LENGTH_SHORT).show();

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

    private void save() {


        Spinner spnDfault = (Spinner) getActivity().findViewById(R.id.SpnDefault);
        String defaultCloset;
        defaultCloset = spnDfault.getSelectedItem().toString();
        Toast.makeText(getActivity(), "SPN-> " + defaultCloset,
                Toast.LENGTH_SHORT).show();
    }

    private void add() {

        EditText txtAddCat = (EditText) getActivity().findViewById(R.id.EditAddCat);
        String cat;
        cat = txtAddCat.getText().toString();
        Toast.makeText(getActivity(), "add CAT-> " + cat,
                Toast.LENGTH_SHORT).show();
    }

    private void rename() {

        Spinner spnCat = (Spinner) getActivity().findViewById(R.id.SpnCat);
        String renameCat;
        renameCat = spnCat.getSelectedItem().toString();

        EditText txtAddCat = (EditText) getActivity().findViewById(R.id.EditRenameCat);
        String cat;
        cat = txtAddCat.getText().toString();
        Toast.makeText(getActivity(), " CAT-> " + renameCat + " --- " + cat,
                Toast.LENGTH_SHORT).show();

    }

}
