package com.a5_designs.recipefinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;


public class HomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        ArrayList<Recipe> recipes = new ArrayList<Recipe>();
        recipes.add(new Recipe(1,"Pizza"));
        recipes.add(new Recipe(2,"Pasta"));
        recipes.add(new Recipe(3,"Chicken"));
        recipes.add(new Recipe(4,"Pizza"));
        recipes.add(new Recipe(5,"Pasta"));
        recipes.add(new Recipe(6,"Chicken"));
        recipes.add(new Recipe(7,"Pizza"));
        recipes.add(new Recipe(8,"Pasta"));
        recipes.add(new Recipe(9,"Chicken"));


        RecipeAdapter recipesAdapter = new RecipeAdapter(this, recipes);

        ListView listView = (ListView) findViewById(R.id.recipelist);

        listView.setAdapter(recipesAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_filter:

                Intent showCategories = new Intent(HomeScreen.this, CategoriesActivity.class);
                startActivity(showCategories);

                return true;
            case R.id.action_add:
                // do something
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
