import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class PizzaMatcher {
  
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    Map<String, ArrayList<String>> pizzas = new HashMap<>();
    // Agregar pizzas y sus ingredientes
    pizzas.put("Pepperoni", new ArrayList<>(Arrays.asList("pepperoni", "queso", "tomate")));
    pizzas.put("Hawaiana", new ArrayList<>(Arrays.asList("piña", "jamón", "queso")));
    pizzas.put("Vegetariana", new ArrayList<>(Arrays.asList("cebolla", "pimiento", "champiñones", "verduras")));
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
    // Mostrar la pizza que mejor coincide
    if (maxCount == 0) {
      System.out.println("Lo sentimos, no tenemos una pizza con los ingredientes que ha proporcionado.");
    } else {
      System.out.println("La pizza que mejor coincide es: " + maxPizza);
    }
  }
  
}
