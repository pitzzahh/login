package mainActivity;

import lib.utilities.misc.Decorations;
import lib.utilities.misc.Delay;
import framework.Process;
import java.util.Scanner;

public class Main {
    public static String account = "";
    // stores every input as a temporary string
    public static String temporaryString;
    // when logging in as admin or user
    public static boolean isAdmin = false;
    // whole condition
    protected static boolean isExit = false;
    // condition of main login menu
    public static boolean loginCondition = true;
    // when user is logged in
    public static boolean userLoggedIn = false;
    // when admin is logged in
    public static boolean adminLoggedIn = false;
    // Scanner object, used in other class
    public static final Scanner scanner = new Scanner(System.in);
    // Process object, used in order to access methods from the Process class
    protected static final Process process = new Process();

    public static void main(String[] args) throws Exception {

        while (!isExit) {
            while (loginCondition) {
                System.out.println(Decorations.TEXT_RED +
                        "  ╔═╗ ╦ ╔╦╗ ╔═╗╦  ╔═╗   \n" +
                        "  ╚═╗ ║ ║║║ ╠═╝║  ║╣    \n" +
                        "  ╚═╝ ╩ ╩ ╩ ╩  ╩═╝╚═╝   \n" + Decorations.TEXT_GREEN +
                        "  ╦  ╔═╗╔═╗ ╦ ╔╗╔       \n" +
                        "  ║  ║ ║║ ╦ ║ ║║║       \n" +
                        "  ╩═╝╚═╝╚═╝ ╩ ╝╚╝       \n" + Decorations.TEXT_BLUE +
                        "  ╔═╗╦═╗╔═╗╔═╗╦═╗╔═╗╔╦╗ \n" +
                        "  ╠═╝╠╦╝║ ║║ ╦╠╦╝╠═╣║║║ \n" +
                        "  ╩  ╩╚═╚═╝╚═╝╩╚═╩ ╩╩ ╩ ");
                System.out.println(Decorations.TEXT_BLUE  + ": 1 : Administrator");
                System.out.println(Decorations.TEXT_GREEN + ": 2 : User");
                System.out.println(Decorations.TEXT_RED   + ": 3 : Exit");
                System.out.print(Decorations.TEXT_YELLOW  + ">>>: ");
                temporaryString = scanner.nextLine().trim();
                switch (temporaryString) {
                    case "1" -> {
                        isAdmin = true;
                        loginCondition = false;
                    }
                    case "2" -> {
                        isAdmin = false;
                        loginCondition = false;
                    }
                    case "3" -> {
                        isExit = true;
                        loginCondition = false;
                        isAdmin = false;
                        System.out.print(Decorations.TEXT_YELLOW + "EXITING THE PROGRAM");
                        Delay.dotLoading("short");
                        System.out.println(
                                Decorations.TEXT_RED +
                                " ┌┬┐┬ ┬┌─┐┌┐┌┬┌─  ┬ ┬┌─┐┬ ┬  ┌─┐┌─┐┬─┐  ┬ ┬┌─┐┬┌┐┌┌─┐  ┌┬┐┬ ┬  ┌─┐┬─┐┌─┐┌─┐┬─┐┌─┐┌┬┐\n" + Decorations.TEXT_GREEN +
                                "  │ ├─┤├─┤│││├┴┐  └┬┘│ ││ │  ├┤ │ │├┬┘  │ │└─┐│││││ ┬  │││└┬┘  ├─┘├┬┘│ ││ ┬├┬┘├─┤│││\n" + Decorations.TEXT_BLUE +
                                "  ┴ ┴ ┴┴ ┴┘└┘┴ ┴   ┴ └─┘└─┘  └  └─┘┴└─  └─┘└─┘┴┘└┘└─┘  ┴ ┴ ┴   ┴  ┴└─└─┘└─┘┴└─┴ ┴┴ ┴");
                    }
                    default -> {
                        Decorations.show.invalidChoice();
                        System.out.print(Decorations.TEXT_GREEN  + "RETURNING TO LOGIN MENU");
                        Delay.dotLoading("short");
                    }
                }
            }
            Process.init();
            if (!isExit) {
                process.allAdminAndUserMenu(isAdmin);
                while (adminLoggedIn || userLoggedIn) {
                    System.out.println(Decorations.TEXT_YELLOW + ": 1 : Show " + account + Decorations.TEXT_YELLOW + " selections");
                    System.out.println(Decorations.TEXT_YELLOW + ": 2 : return to " +  account + Decorations.TEXT_YELLOW + " menu");
                    System.out.println(Decorations.TEXT_YELLOW + ": 3 : return to " + Decorations.TEXT_PURPLE + " LOGIN " + Decorations.TEXT_YELLOW + " menu");
                    System.out.print(Decorations.TEXT_YELLOW  + ">>>: ");
                    temporaryString = scanner.nextLine().trim();
                    switch (temporaryString) {
                        case "1" -> {
                            Decorations.printLoading();
                            Delay.dotLoading("short");
                            if (adminLoggedIn) {
                                process.adminSelections();
                            }
                            if (userLoggedIn) {
                                process.userSelections();
                            }
                        }
                        case "2" -> {
                            System.out.print(Decorations.TEXT_YELLOW + "LOGGING OUT");
                            Delay.dotLoading("long");
                            System.out.println(Decorations.TEXT_BLUE + "SUCCESSFULLY LOGGED OUT");
                            System.out.print(Decorations.TEXT_PURPLE  + "RETURNING TO " + Main.account + Decorations.TEXT_YELLOW + " MENU");
                            Delay.dotLoading("short");
                            if (adminLoggedIn) {
                                adminLoggedIn = false;
                                loginCondition = false;
                                isAdmin = true;
                            }
                            if (userLoggedIn) {
                                userLoggedIn = false;
                                loginCondition = false;
                                isAdmin = false;
                            }
                        }
                        case "3" -> {
                            System.out.print(Decorations.TEXT_YELLOW + "LOGGING OUT");
                            Delay.dotLoading("long");
                            System.out.println(Decorations.TEXT_BLUE + "SUCCESSFULLY LOGGED OUT");
                            System.out.print(Decorations.TEXT_GREEN  + "RETURNING TO LOGIN MENU");
                            Delay.dotLoading("short");
                            process.resetReturningToLoginMenu();
                        }
                        default -> {
                            Decorations.show.invalidChoice();
                            Delay.dotLoading("short");
                        }
                    }
                }
            }
        }
    }
}
