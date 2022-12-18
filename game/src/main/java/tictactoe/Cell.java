package game.src.main.java.tictactoe;

public class Cell {

    private int[] coords;
    private char value;
    private Boolean empty;

    /**
     * Cell default constructor, initializes coords, value and is empty
     */
    public Cell() {
        this.coords = new int[2];
        for(int i = 0; i < this.coords.length; i++){
            this.coords[i] = 0;
        }
        this.value = Symbol.WHITESPACE.getValue();
        this.empty = true;
    }

    /**
     * Gets Cell coordinates
     * @return int[] coordinates as integer array
     */
    public int[] getCoords() {
        return this.coords;
    }

    /**
     * Gets Cell value inside
     * @return char value is represented by a char
     */
    public char getValue() {
        return this.value;
    }

    /**
     * Returns Cell status : empty or not
     * @return boolean cell status represented by a boolean value
     */
    public Boolean isEmpty() {
        return this.empty;
    }

    /**
     * Updates Cell coords with integer params
     * @param i row index
     * @param j column index
     */
    public void changeCoords(int i, int j){
        this.coords[0] = i;
        this.coords[1] = j;
    }

    /**
     * Sets Cell value by replacing the old.
     * @param _v new value to insert in Cell, the char value must be equal to one of Enum values
     */
    public void setValue(char _v) {
        if (!Symbol.isInEnum(_v)){
            throw new RuntimeException("Incorrect value");
        }
        this.value = _v;
    }

    /**
     * Changes Cell state : if it was empty then it's full and vice-versa
     */
    public void changeState() {
        if (this.empty) this.empty = false;
        else this.empty = true;
    }

    @Override
    public String toString(){
        return "" + this.value;
    }
}