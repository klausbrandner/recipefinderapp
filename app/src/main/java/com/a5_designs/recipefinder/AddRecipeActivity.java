package com.a5_designs.recipefinder;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AddRecipeActivity extends AppCompatActivity {


    private String title;
    private String photo;
    private String description;
    private String preparation;
    Ingredient ingr;
    private List<Ingredient> ingredient = new ArrayList<>();
    private String ingredientTitle;
    private String ingredientQuantity;
    private List <String> categories = new ArrayList<>();
    private String newCategory;
    Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);

        final RecipeService recipeService = new RecipeService();

        Button btn = (Button) findViewById(R.id.add_ingredients_button);
        final TableLayout tableLayout = (TableLayout) findViewById(R.id.tableLayout);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ingredientTitle = ((EditText) findViewById(R.id.ingredient)).getText().toString();
                ingredientQuantity = ((EditText) findViewById(R.id.quantity)).getText().toString();
                ingr = new Ingredient(ingredientTitle, ingredientQuantity  );
                ingredient.add(ingr);
                TextView titleText = new TextView(getApplicationContext());
                titleText.setText(ingredientTitle);
                TextView quantityText = new TextView(getApplicationContext());
                quantityText.setText(ingredientQuantity);
                quantityText.setGravity(Gravity.CENTER);
                Button deleteBtn = new Button(getApplicationContext());
                deleteBtn.setText("Delete");
                deleteBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final TableRow parent = (TableRow) v.getParent();
                        ingredient.remove(ingredient.indexOf(ingr));
                        tableLayout.removeView(parent);
                    }
                });
                deleteBtn.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                TableRow tableRow = new TableRow(getApplicationContext());
                tableRow.addView(titleText);
                tableRow.addView(quantityText);
                tableRow.addView(deleteBtn);
                tableLayout.addView(tableRow, 1);
            }
        });

        Button btnAddCategorie = (Button) findViewById(R.id.add_categories_button);
        final TableLayout tableLayoutCateg = (TableLayout) findViewById(R.id.tableLayoutCateg);
        btnAddCategorie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText answer = (EditText) findViewById(R.id.editText_categName);
                newCategory = answer.getText().toString();
                categories.add(newCategory);
                TextView categrieName = new TextView(getApplicationContext());
                categrieName.setText(newCategory);
                Button deleteBtn = new Button(getApplicationContext());
                deleteBtn.setText("Delete");
                deleteBtn.setGravity(Gravity.CENTER);
                deleteBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final TableRow parent = (TableRow) v.getParent();
                        categories.remove(categories.indexOf(newCategory));
                        tableLayoutCateg.removeView(parent);
                    }
                });
                deleteBtn.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                TableRow tableRow = new TableRow(getApplicationContext());
                tableRow.addView(categrieName);
                tableRow.addView(deleteBtn);
                tableLayoutCateg.addView(tableRow, 1);
            }
        });

        Button btnAddRecipe = (Button) findViewById(R.id.add_recipe_button);
        btnAddRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title = ((EditText) findViewById(R.id.editText_name)).getText().toString();
                description = ((EditText) findViewById(R.id.editText_descr)).getText().toString();
                preparation =((EditText) findViewById(R.id.editText_prep)).getText().toString();
                photo = ((EditText) findViewById(R.id.editText_photo)).getText().toString();
                Ingredient [] ingArray =  new Ingredient[ingredient.size()];
                ingredient.toArray(ingArray);

                String [] categAray = new String [categories.size()];
                categories.toArray(categAray);
                try {
                    recipeService.createRecipe(title, photo, description, preparation, ingArray, categAray);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Intent homeScreen = new Intent(AddRecipeActivity.this, HomeScreen.class);
                startActivity(homeScreen);
            }
        });

    }
}
