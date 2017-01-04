package com.a5_designs.recipefinder;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Created by Phil on 01.01.2017.
 */
public class Recipe implements Serializable {

    private int rid;
    private String title;
    private String photo;
    private String description;
    private String preparation;
    private Ingredient[] ingredients;
    private double rating;
    private String[] categories;

    public Recipe(int rid, String title, String photo, String description, String preparation, Ingredient[] ingredients, double rating, String[] categories) {
        this.rid = rid;
        this.title = title;
        this.photo = photo;
        this.description = description;
        this.preparation = preparation;
        this.ingredients = ingredients;
        this.rating = rating;
        this.categories = categories;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPreparation() {
        return preparation;
    }

    public void setPreparation(String preparation) {
        this.preparation = preparation;
    }

    public Ingredient[] getIngredients() {
        return ingredients;
    }

    public void setIngredients(Ingredient[] ingredients) {
        this.ingredients = ingredients;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String[] getCategories() {
        return categories;
    }

    public void setCategories(String[] categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "rid=" + rid +
                ", title='" + title + '\'' +
                ", photo='" + photo + '\'' +
                ", description='" + description + '\'' +
                ", preparation='" + preparation + '\'' +
                ", ingredients=" + Arrays.toString(ingredients) +
                ", rating=" + rating +
                ", categories=" + Arrays.toString(categories) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Recipe recipe = (Recipe) o;

        if (rid != recipe.rid) return false;
        if (Double.compare(recipe.rating, rating) != 0) return false;
        if (title != null ? !title.equals(recipe.title) : recipe.title != null) return false;
        if (photo != null ? !photo.equals(recipe.photo) : recipe.photo != null) return false;
        if (description != null ? !description.equals(recipe.description) : recipe.description != null) return false;
        if (preparation != null ? !preparation.equals(recipe.preparation) : recipe.preparation != null) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (!Arrays.equals(ingredients, recipe.ingredients)) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(categories, recipe.categories);

    }

}
