package tictactoe;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CSVWriter {

    private String path;
    private BufferedWriter writer;

    public CSVWriter(String filepath) {
        this.path = filepath;
    }

    private void writeRow(BufferedWriter writer, String[] data) throws IOException {

        for (int i = 0; i < data.length; i++) {
            writer.write(data[i]);
            if (i < data.length - 1) {
                writer.write(",");
            }
        }

        writer.newLine();
    }

    public void write(String[] header, String[]... data) throws IOException {

        this.writer = new BufferedWriter(new FileWriter(this.path));
        writeRow(writer, header);
        for (String[] row : data) {
            writeRow(writer, row);
        }
        writer.close();
    }

    public void append(String[]... data) throws IOException {

        this.writer = new BufferedWriter(new FileWriter(this.path, true));
        for (String[] row : data) {
            writeRow(writer, row);
        }
        writer.close();

    }

    public static void main(String[] args) throws IOException {
        String path = System.getProperty("user.dir") + File.separator + "src" + File.separator + "res" + File.separator + "test.csv";
        CSVWriter csvWriter = new CSVWriter(path);
        /*String[] header = {"Col1", "Col2"};
        String[] row1 = {"Data1", "Data2"};
        String[] row2 = {"Data3", "Data4"};
        csvWriter.write(header, row1, row2);*/
        String[] row3 = {"Data5", "Data6"};
        csvWriter.append(row3);
    }
}
