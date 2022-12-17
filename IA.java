import java.util.Random;

public class IA extends Player {

    public IA(String _n, char _s) {
        super(_n, _s);
    }

    public void randomMove() {
        this.saveMove(this.moves.get(new Random().nextInt(this.moves.size())));
    }

    public void oppositeMove(Grid g, String loc) {

        int row = Integer.parseInt(Character.toString(loc.charAt(0)));
        int col = Integer.parseInt(Character.toString(loc.charAt(1)));
    
        int oppositeRow = 2 - row;
        int oppositeCol = 2 - col;
    
        if (g.getCellByCoords(oppositeRow, oppositeCol).isEmpty()) {
            this.saveMove(String.valueOf(oppositeRow) + String.valueOf(oppositeCol));
        } else {
            randomMove();
        }
    }


    
}
