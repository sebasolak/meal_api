package com.example.meal.model.meal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MealCompact {

    private final Long idMeal;
    private final String strMeal;
    private final String strInstructions;

    public MealCompact(Long idMeal, String strMeal, String strInstructions) {
        this.idMeal = idMeal;
        this.strMeal = strMeal;
        this.strInstructions = strInstructions;
    }

    public Long getIdMeal() {
        return idMeal;
    }
    @JsonProperty("name")
    public String getStrMeal() {
        return strMeal;
    }
    @JsonProperty("recipe")
    public String getStrInstructions() {
        return strInstructions;
    }

    @Override
    public String toString() {
        return "MealCompact{" +
                "idMeal=" + idMeal +
                ", strMeal='" + strMeal + '\'' +
                ", strInstructions='" + strInstructions + '\'' +
                '}';
    }
}
