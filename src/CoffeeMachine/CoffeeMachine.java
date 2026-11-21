
package CoffeeMachine;

import java.util.Scanner;

public class CoffeeMachine {

    private static final int WATER_PER_CUP_MILLILITERS = 200;
    private static final int MILK_PER_CUP_MILLILITERS = 50;
    private static final int COFFEE_BEANS_PER_CUP_GRAMS = 15;

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            CoffeeMachine coffeeMachine = new CoffeeMachine();
            coffeeMachine.calculateRequiredIngredients(scanner);
        }
    }

    private void calculateRequiredIngredients(Scanner scanner) {
        System.out.println("Write how many cups of coffee you will need:");

        int numberOfCups = scanner.nextInt();

        int requiredWaterMilliliters =
                numberOfCups * WATER_PER_CUP_MILLILITERS;

        int requiredMilkMilliliters =
                numberOfCups * MILK_PER_CUP_MILLILITERS;

        int requiredCoffeeBeansGrams =
                numberOfCups * COFFEE_BEANS_PER_CUP_GRAMS;

        System.out.println("For " + numberOfCups + " cups of coffee you will need:");
        System.out.println(requiredWaterMilliliters + " ml of water");
        System.out.println(requiredMilkMilliliters + " ml of milk");
        System.out.println(requiredCoffeeBeansGrams + " g of coffee beans");
    }
}
