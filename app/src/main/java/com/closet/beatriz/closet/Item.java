package com.closet.beatriz.closet;


import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Beatriz on 24/04/2015.
 */
public class Item  implements Serializable{

    int id;
    Bitmap image;
    //String image;
    String description;
    String category;
    //Date s_date;
    String s_date;
    String size;
    float prize;
    String shop;
    //colors!!
    String[] colorArray;

    public Item() {

    }

    public Item(String description, String category, String s_date, String size, float prize, String shop) {
        this.description = description;
        this.category = category;
        this.s_date = s_date;
        this.size = size;
        this.prize = prize;
        this.shop = shop;
    }

    public Item(String description, String[] colorArray, String category, String s_date, String size, float prize, String shop) {
        this.description = description;
        this.colorArray = colorArray;
        this.category = category;
        this.s_date = s_date;
        this.size = size;
        this.prize = prize;
        this.shop = shop;
    }

    //just for testing
    public Item(int id, String description, String category, String s_date, String size, float prize, String shop) {
        this.id = id;
        this.description = description;
        this.category = category;
        this.s_date = s_date;
        this.size = size;
        this.prize = prize;
        this.shop = shop;
    }
    public Item( Bitmap image, String description, String category, String s_date, String size, float prize, String shop) {
        this.image = image;
        this.description = description;
        this.category = category;
        this.s_date = s_date;
        this.size = size;
        this.prize = prize;
        this.shop = shop;
    }

    public Item(int id, Bitmap image, String description, String category, String s_date, String size, float prize, String shop) {
        this.id = id;
        this.image = image;
        this.description = description;
        this.category = category;
        this.s_date = s_date;
        this.size = size;
        this.prize = prize;
        this.shop = shop;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String name) {
        this.description = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getS_date() {
        return s_date;
    }

    public void setS_date(String s_date) {
        this.s_date = s_date;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public float getPrize() {
        return prize;
    }

    public void setPrize(float prize) {
        this.prize = prize;
    }

    public String getShop() {
        return shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }



}
