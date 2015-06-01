package com.closet.beatriz.closet;


import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Beatriz on 24/04/2015.
 */
public class MyItem implements Serializable {

    int id;
    //Bitmap image;
    String image;
    String description;
    String category;
    String colours;
    String season;
    //Date s_date;
    String s_date;
    String size;
    float prize;
    String shop;


    public MyItem() {

    }


    public MyItem(String image, String description, String category, String season, String s_date, String size, float prize, String shop) {
        this.image = image;
        this.description = description;
        this.category = category;
        this.season = season;
        this.s_date = s_date;
        this.size = size;
        this.prize = prize;
        this.shop = shop;

    }

    public MyItem(int id, String image, String description, String category, String season, String s_date, String size, float prize, String shop) {
        this.id = id;
        this.image = image;
        this.description = description;
        this.category = category;
        this.season = season;
        this.s_date = s_date;
        this.size = size;
        this.prize = prize;
        this.shop = shop;

    }

    public MyItem(String image, String description, String category, String season, String colours, String s_date, String size, float prize, String shop) {
        this.image = image;
        this.description = description;
        this.category = category;
        this.colours = colours;
        this.season = season;
        this.s_date = s_date;
        this.size = size;
        this.prize = prize;
        this.shop = shop;

    }

    public MyItem(int id, String image, String description, String category, String season, String colours, String s_date, String size, float prize, String shop) {
        this.id = id;
        this.image = image;
        this.description = description;
        this.category = category;
        this.colours = colours;
        this.season = season;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getColours() {
        return colours;
    }

    public void setColours(String colours) {
        this.colours = colours;
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
