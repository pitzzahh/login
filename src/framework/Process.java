package framework;

import mainActivity.Main;

import java.io.*;
import java.util.Hashtable;
import java.util.Map;
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
    static Hashtable<String, Boolean> resetPinTickets = new Hashtable<>();
    public Process() {
        super();
    }

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
    public void write(String whatToWrite, File file) throws IOException {
        FileWriter fileWriter = new FileWriter(file);
        PrintWriter printWriter = new PrintWriter(fileWriter);

        printWriter.print(whatToWrite);
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
    public boolean checkEligibility() {
        try {
            boolean check = isEligibleToChangePin.get(getUserName());
            if (check) {
                return true;
            }
        }
        catch (NullPointerException ignored) {}
        return false;
    }
    public void submitTicket() throws IOException {
        Ticketing ticketing = new Ticketing();
        ticketing.submitResetTicket();
    }

    public void showUserDetails() {
        // TODO add show details descriptions
        System.out.println("NAME HERE");
    }
    public void createUserAccount() throws IOException {
        while (Main.createAccountCondition) {
            System.out.print("ENTER USERNAME: ");
            Main.temporaryString = Main.scanner.nextLine().trim();
            if (Main.temporaryString.matches("[a-zA-Z]+") || Main.temporaryString.matches("[a-zA-z0-9]+")) {
                setUserName(Main.temporaryString);
                File userAccountFolder = new File ("src\\" + "files\\" +  "accounts\\" + "user\\" + getUserName());
                boolean success = userAccountFolder.mkdirs();
                if (success) {
                    char[] oneTimePin = generatePin();
                    setPin(String.valueOf(oneTimePin));
                    File userPin = new File ("src\\" + "files\\" +"accounts\\" + "user\\" + getUserName() + "\\pin.txt");
                    File userLoginAttempt = new File ("src\\" + "files\\" + "accounts\\" + "user\\" + getUserName() + "\\loginAttempt.txt");
                    write(getPin(), userPin);
                    if (getPin().length() == 6) {
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
    public void resetPin() throws IOException {
        if (checkEligibility()) {
            File changePinCode = new File ("src\\" + "files\\" + "accounts\\" + "user\\" + getUserName() + "\\pin.txt");
            File updateAttempt = new File ("src\\" + "files\\" + "accounts\\" + "user\\" + getUserName() + "\\loginAttempt.txt");
            char[] oneTimePin = generatePin();
            setPin(String.valueOf(oneTimePin));
            System.out.println("[" + getPin() + "] is your new pin code " + getUserName());
            write(getPin(), changePinCode);
            if (getPin().length() == 6) {
                write("4", updateAttempt); // 4 login attempts, if the user did not follow instructions carefully
            }
            else {
                write("6", updateAttempt); // 6 login attempts
            }
            Main.createAccountCondition = false;
            Main.userLoggedIn = false;
            Main.loginCondition = true;
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
        System.out.println();
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
