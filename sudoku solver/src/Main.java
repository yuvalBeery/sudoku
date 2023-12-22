import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class Main {

    public static int maxValueInSquare(Board board, int r, int c) {
        int maxValue = -1;
        for (int i = 1; i <= Board.BOARD_SIZE; i++) {
            if (validateValue(board, r, c, i))
                maxValue = i;
        }
        return maxValue;
    }

    public static boolean validateValue(Board board, int r, int c, int value) {
        for (int i = 0; i < Board.BOARD_SIZE; i++) {
            if (value == board.board[r][i])
                return false;
        }
        for (int i = 0; i < Board.BOARD_SIZE; i++) {
            if (value == board.board[i][c])
                return false;
        }

        int squareRow;
        int squareColumn;
        if (r < 3)
            squareRow = 0;
        else if(r < 6)
            squareRow = 3;
        else
            squareRow = 6;

        if (c < 3)
            squareColumn = 0;
        else if (c < 6)
            squareColumn = 3;
        else
            squareColumn = 6;

        for (int row = squareRow; row < squareRow + 3; row++) {
            for (int column = squareColumn; column < squareColumn + 3; column++)
                if (value == board.board[row][column])
                    return false;
        }

        return true;
    }

    public static int findValue(Board board, int r, int c, int lowestValue) {
        for (int i = lowestValue; i < Board.BOARD_SIZE + 1; i++) {
            if (validateValue(board, r, c, i))
                return i;
        }
        return -1;
    }

    public static void solve(Board board, int row, int column, int lowestValue, ArrayList<Board> solvedBoardsList) {
        if (row == Board.BOARD_SIZE) {
            solvedBoardsList.add(board);
            return;
        }

        if (solvedBoardsList.size() >= 20)
            return;

        if (board.board[row][column] == 0) {
            int value = findValue(board, row, column, lowestValue);
            if (value == -1){
                return;
            }

            Board newBoard = new Board();
            newBoard.compareTo(board);
            newBoard.board[row][column] = value;

            if (column == Board.BOARD_SIZE - 1)
                solve(newBoard, row + 1, 0, 1, solvedBoardsList);
            else
                solve(newBoard, row, column + 1, 1, solvedBoardsList);

            if (maxValueInSquare(board, row, column) != newBoard.board[row][column])
                solve(board, row, column, newBoard.board[row][column] + 1, solvedBoardsList);
            }

        else {
            if (column == Board.BOARD_SIZE - 1)
                solve(board, row + 1, 0, 1, solvedBoardsList);
            else
                solve(board, row, column + 1, 1, solvedBoardsList);
        }
    }

    public static void main(String[] args) {
        Random random = new Random();
        Board board = new Board();
        board.board = new int[][] {
            {1, 0, 0, 5, 0, 4, 0, 0, 0},
            {0, 3, 5, 0, 0, 0, 1, 0, 0},
            {7, 8, 0, 0, 6, 0, 3, 0, 5},
            {0, 0, 0, 4, 0, 0, 0, 0, 7},
            {6, 9, 0, 0, 0, 0, 0, 0, 3},
            {0, 7, 0, 0, 0, 9, 0, 0, 0},
            {0, 0, 1, 0, 0, 0, 8, 0, 0},
            {0, 0, 0, 0, 4, 0, 0, 0, 2},
            {0, 0, 2, 8, 0, 0, 4, 0, 0}
        };

        ArrayList<Board> sollvedList = new ArrayList<>();
        solve(board, 0, 0, 1, sollvedList);
        for (int i = 0; i < sollvedList.size(); i++) {
            System.out.println("\n**************************************\n");
            sollvedList.get(i).printBoard();
            System.out.println("\n**************************************\n");
        }
    }
}
