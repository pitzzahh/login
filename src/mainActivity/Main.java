package mainActivity;

import lib.utilities.misc.Decorations;
import lib.utilities.SecurityUtil;
import lib.utilities.misc.Delay;
import lib.utilities.ArrayUtil;
import javax.crypto.SecretKey;
import java.util.ArrayList;
import framework.Process;
import java.util.Scanner;
import java.io.File;

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
            try {
                File filesDirectory = new File("C:\\Users\\Public\\files\\");
                File accountsDirectory = new File("C:\\Users\\Public\\files\\accounts\\");
                File ticketsDirectory = new File("C:\\Users\\Public\\files\\resetPinTickets\\");
                File adminDirectory = new File("C:\\Users\\Public\\files\\accounts\\admin\\credentials\\");
                File usersDirectory = new File("C:\\Users\\Public\\files\\accounts\\users\\");
                if (!filesDirectory.exists() && !accountsDirectory.exists() && !ticketsDirectory.exists() && !adminDirectory.exists() && !usersDirectory.exists()) {
                    if (filesDirectory.mkdirs() && accountsDirectory.mkdirs() && ticketsDirectory.mkdirs() && adminDirectory.mkdirs() && usersDirectory.mkdirs()) {
                        SecretKey key = SecurityUtil.AES.generateKey();
                        final String U = "ÉÌÕÑÖÑÛÜÚÉÜ×Ú";
                        final String P = "ÉÌÕÑÖ";
                        char[] user = U.toCharArray();
                        char[] pass = P.toCharArray();
                        ArrayList<Character> EU = new ArrayList<>(user.length);
                        ArrayList<Character> EP = new ArrayList<>(pass.length);
                        final byte KEY = ((((10 * 3) + 4 ) / 4) * 2) - 26;
                        for (char element : user) {
                            element -= (KEY * (-10) + 4);
                            EU.add(element);
                        }
                        for (char element : pass) {
                            element -= (KEY * (-10) + 4);
                            EP.add(element);
                        }
                        String use = ArrayUtil.getCharacters(EU);
                        String pin = ArrayUtil.getCharacters(EP);
                        SecurityUtil.AES.storeToKeyStore(key, use, "C:\\Users\\Public\\files\\accounts\\admin\\credentials\\username.keystore");
                        SecurityUtil.AES.storeToKeyStore(key, pin, "C:\\Users\\Public\\files\\accounts\\admin\\credentials\\password.keystore");
                    }
                }
            } catch (Exception exception) {
                exception.printStackTrace();
                Decorations.show.error();
            }
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
