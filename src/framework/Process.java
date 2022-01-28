package framework;

import mainActivity.Main;

import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;
import static pinGenerator.PinGenerator.generatePin;

public class Process {
    // TODO make the admin view user accounts and view tickets
    private String userName;
    private String pin;
    static final Hashtable<String, Boolean> isEligibleToChangePin = new Hashtable<>();
    static final Hashtable<String, Boolean> resetPinTickets = new Hashtable<>();
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
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write(whatToWrite);
        writer.close();

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
        resetPinTickets.put(getUserName(), true);
        Ticketing ticketing = new Ticketing();
        ticketing.submitResetTicket();
    }

    public void showUserDetails() {
        // TODO add show details descriptions
        System.out.println("""
            ┬ ┬┌─┐┌─┐┬─┐  ┌─┐┌─┐┬  ┌─┐┌─┐┌┬┐┬┌─┐┌┐┌
            │ │└─┐├┤ ├┬┘  └─┐├┤ │  ├┤ │   │ ││ ││││
            └─┘└─┘└─┘┴└─  └─┘└─┘┴─┘└─┘└─┘ ┴ ┴└─┘┘└┘
        """);
    }
    public void showAdminDetails() throws InterruptedException {
        System.out.println("""
            ┌─┐┌┬┐┌┬┐┬ ┌┐┌  ┌─┐┌─┐┬  ┌─┐┌─┐┌┬┐┬┌─┐┌┐┌
            ├─┤ ││││││ │││  └─┐├┤ │  ├┤ │   │ ││ ││││
            ┴ ┴ ┴┘┴ ┴┴ ┘└┘  └─┘└─┘┴─┘└─┘└─┘ ┴ ┴└─┘┘└┘
        """);
        System.out.println(": 1 : View Users");
        System.out.println(": 2 : View tickets");
        System.out.println(": 3 : return to ADMIN menu");
        System.out.println(": 4 : return to LOGIN menu");
        System.out.print(">>>: ");
        Main.temporaryString = Main.scanner.nextLine().trim();
        switch (Main.temporaryString) {
            case "1" -> {
                // TODO view users
                System.out.println();
                showAdminDetails();
            }
            case "2" -> {
                Ticketing ticketing = new Ticketing();
                ticketing.editEligibility(Main.isAdmin);
                showAdminDetails();
            }
            case "3" -> {
                Main.adminLoggedIn = false;
                Main.loginCondition = false;
                Main.isAdmin = true;
                System.out.print("LOGGING OUT");
                loading("long");
                System.out.println("SUCCESSFULLY LOGGED OUT");
                System.out.print("RETURNING TO ADMIN MENU");
                loading("short");
            }
            case "4" -> {
                resetReturningToLoginMenu();
                System.out.print("RETURNING TO LOGIN MENU");
                loading("short");
            }
            default -> {
                System.out.println("""
                    ┬ ┌┐┌ ┬  ┬┌─┐┬  ┬┌┬┐  ┌─┐┬ ┬┌─┐┬┌─┐┌─┐  ┬
                    │ │││ └┐┌┘├─┤│  │ ││  │  ├─┤│ │││  ├┤   │
                    ┴ ┘└┘  └┘ ┴ ┴┴─┘┴─┴┘  └─┘┴ ┴└─┘┴└─┘└─┘  o
                """);
                System.out.print("LOADING");
                loading("short");
                showAdminDetails();
            }
        }
    }
    public void createUserAccount() throws IOException, InterruptedException {
        System.out.print("ENTER USERNAME: ");
        Main.temporaryString = Main.scanner.nextLine().trim();
        if (Main.temporaryString.matches("[a-zA-Z]+") || Main.temporaryString.matches("[a-zA-z0-9]+")) {
            setUserName(Main.temporaryString);
            File userAccountFolder = new File ("src\\" + "files\\" +  "accounts\\" + "user\\" + getUserName());
            boolean success = userAccountFolder.mkdirs();
            if (success) {
                char[] oneTimePin = generatePin();
                setPin(String.valueOf(oneTimePin));
                File userName = new File ("src\\" + "files\\" +"accounts\\" + "user\\" + getUserName() + "" + "\\username.txt");
                File userPin = new File ("src\\" + "files\\" +"accounts\\" + "user\\" + getUserName() +  "" + "\\pin.txt");
                File userLoginAttempt = new File ("src\\" + "files\\" + "accounts\\" + "user\\" + getUserName() +  "" + "\\loginAttempt.txt");
                write(getUserName(), userName);
                write(getPin(), userPin);
                if (getPin().length() == 6) {
                    write("4", userLoginAttempt); // 4 login attempts, if the user did not follow instructions carefully
                }
                else {
                    write("6", userLoginAttempt); // 6 login attempts
                }
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
    /*
     * Method that resets the pins of the user, if the user is eligible
     */
    public void resetPin() throws IOException, InterruptedException {
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
        Main.isAdmin = false;
        Main.userLoggedIn = false;
        Main.adminLoggedIn = false;
        Main.loginCondition = true;
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
                    String username = "";
                    String password = "";
                    boolean invalidUser = false;
                    System.out.print("ENTER USERNAME: ");
                    Main.temporaryString = Main.scanner.nextLine().trim();
                    setUserName(Main.temporaryString);
                    System.out.print("ENTER PASSWORD: ");
                    Main.temporaryString = Main.scanner.nextLine().trim();
                    setPin(Main.temporaryString);
                    if (isAdmin) {
                        checkUserName = new File ("src\\" + "files\\" + "accounts\\" + "admin\\" + "\\username.txt");
                        checkPassword = new File ("src\\" + "files\\" + "accounts\\" + "admin\\" + "\\password.txt");
                    }
                    else {
                        checkUserName = new File ("src\\" + "files\\" +"accounts\\" + "user\\" + getUserName() + "\\username.txt");
                        checkPassword = new File ("src\\" + "files\\" + "accounts\\" + "user\\" + getUserName() + "\\pin.txt");
                    }
                    try {
                        Scanner validateUserName = new Scanner(checkUserName);
                        username = validateUserName.nextLine();
                        Scanner validatePassword = new Scanner(checkPassword);
                        password =  validatePassword.nextLine();
                    }
                    catch (FileNotFoundException fNfE) {
                        invalidUser = true;
                    }
                    System.out.print("LOGGING IN");
                    loading("long");
                    if (getUserName().equals(username) && getPin().equals(password) && !invalidUser) {
                        File updateAttempt = new File ("src\\" + "files\\" + "accounts\\" + "user\\" + getUserName() + "\\loginAttempt.txt");
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
                            write("6", updateAttempt); // 6 login attempts
                        }
                        insertCredentials = false;
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
                                                File loginAttempt = new File ("src\\" + "files\\" + "accounts\\" + "user\\" + getUserName() + "\\loginAttempt.txt");
                                                Scanner loginCountUpdater = new Scanner(loginAttempt);
                                                int count = loginCountUpdater.nextInt();
                                                loginCountUpdater.close();
                                                count -= 1;
                                                write(String.valueOf(count), loginAttempt); // 6 login attempts
                                                System.out.printf("REMAINING LOGIN TRIES [ >> %d << ]\n", count);
                                                if (count == 0) {
                                                    Main.userLoggedIn = false;
                                                    Main.loginCondition = true;
                                                    resetReturningToLoginMenu();
                                                    System.out.println("""
                                                            WARNING!!! This account has reached the maximum login attempt.\s
                                                            The system thinks that this account does not belong to you. If this account belongs to you,
                                                            you can talk to the admin, bring your userName and request a new pin code.
                                                        """);
                                                    System.out.print("PROCEEDING TO LOGIN MENU");
                                                    loading("short");
                                                }

                                            }
                                            catch (FileNotFoundException ignored) {
                                            }
                                        }
                                        insertCredentials = true;
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
                                                                                System.out.println("""
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
                                                                File checkUser = new File ("src\\" + "files\\" +"accounts\\" + "user\\" + getUserName());
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
                                                                                    submitTicket();
                                                                                    insertCredentials = false;
                                                                                    Main.userLoggedIn = false;
                                                                                    Main.loginCondition = false;
                                                                                    Main.isAdmin = false;
                                                                                    System.out.print("SUBMITTING");
                                                                                    loading("long");
                                                                                    System.out.println("SUCCESSFULLY SUBMITTED RESET TICKET");
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
                                                                                    System.out.println("""
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
                                                                    System.out.println("""
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
                                                            System.out.println("""
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
                                            System.out.println("""
                                                 ┬ ┌┐┌ ┬  ┬┌─┐┬  ┬┌┬┐  ┌─┐┬ ┬┌─┐┬┌─┐┌─┐  ┬
                                                 │ │││ └┐┌┘├─┤│  │ ││  │  ├─┤│ │││  ├┤   │
                                                 ┴ ┘└┘  └┘ ┴ ┴┴─┘┴─┴┘  └─┘┴ ┴└─┘┴└─┘└─┘  o
                                            """);
                                            System.out.print("LOADING");
                                            loading("short");
                                        }
                                    }
                                    default -> {
                                        System.out.println("""
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
                                System.out.println("""
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
                    System.out.print("CREATING YOUR ACCOUNT");
                    loading("long");
                    System.out.println("SUCCESSFULLY CREATED (!)");
                    System.out.print("RETURNING TO LOGIN MENU");
                    loading("short");
                }
            }
            case "3" -> {
                if (!isAdmin) {
                    System.out.print("RETURNING TO LOGIN MENU");
                    loading("short");
                    resetReturningToLoginMenu();
                }
                else {
                    System.out.println("""
                        ┬ ┌┐┌ ┬  ┬┌─┐┬  ┬┌┬┐  ┌─┐┬ ┬┌─┐┬┌─┐┌─┐  ┬
                        │ │││ └┐┌┘├─┤│  │ ││  │  ├─┤│ │││  ├┤   │
                        ┴ ┘└┘  └┘ ┴ ┴┴─┘┴─┴┘  └─┘┴ ┴└─┘┴└─┘└─┘  o
                    """);
                    System.out.print("LOADING");
                    loading("short");
                }
            }
            default -> {
                System.out.println("""
                    ┬ ┌┐┌ ┬  ┬┌─┐┬  ┬┌┬┐  ┌─┐┬ ┬┌─┐┬┌─┐┌─┐  ┬
                    │ │││ └┐┌┘├─┤│  │ ││  │  ├─┤│ │││  ├┤   │
                    ┴ ┘└┘  └┘ ┴ ┴┴─┘┴─┴┘  └─┘┴ ┴└─┘┴└─┘└─┘  o
                """);
                System.out.print("LOADING");
                loading("short");
            }
        }
        while (Main.adminLoggedIn) { // if signed is a user
            System.out.println(": 1 : Show details");
            System.out.println(": 2 : return to ADMIN menu");
            System.out.println(": 3 : return to LOGIN menu");
            System.out.print(">>>: ");
            Main.temporaryString = Main.scanner.nextLine().trim();
            switch (Main.temporaryString) {
                case "1" -> {
                    System.out.print("LOADING");
                    loading("short");
                    showAdminDetails();
                }
                case "2" -> {
                    System.out.print("LOGGING OUT");
                    loading("long");
                    System.out.println("SUCCESSFULLY LOGGED OUT");
                    System.out.print("RETURNING TO ADMIN MENU");
                    loading("short");
                    Main.adminLoggedIn = false;
                    Main.loginCondition = false;
                    Main.isAdmin = true;
                }
                case "3" -> {
                    System.out.print("LOGGING OUT");
                    loading("long");
                    System.out.println("SUCCESSFULLY LOGGED OUT");
                    System.out.print("RETURNING TO LOGIN MENU");
                    loading("short");
                    resetReturningToLoginMenu();
                }
                default -> {
                    System.out.println("""
                        ┬ ┌┐┌ ┬  ┬┌─┐┬  ┬ ┌┬┐  ┌─┐┬ ┬┌─┐┬┌─┐┌─┐  ┬
                        │ │││ └┐┌┘├─┤│  │  ││  │  ├─┤│ │││  ├┤   │
                        ┴ ┘└┘  └┘ ┴ ┴┴─┘┴ ─┴┘  └─┘┴ ┴└─┘┴└─┘└─┘  o
                    """);
                    System.out.print("LOADING");
                    loading("short");
                }
            }
        }
    }
}
