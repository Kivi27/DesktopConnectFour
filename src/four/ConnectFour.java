package four;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ConnectFour extends JFrame {

    public static final int countRow = 6;
    public static final int countColumn = 7;
    public static final int sizeOfWindow = 300;

    private String playerSymbol;
    private Cell[][] allCells;
    private boolean isEndGame;

    public ConnectFour() {
        isEndGame = false;
        allCells = new Cell[countRow][countColumn];
        playerSymbol = "X";
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(sizeOfWindow, sizeOfWindow);



        JPanel panelGameField = new JPanel();
        panelGameField.setLayout(new GridLayout(countRow, countColumn, 0, 0));
        initGameField(panelGameField);
        add(panelGameField);

        JPanel resetPanel = new JPanel();
        JButton resetButton = new JButton("reset");
        resetButton.setName("ButtonReset");
        resetButton.addActionListener( e -> {
            resetGame();
        });
        resetPanel.add(resetButton);
        add(resetPanel);

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setTitle("Connect Four");
        setVisible(true);
    }

    private String getCurrentPlayerSymbol() {
        String oldSymbol = playerSymbol;
        playerSymbol = "X".equals(oldSymbol) ? "O" : "X";
        return oldSymbol;
    }

    private void initGameField(JPanel gameField) {
        for (int i = 0; i < countRow; i++) {
            for (char j = 0; j < countColumn; j++) {
                String nameOfCell = (char) ('A' +  j) + ((Integer) (countRow - i)).toString();
                Cell cell = new Cell(i, j, nameOfCell);

                cell.addActionListener( e -> {
                    if (isEndGame)  {
                        return;
                    }
                    Cell bottomCell = getBottomEmptyCell(cell.getColIndex());
                    tryCheckWin(bottomCell);
                });

                allCells[i][j] = cell;
                gameField.add(cell);
            }
        }
    }


    private void tryCheckWin(Cell lastCell) {
        if (lastCell != null) {
            lastCell.setText(getCurrentPlayerSymbol());
            List<Cell> winningCell = Checker.checkWin(allCells, lastCell);
            if (winningCell != null) {
                endGame(winningCell);
            }
        }
    }

    private void changeColorWinCell(List<Cell> cells) {
        for (Cell cell : cells) {
            cell.setBackground(Color.blue);
        }
    }

    private void endGame(List<Cell> cells) {
        changeColorWinCell(cells);
        isEndGame = true;
    }

    private void resetGame() {
        for (int i = 0; i < countRow; i++) {
            for (int j = 0; j < countColumn; j++) {
                allCells[i][j].clean();
            }
        }
        isEndGame = false;
        playerSymbol = "X";
    }

    private Cell getBottomEmptyCell(int column) {
        for (int i = countRow - 1; i >= 0; i--) {
            if (allCells[i][column].isEmpty())
                return allCells[i][column];
        }
        return null;
    }
}