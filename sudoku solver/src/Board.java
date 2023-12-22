public class Board {
    final static int BOARD_SIZE = 9;
    int[][] board = new int[BOARD_SIZE][BOARD_SIZE];

    public void compareTo(Board oldBoard) {
        for (int r = 0; r < BOARD_SIZE; r++) {
            for (int c = 0; c < BOARD_SIZE; c++)
            this.board[r][c] = oldBoard.board[r][c];
        }
    }

    public void printBoard() {
        for (int r = 0; r < BOARD_SIZE; r++) {
            for (int c = 0; c < BOARD_SIZE; c++)
                System.out.print(this.board[r][c] + "  ");
            System.out.println("");
        }
    }
}
