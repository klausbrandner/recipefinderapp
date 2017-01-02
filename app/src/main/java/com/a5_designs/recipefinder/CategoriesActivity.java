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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        ArrayList<String> categories = new ArrayList<>();
        categories.add("All");
        categories.add("Vegetarian");
        categories.add("Meat");
        categories.add("Steak");
        categories.add("Chicken");
        categories.add("Healthy");

        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, categories);

        ListView listView = (ListView) findViewById(R.id.category_list);

        listView.setAdapter(categoryAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent selectCategory = new Intent(CategoriesActivity.this, HomeScreen.class);
                startActivity(selectCategory);

            }
        });

    }
}
