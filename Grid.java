import java.util.Arrays;

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

    public String toString() {
        String output = "\n";
        for (int i = 0; i < this.cells.length; i++) {
            if (i % 3 == 0 && i > 0) {
                output += "\n".repeat(5);
            }
            output += String.valueOf(this.cells[i]).split(":")[1].trim() + "\t".repeat(2);
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
