package TicTacToe;

public class TicTacToe {

    private final char[][] gameBoard;

    public TicTacToe() {
        gameBoard = new char[][]{
                {'X', 'O', 'X'},
                {'O', 'X', 'O'},
                {'X', 'X', 'O'}
        };
    }

    public void displayBoard() {
        for (char[] row : gameBoard) {
            for (char cell : row) {
                System.out.print(cell);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();

        game.displayBoard();
    }
}
