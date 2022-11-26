import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static Boolean START = true;
    public final static String[][] COMBINATIONS = {
            { "00", "01", "02" }, // row 1
            { "10", "11", "12" }, // row 2
            { "20", "21", "22" }, // row 3
            { "00", "10", "20" }, // col 1
            { "01", "11", "21" }, // col 2
            { "02", "12", "22" }, // col 3
            { "00", "11", "22" }, // diag 1
            { "02", "11", "20" }, // diag 2
    };

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
                    System.out.println("Wrong location chosen !");
                    break;
            }

            switchTurn(p1, p2);
            end(grid, p1, p2);
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
        if (isWin(grid, p1, p2)) {
            START = false;
            System.out.println("WINNER");
        } else if (isDraw(grid, p1, p2)) {
            START = false;
            System.out.println("DRAW");
        }
    }

    public static Boolean match(ArrayList<String> list) {
        if (list.size() > 2) {
            String[] array = list.toArray(new String[list.size()]);
            Arrays.sort(array);
            for (String[] row : COMBINATIONS) {
                if (Arrays.equals(row, array)) {
                    System.out.println(list.toString());
                    return true;
                }
            }
        }
        return false;
    }

    public static Boolean isDraw(Grid grid, Player p1, Player p2) {
        if (isWin(grid, p1, p2))
            return false;
        Cell[] cells = grid.getCells();
        int count = 0;
        for (Cell c : cells) {
            if (!c.isEmpty()) {
                count++;
            }
        }
        return count == cells.length ? true : false;
    }

    public static Boolean isWin(Grid grid, Player p1, Player p2) {
        Cell[] cells = grid.getCells();
        ArrayList<String> positionsX = new ArrayList<>();
        ArrayList<String> positionsO = new ArrayList<>();

        for (int i = 0; i < cells.length; i++) {
            if (!cells[i].isEmpty() && cells[i].getValue() == Cell.SYMBOLS[1]) {
                int[] coords = cells[i].getCoords();
                positionsX.add(String.valueOf(coords[0]) + String.valueOf(coords[1]));
            }
            if (!cells[i].isEmpty() && cells[i].getValue() == Cell.SYMBOLS[2]) {
                int[] coords = cells[i].getCoords();
                positionsO.add(String.valueOf(coords[0]) + String.valueOf(coords[1]));
            }
        }
        return match(positionsX) || match(positionsO);
    }

    public static void main(String[] args) {
        Grid grid = new Grid();
        Player p1 = new Player("Player 1", 'X');
        Player p2 = new Player("Player 2", 'O');

        Main.play(grid, p1, p2);
    }

}
