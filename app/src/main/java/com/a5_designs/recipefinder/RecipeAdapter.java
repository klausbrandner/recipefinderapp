package com.a5_designs.recipefinder;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by macbrandy on 27/12/2016.
 */

public class RecipeAdapter extends ArrayAdapter<Recipe> {

    public RecipeAdapter(Activity context, ArrayList<Recipe> recipes){
        super(context, 0, recipes);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.recipe_list_item, parent, false);
        }

        Recipe currentRecipe = getItem(position);

        TextView recipeTitle = (TextView) listItemView.findViewById(R.id.recipe_title);
        recipeTitle.setText(currentRecipe.getTitle());

        TextView recipeSubtitle = (TextView) listItemView.findViewById(R.id.recipe_subtitle);
        recipeSubtitle.setText("5 of 5 stars");

        new ImageManager((ImageView) listItemView.findViewById(R.id.recipe_image)).execute(currentRecipe.getImage());

        return listItemView;
    }

}
