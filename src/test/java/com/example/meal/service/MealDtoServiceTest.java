package com.example.meal.service;

import com.example.meal.model.dto.MealDto;
import com.example.meal.model.meal.Meal;
import com.example.meal.repository.MealDtoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class MealDtoServiceTest {

    private MealDtoRepository mealDtoRepository;
    private MealDtoService mealDtoService;

    @BeforeEach
    void setUp() {
        mealDtoRepository = Mockito.mock(MealDtoRepository.class);
        mealDtoService = new MealDtoService(mealDtoRepository);
    }

    @Test
    void shouldSaveMeal() {
        //given
        Meal[] meals = {
                new Meal((long) 52777,
                        "Mediterranean Pasta Salad",
                        "Seafood",
                        "Italian")
        };
        //when
        when(mealDtoRepository.save(Mockito.any(MealDto.class)))
                .thenAnswer(i -> i.getArguments()[0]);

        ArgumentCaptor<MealDto> captor = ArgumentCaptor.forClass(MealDto.class);
        mealDtoService.saveMeal(meals, "username");
        //then
        verify(mealDtoRepository).save(captor.capture());
        verify(mealDtoRepository).isAlreadyMealSaved((long) 52777, "username");
    }

    @Test
    void shouldDeleteMeal() {
        //given
        given(mealDtoRepository.deleteMealFromDB(any(Long.class), any(String.class)))
                .willReturn(1);
        given(mealDtoRepository.isAlreadyMealSaved(any(Long.class), any(String.class)))
                .willReturn(1);
        //when
        mealDtoService.deleteMealById((long) 52777, "username");
        //then
        verify(mealDtoRepository).deleteMealFromDB((long) 52777, "username");
        verify(mealDtoRepository).isAlreadyMealSaved((long) 52777, "username");
    }
}