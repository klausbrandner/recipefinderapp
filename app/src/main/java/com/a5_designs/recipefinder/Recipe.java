package com.a5_designs.recipefinder;

/**
 * Created by macbrandy on 27/12/2016.
 */

public class Recipe {

    private int rid;
    private String title;
    private String image;

    public Recipe(int rid, String title){
        this.rid = rid;
        this.title = title;
        this.image = "http://www.immetzo.at/images/uploadImages/vy4QCnJ3SeGO.jpg";
    }

    public String getTitle(){
        return this.title;
    }

    public String getImage(){
        return this.image;
    }
}
