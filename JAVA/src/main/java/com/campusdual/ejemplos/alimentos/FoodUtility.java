package com.campusdual.ejemplos.alimentos;

public class FoodUtility {
    public static void main(String[] args) {
        Food zanahoria = new Food(12,0,1);
        System.out.println("100g. zanahoria:" + zanahoria.getCalories(100));

        Food bistec = new Food(0,15,27);
        System.out.println("100g. bistec:" + bistec.getCalories(100));

    }
}
