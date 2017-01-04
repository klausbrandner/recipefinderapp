package com.a5_designs.recipefinder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Recipe recipe = (Recipe) getIntent().getSerializableExtra("recipe");

        Log.d("RECIPE","show recipe " + recipe.getTitle());
    }
}
