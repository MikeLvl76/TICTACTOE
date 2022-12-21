package tictactoe;
import java.util.Random;

public class AI extends Player {

    public AI(String _n, char _s) {
        super(_n, _s);
    }

    public void randomMove() {
        this.saveMove(this.moves.get(new Random().nextInt(this.moves.size())));
    }

    public void oppositeMove(Grid g, String loc) {

        int row = loc.charAt(0) - '0';
        int col = loc.charAt(1) - '0';

        int oppositeRow = 2 - row;
        int oppositeCol = 2 - col;

        if (g.getCellByCoords(oppositeRow, oppositeCol).isEmpty()) {
            this.saveMove(String.valueOf(oppositeRow) + String.valueOf(oppositeCol));
        } else {
            String oppositeLoc = String.valueOf(oppositeRow) + String.valueOf(oppositeCol);
            oppositeMove(g, oppositeLoc);
        }
    }

    public void completeCol(Grid g, String loc) {

        int row = loc.charAt(0) - '0';
        int col = loc.charAt(1) - '0';

        int nextRow = row == 2 ? 0 : row + 1;
        int nextCol = row == 2 ? (col == 2 ? 0 : col + 1) : col;

        if (g.getCellByCoords(nextRow, nextCol).isEmpty()) {
            this.saveMove(String.valueOf(nextRow) + String.valueOf(nextCol));
        } else {
            String nextLoc = String.valueOf(nextRow) + String.valueOf(nextCol);
            completeCol(g, nextLoc);
        }
    }

    public void completeRow(Grid g, String loc) {

        int row = loc.charAt(0) - '0';
        int col = loc.charAt(1) - '0';

        int nextRow = col == 2 ? (row == 2 ? 0 : row + 1) : row;
        int nextCol = col == 2 ? 0 : col + 1;

        if (g.getCellByCoords(nextRow, nextCol).isEmpty()) {
            this.saveMove(String.valueOf(nextRow) + String.valueOf(nextCol));
        } else {
            String nextLoc = String.valueOf(nextRow) + String.valueOf(nextCol);
            completeRow(g, nextLoc);
        }
    }
}
