import java.util.Arrays;
import java.util.Objects;

public class Grid {

    private Cell[] cells;

    public Grid() {
        this.cells = new Cell[9];
        for (int i = 0; i < this.cells.length; i++) {
            Cell cell = new Cell();
            cell.changeCoords((int) (i / 3), i % 3);
            this.cells[i] = cell;
        }
    }

    public Cell[] getCells() {
        return this.cells;
    }

    public Cell getCellByCoords(int i, int j) {
        if (i > 2 || j > 2) {
            throw new RuntimeException("Incorrect index");
        }
        int[] coords = { i, j };
        for (int k = 0; k < this.cells.length; k++) {
            if (Arrays.equals(this.cells[k].getCoords(), coords)) {
                return this.cells[k];
            }
        }
        return null;
    }

    public void updateCellValue(Cell cell, char value){
        if (cell == null) {
            throw new RuntimeException("Object is null");
        }
        if (value != Cell.SYMBOLS[1] && value != Cell.SYMBOLS[2]){
            throw new RuntimeException("Incorrect value");
        }
        for (int k = 0; k < this.cells.length; k++) {
            if (Objects.equals(this.cells[k], cell)) {
                if (!this.cells[k].isEmpty()) {
                    System.out.println("Incorrect cell chosen");
                    break;
                }
                this.cells[k].setValue(value);
                this.cells[k].changeState();
                break;
            }
        }
    }

    public String toString() {
        String output = "";
        for (int i = 0; i < this.cells.length; i++) {
            if (i % 3 == 0 && i > 0){
                output += "\n" + "--- ".repeat(3) + "\n";
            }
            output += (i % 3 == 0 ? " " : "") + this.cells[i] + (i != 2 && i != 5 && i != 8  ? " | " : "");
            
        }
        return output;
    }

    public static void main(String[] args) {
        Grid grid = new Grid();
        System.out.println(grid);
    }
}
