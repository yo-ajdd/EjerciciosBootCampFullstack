package com.campusdual.ejercicio4;

import java.util.ArrayList;

public class Diet {

    public static final Boolean ALLOW_INTAKES_DUPLICATES = false;
    public ArrayList<Intake> intakes = new ArrayList<Intake>();

    private Integer maxCalories = 0;
    private Integer maxFats = 0;
    private Integer maxCarbos = 0;
    private Integer maxProteins = 0;

    public Diet(){

    }

    public Diet(Integer maxCalories) {
        this.maxCalories = maxCalories;
    }

    public Integer getMaxCalories()
    {
        return this.maxCalories;
    }

    public Diet(Integer maxCarbos, Integer maxFats, Integer maxProteins) {
        this.maxFats = maxFats;
        this.maxCarbos = maxCarbos;
        this.maxProteins = maxProteins;
    }

    public Diet(Boolean women, Integer age, Integer height, Integer weight) {
        Integer tmb;

        if (women)
        {
            this.maxCalories = Math.toIntExact(Math.round((10 * weight) + (6.25 * height) - (5 * age) - 161));
        } else {
            this.maxCalories = Math.toIntExact(Math.round((10 * weight) + (6.25 * height) - (5 * age) + 5));
        }

    }

    public Integer getMaxFats() {
        return maxFats;
    }

    public void setMaxFats(Integer maxFats) {
        this.maxFats = maxFats;
    }

    public Integer getMaxCarbos() {
        return maxCarbos;
    }

    public void setMaxCarbos(Integer maxCarbos) {
        this.maxCarbos = maxCarbos;
    }

    public Integer getMaxProteins() {
        return maxProteins;
    }

    public void setMaxProteins(Integer maxProteins) {
        this.maxProteins = maxProteins;
    }

    public String getAllIntakes() {
        String result = "";

        for (Intake element:this.intakes) {
            result = result + element.getName() + ": Peso:" + element.getGrams() + " " + " Carbos.: " + element.calculatedCarbos()
                    + " Grasas: " + element.calculatedFats() + " Proteinas: " + element.calculatedProteins() + " Calorias: " + element.calculatedCalories()
            + System.lineSeparator();
        }

        return result;
    }

    public void addFood(Food food, Integer weight) throws Exception {
        Intake intake = new Intake(food,weight);

        if (this.maxCalories != 0)
        {
            // Limite por calorias
            if (this.getTotalCalories() + intake.calculatedCalories() > this.maxCalories)
            {
                throw new Exception("Alimento " + intake.getName() + " ignorado. Se ha excedido el limite de calorias. Valor actual: " + this.getTotalCalories()
                        + " Limite: " + this.maxCalories + System.lineSeparator());
            }
            else
            {
                addIntakeToDiet(intake,weight);
            }
        } else if (this.maxFats != 0 || this.maxCarbos != 0 || this.maxProteins != 0) {
            // Limite macros
            String message = "Alimento " + intake.getName() + " ignorado:" + System.lineSeparator();
            if (this.getTotalFats() + intake.calculatedFats() > this.maxFats) {
                message += "Se ha excedido el limite de grasas. Valor actual: " + this.getTotalFats() + " Limite: " + this.maxFats + System.lineSeparator();
            }
            if (this.getTotalCarbos() + intake.calculatedCarbos() > this.maxCarbos) {
               message += "Se ha excedido el limite de carbohidratos. Valor actual: " + this.getTotalCarbos() + " Limite: " + this.maxCarbos + System.lineSeparator();
            }
            if (this.getTotalProteins() + intake.calculatedProteins() > this.maxProteins) {
               message += "Se ha excedido el limite de proteinas. Valor actual: " + this.getTotalProteins() + " Limite: " + this.maxProteins + System.lineSeparator();
            }
            if (!message.isEmpty())
            {
                throw new Exception(message);
            }
            addIntakeToDiet(intake,weight);
        } else {
            // Sin limite
            addIntakeToDiet(intake,weight);
        }
    }

    public Integer getTotalCalories()
    {
        Integer total=0;

        for (Intake intake:intakes)
        {
            total += intake.getCalories(intake.getGrams());
        }

        return total;
    }

    public Integer getTotalCarbos()
    {
        Integer total=0;

        for (Intake intake:intakes)
        {
            total += intake.getCarbos() * intake.getGrams()/100;
        }

        return total;
    }

    public Integer getTotalFats()
    {
        Integer total=0;

        for (Intake intake:intakes)
        {
            total += intake.getFats() * intake.getGrams()/100;
        }

        return total;
    }

    public Integer getTotalProteins()
    {
        Integer total=0;

        for (Intake intake:intakes)
        {
            total += intake.getProteins() * intake.getGrams()/100;
        }

        return total;
    }

    public Integer getTotalWeight()
    {
        Integer total=0;

        for (Intake intake:intakes)
        {
            total += intake.getGrams();
        }

        return total;
    }

    private void addFoodOkMessage(Intake intake)
    {
        if (intake == null) {
            return;
        }

        System.out.println("Alimento añadido a la dieta:");
        System.out.println(intake.getName() + " Peso: " + intake.getGrams() + " Carbos: " + intake.calculatedCarbos() + " Grasas: " + intake.calculatedFats()
                + " Proteins: " + intake.calculatedProteins() + " Calorias: " + intake.calculatedCalories());
    }

    private void addIntakeToDiet(Food food, Integer weight)
    {
        if (ALLOW_INTAKES_DUPLICATES == false && !this.intakes.isEmpty()) {
            Boolean found = false;
            // Si coincide el nombre sumo valores
            for (Intake intake : intakes){
                if (intake.getName().contains(food.getName())) {
                    intake.setCarbos(intake.getCarbos() + food.getCarbos());
                    intake.setFats(intake.getFats() + food.getFats());
                    intake.setProteins(intake.getProteins() + food.getProteins());
                    intake.setGrams(intake.getGrams() + weight);
                    found = true;
                    System.out.println("Alimento modificado");
                }
            }
            // Si no lo encontre es nuevo, añado
            if (!found) {
                Intake intake = new Intake(food.getName(),food.getCarbos(),food.getFats(),food.getProteins(),weight);
                intakes.add(intake);
                addFoodOkMessage(intake);
            }
        }
        else
        {
            // Lista vacia. Añado y listo....
            Intake intake = new Intake(food.getName(),food.getCarbos(),food.getFats(),food.getProteins(),weight);
            intakes.add(intake);
            addFoodOkMessage(intake);
        }
    }
}



