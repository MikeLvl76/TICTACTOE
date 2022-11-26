public class Cell {

    private int[] coords;
    private char value;
    private Boolean empty;
    public final static char[] SYMBOLS = {' ', 'X', 'O'};

    public Cell() {
        this.coords = new int[2];
        for(int i = 0; i < this.coords.length; i++){
            this.coords[i] = 0;
        }
        this.value = SYMBOLS[0];
        this.empty = true;
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
        if (_v != SYMBOLS[1] && _v != SYMBOLS[2]){
            throw new RuntimeException("Incorrect value");
        }
        this.value = _v;
    }

    public void changeState() {
        if (this.empty) this.empty = false;
        else this.empty = true;
    }

    public String toString(){
        return "" + this.value;
    }
}