public class Player {
    
    private String name;
    private char symbol;
    private Boolean play;

    public Player(String _n, char _s){
        if (_s != 'X' && _s != 'O'){
            throw new RuntimeException("Incorrect symbol");
        }
        this.name = _n;
        this.symbol = _s;
        this.play = false;
    }

    public String getName(){
        return this.name;
    }

    public char getSymbol(){
        return this.symbol;
    }

    public Boolean isPlaying(){
        return this.play;
    }

    public void setName(String new_name){
        this.name = new_name;
    }

    public void setSymbol(char new_symbol){
        if (new_symbol != 'X' && new_symbol != 'O'){
            throw new RuntimeException("Incorrect symbol");
        }
        this.symbol = new_symbol;
    }

    public void changeState(){
        if (this.play) this.play = false;
        else this.play = true;
    }

    public String toString(){
        return (this.play ? "[Playing] " : "[Idle] ") + this.name.toUpperCase() + " : " + this.symbol;
    }

    public static void main(String[] args) {
        Player player = new Player("Player", 'X');
        System.out.println(player);
        player.changeState();
        System.out.println(player);
    }
}