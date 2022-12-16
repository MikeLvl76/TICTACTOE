import java.util.ArrayList;
import java.util.Arrays;

public class Player {
    
    protected String name;
    protected char symbol;
    protected Boolean play;
    protected String currentMove;
    protected ArrayList<String> moves;

    /**
     * Player constructor that needs String and char values for name and symbol attributes
     * @param _n String value to insert in name attribute
     * @param _s char value to insert in symbol attribute
     */
    public Player(String _n, char _s){
        if (!Symbol.isInEnum(_s)){
            throw new RuntimeException("Incorrect symbol");
        }
        this.name = _n;
        this.symbol = _s;
        this.play = false;
        this.currentMove = "";
        this.moves = new ArrayList<>(Arrays.asList("00", "01", "02", "10", "11", "12", "20", "21", "22"));
    }

    /**
     * Returns Player's name
     * @return String the name
     */
    public String getName(){
        return this.name;
    }

    /**
     * Returns Player symbol
     * @return char the symbol
     */
    public char getSymbol(){
        return this.symbol;
    }

    /**
     * Tells if Player is currently playing
     * @return Boolean Player status represented by Boolean value
     */
    public Boolean isPlaying(){
        return this.play;
    }

    public String getCurrentMove(){
        return this.currentMove;
    }

    public ArrayList<String> getMoves(){
        return this.moves;
    }

    /**
     * Change Player's name
     * @param new_name String value to insert for updating name attribute
     */
    public void setName(String new_name){
        this.name = new_name;
    }

    /**
     * Change Player symbol
     * @param new_symbol char value to insert for updating symbol attribute
     */
    public void setSymbol(char new_symbol){
        if (!Symbol.isInEnum(new_symbol)){
            throw new RuntimeException("Incorrect symbol");
        }
        this.symbol = new_symbol;
    }

    public void deleteMove(String move) {
        this.moves.remove(move);
    }

    public void saveMove(String move){
        if (!this.moves.contains(move)) return;
        this.currentMove = move;
        this.deleteMove(move);
    }

    /**
     * Change Player status : if he's playing then it will not be playing anymore and vice-versa
     */
    public void changeState(){
        if (this.play) this.play = false;
        else this.play = true;
    }

    @Override
    public String toString(){
        return (this.play ? "[Playing] " : "[Idle] ") + this.name.toUpperCase() + " : " + this.symbol;
    }
}
