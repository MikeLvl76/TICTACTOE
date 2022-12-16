import java.util.ArrayList;
import java.util.Random;

public class IA extends Player{

    public IA(String _n, char _s) {
        super(_n, _s);
    }

    public void randomMove(ArrayList<String> moves) {
        this.saveMove(moves.get(new Random().nextInt(moves.size())));
    }
    
}
