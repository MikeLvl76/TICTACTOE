import java.util.Random;

public class IA extends Player{

    public IA(String _n, char _s) {
        super(_n, _s);
    }

    public void randomMove() {
        this.saveMove(this.moves.get(new Random().nextInt(this.moves.size())));
    }
    
}
