package four;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Checker {

    private static int countWinCell = 4;
    public static java.util.List<Cell> checkVertical(Cell[][] allCells, Cell newCell, boolean isTop) {
        int rowIdx = newCell.getRowIndex();
        int colIdx = newCell.getColIndex();
        java.util.List<Cell> cellList = new ArrayList<>();

        int direction = isTop ? -1 : 1;

        for (int i = rowIdx + direction; i >= 0 && i < ConnectFour.countRow; i += direction) {
            Cell currentCell = allCells[i][colIdx];

            if (currentCell.equalsText(newCell)) {
                cellList.add(currentCell);
            } else {
                break;
            }
        }
        return cellList;
    }

    public static java.util.List<Cell> checkVertical(Cell[][] allCells, Cell newCell) {
        java.util.List<Cell> topList = checkVertical(allCells, newCell, true);
        topList.add(newCell);
        java.util.List<Cell> bottomList = checkVertical(allCells, newCell, false);
        topList.addAll(bottomList);
        return topList;
    }

    public static java.util.List<Cell> checkHorizontal(Cell[][] allCells, Cell newCell, boolean isRight) {
        int rowIdx = newCell.getRowIndex();
        int colIdx = newCell.getColIndex();
        java.util.List<Cell> cellList = new ArrayList<>();

        int direction = isRight ? 1 : -1;

        for (int i = colIdx + direction; i >= 0 && i < ConnectFour.countColumn; i += direction) {
            Cell currentCell = allCells[rowIdx][i];
            if (currentCell.equalsText(newCell)) {
                cellList.add(currentCell);
            } else {
                break;
            }
        }
        return cellList;
    }

    public static java.util.List<Cell> checkHorizontal(Cell[][] allCells, Cell newCell) {
        java.util.List<Cell> rightList = checkHorizontal(allCells, newCell, true);
        rightList.add(newCell);
        java.util.List<Cell> leftList = checkHorizontal(allCells, newCell, false);
        rightList.addAll(leftList);
        return rightList;
    }


    public static java.util.List<Cell> checkDiagonal(Cell[][] allCells, Cell newCell, boolean isTop, boolean isRight) {
        int rowIdx = newCell.getRowIndex();
        int colIdx = newCell.getColIndex();
        java.util.List<Cell> cellList = new ArrayList<>();

        int horizontalDirection = isRight ? 1 : -1;
        int verticalDirection = isTop ? -1: 1;

        for (int i = rowIdx + verticalDirection; i >= 0 && i < ConnectFour.countRow; i += verticalDirection) {
            int j = colIdx + horizontalDirection;
            if (j >= 0 && j < ConnectFour.countColumn) {
                Cell currentCell = allCells[i][j];
                if (currentCell.equalsText(newCell)) {
                    colIdx += horizontalDirection;
                    cellList.add(currentCell);
                }
            }

        }
        return cellList;
    }

    public static java.util.List<Cell> checkDiagonal(Cell[][] allCells, Cell newCell, boolean isLeft) {
        java.util.List<Cell> onePartDiagonal;
        java.util.List<Cell> twoPartDiagonal;
        if (isLeft) {
            onePartDiagonal = checkDiagonal(allCells, newCell, true, false);
            twoPartDiagonal = checkDiagonal(allCells, newCell, false, true);
        } else {
            onePartDiagonal = checkDiagonal(allCells, newCell, false, false);
            twoPartDiagonal = checkDiagonal(allCells, newCell, true, true);
        }
        onePartDiagonal.add(newCell);
        onePartDiagonal.addAll(twoPartDiagonal);
        return onePartDiagonal;
    }



    public static java.util.List<Cell> checkWin(Cell[][] allCells, Cell newCell) {
        List<Cell> horizontal = checkHorizontal(allCells, newCell);
        if (horizontal.size() == countWinCell) {
            return horizontal;
        }
        List<Cell> vertical = checkVertical(allCells, newCell);
        if (vertical.size() == countWinCell) {
            return vertical;
        }
        List<Cell> leftDiagonal = checkDiagonal(allCells, newCell, true);
        if (leftDiagonal.size() == countWinCell) {
            return leftDiagonal;
        }
        List<Cell> rightDiagonal = checkDiagonal(allCells, newCell, false);
        if (rightDiagonal.size() == countWinCell) {
            return rightDiagonal;
        }

        return null;
    }

}
