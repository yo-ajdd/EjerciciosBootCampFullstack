package com.campusdual.ejercicio6.exceptions;

public class CustomerRemoveFromFileException extends CustomerException {

    public CustomerRemoveFromFileException() {
        super("No se ha podido borrar el cliente del fichero customers.txt");
    }
}
