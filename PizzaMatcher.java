import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class PizzaMatcher {
  
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.println("***********¡Bienvenido a nuestro sistema de recomendaciones de pizzas!***********");
    System.out.println("¿Que tipo de masa le gusta?");
    String op = input.nextLine();

    Map<String, ArrayList<String>> pizzas = new HashMap<>();
    // Agregar pizzas y sus ingredientes
    pizzas.put("Pepperoni", new ArrayList<>(Arrays.asList("pepperoni", "queso", "tomate")));
    pizzas.put("Hawaiana", new ArrayList<>(Arrays.asList("piña", "jamon", "queso")));
    pizzas.put("Vegetariana", new ArrayList<>(Arrays.asList("cebolla", "pimiento verde", "champiñones", "verduras")));
    pizzas.put("5 carnes", new ArrayList<>(Arrays.asList("jamon", "salchicha", "pepperoni", "salami", "carne molida")));
    pizzas.put("Super Suprema", new ArrayList<>(Arrays.asList("cebolla", "aceitunas", "champiñones", "chile verde", "carne de cerdo")));
    // Pedir los ingredientes de la pizza
    System.out.println("Ingrese los ingredientes que desea en su pizza (separados por comas):");
    String inputString = input.nextLine();
    String[] inputArray = inputString.split(",");
    ArrayList<String> inputList = new ArrayList<>(Arrays.asList(inputArray));
    // Comparar los ingredientes de la pizza ingresados con los ingredientes de las pizzas disponibles
    int maxCount = -1;
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
    System.out.println("¿Cuanto paga por su pizza?");
    String op2 = input.nextLine();

    System.out.println("¿Que ingredientes desearía que lleve la salsa?");

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
      System.out.println("Lo sentimos, no tenemos una pizza con los ingredientes que ha proporcionado.");
    } else {
        if (maxCount1 == 0){
        System.out.println("Lo sentimos, no tenemos una salsa con los ingredientes que ha proporcionado.");
        System.out.println("La pizza que mejor coincide es: " + maxPizza + " de maza " + op);
      } else{
        System.out.println("La pizza que mejor coincide es: " + maxPizza + " de maza " + op + " con una base de salsa de " + maxSalsa);
      } 
    }
  }
  
}
