import java.util.ArrayList;
import java.util.Random;
import java.util.SortedMap;

public class SudokuUtils {
    private static final Random random = new Random();
    private static final int NOT_FOUND = -1;
    private static final int MAX_SOLUTIONS = 10;


    public static Board create(Difficulty difficulty, int boardSize) {
        Board board = new Board(boardSize);
        addRandomValues(board.getBoard());

        ArrayList<Board> solvedBoards = new ArrayList<>();
        solve(board, 0, 0, 1, solvedBoards);
        board = solvedBoards.get(random.nextInt(solvedBoards.size()));
        pullValues(board, difficulty);

        return board;
    }

    private static void pullValues(Board board, Difficulty difficulty) {
        int pulledNumbers = 0;
        int maxPulledNumbers = difficulty.getEmptyCells();

        ArrayList<Integer> remainingIndexes = new ArrayList<>();
        for (int index = 0; index < Math.pow(board.getBoard().length, 2); index++) {
            remainingIndexes.add(index);
        }

        ArrayList<Board> solvedBoards = new ArrayList<>();
        solve(board, 0, 0, 1, solvedBoards);
        for (int index = 0; index < Math.pow(board.getBoard().length, 2) && pulledNumbers <= maxPulledNumbers; index++) {
            remainingIndexes.clear();
            int randomIndex = random.nextInt(solvedBoards.size());
            int randomRow = remainingIndexes.get(randomIndex) / board.getBoard().length;
            int randomColumn = remainingIndexes.get(randomIndex) % board.getBoard().length;
            int value = board.getBoard()[randomRow][randomColumn];

            board.getBoard()[randomRow][randomColumn] = Board.EMPTY_CELL;
            solve(board, 0, 0, 1, solvedBoards);

            if (solvedBoards.size() != 1) {
                board.getBoard()[randomRow][randomColumn] = value;
            } else {
                pulledNumbers++;
            }

            remainingIndexes.remove(randomIndex);
        }
    }

    private static void addRandomValues(int[][] board) {
        ArrayList<Integer> values = new ArrayList<>();
        ArrayList<Integer> rows = new ArrayList<>();
        ArrayList<Integer> columns = new ArrayList<>();

        for (int index = 0; index < board.length; index++) {
            values.add(index + 1);
            rows.add(index);
            columns.add(index);
        }

        for (int index = 0; index < board.length; index++) {
            int randomValue = values.get(random.nextInt(values.size()));
            int randomRow = rows.get(random.nextInt(rows.size()));
            int randomColumn = columns.get(random.nextInt(columns.size()));
            board[randomRow][randomColumn] = randomValue;

            values.remove((Integer) randomValue);
            rows.remove((Integer) randomRow);
            columns.remove((Integer) randomColumn);
        }
    }

    public static void solve(Board board, int row, int col, int lowestValue, ArrayList<Board> solvedBoards) {
        if (solvedBoards.size() == MAX_SOLUTIONS)
            return;

        // If the current row "passed" the size of the board, the board is solved
        if (row == board.getBoard().length) {
            solvedBoards.add(board);
            return;
        }

        if (board.getBoard()[row][col] == Board.EMPTY_CELL) {
            int value = findPossibleValue(board.getBoard(), row, col, lowestValue);

            if (value == NOT_FOUND) {
                return;
            }

            Board newBoard = board.clone();
            newBoard.getBoard()[row][col] = value;

            if (col == newBoard.getBoard().length - 1) {
                solve(newBoard, row + 1, 0, 1, solvedBoards);
            } else {
                solve(newBoard, row, col + 1, 1, solvedBoards);
            }

            // Checks if there can be another value in the cube
            if (getMaxValueInCell(board.getBoard(), row, col) != board.getBoard()[row][col]) {
                solve(board, row, col, newBoard.getBoard()[row][col] + 1, solvedBoards);
            }
        } else {
            if (col == board.getBoard().length - 1) {
                solve(board, row + 1, 0, 1, solvedBoards);
            } else {
                solve(board, row, col + 1, 1, solvedBoards);
            }
        }
    }

    private static int findPossibleValue(int[][] board, int row, int col, int lowestValue) {
        for (int value = lowestValue; value <= board.length; value++) {
            if (isValueValid(board, row, col, value)) {
                return value;
            }
        }

        return NOT_FOUND;
    }

    private static boolean isValueValid(int[][] board, int row, int col, int value) {
        final int SQUARE_SIZE = (int) Math.sqrt(board.length);
        boolean isValid = true;

        for (int index = 0; index < board.length && isValid; index++) {
            if (board[row][index] == value || board[index][col] == value) {
                isValid = false;
            }
        }

        int startingSquareRow = (row / SQUARE_SIZE) * SQUARE_SIZE;
        int startingSquareColumn = (col / SQUARE_SIZE) * SQUARE_SIZE;

        for (int squareRpw = startingSquareRow; squareRpw < squareRpw + SQUARE_SIZE && isValid; squareRpw++) {
            for (int squareColumn = startingSquareColumn; squareColumn < squareColumn + SQUARE_SIZE && isValid; squareColumn++) {
                if (board[squareRpw][squareColumn] == value) {
                    isValid = false;
                }
            }
        }

        return isValid;
    }

    private static int getMaxValueInCell(int[][] board, int row, int col) {
        int maxValue = NOT_FOUND;

        for (int value = 1; value <= board.length; value++) {
            if (isValueValid(board, row, col, value)) {
                maxValue = value;
            }
        }

        return maxValue;
    }
}
