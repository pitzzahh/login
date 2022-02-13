package framework;

import org.apache.commons.io.FilenameUtils;
import lib.utilities.misc.InputChecker;
import lib.utilities.misc.Decorations;
import lib.utilities.misc.Delay;
import lib.utilities.FileUtil;
import jdk.jfr.Description;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import mainActivity.Main;
import java.util.List;
import java.io.*;

public class Ticketing extends Process {
    /**
     * Creates a ticket file named after the username.
     * <p>Checks if the file already exist or not, if not
     * it creates a new file and write false to that text file.
     * @return true if the file does not exist and successfully created a new ticket file
     * @throws InterruptedException if the thread is interrupted while writing to the file.
     * @throws IOException if the file was not present.
     */
    @Description(value =  "Does not work properly, if the user already submitted a ticket, it overrides the current file and rewrites it and takes more time.")
    public boolean submitResetTicket(String user) throws InterruptedException, IOException {
        File ticketFile = new File("C:\\Users\\Public\\files\\resetPinTickets\\" + user + ".txt");
        boolean newTicket = !ticketFile.exists(); // false
        if (newTicket) { // not here
            newTicket = ticketFile.createNewFile();
        }
        if (newTicket) {
            FileUtil.writeToATextFile("false", ticketFile); // writes false to the ticket file
            System.out.print(Decorations.TEXT_BLUE + "SUBMITTING");
            Delay.dotLoading("long");
            System.out.println(Decorations.TEXT_GREEN + "SUCCESSFULLY SUBMITTED RESET TICKET");
            return true;
        }
        return false;
    }

    /**
     * Admin method to give user permission to change their pin.
     * @throws Exception if something went wrong.
     */
    public void editEligibility() throws Exception {
        boolean givePermission = true;
        while (givePermission) {
            if (viewTickets()) {
                System.out.println(Decorations.TEXT_BLUE + "ENTER THE USER YOU WANT TO GIVE PERMISSION TO CHANGE PIN");
                System.out.print(Decorations.TEXT_YELLOW  + ">>>: ");
                Main.temporaryString = Main.scanner.nextLine().trim();
                setUserName(Main.temporaryString);
                if (Files.exists(Path.of("C:\\Users\\Public\\files\\accounts\\user\\" + getUserName() + "'s Folder\\"))) {
                    try {
                        File ticket = new File("C:\\Users\\Public\\files\\resetPinTickets\\"+ getUserName() + ".txt");
                        Scanner ticketScanner = new Scanner(ticket);
                        String checkIfFalse = ticketScanner.nextLine();
                        if (ticket.exists() && checkIfFalse.equals("false")) {
                            System.out.print(Decorations.TEXT_YELLOW + "GIVING PERMISSION");
                            Delay.dotLoading("long");
                            givePermission = false;
                            FileUtil.writeToATextFile("true", ticket);
                            System.out.println(Decorations.TEXT_GREEN + "PERMISSION GRANTED (!)");
                            Decorations.show.pressEnterToContinue();
                            Main.scanner.nextLine();
                            adminSelections();
                        }
                        else {
                            if (checkIfFalse.equals("true")) {
                                System.out.println(Decorations.TEXT_RED + "ALREADY GRANTED PERMISSION (!)");
                            }
                            else {
                                System.out.println(Decorations.TEXT_RED + "USER EXISTS BUT NO TICKET SUBMITTED (!)");
                            }
                            System.out.print(Decorations.TEXT_GREEN + "LOADING");
                            Delay.dotLoading("short");
                            System.out.print(Decorations.TEXT_PURPLE  + "RETURNING TO " + Main.account + Decorations.TEXT_YELLOW + " MENU");
                            Delay.dotLoading("short");
                            givePermission = false;
                        }
                    } catch (FileNotFoundException fileNotFoundException) {
                        fileNotFoundException.printStackTrace();
                        Decorations.show.error();
                        System.out.print(Decorations.TEXT_PURPLE  + "RETURNING TO " + Main.account + Decorations.TEXT_YELLOW + " MENU");
                        Delay.dotLoading("short");
                        adminSelections();
                    }
                }
                else {
                    do {
                        System.out.println(
                                Decorations.TEXT_RED +
                                        " ┬ ┬┬─┐┌─┐┌┐┌┌─┐  ┬ ┬┌─┐┌─┐┬─┐┌┐┌┌─┐┌┬┐┌─┐  ┬\n" +
                                        " │││├┬┘│ │││││ ┬  │ │└─┐├┤ ├┬┘│││├─┤│││├┤   │\n" +
                                        " └┴┘┴└─└─┘┘└┘└─┘  └─┘└─┘└─┘┴└─┘└┘┴ ┴┴ ┴└─┘  o");
                        System.out.println(Decorations.TEXT_YELLOW + ": 1 : Retry");
                        System.out.println(Decorations.TEXT_PURPLE + ": 3 : Return to " + Main.account + Decorations.TEXT_BLUE + " MENU");
                        System.out.println(Decorations.TEXT_GREEN  + ": 4 : return to LOGIN menu");
                        System.out.print(Decorations.TEXT_YELLOW  + ">>>: ");
                        Main.temporaryString = Main.scanner.nextLine().trim();
                        if (InputChecker.isInteger(Main.temporaryString)) {
                            switch (Main.temporaryString) {
                                case "1" -> {
                                    System.out.print(Decorations.TEXT_YELLOW + "RETRYING");
                                    Delay.dotLoading("short");
                                }
                                case "2" -> {
                                    System.out.print(Decorations.TEXT_PURPLE  + "RETURNING TO " + Main.account + Decorations.TEXT_YELLOW + " MENU");
                                    Delay.dotLoading("short");
                                    givePermission = false;
                                    adminSelections();
                                }
                                case "3" -> {
                                    Main.adminLoggedIn = false;
                                    Main.loginCondition = false;
                                    Main.isAdmin = true;
                                    givePermission = false;
                                    System.out.print(Decorations.TEXT_YELLOW + "LOGGING OUT");
                                    Delay.dotLoading("long");
                                    System.out.println(Decorations.TEXT_BLUE + "SUCCESSFULLY LOGGED OUT");
                                    System.out.print(Decorations.TEXT_PURPLE  + "RETURNING TO " + Main.account + Decorations.TEXT_YELLOW + " MENU");
                                    Delay.dotLoading("short");
                                }
                                default -> {
                                    Decorations.show.invalidChoice();
                                    Decorations.printLoading();
                                    Delay.dotLoading("short");
                                }
                            }
                        }
                        else {
                            Decorations.show.invalidChoice();
                            Decorations.printLoading();
                            Delay.dotLoading("short");
                        }
                    } while (!Main.temporaryString.equals("1") && !Main.temporaryString.equals("2") && !Main.temporaryString.equals("3"));
                }
            }
            else {
                System.out.println(
                        Decorations.TEXT_RED +
                        " ┌┐┌┌─┐  ┌┬┐┬┌─┐┬┌─┌─┐┌┬┐┌─┐  ┌─┐┬  ┬┌─┐┬┬  ┌─┐┬  ┌┐ ┌─┐\n" +
                        " ││││ │   │ ││  ├┴┐├┤  │ └─┐  ├─┤└┐┌┘├─┤││  ├─┤│  ├┴┐├┤ \n" +
                        " ┘└┘└─┘   ┴ ┴└─┘┴ ┴└─┘ ┴ └─┘  ┴ ┴ └┘ ┴ ┴┴┴─┘┴ ┴┴─┘└─┘└─┘");
                System.out.print(Decorations.TEXT_PURPLE  + "RETURNING TO " + Main.account + Decorations.TEXT_YELLOW + " MENU");
                Delay.dotLoading("short");
                Main.scanner.reset();
                Main.temporaryString = "";
                adminSelections();
                givePermission = false;
            }
        }
    }

    /**
     * Views all the user tickets.
     * @return {@code true} if there is a ticket available.
     * @throws Exception if something went wrong.
     */
    protected boolean viewTickets() throws Exception {
        List<String> tickets = viewUserTickets();
        try {
            if (tickets.size() != 0) {
                try {
                    for (int i = 0; i < tickets.size(); i++) {
                        File ticket = new File("C:\\Users\\Public\\files\\resetPinTickets\\" + tickets.get(i) + ".txt");
                        Scanner ticketScanner = new Scanner(ticket);
                        String checkIfFalse = ticketScanner.nextLine();
                        if (checkIfFalse.equals("false")) {
                            System.out.println(
                                    Decorations.TEXT_RED +
                                    " ┬  ┬┌─┐┌┬┐  ┌─┐┌─┐  ┬ ┬┌─┐┌─┐┬─┐┌─┐\n" + Decorations.TEXT_GREEN +
                                    " │  │└─┐ │   │ │├┤   │ │└─┐├┤ ├┬┘└─┐\n" + Decorations.TEXT_BLUE  +
                                    " ┴─┘┴└─┘ ┴   └─┘└    └─┘└─┘└─┘┴└─└─┘");
                            if (ticket.exists()) {
                                System.out.printf("USER     [%d]: %s\n", ( i + 1 ), FilenameUtils.getBaseName(tickets.get(i)));
                                return true;
                            }
                        }
                        else {
                            return false;
                        }
                    }
                } catch (FileNotFoundException ignored) {
                    return false;
                }
            }
            else {
                System.out.println(
                    Decorations.TEXT_RED +
                    " ┌┐┌┌─┐  ┌┬┐┬┌─┐┬┌─┌─┐┌┬┐┌─┐  ┌─┐┬  ┬┌─┐┬┬  ┌─┐┬  ┌┐ ┌─┐\n" +
                    " ││││ │   │ ││  ├┴┐├┤  │ └─┐  ├─┤└┐┌┘├─┤││  ├─┤│  ├┴┐├┤ \n" +
                    " ┘└┘└─┘   ┴ ┴└─┘┴ ┴└─┘ ┴ └─┘  ┴ ┴ └┘ ┴ ┴┴┴─┘┴ ┴┴─┘└─┘└─┘"   +
                    Decorations.TEXT_RESET);
                System.out.print(Decorations.TEXT_PURPLE  + "RETURNING TO " + Main.account + Decorations.TEXT_YELLOW + " MENU");
                Delay.dotLoading("short");
                Main.scanner.reset();
                Main.temporaryString = "";
                adminSelections();
            }
        } catch (NullPointerException fNFe) {
            fNFe.printStackTrace();
            Decorations.show.error();
            System.out.print(Decorations.TEXT_PURPLE  + "RETURNING TO " + Main.account + Decorations.TEXT_YELLOW + " MENU");
            Delay.dotLoading("short");
            adminSelections();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println();
        return false;
    }
}
