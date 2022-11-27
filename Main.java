import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
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
        if (Math.random() < 0.5) p1.changeState();
        else p2.changeState();
    }

    public static void saveInput(Grid grid, char picked, char symbol) {
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

            case 'Q':
                System.exit(0);
                break;

            default:
                System.out.println("Wrong location chosen !");
                break;
        }
    }

    public static void playPVP(Grid grid, Player p1, Player p2) {
        startFirst(p1, p2);
        Scanner sc = new Scanner(System.in);
        while (START) {

            System.out.println(
                    "--- Grid model ---\n\n" +
                            " A | " +
                            "B | " +
                            "C\n" + "--- ".repeat(3) + "\n" +
                            " D | " +
                            "E | " +
                            "F\n" + "--- ".repeat(3) + "\n" +
                            " G | " +
                            "H | " +
                            "I\n");
            System.out.println("--- Grid ---\n\n" + grid + "\n");
            String name = p1.isPlaying() ? p1.getName() : p2.getName();
            char symbol = p1.isPlaying() ? p1.getSymbol() : p2.getSymbol();
            System.out.println(name + " (" + symbol + ") is playing.");
            System.out.println("Type Q to quit.");
            System.out.print("Pick one case by typing its corresponding character : ");
            

            char picked = sc.next().charAt(0);

            saveInput(grid, picked, symbol);
            switchTurn(p1, p2);
            end(grid, p1, p2);
        }
        sc.close();
    }

    public static void playIAvsIA(Grid grid, Player p1, Player p2) {
        startFirst(p1, p2);
        ArrayList<Character> inputs = new ArrayList<>(Arrays.asList('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I'));
        Scanner sc = new Scanner(System.in);
        while (START) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("--- Grid ---\n\n" + grid + "\n");
            System.out.println("List of available moves : " + inputs.toString());
            String name = p1.isPlaying() ? p1.getName() : p2.getName();
            char symbol = p1.isPlaying() ? p1.getSymbol() : p2.getSymbol();
            System.out.println(name + " (" + symbol + ") is playing.");
            char picked = inputs.size() > 1 ? inputs.get(new Random().nextInt(inputs.size() - 1)) : inputs.get(0);
            System.out.println(name + " has chosen " + picked);
            inputs.remove(inputs.indexOf(picked));

            saveInput(grid, picked, symbol);
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
                if(list.containsAll(rowList)) return true;
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

    public static void main(String[] args) {
        Grid grid = new Grid();
        Player p1 = new Player("Player 1", 'X');
        Player p2 = new Player("Player 2", 'O');

        Scanner sc = new Scanner(System.in);
        System.out.println("Choose a mode:");
        System.out.println("\t1. IA vs IA");
        System.out.println("\t2. Player vs Player");

        if (sc.nextInt() == 1) Main.playPVP(grid, p1, p2);
        else Main.playIAvsIA(grid, p1, p2);

        sc.close();
    }

}
