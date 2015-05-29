package com.closet.beatriz.closet;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Beatriz on 29/05/2015.
 */
public class Outfit implements Serializable {

    int id;
    String name;
    ArrayList<Item> itemList;


    public Outfit() {

    }

    public Outfit(String name, ArrayList<Item> itemList) {
        this.name = name;
        this.itemList = itemList;
    }

    public Outfit(int id, String name, ArrayList<Item> itemList) {
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

    public ArrayList<Item> getItemList() {
        return itemList;
    }

    public void setItemList(ArrayList<Item> itemList) {
        this.itemList = itemList;
    }


}
