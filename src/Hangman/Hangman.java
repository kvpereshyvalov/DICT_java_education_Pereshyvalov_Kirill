
package Hangman;

import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class Hangman {

    private static final int MAX_ATTEMPTS = 8;

    private static final String[] AVAILABLE_WORDS = {
            "python",
            "java",
            "javascript",
            "kotlin"
    };

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Random randomGenerator = new Random();

        System.out.println("HANGMAN");

        while (true) {

            System.out.print("Type \"play\" to play the game, \"exit\" to quit: ");

            String menuChoice = scanner.nextLine();

            if (menuChoice.equals("play")) {

                playGame(scanner, randomGenerator);

            } else if (menuChoice.equals("exit")) {

                break;
            }
        }

        scanner.close();
    }

    private static void playGame(Scanner scanner, Random randomGenerator) {

        int selectedWordIndex = randomGenerator.nextInt(AVAILABLE_WORDS.length);
        String secretWord = AVAILABLE_WORDS[selectedWordIndex];

        StringBuilder revealedWord = new StringBuilder("-".repeat(secretWord.length()));

        Set<Character> guessedLetters = new HashSet<>();

        int remainingAttempts = MAX_ATTEMPTS;

        while (remainingAttempts > 0 && !revealedWord.toString().equals(secretWord)) {

            System.out.println();
            System.out.println(revealedWord);
            System.out.print("Input a letter: > ");

            String playerInput = scanner.nextLine();

            if (playerInput.length() != 1) {

                System.out.println("You should input a single letter");
                continue;
            }

            char guessedLetter = playerInput.charAt(0);

            if (guessedLetter < 'a' || guessedLetter > 'z') {

                System.out.println("Please enter a lowercase English letter");
                continue;
            }

            if (guessedLetters.contains(guessedLetter)) {

                System.out.println("You've already guessed this letter");
                continue;
            }

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

        if (revealedWord.toString().equals(secretWord)) {

            System.out.println("You guessed the word " + secretWord + "!");
            System.out.println("You survived!");

        } else {

            System.out.println("You lost!");
        }
    }
}
