package com.a5_designs.recipefinder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import com.a5_designs.recipefinder.Recipe;

public class HomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        ArrayList<Recipe> recipes = new ArrayList<Recipe>();
        recipes.add(new Recipe(1,"Pizza"));
        recipes.add(new Recipe(2,"Pasta"));
        recipes.add(new Recipe(3,"Chicken"));


        ArrayAdapter<Recipe> recipesAdapter = new ArrayAdapter<Recipe>(this, R.layout.recipe_list_item, recipes);

        ListView listView = (ListView) findViewById(R.id.recipelist);

        listView.setAdapter(recipesAdapter);

    }

}
