package framework;

import mainActivity.Main;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Scanner;

public class Ticketing extends Process {
    public void submitResetTicket() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("src\\" + "files\\" + "resetPinTickets\\" + "\\tickets.txt"));
        for (Map.Entry<String, Boolean> entry : insertTicket.entrySet()) {
            writer.write("[ IS TRYING TO RESET PIN: " + entry.getValue() + " ]" + " USER: " + entry.getKey()) ;
            writer.newLine();
        }
        writer.close();
    }
    public void editEligibility(boolean isAdmin) throws InterruptedException, IOException {
        boolean givePermission = true;
        if (isAdmin) {
            while (givePermission) {
                if (viewTickets()) {
                    Process process = new Process();
                    System.out.println("ENTER THE USER YOU WANT TO GIVE PERMISSION TO CHANGE PIN");
                    System.out.print(">>>: ");
                    Main.temporaryString = Main.scanner.nextLine().trim();
                    if (!isNumber(Main.temporaryString)) {
                        setUserName(Main.temporaryString);
                        if (Files.exists(Path.of("src\\files\\accounts\\user\\" + getUserName() + "\\username.txt"))) {
                            isEligibleToChangePin.put(getUserName(),true);
                            System.out.print("GIVING PERMISSION");
                            loading("long");
                            insertTicket.remove(getUserName());
                            submitResetTicket();
                            givePermission = false;
                            System.out.println("PERMISSION GRANTED (!)");
                            System.out.println("=========================");
                            System.out.println("|PRESS ENTER TO CONTINUE|");
                            System.out.println("=========================");
                            Main.scanner.nextLine();
                            showAdminDetails();
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
                                System.out.println(": 3 : Return to ADMIN MENU");
                                System.out.print(">>>: ");
                                Main.temporaryString = Main.scanner.nextLine().trim();
                                if (isNumber(Main.temporaryString)) {
                                    switch (Main.temporaryString) {
                                        case "1" -> {
                                            System.out.print("RETRYING");
                                            loading("short");
                                        }
                                        case "2" -> {
                                            System.out.print("RETURNING TO ADMIN SELECTION");
                                            loading("short");
                                            givePermission = false;
                                            showAdminDetails();
                                        }
                                        case "3" -> {
                                            Main.adminLoggedIn = false;
                                            Main.loginCondition = false;
                                            Main.isAdmin = true;
                                            givePermission = false;
                                            System.out.print("LOGGING OUT");
                                            loading("long");
                                            System.out.println("SUCCESSFULLY LOGGED OUT");
                                            System.out.print("RETURNING TO ADMIN MENU");
                                            loading("short");
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
    protected boolean viewTickets() throws InterruptedException {
        try {
            BufferedReader reader =  new BufferedReader(new FileReader("src\\files\\resetPinTickets\\tickets.txt"));
            if (!reader.readLine().isEmpty()) {
                File file = new File("src\\files\\resetPinTickets\\tickets.txt");
                Scanner sc = new Scanner(file);
                System.out.println("""
                    ┬ ┬┌─┐┌─┐┬─┐┌─┐  ┬ ┬┬ ┬┌─┐  ┬ ┬┌─┐┌┐┌┌┬┐  ┌┬┐┌─┐  ┬─┐┌─┐┌─┐┌─┐┌┬┐  ┌─┐┬┌┐┌
                    │ │└─┐├┤ ├┬┘└─┐  │││├─┤│ │  │││├─┤│││ │    │ │ │  ├┬┘├┤ └─┐├┤  │   ├─┘││││
                    └─┘└─┘└─┘┴└─└─┘  └┴┘┴ ┴└─┘  └┴┘┴ ┴┘└┘ ┴    ┴ └─┘  ┴└─└─┘└─┘└─┘ ┴   ┴  ┴┘└┘
                """);
                while (sc.hasNextLine()) {
                    System.out.println(sc.nextLine());
                }
                reader.close();
                return true;
            }
            else {
                System.out.println("""
                    ┌┐┌┌─┐  ┌┬┐┬┌─┐┬┌─┌─┐┌┬┐┌─┐  ┌─┐┬  ┬┌─┐┬┬  ┌─┐┬  ┌┐ ┌─┐
                    ││││ │   │ ││  ├┴┐├┤  │ └─┐  ├─┤└┐┌┘├─┤││  ├─┤│  ├┴┐├┤\s
                    ┘└┘└─┘   ┴ ┴└─┘┴ ┴└─┘ ┴ └─┘  ┴ ┴ └┘ ┴ ┴┴┴─┘┴ ┴┴─┘└─┘└─┘
                """);
                System.out.print("RETURNING TO ADMIN MENU");
                loading("short");
            }
        } catch (FileNotFoundException | NullPointerException fNFe) {
            System.out.println("""
               ┌┐┌┌─┐  ┌┬┐┬┌─┐┬┌─┌─┐┌┬┐┌─┐  ┌─┐┬  ┬┌─┐┬┬  ┌─┐┬  ┌┐ ┌─┐
               ││││ │   │ ││  ├┴┐├┤  │ └─┐  ├─┤└┐┌┘├─┤││  ├─┤│  ├┴┐├┤\s
               ┘└┘└─┘   ┴ ┴└─┘┴ ┴└─┘ ┴ └─┘  ┴ ┴ └┘ ┴ ┴┴┴─┘┴ ┴┴─┘└─┘└─┘
            """);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
