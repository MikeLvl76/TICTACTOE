
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
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

    public static String winner = "";

    public static void startFirst(Player p1, Player p2) {
        if (Math.random() < 0.5)
            p1.changeState();
        else
            p2.changeState();
    }

    public static void saveInput(Grid grid, String picked, char symbol) {

        if (picked.equals("Quit"))
            System.exit(0);

        int i = Integer.parseInt(Character.toString(picked.charAt(0)));
        int j = Integer.parseInt(Character.toString(picked.charAt(1)));

        if (i > 2 || j > 2) {
            System.out.println("Out of bounds !");
        } else if (i < 0 || j < 0) {
            System.out.println("Negative indices not supported !");
        } else {
            Cell cell = grid.getCellByCoords(i, j);
            grid.updateCellValue(cell, symbol);
            return;
        }

    }

    public static void playPVP(Grid grid, Player p1, Player p2) {
        startFirst(p1, p2);
        Scanner sc = new Scanner(System.in);
        while (START) {

            System.out.println(
                    "--- Grid model ---\n\n" +
                            " 00 | " +
                            "01 | " +
                            "02\n" + "--- ".repeat(3) + "\n" +
                            " 10 | " +
                            "11 | " +
                            "12\n" + "--- ".repeat(3) + "\n" +
                            " 20 | " +
                            "21 | " +
                            "22\n");
            System.out.println("--- Grid ---\n\n" + grid + "\n");
            String name = p1.isPlaying() ? p1.getName() : p2.getName();
            char symbol = p1.isPlaying() ? p1.getSymbol() : p2.getSymbol();
            System.out.println(name + " (" + symbol + ") is playing.");
            System.out.println("Type Quit to quit.");
            System.out.print("Pick one case by typing its corresponding character : ");

            saveInput(grid, sc.next(), symbol);
            switchTurn(p1, p2);
            end(grid, p1, p2);
        }
        sc.close();
    }

    public static void playIAvsIA(Grid grid) {
        IA ia_1 = new IA("BOT1", 'X');
        IA ia_2 = new IA("BOT2", 'O');
        ArrayList<String> moves = new ArrayList<>(Arrays.asList("00", "01", "02", "10", "11", "12", "20", "21", "22"));
        startFirst(ia_1, ia_2);
        System.out.println("--- Grid ---\n\n" + grid + "\n");

        while (START) {

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            String choice = "";
            char symbol = '\0';

            if (ia_1.isPlaying()) {
                System.out.println(ia_1.getName() + " (" + ia_1.getSymbol() + ") is playing.");
                symbol = ia_1.getSymbol();
                choice = ia_1.randomMove(moves);
                moves.remove(choice);
                System.out.println(ia_1.getName() + " has picked : " + choice);
            } else {
                System.out.println(ia_2.getName() + " (" + ia_2.getSymbol() + ") is playing.");
                symbol = ia_2.getSymbol();
                choice = ia_2.randomMove(moves);
                moves.remove(choice);
                System.out.println(ia_2.getName() + " has picked : " + choice);
            }

            saveInput(grid, choice, symbol);
            System.out.println("--- Grid ---\n\n" + grid + "\n");
            switchTurn(ia_1, ia_2);
            end(grid, ia_1, ia_2);
        }
    }

    public static void playPlayerVsIA(Grid grid, Player p) {
        IA ia = new IA("BOT", p.getSymbol() == 'X' ? 'O': 'X');
        startFirst(p, ia);
        ArrayList<String> moves = new ArrayList<>(Arrays.asList("00", "01", "02", "10", "11", "12", "20", "21", "22"));
        System.out.println("--- Grid ---\n\n" + grid + "\n");
        Scanner sc = new Scanner(System.in);

        while (START) {

            String choice = "";
            char symbol = '\0';

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (p.isPlaying()) {
                System.out.println(p.getName() + " (" + p.getSymbol() + ") is playing.");
                symbol = p.getSymbol();
                System.out.print("Make your choice : ");
                choice = sc.next();
                moves.remove(choice);
                System.out.println(p.getName() + " has picked : " + choice);
            } else {
                System.out.println(ia.getName() + " (" + ia.getSymbol() + ") is playing.");
                symbol = ia.getSymbol();
                choice = ia.randomMove(moves);
                moves.remove(choice);
                System.out.println(ia.getName() + " has picked : " + choice);
            }

            saveInput(grid, choice, symbol);
            System.out.println("--- Grid ---\n\n" + grid + "\n");
            switchTurn(p, ia);
            end(grid, p, ia);
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
            System.out.println("Result :\n" + grid);
            System.out.println("The winner is : " + winner.toUpperCase());
        } else if (isDraw(grid, p1, p2)) {
            START = false;
            System.out.println("Result :\n" + grid);
            System.out.println("DRAW");
        }
    }

    public static Boolean match(ArrayList<String> list) {
        if (list.size() > 2) {
            Collections.sort(list);
            for (String[] row : COMBINATIONS) {
                List<String> rowList = Arrays.asList(row);
                if (list.containsAll(rowList))
                    return true;
            }
        }
        return false;
    }

    public static Boolean isDraw(Grid grid, Player p1, Player p2) {
        return !isWin(grid, p1, p2) && grid.getCells().stream().allMatch((cell) -> !cell.isEmpty());
    }

    public static Boolean isWin(Grid grid, Player p1, Player p2) {
        ArrayList<Cell> cells = grid.getCells();
        ArrayList<String> positionsX = new ArrayList<>();
        ArrayList<String> positionsO = new ArrayList<>();

        for (int i = 0; i < cells.size(); i++) {

            if (!cells.get(i).isEmpty() && cells.get(i).getValue() == Cell.SYMBOLS[1]) {
                int[] coords = cells.get(i).getCoords();
                positionsX.add(String.valueOf(coords[0]) + String.valueOf(coords[1]));
            }
            if (!cells.get(i).isEmpty() && cells.get(i).getValue() == Cell.SYMBOLS[2]) {
                int[] coords = cells.get(i).getCoords();
                positionsO.add(String.valueOf(coords[0]) + String.valueOf(coords[1]));
            }
        }
        if (match(positionsX)) {
            winner = p1.getSymbol() == Cell.SYMBOLS[1] ? p1.getName() : p2.getName();
            return true;
        }

        if (match(positionsO)) {
            winner = p1.getSymbol() == Cell.SYMBOLS[2] ? p1.getName() : p2.getName();
            return true;
        }
        return false;
    }

    public static void chooseModeAndPlay(Grid grid, Player p1, Player p2) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Choose a mode:");
        System.out.println("\t1. IA vs IA");
        System.out.println("\t2. Player vs Player");
        System.out.println("\t3. Player vs IA");

        switch (sc.nextInt()) {
            case 1:
                playIAvsIA(grid);
                break;

            case 2:
                playPVP(grid, p1, p2);
                break;

            case 3:
                playPlayerVsIA(grid, p1);
                break;

            default:
                break;
        }

        sc.close();
    }

    public static void main(String[] args) {
        Grid grid = new Grid();
        Player p1 = new Player("Player 1", 'X');
        Player p2 = new Player("Player 2", 'O');

        Main.chooseModeAndPlay(grid, p1, p2);
    }

}
