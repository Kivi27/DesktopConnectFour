package four;

import javax.swing.*;
import java.awt.*;

public class Cell extends JButton{

    public static final String defaultValue = " ";
    private int rowIndex;
    private int colIndex;

    public Cell(int rowIndex, int colIndex, String name) {
        super(defaultValue);
        setName("Button" + name);
        setBackground(Color.GRAY);
        this.rowIndex = rowIndex;
        this.colIndex = colIndex;
        setFocusPainted(false);
    }

    public boolean equalsText(Cell cell) {
        return cell.getText().equals(getText());
    }

    public void clean() {
        setText(defaultValue);
        setBackground(Color.GRAY);
    }
    public boolean isEmpty() {
        return getText().equals(" ");
    }

    public int getColIndex() {
        return colIndex;
    }

    public int getRowIndex() {return  rowIndex;}
}
