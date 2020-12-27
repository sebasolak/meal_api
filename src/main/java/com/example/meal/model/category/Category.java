package com.example.meal.model.category;

public class Category {

    private String strCategory;
    private String strCategoryDescription;

    public Category(String strCategory, String strCategoryDescription) {
        this.strCategory = strCategory;
        this.strCategoryDescription = strCategoryDescription;
    }

    public Category() {
    }

    public String getStrCategory() {
        return strCategory;
    }

    public String getStrCategoryDescription() {
        return strCategoryDescription;
    }
}
