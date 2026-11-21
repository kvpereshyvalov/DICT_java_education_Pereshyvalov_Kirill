
package CoffeeMachine;

import java.util.Scanner;

public class CoffeeMachine {

    private int waterAmount = 400;
    private int milkAmount = 540;
    private int coffeeBeansAmount = 120;
    private int disposableCupsCount = 9;
    private int moneyAmount = 550;

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            CoffeeMachine coffeeMachine = new CoffeeMachine();
            coffeeMachine.run(scanner);
        }
    }

    // Main menu: processes commands until the machine is turned off
    private void run(Scanner scanner) {
        boolean isRunning = true;

        while (isRunning) {
            System.out.println(
                    "\nWrite action (buy, fill, take, remaining, exit):"
            );

            String action = scanner.next().toLowerCase();

            switch (action) {
                case "buy" -> buyCoffee(scanner);
                case "fill" -> fillResources(scanner);
                case "take" -> takeMoney();
                case "remaining" -> displayResources();
                case "exit" -> isRunning = false;
                default -> System.out.println("Unknown action.");
            }
        }
    }

    // Displays current resources
    private void displayResources() {
        System.out.println("\nThe coffee machine has:");
        System.out.println(waterAmount + " of water");
        System.out.println(milkAmount + " of milk");
        System.out.println(coffeeBeansAmount + " of coffee beans");
        System.out.println(disposableCupsCount + " of disposable cups");
        System.out.println(moneyAmount + " of money");
    }

    // Buys one selected type of coffee
    private void buyCoffee(Scanner scanner) {
        System.out.println(
                "What do you want to buy? 1 - espresso, 2 - latte, "
                        + "3 - cappuccino, back - to main menu:"
        );

        String coffeeChoiceInput = scanner.next();

        if (coffeeChoiceInput.equalsIgnoreCase("back")) {
            return;
        }

        int coffeeChoice;

        try {
            coffeeChoice = Integer.parseInt(coffeeChoiceInput);
        } catch (NumberFormatException exception) {
            System.out.println("Invalid coffee choice.");
            return;
        }

        int requiredWaterMilliliters;
        int requiredMilkMilliliters;
        int requiredCoffeeBeansGrams;
        int coffeePrice;

        switch (coffeeChoice) {
            case 1 -> {
                requiredWaterMilliliters = 250;
                requiredMilkMilliliters = 0;
                requiredCoffeeBeansGrams = 16;
                coffeePrice = 4;
            }
            case 2 -> {
                requiredWaterMilliliters = 350;
                requiredMilkMilliliters = 75;
                requiredCoffeeBeansGrams = 20;
                coffeePrice = 7;
            }
            case 3 -> {
                requiredWaterMilliliters = 200;
                requiredMilkMilliliters = 100;
                requiredCoffeeBeansGrams = 12;
                coffeePrice = 6;
            }
            default -> {
                System.out.println("Invalid coffee choice.");
                return;
            }
        }

        if (waterAmount < requiredWaterMilliliters) {
            System.out.println("Sorry, not enough water!");
            return;
        }

        if (milkAmount < requiredMilkMilliliters) {
            System.out.println("Sorry, not enough milk!");
            return;
        }

        if (coffeeBeansAmount < requiredCoffeeBeansGrams) {
            System.out.println("Sorry, not enough coffee beans!");
            return;
        }

        if (disposableCupsCount < 1) {
            System.out.println("Sorry, not enough disposable cups!");
            return;
        }

        waterAmount -= requiredWaterMilliliters;
        milkAmount -= requiredMilkMilliliters;
        coffeeBeansAmount -= requiredCoffeeBeansGrams;
        disposableCupsCount--;
        moneyAmount += coffeePrice;

        System.out.println(
                "I have enough resources, making you a coffee!"
        );
    }

    // Adds resources to the coffee machine
    private void fillResources(Scanner scanner) {
        System.out.println("Write how many ml of water do you want to add:");
        int waterToAdd = scanner.nextInt();

        System.out.println("Write how many ml of milk do you want to add:");
        int milkToAdd = scanner.nextInt();

        System.out.println("Write how many grams of coffee beans do you want to add:");
        int coffeeBeansToAdd = scanner.nextInt();

        System.out.println("Write how many disposable cups of coffee do you want to add:");
        int disposableCupsToAdd = scanner.nextInt();

        if (waterToAdd < 0 || milkToAdd < 0
                || coffeeBeansToAdd < 0 || disposableCupsToAdd < 0) {
            System.out.println("Amounts cannot be negative.");
            return;
        }

        waterAmount += waterToAdd;
        milkAmount += milkToAdd;
        coffeeBeansAmount += coffeeBeansToAdd;
        disposableCupsCount += disposableCupsToAdd;
    }

    // Withdraws all money from the coffee machine
    private void takeMoney() {
        int collectedMoney = moneyAmount;
        moneyAmount = 0;

        System.out.println("I gave you " + collectedMoney);
    }
}

