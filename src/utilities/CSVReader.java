package utilities;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Adrian Vazquez
 */
public class CSVReader {

    private File file;

    public CSVReader(File file) {
        this.file = file;
    }

    public List<String> getColums() throws FileNotFoundException {

        return readLine(0);
    }

    public int getColumCount() throws FileNotFoundException {

        return getColums().size();
    }

    public int getLineCount() throws FileNotFoundException {

        int i = 0;

        Scanner scanner = new Scanner(file);
        scanner.useDelimiter("\n");

        while (scanner.hasNext()) {
            scanner.nextLine();
            i++;
        }

        return i;
    }

    private List<String> readLine(int l) throws FileNotFoundException {

        List<String> line = new ArrayList<>();

        Scanner scanner = new Scanner(file);
        scanner.useDelimiter("\n");

        String buffer = "";

        int i = 0;
        if (scanner.hasNext()) {

            do {

                buffer = scanner.nextLine();
                i++;

            } while (i <= l && scanner.hasNext());
        }

        scanner.close();

        String[] data = buffer.split(",");

        line.addAll(Arrays.asList(data));
        
        return line;
    }
    
    public List<List<String>> getAll() throws FileNotFoundException {

        int lines = getLineCount();

        List<List<String>> allData = new LinkedList<>();

        for (int i = 1; i < lines; i++) {
            allData.add(readLine(i));
        }

        return allData;
    }

    public void addColumn(String columName) throws FileNotFoundException, IOException {

        List<List<String>> lines = getAll();

        file.delete();

        Iterator<List<String>> it = lines.iterator();

        List<String> header;

        if (it.hasNext()) {

            header = it.next();

        } else {

            header = new LinkedList<>();
        }

        header.add(columName);

        List<String> data;
        while (it.hasNext()) {

            data = it.next();
            data.add("");
        }

        setData(lines);
    }

    public void setData(List<List<String>> lines) throws FileNotFoundException, IOException {

        BufferedWriter writter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));

        lines
                .forEach((line) -> {

                    try {
                        writeLine(writter, line);
                    } catch (IOException ex) {
                        utilities.DisplayMessage.showErrorDialog(ex.getMessage());
                    }
                });

        writter.close();

    }

    private void writeLine(BufferedWriter writter, List<String> line) throws IOException {

        String buffer = "";

        buffer = line
                .stream()
                .map((data) -> data + ",")
                .reduce(buffer, String::concat);

        if (buffer.length() > 1) {
            buffer = buffer.substring(0, buffer.length() - 1);
        } else {
            buffer = "";
        }

        writter.write(buffer);
        writter.newLine();
    }

}
