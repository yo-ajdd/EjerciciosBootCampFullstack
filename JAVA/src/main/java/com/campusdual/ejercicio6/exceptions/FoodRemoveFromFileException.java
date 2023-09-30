package com.campusdual.ejercicio6.exceptions;

public class FoodRemoveFromFileException extends CustomerException {

    public FoodRemoveFromFileException() {
        super("No se ha podido borrar el cliente del fichero customers.txt");
    }
}
