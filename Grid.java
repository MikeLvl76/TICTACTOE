import java.util.Arrays;
import java.util.Objects;

public class Grid {

    private Cell[] cells;

    public Grid() {
        this.cells = new Cell[9];
        for (int i = 0; i < this.cells.length; i++) {
            Cell cell = new Cell();
            cell.changeCoords((int) (i / 3), i % 3);
            System.out.println(cell);
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
        if (value != 'X' && value != 'O'){
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
        String output = "\n";
        for (int i = 0; i < this.cells.length; i++) {
            if (i % 3 == 0 && i > 0) {
                output += "\n";
                for (int j = i - 3; j < i; j++){
                    output += String.valueOf(this.cells[j]).split(":")[0].trim() + "\t".repeat(2);
                }
                output += "\n".repeat(3);
            }
            output += String.valueOf(this.cells[i]).split(":")[1].trim() + "\t".repeat(2);
        }
        output += "\n";
        for (int j = this.cells.length - 3; j < this.cells.length; j++){
            output += String.valueOf(this.cells[j]).split(":")[0].trim() + "\t".repeat(2);
        }
        output += "\n";
        return output;
    }

    public static void main(String[] args) {
        Grid grid = new Grid();
        System.out.println(grid.getCellByCoords(1, 2));
        System.out.println(grid);
    }
}
