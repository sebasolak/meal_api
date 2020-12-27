package com.example.meal.model.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MealDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long autoId;
    private Long idMeal;
    private String strMeal;
    private String username;

    public MealDto(Long idMeal, String strMeal, String username) {
        this.idMeal = idMeal;
        this.strMeal = strMeal;
        this.username = username;
    }

    public MealDto() {
    }

    public Long getAutoId() {
        return autoId;
    }

    public void setAutoId(Long autoId) {
        this.autoId = autoId;
    }

    public Long getIdMeal() {
        return idMeal;
    }

    public void setIdMeal(Long idMeal) {
        this.idMeal = idMeal;
    }

    public String getStrMeal() {
        return strMeal;
    }

    public void setStrMeal(String strMeal) {
        this.strMeal = strMeal;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
