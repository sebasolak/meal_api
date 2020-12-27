package com.example.meal.service;

import com.example.meal.controller.MealController;
import com.example.meal.model.category.Category;
import com.example.meal.model.meal.Meal;
import com.example.meal.model.meal.MealCompact;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

class MealControllerTest {

    private MealService mealService;
    SecurityContextHolderService contextHolderService;
    private MealController mealController;

    @BeforeEach
    void beforeEach() {
        mealService = Mockito.mock(MealService.class);
        contextHolderService = Mockito.mock(SecurityContextHolderService.class);
        mealController = new MealController(mealService, null, contextHolderService, null, null);
    }


    @Test
    void shouldSelectMealByName() {
        //given
        Meal[] meals = {
                new Meal((long) 52771, "Spicy Arrabiata Penne", "Vegetarian", "Italian")
        };
        given(mealService.selectMealByName("Arrabiata")).willReturn(meals);

        //then
        Meal[] arrabiatas = mealController.getMealByName("Arrabiata");

        //when
        assertThat(arrabiatas).hasSize(1);
        assertThat(arrabiatas[0].getIdMeal()).isEqualTo(52771);
        assertThat(arrabiatas[0].getStrMeal()).isEqualTo("Spicy Arrabiata Penne");
        assertThat(arrabiatas[0].getStrCategory()).isEqualTo("Vegetarian");
        assertThat(arrabiatas[0].getStrArea()).isEqualTo("Italian");
    }

    @Test
    void shouldSelectAllMealCategories() {
        //given
        Category[] categories = {
                new Category("Beef", "Beef is cow"),
                new Category("Chicken", "Chicken is bird"),
                new Category("Pasta", "Pasta is Greek")};
        given(mealService.selectAllMealsCategories()).willReturn(categories);

        //then
        Category[] allMealsCategories = mealController.getAllMealsCategories();

        //when
        assertThat(allMealsCategories).hasSize(3);
        assertThat(allMealsCategories).extracting("strCategory").contains("Beef", "Chicken", "Pasta");
        assertThat(allMealsCategories).extracting("strCategoryDescription")
                .contains("Beef is cow", "Chicken is bird", "Pasta is Greek");
    }

    @Test
    void shouldSelectMealById() {
        //given
        Meal[] meals = {
                new Meal((long) 52771, "Spicy Arrabiata Penne", "Vegetarian", "Italian")
        };
        given(mealService.selectMealById((long) 52771)).willReturn(meals);

        //then
        Meal[] mealById = mealController.getMealById((long) 52771);

        //when
        assertThat(mealById).hasSize(1);
        assertThat(mealById[0].getIdMeal()).isEqualTo(52771);
        assertThat(mealById[0].getStrMeal()).isEqualTo("Spicy Arrabiata Penne");
        assertThat(mealById[0].getStrCategory()).isEqualTo("Vegetarian");
        assertThat(mealById[0].getStrArea()).isEqualTo("Italian");
    }

    @Test
    void shouldThrowAnExceptionWhenIdIsIncorrect() {
        given(mealService.selectMealById((long) 1))
                .willThrow(new IllegalArgumentException("Id 1 is incorrect"));

        assertThatThrownBy(() -> mealController.getMealById((long) 1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Id 1 is incorrect");
    }

    @Test
    void shouldSelectMealsByCategory() {
        //given
        Meal[] mealsByCategory = {
                new Meal((long) 52874, "Beef and Mustard Pie", null, null),
                new Meal((long) 52878, "Beef and Oyster pie", null, null),
                new Meal((long) 52997, "Beef Banh Mi Bowls with Sriracha Mayo", null, null),
                new Meal((long) 52904, "Beef Bourguignon", null, null),
                new Meal((long) 52812, "Beef Brisket Pot Roast", null, null)
        };
        given(mealService.selectMealsByCategory("Beef")).willReturn(mealsByCategory);

        //then
        Meal[] meals = mealController.getMealsByCategory("Beef");

        //when
        assertThat(meals).hasSize(5);
        assertThat(meals).extracting("idMeal").contains(52874L, 52878L, 52997L, 52904L, 52812L);
        assertThat(meals).extracting("strMeal").contains("Beef and Mustard Pie", "Beef and Oyster pie",
                "Beef Banh Mi Bowls with Sriracha Mayo", "Beef Bourguignon", "Beef Brisket Pot Roast");

    }

    @Test
    void shouldSelectMealsByFirstLetter() {
        //given
        Meal[] mealsByFirstLetter = {
                new Meal((long) 52768, "Apple Frangipan Tart", "Dessert", "British"),
                new Meal((long) 52893, "Apple & Blackberry Crumble", "Dessert", "British")
        };
        given(mealService.selectMealsByFirstLetter("a")).willReturn(mealsByFirstLetter);

        //then
        Meal[] meals = mealController.getMealsByFirstLetter("a");

        //when
        assertThat(meals).hasSize(2);
        assertThat(meals).extracting("idMeal").contains(52768L, 52893L);
        assertThat(meals).extracting("strMeal").contains("Apple Frangipan Tart", "Apple & Blackberry Crumble");
        assertThat(meals).extracting("strCategory").contains("Dessert");
        assertThat(meals).extracting("strArea").contains("British");
    }

    @Test
    void shouldSelectSaveUserMeals() {
        //given
        List<MealCompact> mealCompacts = Arrays.asList(
                new MealCompact((long) 52791, "Eton Mess", "Purée half the strawberries in a blender."),
                new MealCompact((long) 52771, "Spicy Arrabiata Penne", "Bring a large pot of water to a boil."),
                new MealCompact((long) 52893, "Apple & Blackberry Crumble", "Tip the flour and sugar into a large bowl.")

        );

        given(mealService.selectAllMealByUsername("fakeUsername"))
                .willReturn(mealCompacts);
        given(contextHolderService.getUserUsername())
                .willReturn("fakeUsername");

        //then
        List<MealCompact> allUserMeals = mealController.getAllUserMeals();

        //when
        assertThat(allUserMeals).hasSize(3);
        assertThat(allUserMeals).extracting("idMeal").contains(52791L, 52771L, 52893L);
        assertThat(allUserMeals).extracting("strMeal")
                .contains("Eton Mess", "Spicy Arrabiata Penne", "Apple & Blackberry Crumble");
        assertThat(allUserMeals).extracting("strInstructions")
                .contains("Purée half the strawberries in a blender.",
                        "Bring a large pot of water to a boil.",
                        "Tip the flour and sugar into a large bowl.");
    }
}