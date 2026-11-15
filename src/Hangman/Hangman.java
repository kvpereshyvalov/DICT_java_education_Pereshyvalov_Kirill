
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

            boolean letterWasRevealed = false;

            for (int letterIndex = 0; letterIndex < secretWord.length(); letterIndex++) {

                if (secretWord.charAt(letterIndex) == guessedLetter
                        && revealedWord.charAt(letterIndex) == '-') {

                    revealedWord.setCharAt(letterIndex, guessedLetter);
                    letterWasRevealed = true;
                }
            }

            if (!letterWasRevealed) {

                if (secretWord.indexOf(guessedLetter) >= 0) {
                    System.out.println("No improvements");
                } else {
                    System.out.println("That letter doesn't appear in the word");
                }

                remainingAttempts--;
            }

            System.out.println();
            System.out.println(revealedWord);

            if (revealedWord.toString().equals(secretWord)) {
                System.out.println("You guessed the word!");
                System.out.println("You survived!");
                break;
            }

            if (remainingAttempts == 0) {
                System.out.println("You lost!");
            }
        }

        scanner.close();
    }
}
