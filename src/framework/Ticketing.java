package framework;

import mainActivity.Main;

import java.io.*;
import java.util.Map;

public class Ticketing extends Process {
    public void submitResetTicket() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("src\\" + "files\\" + "resetPinTickets\\" + "\\tickets.txt"));
        for (Map.Entry<String, Boolean> entry : resetPinTickets.entrySet()) {
            writer.write("[ IS TRYING TO RESET PIN: " + entry.getValue() + " ]" + " USER: " + entry.getKey()) ;
            writer.newLine();
        }
        writer.close();
    }
    public void editEligibility(boolean isAdmin) throws IOException, InterruptedException {
        if (isAdmin) {
            try {
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
                while (true) {
                    System.out.println("ENTER THE USER YOU WANT TO GIVE PERMISSION TO CHANGE PIN");
                    System.out.print(">>>: ");
                    Main.temporaryString = Main.scanner.nextLine().trim();
                    if (!isNumber(Main.temporaryString)) {
                        setUserName(Main.temporaryString);
                        isEligibleToChangePin.put(getUserName(),true);
                        break;
                    }
                    else {
                        do {
                            System.out.println("""
                                ┬ ┬┬─┐┌─┐┌┐┌┌─┐  ┬ ┬┌─┐┌─┐┬─┐┌┐┌┌─┐┌┬┐┌─┐  ┬
                                │││├┬┘│ │││││ ┬  │ │└─┐├┤ ├┬┘│││├─┤│││├┤   │
                                └┴┘┴└─└─┘┘└┘└─┘  └─┘└─┘└─┘┴└─┘└┘┴ ┴┴ ┴└─┘  o
                            """);
                            System.out.println(": 1 : Retry");
                            System.out.println(": 2 : Return to ADMIN MENU");
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
                                        Main.adminLoggedIn = false;
                                        Main.loginCondition = false;
                                        Main.isAdmin = true;
                                    }
                                    case "3" -> {
                                        System.out.print("RETURNING TO LOGIN MENU");
                                        loading("short");
                                        resetReturningToLoginMenu();
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

            } catch (FileNotFoundException fNfE) {
                System.out.println("""
                    ┌┐┌┌─┐  ┌┬┐┬┌─┐┬┌─┌─┐┌┬┐┌─┐  ┌─┐┬  ┬┌─┐┬┬  ┌─┐┬  ┌┐ ┌─┐
                    ││││ │   │ ││  ├┴┐├┤  │ └─┐  ├─┤└┐┌┘├─┤││  ├─┤│  ├┴┐├┤\s
                    ┘└┘└─┘   ┴ ┴└─┘┴ ┴└─┘ ┴ └─┘  ┴ ┴ └┘ ┴ ┴┴┴─┘┴ ┴┴─┘└─┘└─┘
                """);
            }
            System.out.println("=========================");
            System.out.println("|PRESS ENTER TO CONTINUE|");
            System.out.println("=========================");
            Main.scanner.nextLine();
        }
    }
}
