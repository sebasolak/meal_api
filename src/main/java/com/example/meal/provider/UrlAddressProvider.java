package com.example.meal.provider;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UrlAddressProvider {

    @Value("${meal.name.url}")
    private String mealNameUrl;
    @Value("${meal.categories.url}")
    private String mealCategoriesUrl;
    @Value("${meal.id.url}")
    private String mealIdUrl;
    @Value("${meal.category.url}")
    private String mealCategoryUrl;
    @Value("${meal.firstLetter.url}")
    private String mealFirstLetterUrl;


    public String getMealNameUrl() {
        return mealNameUrl;
    }

    public String getMealCategoriesUrl() {
        return mealCategoriesUrl;
    }

    public String getMealIdUrl() {
        return mealIdUrl;
    }

    public String getMealCategoryUrl() {
        return mealCategoryUrl;
    }

    public String getMealFirstLetterUrl() {
        return mealFirstLetterUrl;
    }
}
