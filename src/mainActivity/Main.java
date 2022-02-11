package mainActivity;
import framework.Process;
import lib.utilities.misc.Decorations;

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
                        "  ╩  ╩╚═╚═╝╚═╝╩╚═╩ ╩╩ ╩ "   +
                        Decorations.TEXT_RESET);
                System.out.println(Decorations.TEXT_BLUE  + ": 1 : Administrator" + Decorations.TEXT_RESET);
                System.out.println(Decorations.TEXT_GREEN + ": 2 : User" + Decorations.TEXT_RESET);
                System.out.println(Decorations.TEXT_RED   + ": 3 : Exit" + Decorations.TEXT_RESET);
                System.out.print(Decorations.TEXT_YELLOW  + ">>>: " + Decorations.TEXT_RESET);
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
                        System.out.print(Decorations.TEXT_YELLOW + "EXITING THE PROGRAM" + Decorations.TEXT_RESET);
                        Process.loading("short");
                        System.out.println(
                                Decorations.TEXT_RED +
                                " ┌┬┐┬ ┬┌─┐┌┐┌┬┌─  ┬ ┬┌─┐┬ ┬  ┌─┐┌─┐┬─┐  ┬ ┬┌─┐┬┌┐┌┌─┐  ┌┬┐┬ ┬  ┌─┐┬─┐┌─┐┌─┐┬─┐┌─┐┌┬┐\n" + Decorations.TEXT_GREEN +
                                "  │ ├─┤├─┤│││├┴┐  └┬┘│ ││ │  ├┤ │ │├┬┘  │ │└─┐│││││ ┬  │││└┬┘  ├─┘├┬┘│ ││ ┬├┬┘├─┤│││\n" + Decorations.TEXT_BLUE +
                                "  ┴ ┴ ┴┴ ┴┘└┘┴ ┴   ┴ └─┘└─┘  └  └─┘┴└─  └─┘└─┘┴┘└┘└─┘  ┴ ┴ ┴   ┴  ┴└─└─┘└─┘┴└─┴ ┴┴ ┴"   +
                                Decorations.TEXT_RESET);
                    }
                    default -> {
                        System.out.println(Decorations.TEXT_RED +
                                " ┬ ┌┐┌┬  ┬┌─┐┬  ┬┌┬┐  ┌─┐┬ ┬┌─┐┬┌─┐┌─┐  ┬\n" +
                                " │ │││└┐┌┘├─┤│  │ ││  │  ├─┤│ │││  ├┤   │\n" +
                                " ┴ ┘└┘ └┘ ┴ ┴┴─┘┴─┴┘  └─┘┴ ┴└─┘┴└─┘└─┘  o" +
                                Decorations.TEXT_RESET);
                        System.out.print("RETURNING TO LOGIN MENU");
                        Process.loading("short");
                    }
                }
            }
            if (!isExit) {
                process.allAdminAndUserMenu(isAdmin);
                while (adminLoggedIn || userLoggedIn) { // if signed is a user

                    if (adminLoggedIn) {
                        account = Decorations.TEXT_BLUE + "ADMIN" + Decorations.TEXT_RESET;
                    }
                    if (userLoggedIn) {
                        account = Decorations.TEXT_GREEN + "USER" + Decorations.TEXT_RESET;
                    }
                    System.out.println(": 1 : Show " + account + " selections");
                    System.out.println(": 2 : return to " +  account + " menu");
                    System.out.println(": 3 : return to LOGIN menu");
                    System.out.print(">>>: ");
                    temporaryString = scanner.nextLine().trim();
                    switch (temporaryString) {
                        case "1" -> {
                            System.out.print("LOADING");
                            Process.loading("short");
                            if (adminLoggedIn) {
                                process.adminSelections();
                            }
                            if (userLoggedIn) {
                                process.userSelections();
                            }
                        }
                        case "2" -> {
                            System.out.print("LOGGING OUT");
                            Process.loading("long");
                            System.out.println(Decorations.TEXT_BLUE + "SUCCESSFULLY LOGGED OUT" + Decorations.TEXT_RESET);
                            System.out.print("RETURNING TO " + account + " MENU");
                            Process.loading("short");

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
                            System.out.print("LOGGING OUT");
                            Process.loading("long");
                            System.out.println("SUCCESSFULLY LOGGED OUT");
                            System.out.print("RETURNING TO LOGIN MENU");
                            Process.loading("short");
                            process.resetReturningToLoginMenu();
                        }
                        default -> {
                            System.out.println(Decorations.TEXT_RED +
                                    " ┬ ┌┐┌┬  ┬┌─┐┬  ┬┌┬┐  ┌─┐┬ ┬┌─┐┬┌─┐┌─┐  ┬\n" +
                                    " │ │││└┐┌┘├─┤│  │ ││  │  ├─┤│ │││  ├┤   │\n" +
                                    " ┴ ┘└┘ └┘ ┴ ┴┴─┘┴─┴┘  └─┘┴ ┴└─┘┴└─┘└─┘  o" +
                                    Decorations.TEXT_RESET);
                            System.out.print("LOADING");
                            Process.loading("short");
                        }
                    }
                }
            }
        }
    }
}
