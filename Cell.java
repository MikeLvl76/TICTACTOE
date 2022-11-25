public class Cell {

    private int[] coords;
    private char value;
    private Boolean empty;

    public Cell() {
        this.coords = new int[2];
        for(int i = 0; i < this.coords.length; i++){
            this.coords[i] = 0;
        }
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

    public void changeCoords(int i, int j){
        this.coords[0] = i;
        this.coords[1] = j;
    }

    public void setValue(char _v) {
        if (_v != 'X' && _v != 'O'){
            throw new RuntimeException("Incorrect value");
        }
        this.value = _v;
    }

    public void changeState() {
        if (this.empty) this.empty = false;
        else this.empty = true;
    }

    public String toString(){
        return "[ " + this.value + " ]";
    }

    public static void main(String[] args) {
        Cell cell = new Cell();
        cell.changeCoords(3, 3);
        int[] pos = cell.getCoords();
        System.out.println(String.valueOf(pos[0]) + "-" + String.valueOf(pos[1]));
    }
}