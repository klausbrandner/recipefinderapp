package com.a5_designs.recipefinder;

import java.io.Serializable;

/**
 * Created by Phil on 01.01.2017.
 */
public class Ingredient implements Serializable {

    private String title;
    private String quantity;

    public Ingredient(String title, String quantity) {
        this.title = title;
        this.quantity = quantity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "title='" + title + '\'' +
                ", quantity='" + quantity + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ingredient that = (Ingredient) o;

        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        return quantity != null ? quantity.equals(that.quantity) : that.quantity == null;

    }

}
