package com.example.meal.service;

import com.example.meal.model.category.Category;
import com.example.meal.model.category.CategoryArray;
import com.example.meal.model.meal.Meal;
import com.example.meal.model.meal.MealCompact;
import com.example.meal.model.meal.MealsArray;
import com.example.meal.provider.UrlAddressProvider;
import com.example.meal.repository.MealDtoRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MealService {

    private final DeserializeService deserializeService;
    private final UrlAddressProvider addressProvider;
    private final Gson gson;
    private final MealDtoRepository mealDtoRepository;

    @Autowired
    public MealService(DeserializeService deserializeService,
                       UrlAddressProvider addressProvider,
                       Gson gson,
                       MealDtoRepository mealDtoRepository) {
        this.deserializeService = deserializeService;
        this.addressProvider = addressProvider;
        this.gson = gson;
        this.mealDtoRepository = mealDtoRepository;
    }

    public Meal[] selectMealByName(String mealName) {
        String s = deserializeService.deserializeJson(addressProvider.getMealNameUrl() + mealName);
        MealsArray mealsArray = gson.fromJson(s, MealsArray.class);
        return mealsArray.getMeals();
    }

    public Category[] selectAllMealsCategories() {
        String s = deserializeService.deserializeJson(addressProvider.getMealCategoriesUrl());
        CategoryArray categoryArray = gson.fromJson(s, CategoryArray.class);
        return categoryArray.getCategories();
    }

    public Meal[] selectMealById(Long mealId) {
        String s = deserializeService.deserializeJson(addressProvider.getMealIdUrl() + mealId);
        MealsArray mealsArray = gson.fromJson(s, MealsArray.class);
        if (mealsArray.getMeals() == null) {
            throw new IllegalArgumentException(String.format("Id %d is incorrect", mealId));
        }
        return mealsArray.getMeals();
    }

    public Meal[] selectMealsByCategory(String mealsCategory) {
        String s = deserializeService.deserializeJson(addressProvider.getMealCategoryUrl() + mealsCategory);
        MealsArray mealsArray = gson.fromJson(s, MealsArray.class);
        return mealsArray.getMeals();
    }

    public Meal[] selectMealsByFirstLetter(String firstLetter) {
        String s = deserializeService.deserializeJson(addressProvider.getMealFirstLetterUrl() + firstLetter);
        MealsArray mealsArray = gson.fromJson(s, MealsArray.class);
        return mealsArray.getMeals();
    }

    public List<MealCompact> selectAllMealByUsername(String username) {
        return mealDtoRepository.selectAllIdMealFromCurrentUser(username)
                .stream().map(id -> {
                    Meal meal = selectMealById(id)[0];
                    return new MealCompact(meal.getIdMeal(), meal.getStrMeal(), meal.getStrInstructions());
                }).collect(Collectors.toList());
    }

}
