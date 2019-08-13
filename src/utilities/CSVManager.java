package utilities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Adrian Vazquez
 */
public class CSVManager {

    private File file;
    private List<List<String>> data;

    public CSVManager(File file) {
        this.file = file;
    }

    private void read() throws FileNotFoundException, IOException {


        List<List<String>> data = new LinkedList<>();

        List<String> allLines = Files.readAllLines(file.toPath());
        
        allLines.forEach((line) -> {
            data.add(split(line));
        });
        
      
        this.data = data;

    }


    private static List<String> split(String l) {

        List<String> line = new LinkedList<>();

        boolean canSplit = true;

        String buffer = "";
        l = l + ",";
        for (int i = 0; i < l.length(); i++) {

            if (l.charAt(i) == '\"') {
                canSplit = !canSplit;
            } else if ((l.charAt(i) == ',' && canSplit)) {
                line.add(buffer);
                buffer = "";
            } else {
                buffer += l.charAt(i);
            }
        }

        return line;
    }

    public synchronized List<List<String>> getAll() throws Exception {

        if (data == null) {
            read();
        }

        return data;
    }

    public synchronized void setData(List<List<String>> data) {
        this.data = data;
    }

    public synchronized void save() throws FileNotFoundException, IOException {

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));

        for (List<String> line : data) {
            writeLine(writer, line);
        }

        writer.close();
    }

    private synchronized void writeLine(BufferedWriter writer, List<String> line) throws IOException {

        String buffer = "";

        buffer = line
                .stream()
                .map((s) -> s.replaceAll("\"", "''"))
                .map((resource) -> "\"" + resource + "\"")
                .map((resource) -> resource + ",")
                .reduce(buffer, String::concat);

        /*
                for (String s : line) {

            String resource = s.replaceAll("\"", "''");

            resource = "\"" + resource + "\"";

            buffer += resource + ",";
        }
         */
        if (buffer.length() > 1) {
            buffer = buffer.substring(0, buffer.length() - 1);
        }

        writer.write(buffer);
        writer.newLine();

    }

}
