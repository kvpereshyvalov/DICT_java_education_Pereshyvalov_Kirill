
package CoffeeMachine;

import java.util.Scanner;

public class CoffeeMachine {

    // Coffee machine resources
    private int waterAmount = 400;
    private int milkAmount = 540;
    private int coffeeBeansAmount = 120;
    private int disposableCupsCount = 9;
    private int moneyAmount = 550;

    // Current coffee machine state
    private MachineState currentState = MachineState.CHOOSING_ACTION;

    // Controls whether the coffee machine is running
    private boolean isRunning = true;

    // Possible coffee machine states
    private enum MachineState {
        CHOOSING_ACTION,
        CHOOSING_COFFEE,
        FILLING_WATER,
        FILLING_MILK,
        FILLING_COFFEE_BEANS,
        FILLING_DISPOSABLE_CUPS
    }

    public static void main(String[] args) {
        CoffeeMachine coffeeMachine = new CoffeeMachine();

        try (Scanner scanner = new Scanner(System.in)) {
            while (coffeeMachine.isRunning && scanner.hasNextLine()) {
                coffeeMachine.displayPrompt();

                String userInput = scanner.nextLine();

                coffeeMachine.processInput(userInput);
            }
        }
    }

    // Processes user input according to the current machine state
    public void processInput(String input) {
        if (input == null || input.isBlank()) {
            System.out.println("Please enter a valid command.");
            return;
        }

        switch (currentState) {
            case CHOOSING_ACTION ->
                    processAction(input.trim());

            case CHOOSING_COFFEE ->
                    processCoffeeChoice(input.trim());

            case FILLING_WATER ->
                    processWaterAmount(input.trim());

            case FILLING_MILK ->
                    processMilkAmount(input.trim());

            case FILLING_COFFEE_BEANS ->
                    processCoffeeBeansAmount(input.trim());

            case FILLING_DISPOSABLE_CUPS ->
                    processDisposableCupsAmount(input.trim());
        }
    }

    // Displays the prompt corresponding to the current state
    private void displayPrompt() {
        switch (currentState) {
            case CHOOSING_ACTION ->
                    System.out.println(
                            "\nWrite action (buy, fill, take, remaining, exit):"
                    );

            case CHOOSING_COFFEE ->
                    System.out.println(
                            "What do you want to buy? 1 - espresso, "
                                    + "2 - latte, 3 - cappuccino, "
                                    + "back - to main menu:"
                    );

            case FILLING_WATER ->
                    System.out.println(
                            "Write how many ml of water do you want to add:"
                    );

            case FILLING_MILK ->
                    System.out.println(
                            "Write how many ml of milk do you want to add:"
                    );

            case FILLING_COFFEE_BEANS ->
                    System.out.println(
                            "Write how many grams of coffee beans do you want to add:"
                    );

            case FILLING_DISPOSABLE_CUPS ->
                    System.out.println(
                            "Write how many disposable cups of coffee do you want to add:"
                    );
        }
    }

    // Processes commands from the main menu
    private void processAction(String action) {
        switch (action.toLowerCase()) {
            case "buy" ->
                    currentState = MachineState.CHOOSING_COFFEE;

            case "fill" ->
                    currentState = MachineState.FILLING_WATER;

            case "take" ->
                    takeMoney();

            case "remaining" ->
                    displayResources();

            case "exit" ->
                    isRunning = false;

            default ->
                    System.out.println("Unknown action.");
        }
    }

    // Processes the selected coffee type
    private void processCoffeeChoice(String input) {
        if (input.equalsIgnoreCase("back")) {
            currentState = MachineState.CHOOSING_ACTION;
            return;
        }

        int coffeeChoice;

        try {
            coffeeChoice = Integer.parseInt(input);
        } catch (NumberFormatException exception) {
            System.out.println("Invalid coffee choice. Please enter 1, 2, or 3.");
            return;
        }

        switch (coffeeChoice) {
            case 1 ->
                    prepareCoffee(250, 0, 16, 4);

            case 2 ->
                    prepareCoffee(350, 75, 20, 7);

            case 3 ->
                    prepareCoffee(200, 100, 12, 6);

            default ->
                    System.out.println("Invalid coffee choice. Please enter 1, 2, or 3.");
        }
    }

    // Checks resources and prepares the selected coffee
    private void prepareCoffee(
            int requiredWaterMilliliters,
            int requiredMilkMilliliters,
            int requiredCoffeeBeansGrams,
            int coffeePrice
    ) {
        if (waterAmount < requiredWaterMilliliters) {
            System.out.println("Sorry, not enough water!");
            currentState = MachineState.CHOOSING_ACTION;
            return;
        }

        if (milkAmount < requiredMilkMilliliters) {
            System.out.println("Sorry, not enough milk!");
            currentState = MachineState.CHOOSING_ACTION;
            return;
        }

        if (coffeeBeansAmount < requiredCoffeeBeansGrams) {
            System.out.println("Sorry, not enough coffee beans!");
            currentState = MachineState.CHOOSING_ACTION;
            return;
        }

        if (disposableCupsCount < 1) {
            System.out.println("Sorry, not enough disposable cups!");
            currentState = MachineState.CHOOSING_ACTION;
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

        currentState = MachineState.CHOOSING_ACTION;
    }

    // Reads and validates the amount of water to add
    private void processWaterAmount(String input) {
        int amount = parseNonNegativeAmount(input);

        if (amount < 0) {
            return;
        }

        waterAmount += amount;
        currentState = MachineState.FILLING_MILK;
    }

    // Reads and validates the amount of milk to add
    private void processMilkAmount(String input) {
        int amount = parseNonNegativeAmount(input);

        if (amount < 0) {
            return;
        }

        milkAmount += amount;
        currentState = MachineState.FILLING_COFFEE_BEANS;
    }

    // Reads and validates the amount of coffee beans to add
    private void processCoffeeBeansAmount(String input) {
        int amount = parseNonNegativeAmount(input);

        if (amount < 0) {
            return;
        }

        coffeeBeansAmount += amount;
        currentState = MachineState.FILLING_DISPOSABLE_CUPS;
    }

    // Reads and validates the number of disposable cups to add
    private void processDisposableCupsAmount(String input) {
        int amount = parseNonNegativeAmount(input);

        if (amount < 0) {
            return;
        }

        disposableCupsCount += amount;
        currentState = MachineState.CHOOSING_ACTION;
    }

    // Converts text input into a non-negative integer
    private int parseNonNegativeAmount(String input) {
        try {
            int amount = Integer.parseInt(input);

            if (amount < 0) {
                System.out.println("Amount cannot be negative.");
                return -1;
            }

            return amount;

        } catch (NumberFormatException exception) {
            System.out.println("Please enter a valid whole number.");
            return -1;
        }
    }

    // Displays all available coffee machine resources
    private void displayResources() {
        System.out.println("\nThe coffee machine has:");
        System.out.println(waterAmount + " of water");
        System.out.println(milkAmount + " of milk");
        System.out.println(coffeeBeansAmount + " of coffee beans");
        System.out.println(disposableCupsCount + " of disposable cups");
        System.out.println(moneyAmount + " of money");
    }

    // Withdraws all money from the coffee machine
    private void takeMoney() {
        int collectedMoney = moneyAmount;

        moneyAmount = 0;

        System.out.println("I gave you " + collectedMoney);
    }
}
