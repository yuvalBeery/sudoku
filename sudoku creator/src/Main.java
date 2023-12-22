import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Board board = new Board();
        Random random = new Random();
        LinkedList<Integer> valueList = new LinkedList<>();
        for (int i = 1; i <= 9; i++)
            valueList.add(i);

        LinkedList<Integer> rowList = new LinkedList<>();
        for (int i = 0; i < 9; i++)
            rowList.add(i);

        LinkedList<Integer> columnList = new LinkedList<>();
        for (int i = 0; i < 9; i++)
            columnList.add(i);

        for (int i = 0; i < Board.BOARD_SIZE; i++) {
            int randomValue = valueList.get(random.nextInt(valueList.size()));
            int randomRow = rowList.get(random.nextInt(rowList.size()));
            int randomColumn = columnList.get(random.nextInt(columnList.size()));
            board.board[randomRow][randomColumn] = randomValue;
            valueList.remove(valueList.indexOf(randomValue));
            rowList.remove(rowList.indexOf(randomRow));
            columnList.remove(columnList.indexOf(randomColumn));
        }

        ArrayList<Board> sollvedList = new ArrayList<>();
        Solve.solve(board, 0, 0, 1, sollvedList);
        Board randomBoard = sollvedList.get(random.nextInt(sollvedList.size()));

        LinkedList<Integer> boardIndexs = new LinkedList<>();
        for (int i = 0; i < 81; i++) {
            boardIndexs.add(i);
        }
        for (int i = 0; i < 81; i++) {
            sollvedList.clear();
            int randomIndex = random.nextInt(boardIndexs.size());
            int value = randomBoard.board[boardIndexs.get(randomIndex) / 9][boardIndexs.get(randomIndex) % 9];
            randomBoard.board[boardIndexs.get(randomIndex) / 9][boardIndexs.get(randomIndex) % 9] = 0;
            Solve.solve(randomBoard, 0, 0, 1, sollvedList);
            if (sollvedList.size() != 1)
                randomBoard.board[boardIndexs.get(randomIndex) / 9][boardIndexs.get(randomIndex) % 9] = value;
            boardIndexs.remove(randomIndex);
        }
        randomBoard.printBoard();
    }
}
