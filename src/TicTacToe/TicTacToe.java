
package TicTacToe;

import java.util.Scanner;

public class TicTacToe {

    private static final int BOARD_SIZE = 3;

    private static final char PLAYER_X = 'X';
    private static final char PLAYER_O = 'O';
    private static final char EMPTY_CELL = '_';

    private final char[][] gameBoard;

    public TicTacToe() {
        gameBoard = new char[BOARD_SIZE][BOARD_SIZE];

        for (int rowIndex = 0; rowIndex < BOARD_SIZE; rowIndex++) {
            for (int columnIndex = 0; columnIndex < BOARD_SIZE; columnIndex++) {
                gameBoard[rowIndex][columnIndex] = EMPTY_CELL;
            }
        }
    }

    public void displayBoard() {
        System.out.println("---------");

        for (int rowIndex = 0; rowIndex < BOARD_SIZE; rowIndex++) {
            System.out.print("|");

            for (int columnIndex = 0; columnIndex < BOARD_SIZE; columnIndex++) {
                char cellSymbol = gameBoard[rowIndex][columnIndex];

                if (cellSymbol == EMPTY_CELL) {
                    System.out.print(" ");
                } else {
                    System.out.print(cellSymbol);
                }
            }

            System.out.println("|");
        }

        System.out.println("---------");
    }

    private boolean hasWinningLine(char playerSymbol) {

        // Check horizontal lines.
        for (int rowIndex = 0; rowIndex < BOARD_SIZE; rowIndex++) {
            if (gameBoard[rowIndex][0] == playerSymbol
                    && gameBoard[rowIndex][1] == playerSymbol
                    && gameBoard[rowIndex][2] == playerSymbol) {
                return true;
            }
        }

        // Check vertical lines.
        for (int columnIndex = 0; columnIndex < BOARD_SIZE; columnIndex++) {
            if (gameBoard[0][columnIndex] == playerSymbol
                    && gameBoard[1][columnIndex] == playerSymbol
                    && gameBoard[2][columnIndex] == playerSymbol) {
                return true;
            }
        }

        // Check the main diagonal.
        if (gameBoard[0][0] == playerSymbol
                && gameBoard[1][1] == playerSymbol
                && gameBoard[2][2] == playerSymbol) {
            return true;
        }

        // Check the secondary diagonal.
        if (gameBoard[0][2] == playerSymbol
                && gameBoard[1][1] == playerSymbol
                && gameBoard[2][0] == playerSymbol) {
            return true;
        }

        return false;
    }

    private boolean isBoardFull() {
        for (int rowIndex = 0; rowIndex < BOARD_SIZE; rowIndex++) {
            for (int columnIndex = 0; columnIndex < BOARD_SIZE; columnIndex++) {
                if (gameBoard[rowIndex][columnIndex] == EMPTY_CELL) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean isCoordinateWithinBoard(
            int rowCoordinate,
            int columnCoordinate
    ) {
        return rowCoordinate >= 1
                && rowCoordinate <= BOARD_SIZE
                && columnCoordinate >= 1
                && columnCoordinate <= BOARD_SIZE;
    }

    private void requestPlayerMove(Scanner scanner, char playerSymbol) {
        while (true) {
            System.out.print("Enter the coordinates: ");

            String coordinateInput = scanner.nextLine().trim();

            String[] coordinateValues = coordinateInput.split("\\s+");

            if (coordinateValues.length != 2) {
                System.out.println("You should enter numbers!");
                continue;
            }

            int rowCoordinate;
            int columnCoordinate;

            try {
                rowCoordinate = Integer.parseInt(coordinateValues[0]);
                columnCoordinate = Integer.parseInt(coordinateValues[1]);

            } catch (NumberFormatException exception) {
                System.out.println("You should enter numbers!");
                continue;
            }

            if (!isCoordinateWithinBoard(rowCoordinate, columnCoordinate)) {
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            }

            int rowIndex = rowCoordinate - 1;
            int columnIndex = columnCoordinate - 1;

            if (gameBoard[rowIndex][columnIndex] != EMPTY_CELL) {
                System.out.println(
                        "This cell is occupied! Choose another one!"
                );
                continue;
            }

            gameBoard[rowIndex][columnIndex] = playerSymbol;

            break;
        }
    }

    public void playGame(Scanner scanner) {
        char currentPlayer = PLAYER_X;

        displayBoard();

        while (true) {
            requestPlayerMove(scanner, currentPlayer);

            displayBoard();

            if (hasWinningLine(currentPlayer)) {
                System.out.println(currentPlayer + " wins");
                break;
            }

            if (isBoardFull()) {
                System.out.println("Draw");
                break;
            }

            if (currentPlayer == PLAYER_X) {
                currentPlayer = PLAYER_O;
            } else {
                currentPlayer = PLAYER_X;
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        TicTacToe game = new TicTacToe();

        game.playGame(scanner);

        scanner.close();
    }
}
