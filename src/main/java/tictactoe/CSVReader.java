package tictactoe;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class CSVReader {

    private String path;
    BufferedReader reader;

    public CSVReader(String filepath) {
        this.path = filepath;
    }

    public String[] getHeader() throws IOException {

        this.reader = new BufferedReader(new FileReader(this.path));
        String[] header = this.reader.readLine().split(",");
        this.reader.close();

        return header;
    }

    public String[] getRow(int index) throws IOException{

        this.reader = new BufferedReader(new FileReader(this.path));
        String[] row = ((String) this.reader.lines().toArray()[index]).split(",");
        this.reader.close();

        return row;
    }

    public List<String> getCol(int colIndex) throws IOException {

        this.reader = new BufferedReader(new FileReader(this.path));
        List<String> content = this.reader.lines().map((elt) -> {
            String[] split = elt.split(" ");
            String col = "";
            for(String item : split) {
                col += item.split(",")[colIndex];
            }
            return col;
        }).toList();

        this.reader.close();

        return content;
    }

    public List<String> getAllContent() throws IOException {

        this.reader = new BufferedReader(new FileReader(this.path));
        List<String> rows = this.reader.lines().toList();
        this.reader.close();

        return rows;
    }

    public int size() throws IOException {

        this.reader = new BufferedReader(new FileReader(this.path));
        int size = this.reader.lines().toList().size();
        this.reader.close();

        return size;

    }

    public Boolean isEmpty() throws IOException {
        return this.size() == 0;
    }

    public static void main(String[] args) {
        String path = System.getProperty("user.dir") + File.separator + "src" + File.separator + "res" + File.separator
                + "test.csv";
        CSVReader csvReader = new CSVReader(path);
        try {
            String [] rows = csvReader.getRow(1);
            for (String row : rows) {
                System.out.println(row);
            }

            System.out.println(csvReader.getAllContent().toString());
            System.out.println(csvReader.getCol(0));
            System.out.println(csvReader.getCol(1));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
