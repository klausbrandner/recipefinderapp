package com.a5_designs.recipefinder;

import android.util.Log;

import com.google.gson.Gson;

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
    private String service = "http://localhost:4040/";
    private final String USER_AGENT = "Mozilla/5.0";
    private Gson gson = new Gson();


    public RecipeService(List<Recipe> recipes, List<String> categories) {
        this.categories = categories;
        this.recipes = recipes;
    }

    public RecipeService() {
        this.recipes = new ArrayList<>();
        this.categories = new ArrayList<>();
    }


    public List<Recipe> getRecipes() throws Exception {
        String recipesJsonString = sendGet("recipes");
        List<Recipe> recs = new ArrayList<>();
        Recipe[] recipeArr = gson.fromJson(recipesJsonString, Recipe[].class);

        for (Recipe r : recipeArr) {
            recs.add(r);
        }

        this.recipes = recs;
        return this.recipes;
    }

    /*public Recipe getRecipe(int rid) {
        return recipes.stream().filter(r -> r.getRid() == rid).findFirst().get();
    }*/

    //addRecipe(rid:number, title:string, photo:string, description:string, preparation:string, rating:number, ingredients:Ingredient[],categories:string[])
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

        Integer rid = Integer.valueOf(sendPost("recipe", data));
        addRecipe(rid, title, photo, description, preparation, 0, ingredients, categories);

    }

    public List<String> getCategories() throws Exception {
        String categoriesJsonString = sendGet("categories");
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

        return Double.parseDouble(sendPost("evaluate", data));
    }

    private String sendGet(String resource) throws Exception {



        String url = service + resource;

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);



        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();



        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response.toString();
    }

    private String sendPost(String resource, String data) throws Exception {
        String url = service + resource;
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //add reuqest header
        con.setRequestMethod("POST");
        con.setDoOutput(true);
        con.setDoInput(true);
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Accept", "application/json");


        // Send post request
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(data);
        wr.flush();
        wr.close();

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response.toString();
    }
}
