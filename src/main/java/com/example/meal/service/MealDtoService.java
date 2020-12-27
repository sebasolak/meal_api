package com.example.meal.service;

import com.example.meal.model.dto.MealDto;
import com.example.meal.model.meal.Meal;
import com.example.meal.repository.MealDtoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class MealDtoService {

    private final MealDtoRepository mealDtoRepository;

    @Autowired
    public MealDtoService(@Qualifier("mealDb") MealDtoRepository mealDtoRepository) {
        this.mealDtoRepository = mealDtoRepository;
    }

    public int saveMeal(Meal[] meals, String username) {
        Meal meal = meals[0];
        MealDto mealDto = new MealDto(meal.getIdMeal(), meal.getStrMeal(), username);
        if (mealDtoRepository.isAlreadyMealSaved(meal.getIdMeal(), username) == 0) {
            mealDtoRepository.save(mealDto);
            return 1;
        }
        return 0;
    }

    public int deleteMealById(Long mealId, String username) {
        if (mealDtoRepository.isAlreadyMealSaved(mealId, username) == 1) {
            return mealDtoRepository.deleteMealFromDB(mealId, username);
        }
        return 0;
    }
}
