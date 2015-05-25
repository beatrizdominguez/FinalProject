package com.closet.beatriz.closet;

/**
 * Created by Beatriz on 25/05/2015.
 */
public class SpinnerModel {
    private int Type = 0;
    private String Name = "";
    private String Image = "";

    public SpinnerModel() {

    }

    public SpinnerModel(int type, String name, String image) {
        Type = type;
        Name = name;
        Image = image;
    }

    public int getType() {
        return Type;
    }

    public void setType(int type) {
        Type = type;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
