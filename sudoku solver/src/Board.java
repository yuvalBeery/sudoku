import java.util.Arrays;

public class Board {
    final static int BOARD_SIZE = 9;
    int[][] board = new int[BOARD_SIZE][BOARD_SIZE];

    public void compareTo(Board oldBoard) {
        for (int rowIndex = 0; rowIndex < board.length; rowIndex++) {
            board[rowIndex] = Arrays.copyOf(oldBoard.board[rowIndex], board[rowIndex].length);
        }
    }

    public void printBoard() {
        for (int[] row : board) {
            Arrays.toString(row);
        }
    }
}
