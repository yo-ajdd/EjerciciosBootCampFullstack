package com.campusdual.ejercicio6.exceptions;

public class CustomerAddToFileException extends CustomerException {

    public CustomerAddToFileException() {
        super("No se ha podido guardar el cliente en el fichero customers.txt");
    }
}
