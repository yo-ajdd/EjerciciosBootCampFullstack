package com.campusdual.ejercicio6.exceptions;

public class FoodLoadFromFileException extends CustomerException {

    public FoodLoadFromFileException() {
        super("No se han podido recuperar los datos del fichero food.txt");
    }
}
