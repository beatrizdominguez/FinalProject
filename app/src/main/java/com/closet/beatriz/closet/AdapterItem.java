package com.closet.beatriz.closet;

/**
 * Created by Beatriz on 24/04/2015.
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.Collections;

public class AdapterItem extends BaseAdapter {

    protected ArrayList<String> data;
    //atributos
    private ArrayList<Item> lista;
    private LayoutInflater layout;
    Context context;

    public AdapterItem(Context context, ArrayList<Item> lista) {
        this.layout = LayoutInflater.from(context);
        this.lista = lista;
        this.context = context;

    }

    public AdapterItem sortData() {
        Collections.sort(data);
        return this;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int arg0) {
        return lista.get(arg0);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {

            //cambiar el layout que corresponda
            convertView = layout.inflate(R.layout.item_layout, null);

        }

        //definir los controles

        ImageView photo = (ImageView) convertView.findViewById(R.id.imgItem);
        TextView desc = (TextView) convertView.findViewById(R.id.txtDesc);
        TextView cat = (TextView) convertView.findViewById(R.id.txtCat);
        TextView price = (TextView) convertView.findViewById(R.id.txtPrice);

        Item i = (Item) getItem(position);
        //definir segun control y clase

        if (i.getImage() == null) {
            Bitmap b = BitmapFactory.decodeResource(context.getResources(), R.drawable.no_image);
            photo.setImageBitmap(b);

        } else {
            photo.setImageBitmap(i.getImage());
        }

        desc.setText(i.getDescription());
        cat.setText(i.getCategory());
        price.setText(String.valueOf(i.getPrize()) + "€");

        Log.e("TAG-------------adapter", String.valueOf(i.getPrize()));
        convertView.setTag(i.getId());

        return convertView;
    }

}
