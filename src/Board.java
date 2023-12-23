import java.util.Arrays;

public class Board {
    public static final int EMPTY_CELL = 0;
    private int[][] board;

    public Board(int size) {
        int[][] newBoard = new int[size][size];

        for (int[] row : newBoard) {
            Arrays.fill(row, EMPTY_CELL);
        }

        setBoard(newBoard);
    }

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

    @Override
    public Board clone() {
        Board cloned = new Board(getBoard().length);

        for (int row = 0; row < getBoard().length; row++) {
            for (int col = 0; col < getBoard().length; col++) {
                cloned.getBoard()[row][col] = getBoard()[row][col];
            }
        }

        return cloned;
    }

    public void print() {
        for (int[] row : getBoard())  {
            System.out.println(Arrays.toString(row));
        }
    }
}