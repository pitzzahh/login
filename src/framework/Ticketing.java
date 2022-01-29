package framework;

import mainActivity.Main;
import org.apache.commons.io.FilenameUtils;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class Ticketing extends Process {
    public boolean submitResetTicket(String user) throws InterruptedException, IOException {
        File userAccountFolder = new File ("src\\files\\resetPinTickets\\usersWhoWantsToResetPin\\"+ user + ".txt");
        boolean success = userAccountFolder.createNewFile();
        if (success) {
            System.out.print("SUBMITTING");
            loading("long");
            System.out.println("SUCCESSFULLY SUBMITTED RESET TICKET");
            return true;
        }
        return false;
    }
    public void editEligibility(boolean isAdmin) throws InterruptedException, IOException {
        boolean givePermission = true;
        if (isAdmin) {
            while (givePermission) {
                if (viewTickets()) {
                    System.out.println("\nENTER THE USER YOU WANT TO GIVE PERMISSION TO CHANGE PIN");
                    System.out.print(">>>: ");
                    Main.temporaryString = Main.scanner.nextLine().trim();
                    if (!isNumber(Main.temporaryString)) {
                        setUserName(Main.temporaryString);
                        if (Files.exists(Path.of("src\\files\\accounts\\user\\" + getUserName() + "'s Folder\\username.txt"))) {
                            BufferedWriter writer = new BufferedWriter(new FileWriter("src\\files\\resetPinTickets\\tickets.txt"));
                            insertTicket.put(getUserName(), true);
                            for (Map.Entry<String, Boolean> entry : insertTicket.entrySet()) {
                                writer.write("[ IS TRYING TO RESET PIN: " + entry.getValue() + " ]" + " USER: " + entry.getKey()) ;
                                writer.newLine();
                            }
                            writer.close();
                            System.out.print("GIVING PERMISSION");
                            loading("long");
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
    protected boolean viewTickets() throws InterruptedException, IOException {
        List<String> tickets = viewUserTickets();
        try {
            if (tickets.size() != 0) {
                System.out.println("""
                    ┬  ┬┌─┐┌┬┐  ┌─┐┌─┐  ┬ ┬┌─┐┌─┐┬─┐┌─┐
                    │  │└─┐ │   │ │├┤   │ │└─┐├┤ ├┬┘└─┐
                    ┴─┘┴└─┘ ┴   └─┘└    └─┘└─┘└─┘┴└─└─┘
                """);
                for (int i = 0; i < tickets.size(); i++) {
                    System.out.printf("USER     [%d]: %s\n", ( i + 1 ), FilenameUtils.getBaseName(tickets.get(i)));
                }
                return true;
            }
            else {
                System.out.println("""
                    ┌┐┌┌─┐  ┌┬┐┬┌─┐┬┌─┌─┐┌┬┐┌─┐  ┌─┐┬  ┬┌─┐┬┬  ┌─┐┬  ┌┐ ┌─┐
                    ││││ │   │ ││  ├┴┐├┤  │ └─┐  ├─┤└┐┌┘├─┤││  ├─┤│  ├┴┐├┤\s
                    ┘└┘└─┘   ┴ ┴└─┘┴ ┴└─┘ ┴ └─┘  ┴ ┴ └┘ ┴ ┴┴┴─┘┴ ┴┴─┘└─┘└─┘
                """);
                System.out.print("RETURNING TO ADMIN SELECTION");
                loading("short");
                showAdminDetails();
            }
        } catch (NullPointerException fNFe) {
            System.out.println("""
                ╔═╗╦═╗╦═╗╔═╗╦═╗
                ║╣ ╠╦╝╠╦╝║ ║╠╦╝
                ╚═╝╩╚═╩╚═╚═╝╩╚═
            """);
            fNFe.printStackTrace();
            System.out.print("RETURNING TO ADMIN SELECTION");
            loading("short");
            showAdminDetails();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
