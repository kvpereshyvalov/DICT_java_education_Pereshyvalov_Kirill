
package CoffeeMachine;

import java.util.Scanner;

public class CoffeeMachine {

    private static final int WATER_PER_CUP_MILLILITERS = 200;
    private static final int MILK_PER_CUP_MILLILITERS = 50;
    private static final int COFFEE_BEANS_PER_CUP_GRAMS = 15;

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            CoffeeMachine coffeeMachine = new CoffeeMachine();
            coffeeMachine.checkIngredientAvailability(scanner);
        }
    }

    private void checkIngredientAvailability(Scanner scanner) {
        System.out.println("Write how many ml of water the coffee machine has:");
        int availableWaterMilliliters = scanner.nextInt();

        System.out.println("Write how many ml of milk the coffee machine has:");
        int availableMilkMilliliters = scanner.nextInt();

        System.out.println("Write how many grams of coffee beans the coffee machine has:");
        int availableCoffeeBeansGrams = scanner.nextInt();

        System.out.println("Write how many cups of coffee you will need:");
        int requestedCups = scanner.nextInt();

        int maximumCupsByWater =
                availableWaterMilliliters / WATER_PER_CUP_MILLILITERS;

        int maximumCupsByMilk =
                availableMilkMilliliters / MILK_PER_CUP_MILLILITERS;

        int maximumCupsByCoffeeBeans =
                availableCoffeeBeansGrams / COFFEE_BEANS_PER_CUP_GRAMS;

        int maximumPossibleCups = Math.min(
                maximumCupsByWater,
                Math.min(maximumCupsByMilk, maximumCupsByCoffeeBeans)
        );

        if (maximumPossibleCups < requestedCups) {
            System.out.println(
                    "No, I can make only " + maximumPossibleCups + " cups of coffee"
            );
        } else if (maximumPossibleCups > requestedCups) {
            int additionalCups = maximumPossibleCups - requestedCups;

            System.out.println(
                    "Yes, I can make that amount of coffee (and even "
                            + additionalCups + " more than that)"
            );
        } else {
            System.out.println("Yes, I can make that amount of coffee");
        }
    }
}


