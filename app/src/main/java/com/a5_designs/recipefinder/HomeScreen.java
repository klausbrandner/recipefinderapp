package com.a5_designs.recipefinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class HomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        RecipeService recipeService = new RecipeService();
        String activeCategory = "";

        // receive filter from categoryActivity
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            activeCategory = extras.getString("activeCategory");
        }

        try {

            // get recipes from service
            ArrayList<Recipe> allRecipes = (ArrayList<Recipe>) recipeService.getRecipes();
            ArrayList<Recipe> recipes = new ArrayList<>();

            // if a category is selected -> filter by category
            // else show all recipes
            if(activeCategory != null && !activeCategory.equals("")) {
                for (int i = 0; i < allRecipes.size(); i++) {
                    Recipe r = allRecipes.get(i);
                    String[] cat = r.getCategories();
                    boolean contains = false;
                    for (String s : cat) {
                        if (s.equals(activeCategory)) {
                            contains = true;
                        }
                    }
                    if(contains){
                        recipes.add(r);
                    }
                }
            }else {
                recipes = allRecipes;
            }

            // create custom ArrayAdapter for ListView
            RecipeAdapter recipesAdapter = new RecipeAdapter(this, recipes);
            ListView listView = (ListView) findViewById(R.id.recipelist);
            listView.setAdapter(recipesAdapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    Recipe r = (Recipe) adapterView.getItemAtPosition(i);

                    Intent selectRecipe = new Intent(HomeScreen.this, DetailActivity.class);
                    Bundle myBundle = new Bundle();
                    myBundle.putSerializable("recipe",r);
                    selectRecipe.putExtras(myBundle);
                    startActivity(selectRecipe);

                }
            });

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
                Intent showAddRecipe = new Intent(HomeScreen.this, AddRecipeActivity.class);
                startActivity(showAddRecipe);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
