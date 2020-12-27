package com.example.meal.controller;

import com.example.meal.model.category.Category;
import com.example.meal.model.meal.Meal;
import com.example.meal.model.meal.MealCompact;
import com.example.meal.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("meal")
public class MealController {

    private final MealService mealService;
    private final MealDtoService mealDtoService;
    private final SecurityContextHolderService securityContextHolderService;
    private final MailService mailService;
    private final UserService userService;

    @Autowired
    public MealController(MealService mealService,
                          MealDtoService mealDtoService,
                          SecurityContextHolderService securityContextHolderService,
                          MailService mailService,
                          UserService userService) {
        this.mealService = mealService;
        this.mealDtoService = mealDtoService;
        this.securityContextHolderService = securityContextHolderService;
        this.mailService = mailService;
        this.userService = userService;
    }

    @GetMapping(
            path = "name/{mealName}"
    )
    public Meal[] getMealByName(@PathVariable String mealName) {
        return mealService.selectMealByName(mealName);
    }

    @GetMapping(
            path = "category"
    )
    public Category[] getAllMealsCategories() {
        return mealService.selectAllMealsCategories();
    }

    @GetMapping(
            path = "id/{mealId}"
    )
    public Meal[] getMealById(@PathVariable Long mealId) {
        return mealService.selectMealById(mealId);
    }

    @PostMapping(
            path = "id/{mealId}"
    )
    public void saveMeal(@PathVariable Long mealId) {
        mealDtoService.saveMeal(mealService.selectMealById(mealId), securityContextHolderService.getUserUsername());
    }

    @DeleteMapping(
            path = "id/{mealId}"
    )
    public void deleteMeal(@PathVariable Long mealId) {
        mealDtoService.deleteMealById(mealId, securityContextHolderService.getUserUsername());
    }

    @GetMapping(
            path = "category/{mealsCategory}"
    )
    public Meal[] getMealsByCategory(@PathVariable String mealsCategory) {
        return mealService.selectMealsByCategory(mealsCategory);
    }

    @GetMapping(
            path = "first/{firstLetter}"
    )
    public Meal[] getMealsByFirstLetter(@PathVariable String firstLetter) {
        return mealService.selectMealsByFirstLetter(firstLetter);
    }

    @GetMapping(
            path = "saved"
    )
    public List<MealCompact> getAllUserMeals() {
        return mealService.selectAllMealByUsername(securityContextHolderService.getUserUsername());
    }

    @GetMapping(
            path = "send"
    )
    public List<MealCompact> getAllUserMealsAndSendAnEmail() {
        String username = securityContextHolderService.getUserUsername();
        mailService.sendMail(userService.selectEmailByLogin(username),
                String.format("Hello %s! Your favourite food recipe(s)!",username),
                mealService.selectAllMealByUsername(username).toString(),
                true);
        return mealService.selectAllMealByUsername(username);
    }
}
