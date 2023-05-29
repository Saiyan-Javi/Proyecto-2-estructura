/*
 * Universidad del Valle de Guatemala
 * Algoritmos y Estructuras de Datos
 * Sección 10
 * 
 * Nelson Escalante - 22046
 * Juan Luis Solorzano - 201598
 * Javier Ovalle - 22103
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.LinkedList;

public class PizzaMatcher {
  
  public static void main(String[] args) {
    EmbeddedNeo4j database = new EmbeddedNeo4j("bolt://localhost:7687", "neo4j", "hola12345");
    Scanner input = new Scanner(System.in);

    /*










    System.out.println("\n**************************************************\n" +
            "* ¿Que ingredientes desearía que lleve la salsa? *\n" +
            "**************************************************");

    Map<String, ArrayList<String>> salsas = new HashMap<>();
    // Agregar salsas y sus ingredientes
    salsas.put("base orégano", new ArrayList<>(Arrays.asList("oregano", "aceite", "tomate")));
    salsas.put("picante", new ArrayList<>(Arrays.asList("jalapeño", "tomate")));
    salsas.put("hongos", new ArrayList<>(Arrays.asList("tomate", "pimiento", "champiñones")));
    salsas.put("blanca", new ArrayList<>(Arrays.asList("crema", "queso", "tomillo", "verduras")));
    salsas.put("pesto", new ArrayList<>(Arrays.asList("cilantro", "albahaca", "ajo", "verduras")));
    salsas.put("ajo", new ArrayList<>(Arrays.asList("aciete de oliva", "ajo")));
    // Pedir los ingredientes de la pizza
    String inputString1 = input.nextLine();
    String[] inputArray1 = inputString1.split(",");
    ArrayList<String> inputList1 = new ArrayList<>(Arrays.asList(inputArray1));
    // Comparar los ingredientes de la pizza ingresados con los ingredientes de las salsas disponibles
    int maxCount1 = -1;
    String maxSalsa = "";
    for (Map.Entry<String, ArrayList<String>> entry : salsas.entrySet()) {
      ArrayList<String> salsaIngredients = entry.getValue();
      int count1 = 0;
      for (String ingredient1 : inputList1) {
        if (salsaIngredients.contains(ingredient1.trim())) {
          count1++;
        }
      }
      if (count1 > maxCount1) {
        maxCount1 = count1;
        maxSalsa = entry.getKey();
      }
    }

    // Mostrar la pizza que mejor coincide
    if (maxCount == 0 ) {
      System.out.println("\n********************************************************************************\n" +
              "* Lo sentimos, no tenemos una pizza con los ingredientes que ha proporcionado. *\n" +
              "********************************************************************************");
    } else {
        if (maxCount1 == 0){
        System.out.println("********************************************************************************\n" +
                "* Lo sentimos, no tenemos una salsa con los ingredientes que ha proporcionado. *\n" +
                "********************************************************************************");
        System.out.println("*\n * La pizza que mejor coincide es: " + maxPizza + " de maza " + op);
      } else{
        System.out.println("*\n * La pizza que mejor coincide es: " + maxPizza + " de maza " + op + " con una base de salsa de " + maxSalsa);
      } 
    }

*/

    // Agregar pizzas y sus ingredientes
    pizzas.put("Pepperoni", new ArrayList<>(Arrays.asList("pepperoni", "queso", "tomate")));
    pizzas.put("Hawaiana", new ArrayList<>(Arrays.asList("piña", "jamon", "queso")));
    pizzas.put("Vegetariana", new ArrayList<>(Arrays.asList("cebolla", "pimiento verde", "champiñones", "verduras")));
    pizzas.put("5 carnes", new ArrayList<>(Arrays.asList("jamon", "salchicha", "pepperoni", "salami", "carne molida")));
    pizzas.put("Super Suprema", new ArrayList<>(Arrays.asList("cebolla", "aceitunas", "champiñones", "chile verde", "carne de cerdo")));

    int op = 0;
    do {
      System.out.println("***************************************************************\n" +
              "* ¡Bienvenido a nuestro sistema de recomendaciones de pizzas! *\n" +
              "***************************************************************\n" +
              "* 1. recomendacion basada en tipo de pizza                    *\n" +
              "* 2. recomendacion basada en lista de ingredientes            *\n" +
              "* 3. salir                                                    *\n" +
              "***************************************************************");
      op = Integer.parseInt(getNumber(input));
      switch(op) {
        case 1:
        System.out.println("\n*****************************\n" +
                "*      Menú de pizzas       *\n" +
                "*****************************\n");
        LinkedList<String> myList = database.getPIZZAS();
        for (String string : myList) {
          System.out.println("* -" + string);
        }

        System.out.print("\n* Ingrese la pizza que le gusta:");
        String p = input.nextLine();
        //crear metodo en EmbeddedNeo4j.java para recomendar pizza basado en los ingredientes de la pizza ingresada
          System.out.println("Pizzas que le podrían gustar incluyen:");

          LinkedList<String> pizzaList = database.getSimilarPizzas(p);

          for (String b : pizzaList) {
            System.out.println(b);
          }
        break;

        case 2:
        System.out.println("\n");

        System.out.println("\n**********************************\n" +
                "*      Lista de ingredientes     *\n" +
                "**********************************\n");
        LinkedList<String> ingr = database.getIngredients();
        for (String ingrediente : ingr) {
          System.out.println("* -" + ingrediente);
        }
        Map<String, ArrayList<String>> pizzas = new HashMap<>();
        // Pedir los ingredientes de la pizza
        System.out.print("\n* Ingrese los ingredientes que desea en su pizza (separados por comas): ");
        String inputString = input.nextLine();
        String[] inputArray = inputString.split(",");
        LinkedList<String> listaa = new LinkedList<>();
        for (String a : inputArray) {
          listaa.add(a);
        }
        ArrayList<String> inputList = new ArrayList<>(Arrays.asList(inputArray));

        System.out.println("Pizzas que le podrían gustar incluyen:");

        LinkedList<String> pizzaList = database.getSimilarPizzas(listaa);

        for (String b : pizzaList) {
          System.out.println(b);
        }
        break;
        case 4: //salir
          System.out.println("Gracias por su tiempo");
          break;

        default:
          System.out.print("Esta opción no es válida por favor reintentar \n");
          break;

        // Comparar los ingredientes de la pizza ingresados con los ingredientes de las pizzas disponibles
       /* int maxCount = -1;
        String maxPizza = "";
        for (Map.Entry<String, ArrayList<String>> entry : pizzas.entrySet()) {
          ArrayList<String> pizzaIngredients = entry.getValue();
          int count = 0;
          for (String ingredient : inputList) {
            if (pizzaIngredients.contains(ingredient.trim())) {
              count++;
            }
          }
          if (count > maxCount) {
            maxCount = count;
            maxPizza = entry.getKey();
          }
        }
        */
      }
    }while( op != 3);

    //System.out.print("\n Ingrese cuanto paga por su pizza usualmente: ");


    input.close();

  }
  public static String getNumber(Scanner keyboard) {
    String number = keyboard.nextLine(); // Obtiene el input
    boolean isNumber = false;

    while (!isNumber) { // Vuelve a pedir input hasta que este sea un número
      try {
        int nm = Integer.parseInt(number); // Verifica que el input sea un número
        isNumber = true;
      } catch (NumberFormatException nft) {
        System.out.println("ERROR. Verifique que el valor ingresado sea numérico e intente de nuevo.");
        number = keyboard.nextLine();
      }
    }

    return number;
  }
  
}
