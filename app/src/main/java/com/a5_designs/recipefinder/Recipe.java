package com.a5_designs.recipefinder;

/**
 * Created by macbrandy on 27/12/2016.
 */

public class Recipe {

    private int rid;
    private String title;

    public Recipe(int rid, String title){
        this.rid = rid;
        this.title = title;
    }

    public String getTitle(){
        return this.title;
    }
}
