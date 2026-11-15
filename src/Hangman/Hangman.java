
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

        StringBuilder revealedWord = new StringBuilder("-".repeat(secretWord.length()));

        int remainingAttempts = 8;

        System.out.println("HANGMAN");

        while (remainingAttempts > 0) {

            System.out.println();
            System.out.println(revealedWord);
            System.out.print("Input a letter: > ");

            String playerInput = scanner.nextLine();
            char guessedLetter = playerInput.charAt(0);

            for (int letterIndex = 0; letterIndex < secretWord.length(); letterIndex++) {

                if (secretWord.charAt(letterIndex) == guessedLetter) {
                    revealedWord.setCharAt(letterIndex, guessedLetter);
                }
            }

            remainingAttempts--;
        }

        System.out.println();
        System.out.println(revealedWord);
        System.out.println();
        System.out.println("Thanks for playing!");
        System.out.println("We'll see how well you did in the next stage");

        scanner.close();
    }
}
