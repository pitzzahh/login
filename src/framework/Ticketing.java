package framework;

import mainActivity.Main;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class Ticketing extends Process {
    static boolean isThereATicket = false;
    public void submitResetTicket() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("src\\" + "files\\" + "resetPinTickets\\" + "\\tickets.txt"));
        for (Map.Entry<String, Boolean> entry : resetPinTickets.entrySet()) {
            writer.write("[ IS TRYING TO RESET PIN: " + entry.getValue() + " ]" + " USER: " + entry.getKey()) ;
            writer.newLine();
        }
        writer.close();
    }
    public void editEligibility(boolean isAdmin) throws InterruptedException {
        boolean givePermission = true;
        if (isAdmin) {
            while (givePermission) {
                viewTickets();
                if (isThereATicket) {
                    System.out.println("ENTER THE USER YOU WANT TO GIVE PERMISSION TO CHANGE PIN");
                    System.out.print(">>>: ");
                    Main.temporaryString = Main.scanner.nextLine().trim();
                    if (!isNumber(Main.temporaryString)) {
                        setUserName(Main.temporaryString);
                        if (Files.exists(Path.of("src\\" + "files\\" + "accounts\\" + "user\\" + getUserName() + "\\username.txt"))) {
                            isEligibleToChangePin.put(getUserName(),true);
                            System.out.print("GIVING PERMISSION");
                            loading("long");
                            resetPinTickets.remove(getUserName());
                            givePermission = false;
                            System.out.println("PERMISSION GRANTED (!)");
                            System.out.println("=========================");
                            System.out.println("|PRESS ENTER TO CONTINUE|");
                            System.out.println("=========================");
                            Main.scanner.nextLine();
                        }
                        else {
                            do {
                                System.out.println("""
                                ┬ ┬┬─┐┌─┐┌┐┌┌─┐  ┬ ┬┌─┐┌─┐┬─┐┌┐┌┌─┐┌┬┐┌─┐  ┬
                                │││├┬┘│ │││││ ┬  │ │└─┐├┤ ├┬┘│││├─┤│││├┤   │
                                └┴┘┴└─└─┘┘└┘└─┘  └─┘└─┘└─┘┴└─┘└┘┴ ┴┴ ┴└─┘  o
                            """);
                                System.out.println(": 1 : Retry");
                                System.out.println(": 2 : Return to ADMIN SELECTION");
                                System.out.println(": 3 : Return to LOGIN MENU");
                                System.out.print(">>>: ");
                                Main.temporaryString = Main.scanner.nextLine().trim();
                                if (isNumber(Main.temporaryString)) {
                                    switch (Main.temporaryString) {
                                        case "1" -> {
                                            System.out.print("RETRYING");
                                            loading("short");
                                        }
                                        case "2" -> {
                                            System.out.print("RETURNING TO ADMIN MENU");
                                            loading("short");
                                            givePermission = false;
                                        }
                                        case "3" -> {
                                            System.out.print("RETURNING TO LOGIN MENU");
                                            loading("short");
                                            resetReturningToLoginMenu();
                                            givePermission = false;
                                        }
                                        default -> {
                                            System.out.println("""
                                             ┬ ┌┐┌┬  ┬┌─┐┬  ┬┌┬┐  ┌─┐┬ ┬┌─┐┬┌─┐┌─┐  ┬
                                             │ │││└┐┌┘├─┤│  │ ││  │  ├─┤│ │││  ├┤   │
                                             ┴ ┘└┘ └┘ ┴ ┴┴─┘┴─┴┘  └─┘┴ ┴└─┘┴└─┘└─┘  o
                                        """);
                                            System.out.print("LOADING");
                                            loading("short");
                                        }
                                    }
                                }
                                else {
                                    System.out.println("""
                                      ┬ ┌┐┌┬  ┬┌─┐┬  ┬┌┬┐  ┌─┐┬ ┬┌─┐┬┌─┐┌─┐  ┬
                                      │ │││└┐┌┘├─┤│  │ ││  │  ├─┤│ │││  ├┤   │
                                      ┴ ┘└┘ └┘ ┴ ┴┴─┘┴─┴┘  └─┘┴ ┴└─┘┴└─┘└─┘  o
                                """);
                                    System.out.print("LOADING");
                                    loading("short");
                                }
                            } while (!Main.temporaryString.equals("1") && !Main.temporaryString.equals("2") && !Main.temporaryString.equals("3"));
                        }
                    }
                    else {
                        System.out.println("""
                            ┬ ┌┐┌┬  ┬┌─┐┬  ┬┌┬┐  ┌─┐┬ ┬┌─┐┬┌─┐┌─┐  ┬
                            │ │││└┐┌┘├─┤│  │ ││  │  ├─┤│ │││  ├┤   │
                            ┴ ┘└┘ └┘ ┴ ┴┴─┘┴─┴┘  └─┘┴ ┴└─┘┴└─┘└─┘  o
                        """);
                        System.out.print("LOADING");
                        loading("short");
                    }
                }
                else {
                    givePermission = false;
                }
            }
        }
    }
    protected void viewTickets() {
        try {
            String line;
            BufferedReader reader =  new BufferedReader(new FileReader("src\\" + "files\\" + "resetPinTickets\\" + "\\tickets.txt"));
            isThereATicket = true;
            System.out.println("""
                    ┬ ┬┌─┐┌─┐┬─┐┌─┐  ┬ ┬┬ ┬┌─┐  ┬ ┬┌─┐┌┐┌┌┬┐  ┌┬┐┌─┐  ┬─┐┌─┐┌─┐┌─┐┌┬┐  ┌─┐┬┌┐┌
                    │ │└─┐├┤ ├┬┘└─┐  │││├─┤│ │  │││├─┤│││ │    │ │ │  ├┬┘├┤ └─┐├┤  │   ├─┘││││
                    └─┘└─┘└─┘┴└─└─┘  └┴┘┴ ┴└─┘  └┴┘┴ ┴┘└┘ ┴    ┴ └─┘  ┴└─└─┘└─┘└─┘ ┴   ┴  ┴┘└┘
                    """);
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            reader.close();
        } catch (FileNotFoundException | NullPointerException fNFe) {
            System.out.println("""
               ┌┐┌┌─┐  ┌┬┐┬┌─┐┬┌─┌─┐┌┬┐┌─┐  ┌─┐┬  ┬┌─┐┬┬  ┌─┐┬  ┌┐ ┌─┐
               ││││ │   │ ││  ├┴┐├┤  │ └─┐  ├─┤└┐┌┘├─┤││  ├─┤│  ├┴┐├┤\s
               ┘└┘└─┘   ┴ ┴└─┘┴ ┴└─┘ ┴ └─┘  ┴ ┴ └┘ ┴ ┴┴┴─┘┴ ┴┴─┘└─┘└─┘
            """);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
