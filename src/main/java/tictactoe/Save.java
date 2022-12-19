package tictactoe;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public interface Save {
    
    static void writeRow(BufferedWriter writer, String[] data) throws IOException {

        for (int i = 0; i < data.length; i++) {
            writer.write(data[i]);
            if (i < data.length - 1) {
                writer.write(",");
            }
        }

        writer.newLine();
    }

    static void write(String path, String[] header, String[]... data) throws IOException {

        BufferedWriter writer = new BufferedWriter(new FileWriter(path));
        writeRow(writer, header);
        for (String[] row : data) {
            writeRow(writer, row);
        }
        writer.close();
    }

    static void append(String path, String[]... data) throws IOException {

        BufferedWriter writer = new BufferedWriter(new FileWriter(path, true));
        for (String[] row : data) {
            writeRow(writer, row);
        }
        writer.close();

    }

    static String[] getHeader(String path) throws IOException {

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            return reader.readLine().split(",");
        }
    }

    static String[] getRow(String path, int index) throws IOException {

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            return ((String) reader.lines().toArray()[index]).split(",");
        }
    }

    static List<String> getCol(String path, int colIndex) throws IOException {

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            return reader.lines()
                    .map(line -> line.split(" "))
                    .map(split -> {
                        String col = "";
                        for (String item : split) {
                            col += item.split(",")[colIndex];
                        }
                        return col;
                    })
                    .collect(Collectors.toList());
        }
    };

    static List<String> getAllContent(String path) throws IOException {

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            return reader.lines().toList();
        }
    }

    static int size(String path) throws IOException {

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            return reader.lines().toList().size();
        }
    }

    static Boolean isEmpty(String path) throws IOException {
        return size(path) == 0;
    }
}
