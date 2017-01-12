package com.a5_designs.recipefinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class CategoriesActivity extends AppCompatActivity {

    private RecipeService recipeService;
    private String fbToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            if(extras.getString("fbToken") != null){
                fbToken = extras.getString("fbToken");
                this.recipeService = new RecipeService(fbToken);
            }
        }

        try {

            // get categories from service
            ArrayList<String> categories = (ArrayList<String>) this.recipeService.getCategories();
            categories.add(0, "All");

            // create array adapter for listview
            ArrayAdapter<String> categoryAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, categories);
            ListView listView = (ListView) findViewById(R.id.category_list);
            listView.setAdapter(categoryAdapter);

            // add click event to list items
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    String activeCategory = (String) adapterView.getItemAtPosition(i);
                    if(activeCategory == "All"){
                        activeCategory = "";
                    }

                    Intent selectCategory = new Intent(CategoriesActivity.this, HomeScreen.class);
                    selectCategory.putExtra("activeCategory",activeCategory);
                    selectCategory.putExtra("fbToken",fbToken);
                    startActivity(selectCategory);
                }
            });

        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
