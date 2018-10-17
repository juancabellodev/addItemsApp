package com.juanapps.shopwme;

public class GroceryStore {

    String groceryStoreName;
    int textViewBackgroundColor;
    int addBackgroundColor;
    int addTextColor;
    String storeName;

    public void setGroceryStoreName(String groceryStoreName) {
        this.groceryStoreName = groceryStoreName;
    }

    public void setTextViewBackgroundColor(int textBackgroundColor){
        this.textViewBackgroundColor = textBackgroundColor;

    }

    public void setAddBackgroundColor(int addBackgroundColor) {
        this.addBackgroundColor = addBackgroundColor;
    }

    public void setTextColor(int addTextColor) {
        this.addTextColor = addTextColor;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }
}

