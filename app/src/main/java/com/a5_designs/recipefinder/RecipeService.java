package com.a5_designs.recipefinder;


import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Phil on 01.01.2017.
 */
public class RecipeService {

    private List<Recipe> recipes;
    private List<String> categories;
    private String service = "http://35.167.61.204:9000/";
    private final String USER_AGENT = "Mozilla/5.0";
    private Gson gson = new Gson();

    public RecipeService() {
        this.recipes = new ArrayList<>();
        this.categories = new ArrayList<>();
    }

    public List<Recipe> getRecipes() throws Exception {
        String recipesJsonString = new RecipeHttpGetService().execute(service+"recipes").get();
        List<Recipe> recs = new ArrayList<>();
        Recipe[] recipeArr = gson.fromJson(recipesJsonString, Recipe[].class);

        for (Recipe r : recipeArr) {
            recs.add(r);
        }

        this.recipes = recs;
        return this.recipes;
    }

    public void addRecipe(int number, String title, String photo, String description, String preparation, double rating, Ingredient[] ingredients, String[] categories) {
        Recipe recipe = new Recipe(number, title, photo, description, preparation, ingredients, rating, categories);
        this.recipes.add(recipe);
        for (String c : categories) {
            if (!this.categories.contains(c)) this.categories.add(c);
        }
    }

    public void createRecipe(String title, String photo, String description, String preparation, Ingredient[] ingredients, String[] categories) throws Exception {
        StringBuilder ingredientString = new StringBuilder();
        ingredientString.append("[");
        Ingredient lastIngredient = ingredients[ingredients.length - 1];
        for (Ingredient ingredient : ingredients) {
            ingredientString.append("{\"title\":\"" + ingredient.getTitle() + "\",");
            ingredientString.append("\"quantity\":\"" + ingredient.getQuantity() + "\"}");
            if (!lastIngredient.equals(ingredient)) ingredientString.append(",");

        }
        ingredientString.append("]");


        StringBuilder categoryString = new StringBuilder();
        categoryString.append("[");
        String lastCategory = categories[categories.length - 1];
        for (String cat : categories) {
            categoryString.append("\"" + cat + "\"");
            if (!cat.equals(lastCategory)) categoryString.append(",");
        }
        categoryString.append("]");

        String data = "{" +
                "\"title\": \"" + title + "\"," +
                "\"photo\": \"" + photo + "\"," +
                "\"description\": \"" + description + "\"," +
                "\"preparation\": \"" + preparation + "\"," +
                "\"ingredients\": " + ingredientString + "," +
                "\"categories\": " + categoryString +
                "}";

        Integer rid = Integer.valueOf(new RecipeHttpPostService(data).execute(service+"recipe").get());
        addRecipe(rid, title, photo, description, preparation, 0, ingredients, categories);

    }

    public List<String> getCategories() throws Exception {
        String categoriesJsonString = new RecipeHttpGetService().execute(service+"categories").get();
        List<String> catList = new ArrayList<>();
        String []cats = gson.fromJson(categoriesJsonString, String[].class);
        for (String c : cats) {
            catList.add(c);
        }
        this.categories = catList;
        return this.categories;
    }

    public Double evaluate(int rid, int rating) throws Exception {

        String data = "{" +
                "\"rid\": \"" + rid + "\"," +
                "\"rating\": \"" + rating + "\"" +
                "}";

        String response = new RecipeHttpPostService(data).execute(service+"evaluate").get();

        JsonObject jobj = new Gson().fromJson(response, JsonObject.class);

        return Double.parseDouble(jobj.get("rating").getAsString());
    }

}
