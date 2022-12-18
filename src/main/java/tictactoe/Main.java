package tictactoe;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static Boolean START = true;
    private final static String[][] COMBINATIONS = {
            { "00", "01", "02" }, // row 1
            { "10", "11", "12" }, // row 2
            { "20", "21", "22" }, // row 3
            { "00", "10", "20" }, // col 1
            { "01", "11", "21" }, // col 2
            { "02", "12", "22" }, // col 3
            { "00", "11", "22" }, // diag 1
            { "02", "11", "20" }, // diag 2
    };

    private static String winner = "", loser = "";
    public static CSVWriter writer;
    public static CSVReader reader;

    public static void initWriter(String filepath, String... headerElements) {
        writer = new CSVWriter(filepath);
        try {
            writer.write(headerElements);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void initReader(String filepath) {
        reader = new CSVReader(filepath);
    }

    public static void startFirst(Player p1, Player p2) {
        if (Math.random() < 0.5)
            p1.changeState();
        else
            p2.changeState();
    }

    public static void saveInput(Grid grid, Player player) {

        String picked = player.getCurrentMove();

        int i = Integer.parseInt(Character.toString(picked.charAt(0)));
        int j = Integer.parseInt(Character.toString(picked.charAt(1)));

        if (i > 2 || j > 2) {
            System.out.println("Out of bounds !");
        } else if (i < 0 || j < 0) {
            System.out.println("Negative indices not supported !");
        } else {
            grid.updateCellValue(grid.getCellByCoords(i, j), player.getSymbol());
            return;
        }

    }

    private static void printGridModel() {
        StringBuilder builder = new StringBuilder();
        builder
                .append(" 00 | ")
                .append("01 | ")
                .append("02\n")
                .append("---- ".repeat(3))
                .append("\n")
                .append(" 10 | ")
                .append("11 | ")
                .append("12\n")
                .append("---- ".repeat(3))
                .append("\n")
                .append(" 20 | ")
                .append("21 | ")
                .append("22\n");
        System.out.println(builder.toString());
    }

    private static String getPlayerInfo(Player p) {
        return p.getSymbol() + " | " + p.getName() + " : Playing";
    }

    public static void playPVP(Grid grid, Player p1, Player p2) {

        // Determine which player starts at first
        startFirst(p1, p2);

        printGridModel();

        Scanner sc = new Scanner(System.in);

        while (START) {

            // Determine which player's turn it is
            Player currentPlayer = p1.isPlaying() ? p1 : p2;
            System.out.println(getPlayerInfo(currentPlayer));

            // Allow player to type location or Quit and save his move
            System.out.println("Possible moves : " + currentPlayer.getMoves().toString());
            System.out.print("Pick one case by typing its location (type Quit to quit) : ");
            String input = sc.next();
            if ("Quit".equalsIgnoreCase(input)) {
                break;
            }
            currentPlayer.saveMove(input);

            // Remove the move from the other player's list of possible moves
            if (p1.isPlaying())
                p2.deleteMove(currentPlayer.getCurrentMove());
            else
                p1.deleteMove(currentPlayer.getCurrentMove());

            // Save input and display the grid
            saveInput(grid, currentPlayer);
            System.out.println("--- Grid ---\n\n" + grid + "\n");

            // Switch turn and check if game is over
            switchTurn(p1, p2);
            end(grid, p1, p2);
        }

        sc.close();
    }

    private static void AIAction(AI ai, Grid g, String move) {

        if (move.length() == 0) {
            ai.randomMove();
            return;
        }

        double rand = Math.random();

        if (rand <= 0.25) {
            System.out.println("Stategy : complete the row");
            ai.completeRow(g, move);
        } else if (rand > 0.25 && rand <= 0.5) {
            System.out.println("Stategy : complete the column");
            ai.completeCol(g, move);
        } else if (rand > 0.5 && rand <= 0.75) {
            System.out.println("Stategy : playing at the opposite");
            ai.oppositeMove(g, move);
        } else {
            System.out.println("Stategy : playing randomly");
            ai.randomMove();
        }
    }

    public static void playAIvsAI(Grid grid) {

        AI p1 = new AI("BOT1", 'X');
        AI p2 = new AI("BOT2", 'O');

        // Determine which player starts at first
        startFirst(p1, p2);

        printGridModel();

        while (START) {

            // Sleep for 2 seconds
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Determine which player's turn it is
            AI currentPlayer = p1.isPlaying() ? p1 : p2;
            System.out.println(getPlayerInfo(currentPlayer));
            System.out.println("Possible moves : " + currentPlayer.getMoves().toString());

            // Have the AI make a move
            AIAction(currentPlayer, grid, currentPlayer.getCurrentMove());

            // Remove the move from the other player's list of possible moves
            System.out.println("Chosen move : " + currentPlayer.getCurrentMove());
            if (p1.isPlaying())
                p2.deleteMove(currentPlayer.getCurrentMove());
            else
                p1.deleteMove(currentPlayer.getCurrentMove());

            // Save the move and print the updated grid
            saveInput(grid, currentPlayer);
            System.out.println("--- Grid ---\n\n" + grid + "\n");

            // Switch turns and check if the game is over
            switchTurn(p1, p2);
            end(grid, p1, p2);
        }
    }

    public static void playPlayerVsIA(Grid grid, Player p) {
        AI ai = new AI("BOT", p.getSymbol() == 'X' ? 'O' : 'X');

        // Determine which player starts at first
        startFirst(p, ai);

        printGridModel();

        Scanner sc = new Scanner(System.in);

        while (START) {
            // Sleep for 2 seconds
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (p.isPlaying()) {
                // Player's turn
                System.out.println(getPlayerInfo(p));
                System.out.println("Possible moves : " + p.getMoves().toString());
                System.out.print("Pick one case by typing its location (type Quit to quit) : ");
                String input = sc.next();
                if ("Quit".equalsIgnoreCase(input)) {
                    break;
                }

                p.saveMove(input);
                ai.deleteMove(p.getCurrentMove());
                saveInput(grid, p);

            } else {
                // AI's turn
                System.out.println(getPlayerInfo(ai));
                System.out.println("Possible moves : " + ai.getMoves().toString());

                AIAction(ai, grid, p.getCurrentMove());
                System.out.println("Chosen move : " + ai.getCurrentMove());
                p.deleteMove(ai.getCurrentMove());
                saveInput(grid, ai);

            }

            // Print the updated grid and switch turns
            System.out.println("--- Grid ---\n\n" + grid + "\n");
            switchTurn(p, ai);
            end(grid, p, ai);
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

    // "Player", "Opponent", "Winner", "Loser", "isDraw", "Date"
    public static String endGameMessage(Player p1, Player p2) {
        if (winner.length() == 0) {
            String[] row = { p1.getName(), p2.getName(), "Nobody", "Nobody", "Yes", new Date().toString() };
            try {
                writer.append(row);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "Draw !";
        }
        String[] row = { p1.getName(), p2.getName(), winner, loser, "No", new Date().toString() };
        try {
            writer.append(row);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "We have a winner ! It's " + winner + " !";
    }

    public static void end(Grid grid, Player p1, Player p2) {
        if (isWin(grid, p1, p2)) {
            START = false;
            System.out.println(endGameMessage(p1, p2));
        } else if (isDraw(grid, p1, p2)) {
            START = false;
            System.out.println(endGameMessage(p1, p2));
        }
    }

    private static Boolean match(ArrayList<String> list) {
        if (list.size() > 2) {
            Collections.sort(list);
            for (String[] combination : COMBINATIONS) {
                List<String> combinationList = Arrays.asList(combination);
                if (list.containsAll(combinationList)) {
                    return true;
                }
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

            if (!cells.get(i).isEmpty() && cells.get(i).getValue() == Symbol.X.getValue()) {
                int[] coords = cells.get(i).getCoords();
                positionsX.add(String.valueOf(coords[0]) + String.valueOf(coords[1]));
            }
            if (!cells.get(i).isEmpty() && cells.get(i).getValue() == Symbol.O.getValue()) {
                int[] coords = cells.get(i).getCoords();
                positionsO.add(String.valueOf(coords[0]) + String.valueOf(coords[1]));
            }
        }
        if (match(positionsX)) {
            winner = p1.getSymbol() == Symbol.X.getValue() ? p1.getName() : p2.getName();
            loser = p1.getSymbol() != Symbol.X.getValue() ? p1.getName() : p2.getName();
            return true;
        }

        if (match(positionsO)) {
            winner = p1.getSymbol() == Symbol.O.getValue() ? p1.getName() : p2.getName();
            loser = p1.getSymbol() != Symbol.O.getValue() ? p1.getName() : p2.getName();
            return true;
        }
        return false;
    }

    public static void chooseModeAndPlay(Grid grid, Player p1, Player p2) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Choose a mode:");
        System.out.println("  1. AI vs AI");
        System.out.println("  2. Player vs Player");
        System.out.println("  3. Player vs AI");
        System.out.println("  4. Quit");

        int mode = sc.nextInt();

        if (mode == 1) {
            playAIvsAI(grid);
        } else if (mode == 2) {
            playPVP(grid, p1, p2);
        } else if (mode == 3) {
            playPlayerVsIA(grid, p1);
        } else {
            System.exit(1);
        }

        sc.close();
    }

    public static void main(String[] args) {
        String path = System.getProperty("user.dir") + File.separator + "src" + File.separator + "res" + File.separator
                + "Results.csv";
        initWriter(path, "Player", "Opponent", "Winner", "Loser", "isDraw", "Date");
        initReader(path);
        Grid grid = new Grid();
        Player p1 = new Player("Player 1", 'X');
        Player p2 = new Player("Player 2", 'O');

        chooseModeAndPlay(grid, p1, p2);
    }

}
