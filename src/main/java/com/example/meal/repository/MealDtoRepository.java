package com.example.meal.repository;

import com.example.meal.model.dto.MealDto;
import com.example.meal.model.meal.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository("mealDb")
public interface MealDtoRepository extends JpaRepository<MealDto, Long> {

    @Query(value = "Select count(*) from meal_dto WHERE id_meal like ?1 and username like ?2", nativeQuery = true)
    int isAlreadyMealSaved(Long id_meal, String username);

    @Query(value = "Select id_meal from meal_dto WHERE username like ?1", nativeQuery = true)
    List<Long> selectAllIdMealFromCurrentUser(String username);

    @Modifying
    @Transactional
    @Query(value = "Delete from meal_dto WHERE id_meal like ?1 and username like ?2", nativeQuery = true)
    int deleteMealFromDB(Long id_meal, String username);
}
