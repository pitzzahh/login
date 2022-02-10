package framework;

import lib.computing.algorithms.recursion.factorial.Factorial;
import lib.computing.algorithms.recursion.fibonacci.Fibonacci;
import lib.computing.algorithms.recursion.sumOfAllNumbers.SumOfAllNumbers;
import lib.computing.algorithms.sorting.quickSort.QuickSort;
import lib.utilities.misc.InputChecker;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.FileUtils;
import java.util.concurrent.TimeUnit;
import lib.utilities.misc.PinGenerator;
import lib.utilities.SecurityUtil;
import lib.utilities.FileUtil;
import mainActivity.Main;


import java.util.*;
import java.io.*;

public class Process {
    private String userName;
    private String pin;
    public static boolean isResettingPin = false;
    static final Hashtable<String, Boolean> insertTicket = new Hashtable<>();

    private static boolean computeFactorial;
    private static boolean computeFibonacci;
    private static boolean computeSumOfAllNumbers = true;
    private static boolean sort = true;

    public String getUserName() {
        return userName;
    }
    public String getPin() {
        return pin;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public void setPin(String pin) {
        this.pin = pin;
    }
    public void allAdminAndUserMenu(boolean isAdmin) throws IOException, InterruptedException {
        if (isAdmin) {
            System.out.println("""
                ┌─┐┌┬┐ ┌┬┐ ┬ ┌┐┌  ┌┬┐┌─┐┌┐┌ ┬ ┬
                ├─┤ ││ │││ │ │││  │││├┤ │││ │ │
                ┴ ┴ ┴┘ ┴ ┴ ┴ ┘└┘  ┴ ┴└─┘┘└┘ └─┘
            """);
            System.out.println(": 1 : Sign in");
            System.out.println(": 2 : return to login menu");
        }
        else {
            System.out.println("""
                ┬ ┬┌─┐┌─┐┬─┐  ┌┬┐┌─┐┌┐┌ ┬ ┬
                │ │└─┐├┤ ├┬┘  │││├┤ │││ │ │
                └─┘└─┘└─┘┴└─  ┴ ┴└─┘┘└┘ └─┘
            """);
            System.out.println(": 1 : Sign in");
            System.out.println(": 2 : create account");
            System.out.println(": 3 : return to login menu");
        }
        System.out.print(">>>: ");
        Main.temporaryString = Main.scanner.nextLine().trim();
        switch (Main.temporaryString) {
            case "1" -> {
                boolean insertCredentials = true;
                while (insertCredentials) {
                    File checkUserName;
                    File checkPassword;
                    System.out.print("ENTER USERNAME: ");
                    Main.temporaryString = Main.scanner.nextLine().trim();
                    setUserName(Main.temporaryString);
                    System.out.print("ENTER PASSWORD: ");
                    Main.temporaryString = Main.scanner.nextLine().trim();
                    setPin(Main.temporaryString);
                    if (isAdmin) {
                        checkUserName = new File ("src\\files\\accounts\\admin\\credentials\\username.txt");
                        checkPassword = new File ("src\\files\\accounts\\admin\\credentials\\password.txt");
                    }
                    else {
                        checkUserName = new File ("src\\files\\accounts\\user\\" + getUserName() + "'s Folder\\credentials\\username.txt");
                        checkPassword = new File ("src\\files\\accounts\\user\\" + getUserName() + "'s Folder\\credentials\\pin.txt");
                    }
                    System.out.print("LOGGING IN");
                    loading("long");
                    if (SecurityUtil.checkCredentials(checkUserName, checkPassword, getUserName(), getPin(), isAdmin)) {
                        try {
                            System.out.println("""
                                 ┬  ┌─┐┌─┐┌─┐┌─┐┌┬┐  ┬ ┌┐┌  ┬
                                 │  │ ││ ┬│ ┬├┤  ││  │ │││  │
                                 ┴─┘└─┘└─┘└─┘└─┘─┴┘  ┴ ┘└┘  o
                            """);
                            if (isAdmin) {
                                Main.adminLoggedIn = true;
                            }
                            else {
                                Main.userLoggedIn = true;
                                File updateAttempt = new File ("src\\files\\accounts\\user\\" + getUserName() + "'s Folder\\loginAttempts\\remainingAttempts.txt");
                                FileUtil.writeToATextFile("6", updateAttempt); // reset to 6 login attempts if the user finally logged in
                            }
                            insertCredentials = false;
                        } catch (FileNotFoundException ignored) {
                            System.out.println("HERE");
                        }
                    }
                    else {
                        do {
                            System.out.println("""
                                    ┬ ┬┬─┐┌─┐┌┐┌┌─┐  ┬ ┬┌─┐┌─┐┬─┐┌┐┌┌─┐┌┬┐┌─┐
                                    │││├┬┘│ │││││ ┬  │ │└─┐├┤ ├┬┘│││├─┤│││├┤\s
                                    └┴┘┴└─└─┘┘└┘└─┘  └─┘└─┘└─┘┴└─┘└┘┴ ┴┴ ┴└─┘
                                    ┌─┐┬─┐                                  \s
                                    │ │├┬┘                                  \s
                                    └─┘┴└─                                  \s
                                    ┌─┐┌─┐┌─┐┌─┐┬ ┬┌─┐┬─┐┌┬┐  ┬             \s
                                    ├─┘├─┤└─┐└─┐││││ │├┬┘ ││  │             \s
                                    ┴  ┴ ┴└─┘└─┘└┴┘└─┘┴└──┴┘  o             \s
                            """);
                            if (!isAdmin) {
                                System.out.println(": 1 : Retry");
                                System.out.println(": 2 : Forgot pin (users only)");
                                System.out.println(": 3 : Return to USER MENU");
                                System.out.println(": 4 : Return to LOGIN MENU");
                            }
                            else {
                                System.out.println(": 1 : Retry");
                                System.out.println(": 2 : Return to ADMIN MENU");
                                System.out.println(": 3 : Return to LOGIN MENU");
                            }
                            System.out.print(">>>: ");
                            Main.temporaryString = Main.scanner.nextLine().trim();
                            if (isNumber(Main.temporaryString)) {
                                switch (Main.temporaryString) {
                                    case "1" -> {
                                        System.out.print("RETRYING");
                                        loading("short");
                                        if (!isAdmin) {
                                            try {
                                                File loginAttempt = new File ("src\\files\\accounts\\user\\" + getUserName() + "'s Folder\\loginAttempts\\remainingAttempts.txt");
                                                Scanner loginCountUpdater = new Scanner(loginAttempt);
                                                int count = loginCountUpdater.nextInt();
                                                loginCountUpdater.close();
                                                count -= 1;
                                                FileUtil.writeToATextFile(String.valueOf(count), loginAttempt);
                                                if (count <= 0) {
                                                    Main.userLoggedIn = false;
                                                    Main.loginCondition = true;
                                                    insertCredentials = false;
                                                    System.out.println("""
                                                         WARNING!!! This account has reached the maximum login attempt.\s
                                                         The system thinks that this account does not belong to you. If this account belongs to you,
                                                         you can talk to the admin, bring your userName and request a new pin code.
                                                    """);
                                                    System.out.print("PROCEEDING TO LOGIN MENU");
                                                    loading("short");
                                                    System.out.println("\n=========================");
                                                    System.out.println("|PRESS ENTER TO CONTINUE|");
                                                    System.out.println("=========================");
                                                    Main.scanner.nextLine();
                                                    resetReturningToLoginMenu();
                                                }
                                                else {
                                                    System.out.printf("REMAINING LOGIN TRIES [ >> %d << ]\n", count);
                                                    insertCredentials = true;
                                                }
                                            }
                                            catch (FileNotFoundException ignored) {
                                            }
                                        }
                                    }
                                    case "2" -> {
                                        if (!isAdmin) {
                                            do {
                                                System.out.println("""
                                                     ┌─┐┬ ┬┌─┐┌─┐┬┌─  ┬┌─┐  ┬ ┬┌─┐┬ ┬                   \s
                                                     │  ├─┤├┤ │  ├┴┐  │├┤   └┬┘│ ││ │                   \s
                                                     └─┘┴ ┴└─┘└─┘┴ ┴  ┴└     ┴ └─┘└─┘                   \s
                                                     ┌─┐┌─┐┌┐┌  ┌─┐┬ ┬┌─┐┌┐┌┌─┐┌─┐  ┬ ┬┌─┐┬ ┬┬─┐  ┌─┐┬┌┐┌
                                                     │  ├─┤│││  │  ├─┤├─┤││││ ┬├┤   └┬┘│ ││ │├┬┘  ├─┘││││
                                                     └─┘┴ ┴┘└┘  └─┘┴ ┴┴ ┴┘└┘└─┘└─┘   ┴ └─┘└─┘┴└─  ┴  ┴┘└┘
                                                """);
                                                System.out.println(": 1 : Check if resetting pin is available");
                                                System.out.println(": 2 : Return to USER MENU");
                                                System.out.println(": 3 : Return to LOGIN MENU");
                                                System.out.print(">>>: ");
                                                Main.temporaryString = Main.scanner.nextLine().trim();
                                                if (isNumber(Main.temporaryString)) {
                                                    switch (Main.temporaryString) {
                                                        case "1" -> {
                                                            System.out.print("CHECKING");
                                                            loading("long");
                                                            if (checkEligibility()) {
                                                                do {
                                                                    System.out.println("""
                                                                          ┬ ┬┌─┐┬ ┬  ┌─┐┬─┐┌─┐  ┌─┐┬  ┬┌─┐┬┌┐ ┬  ┌─┐  ┌┬┐┌─┐  ┌─┐┬ ┬┌─┐┌┐┌┌─┐┌─┐  ┬ ┬┌─┐┬ ┬┬─┐  ┌─┐┬┌┐┌
                                                                          └┬┘│ ││ │  ├─┤├┬┘├┤   ├┤ │  ││ ┬│├┴┐│  ├┤    │ │ │  │  ├─┤├─┤││││ ┬├┤   └┬┘│ ││ │├┬┘  ├─┘││││
                                                                           ┴ └─┘└─┘  ┴ ┴┴└─└─┘  └─┘┴─┘┴└─┘┴└─┘┴─┘└─┘   ┴ └─┘  └─┘┴ ┴┴ ┴┘└┘└─┘└─┘   ┴ └─┘└─┘┴└─  ┴  ┴┘└┘
                                                                           ┬┐┌─┐  ┬ ┬┌─┐┬ ┬  ┬ ┬┌─┐┌┐┌┌┬┐  ┌┬┐┌─┐  ┌─┐┬ ┬┌─┐┌┐┌┌─┐┌─┐  ┬┌┬┐  ┌┐┌┌─┐┬ ┬ ┌─┐
                                                                           │││ │  └┬┘│ ││ │  │││├─┤│││ │    │ │ │  │  ├─┤├─┤││││ ┬├┤   │ │   ││││ ││││  ┌┘
                                                                          ─┴┘└─┘   ┴ └─┘└─┘  └┴┘┴ ┴┘└┘ ┴    ┴ └─┘  └─┘┴ ┴┴ ┴┘└┘└─┘└─┘  ┴ ┴   ┘└┘└─┘└┴┘  o\s
                                                                    """);
                                                                    System.out.println(": 1 : Reset Pin Now");
                                                                    System.out.println(": 2 : Return to USER MENU");
                                                                    System.out.println(": 3 : Return to LOGIN MENU");
                                                                    System.out.print(">>>: ");
                                                                    Main.temporaryString = Main.scanner.nextLine().trim();
                                                                    if (isNumber(Main.temporaryString)) {
                                                                        switch (Main.temporaryString) {
                                                                            case "1" -> {
                                                                                resetPin();
                                                                                insertCredentials = false;
                                                                                Main.userLoggedIn = false;
                                                                                Main.loginCondition = false;
                                                                                Main.isAdmin = false;
                                                                                File ticketFile = new File ("src\\files\\resetPinTickets\\"+ getUserName() + ".txt");
                                                                                FileUtil.writeToATextFile("false", ticketFile);
                                                                                ticketFile.deleteOnExit();
                                                                            }
                                                                            case "2" -> {
                                                                                insertCredentials = false;
                                                                                Main.userLoggedIn = false;
                                                                                Main.loginCondition = false;
                                                                                System.out.print("RETURNING TO USER MENU");
                                                                                loading("short");
                                                                            }
                                                                            case "3" -> {
                                                                                insertCredentials = false;
                                                                                Main.userLoggedIn = false;
                                                                                Main.loginCondition = true;
                                                                                System.out.print("RETURNING TO LOGIN MENU");
                                                                                loading("short");
                                                                            }
                                                                            default -> {
                                                                                System.err.println("""
                                                                                    ┬ ┌┐┌ ┬  ┬┌─┐┬  ┬┌┬┐  ┌─┐┬ ┬┌─┐┬┌─┐┌─┐  ┬
                                                                                    │ │││ └┐┌┘├─┤│  │ ││  │  ├─┤│ │││  ├┤   │
                                                                                    ┴ ┘└┘  └┘ ┴ ┴┴─┘┴─┴┘  └─┘┴ ┴└─┘┴└─┘└─┘  o
                                                                                """);
                                                                                System.out.print("RETURNING TO USER MENU");
                                                                                loading("short");
                                                                            }
                                                                        }
                                                                    }

                                                                } while (!Main.temporaryString.equals("1") && !Main.temporaryString.equals("2") && !Main.temporaryString.equals("3"));
                                                            }
                                                            else {
                                                                File checkUser = new File ("src\\files\\accounts\\user\\" + getUserName() + "'s Folder");
                                                                if (checkUser.isDirectory()) {
                                                                    do {
                                                                        System.out.println("""
                                                                            ┬ ┬┌─┐┬ ┬  ┌─┐┬─┐┌─┐  ┌┐┌┌─┐┌┬┐  ┬ ┬┌─┐┌┬┐  ┌─┐┌─┐┬─┐┌┬┐┬┌┬┐┌┬┐┌─┐┌┬┐ \s
                                                                            └┬┘│ ││ │  ├─┤├┬┘├┤   ││││ │ │   └┬┘├┤  │   ├─┘├┤ ├┬┘││││ │  │ ├┤  ││ \s
                                                                             ┴ └─┘└─┘  ┴ ┴┴└─└─┘  ┘└┘└─┘ ┴    ┴ └─┘ ┴   ┴  └─┘┴└─┴ ┴┴ ┴  ┴ └─┘─┴┘ \s
                                                                            ┌┬┐┌─┐  ┌─┐┬ ┬┌─┐┌┐┌┌─┐┌─┐  ┬ ┬┌─┐┬ ┬┬─┐  ┌─┐┬┌┐┌                     \s
                                                                             │ │ │  │  ├─┤├─┤││││ ┬├┤   └┬┘│ ││ │├┬┘  ├─┘││││                     \s
                                                                             ┴ └─┘  └─┘┴ ┴┴ ┴┘└┘└─┘└─┘   ┴ └─┘└─┘┴└─  ┴  ┴┘└┘                     \s
                                                                        """);
                                                                        System.out.println(": 1 : Submit pin reset ticket");
                                                                        System.out.println(": 2 : Return to USER MENU");
                                                                        System.out.println(": 3 : Return to LOGIN MENU");
                                                                        System.out.print(">>>: ");
                                                                        Main.temporaryString = Main.scanner.nextLine().trim();
                                                                        if (isNumber(Main.temporaryString)) {
                                                                            switch (Main.temporaryString) {
                                                                                case "1" -> {
                                                                                    if (!submitTicket()) {
                                                                                        System.out.println("YOU ALREADY SUBMITTED A TICKET");
                                                                                    }
                                                                                    insertCredentials = false;
                                                                                    Main.userLoggedIn = false;
                                                                                    Main.loginCondition = false;
                                                                                    Main.isAdmin = false;
                                                                                    System.out.print("RETURNING TO USER MENU");
                                                                                    loading("short");
                                                                                }
                                                                                case "2" -> {
                                                                                    insertCredentials = false;
                                                                                    Main.userLoggedIn = false;
                                                                                    Main.loginCondition = false;
                                                                                    System.out.print("RETURNING TO USER MENU");
                                                                                    loading("short");
                                                                                }
                                                                                case "3" -> {
                                                                                    insertCredentials = false;
                                                                                    Main.userLoggedIn = false;
                                                                                    Main.loginCondition = true;
                                                                                    System.out.print("RETURNING TO LOGIN MENU");
                                                                                    loading("short");
                                                                                }
                                                                                default -> {
                                                                                    System.err.println("""
                                                                                        ┬ ┌┐┌ ┬  ┬┌─┐┬  ┬┌┬┐  ┌─┐┬ ┬┌─┐┬┌─┐┌─┐  ┬
                                                                                        │ │││ └┐┌┘├─┤│  │ ││  │  ├─┤│ │││  ├┤   │
                                                                                        ┴ ┘└┘  └┘ ┴ ┴┴─┘┴─┴┘  └─┘┴ ┴└─┘┴└─┘└─┘  o
                                                                                    """);
                                                                                    System.out.print("LOADING");
                                                                                    loading("short");
                                                                                }
                                                                            }
                                                                        }
                                                                    } while (!Main.temporaryString.equals("1") && !Main.temporaryString.equals("2") && !Main.temporaryString.equals("3"));
                                                                }
                                                                else {
                                                                    insertCredentials = false;
                                                                    resetReturningToLoginMenu();
                                                                    System.err.println("""
                                                                            ┬ ┬┌─┐┌─┐┬─┐  ┌┬┐┌─┐┌─┐┌─┐  ┌┐┌┌─┐┌┬┐  ┌─┐─┐ ┬┬┌─┐┌┬┐  ┬
                                                                            │ │└─┐├┤ ├┬┘   │││ │├┤ └─┐  ││││ │ │   ├┤ ┌┴┬┘│└─┐ │   │
                                                                            └─┘└─┘└─┘┴└─  ─┴┘└─┘└─┘└─┘  ┘└┘└─┘ ┴   └─┘┴ └─┴└─┘ ┴   o
                                                                    """);
                                                                    System.out.print("RETURNING TO LOGIN MENU");
                                                                    loading("long");
                                                                }
                                                            }
                                                        }
                                                        case "2" -> {
                                                            insertCredentials = false;
                                                            Main.userLoggedIn = false;
                                                            Main.loginCondition = false;
                                                            System.out.print("RETURNING TO USER MENU");
                                                            loading("short");
                                                        }
                                                        case "3" -> {
                                                            insertCredentials = false;
                                                            Main.userLoggedIn = false;
                                                            Main.loginCondition = true;
                                                            System.out.print("RETURNING TO LOGIN MENU");
                                                            loading("short");
                                                        }
                                                        default -> {
                                                            System.err.println("""
                                                                    ┬ ┌┐┌ ┬  ┬┌─┐┬  ┬┌┬┐  ┌─┐┬ ┬┌─┐┬┌─┐┌─┐  ┬
                                                                    │ │││ └┐┌┘├─┤│  │ ││  │  ├─┤│ │││  ├┤   │
                                                                    ┴ ┘└┘  └┘ ┴ ┴┴─┘┴─┴┘  └─┘┴ ┴└─┘┴└─┘└─┘  o
                                                                """);
                                                            System.out.print("LOADING");
                                                            loading("short");
                                                        }
                                                    }
                                                }
                                            } while (!Main.temporaryString.equals("1") && !Main.temporaryString.equals("2") && !Main.temporaryString.equals("3"));
                                        }
                                        else {
                                            insertCredentials = false;
                                            System.out.print("RETURNING TO ADMIN MENU");
                                            loading("short");
                                        }
                                    }
                                    case "3" -> {
                                        if (!isAdmin) {
                                            insertCredentials = false;
                                            Main.userLoggedIn = false;
                                            Main.loginCondition = false;
                                            System.out.print("RETURNING TO USER MENU");
                                            loading("short");
                                        }
                                        else {
                                            insertCredentials = false;
                                            Main.userLoggedIn = false;
                                            Main.loginCondition = true;
                                            System.out.print("RETURNING TO LOGIN MENU");
                                            loading("short");
                                            resetReturningToLoginMenu();
                                        }
                                    }
                                    case "4" -> {
                                        if (!isAdmin) {
                                            insertCredentials = false;
                                            Main.userLoggedIn = false;
                                            Main.loginCondition = true;
                                            System.out.print("RETURNING TO LOGIN MENU");
                                            loading("short");
                                            resetReturningToLoginMenu();
                                        }
                                        else {
                                            System.err.println("""
                                                 ┬ ┌┐┌ ┬  ┬┌─┐┬  ┬┌┬┐  ┌─┐┬ ┬┌─┐┬┌─┐┌─┐  ┬
                                                 │ │││ └┐┌┘├─┤│  │ ││  │  ├─┤│ │││  ├┤   │
                                                 ┴ ┘└┘  └┘ ┴ ┴┴─┘┴─┴┘  └─┘┴ ┴└─┘┴└─┘└─┘  o
                                            """);
                                            System.out.print("LOADING");
                                            loading("short");
                                        }
                                    }
                                    default -> {
                                        System.err.println("""
                                            ┬ ┌┐┌ ┬  ┬┌─┐┬  ┬┌┬┐  ┌─┐┬ ┬┌─┐┬┌─┐┌─┐  ┬
                                            │ │││ └┐┌┘├─┤│  │ ││  │  ├─┤│ │││  ├┤   │
                                            ┴ ┘└┘  └┘ ┴ ┴┴─┘┴─┴┘  └─┘┴ ┴└─┘┴└─┘└─┘  o
                                        """);
                                        System.out.print("LOADING");
                                        loading("short");
                                    }
                                }
                            }
                            else {
                                System.err.println("""
                                     ┬ ┌┐┌ ┬  ┬┌─┐┬  ┬┌┬┐  ┌─┐┬ ┬┌─┐┬┌─┐┌─┐  ┬
                                     │ │││ └┐┌┘├─┤│  │ ││  │  ├─┤│ │││  ├┤   │
                                     ┴ ┘└┘  └┘ ┴ ┴┴─┘┴─┴┘  └─┘┴ ┴└─┘┴└─┘└─┘  o
                                """);
                                System.out.print("LOADING");
                                loading("short");
                            }
                        } while (!Main.temporaryString.equals("1") && !Main.temporaryString.equals("2") && !Main.temporaryString.equals("3") && !Main.temporaryString.equals("4"));
                    }
                }
            }
            case "2" -> {
                if (isAdmin) {
                    System.out.print("RETURNING TO LOGIN MENU");
                    loading("short");
                    resetReturningToLoginMenu();
                }
                else {
                    createUserAccount();
                }
            }
            case "3" -> {
                if (!isAdmin) {
                    System.out.print("RETURNING TO LOGIN MENU");
                    loading("short");
                    resetReturningToLoginMenu();
                }
                else {
                    System.err.println("""
                        ┬ ┌┐┌ ┬  ┬┌─┐┬  ┬┌┬┐  ┌─┐┬ ┬┌─┐┬┌─┐┌─┐  ┬
                        │ │││ └┐┌┘├─┤│  │ ││  │  ├─┤│ │││  ├┤   │
                        ┴ ┘└┘  └┘ ┴ ┴┴─┘┴─┴┘  └─┘┴ ┴└─┘┴└─┘└─┘  o
                    """);
                    System.out.print("LOADING");
                    loading("short");
                }
            }
            default -> {
                System.err.println("""
                    ┬ ┌┐┌ ┬  ┬┌─┐┬  ┬┌┬┐  ┌─┐┬ ┬┌─┐┬┌─┐┌─┐  ┬
                    │ │││ └┐┌┘├─┤│  │ ││  │  ├─┤│ │││  ├┤   │
                    ┴ ┘└┘  └┘ ┴ ┴┴─┘┴─┴┘  └─┘┴ ┴└─┘┴└─┘└─┘  o
                """);
                System.out.print("LOADING");
                loading("short");
            }
        }
    }
    /**
     *  User functionalities if the user is logged in.
     */
    public void showUserDetails() throws InterruptedException {
        // TODO add user selections
        System.out.println("""
            ┬ ┬┌─┐┌─┐┬─┐  ┌─┐┌─┐┬  ┌─┐┌─┐┌┬┐┬┌─┐┌┐┌
            │ │└─┐├┤ ├┬┘  └─┐├┤ │  ├┤ │   │ ││ ││││
            └─┘└─┘└─┘┴└─  └─┘└─┘┴─┘└─┘└─┘ ┴ ┴└─┘┘└┘
        """);
        System.out.println(": 1 : Perform computation");
        System.out.println(": 2 : Play Games");
        System.out.println(": 3 : Start systems");
        System.out.println(": 4 : return to USER menu");
        System.out.println(": 5 : return to LOGIN menu");
        System.out.print(">>>: ");
        Main.temporaryString = Main.scanner.nextLine().trim();
        switch (Main.temporaryString) {
            case "1" -> {
                boolean performComputation = true;
                while (performComputation) {
                    System.out.println(": 1 : Compute factorial");
                    System.out.println(": 2 : Compute Fibonacci");
                    System.out.println(": 3 : Sum all numbers");
                    System.out.println(": 4 : Sort a number");
                    System.out.println(": 5 : Compute hypotenuse");
                    System.out.println(": 6 : Start calculator");
                    System.out.println(": 7 : Compute Collatz conjecture");
                    System.out.println(": 8 : Centimeter Conversion");
                    System.out.println(": 9 : Meter conversion");
                    System.out.println(":10 : Quadrant analyzer");
                    System.out.println(":11 : Compute mean, median, mode, and standard deviation");
                    System.out.println(":12 : Return to USER Selection");
                    System.out.print(">>>: ");
                    Main.temporaryString = Main.scanner.nextLine().trim();
                    switch (Main.temporaryString) {
                        case "1" -> {
                            Factorial factorial = new Factorial();
                            computeFactorial = true;
                            while (computeFactorial) {
                                System.out.println("Enter a number");
                                System.out.print(">>>: ");
                                Main.temporaryString = Main.scanner.nextLine().trim();
                                if (InputChecker.isInteger(Main.temporaryString)) {
                                    int number = Integer.parseInt(Main.temporaryString);
                                    factorial.setNumber(number).getFactorial();
                                    tryAgain("computeFactorial");
                                }
                                else {
                                    System.err.println("THAT IS NOT A NUMBER (!)");
                                }
                            }
                        }
                        case "2" -> {
                            Fibonacci fibonacci = new Fibonacci();
                            computeFibonacci = true;
                            while (computeFibonacci) {
                                System.out.println("HOW DO YOU WANT TO COMPUTE FIBONACCI?");
                                System.out.println(": 1 : Get the fibonacci at nth position");
                                System.out.println(": 2 : Get the fibonacci until nth position");
                                System.out.print(">>>: ");
                                Main.temporaryString = Main.scanner.nextLine().trim();
                                switch (Main.temporaryString) {
                                    case "1" -> {
                                        System.out.println("Enter a number");
                                        System.out.print(">>>: ");
                                        Main.temporaryString = Main.scanner.nextLine().trim();
                                        if (InputChecker.isInteger(Main.temporaryString)) {
                                            int fibonacciAtNthPosition = Integer.parseInt(Main.temporaryString);
                                            fibonacci.setNumber(fibonacciAtNthPosition).getFibonacciNumberAtNth();
                                            tryAgain("computeFibonacci");
                                        }
                                    }
                                    case "2" -> {
                                        System.out.println("Enter a number");
                                        System.out.print(">>>: ");
                                        Main.temporaryString = Main.scanner.nextLine().trim();
                                        if (InputChecker.isInteger(Main.temporaryString)) {
                                            int fibonacciNumberUntilN = Integer.parseInt(Main.temporaryString);
                                            fibonacci.setNumber(fibonacciNumberUntilN).getFibonacciNumberUntilN();
                                            tryAgain("computeFibonacci");
                                        }
                                    }
                                    default -> System.err.println("PLEASE CHOOSE 1 or 2");
                                }
                            }
                        }
                        case "3" -> {
                            SumOfAllNumbers sumOfAllNumbers = new SumOfAllNumbers();
                            computeSumOfAllNumbers = true;
                            while (computeSumOfAllNumbers) {
                                System.out.println("Enter a number");
                                System.out.print(">>>: ");
                                Main.temporaryString = Main.scanner.nextLine().trim();
                                if (InputChecker.isInteger(Main.temporaryString)) {
                                    int number = Integer.parseInt(Main.temporaryString);
                                    sumOfAllNumbers.setNumber(number).getSumOfAllNumbers();
                                    tryAgain("computeSumOfAllNumbers");
                                }
                                else {
                                    System.err.println("THAT IS NOT A NUMBER (!)");
                                }
                            }
                        }
                        case "4" -> {
                            byte length;
                            int[] intArray;
                            double[] doubleArray;
                            int intChoice;
                            double doubleChoice;
                            sort = true;
                            while (sort) {
                                System.out.println("WHAT TYPE OF NUMBERS DO YOU WANT TO SORT?");
                                System.out.println(": 1 : Whole numbers");
                                System.out.println(": 2 : Numbers with decimal");
                                System.out.print(">>>: ");
                                Main.temporaryString = Main.scanner.nextLine().trim();
                                switch (Main.temporaryString) {
                                    case "1" -> {
                                        System.out.println("HOW MANY NUMBERS YOU WANT TO SORT?");
                                        System.out.print(">>>: ");
                                        Main.temporaryString = Main.scanner.nextLine().trim();
                                        if (InputChecker.isByte(Main.temporaryString)) {
                                            System.err.println("PLEASE ENTER ONLY NUMBERS IN THIS PROCESS");
                                            System.err.println("IF YOU ENTERED A CHARACTER OR A STRING\nTHE PROGRAM WILL AUTOMATICALLY INSERT 1 IN THE ARRAY");
                                            length = Byte.parseByte(Main.temporaryString);
                                            intArray = new int[length];
                                            for (int i = 0; i < intArray.length; i++) {
                                                System.out.println("ENTER WHOLE NUMBERS");
                                                System.out.print(">>>: ");
                                                Main.temporaryString = Main.scanner.nextLine().trim();
                                                if (InputChecker.isInteger(Main.temporaryString)) {
                                                    intChoice = Integer.parseInt(Main.temporaryString);
                                                    intArray[i] = intChoice;
                                                }
                                                else {
                                                    intArray[i] = 1;
                                                }
                                            }
                                            QuickSort.sort(intArray); // sorting
                                            tryAgain("sort");
                                        }
                                        else {
                                            System.err.println("THAT IS NOT A NUMBER");
                                        }
                                    }
                                    case "2" -> {

                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    /**
     *  Admin functionalities if the admin is logged in.
     *  Enables the admin to view user accounts, remove user accounts, and view tickets of user who want to reset their pin.
     *  @throws InterruptedException if the thread is interrupted during execution.
     */
    public void showAdminDetails() throws InterruptedException, IOException {
        System.out.println("""
            ┌─┐┌┬┐ ┌┬┐ ┬ ┌┐┌  ┌─┐┌─┐┬  ┌─┐┌─┐┌┬┐┬┌─┐┌┐┌
            ├─┤ ││ │││ │ │││  └─┐├┤ │  ├┤ │   │ ││ ││││
            ┴ ┴ ┴┘ ┴ ┴ ┴ ┘└┘  └─┘└─┘┴─┘└─┘└─┘ ┴ ┴└─┘┘└┘
        """);
        System.out.println(": 1 : View accounts");
        System.out.println(": 2 : Remove accounts");
        System.out.println(": 3 : View tickets");
        System.out.println(": 4 : return to ADMIN menu");
        System.out.println(": 5 : return to LOGIN menu");
        System.out.print(">>>: ");
        Main.temporaryString = Main.scanner.nextLine().trim();
        switch (Main.temporaryString) {
            case "1" -> {
                listOfUsersAndPasswords();
                System.out.println("\n=========================");
                System.out.println("|PRESS ENTER TO CONTINUE|");
                System.out.println("=========================");
                Main.scanner.nextLine();
                Main.temporaryString = "";
                showAdminDetails();
            }
            case "2" -> {
                List<String> users = viewUsers();
                if (users.size() != 0) {
                    System.out.print("ENTER USER ACCOUNT YOU WANT TO REMOVE: ");
                    String name = Main.scanner.nextLine().trim();
                    if (!name.isEmpty()) {
                        File user = new File("src\\files\\accounts\\user\\" + name + "'s Folder"); // users folder
                        File userCache = new File("C:\\Users\\Public\\Cache\\" + name + "'s Folder");
                        if (user.exists()) {
                            try {
                                FileUtils.deleteDirectory(new File(String.valueOf(user))); //deletes the whole user folder
                                FileUtils.deleteDirectory(new File(String.valueOf(userCache))); //deletes the whole user cache folder
                                System.out.printf("REMOVING THE ACCOUNT: [%s]", name);
                                loading("long");
                                System.out.println("SUCCESSFULLY REMOVED (!)");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        else {
                            System.err.println("""
                            ┬ ┬┌─┐┌─┐┬─┐  ┌┬┐┌─┐┌─┐┌─┐  ┌┐┌┌─┐┌┬┐  ┌─┐─┐ ┬┬┌─┐┌┬┐
                            │ │└─┐├┤ ├┬┘   │││ │├┤ └─┐  ││││ │ │   ├┤ ┌┴┬┘│└─┐ │\s
                            └─┘└─┘└─┘┴└─  ─┴┘└─┘└─┘└─┘  ┘└┘└─┘ ┴   └─┘┴ └─┴└─┘ ┴\s
                        """);
                        }
                    }
                    else {
                        System.err.println("""
                        ┬ ┬┌┐┌┬┌─┌┐┌┌─┐┬ ┬┌┐┌  ┬ ┬┌─┐┌─┐┬─┐┌┐┌┌─┐┌┬┐┌─┐
                        │ ││││├┴┐││││ │││││││  │ │└─┐├┤ ├┬┘│││├─┤│││├┤\s
                        └─┘┘└┘┴ ┴┘└┘└─┘└┴┘┘└┘  └─┘└─┘└─┘┴└─┘└┘┴ ┴┴ ┴└─┘
                     """);
                    }
                }
                else {
                    System.err.println("""
                       ┌┬┐┬ ┬┌─┐┬─┐┌─┐  ┌─┐┬─┐┌─┐  ┌┐┌┌─┐  ┬ ┬┌─┐┌─┐┬─┐  ┌─┐┌─┐┌─┐┌─┐┬ ┬┌┐┌┌┬┐┌─┐  ┬ ┌┐┌  ┌┬┐┬ ┬┌─┐  ┬  ┬┌─┐┌┬┐
                        │ ├─┤├┤ ├┬┘├┤   ├─┤├┬┘├┤   ││││ │  │ │└─┐├┤ ├┬┘  ├─┤│  │  │ ││ ││││ │ └─┐  │ │││   │ ├─┤├┤   │  │└─┐ │\s
                        ┴ ┴ ┴└─┘┴└─└─┘  ┴ ┴┴└─└─┘  ┘└┘└─┘  └─┘└─┘└─┘┴└─  ┴ ┴└─┘└─┘└─┘└─┘┘└┘ ┴ └─┘  ┴ ┘└┘   ┴ ┴ ┴└─┘  ┴─┘┴└─┘ ┴\s
                    """);
                }
                System.out.print("RETURNING TO ADMIN SELECTION");
                loading("short");
                System.out.println("\n=========================");
                System.out.println("|PRESS ENTER TO CONTINUE|");
                System.out.println("=========================");
                Main.scanner.nextLine();
                Main.temporaryString = "";
                showAdminDetails();
            }
            case "3" -> {
                Ticketing ticketing = new Ticketing();
                ticketing.editEligibility(Main.isAdmin);
            }
            case "4" -> {
                Main.adminLoggedIn = false;
                Main.loginCondition = false;
                Main.isAdmin = true;
                System.out.print("LOGGING OUT");
                loading("long");
                System.out.println("SUCCESSFULLY LOGGED OUT");
                System.out.print("RETURNING TO ADMIN MENU");
                loading("short");
            }
            case "5" -> {
                resetReturningToLoginMenu();
                System.out.print("LOGGING OUT");
                loading("long");
                System.out.print("RETURNING TO LOGIN MENU");
                loading("short");
            }
            default -> {
                System.err.println("""
                    ┬ ┌┐┌ ┬  ┬┌─┐┬  ┬┌┬┐  ┌─┐┬ ┬┌─┐┬┌─┐┌─┐  ┬
                    │ │││ └┐┌┘├─┤│  │ ││  │  ├─┤│ │││  ├┤   │
                    ┴ ┘└┘  └┘ ┴ ┴┴─┘┴─┴┘  └─┘┴ ┴└─┘┴└─┘└─┘  o
                """);
                System.out.print("LOADING");
                loading("short");
                Main.temporaryString = "";
                showAdminDetails();
            }
        }
    }
    private void listOfUsersAndPasswords() throws InterruptedException, FileNotFoundException {
        List<String> allUsers = viewUsers();
        if (allUsers.size() != 0) {
            System.out.println("""
                ┬  ┬┌─┐┌┬┐  ┌─┐┌─┐  ┬ ┬┌─┐┌─┐┬─┐┌─┐
                │  │└─┐ │   │ │├┤   │ │└─┐├┤ ├┬┘└─┐
                ┴─┘┴└─┘ ┴   └─┘└    └─┘└─┘└─┘┴└─└─┘
            """);
            for (int i = 0; i < allUsers.size(); i++) {
                File userName = new File ("src\\files\\accounts\\user\\" + allUsers.get(i) + "\\credentials\\username.txt");
                File userPasswords = new File ("src\\files\\accounts\\user\\" + allUsers.get(i) + "\\credentials\\pin.txt");
                String credentials = SecurityUtil.viewCredentials(userName, userPasswords, Main.isAdmin);
                String[] cred =  credentials.split(" +");
                System.out.printf("USER     [%d]: %s\n", ( i + 1 ), cred[0]);
                System.out.printf("PASSWORD [%d]: %s\n", ( i + 1 ), cred[1]);
            }
        }
        else {
            System.err.println("""
              ┌┬┐┬ ┬┌─┐┬─┐┌─┐  ┌─┐┬─┐┌─┐  ┌┐┌┌─┐  ┬ ┬┌─┐┌─┐┬─┐  ┌─┐┌─┐┌─┐┌─┐┬ ┬┌┐┌┌┬┐┌─┐  ┬ ┌┐┌  ┌┬┐┬ ┬┌─┐  ┬  ┬┌─┐┌┬┐
               │ ├─┤├┤ ├┬┘├┤   ├─┤├┬┘├┤   ││││ │  │ │└─┐├┤ ├┬┘  ├─┤│  │  │ ││ ││││ │ └─┐  │ │││   │ ├─┤├┤   │  │└─┐ │\s
               ┴ ┴ ┴└─┘┴└─└─┘  ┴ ┴┴└─└─┘  ┘└┘└─┘  └─┘└─┘└─┘┴└─  ┴ ┴└─┘└─┘└─┘└─┘┘└┘ ┴ └─┘  ┴ ┘└┘   ┴ ┴ ┴└─┘  ┴─┘┴└─┘ ┴\s
            """);
            System.out.print("RETURNING TO ADMIN SELECTION");
            loading("short");
        }
    }


    /*
     * Method that prints a tailing dot like it was loading
     */
    public static void loading(String delay) throws InterruptedException {
        if (delay.equals("long")){
            for (int i = 1; i <= 3; i++) {
                TimeUnit.MILLISECONDS.sleep(700);
                System.out.print('.');
            }
        }
        else if (delay.equals("short")){
            for (int i = 1; i <= 3; i++) {
                TimeUnit.MILLISECONDS.sleep(400);
                System.out.print('.');
            }
        }
        System.out.println();
    }

    protected List<String> viewUsers() {
        File usersDirectory = new File("src\\files\\accounts\\user\\");
        FileFilter directoryFileFilter = File::isDirectory;
        File[] directoryListAsFile = usersDirectory.listFiles(directoryFileFilter);
        assert directoryListAsFile != null;
        List<String> users = new ArrayList<>(directoryListAsFile.length);
        for (File directoryAsFile : directoryListAsFile) {
            users.add(directoryAsFile.getName());
        }
        return users;
    }
    protected List<String> viewUserTickets() {
        File userTicketsDirectory = new File("src\\files\\resetPinTickets\\");
        FileFilter allFiles = File::isFile;
        File[] filesAsList = userTicketsDirectory.listFiles(allFiles);
        assert filesAsList != null;
        List<String> tickets = new ArrayList<>(filesAsList.length);
        for (File file : filesAsList) {
            tickets.add(FilenameUtils.getBaseName(file.getName()));
        }
        return tickets;
    }
    protected boolean checkUserTicket(File ticketFile) throws IOException {
        String fileValue = null;
        try {
            File file = new File(String.valueOf(ticketFile));
            Scanner checkTicket = new Scanner(file);
            fileValue = checkTicket.nextLine();
        } catch (FileNotFoundException ignored) {
        }
        assert fileValue != null;
        return fileValue.equals("true");
    }
    /**
     *  Admin functionalities if the admin is logged in.
     *  Method that allows the user to create accounts.
     *  @throws IOException if input output process was interrupted
     *  @throws InterruptedException if the thread is interrupted during execution
     */
    private void createUserAccount() throws IOException, InterruptedException {
        System.out.print("ENTER USERNAME: ");
        Main.temporaryString = Main.scanner.nextLine().trim();
        if (Main.temporaryString.matches("[a-zA-Z]+") || Main.temporaryString.matches("[a-zA-z0-9]+")) {
            setUserName(Main.temporaryString);
            File userAccountFolder = new File ("src\\files\\accounts\\user\\" + getUserName() + "'s Folder\\");
            File cache = new File ("C:\\Users\\Public\\Cache\\" + getUserName() + "'s Folder\\");
            File credentialsFolder = new File ("src\\files\\accounts\\user\\" + getUserName() + "'s Folder\\credentials");
            File attemptsFolder = new File ("src\\files\\accounts\\user\\" + getUserName() + "'s Folder\\loginAttempts");
            if (userAccountFolder.mkdirs() && credentialsFolder.mkdirs() && attemptsFolder.mkdirs() | cache.mkdirs()) {
                File userName = new File("src\\files\\accounts\\user\\" + getUserName() + "'s Folder\\credentials\\username.txt");
                File userPin = new File("src\\files\\accounts\\user\\" + getUserName() + "'s Folder\\credentials\\pin.txt");
                File userLoginAttempt = new File("src\\files\\accounts\\user\\" + getUserName() + "'s Folder\\loginAttempts\\remainingAttempts.txt");
                FileUtil.writeToATextFile(getUserName(), userName);
                char[] oneTimePin = PinGenerator.generatePin();
                setPin(String.valueOf(oneTimePin));
                FileUtil.writeToATextFile(getPin(), userPin);
                SecurityUtil.encryptUserName(userName, getUserName());
                SecurityUtil.encryptPin(userPin, getUserName());
                if (getPin().length() == 6) {
                    FileUtil.writeToATextFile("4", userLoginAttempt); // 4 login attempts, if the user did not follow instructions carefully
                }
                else {
                    FileUtil.writeToATextFile("6", userLoginAttempt); // 6 login attempts
                }
                System.out.print("CREATING YOUR ACCOUNT");
                loading("long");
                System.out.println("SUCCESSFULLY CREATED (!)");
                System.out.println("=========================");
                System.out.print("YOUR PIN: ");
                for (char c : oneTimePin) {
                    System.out.print(c);
                }
                System.out.println("\n=========================");
                System.out.print("RETURNING TO LOGIN MENU");
                loading("short");
                System.out.println("=========================");
                System.out.println("|PRESS ENTER TO CONTINUE|");
                System.out.println("=========================");
                Main.scanner.nextLine();
                Main.userLoggedIn = false;
                Main.loginCondition = true;
            }
            else {
                System.err.println("""
                    ┬ ┬┌─┐┌─┐┬─┐┌┐┌┌─┐┌┬┐┌─┐  ┌─┐┬  ┬─┐┌─┐┌─┐┌┬┐┬ ┬  ┌─┐─┐ ┬┬┌─┐┌┬┐┌─┐┌┬┐        \s
                    │ │└─┐├┤ ├┬┘│││├─┤│││├┤   ├─┤│  ├┬┘├┤ ├─┤ ││└┬┘  ├┤ ┌┴┬┘│└─┐ │ ├┤  ││        \s
                    └─┘└─┘└─┘┴└─┘└┘┴ ┴┴ ┴└─┘  ┴ ┴┴─┘┴└─└─┘┴ ┴─┴┘ ┴   └─┘┴ └─┴└─┘ ┴ └─┘─┴┘        \s
                    ┌─┐┬  ┌─┐┌─┐┌─┐┌─┐  ┌┬┐┬─┐┬ ┬  ┌─┐┌┐┌┌─┐┌┬┐┬ ┬┌─┐┬─┐  ┬ ┬┌─┐┌─┐┬─┐┌┐┌┌─┐┌┬┐┌─┐
                    ├─┘│  ├┤ ├─┤└─┐├┤    │ ├┬┘└┬┘  ├─┤││││ │ │ ├─┤├┤ ├┬┘  │ │└─┐├┤ ├┬┘│││├─┤│││├┤\s
                    ┴  ┴─┘└─┘┴ ┴└─┘└─┘   ┴ ┴└─ ┴   ┴ ┴┘└┘└─┘ ┴ ┴ ┴└─┘┴└─  └─┘└─┘└─┘┴└─┘└┘┴ ┴┴ ┴└─┘
                """);
                System.out.print("RETURNING TO USER MENU");
                loading("short");
            }
        }
    }
    /**
     *  Method that resets the pins of the user, if the user is eligible to change pin
     * @throws IOException if input output process was interrupted
     * @throws InterruptedException if the thread is interrupted during execution
     */
    private void resetPin() throws IOException, InterruptedException {
        if (checkEligibility()) {
            isResettingPin = true;
            File username = new File ("src\\files\\accounts\\user\\" + getUserName() + "'s Folder\\credentials\\username.txt");
            File changePinCode = new File ("src\\files\\accounts\\user\\" + getUserName() + "'s Folder\\credentials\\pin.txt");
            File updateAttempt = new File ("src\\files\\accounts\\user\\" + getUserName() + "'s Folder\\loginAttempts\\remainingAttempts.txt");
            char[] oneTimePin = PinGenerator.generatePin();
            setPin(String.valueOf(oneTimePin));
            FileUtil.writeToATextFile(getUserName(), username);
            FileUtil.writeToATextFile(getPin(), changePinCode);
            if (getPin().length() == 6) {
                FileUtil.writeToATextFile("4", updateAttempt); // 4 login attempts, if the user did not follow instructions carefully
            }
            else {
                FileUtil.writeToATextFile("6", updateAttempt); // 6 login attempts
            }
            SecurityUtil.encryptPin(changePinCode, getUserName());
            System.out.print("CHANGING YOUR PIN");
            loading("long");
            System.out.println("SUCCESSFULLY CHANGED YOUR PIN (!)");
            System.out.println("=========================");
            System.out.print("YOUR PIN: ");
            for (char c : oneTimePin) {
                System.out.print(c);
            }
            System.out.println("\n=========================");
            System.out.print("RETURNING TO LOGIN MENU");
            loading("short");
            System.out.println("=========================");
            System.out.println("|PRESS ENTER TO CONTINUE|");
            System.out.println("=========================");
            Main.scanner.nextLine();
            Main.userLoggedIn = false;
            Main.loginCondition = true;
        }
    }
    /**
     *  Checks if a String that is passed in is a number or not.
     *  @param numberString a String that contains an integer value.
     *  @return true if the String that is passed in is a number or not.
     */
    private boolean isNumber(String numberString) {
        try {
            Byte.parseByte(numberString);
            return true;
        }
        catch (NumberFormatException ignored) {
        }
        return false;
    }
    /**
     *  Checks if the user is eligible to change his/her pin.
     *  getting the userName and checks if the username exists as a key in a HashTable.
     *  gets the value of that key and checks if true or not.
     *  @return true if the key has the value true in a Hashtable.
     */
    private boolean checkEligibility() {
        try {
            boolean isActiveTicket = checkUserTicket(new File("src\\files\\resetPinTickets\\" + getUserName() + ".txt"));
            if (isActiveTicket) {
                return true;
            }
        }
        catch (NullPointerException ignored) {
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    /**
     *  Enables the user to send reset pin ticket and storing their ticket in a HashTable, only if the user forgot his/her password.
     */
    private boolean submitTicket() throws InterruptedException, IOException {
        insertTicket.put(getUserName(), true);
        Ticketing ticketing = new Ticketing();
        return ticketing.submitResetTicket(getUserName());
    }
    /**
     * Resets the fields in order to return to the login menu.
     */
    public void resetReturningToLoginMenu() {
        Main.isAdmin = false;
        Main.userLoggedIn = false;
        Main.adminLoggedIn = false;
        Main.loginCondition = true;
    }
    private static void tryAgain(String whichComputation) throws InterruptedException {
        System.out.println("DO YOU WANT TO COMPUTE AGAIN? ");
        System.out.println(": 1 : Yes");
        System.out.println(": 2 : No");
        System.out.print(">>>: ");
        Main.temporaryString = Main.scanner.nextLine().trim();
        switch (Main.temporaryString) {
            case "1" -> {
                System.out.print("PROCEEDING");
                loading("short");
            }
            case "2" -> {
                System.out.print("RETURNING TO COMPUTATION SELECTION");
                loading("short");
                switch (whichComputation) {
                    case "computeFactorial" -> computeFactorial = false;
                    case "computeFibonacci" -> computeFibonacci = false;
                    case "computeSumOfAllNumbers" -> computeSumOfAllNumbers = false;
                    case "sort" -> sort = false;
                }

            }
            default -> System.err.println("PLEASE CHOOSE 1 or 2");
        }
    }
    private static void resetUserSelectionPerformComputation() {
        computeFactorial = true;
        computeFibonacci = true;
        computeSumOfAllNumbers = true;
        sort = true;
    }
}
