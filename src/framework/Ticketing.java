package framework;

import mainActivity.Main;

import java.io.*;
import java.util.Map;
import java.util.Objects;

public class Ticketing extends Process {
    public void submitResetTicket(String userName) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("src\\" + "files\\" + "resetPinTickets\\" + "\\tickets.txt"));
        for (Map.Entry<String, Boolean> entry : resetPinTickets.entrySet()) {
            writer.write("[ IS TRYING TO RESET PIN: " + entry.getValue() + " ]" + " USER: " + entry.getKey()) ;
            writer.newLine();
        }
        writer.close();
    }
    public void editEligibility(boolean isAdmin) throws IOException {
        if (isAdmin) {
            BufferedReader reader =  new BufferedReader(new FileReader("src\\" + "files\\" + "resetPinTickets\\" + "\\tickets.txt"));
            String line;
            System.out.println("""
                    ┬ ┬┌─┐┌─┐┬─┐┌─┐  ┬ ┬┬ ┬┌─┐  ┬ ┬┌─┐┌┐┌┌┬┐  ┌┬┐┌─┐  ┬─┐┌─┐┌─┐┌─┐┌┬┐  ┌─┐┬┌┐┌
                    │ │└─┐├┤ ├┬┘└─┐  │││├─┤│ │  │││├─┤│││ │    │ │ │  ├┬┘├┤ └─┐├┤  │   ├─┘││││
                    └─┘└─┘└─┘┴└─└─┘  └┴┘┴ ┴└─┘  └┴┘┴ ┴┘└┘ ┴    ┴ └─┘  ┴└─└─┘└─┘└─┘ ┴   ┴  ┴┘└┘
                    """);
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            reader.close();
            System.out.println("=========================");
            System.out.println("PRESS ENTER TO CONTINUE");
            System.out.println("=========================");
            Main.scanner.nextLine();
        }
    }
}
