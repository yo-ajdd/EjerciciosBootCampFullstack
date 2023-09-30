package com.campusdual.ejercicio6.exceptions;

public class CustomerLoadFromFileException extends CustomerException {

    public CustomerLoadFromFileException() {
        super("No se han podido recuperar los datos del fichero customers.txt");
    }
}
