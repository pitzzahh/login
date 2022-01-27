package mainActivity;

import framework.Process;

import java.io.File;
import java.io.IOException;

import java.util.Scanner;
public class Main {
    public static String temporaryString;
    // when logging in as admin or user
    public static boolean isAdmin = false;
    public static boolean isUser = false;
    public static boolean isExit = false;
    public static boolean loginCondition = true;

    // when user is selected
    public static boolean userLoggedIn = true;

    // when creating account
    public static boolean createAccountCondition = true;

    public static Scanner scanner = new Scanner(System.in);
    static Process process = new Process();
    public static void main(String[] args) throws InterruptedException, IOException {

        while (!isExit) {
            while (loginCondition) {
                System.out.println("""
                              ╔═╗╦╔╦╗╔═╗╦  ╔═╗  \s
                              ╚═╗║║║║╠═╝║  ║╣   \s
                              ╚═╝╩╩ ╩╩  ╩═╝╚═╝  \s
                            ╦  ╔═╗╔═╗╦╔╗╔       \s
                            ║  ║ ║║ ╦║║║║       \s
                            ╩═╝╚═╝╚═╝╩╝╚╝       \s
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
                        isUser = false;
                        loginCondition = false;
                    }
                    case "2" -> {
                        isAdmin = false;
                        isUser = true;
                        loginCondition = false;
                    }
                    case "3" -> {
                        isExit = true;
                        loginCondition = false;
                        isAdmin = false;
                        isUser = false;
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
            if (isAdmin) {
                // TODO admin can delete users and can see users usernames and passwords
                System.out.println("""
                        ┌─┐┌┬┐┌┬┐ ┬ ┌┐┌  ┌┬┐┌─┐┌┐┌ ┬ ┬
                        ├─┤ │││││ │ │││  │││├┤ │││ │ │
                        ┴ ┴ ┴┘┴ ┴ ┴ ┘└┘  ┴ ┴└─┘┘└┘ └─┘
                        """);
                loginCondition = true;
            }
            else if (isUser) {
                System.out.println("""
                        ┬ ┬┌─┐┌─┐┬─┐  ┌┬┐┌─┐┌┐┌ ┬ ┬
                        │ │└─┐├┤ ├┬┘  │││├┤ │││ │ │
                        └─┘└─┘└─┘┴└─  ┴ ┴└─┘┘└┘ └─┘
                """);
                System.out.println(": 1 : Sign in");
                System.out.println(": 2 : create account");
                System.out.println(": 3 : return to login menu");
                System.out.print(">>>: ");
                temporaryString = scanner.nextLine().trim();
                switch (temporaryString) {
                    case "1" -> {
                        // TODO change
                        boolean userCondition = true;
                        boolean pinCondition = true;
                        while (userCondition) {
                            System.out.print("ENTER USERNAME: ");
                            temporaryString = scanner.nextLine().trim();
                            if (!process.isNumber(temporaryString)) {
                                process.setUserName(temporaryString);
                                File user = new File ("src\\" + "files\\" + "accounts\\" + "user\\" + process.getUserName());
                                if (user.exists()) {
                                    while (pinCondition) {
                                        File loginAttempt = new File ("src\\" + "files\\" + "accounts\\" + "user\\" + process.getUserName() + "\\loginAttempt.txt");
                                        Scanner updater = new Scanner(loginAttempt);
                                        int count = updater.nextInt();
                                        updater.close();
                                        if (count == 0) {
                                            userCondition = false;
                                            pinCondition = false;
                                            userLoggedIn = false;
                                            System.out.println("""
                                                    WARNING!!! This account has reached the maximum login attempt.\s
                                                    The system thinks that this account does not belong to you. If this account belongs to you,
                                                    you can talk to the admin, bring your ID and request a new pin code.
                                            """);
                                            System.out.print("Proceeding to Student's Menu");
                                            process.loading("short");
                                        }
                                        if (count != 0) {
                                            System.out.println("Login Attempts [ " + count +" ]");
                                            System.out.print("ENTER PIN: ");
                                            temporaryString = scanner.nextLine().trim();
                                            process.setPin(temporaryString);
                                            File checkPin = new File ("src\\" + "files\\" + "accounts\\" + "user\\" + process.getUserName() + "\\pin.txt");
                                            Scanner validatePin = new Scanner(checkPin);
                                            String pinCheck =  validatePin.nextLine();
                                            System.out.print("LOGGING IN");
                                            process.loading("long");
                                            if (process.getPin().equals(pinCheck)) {
                                                System.out.println("LOGGED IN!");
                                                userCondition = false;
                                                pinCondition = false;
                                                userLoggedIn = true;
                                            }
                                            else {
                                                do {
                                                    System.out.println("""
                                                            ┬ ┌┐┌ ┬  ┬┌─┐┬  ┬┌┬┐  ┌─┐┬┌┐┌
                                                            │ │││ └┐┌┘├─┤│  │ ││  ├─┘││││
                                                            ┴ ┘└┘  └┘ ┴ ┴┴─┘┴─┴┘  ┴  ┴┘└┘
                                                            """);
                                                    System.out.println(": 1 : Retry");
                                                    System.out.println(": 2 : Return to USER MENU");
                                                    System.out.println(": 3 : Return to LOGIN MENU");
                                                    System.out.println(": 4 : Forgot pin");
                                                    System.out.print(">>>: ");
                                                    temporaryString = scanner.nextLine().trim();
                                                    if (process.isNumber(temporaryString)) {
                                                        switch (temporaryString) {
                                                            case "1" -> {
                                                                count -= 1;
                                                                process.write(String.valueOf(count), loginAttempt);
                                                                System.out.print("RETRYING");
                                                                process.loading("short");
                                                            }
                                                            case "2" -> {
                                                                userCondition = false;
                                                                pinCondition = false;
                                                            }
                                                            case "3" -> {
                                                                userCondition = false;
                                                                pinCondition = false;
                                                                userLoggedIn = false;
                                                                isUser = false;
                                                                loginCondition = true;
                                                            }
                                                            case "4" -> {
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
                                                                    temporaryString = scanner.nextLine().trim();
                                                                    if (process.isNumber(temporaryString)) {
                                                                        switch (temporaryString) {
                                                                            case "1" -> {
                                                                                System.out.print("CHECKING");
                                                                                process.loading("long");
                                                                                if (process.checkEligibility()) {
                                                                                    do {
                                                                                        System.out.println("""
                                                                                                ┬ ┬┌─┐┬ ┬  ┌─┐┬─┐┌─┐  ┌─┐┬  ┬┌─┐┬┌┐ ┬  ┌─┐  ┌┬┐┌─┐  ┌─┐┬ ┬┌─┐┌┐┌┌─┐┌─┐  ┬ ┬┌─┐┬ ┬┬─┐  ┌─┐┬┌┐┌
                                                                                                └┬┘│ ││ │  ├─┤├┬┘├┤   ├┤ │  ││ ┬│├┴┐│  ├┤    │ │ │  │  ├─┤├─┤││││ ┬├┤   └┬┘│ ││ │├┬┘  ├─┘││││
                                                                                                 ┴ └─┘└─┘  ┴ ┴┴└─└─┘  └─┘┴─┘┴└─┘┴└─┘┴─┘└─┘   ┴ └─┘  └─┘┴ ┴┴ ┴┘└┘└─┘└─┘   ┴ └─┘└─┘┴└─  ┴  ┴┘└┘
                                                                                                """);
                                                                                        System.out.println("""
                                                                                                ┌┬┐┌─┐  ┬ ┬┌─┐┬ ┬  ┬ ┬┌─┐┌┐┌┌┬┐  ┌┬┐┌─┐  ┌─┐┬ ┬┌─┐┌┐┌┌─┐┌─┐  ┬┌┬┐  ┌┐┌┌─┐┬ ┬┌─┐
                                                                                                 │││ │  └┬┘│ ││ │  │││├─┤│││ │    │ │ │  │  ├─┤├─┤││││ ┬├┤   │ │   ││││ ││││ ┌┘
                                                                                                ─┴┘└─┘   ┴ └─┘└─┘  └┴┘┴ ┴┘└┘ ┴    ┴ └─┘  └─┘┴ ┴┴ ┴┘└┘└─┘└─┘  ┴ ┴   ┘└┘└─┘└┴┘ o\s
                                                                                                """);
                                                                                        System.out.println(": 1 : Reset Pin Now");
                                                                                        System.out.println(": 2 : Return to USER MENU");
                                                                                        System.out.println(": 3 : Return to LOGIN MENU");
                                                                                        System.out.print(">>>: ");
                                                                                        temporaryString = scanner.nextLine().trim();
                                                                                        if (process.isNumber(temporaryString)) {
                                                                                            switch (temporaryString) {
                                                                                                case "1" -> {
                                                                                                    process.resetPin();
                                                                                                    pinCondition = false;
                                                                                                    userCondition = false;
                                                                                                    userLoggedIn = false;
                                                                                                    loginCondition = false;
                                                                                                    isAdmin = false;
                                                                                                    isUser = true;
                                                                                                }
                                                                                                case "2" -> {
                                                                                                    userCondition = false;
                                                                                                    pinCondition = false;
                                                                                                    userLoggedIn = false;
                                                                                                    loginCondition = false;
                                                                                                }
                                                                                                case "3" -> {
                                                                                                    userCondition = false;
                                                                                                    pinCondition = false;
                                                                                                    userLoggedIn = false;
                                                                                                    loginCondition = true;
                                                                                                }
                                                                                                default -> System.out.println("""
                                                                                                     ┬ ┌┐┌┬  ┬┌─┐┬  ┬┌┬┐  ┌─┐┬ ┬┌─┐┬┌─┐┌─┐  ┬
                                                                                                     │ │││└┐┌┘├─┤│  │ ││  │  ├─┤│ │││  ├┤   │
                                                                                                     ┴ ┘└┘ └┘ ┴ ┴┴─┘┴─┴┘  └─┘┴ ┴└─┘┴└─┘└─┘  o
                                                                                                """);
                                                                                            }
                                                                                        }

                                                                                    } while (!temporaryString.equals("1") && !temporaryString.equals("2") && !temporaryString.equals("3"));
                                                                                }
                                                                                else {

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
                                                                                        temporaryString = scanner.nextLine().trim();
                                                                                        if (process.isNumber(temporaryString)) {
                                                                                            switch (temporaryString) {
                                                                                                case "1" -> {
                                                                                                    process.submitResetTicket();
                                                                                                    pinCondition = false;
                                                                                                    userCondition = false;
                                                                                                    userLoggedIn = false;
                                                                                                    loginCondition = false;
                                                                                                    isAdmin = false;
                                                                                                    isUser = true;
                                                                                                    System.out.print("SUBMITTING");
                                                                                                    process.loading("long");
                                                                                                    System.out.println("SUCCESSFULLY SUBMITTED RESET TICKET");
                                                                                                    System.out.print("RETURNING TO USER MENU");
                                                                                                    process.loading("short");
                                                                                                }
                                                                                                case "2" -> {
                                                                                                    userCondition = false;
                                                                                                    pinCondition = false;
                                                                                                    userLoggedIn = false;
                                                                                                    loginCondition = false;
                                                                                                    System.out.print("RETURNING TO USER MENU");
                                                                                                    process.loading("short");
                                                                                                }
                                                                                                case "3" -> {
                                                                                                    userCondition = false;
                                                                                                    pinCondition = false;
                                                                                                    userLoggedIn = false;
                                                                                                    loginCondition = true;
                                                                                                    System.out.print("RETURNING TO LOGIN MENU");
                                                                                                    process.loading("short");
                                                                                                }
                                                                                                default -> System.out.println("""
                                                                                                     ┬ ┌┐┌┬  ┬┌─┐┬  ┬┌┬┐  ┌─┐┬ ┬┌─┐┬┌─┐┌─┐  ┬
                                                                                                     │ │││└┐┌┘├─┤│  │ ││  │  ├─┤│ │││  ├┤   │
                                                                                                     ┴ ┘└┘ └┘ ┴ ┴┴─┘┴─┴┘  └─┘┴ ┴└─┘┴└─┘└─┘  o
                                                                                                """);
                                                                                            }
                                                                                        }
                                                                                    } while (!temporaryString.equals("1") && !temporaryString.equals("2") && !temporaryString.equals("3"));
                                                                                }
                                                                            }
                                                                            case "2" -> {
                                                                                userCondition = false;
                                                                                pinCondition = false;
                                                                                userLoggedIn = false;
                                                                                loginCondition = false;
                                                                                System.out.print("RETURNING TO USER MENU");
                                                                                process.loading("short");
                                                                            }
                                                                            case "3" -> {
                                                                                userCondition = false;
                                                                                pinCondition = false;
                                                                                userLoggedIn = false;
                                                                                loginCondition = true;
                                                                                System.out.print("RETURNING TO LOGIN MENU");
                                                                                process.loading("short");
                                                                            }
                                                                            default -> System.out.println("""
                                                                                ┬ ┌┐┌┬  ┬┌─┐┬  ┬┌┬┐  ┌─┐┬ ┬┌─┐┬┌─┐┌─┐  ┬
                                                                                │ │││└┐┌┘├─┤│  │ ││  │  ├─┤│ │││  ├┤   │
                                                                                ┴ ┘└┘ └┘ ┴ ┴┴─┘┴─┴┘  └─┘┴ ┴└─┘┴└─┘└─┘  o
                                                                            """);
                                                                        }
                                                                    }
                                                                } while (!temporaryString.equals("1") && !temporaryString.equals("2") && !temporaryString.equals("3"));
                                                            }
                                                            default -> System.out.println("""
                                                                ┬ ┌┐┌┬  ┬┌─┐┬  ┬┌┬┐  ┌─┐┬ ┬┌─┐┬┌─┐┌─┐  ┬
                                                                │ │││└┐┌┘├─┤│  │ ││  │  ├─┤│ │││  ├┤   │
                                                                ┴ ┘└┘ └┘ ┴ ┴┴─┘┴─┴┘  └─┘┴ ┴└─┘┴└─┘└─┘  o
                                                            """);
                                                        }
                                                    }
                                                    else {
                                                        System.out.println("""
                                                                ┬ ┌┐┌┬  ┬┌─┐┬  ┬┌┬┐  ┌─┐┬ ┬┌─┐┬┌─┐┌─┐  ┬
                                                                │ │││└┐┌┘├─┤│  │ ││  │  ├─┤│ │││  ├┤   │
                                                                ┴ ┘└┘ └┘ ┴ ┴┴─┘┴─┴┘  └─┘┴ ┴└─┘┴└─┘└─┘  o
                                                        """);
                                                    }
                                                } while (!temporaryString.equals("1") && !temporaryString.equals("2") && !temporaryString.equals("3") && !temporaryString.equals("4"));
                                            }
                                        }
                                    }
                                }
                                else {
                                    do {
                                        System.out.println("""
                                                ┬ ┬┌─┐┌─┐┬─┐  ┌┬┐┌─┐┌─┐┌─┐  ┌┐┌┌─┐┌┬┐  ┌─┐─┐ ┬┬┌─┐┌┬┐  ┬
                                                │ │└─┐├┤ ├┬┘   │││ │├┤ └─┐  ││││ │ │   ├┤ ┌┴┬┘│└─┐ │   │
                                                └─┘└─┘└─┘┴└─  ─┴┘└─┘└─┘└─┘  ┘└┘└─┘ ┴   └─┘┴ └─┴└─┘ ┴   o
                                                """);
                                        System.out.println(": 1 : Retry");
                                        System.out.println(": 2 : Return to USER MENU");
                                        System.out.println(": 3 : Return to LOGIN MENU");
                                        System.out.print(">>>: ");
                                        temporaryString = scanner.nextLine().trim();
                                        if (process.isNumber(temporaryString)) {
                                            switch (temporaryString) {
                                                case "1" -> {
                                                    System.out.print("RETRYING");
                                                    process.loading("long");
                                                }
                                                case "2" -> {
                                                    userCondition = false;
                                                    pinCondition = false;
                                                    userLoggedIn = false;
                                                }
                                                case "3" -> {
                                                    userCondition = false;
                                                    pinCondition = false;
                                                    userLoggedIn = false;
                                                    isUser = false;
                                                    loginCondition = true;
                                                }
                                                default -> System.out.println("""
                                                   ┬ ┌┐┌┬  ┬┌─┐┬  ┬┌┬┐  ┌─┐┬ ┬┌─┐┬┌─┐┌─┐  ┬
                                                   │ │││└┐┌┘├─┤│  │ ││  │  ├─┤│ │││  ├┤   │
                                                   ┴ ┘└┘ └┘ ┴ ┴┴─┘┴─┴┘  └─┘┴ ┴└─┘┴└─┘└─┘  o
                                                """);
                                            }
                                        }
                                        else {
                                            System.out.println("""
                                               ┬ ┌┐┌┬  ┬┌─┐┬  ┬┌┬┐  ┌─┐┬ ┬┌─┐┬┌─┐┌─┐  ┬
                                               │ │││└┐┌┘├─┤│  │ ││  │  ├─┤│ │││  ├┤   │
                                               ┴ ┘└┘ └┘ ┴ ┴┴─┘┴─┴┘  └─┘┴ ┴└─┘┴└─┘└─┘  o
                                            """);
                                        }
                                    } while (!temporaryString.equals("1") && !temporaryString.equals("2") && !temporaryString.equals("3"));
                                }
                            }
                            else {
                                System.out.println("""
                                    ┌─┐┬  ┌─┐┌─┐┌─┐┌─┐  ┌─┐┌┐┌┌┬┐┌─┐┬─┐  ┬ ┬┌─┐┬ ┬┬─┐  ┬ ┬┌─┐┌─┐┬─┐┌┐┌┌─┐┌┬┐┌─┐  ┌─┐┬─┐┌─┐┌─┐┌─┐┬─┐┬  ┬ ┬
                                    ├─┘│  ├┤ ├─┤└─┐├┤   ├┤ │││ │ ├┤ ├┬┘  └┬┘│ ││ │├┬┘  │ │└─┐├┤ ├┬┘│││├─┤│││├┤   ├─┘├┬┘│ │├─┘├┤ ├┬┘│  └┬┘
                                    ┴  ┴─┘└─┘┴ ┴└─┘└─┘  └─┘┘└┘ ┴ └─┘┴└─   ┴ └─┘└─┘┴└─  └─┘└─┘└─┘┴└─┘└┘┴ ┴┴ ┴└─┘  ┴  ┴└─└─┘┴  └─┘┴└─┴─┘ ┴\s
                                """);
                            }
                        }
                    }
                    case "2" -> {
                        process.createUserAccount();
                        System.out.print("CREATING YOUR ACCOUNT");
                        process.loading("long");
                        System.out.println("SUCCESSFULLY CREATED (!)");
                        System.out.print("RETURNING TO LOGIN MENU");
                        process.loading("short");
                        createAccountCondition = true;
                    }
                    case "3" -> {
                        userLoggedIn = false;
                        loginCondition = true;
                    }
                }
                while (userLoggedIn) { // if signed is a user
                    System.out.println(": 1 : Show details");
                    System.out.println(": 2 : return to user menu");
                    System.out.println(": 3 : return to login menu");
                    System.out.print(">>>: ");
                    temporaryString = scanner.nextLine().trim();
                    switch (temporaryString) {
                        case "1" -> process.showUserDetails();
                        case "2" -> {
                            System.out.print("RETURNING TO USER MENU");
                            process.loading("short");
                            userLoggedIn = false;
                            loginCondition = false;
                            isUser = true;
                        }
                        case "3" -> {
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
                            System.out.print("RETURNING TO USER MENU");
                            process.loading("short");
                        }
                    }
                }
            }
        }
    }
}
