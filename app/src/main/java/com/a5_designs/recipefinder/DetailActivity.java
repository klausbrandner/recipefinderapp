package com.a5_designs.recipefinder;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Arrays;

public class DetailActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        final Recipe recipe = (Recipe) getIntent().getSerializableExtra("recipe");

        Log.d("RECIPE","show recipe " + recipe.getTitle());

        TextView title = (TextView) findViewById(R.id.titleView);
        title.setText(recipe.getTitle());
        final TextView ratingValue = (TextView) findViewById(R.id.ratingView);
        String stars = String.format("%.1f", recipe.getRating());
        ratingValue.setText(stars + " of 5 stars");
        TextView description = (TextView) findViewById(R.id.descriptionView);
        description.setText(recipe.getDescription());
        TextView ingredientsView = (TextView) findViewById(R.id.ingredientView);
        Ingredient [] ingredientArray = recipe.getIngredients();
        int arraySize = ingredientArray.length;
        for (int i = 0; i < arraySize; i++){
            Ingredient ingr = ingredientArray[i];
            ingredientsView.append(ingr.getTitle() + " " + ingr.getQuantity());
            ingredientsView.append("\n");
        }

        TextView preparation = (TextView) findViewById(R.id.preparationView);
        preparation.setText(recipe.getPreparation());

        new ImageManager((ImageView) findViewById(R.id.imageView_recipeDetail)).execute(recipe.getPhoto());

        final RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                int value = Math.round(rating);
                RecipeService recipeService = new RecipeService();
                try {
                    recipe.setRating((recipeService.evaluate(recipe.getRid(), value)));

                } catch (Exception e) {
                    e.printStackTrace();
                }
                ratingBar.setEnabled(false);
            }
        });
    }
}
