package tictactoe;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.opencsv.CSVWriter;

public class Save {
    
    private String filepath;
    private File file;

    public Save(String _filepath) {
        this.filepath = _filepath;
        this.file = new File(this.filepath);
    }

    public String getFilepath() {
        return this.filepath;
    }
    
    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }
    
    public File getFile() {
        return this.file;
    }
    
    public void setFile(File file) {
        this.file = file;
    }
    
    public void writeCSV(String[] header, String[] ...data) {
        try {
            // create FileWriter object with file as parameter
            FileWriter outputfile = new FileWriter(this.file);
      
            // create CSVWriter object filewriter object as parameter
            CSVWriter writer = new CSVWriter(outputfile);
      
            // adding header to csv
            writer.writeNext(header);

            for (String[] row : data) {
                writer.writeNext(row);
            }
      
            // closing writer connection
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String fullPath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "res" + File.separator + "test.csv";
        Save saver = new Save(fullPath);
        String[] header = {"One", "Two"};
        String[] d1 = {"1", "2"};
        String[] d2 = {"3", "4"};
        saver.writeCSV(header, d1, d2);
    }

}
