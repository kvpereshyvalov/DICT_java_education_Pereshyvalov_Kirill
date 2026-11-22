
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

    public boolean loadBoard(String cellState) {
        if (cellState.length() != BOARD_SIZE * BOARD_SIZE) {
            return false;
        }

        for (int index = 0; index < cellState.length(); index++) {
            char cellSymbol = cellState.charAt(index);

            if (cellSymbol != PLAYER_X
                    && cellSymbol != PLAYER_O
                    && cellSymbol != EMPTY_CELL) {
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

    private int countSymbols(char playerSymbol) {
        int symbolCount = 0;

        for (int rowIndex = 0; rowIndex < BOARD_SIZE; rowIndex++) {
            for (int columnIndex = 0; columnIndex < BOARD_SIZE; columnIndex++) {
                if (gameBoard[rowIndex][columnIndex] == playerSymbol) {
                    symbolCount++;
                }
            }
        }

        return symbolCount;
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
        return countSymbols(EMPTY_CELL) == 0;
    }

    public String analyzeGameState() {
        int xCount = countSymbols(PLAYER_X);
        int oCount = countSymbols(PLAYER_O);

        boolean xWins = hasWinningLine(PLAYER_X);
        boolean oWins = hasWinningLine(PLAYER_O);

        if (Math.abs(xCount - oCount) > 1 || (xWins && oWins)) {
            return "Impossible";
        }

        if (xWins) {
            return "X wins";
        }

        if (oWins) {
            return "O wins";
        }

        if (isBoardFull()) {
            return "Draw";
        }

        return "Game not finished";
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

        System.out.println(game.analyzeGameState());

        scanner.close();
    }
}
