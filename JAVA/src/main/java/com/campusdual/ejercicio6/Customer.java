package com.campusdual.ejercicio6;

import com.campusdual.ejercicio6.enums.Gender;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class Customer {
    //nombre, apellidos, peso, altura, edad y sexo

    private String name;
    private String surname;
    private Integer weight;
    private Integer height;
    private Integer age;
    private Gender gender;

    private static final String FILE_PATH = "src/main/resources/com/campusdual/ejercicio6/ficheros/customers.txt";

    private HashMap<Integer,String> customerDiets;

    public Customer() {
        customerDiets = new HashMap<>();
    }

    public Customer(String name, String surname, Integer weight, Integer height, Integer age, String gender) {
        this.name = name;
        this.surname = surname;
        this.weight = weight;
        this.height = height;
        this.age = age;
        this.gender = Gender.getByString(gender);
        customerDiets = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public HashMap<Integer, String> getCustomerDiets() {
        return customerDiets;
    }

    public void setCustomerDiets(HashMap<Integer, String> customerDiets) {
        this.customerDiets = customerDiets;
    }



    public String getCustomerDetails() {
        String message;
        message = "Datos del paciente" + System.lineSeparator();
        message += "Nombre: " + name + " " + surname + " Peso: " + weight + " Altura: " + height + " Edad: "
                + age + " Género: " + gender;
        return message;
    }

   public Boolean Save(String filePath) {
        String line;
        Integer i;
        String customerData;
        File customerFile = new File(filePath);
        try(PrintWriter pw = new PrintWriter(new FileWriter(customerFile,true)))  {
            // Preparo los datos del cliente
            customerData = this.getName() + "," + this.getSurname() + "," + this.getWeight() + "," + getHeight()
                    + "," +  this.getAge() + "," + this.getGender();
           for (i=1;i <= 7;i++) {
               customerData += "," + this.getCustomerDiets().get(i-1);
           }
           pw.println(customerData);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }





}
