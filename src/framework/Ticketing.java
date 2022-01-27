package framework;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class Ticketing extends Process {
    public void submitResetTicket() throws IOException {
        resetPinTickets.put(getUserName(), false);
        File userAccountFolder = new File("src\\" + "files\\" + "resetPinTickets\\" + getUserName());
        boolean success = userAccountFolder.mkdirs();
        if (success) {
            BufferedWriter writer = new BufferedWriter(new FileWriter("src\\" + "files\\" + "resetPinTickets\\" + getUserName() + "\\tickets.txt"));
            for (Map.Entry<String, Boolean> entry : resetPinTickets.entrySet()) {
                // put key and value separated by a colon
                writer.write("USER: " + entry.getKey() + " : IS ELIGIBLE: " + entry.getValue());
                // new line
                writer.newLine();
            }
            writer.flush();
        }
    }
}
