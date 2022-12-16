
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Grid {

    private ArrayList<Cell> cells;

    /**
     * Grid default constructor, initializes Cell list and Cell coordinates
     */
    public Grid() {
        this.cells = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            Cell cell = new Cell();
            cell.changeCoords((int) (i / 3), i % 3);
            this.cells.add(cell);
        }
    }

    /**
     * Returns list of Cell
     * @return ArrayList<Cell> a list containing Cell object
     */
    public ArrayList<Cell> getCells() {
        return this.cells;
    }

    /**
     * Returns Cell at given coordinates
     * @param i row index
     * @param j column index
     * @return Cell object at the given indices
     */
    public Cell getCellByCoords(int i, int j) {
        int[] coords = { i, j };
        for (int k = 0; k < this.cells.size(); k++) {
            if (Arrays.equals(this.cells.get(k).getCoords(), coords)) {
                return this.cells.get(k);
            }
        }
        return null;
    }

    /**
     * Updates Cell object value
     * @param cell Cell object to be updated
     * @param value char value to insert
     */
    public void updateCellValue(Cell cell, char value){
        if (cell == null) {
            throw new RuntimeException("Object is null");
        }
        if (!Symbol.isInEnum(value)){
            throw new RuntimeException("Incorrect value");
        }
        for (int k = 0; k < this.cells.size(); k++) {
            if (Objects.equals(this.cells.get(k), cell)) {
                if (!this.cells.get(k).isEmpty()) {
                    System.out.println("Incorrect cell chosen");
                    break;
                }
                this.cells.get(k).setValue(value);
                this.cells.get(k).changeState();
                break;
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < this.cells.size(); i++) {
            if (i % 3 == 0 && i > 0){
                builder.append("\n").append("--- ".repeat(3)).append("\n");
            }
            builder.append((i % 3 == 0 ? " " : "")).append(this.cells.get(i)).append((i != 2 && i != 5 && i != 8  ? " | " : ""));
        }
        return builder.toString();
    }
}
