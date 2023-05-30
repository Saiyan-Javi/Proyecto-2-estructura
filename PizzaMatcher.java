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

          System.out.println("Pizzas que le podrían gustar incluyen:");

          LinkedList<String> pizzaList2 = database.getSimilarPizzas(p);

          for (String b : pizzaList2) {
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
        case 3: //salir
          System.out.println("Gracias por su tiempo");
          break;

        default:
          System.out.print("Esta opción no es válida por favor reintentar \n");
          break;
      }
    }while( op != 3);

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
