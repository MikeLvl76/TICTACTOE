import java.util.ArrayList;
import java.util.Random;

public class IA extends Player{

    public IA(String _n, char _s) {
        super(_n, _s);
    }

    public String randomMove(ArrayList<String> moves) {
        return moves.get(new Random().nextInt(moves.size()));
    }
    
}
