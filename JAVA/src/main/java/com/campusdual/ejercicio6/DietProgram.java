package com.campusdual.ejercicio6;

import com.campusdual.ejercicio6.enums.Days;
import com.campusdual.ejercicio6.enums.Gender;
import com.campusdual.ejercicio6.exceptions.*;
import com.campusdual.ejercicio6.model.Diet;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DietProgram {

    public static final Boolean ADD_TEST_DATA = true;
    private static final String CUSTOMER_FILE_PATH = "src/main/resources/com/campusdual/ejercicio6/ficheros/customers.txt";

    private static final String FOOD_FILE_PATH = "src/main/resources/com/campusdual/ejercicio6/ficheros/food.txt";

    private static final String DIET_FILE_PATH = "src/main/resources/com/campusdual/ejercicio6/ficheros/diets.txt";


    // Almacena la lista de alimentos
    private List<Food> foodList;
    // Creo un hashmap para la lista de dietas
    private HashMap<String, Diet> dietList;
    private List<Customer> customerList;

    public DietProgram() {
        foodList = new ArrayList<>();
        dietList = new HashMap<>();
        customerList = new ArrayList<>();
    }


    public void showMenuProgram(){
        if (ADD_TEST_DATA) {
            //insertDummyFood(); Para que non engada os alimentos cada vez que arranquemos
            //insertDummyDiet();
            //insertDummyCustomer();
        }
        try {
            customersLoadFromFile();
            foodsLoadFromFile();
            dietLoadFromFile();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("########################################################");
        System.out.println("################# Programa de Menús ###################");
        System.out.println("########################################################");
        Integer option;
        do {
            System.out.println("Escriba una opción:");
            System.out.println("===================================");
            System.out.println("1-Gestión de Dietas");
            System.out.println("2-Gestión de Pacientes");
            System.out.println("3-Gestión de Alimentos");
            System.out.println("4-Salir");
            option = Kb.getOption(1, 4);
            switch (option) {
                case 1:
                    dietManage();
                    break;
                case 2:
                    customerManage();
                    break;
                case 3:
                    foodManage();
                    break;
                case 4:
                    System.out.println("Gracias por usar el programa, hasta pronto :)");
                    break;
            }
        } while (option != 3);
    }

    private void dietManage() {
        Integer option;
        do {
            System.out.println("########################################################");
            System.out.println("################# Gestión de Dietas ###################");
            System.out.println("########################################################");
            System.out.println("Escriba una opción:");
            System.out.println("===================================");
            System.out.println("1-Agregar nueva dieta");
            System.out.println("2-Listar Dietas");
            System.out.println("3-Eliminar Dieta");
            System.out.println("4-Volver");
            option = Kb.getOption(1, 4);
            switch (option) {
                case 1:
                    dietCreate();
                    break;
                case 2:
                    dietShow();
                    break;
                case 3:
                    dietRemove();
                case 4:
                    break;
            }
        } while (option != 4);
    }

    private void dietCreate() {
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("Crear/reiniciar dieta");
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        Boolean validDietName;
        String dietName;
        Diet newDiet;
        do {
            System.out.println("Introduzca un nombre para la dieta:");
            dietName = Kb.nextLine();
            validDietName = !dietList.containsKey(dietName);
            if (!validDietName) {
                System.out.println("Ya existe una dieta con ese nombre");
            }
        } while (!validDietName);

        System.out.println("Escriba una opción:");
        System.out.println("===================================");
        System.out.println("1-Dieta sin límite");
        System.out.println("2-Dieta por calorías");
        System.out.println("3-Dieta por macronutrientes");
        System.out.println("4-Dieta por datos personales");
        System.out.println("5-Dieta por paciente");
        Integer option = Kb.getOption(1, 5);
        switch (option) {
            case 1:
                dietList.put(dietName, new Diet());
                System.out.println("Se ha creado una dieta sin límites");
                break;
            case 2:
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                System.out.println("Escriba número de calorias");
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                Integer calories = Kb.forceNextInt();
                dietList.put(dietName, new Diet(calories));
                System.out.println("Se ha creado una dieta con " + calories + " calorías máximas");
                break;
            case 3:
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                System.out.println("Escriba los macronutrientes");
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                System.out.println("Carbohidratos:");
                Integer carbs = Kb.forceNextInt();
                System.out.println("Grasas:");
                Integer fats = Kb.forceNextInt();
                System.out.println("Proteínas:");
                Integer proteins = Kb.forceNextInt();
                dietList.put(dietName, new Diet(fats, carbs, proteins));
                System.out.println("Se ha creado una dieta con Carbohidratos:" + carbs + ", Grasas:" + fats + " ,Proteínas:" + proteins);
                break;
            case 4:
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                System.out.println("Escriba los datos personales del paciente");
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                System.out.println("Peso:");
                Integer weight = Kb.forceNextInt();
                System.out.println("Altura:");
                Integer height = Kb.forceNextInt();
                System.out.println("Edad:");
                Integer age = Kb.forceNextInt();
                System.out.println("Mujer u Hombre(m/h):");
                String sexCharacter = Kb.nextLine();
                newDiet = new Diet(Gender.getByString(sexCharacter), age, height, weight);
                dietList.put(dietName, newDiet);
                System.out.println("Se ha creado una dieta de " + newDiet.getMaxCalories() + " calorías máximas");
                break;
            case 5:
                Customer customer = getSelectedCustomer();
                if (customer == null) {
                    System.out.println("Elemento no encontrado");
                } else {
                    newDiet = new Diet(customer.getGender(), customer.getAge(), customer.getHeight(), customer.getWeight());
                }

        }
    }

    private void dietShow() {
        // Vuelve un nombre de dieta....
        String dietname = getSelectedDiet();
        if (dietname == null) {
            System.out.println("Elemento no encontrado");
            return;
        }
        Diet diet = dietList.get(dietname);
        Integer option;
        Integer userdata;
        do {
            showDetailsMenu(diet);
            System.out.println("1-Cambiar calorias máx.");
            System.out.println("2-Cambiar carbohidratos máx.");
            System.out.println("3-Cambiar grasa máx.");
            System.out.println("4-Cambiar proteinas máx.");
            System.out.println("5-Añadir alimento");
            System.out.println("6-Volver");
            option = Kb.getOption(1, 6);
            if (option >= 1 && option <= 4) {
                updateDiet(diet, option);
            } else if (option == 5) {
                addFoodMenu(diet);
            }
        } while (option != 6);
    }

    private void dietRemove() {
        // Vuelve un nombre de dieta....
        String dietname = getSelectedDiet();
        if (dietname == null) {
            System.out.println("Elemento no encontrado");
        } else {
            // Borro la dieta solo si no está asignada
            if (dietAssigned(dietname)) {
                System.out.println("La dieta está asignada a un paciente. No se puede eliminar");
                return;
            }
            dietList.remove(dietname);
        }
    }

    public Boolean dietSave(String filePath, String dietName) {
        String line;
        Integer i;
        String dietData;
        File dietFile = new File(filePath);
        try(PrintWriter pw = new PrintWriter(new FileWriter(dietFile,true)))  {
            // Preparo los datos de la dieta
            Diet diet = dietList.get(dietName); //Nome da dieta
            dietData = dietName + "," + diet.getMaxCalories() + "," + diet.getMaxCarbs() + "," + diet.getMaxFats()
                    + "," +  diet.getMaxProteins();
            for (Intake intake: diet.getIntakes()) {
                dietData = dietData + "," + intake.getName() + ";" + intake.getCarbos() + ";" + intake.getFats() + ";" + intake.getProteins() +";" + intake.getGrams(); //Datos da dieta + nome da inxesta
            }
            pw.println(dietData);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
    private void dietLoadFromFile() throws DietLoadFromFileException {
        String line;
        Integer i;
        String dietData;
        File dietFile = new File(DIET_FILE_PATH);
        try (BufferedReader br = new BufferedReader(new FileReader(dietFile))) {
            while ((line = br.readLine()) != null) {
                String[] lineParts = line.split(","); //Esto é un String!!!!
                // Cargo os datos da dieta
                Integer maxCalories = 0;
                Integer maxCarbs = 0;
                if(lineParts[1].equalsIgnoreCase("null")){
                    maxCalories = null;
                }else{
                    maxCalories = Integer.parseInt(lineParts[1]);
                }
                if(lineParts[2].equalsIgnoreCase("null")){
                    lineParts[2] = "0";
                    maxCarbs = null;
                }else{
                    maxCarbs = Integer.parseInt(lineParts[2]);
                }
                if(lineParts[3].equalsIgnoreCase("null")){
                    lineParts[3] = "0";
                }
                if(lineParts[4].equalsIgnoreCase("null")){
                    lineParts[4] = "0";
                }
                Diet diet = new Diet(maxCalories,
                        Integer.parseInt(lineParts[2]), Integer.parseInt(lineParts[3]),
                        Integer.parseInt(lineParts[4]));
                // Ahora cargo as súas dietas
                for (i = 5; i < lineParts.length; i++) {
                    if (lineParts[i] != null) {
                        String[] intakeParts = lineParts[i].split(";"); //Partimos da 5, e como hai un punto e coma, lle temos que indicar que o ten que partir en dous
                        if(intakeParts[1].equalsIgnoreCase("null")){
                            intakeParts[1] = "0";
                        }
                        if(intakeParts[2].equalsIgnoreCase("null")){
                            intakeParts[2] = "0";
                        }
                        if(intakeParts[3].equalsIgnoreCase("null")){
                            intakeParts[3] = "0";
                        }
                        if(intakeParts[4].equalsIgnoreCase("null")){
                            intakeParts[4] = "0";
                        }
                        Intake intake = new Intake(intakeParts[0], Integer.parseInt(intakeParts[1]),
                                Integer.parseInt(intakeParts[2]),Integer.parseInt(intakeParts[3]),
                                Integer.parseInt(intakeParts[4]));
                        diet.addFood(intake, intake.getGrams()); //Á dieta lle engadimos unha comida, coa súa inxesta e os seus gramos
                    }
                }
                dietList.put(lineParts[0], diet); //Engadimos a dieta á lista, co seu nome e a dieta completa
            }
        } catch (Exception e) {
            throw new DietLoadFromFileException();
        }
    }

    private void foodManage() {
        Integer option;
        do {
            System.out.println("########################################################");
            System.out.println("################# Gestión de Alimentos ###################");
            System.out.println("########################################################");
            System.out.println("Escriba una opción:");
            System.out.println("===================================");
            System.out.println("1-Agregar nuevo alimento");
            System.out.println("2-Listar alimentos");
            System.out.println("3-Eliminar alimentos");
            System.out.println("4-Volver");
            option = Kb.getOption(1, 4);
            switch (option) {
                case 1:
                    foodCreate();
                    break;
                case 2:
                    foodShow();
                    break;
                case 3:
                    foodRemove();
                    break;
                case 4:
                    break;
            }
        } while (option != 4);
    }

    private void foodCreate() {
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("Crear alimento");
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        String foodName;
        Food newFood;
        System.out.println("Introduzca el nombre del alimento:");
        foodName = Kb.nextLine();
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("Escriba los macronutrientes");
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("Carbohidratos:");
        Integer carbs = Kb.forceNextInt();
        System.out.println("Grasas:");
        Integer fats = Kb.forceNextInt();
        System.out.println("Proteínas:");
        Integer proteins = Kb.forceNextInt();
        newFood = new Food(foodName, carbs, fats, proteins);
        foodList.add(newFood);
        newFood.save(FOOD_FILE_PATH);
        System.out.println("Se ha creado un alimento de nombre:" + foodName + ", Carbohidratos:" + carbs + ", Grasas:" + fats + " ,Proteínas:" + proteins);
    }

    private void foodShow() {
        Food food = getSelectedFood();
        if (food == null) {
            System.out.println("Elemento no encontrado");
            return;
        }
        Integer option;
        do {
            System.out.println("1-Cambiar carbohidratos máx.");
            System.out.println("2-Cambiar grasa máx.");
            System.out.println("3-Cambiar proteínas máx.");
            System.out.println("4-Volver");
            option = Kb.getOption(1, 4);
            if (option >= 1 && option <= 3) {
                updateFood(food, option);
                food.save(FOOD_FILE_PATH); //Gardamos a comida que acabamos de modificar se escollemos 1-3
            }
        } while (option != 4);
    }

    private Food getSelectedFood() {
        if (foodList.size() == 0) {
            System.out.println("No existen alimentos");
        }
        System.out.println("Escoja un alimento:");
        Integer option = 1;
        for (Food food : foodList) {
            System.out.println(option + "-" + food.getName());
            option++;
        }
        System.out.println(option + "-Volver");
        Integer selected = Kb.getOption(1, option);
        if (selected == option) {
            return null;
        } else {
            return foodList.get(selected - 1);
        }
    }

    private void updateFood(Food food, Integer option) {
        Integer userdata;
        System.out.println("Introduce nuevo valor:");
        userdata = Kb.nextInt();
        switch (option) {
            case 1:
                food.setCarbos(userdata);
                break;
            case 2:
                food.setFats(userdata);
                break;
            case 3:
                food.setProteins(userdata);
                break;
        }
    }

    private void foodRemove() {
        Food food = getSelectedFood();
        if (food == null) {
            System.out.println("Alimento no encontrado");
        } else {
            // Borro el alimento solo si no está asignado
            if (foodAssigned(food)) {
                System.out.println("El alimento está asignado a una dieta. No se puede eliminar");
                return;
            }
            foodList.remove(food);
            try {
                foodRemoveFromFile();
                System.out.println("El alimento " + food.getName() + " ha sido eliminado"); //Poñémolo aquí porque se falla o foodRemoveFromFile, non imprime que o eliminou
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private boolean foodAssigned(Food food) {
        Boolean assigned;
        for (String key : dietList.keySet()) { //Devolve a lista das claves
            Diet diet = dietList.get(key); //Devolve unha dieta
            for (Intake intake : diet.getIntakes()) { //Recorremos todas as inxestas da dieta
                if (food.getName().equalsIgnoreCase(intake.getName())) { //Comparamos o nome da comida e da inxesta
                    return true;  //Se son iguais, está asignado
                }
            }
        }
        return false;
    }

    private void customersLoadFromFile() throws CustomerLoadFromFileException {
        String line;
        Integer i;
        String customerData;
        File customerFile = new File(CUSTOMER_FILE_PATH);
        try (BufferedReader br = new BufferedReader(new FileReader(customerFile))) {
            while ((line = br.readLine()) != null) {
                String[] lineParts = line.split(",");
                // Cargo los datos del cliente
                Customer customer = new Customer(lineParts[0], lineParts[1], Integer.parseInt(lineParts[2]),
                        Integer.parseInt(lineParts[3]), Integer.parseInt(lineParts[4]), lineParts[5]);
                // Ahora cargo sus dietas
                for (i = 6; i < lineParts.length; i++) {
                    if (lineParts[i] != null) {
                        customer.getCustomerDiets().put(i - 5, lineParts[i]);
                    }
                }
                // Lo añado a la lista
                customerList.add(customer);
            }
        } catch (Exception e) {
            throw new CustomerLoadFromFileException();
        }
    }

    private void customerManage() {
        Integer option;
        do {
            System.out.println("########################################################");
            System.out.println("################# Gestión de Pacientes #################");
            System.out.println("########################################################");
            System.out.println("Escriba una opción:");
            System.out.println("===================================");
            System.out.println("1-Nuevo Paciente");
            System.out.println("2-Listar Pacientes");
            System.out.println("3-Eliminar Paciente");
            System.out.println("4-Volver");
            option = Kb.getOption(1, 4);
            switch (option) {
                case 1:
                    try {
                        customerCreate();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    return;
                case 2:
                    customerShow();
                    break;
                case 3:
                    customerRemove();
                case 4:
                    break;
            }
        } while (option != 4);

    }

    private void customerCreate() throws CustomerAddToFileException {
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("Escriba los datos personales del paciente");
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("Nombre:");
        String name = Kb.nextLine();
        System.out.println("Apellidos:");
        String surname = Kb.nextLine();
        System.out.println("Peso:");
        Integer weight = Kb.forceNextInt();
        System.out.println("Altura:");
        Integer height = Kb.forceNextInt();
        System.out.println("Edad:");
        Integer age = Kb.forceNextInt();
        System.out.println("Mujer u Hombre(m/h):");
        String sexCharacter = Kb.nextLine();
        Customer newCustomer = new Customer(name, surname, weight, height, age, sexCharacter);
        try {
            customerAddToFile(newCustomer);
            customerList.add(newCustomer);
        } catch (CustomerAddToFileException e) {
            System.out.println(e.getMessage());
            return;
        }
        System.out.println("Cliente creado");
    }

    private void customerAddToFile(Customer customer) throws CustomerAddToFileException {
        String line;
        Integer i;
        String customerData;
        File customerFile = new File(CUSTOMER_FILE_PATH);
        try (PrintWriter pw = new PrintWriter(new FileWriter(customerFile, true))) {
            // Preparo los datos del cliente
            customerData = customer.getName() + "," + customer.getSurname() + "," + customer.getWeight() + "," + customer.getHeight()
                    + "," + customer.getAge() + "," + customer.getGender();
            for (i = Days.L.getPosition(); i <= Days.D.getPosition(); i++) {
                customerData += "," + customer.getCustomerDiets().get(i - 1);
            }
            pw.println(customerData);
        } catch (Exception e) {
            throw new CustomerAddToFileException();
        }
    }

    private void customerShow() {
        Customer customer = getSelectedCustomer();
        if (customer == null) {
            System.out.println("Elemento no encontrado");
        } else {
            Integer option;
            Integer i;
            System.out.println(customer.getCustomerDetails());
            System.out.println(" -- DIETAS --");
            for (i = 1; i < 8; i++) {
                System.out.print(Days.getByPosition(i).getName() + "-");
                if (customer.getCustomerDiets().get(i - 1) == null) {
                    System.out.println("");
                } else {
                    System.out.println(customer.getCustomerDiets().get(i - 1));
                }
            }
            System.out.println("1-Cambiar Nombre");
            System.out.println("2-Cambiar Apellidos");
            System.out.println("3-Cambiar Peso");
            System.out.println("4-Cambiar Altura");
            System.out.println("5-Cambiar Edad");
            System.out.println("6-Cambiar Sexo");
            System.out.println("7-Agregar Dieta");
            System.out.println("8-Eliminar Dieta");
            System.out.println("9-Volver");
            option = Kb.getOption(1, 9);
            if (option >= 1 && option <= 6) {
                customerUpdate(customer, option);
            } else if (option == 7) {
                customerAddDiet(customer);
            } else if (option == 8) {
                customerRemoveDiet(customer);
            }
        }
    }

    private void customerRemove() {
        Integer custIndex;
        Customer customer = getSelectedCustomer();
        if (customer == null) {
            System.out.println("Elemento no encontrado");
        } else {
            // Borro la dieta
            customerList.remove(customer);
            try {
                customerRemoveFromFile();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void customerRemoveFromFile() throws CustomerRemoveFromFileException {
        // Para no liarnos con las lineas simplemente reescribimos el fichero una vez eliminado el cliente
        // en customerremove
        String line;
        Integer i;
        String customerData;
        File customerFile = new File(CUSTOMER_FILE_PATH);
        // el append = false hace que borre el contenido del fichero
        try (PrintWriter pw = new PrintWriter(new FileWriter(customerFile, false))) {
            for (Customer customer : customerList) {
                customer.Save(CUSTOMER_FILE_PATH);
            }
        } catch (Exception e) {
            throw new CustomerRemoveFromFileException();
        }
    }

    private void customerUpdate(Customer customer, Integer option) {
        Integer userdataInt;
        String userdataStr;
        System.out.println("Introduce nuevo valor:");
        switch (option) {
            case 1:
                userdataStr = Kb.nextLine();
                customer.setName(userdataStr);
                break;
            case 2:
                userdataStr = Kb.nextLine();
                customer.setSurname(userdataStr);
                break;
            case 3:
                userdataInt = Kb.nextInt();
                customer.setWeight(userdataInt);
                break;
            case 4:
                userdataInt = Kb.nextInt();
                customer.setHeight(userdataInt);
                break;
            case 5:
                userdataInt = Kb.nextInt();
                customer.setAge(userdataInt);
                break;
            case 6:
                userdataStr = Kb.nextLine();
                customer.setGender(Gender.getByString(userdataStr));
                break;

        }
    }

    private void customerRemoveDiet(Customer customer) {
        Days day = getSelectedDay();
        if (day == null) {
            System.out.println("Dato no válido");
            return;
        }
        customer.getCustomerDiets().remove(day.getPosition());
        System.out.println("Dieta eliminada");
    }

    private void customerAddDiet(Customer customer) {
        String dietname = getSelectedDiet();
        if (dietname == null) {
            System.out.println("Elemento no encontrado");
        } else {
            // Tengo la dieta, necesito el dia de la semana
            Days day = getSelectedDay();
            if (day == null) {
                System.out.println("Dato no válido");
                return;
            }
            customer.getCustomerDiets().put(day.getPosition(), dietname);
            System.out.println("Dieta añadida al paciente");
        }
    }

    // ==========================================


    private boolean dietAssigned(String dietNameSearch) {
        Boolean assigned;
        for (Customer customer : customerList) {
            for (String dietName : customer.getCustomerDiets().values()) {
                if (dietName.equalsIgnoreCase(dietNameSearch)) {
                    return true;
                }
            }
        }
        return false;
    }

    private void updateDiet(Diet diet, Integer option) {
        Integer userdata;
        System.out.println("Introduce nuevo valor:");
        userdata = Kb.nextInt();
        switch (option) {
            case 1:
                diet.setMaxCalories(userdata);
                break;
            case 2:
                diet.setMaxCarbs(userdata);
                break;
            case 3:
                diet.setMaxFats(userdata);
                break;
            case 4:
                diet.setMaxProteins(userdata);
                break;
            case 5:
                addFoodMenu(diet);
                break;
        }
    }

    private String getSelectedDiet() {
        if (dietList.size() == 0) {
            System.out.println("No existen dietas");
        }
        // tengo dietas asi que necesito sus keys
        List<String> keys = new ArrayList<>();
        System.out.println("Escoja una dieta:");
        Integer option = 1;
        for (String key : dietList.keySet()) {
            keys.add(key);
            System.out.println(option + "-" + key);
            option++;
        }
        System.out.println(option + "-Volver");
        Integer selected = Kb.getOption(1, option);
        if (selected == option) {
            return null;
        } else {
            return keys.get(selected - 1);
        }
    }

    private Customer getSelectedCustomer() {
        String userdata;
        if (customerList.size() == 0) {
            System.out.println("No existen pacientes");
        }
        System.out.println("Introduzca los datos a buscar o ENTER para ver todos:");
        userdata = Kb.nextLine();

        System.out.println("Escoja un paciente:");
        Integer option = 1;
        for (Customer customer : customerList) {
            if (userdata.isEmpty()) {
                // los muestro todos
                System.out.println(option + "-" + customer.getName() + " " + customer.getSurname());
                option++;
            } else {
                // escojo los que coincidan
                String fullName = customer.getName() + " " + customer.getSurname();
                if (fullName.contains(userdata)) {
                    System.out.println(option + "-" + fullName);
                    option++;
                }
            }
        }
        System.out.println(option + "-Volver");
        Integer selected = Kb.getOption(1, option);
        if (selected == option) {
            return null;
        } else {
            return customerList.get(selected - 1);
        }
    }


    private Days getSelectedDay() {
        System.out.println("Escoja un día de la semana:");
        Integer option = 1;
        for (Days day : Days.values()) {
            System.out.println(option + "-" + day.getName());
            option++;
        }
        System.out.println(option + "-Volver");
        Integer selected = Kb.getOption(1, option);
        if (selected == option) {
            return null;
        } else {
            return Days.getByPosition(selected - 1);
        }
    }


    private void addFoodMenu(Diet diet) {
        if (diet == null) {
            System.out.println("Para agregar alimentos hace falta iniciar una dieta");
            return;
        }
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("Agregar Alimentos a la dieta");
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("Escriba una opción:");
        System.out.println("===================================");
        System.out.println("1-Agregar un nuevo alimento");
        System.out.println("2-Agregar un alimento ya existente");

        Integer option = Kb.getOption(1, 2);
        switch (option) {
            case 1:
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                System.out.println("Datos de nuevo alimento");
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                System.out.println("Nombre del alimento:");
                String name = Kb.nextLine();
                System.out.println("Carbohidratos:");
                Integer carbs = Kb.forceNextInt();
                System.out.println("Grasas:");
                Integer fats = Kb.forceNextInt();
                System.out.println("Proteínas:");
                Integer proteins = Kb.forceNextInt();
                System.out.println("Gramos:");
                Integer grams = Kb.forceNextInt();
                Food newFood = new Food(name, carbs, fats, proteins);
                validateAndAddFoodToDiet(diet, newFood, grams);
                foodList.add(newFood);
                newFood.save(FOOD_FILE_PATH);
                break;
            case 2:
                if (foodList.size() == 0) {
                    System.out.println("Para agregar un alimento existente, tienen que existir alimentos previos");
                    return;
                }
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                System.out.println("Escoja un alimento");
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                Integer i = 1;
                for (Food food : foodList) {
                    System.out.println(i + "- " + food.getName());
                    i++;
                }
                System.out.println(i + "- Cancelar");
                Integer element = Kb.getOption(1, i);
                if (element == i) {
                    System.out.println("Cancelando alimento");
                    return;
                }
                Food storedFood = foodList.get(element - 1);
                System.out.println("indique el número de gramos de " + storedFood.getName());
                Integer foodGrams = Kb.forceNextInt();
                validateAndAddFoodToDiet(diet, storedFood, foodGrams);
                break;
        }
    }

    private void validateAndAddFoodToDiet(Diet diet, Food food, Integer grams) {
        try {
            diet.addFood(food, grams);
        } catch (MaxCaloriesReachedException ecal) {
            System.out.println("Se ha alcanzado el máximo valor de calorías permitido");
        } catch (MaxCarbsReachedException ecar) {
            System.out.println("Se ha alcanzado el máximo valor de carbohidratos permitido");
        } catch (MaxFatsReachedException efat) {
            System.out.println("Se ha alcanzado el máximo valor de grasas permitido");
        } catch (MaxProteinsReachedException epro) {
            System.out.println("Se ha alcanzado el máximo valor de proteínas permitido");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void showDetailsMenu(Diet diet) {
        if (diet != null) {
            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
            System.out.println("Detalles de la dieta");
            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
            if (diet.getMaxCalories() != null) {
                System.out.println("El número máximo de calorías es:" + diet.getMaxCalories());
            }
            if (diet.getMaxCarbs() != null || diet.getMaxFats() != null || diet.getMaxProteins() != null) {
                System.out.println("Los valores máximos de macronutrientes son: Carbohidratos:" + diet.getMaxCarbs() + " , Grasas:" + diet.getMaxFats() + " , Proteinas:" + diet.getMaxProteins());
            }
            System.out.println("Número de alimentos de la dieta:" + diet.getFoodNumber());
            System.out.println("Calorías:" + diet.getTotalCalories());
            System.out.println("Carbohidratos:" + diet.getTotalCarbs());
            System.out.println("Grasas:" + diet.getTotalFats());
            System.out.println("Proteínas:" + diet.getTotalProteins());
            System.out.println("Alimentos de la dieta:" + diet.getDietIntakes());
        } else {
            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
            System.out.println("La dieta no esta iniciada");
            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        }
    }

    private void foodsLoadFromFile() throws FoodLoadFromFileException {
        String line;
        Integer i;
        String foodData;
        File foodFile = new File(FOOD_FILE_PATH);
        try (BufferedReader br = new BufferedReader(new FileReader(foodFile))) {
            while ((line = br.readLine()) != null) {
                String[] lineParts = line.split(",");
                // Cargo los datos da comida
                Food food = new Food(lineParts[0], Integer.parseInt(lineParts[1]), Integer.parseInt(lineParts[2]), Integer.parseInt(lineParts[3]));
                // Lo añado a la lista
                foodList.add(food);
            }
        } catch (Exception e) {
            throw new FoodLoadFromFileException();
        }
    }

    private void foodRemoveFromFile() throws FoodRemoveFromFileException {
        String line;
        Integer i;
        String foodData;
        File foodFile = new File(FOOD_FILE_PATH);
        // el append = false hace que borre el contenido del fichero
        try (PrintWriter pw = new PrintWriter(new FileWriter(foodFile, false))) {
            for (Food food : foodList) {
                food.save(FOOD_FILE_PATH);
            }
        } catch (Exception e) {
            throw new FoodRemoveFromFileException();
        }
    }

    // MIS COSAS

    private void insertDummyFood() {

        Food food = new Food("a1", 10, 20, 30);
        foodList.add(food);
        food.addFood(food);
        food.save(FOOD_FILE_PATH);

        food = new Food("a1", 20, 30, 40);
        foodList.add(food);
        food.addFood(food);
        food.save(FOOD_FILE_PATH);

        food = new Food("a2", 30, 40, 50);
        foodList.add(food);
        food.addFood(food);
        food.save(FOOD_FILE_PATH);

        System.out.println(">> Alimentos de prueba añadidos a la dieta");
    }

    private void insertDummyDiet() {

        Diet diet = new Diet();
        dietList.put("dieta no limite", diet);
        Food food = new Food("plátano", 20,10,0);
        try{
            diet.addFood(food, 50);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        dietSave(DIET_FILE_PATH, "dieta no limite");

        diet = new Diet(2000);
        dietList.put("dieta calorias", diet);
        food = new Food("patata", 25,12,2);
        try{
            diet.addFood(food, 40);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        dietSave(DIET_FILE_PATH, "dieta calorias");

        diet = new Diet(100, 200, 300);
        dietList.put("dieta macros", diet);
        food = new Food("yogur", 10,5,40);
        try{
            diet.addFood(food, 125);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        dietSave(DIET_FILE_PATH, "dieta macros");

        System.out.println(">> Dietas de prueba creadas");
    }

    private void insertDummyCustomer() {

        Customer customer = new Customer("pac1", "ape1", 70, 160, 30, "MALE");
        customer.getCustomerDiets().put(5, "dieta no limite");
        customerList.add(customer);
        customer.Save(CUSTOMER_FILE_PATH);

        customer = new Customer("pac2", "ape2", 70, 160, 30, "hombre");
        customerList.add(customer);
        customer.Save(CUSTOMER_FILE_PATH);

        customer = new Customer("pac3", "ape3", 50, 170, 20, "m");
        customerList.add(customer);
        customer.Save(CUSTOMER_FILE_PATH);

        System.out.println(">> Pacientes de prueba creados");
    }
}
