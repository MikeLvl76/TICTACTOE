public class Cell {

    private int[] coords;
    private char value;
    private Boolean empty;

    public Cell() {
        this.coords = new int[2];
        this.value = '\0';
        this.empty = false;
    }

    public int[] getCoords() {
        return this.coords;
    }

    public char getValue() {
        return this.value;
    }

    public Boolean isEmpty() {
        return this.empty;
    }

    public String toString(){
        return "[ " + this.value + " ]";
    }

    public static void main(String[] args) {
        Cell cell = new Cell();
        System.out.println(cell);
    }
}