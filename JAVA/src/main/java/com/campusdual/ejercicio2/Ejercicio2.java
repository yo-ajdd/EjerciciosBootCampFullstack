

package com.campusdual.ejercicio2;

import java.util.Scanner;

public class Ejercicio2 {

    public static void main(String[] args) {
        Integer year;
        Scanner teclado = new Scanner(System.in);

        System.out.println("Escriba un año:");
        year = teclado.nextInt();

        if (year % 4 != 0) {
            System.out.println("no es bisiesto");
        }
        else {

            if (year % 100 == 0) {

                if (year % 400 == 0) {
                    System.out.println("secular y bisiesto");
                } else {
                    System.out.println("secular y no bisiesto");
                }

            } else {
                System.out.println("no es bisiesto");
            }

        }

    }
}

