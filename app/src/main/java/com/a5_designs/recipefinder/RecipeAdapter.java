package com.a5_designs.recipefinder;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.a5_designs.recipefinder.Recipe;

/**
 * Created by macbrandy on 27/12/2016.
 */

public class RecipeAdapter extends ArrayAdapter<Recipe> {
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return super.getView(position, convertView, parent);
    }


}
