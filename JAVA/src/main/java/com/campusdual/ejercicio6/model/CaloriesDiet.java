package com.campusdual.ejercicio6.model;

import com.campusdual.ejercicio6.Food;
import com.campusdual.ejercicio6.Intake;
import com.campusdual.ejercicio6.exceptions.*;

public class CaloriesDiet extends Diet {

    // El resto de propiedades y metodos van en la clase padre
    private Integer maxCalories;

    public CaloriesDiet(Integer maxCalories)
    {
        this.maxCalories = maxCalories;
    }
    @Override
    public void addFood(Food food, Integer grams) throws MaxValuedReachedException {
        Intake intake = new Intake(food,grams);
        if (this.getTotalCalories() + intake.calculatedCalories() > this.maxCalories) {
            throw new MaxCaloriesReachedException();
        }

    }
}
