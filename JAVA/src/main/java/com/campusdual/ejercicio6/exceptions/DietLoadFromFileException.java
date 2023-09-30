package com.campusdual.ejercicio6.exceptions;

public class DietLoadFromFileException extends CustomerException {

    public DietLoadFromFileException() {
        super("No se han podido recuperar los datos del fichero diets.txt");
    }
}
