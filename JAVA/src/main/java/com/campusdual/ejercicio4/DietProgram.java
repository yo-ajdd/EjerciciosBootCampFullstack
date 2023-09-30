package com.campusdual.ejercicio4;

import java.util.ArrayList;
import java.util.Scanner;

public class DietProgram {
    public static final Boolean TESTING = true;
    Scanner keyb = new Scanner(System.in);
    ArrayList<Food> foods = new ArrayList<Food>();
    Diet diet = null;

    public void showMainMenu() {
        Integer menuoption;

        do {
            System.out.println("====== DIETAS ======");
            System.out.println("** MENU PRINCIPAL **");
            if (diet == null) {
                System.out.println("1. Crear Dieta");
            } else {
                System.out.println("1. Reiniciar Dieta");
            }
            System.out.println("2. Mostrar información");
            System.out.println("3. Agregar alimento");
            System.out.println("--");
            System.out.println("0. SALIR");
            System.out.println("");
            menuoption = getMenuInt(1, 3);

            switch (menuoption) {
                case 1:
                    if (diet == null) {
                        // crear
                        try {
                            showCreateResetDietMenu();
                        } catch (Exception ex) {
                            System.out.println(ex.getMessage());
                        }
                    } else {
                        // reiniciar
                        String userdata = null;
                        userdata = getInputChoose("Está seguro de querer reiniciar la dieta? ", "(s/n):", "SN");
                        if (userdata.equalsIgnoreCase("s")) {
                            showCreateResetDietMenu();
                        }
                    }
                    break;
                case 2:
                    showDietInfo();
                    break;
                case 3:
                    addIntake();
                    break;
                case 0:
                    System.out.println(">> Hasta pronto!!");
                    break;
            }
        } while (menuoption != 0);
    }

    private void showCreateResetDietMenu() {
        Integer menuoption;
        Integer userdata;

        do {
            System.out.println("====== DIETAS ======");
            System.out.println("** OPCION 1. CREAR DIETA **");
            System.out.println("1. Sin Limite");
            System.out.println("2. Por Calorias");
            System.out.println("3. Por Macronutrientes");
            System.out.println("4. Por Datos Personales");
            System.out.println("--");
            System.out.println("0. SALIR");
            System.out.println("");
            menuoption = getMenuInt(1, 4);

            switch (menuoption) {
                case 1:
                    diet = new Diet();
                    System.out.println("Se ha creado una dieta sin limite de calorias. Disfrútela!!");
                    if (TESTING) {
                        insertDummyFood();
                    }
                    menuoption = 0;
                    break;
                case 2:
                    System.out.println("====== DIETAS ======");
                    System.out.println("** OPCION 1.1. CREAR DIETA POR CALORIAS **");
                    userdata = getInputInt("Calorias Maximas:");
                    diet = new Diet(userdata);
                    System.out.println("Se ha creado una dieta limitada a " + diet.getMaxCalories() + " calorias. Cuidadin!!");
                    if (TESTING) {
                        insertDummyFood();
                    }
                    menuoption = 0;
                    break;
                case 3:
                    Integer carbos;
                    Integer fats;
                    Integer proteins;
                    carbos = getInputInt("Carbohidratos:");
                    fats = getInputInt("Grasas:");
                    proteins = getInputInt("Proteinas:");
                    diet = new Diet(carbos, fats, proteins);
                    System.out.println("Se ha creado una dieta limitada a: ");
                    System.out.println("Carbohidratos: " + diet.getMaxCarbos() + " Grasas: " + diet.getMaxFats()
                            + " Proteinas: " + diet.getMaxProteins());
                    if (TESTING) {
                        insertDummyFood();
                    }
                    menuoption = 0;
                    break;
                case 4:
                    Boolean women;
                    Integer age;
                    Integer height;
                    Integer weight;
                    String userGender;
                    userGender = getInputChoose("Nacido Hombre o Mujer? ", "(h/m):", "HM");
                    women = userGender.equalsIgnoreCase("m");
                    age = getInputInt("Edad:");
                    height = getInputInt("Altura:");
                    weight = getInputInt("Peso:");
                    diet = new Diet(women, age, height, weight);
                    System.out.print("Se ha creado una dieta para ");
                    if (women) {
                        System.out.print(" una mujer ");
                    } else {
                        System.out.print(" un hombre ");
                    }
                    System.out.println("de acuerdo con los siguientes datos:");
                    System.out.println("Edad: " + age + " Altura: " + height
                            + " Peso: " + weight + ". Le corresponde un máximo de calorias de: " + diet.getMaxCalories());
                    if (TESTING) {
                        insertDummyFood();
                    }
                    menuoption = 0;
                    break;
            }
        } while (menuoption != 0);
    }

    private void showDietInfo() {
        System.out.println("====== DIETAS ======");
        System.out.println("** OPCION 2. DETALLE DE LA DIETA **");
        System.out.println("- LISTA DE ALIMENTOS -");
        System.out.println(diet.getAllIntakes());
        System.out.println("- TOTALES DE LA DIETA -");
        System.out.print("Calorias: " + diet.getTotalCalories());
        System.out.print(" Carbohidratos: " + diet.getTotalCarbos());
        System.out.print(" Grasas: " + diet.getTotalFats());
        System.out.print(" Proteinas: " + diet.getTotalProteins());
        System.out.println(" Peso: " + diet.getTotalWeight());
        System.out.println("-------------------");
    }

    private void addIntake() {
        Integer menuoption;
        Integer userdata;
        Integer weight;
        Food food = null;

        if (diet == null) {
            System.out.println("Para añadir alimentos primero ha de crear una dieta.");
            return;
        }

        do {
            System.out.println("====== DIETAS ======");
            System.out.println("** OPCION 3. AÑADIR ALIMENTO **");
            System.out.println("1. Nuevo Alimento");
            System.out.println("2. Alimento Existente");
            System.out.println("--");
            System.out.println("0. SALIR");
            System.out.println("");
            menuoption = getMenuInt(1, 2);
            switch (menuoption) {
                case 1:
                    // Nuevo alimento
                    String name;
                    Integer carbos;
                    Integer fats;
                    Integer proteins;
                    System.out.println("====== DIETAS ======");
                    System.out.println("** OPCION 2.1 AÑADIR NUEVO ALIMENTO **");
                    name = getInputStr("Nombre:");
                    carbos = getInputInt("Carbohidratos:");
                    fats = getInputInt("Grasas:");
                    proteins = getInputInt("Proteinas:");
                    // crea un nuevo alimento y lo añade a la lista de alimentos
                    food = new Food(name, carbos, fats, proteins);
                    foods.add(food);
                    // necesito el peso para crear la ingesta y añadirlo a la dieta...si puedo...
                    System.out.println("Nuevo alimento: ");
                    System.out.println(food.getName() + " - Carb.: " + food.getCarbos() + " Grasas: " + food.getFats() + " Proteinas:"
                            + food.getProteins() + " Calorias (100gr.): " + food.getCalories(100));
                    System.out.println("--");
                    System.out.println("Indique la cantidad de este alimento que desea añadir a la dieta");
                    weight = getInputInt("Peso:");
                    try {
                        diet.addFood(food, weight);
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case 2:
                    // Existente
                    Integer i;
                    System.out.println("Escoge un alimento de la lista para añadir a la dieta");
                    for (i = 0; i < foods.size(); i++) {
                        System.out.println(i + 1 + ". " + foods.get(i).getName());
                    }
                    System.out.println("---");
                    menuoption = getMenuInt(1, i);
                    weight = getInputInt("Peso de " + foods.get(i).getName() + ":");
                    if (menuoption != 0 && weight != 0) {
                        try {
                            diet.addFood(foods.get(menuoption - 1), weight);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    break;
                case 3:
                    break;
            }
        } while (menuoption != 0);
    }

    // only for testing
    private void insertDummyFood() {

        Food food = new Food("a1", 10, 20, 30);
        try {
            foods.add(food);
            diet.addFood(food, 350);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        food = new Food("a1", 20, 30, 40);
        try {
            foods.add(food);
            diet.addFood(food, 350);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        food = new Food("a2", 30, 40, 50);
        try {
            foods.add(food);
            diet.addFood(food, 250);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        System.out.println("** Alimentos de prueba añadidos a la dieta");
    }


    private Integer getInputInt(String message) {
        Integer userdata = null;

        do {
            System.out.println(message);
            if (keyb.hasNextInt()) {
                userdata = keyb.nextInt();
                if (userdata < 0) {
                    userdata = null;
                }
            } else {
                System.out.println("Por favor, introduzca un numero entero positivo");
            }
            keyb.nextLine();
        } while (userdata == null);

        return userdata;
    }

    private String getInputStr(String message) {
        String userdata = null;

        do {
            System.out.println(message);
            if (keyb.hasNextLine()) {
                userdata = keyb.nextLine();
            } else {
                System.out.println("Se ha producido un error");
            }
        } while (userdata == null);

        return userdata;
    }

    private String getInputChoose(String message, String messageOptions, String optionsToCompare) {
        String userdata = null;

        do {
            System.out.println(message + "  " + messageOptions);
            userdata = keyb.nextLine();
            if (!optionsToCompare.contains(userdata.toUpperCase())) {
                System.out.println("Opcion incorrecta");
            }
        } while (!optionsToCompare.contains(userdata.toUpperCase()));

        return userdata;
    }

    private Integer getMenuInt(Integer minOption, Integer maxOption) {
        Integer userdata = null;
        Integer count = 0;
        String message = "Escoge una opcion (" + minOption + "-" + maxOption + ") (0 PARA SALIR):";

        do {
            System.out.println(message);
            if (keyb.hasNextInt()) {
                userdata = keyb.nextInt();
                if ((userdata != 0) && !(userdata >= minOption && userdata <= maxOption)) {
                    userdata = null;
                }
            }
            if (userdata == null) {
                System.out.println(getFunnyMessage(count, minOption, maxOption));
                if (count == 9) {
                    userdata = 0;
                }
            }
            count++;
            keyb.nextLine();
        } while (userdata == null);

        return userdata;
    }

    private String getFunnyMessage(Integer count, Integer minOption, Integer maxOption) {
        ArrayList<String> funnyMessages = new ArrayList<String>();

        funnyMessages.add("Venga, tú puedes, un número entre 0 y " + maxOption);
        funnyMessages.add("No es tan complicado....0-" + maxOption);
        funnyMessages.add("Es lunes?, dormiste mal?.....");
        funnyMessages.add("Tienes algún problema en los dedos??");
        funnyMessages.add("Me estoy mosqueando....");
        funnyMessages.add("Me estás tomando el pelo?");
        funnyMessages.add("No tengo tiempo para esto....");
        funnyMessages.add("Y seguimos para bingo....anda que...");
        funnyMessages.add("Otra y verás...");
        funnyMessages.add("Hala, ya me cansé!!");

        return funnyMessages.get(count);
    }
}
