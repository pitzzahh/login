package mainActivity;

import framework.Process;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import static pinGenerator.PinGenerator.generatePin;
public class Main {

    static String userName;
    public static String pin;
    public static String temporaryString;

    // when logging in as admin or user
    static boolean isAdmin = false;
    static boolean isUser = false;
    static boolean isExit = false;
    static boolean loginCondition = true;

    // when user is selected
    static boolean userLoggedIn = true;

    // when creating account
    static boolean createAccountCondition = true;

    static Scanner scanner = new Scanner(System.in);
    static Process process = new Process();
    public static void main(String[] args) throws InterruptedException, IOException {

        while (!isExit) {
            while (loginCondition) {
                System.out.println("WELCOME TO SIMPLE LOGIN PROGRAM");
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
                        System.out.println("THANK YOU FOR USING MY PROGRAM");
                    }
                    default -> {
                        System.out.println("INVALID CHOICE");
                        System.out.print("RETURNING TO LOGIN MENU");
                        loading();
                    }
                }
            }
            if (isAdmin) {
                // TODO admin can delete users and can see users usernames and passwords
                System.out.println("ADMIN HERE");
                loginCondition = true;
            }
            else if (isUser) {
                System.out.println("USER MENU");
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
                                userName = temporaryString;
                                File user = new File ("src\\" + "accounts\\" + "user\\" + userName);
                                if (user.exists()) {
                                    while (pinCondition) {
                                        File loginAttempt = new File ("src\\" + "accounts\\" + "user\\" + userName + "\\loginAttempt.txt");
                                        Scanner updater = new Scanner(loginAttempt);
                                        int count = updater.nextInt();
                                        updater.close();
                                        if (count == 0) {
                                            System.out.println("""
                                                    WARNING!!! This account has reached the maximum login attempt.\s
                                                    The system thinks that this account does not belong to you. If this account belongs to you,
                                                    you can talk to the admin, bring your ID and request a new pin code.
                                                    """);
                                            System.out.print("Proceeding to Student's Menu");
                                            loading();
                                        }
                                        if (count != 0) {
                                            System.out.println("Login Attempts [ " + count +" ]");
                                            System.out.print("ENTER PIN: ");
                                            temporaryString = scanner.nextLine().trim();
                                            pin = temporaryString;
                                            File checkPin = new File ("src\\" + "accounts\\" + "user\\" + userName + "\\pin.txt");
                                            Scanner validatePin = new Scanner(checkPin);
                                            String pinCheck =  validatePin.nextLine();
                                            System.out.print("LOGGING IN");
                                            loading();
                                            if (pin.equals(pinCheck)) {
                                                System.out.println("LOGGED IN!");
                                                userCondition = false;
                                                pinCondition = false;
                                                userLoggedIn = true;
                                            }
                                            else {
                                                System.out.println("| INVALID PASSWORD |");
                                                System.out.println(": 1 : Retry");
                                                System.out.println(": 2 : Return to USER MENU");
                                                System.out.println(": 3 : Return to LOGIN MENU");
                                                System.out.print(">>>: ");
                                                temporaryString = scanner.nextLine().trim();
                                                if (process.isNumber(temporaryString)) {
                                                    switch (temporaryString) {
                                                        case "1" -> {
                                                            count -= 1;
                                                            process.write(String.valueOf(count), loginAttempt);
                                                            System.out.print("RETRYING");
                                                            loading();
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
                                                        default -> System.out.println("INVALID CHOICE (!)");
                                                    }
                                                }
                                                else {
                                                    System.out.println("INVALID CHOICE (!)");
                                                }
                                            }
                                        }
                                    }
                                }
                                else {
                                    System.out.println("| USER DOES NOT EXIST |");
                                    System.out.println(": 1 : Retry");
                                    System.out.println(": 2 : Return to USER MENU");
                                    System.out.println(": 3 : Return to LOGIN MENU");
                                    System.out.print(">>>: ");
                                    temporaryString = scanner.nextLine().trim();
                                    if (process.isNumber(temporaryString)) {
                                        switch (temporaryString) {
                                            case "1" -> {
                                                System.out.print("RETRYING");
                                                loading();
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
                                            default -> System.out.println("INVALID CHOICE (!)");
                                        }
                                    }
                                    else {
                                        System.out.println("INVALID CHOICE (!)");
                                    }
                                }
                            }
                            else {
                                System.out.println("PLEASE ENTER YOUR USERNAME PROPERLY");
                            }
                        }
                    }
                    case "2" -> {
                        createUserAccount();
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
                        case "1" -> showUserDetails();
                        case "2" -> {
                            System.out.print("RETURNING TO USER MENU");
                            loading();
                            userLoggedIn = false;
                            loginCondition = false;
                            isUser = true;
                        }
                        case "3" -> {
                            System.out.print("RETURNING TO LOGIN MENU");
                            loading();
                            resetReturningToLoginMenu();
                        }
                        default -> {
                            System.out.println("INVALID CHOICE");
                            System.out.print("RETURNING TO USER MENU");
                            loading();
                        }
                    }
                }
            }
        }
    }
    protected static void showUserDetails() {
        // TODO add show details descriptions
        System.out.println("NAME HERE");
    }
    protected static void createUserAccount() throws IOException {
        while (createAccountCondition) {
            System.out.print("Enter username: ");
            temporaryString = scanner.nextLine().trim();
            if (temporaryString.matches("[a-zA-Z]+") || temporaryString.matches("[a-zA-z0-9]+")) {
                userName = temporaryString;
                File userAccountFolder = new File ("src\\" + "accounts\\" + "user\\" + userName);
                boolean success = userAccountFolder.mkdirs();
                if (success) {
                    char[] oneTimePin = generatePin();
                    pin = String.valueOf(oneTimePin);
                    File userPin = new File ("src\\" + "accounts\\" + "user\\" + userName + "\\pin.txt");
                    File userLoginAttempt = new File ("src\\" + "accounts\\" + "user\\" + userName + "\\loginAttempt.txt");
                    process.write(pin, userPin);
                    if (pin.length() == 6) {
                        process.write("2", userLoginAttempt); // 2 login attempts, if the user did not follow instructions carefully
                    }
                    else {
                        process.write("4", userLoginAttempt); // 4 login attempts
                    }
                    createAccountCondition = false;
                    userLoggedIn = false;
                    loginCondition = true;
                }
                else {
                    System.out.println("USERNAME ALREADY EXISTED\nPLEASE TRY OTHER USERNAMES");
                }
            }
        }
    }
    public static void loading() throws InterruptedException {
        for (int i = 1; i <= 3; i++) {
            TimeUnit.MILLISECONDS.sleep(400);
            System.out.print('.');
        }
        System.out.println("\n");
    }
    protected static void resetReturningToLoginMenu() {
        userLoggedIn = false;
        loginCondition = true;
        isAdmin = false;
    }
}
