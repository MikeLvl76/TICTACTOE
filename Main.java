import java.util.Scanner;

public class Main {

    public static Boolean START = true;
    public static Boolean END = false;

    public static void play(Grid grid, Player p1, Player p2) {
        Scanner sc = new Scanner(System.in);
        while (START) {

            char symbol = p1.isPlaying() ? p1.getSymbol() : p2.getSymbol();
            System.out.println(grid);
            System.out.println(
                    "--- Pick a location ---\n" +
                            "-> A = (0, 0)\n" +
                            "-> B = (0, 1)\n" +
                            "-> C = (0, 2)\n" +
                            "-> D = (1, 0)\n" +
                            "-> E = (1, 1)\n" +
                            "-> F = (1, 2)\n" +
                            "-> G = (2, 0)\n" +
                            "-> H = (2, 1)\n" +
                            "-> I = (2, 2)\n");

            char picked = sc.next().charAt(0);

            switch (picked) {

                case 'A':
                    Cell cellA = grid.getCellByCoords(0, 0);
                    grid.updateCellValue(cellA, symbol);
                    break;

                case 'B':
                    Cell cellB = grid.getCellByCoords(0, 1);
                    grid.updateCellValue(cellB, symbol);
                    break;

                case 'C':
                    Cell cellC = grid.getCellByCoords(0, 2);
                    grid.updateCellValue(cellC, symbol);
                    break;

                case 'D':
                    Cell cellD = grid.getCellByCoords(1, 0);
                    grid.updateCellValue(cellD, symbol);
                    break;

                case 'E':
                    Cell cellE = grid.getCellByCoords(1, 1);
                    grid.updateCellValue(cellE, symbol);
                    break;

                case 'F':
                    Cell cellF = grid.getCellByCoords(1, 2);
                    grid.updateCellValue(cellF, symbol);
                    break;

                case 'G':
                    Cell cellG = grid.getCellByCoords(2, 0);
                    grid.updateCellValue(cellG, symbol);
                    break;

                case 'H':
                    Cell cellH = grid.getCellByCoords(2, 1);
                    grid.updateCellValue(cellH, symbol);
                    break;

                case 'I':
                    Cell cellI = grid.getCellByCoords(2, 2);
                    grid.updateCellValue(cellI, symbol);
                    break;

                default:
                    break;
            }

            switchTurn(p1, p2);
        }
        sc.close();
    }

    public static void switchTurn(Player p1, Player p2) {
        if (p1.isPlaying()) {
            p1.changeState();
            p2.changeState();
        } else {
            p2.changeState();
            p1.changeState();
        }

    }

    public static void end(Grid grid, Player p1, Player p2) {

    }

    public static Boolean isWin(Grid grid) {
        return false;
    }

    public static Boolean isDraw(Grid grid) {
        return false;
    }

    public static void main(String[] args) {
        Grid grid = new Grid();
        Player p1 = new Player("Player 1", 'X');
        Player p2 = new Player("Player 2", 'O');
        Main.play(grid, p1, p2);
    }

}
