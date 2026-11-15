
package Hangman;

import java.util.Random;
import java.util.Scanner;

public class Hangman {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Random randomGenerator = new Random();

        String[] availableWords = {
                "python",
                "java",
                "javascript",
                "kotlin"
        };

        int selectedWordIndex = randomGenerator.nextInt(availableWords.length);
        String secretWord = availableWords[selectedWordIndex];

        System.out.println("HANGMAN");
        System.out.print("Guess the word: > ");

        String playerGuess = scanner.nextLine();

        if (playerGuess.equals(secretWord)) {
            System.out.println("You survived!");
        } else {
            System.out.println("You lost!");
        }

        scanner.close();
    }
}
