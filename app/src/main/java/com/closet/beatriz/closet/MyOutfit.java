package com.closet.beatriz.closet;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Beatriz on 29/05/2015.
 */
public class MyOutfit implements Serializable {

    int id;
    String name;
    ArrayList<MyItem> itemList;


    public MyOutfit() {

    }

    public MyOutfit(String name, ArrayList<MyItem> itemList) {
        this.name = name;
        this.itemList = itemList;
    }

    public MyOutfit(int id, String name, ArrayList<MyItem> itemList) {
        this.id = id;
        this.name = name;
        this.itemList = itemList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<MyItem> getItemList() {
        return itemList;
    }

    public void setItemList(ArrayList<MyItem> itemList) {
        this.itemList = itemList;
    }


}
