
package TicTacToe;

import java.util.Scanner;

public class TicTacToe {

    private static final int BOARD_SIZE = 3;

    private final char[][] gameBoard;

    public TicTacToe() {
        gameBoard = new char[BOARD_SIZE][BOARD_SIZE];

        for (int rowIndex = 0; rowIndex < BOARD_SIZE; rowIndex++) {
            for (int columnIndex = 0; columnIndex < BOARD_SIZE; columnIndex++) {
                gameBoard[rowIndex][columnIndex] = '_';
            }
        }
    }

    public boolean loadBoard(String cellState) {
        if (cellState.length() != BOARD_SIZE * BOARD_SIZE) {
            return false;
        }

        for (int index = 0; index < cellState.length(); index++) {
            char cellSymbol = cellState.charAt(index);

            if (cellSymbol != 'X' && cellSymbol != 'O' && cellSymbol != '_') {
                return false;
            }
        }

        for (int rowIndex = 0; rowIndex < BOARD_SIZE; rowIndex++) {
            for (int columnIndex = 0; columnIndex < BOARD_SIZE; columnIndex++) {
                int cellIndex = rowIndex * BOARD_SIZE + columnIndex;

                gameBoard[rowIndex][columnIndex] = cellState.charAt(cellIndex);
            }
        }

        return true;
    }

    public void displayBoard() {
        System.out.println("---------");

        for (int rowIndex = 0; rowIndex < BOARD_SIZE; rowIndex++) {
            System.out.print("|");

            for (int columnIndex = 0; columnIndex < BOARD_SIZE; columnIndex++) {
                System.out.print(gameBoard[rowIndex][columnIndex]);
            }

            System.out.println("|");
        }

        System.out.println("---------");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        TicTacToe game = new TicTacToe();

        boolean boardLoaded = false;

        while (!boardLoaded) {
            System.out.print("Enter cells: ");

            String cellState = scanner.nextLine();

            boardLoaded = game.loadBoard(cellState);

            if (!boardLoaded) {
                System.out.println(
                        "Invalid input. Enter exactly 9 characters using X, O, and _."
                );
            }
        }

        game.displayBoard();

        scanner.close();
    }
}
