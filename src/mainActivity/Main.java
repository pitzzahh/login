package mainActivity;

import framework.Process;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    // stores every input as a temporary string
    public static String temporaryString;
    // when logging in as admin or user
    public static boolean isAdmin = false;
    // whole condition
    protected static boolean isExit = false;

    public static boolean loginCondition = true;
    // when user is logged in
    public static boolean userLoggedIn = false;
    // when admin is logged in
    public static boolean adminLoggedIn = false;
    // Scanner object, used in other class
    public static final Scanner scanner = new Scanner(System.in);
    // Process object, used in order to access methods from the Process class
    protected static final Process process = new Process();
    public static void main(String[] args) throws InterruptedException, IOException {

        while (!isExit) {
            while (loginCondition) {
                System.out.println("""
                    ╔═╗ ╦ ╔╦╗ ╔═╗╦  ╔═╗  \s
                    ╚═╗ ║ ║║║ ╠═╝║  ║╣   \s
                    ╚═╝ ╩ ╩ ╩ ╩  ╩═╝╚═╝  \s
                    ╦  ╔═╗╔═╗ ╦ ╔╗╔       \s
                    ║  ║ ║║ ╦ ║ ║║║       \s
                    ╩═╝╚═╝╚═╝ ╩ ╝╚╝       \s
                    ╔═╗╦═╗╔═╗╔═╗╦═╗╔═╗╔╦╗ \s
                    ╠═╝╠╦╝║ ║║ ╦╠╦╝╠═╣║║║ \s
                    ╩  ╩╚═╚═╝╚═╝╩╚═╩ ╩╩ ╩ \s
                """);
                System.out.println(": 1 : Administrator");
                System.out.println(": 2 : User");
                System.out.println(": 3 : Exit");
                System.out.print(">>>: ");
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
                        System.out.print("EXITING THE PROGRAM");
                        process.loading("short");
                        System.out.println("""
                            ┌┬┐┬ ┬┌─┐┌┐┌┬┌─  ┬ ┬┌─┐┬ ┬  ┌─┐┌─┐┬─┐  ┬ ┬┌─┐┬┌┐┌┌─┐  ┌┬┐┬ ┬  ┌─┐┬─┐┌─┐┌─┐┬─┐┌─┐┌┬┐
                             │ ├─┤├─┤│││├┴┐  └┬┘│ ││ │  ├┤ │ │├┬┘  │ │└─┐│││││ ┬  │││└┬┘  ├─┘├┬┘│ ││ ┬├┬┘├─┤│││
                             ┴ ┴ ┴┴ ┴┘└┘┴ ┴   ┴ └─┘└─┘  └  └─┘┴└─  └─┘└─┘┴┘└┘└─┘  ┴ ┴ ┴   ┴  ┴└─└─┘└─┘┴└─┴ ┴┴ ┴
                        """);
                    }
                    default -> {
                        System.out.println("""
                            ┬ ┌┐┌┬  ┬┌─┐┬  ┬┌┬┐  ┌─┐┬ ┬┌─┐┬┌─┐┌─┐  ┬
                            │ │││└┐┌┘├─┤│  │ ││  │  ├─┤│ │││  ├┤   │
                            ┴ ┘└┘ └┘ ┴ ┴┴─┘┴─┴┘  └─┘┴ ┴└─┘┴└─┘└─┘  o
                        """);
                        System.out.print("RETURNING TO LOGIN MENU");
                        process.loading("short");
                    }
                }
            }
            process.signIn(isAdmin);
            while (adminLoggedIn) { // if signed is a user
                System.out.println(": 1 : Show details");
                System.out.println(": 2 : return to ADMIN menu");
                System.out.println(": 3 : return to LOGIN menu");
                System.out.print(">>>: ");
                temporaryString = scanner.nextLine().trim();
                switch (temporaryString) {
                    case "1" -> {
                        System.out.print("LOADING");
                        process.loading("short");
                        process.showAdminDetails();
                    }
                    case "2" -> {
                        System.out.print("LOGGING OUT");
                        process.loading("long");
                        System.out.println("SUCCESSFULLY LOGGED OUT");
                        System.out.print("RETURNING TO ADMIN MENU");
                        process.loading("short");
                        adminLoggedIn = false;
                        loginCondition = false;
                        isAdmin = true;
                    }
                    case "3" -> {
                        System.out.print("LOGGING OUT");
                        process.loading("long");
                        System.out.println("SUCCESSFULLY LOGGED OUT");
                        System.out.print("RETURNING TO LOGIN MENU");
                        process.loading("short");
                        process.resetReturningToLoginMenu();
                    }
                    default -> {
                        System.out.println("""
                                ┬ ┌┐┌ ┬  ┬┌─┐┬  ┬ ┌┬┐  ┌─┐┬ ┬┌─┐┬┌─┐┌─┐  ┬
                                │ │││ └┐┌┘├─┤│  │  ││  │  ├─┤│ │││  ├┤   │
                                ┴ ┘└┘  └┘ ┴ ┴┴─┘┴ ─┴┘  └─┘┴ ┴└─┘┴└─┘└─┘  o
                            """);
                        System.out.print("LOADING");
                        process.loading("short");
                    }
                }
            }

            while (userLoggedIn) { // if signed is a user
                System.out.println(": 1 : Show details");
                System.out.println(": 2 : return to user menu");
                System.out.println(": 3 : return to login menu");
                System.out.print(">>>: ");
                temporaryString = scanner.nextLine().trim();
                switch (temporaryString) {
                    case "1" -> {
                        System.out.print("LOADING");
                        process.loading("short");
                        process.showUserDetails();
                    }
                    case "2" -> {
                        userLoggedIn = false;
                        loginCondition = false;
                        System.out.print("LOGGING OUT");
                        process.loading("long");
                        System.out.println("SUCCESSFULLY LOGGED OUT");
                        System.out.print("RETURNING TO USER MENU");
                        process.loading("short");

                    }
                    case "3" -> {
                        System.out.print("LOGGING OUT");
                        process.loading("long");
                        System.out.println("SUCCESSFULLY LOGGED OUT");
                        System.out.print("RETURNING TO LOGIN MENU");
                        process.loading("short");
                        process.resetReturningToLoginMenu();
                    }
                    default -> {
                        System.out.println("""
                                ┬ ┌┐┌┬  ┬┌─┐┬  ┬┌┬┐  ┌─┐┬ ┬┌─┐┬┌─┐┌─┐  ┬
                                │ │││└┐┌┘├─┤│  │ ││  │  ├─┤│ │││  ├┤   │
                                ┴ ┘└┘ └┘ ┴ ┴┴─┘┴─┴┘  └─┘┴ ┴└─┘┴└─┘└─┘  o
                            """);
                        System.out.print("LOADING");
                        process.loading("short");
                    }
                }
            }
        }
    }
}
