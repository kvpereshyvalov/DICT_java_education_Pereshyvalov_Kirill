
package Hangman;

import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

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

        Set<Character> guessedLetters = new HashSet<>();

        int remainingAttempts = 8;

        System.out.println("HANGMAN");

        while (remainingAttempts > 0) {

            System.out.println();
            System.out.println(revealedWord);
            System.out.print("Input a letter: > ");

            String playerInput = scanner.nextLine();

            if (playerInput.length() != 1) {
                System.out.println("You should input a single letter");
            } else {

                char guessedLetter = playerInput.charAt(0);

                if (guessedLetter < 'a' || guessedLetter > 'z') {
                    System.out.println("Please enter a lowercase English letter");

                } else if (guessedLetters.contains(guessedLetter)) {
                    System.out.println("You've already guessed this letter");

                } else {

                    guessedLetters.add(guessedLetter);

                    boolean newLetterWasRevealed = false;

                    for (int letterIndex = 0; letterIndex < secretWord.length(); letterIndex++) {

                        if (secretWord.charAt(letterIndex) == guessedLetter) {
                            revealedWord.setCharAt(letterIndex, guessedLetter);
                            newLetterWasRevealed = true;
                        }
                    }

                    if (!newLetterWasRevealed) {
                        System.out.println("That letter doesn't appear in the word");
                        remainingAttempts--;
                    }
                }
            }

            System.out.println();
            System.out.println(revealedWord);

            if (revealedWord.toString().equals(secretWord)) {
                System.out.println("You guessed the word " + secretWord + "!");
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
