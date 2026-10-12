
package ChatBot;

import java.util.Scanner;

public class ChatBot {

    public static void main(String[] args) {
        String botName = "CodeBot";
        int birthYear = 2026;

        Scanner scanner = new Scanner(System.in);

        System.out.println("Hello! My name is " + botName + ".");
        System.out.println("I was created in " + birthYear + ".");
        System.out.println("Please, remind me your name.");

        String userName = scanner.nextLine();

        System.out.println("What a great name you have, " + userName + "!");
        System.out.println("Let me guess your age.");
        System.out.println("Enter remainders of dividing your age by 3, 5 and 7.");

        int remainderByThree = scanner.nextInt();
        int remainderByFive = scanner.nextInt();
        int remainderBySeven = scanner.nextInt();

        int userAge = (remainderByThree * 70
                + remainderByFive * 21
                + remainderBySeven * 15) % 105;

        System.out.println("Your age is " + userAge
                + "; that's a good time to start programming!");

        System.out.println("Now I will prove to you that I can count to any number you want!");

        int countLimit = scanner.nextInt();

        for (int currentNumber = 0; currentNumber <= countLimit; currentNumber++) {
            System.out.println(currentNumber + "!");
        }

        String userAnswer;

        do {
            System.out.println("Which option correctly declares an integer variable in Java?");
            System.out.println("1. int number = 5;");
            System.out.println("2. number int = 5;");
            System.out.println("3. int = number 5;");
            System.out.println("4. integer number = 5;");

            userAnswer = scanner.next();

            if (!userAnswer.equals("1")) {
                System.out.println("That answer is incorrect. Please, try again.");
            }

        } while (!userAnswer.equals("1"));

        System.out.println("Correct!");
        System.out.println("Goodbye, have a nice day!");

        scanner.close();
    }
}
