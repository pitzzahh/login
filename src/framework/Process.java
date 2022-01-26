package framework;

import mainActivity.Main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;
import java.util.concurrent.TimeUnit;
import static pinGenerator.PinGenerator.generatePin;

public class Process {
    // TODO create a simple login program
    // TODO allow anyone to create a username and password
    // TODO store the password in a file
    // TODO when the user tries to login, check if the password matches the password stored in the file

    private String userName;
    private String pin;

    private static boolean changePin = false;
    private static Hashtable<String, Boolean> isEligibleToChangePin = new Hashtable<>();
    private static Hashtable<String, Boolean> resetPinTickets = new Hashtable<>();

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
    public void write(String writer, File file) throws IOException {
        FileWriter fileWriter = new FileWriter(file);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.println(writer);
        printWriter.close();
    }
    public boolean isNumber(String numberString) {
        try {
            Byte.parseByte(numberString);
            return true;
        }
        catch (NumberFormatException ignored) {
        }
        return false;
    }
    public boolean checkEligibility(String userName) {
        try {
            boolean check = isEligibleToChangePin.get(userName);
            if (check) {
                return true;
            }
        }
        catch (NullPointerException ignored) {}
        return false;
    }
    public void submitResetTicket(String userName) {
        resetPinTickets.put(userName, true);
    }
    public void showUserDetails() {
        // TODO add show details descriptions
        System.out.println("NAME HERE");
    }
    public void createUserAccount() throws IOException {
        while (Main.createAccountCondition) {
            System.out.print("Enter username: ");
            Main.temporaryString = Main.scanner.nextLine().trim();
            if (Main.temporaryString.matches("[a-zA-Z]+") || Main.temporaryString.matches("[a-zA-z0-9]+")) {
                userName = Main.temporaryString;
                File userAccountFolder = new File ("src\\" + "accounts\\" + "user\\" + userName);
                boolean success = userAccountFolder.mkdirs();
                if (success) {
                    char[] oneTimePin = generatePin();
                    pin = String.valueOf(oneTimePin);
                    File userPin = new File ("src\\" + "accounts\\" + "user\\" + userName + "\\pin.txt");
                    File userLoginAttempt = new File ("src\\" + "accounts\\" + "user\\" + userName + "\\loginAttempt.txt");
                    write(pin, userPin);
                    if (pin.length() == 6) {
                        write("4", userLoginAttempt); // 4 login attempts, if the user did not follow instructions carefully
                    }
                    else {
                        write("6", userLoginAttempt); // 6 login attempts
                    }
                    Main.createAccountCondition = false;
                    Main.userLoggedIn = false;
                    Main.loginCondition = true;
                }
                else {
                    System.out.println("""
                            ┬ ┬┌─┐┌─┐┬─┐┌┐┌┌─┐┌┬┐┌─┐  ┌─┐┬  ┬─┐┌─┐┌─┐┌┬┐┬ ┬  ┌─┐─┐ ┬┬┌─┐┌┬┐┌─┐┌┬┐        \s
                            │ │└─┐├┤ ├┬┘│││├─┤│││├┤   ├─┤│  ├┬┘├┤ ├─┤ ││└┬┘  ├┤ ┌┴┬┘│└─┐ │ ├┤  ││        \s
                            └─┘└─┘└─┘┴└─┘└┘┴ ┴┴ ┴└─┘  ┴ ┴┴─┘┴└─└─┘┴ ┴─┴┘ ┴   └─┘┴ └─┴└─┘ ┴ └─┘─┴┘        \s
                            ┌─┐┬  ┌─┐┌─┐┌─┐┌─┐  ┌┬┐┬─┐┬ ┬  ┌─┐┌┐┌┌─┐┌┬┐┬ ┬┌─┐┬─┐  ┬ ┬┌─┐┌─┐┬─┐┌┐┌┌─┐┌┬┐┌─┐
                            ├─┘│  ├┤ ├─┤└─┐├┤    │ ├┬┘└┬┘  ├─┤││││ │ │ ├─┤├┤ ├┬┘  │ │└─┐├┤ ├┬┘│││├─┤│││├┤\s
                            ┴  ┴─┘└─┘┴ ┴└─┘└─┘   ┴ ┴└─ ┴   ┴ ┴┘└┘└─┘ ┴ ┴ ┴└─┘┴└─  └─┘└─┘└─┘┴└─┘└┘┴ ┴┴ ┴└─┘
                    """);
                }
            }
        }
    }
    /*
     * Method that resets the pins of the user, if the user is eligible
     */
    public void resetPin(boolean isPermitted) throws IOException {
        if (checkEligibility(userName)) {
            File changePinCode = new File ("src\\" + "accounts\\" + "user\\" + userName + "\\pin.txt");
            File updateAttempt = new File ("src\\" + "accounts\\" + "user\\" + userName + "\\loginAttempt.txt");
            char[] oneTimePin = generatePin();
            pin = String.valueOf(oneTimePin);
            System.out.println("[" + pin + "] is your new pin code " + userName);
            write(pin, changePinCode);
            write("4", updateAttempt);
        }
    }
    /*
     * Method that prints a tailing dot like it was loading
     */
    public void loading(String delay) throws InterruptedException {
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
        System.out.println("\n");
    }
    /*
     *
     */
    public void resetReturningToLoginMenu() {
        Main.userLoggedIn = false;
        Main.loginCondition = true;
        Main.isAdmin = false;
    }
}
