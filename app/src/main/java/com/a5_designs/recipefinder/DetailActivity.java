package com.a5_designs.recipefinder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private RecipeService recipeService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            if(extras.getString("fbToken") != null){
                this.recipeService = new RecipeService(extras.getString("fbToken"));
            }
        }

        final Recipe recipe = (Recipe) getIntent().getSerializableExtra("recipe");

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
                try {
                    Double newRating = recipeService.evaluate(recipe.getRid(), value);
                    recipe.setRating(newRating);
                    ratingValue.setText(String.format("%.1f", recipe.getRating()) + " of 5 stars");

                } catch (Exception e) {
                    e.printStackTrace();
                }
                ratingBar.setEnabled(false);
            }
        });
    }
}
