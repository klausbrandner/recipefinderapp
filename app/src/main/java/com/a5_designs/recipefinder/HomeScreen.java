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

        RecipeService recipeService = new RecipeService();

        try {

            ArrayList<Recipe> recipes = (ArrayList) recipeService.getRecipes();

            RecipeAdapter recipesAdapter = new RecipeAdapter(this, recipes);

            ListView listView = (ListView) findViewById(R.id.recipelist);

            listView.setAdapter(recipesAdapter);

        }catch(Exception e){
            e.printStackTrace();
        }

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
