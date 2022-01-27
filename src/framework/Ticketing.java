package framework;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

public class Ticketing {
    public static void main(String[] args) throws IOException {
        Hashtable<Integer, String> hashtable = new Hashtable<>();
        BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));

        for (int i = 1; i <= 30_000_000 ; i++) {
            hashtable.put(i, "PETER");
        }
        hashtable.put(10_000_000, "JOHN");
        for (Map.Entry<Integer, String> entry : hashtable.entrySet()) {
            // put key and value separated by a colon
            writer.write(entry.getKey() + ":" + entry.getValue());
            // new line
            writer.newLine();
        }
        writer.flush();
    }

}
