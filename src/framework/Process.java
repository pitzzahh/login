package framework;

import lib.computing.daysBetweenDates.withLeapYear.RemainingDaysWithLeapYear;
import lib.computing.algorithms.recursion.sumOfAllNumbers.SumOfAllNumbers;
import lib.computing.daysBetweenDates.noLeapYear.RemainingDaysNoLearYear;
import lib.games.guessingGames.myFirstGuessingGame.MyFirstGuessingGame;
import lib.games.ticTacToe.ticTacToeWithMiniMaxAlgorithm.TicTacToe;
import lib.computing.conversion.centimeter.CentimeterConversion;
import lib.computing.statistics.MeanMedianModeStandardDeviation;
import lib.games.ticTacToe.simpleTicTacToe.SimpleTicTacToeGame;
import lib.computing.algorithms.recursion.factorial.Factorial;
import lib.computing.algorithms.recursion.fibonacci.Fibonacci;
import lib.systems.studentManagementSystem.main.MainActivity;
import lib.games.rockPaperScissors.normal.RockPaperScissors;
import lib.computing.algorithms.sorting.quickSort.QuickSort;
import lib.computing.calculateHypotenuse.HypotenuseComputer;
import lib.games.mathProblemGenerator.MathProblemGenerator;
import lib.games.guessingGames.guessTheWords.GuessTheWord;
import lib.computing.quadrantAnalyzer.QuadrantAnalyzer;
import lib.computing.conversion.meter.MeterConversion;
import lib.computing.calculator.neat.Calculator;
import lib.computing.collatzConjecture.Collatz;
import org.apache.commons.io.FilenameUtils;
import lib.games.headAndTails.HeadAndTails;
import lib.utilities.misc.PinGenerator;
import lib.utilities.misc.InputChecker;
import org.apache.commons.io.FileUtils;
import lib.utilities.misc.Decorations;
import lib.utilities.misc.Loading;
import lib.utilities.SecurityUtil;
import lib.utilities.FileUtil;
import javax.crypto.SecretKey;
import javax.crypto.Cipher;
import mainActivity.Main;
import java.util.*;
import java.io.*;

public class Process {
    private String userName;
    private String pin;
    private static SecretKey key;
    private static Cipher cipher;

    // computing
    private static boolean computeFactorial;
    private static boolean computeFibonacci;
    private static boolean computeSumOfAllNumbers;
    private static boolean sort;
    private static boolean computeHypotenuse;
    private static boolean runCalculator;
    private static boolean computeCollatzConjecture;
    private static boolean centimeterConversion;
    private static boolean meterConversion;
    private static boolean daysBetweenDates;
    private static boolean quadrantAnalyzer;
    private static boolean meanMedianModeSd;

    // games
    private static boolean playGuessingGame;
    private static boolean playHeadAndTails;
    private static boolean mathProblemGame;
    private static boolean playRockPaperScissors;
    private static boolean playTicTacToe;

    // systems
    private static boolean runStudentManagementSystem;

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
    public void allAdminAndUserMenu(boolean isAdmin) throws Exception {
        if (isAdmin) {
            System.out.println(
                    Decorations.TEXT_BLUE +
                    " ┌─┐┌┬┐ ┌┬┐ ┬ ┌┐┌  ┌┬┐┌─┐┌┐┌ ┬ ┬\n" +
                    " ├─┤ ││ │││ │ │││  │││├┤ │││ │ │\n" +
                    " ┴ ┴ ┴┘ ┴ ┴ ┴ ┘└┘  ┴ ┴└─┘┘└┘ └─┘"   +
                    Decorations.TEXT_RESET);
            System.out.println(Decorations.TEXT_YELLOW + ": 1 : Sign in" + Decorations.TEXT_RESET);
            System.out.println(Decorations.TEXT_GREEN  + ": 2 : return to LOGIN menu" + Decorations.TEXT_RESET);
        }
        else {
            System.out.println(
                    Decorations.TEXT_GREEN +
                    " ┬ ┬┌─┐┌─┐┬─┐  ┌┬┐┌─┐┌┐┌ ┬ ┬\n" +
                    " │ │└─┐├┤ ├┬┘  │││├┤ │││ │ │\n" +
                    " └─┘└─┘└─┘┴└─  ┴ ┴└─┘┘└┘ └─┘"   +
                    Decorations.TEXT_RESET);
            System.out.println(Decorations.TEXT_YELLOW + ": 1 : Sign in" + Decorations.TEXT_RESET);
            System.out.println(Decorations.TEXT_PURPLE + ": 2 : create account" + Decorations.TEXT_RESET);
            System.out.println(Decorations.TEXT_GREEN  + ": 2 : return to LOGIN menu" + Decorations.TEXT_RESET);
        }
        System.out.print(Decorations.TEXT_YELLOW  + ">>>: " + Decorations.TEXT_RESET);
        Main.temporaryString = Main.scanner.nextLine().trim();
        switch (Main.temporaryString) {
            case "1" -> {
                boolean insertCredentials = true;
                while (insertCredentials) {
                    SecretKey checkUserName = null;
                    SecretKey checkPassword = null;
                    File loginAttempt = null;
                    Scanner loginCountUpdater = null;
                    String loginCount = "";
                    boolean isWrongUserName = false;
                    boolean isWrongPassword = false;
                    System.out.print(Decorations.TEXT_GREEN + "ENTER USERNAME: " + Decorations.TEXT_RESET);
                    Main.temporaryString = Main.scanner.nextLine().trim();
                    setUserName(Main.temporaryString);
                    System.out.print(Decorations.TEXT_GREEN + "ENTER PASSWORD: " + Decorations.TEXT_RESET);
                    Main.temporaryString = Main.scanner.nextLine().trim();
                    setPin(Main.temporaryString);
                    if (isAdmin) {
                        try {
                            checkUserName = SecurityUtil.AES.loadFromKeyStore(getUserName(), "src\\files\\accounts\\admin\\credentials\\username.keystore");
                        } catch (Exception e) {
                            isWrongUserName = true;
                        }
                        try {
                            checkPassword = SecurityUtil.AES.loadFromKeyStore(getPin(), "src\\files\\accounts\\admin\\credentials\\password.keystore");
                        } catch (Exception e) {
                            isWrongPassword = true;
                        }
                    } else {
                        try {
                            checkUserName = SecurityUtil.AES.loadFromKeyStore(getUserName(), "src\\files\\accounts\\user\\" + getUserName() + "'s Folder\\credentials\\username.keystore");
                        } catch (Exception e) {
                            isWrongUserName = true;
                        }
                        try {
                            checkPassword = SecurityUtil.AES.loadFromKeyStore(getPin(), "src\\files\\accounts\\user\\" + getUserName() + "'s Folder\\credentials\\password.keystore");
                        } catch (Exception e) {
                            isWrongPassword = true;
                        }
                        if (!isWrongUserName && !isWrongPassword) {
                            loginAttempt = new File("src\\files\\accounts\\user\\" + getUserName() + "'s Folder\\loginAttempts\\remainingAttempts.txt");
                            loginCountUpdater = new Scanner(loginAttempt);
                            loginCount = loginCountUpdater.nextLine();
                        }
                    }
                    String decryptedUsername = "";
                    String decryptedPassword = "";
                    if (!isWrongUserName && !isWrongPassword) {
                        cipher = Cipher.getInstance("AES");
                        byte[] decryptedUsernameData = SecurityUtil.AES.encrypt(getUserName(), checkUserName, cipher);
                        byte[] decryptedPinData = SecurityUtil.AES.encrypt(getPin(), checkPassword, cipher);
                        decryptedUsername = SecurityUtil.AES.decrypt(decryptedUsernameData, checkUserName, cipher);
                        decryptedPassword = SecurityUtil.AES.decrypt(decryptedPinData, checkPassword, cipher);
                    }
                    System.out.print(Decorations.TEXT_YELLOW + "LOGGING IN" + Decorations.TEXT_RESET);
                    Loading.dotLoading("long");
                    if ((decryptedUsername.equals(getUserName()) && decryptedPassword.equals(getPin())) && !loginCount.equals("0") && (!isWrongUserName && !isWrongPassword)) {
                        try {
                            System.out.println(
                                    Decorations.TEXT_BLUE +
                                    " ┬  ┌─┐┌─┐┌─┐┌─┐┌┬┐  ┬ ┌┐┌  ┬\n" +
                                    " │  │ ││ ┬│ ┬├┤  ││  │ │││  │\n" +
                                    " ┴─┘└─┘└─┘└─┘└─┘─┴┘  ┴ ┘└┘  o"   +
                                    Decorations.TEXT_RESET);
                            if (isAdmin) {
                                Main.adminLoggedIn = true;
                                Main.account = Decorations.TEXT_BLUE + "ADMIN" + Decorations.TEXT_RESET;
                            } else {
                                Main.userLoggedIn = true;
                                File updateAttempt = new File("src\\files\\accounts\\user\\" + getUserName() + "'s Folder\\loginAttempts\\remainingAttempts.txt");
                                FileUtil.writeToATextFile("6", updateAttempt); // reset to 6 login attempts if the user finally logged in
                                Main.account = Decorations.TEXT_GREEN + "USER" + Decorations.TEXT_RESET;
                            }
                            insertCredentials = false;
                        } catch (FileNotFoundException ignored) {
                        }
                    } else if (loginCount.equals("0")) {
                        Main.userLoggedIn = false;
                        Main.loginCondition = true;
                        insertCredentials = false;
                        System.out.println(
                                Decorations.TEXT_RED +
                                " WARNING!!! This account has reached the maximum login attempt.s\n" +
                                " The system thinks that this account does not belong to you. If this account belongs to you,\n" +
                                " you can talk to the admin, bring your userName and request a new pin code." +
                                Decorations.TEXT_RESET);
                        System.out.print(Decorations.TEXT_GREEN + "PROCEEDING TO LOGIN MENU" + Decorations.TEXT_RESET);
                        Loading.dotLoading("short");
                        Decorations.pressEnterToContinue();
                        Main.scanner.nextLine();
                        resetReturningToLoginMenu();
                    }
                    else {
                        do {
                            System.out.println(
                                    Decorations.TEXT_RED +
                                    " ┬ ┬┬─┐┌─┐┌┐┌┌─┐  ┬ ┬┌─┐┌─┐┬─┐┌┐┌┌─┐┌┬┐┌─┐\n" +
                                    " │││├┬┘│ │││││ ┬  │ │└─┐├┤ ├┬┘│││├─┤│││├┤ \n" +
                                    " └┴┘┴└─└─┘┘└┘└─┘  └─┘└─┘└─┘┴└─┘└┘┴ ┴┴ ┴└─┘\n" +
                                    "               ┌─┐┬─┐                     \n" +
                                    "               │ │├┬┘                     \n" +
                                    "               └─┘┴└─                     \n" +
                                    " ┌─┐┌─┐┌─┐┌─┐┬ ┬┌─┐┬─┐┌┬┐  ┬              \n" +
                                    " ├─┘├─┤└─┐└─┐││││ │├┬┘ ││  │              \n" +
                                    " ┴  ┴ ┴└─┘└─┘└┴┘└─┘┴└──┴┘  o               "  +
                                    Decorations.TEXT_RESET);
                            if (!isAdmin) {
                                System.out.println(Decorations.TEXT_YELLOW + ": 1 : Retry" + Decorations.TEXT_RESET);
                                System.out.println(Decorations.TEXT_RED    + ": 2 : Forgot pin (users only)" + Decorations.TEXT_RESET);
                                System.out.println(Decorations.TEXT_PURPLE + ": 3 : Return to " + Main.account + Decorations.TEXT_BLUE + " MENU" + Decorations.TEXT_RESET);
                                System.out.println(Decorations.TEXT_GREEN  + ": 4 : return to LOGIN menu" + Decorations.TEXT_RESET);
                            } else {
                                System.out.println(Decorations.TEXT_YELLOW + ": 1 : Retry" + Decorations.TEXT_RESET);
                                System.out.println(Decorations.TEXT_PURPLE + ": 3 : Return to " + Main.account + Decorations.TEXT_BLUE + " MENU" + Decorations.TEXT_RESET);
                                System.out.println(Decorations.TEXT_GREEN  + ": 4 : return to LOGIN menu" + Decorations.TEXT_RESET);
                            }
                            System.out.print(Decorations.TEXT_YELLOW  + ">>>: " + Decorations.TEXT_RESET);
                            Main.temporaryString = Main.scanner.nextLine().trim();
                            if (isNumber(Main.temporaryString)) {
                                switch (Main.temporaryString) {
                                    case "1" -> {
                                        System.out.print(Decorations.TEXT_YELLOW + "RETRYING" + Decorations.TEXT_RESET);
                                        Loading.dotLoading("long");
                                        if (!isAdmin) {
                                            try {
                                                if (!isWrongUserName && !isWrongPassword) {
                                                    int count = Integer.parseInt(loginCount);
                                                    loginCountUpdater.close();
                                                    count -= 1;
                                                    FileUtil.writeToATextFile(String.valueOf(count), loginAttempt);
                                                    if (count <= 0) {
                                                        Main.userLoggedIn = false;
                                                        Main.loginCondition = true;
                                                        insertCredentials = false;
                                                        System.out.println(
                                                                Decorations.TEXT_RED +
                                                                " WARNING!!! This account has reached the maximum login attempt.s\n" +
                                                                " The system thinks that this account does not belong to you. If this account belongs to you,\n" +
                                                                " you can talk to the admin, bring your userName and request a new pin code." +
                                                                Decorations.TEXT_RESET);
                                                        System.out.print(Decorations.TEXT_GREEN + "PROCEEDING TO LOGIN MENU" + Decorations.TEXT_RESET);
                                                        Loading.dotLoading("short");
                                                        Decorations.pressEnterToContinue();
                                                        Main.scanner.nextLine();
                                                        resetReturningToLoginMenu();
                                                    }
                                                    else {
                                                        System.out.printf("REMAINING LOGIN TRIES [ >>" + Decorations.TEXT_RED + " %d " + Decorations.TEXT_RESET + " << ]\n", count);
                                                        insertCredentials = true;
                                                    }
                                                }
                                            } catch (FileNotFoundException ignored) {
                                            }
                                        }
                                    }
                                    case "2" -> {
                                        if (!isAdmin) {
                                            do {
                                                System.out.println(
                                                        Decorations.TEXT_GREEN +
                                                        " ┌─┐┬ ┬┌─┐┌─┐┬┌─  ┬┌─┐  ┬ ┬┌─┐┬ ┬                    \n" +
                                                        " │  ├─┤├┤ │  ├┴┐  │├┤   └┬┘│ ││ │                    \n" +
                                                        " └─┘┴ ┴└─┘└─┘┴ ┴  ┴└     ┴ └─┘└─┘                    \n" + Decorations.TEXT_BLUE +
                                                        " ┌─┐┌─┐┌┐┌  ┌─┐┬ ┬┌─┐┌┐┌┌─┐┌─┐  ┬ ┬┌─┐┬ ┬┬─┐  ┌─┐┬┌┐┌\n" +
                                                        " │  ├─┤│││  │  ├─┤├─┤││││ ┬├┤   └┬┘│ ││ │├┬┘  ├─┘││││\n" +
                                                        " └─┘┴ ┴┘└┘  └─┘┴ ┴┴ ┴┘└┘└─┘└─┘   ┴ └─┘└─┘┴└─  ┴  ┴┘└┘"   +
                                                        Decorations.TEXT_RESET);
                                                System.out.println(Decorations.TEXT_GREEN + ": 1 : Check if resetting pin is available" + Decorations.TEXT_RESET);
                                                System.out.println(Decorations.TEXT_PURPLE + ": 3 : Return to " + Main.account + Decorations.TEXT_BLUE + " MENU" + Decorations.TEXT_RESET);
                                                System.out.println(Decorations.TEXT_GREEN  + ": 4 : return to LOGIN menu" + Decorations.TEXT_RESET);
                                                System.out.print(Decorations.TEXT_YELLOW  + ">>>: " + Decorations.TEXT_RESET);
                                                Main.temporaryString = Main.scanner.nextLine().trim();
                                                if (isNumber(Main.temporaryString)) {
                                                    switch (Main.temporaryString) {
                                                        case "1" -> {
                                                            System.out.print(Decorations.TEXT_YELLOW + "CHECKING" + Decorations.TEXT_RESET);
                                                            Loading.dotLoading("long");
                                                            if (checkEligibility()) {
                                                                do {
                                                                    System.out.println(
                                                                            Decorations.TEXT_BLUE + "\n" +
                                                                            "  ┬ ┬┌─┐┬ ┬  ┌─┐┬─┐┌─┐  ┌─┐┬  ┬┌─┐┬┌┐ ┬  ┌─┐  ┌┬┐┌─┐  ┌─┐┬ ┬┌─┐┌┐┌┌─┐┌─┐  ┬ ┬┌─┐┬ ┬┬─┐  ┌─┐┬┌┐┌\n" +
                                                                            "  └┬┘│ ││ │  ├─┤├┬┘├┤   ├┤ │  ││ ┬│├┴┐│  ├┤    │ │ │  │  ├─┤├─┤││││ ┬├┤   └┬┘│ ││ │├┬┘  ├─┘││││\n" +
                                                                            "   ┴ └─┘└─┘  ┴ ┴┴└─└─┘  └─┘┴─┘┴└─┘┴└─┘┴─┘└─┘   ┴ └─┘  └─┘┴ ┴┴ ┴┘└┘└─┘└─┘   ┴ └─┘└─┘┴└─  ┴  ┴┘└┘\n" +
                                                                            "   ┬┐┌─┐  ┬ ┬┌─┐┬ ┬  ┬ ┬┌─┐┌┐┌┌┬┐  ┌┬┐┌─┐  ┌─┐┬ ┬┌─┐┌┐┌┌─┐┌─┐  ┬┌┬┐  ┌┐┌┌─┐┬ ┬ ┌─┐\n" +
                                                                            "   │││ │  └┬┘│ ││ │  │││├─┤│││ │    │ │ │  │  ├─┤├─┤││││ ┬├┤   │ │   ││││ ││││  ┌┘\n" +
                                                                            "  ─┴┘└─┘   ┴ └─┘└─┘  └┴┘┴ ┴┘└┘ ┴    ┴ └─┘  └─┘┴ ┴┴ ┴┘└┘└─┘└─┘  ┴ ┴   ┘└┘└─┘└┴┘  o\\s" +
                                                                            Decorations.TEXT_RESET);
                                                                    System.out.println(Decorations.TEXT_RED + ": 1 : Reset Pin Now" + Decorations.TEXT_RESET);
                                                                    System.out.println(Decorations.TEXT_PURPLE + ": 2 : Return to " + Main.account + Decorations.TEXT_YELLOW + " MENU" + Decorations.TEXT_RESET);
                                                                    System.out.println(Decorations.TEXT_GREEN  + ": 3 : return to LOGIN menu" + Decorations.TEXT_RESET);
                                                                    System.out.print(Decorations.TEXT_YELLOW  + ">>>: " + Decorations.TEXT_RESET);
                                                                    Main.temporaryString = Main.scanner.nextLine().trim();
                                                                    if (isNumber(Main.temporaryString)) {
                                                                        switch (Main.temporaryString) {
                                                                            case "1" -> {
                                                                                resetPin();
                                                                                insertCredentials = false;
                                                                                Main.userLoggedIn = false;
                                                                                Main.loginCondition = false;
                                                                                Main.isAdmin = false;
                                                                                File ticketFile = new File("src\\files\\resetPinTickets\\" + getUserName() + ".txt");
                                                                                FileUtil.writeToATextFile("false", ticketFile);
                                                                                ticketFile.deleteOnExit();
                                                                            }
                                                                            case "2" -> {
                                                                                insertCredentials = false;
                                                                                Main.userLoggedIn = false;
                                                                                Main.loginCondition = false;
                                                                                System.out.print(Decorations.TEXT_PURPLE  + "RETURNING TO " + Main.account + Decorations.TEXT_YELLOW + "MENU" + Decorations.TEXT_RESET);
                                                                                Loading.dotLoading("short");
                                                                            }
                                                                            case "3" -> {
                                                                                insertCredentials = false;
                                                                                Main.userLoggedIn = false;
                                                                                Main.loginCondition = true;
                                                                                System.out.print(Decorations.TEXT_GREEN  + "RETURNING TO LOGIN MENU" + Decorations.TEXT_RESET);
                                                                                Loading.dotLoading("short");
                                                                            }
                                                                            default -> {
                                                                                System.out.println(
                                                                                        Decorations.TEXT_RED +
                                                                                        " ┬ ┌┐┌┬  ┬┌─┐┬  ┬┌┬┐  ┌─┐┬ ┬┌─┐┬┌─┐┌─┐  ┬\n" +
                                                                                        " │ │││└┐┌┘├─┤│  │ ││  │  ├─┤│ │││  ├┤   │\n" +
                                                                                        " ┴ ┘└┘ └┘ ┴ ┴┴─┘┴─┴┘  └─┘┴ ┴└─┘┴└─┘└─┘  o"   +
                                                                                        Decorations.TEXT_RESET);
                                                                                System.out.print(Decorations.TEXT_PURPLE  + "RETURNING TO " + Main.account + Decorations.TEXT_YELLOW + "MENU" + Decorations.TEXT_RESET);
                                                                                Loading.dotLoading("short");
                                                                            }
                                                                        }
                                                                    }

                                                                } while (!Main.temporaryString.equals("1") && !Main.temporaryString.equals("2") && !Main.temporaryString.equals("3"));
                                                            } else {
                                                                File checkUser = new File("src\\files\\accounts\\user\\" + getUserName() + "'s Folder");
                                                                if (checkUser.isDirectory()) {
                                                                    do {
                                                                        System.out.println(Decorations.TEXT_RED +
                                                                                " ┬ ┬┌─┐┬ ┬  ┌─┐┬─┐┌─┐  ┌┐┌┌─┐┌┬┐  ┬ ┬┌─┐┌┬┐  ┌─┐┌─┐┬─┐┌┬┐┬┌┬┐┌┬┐┌─┐┌┬┐  \n" +
                                                                                " └┬┘│ ││ │  ├─┤├┬┘├┤   ││││ │ │   └┬┘├┤  │   ├─┘├┤ ├┬┘││││ │  │ ├┤  ││  \n" +
                                                                                "  ┴ └─┘└─┘  ┴ ┴┴└─└─┘  ┘└┘└─┘ ┴    ┴ └─┘ ┴   ┴  └─┘┴└─┴ ┴┴ ┴  ┴ └─┘─┴┘  \n" +
                                                                                " ┌┬┐┌─┐  ┌─┐┬ ┬┌─┐┌┐┌┌─┐┌─┐  ┬ ┬┌─┐┬ ┬┬─┐  ┌─┐┬┌┐┌                      \n" +
                                                                                "  │ │ │  │  ├─┤├─┤││││ ┬├┤   └┬┘│ ││ │├┬┘  ├─┘││││                      \n" +
                                                                                "  ┴ └─┘  └─┘┴ ┴┴ ┴┘└┘└─┘└─┘   ┴ └─┘└─┘┴└─  ┴  ┴┘└┘                      "   +
                                                                                Decorations.TEXT_RESET);
                                                                        System.out.println(Decorations.TEXT_GREEN  + ": 1 : Submit pin reset ticket" + Decorations.TEXT_RESET);
                                                                        System.out.println(Decorations.TEXT_PURPLE + ": 2 : Return to " + Main.account + Decorations.TEXT_YELLOW + " MENU" + Decorations.TEXT_RESET);
                                                                        System.out.println(Decorations.TEXT_GREEN  + ": 3 : return to LOGIN menu" + Decorations.TEXT_RESET);
                                                                        System.out.print(Decorations.TEXT_YELLOW   + ">>>: " + Decorations.TEXT_RESET);
                                                                        Main.temporaryString = Main.scanner.nextLine().trim();
                                                                        if (isNumber(Main.temporaryString)) {
                                                                            switch (Main.temporaryString) {
                                                                                case "1" -> {
                                                                                    if (!submitTicket()) {
                                                                                        System.out.println(Decorations.TEXT_RED + "YOU ALREADY SUBMITTED A TICKET" + Decorations.TEXT_RESET);
                                                                                    }
                                                                                    insertCredentials = false;
                                                                                    Main.userLoggedIn = false;
                                                                                    Main.loginCondition = false;
                                                                                    Main.isAdmin = false;
                                                                                    System.out.print(Decorations.TEXT_PURPLE  + "RETURNING TO " + Main.account + Decorations.TEXT_YELLOW + "MENU" + Decorations.TEXT_RESET);
                                                                                    Loading.dotLoading("short");
                                                                                }
                                                                                case "2" -> {
                                                                                    insertCredentials = false;
                                                                                    Main.userLoggedIn = false;
                                                                                    Main.loginCondition = false;
                                                                                    System.out.print(Decorations.TEXT_PURPLE  + "RETURNING TO " + Main.account + Decorations.TEXT_YELLOW + "MENU" + Decorations.TEXT_RESET);
                                                                                    Loading.dotLoading("short");
                                                                                }
                                                                                case "3" -> {
                                                                                    insertCredentials = false;
                                                                                    Main.userLoggedIn = false;
                                                                                    Main.loginCondition = true;
                                                                                    System.out.print(Decorations.TEXT_GREEN  + "RETURNING TO LOGIN MENU" + Decorations.TEXT_RESET);
                                                                                    Loading.dotLoading("short");
                                                                                }
                                                                                default -> {
                                                                                    Loading.loadingInvalidChoice();
                                                                                    Loading.dotLoading("short");
                                                                                }
                                                                            }
                                                                        }
                                                                    } while (!Main.temporaryString.equals("1") && !Main.temporaryString.equals("2") && !Main.temporaryString.equals("3"));
                                                                } else {
                                                                    insertCredentials = false;
                                                                    resetReturningToLoginMenu();
                                                                    System.out.println(
                                                                            Decorations.TEXT_RED +
                                                                            " ┬ ┬┌─┐┌─┐┬─┐  ┌┬┐┌─┐┌─┐┌─┐  ┌┐┌┌─┐┌┬┐  ┌─┐─┐ ┬┬┌─┐┌┬┐  ┬ \n" +
                                                                            " │ │└─┐├┤ ├┬┘   │││ │├┤ └─┐  ││││ │ │   ├┤ ┌┴┬┘│└─┐ │   │ \n" +
                                                                            " └─┘└─┘└─┘┴└─  ─┴┘└─┘└─┘└─┘  ┘└┘└─┘ ┴   └─┘┴ └─┴└─┘ ┴   o"    +
                                                                            Decorations.TEXT_RESET);
                                                                    System.out.print(Decorations.TEXT_GREEN  + "RETURNING TO LOGIN MENU" + Decorations.TEXT_RESET);
                                                                    Loading.dotLoading("long");
                                                                }
                                                            }
                                                        }
                                                        case "2" -> {
                                                            insertCredentials = false;
                                                            Main.userLoggedIn = false;
                                                            Main.loginCondition = false;
                                                            System.out.print(Decorations.TEXT_PURPLE  + "RETURNING TO " + Main.account + Decorations.TEXT_YELLOW + "MENU" + Decorations.TEXT_RESET);
                                                            Loading.dotLoading("short");
                                                        }
                                                        case "3" -> {
                                                            insertCredentials = false;
                                                            Main.userLoggedIn = false;
                                                            Main.loginCondition = true;
                                                            System.out.print(Decorations.TEXT_GREEN  + "RETURNING TO LOGIN MENU" + Decorations.TEXT_RESET);
                                                            Loading.dotLoading("short");
                                                        }
                                                        default -> {
                                                            Loading.loadingInvalidChoice();
                                                            Loading.dotLoading("short");
                                                        }
                                                    }
                                                }
                                            } while (!Main.temporaryString.equals("1") && !Main.temporaryString.equals("2") && !Main.temporaryString.equals("3"));
                                        } else {
                                            insertCredentials = false;
                                            System.out.print(Decorations.TEXT_PURPLE  + "RETURNING TO " + Main.account + Decorations.TEXT_YELLOW + "MENU" + Decorations.TEXT_RESET);
                                            Loading.dotLoading("short");
                                        }
                                    }
                                    case "3" -> {
                                        if (!isAdmin) {
                                            insertCredentials = false;
                                            Main.userLoggedIn = false;
                                            Main.loginCondition = false;
                                            System.out.print(Decorations.TEXT_PURPLE  + "RETURNING TO " + Main.account + Decorations.TEXT_YELLOW + "MENU" + Decorations.TEXT_RESET);
                                            Loading.dotLoading("short");
                                        } else {
                                            insertCredentials = false;
                                            Main.userLoggedIn = false;
                                            Main.loginCondition = true;
                                            System.out.print(Decorations.TEXT_GREEN  + "RETURNING TO LOGIN MENU" + Decorations.TEXT_RESET);
                                            Loading.dotLoading("short");
                                            resetReturningToLoginMenu();
                                        }
                                    }
                                    case "4" -> {
                                        if (!isAdmin) {
                                            insertCredentials = false;
                                            Main.userLoggedIn = false;
                                            Main.loginCondition = true;
                                            System.out.print(Decorations.TEXT_GREEN  + "RETURNING TO LOGIN MENU" + Decorations.TEXT_RESET);
                                            Loading.dotLoading("short");
                                            resetReturningToLoginMenu();
                                        } else {
                                            Loading.loadingInvalidChoice();
                                            Loading.dotLoading("short");
                                        }
                                    }
                                    default -> {
                                        Loading.loadingInvalidChoice();
                                        Loading.dotLoading("short");
                                    }
                                }
                            } else {
                                Loading.loadingInvalidChoice();
                                Loading.dotLoading("short");
                            }
                        } while (!Main.temporaryString.equals("1") && !Main.temporaryString.equals("2") && !Main.temporaryString.equals("3") && !Main.temporaryString.equals("4"));
                    }
                }
            }
            case "2" -> {
                if (isAdmin) {
                    System.out.print(Decorations.TEXT_GREEN  + "RETURNING TO LOGIN MENU" + Decorations.TEXT_RESET);
                    Loading.dotLoading("short");
                    resetReturningToLoginMenu();
                }
                else {
                    createUserAccount();
                }
            }
            case "3" -> {
                if (!isAdmin) {
                    System.out.print(Decorations.TEXT_GREEN  + "RETURNING TO LOGIN MENU" + Decorations.TEXT_RESET);
                    Loading.dotLoading("short");
                    resetReturningToLoginMenu();
                }
                else {
                    Loading.loadingInvalidChoice();
                    Loading.dotLoading("short");
                }
            }
            default -> {
                Loading.loadingInvalidChoice();
                Loading.dotLoading("short");
            }
        }
    }
    /**
     *  User functionalities if the user is logged in.
     */
    public void userSelections() throws InterruptedException {
        System.out.println(
                Decorations.TEXT_GREEN +
                " ┬ ┬┌─┐┌─┐┬─┐  ┌─┐┌─┐┬  ┌─┐┌─┐┌┬┐┬┌─┐┌┐┌┌─┐\n" +
                " │ │└─┐├┤ ├┬┘  └─┐├┤ │  ├┤ │   │ ││ ││││└─┐\n" +
                " └─┘└─┘└─┘┴└─  └─┘└─┘┴─┘└─┘└─┘ ┴ ┴└─┘┘└┘└─┘"   +
                Decorations.TEXT_RESET);
        System.out.println(Decorations.TEXT_YELLOW + ": 1 : Perform computation" + Decorations.TEXT_RESET);
        System.out.println(Decorations.TEXT_BLUE   + ": 2 : Play Games" + Decorations.TEXT_RESET);
        System.out.println(Decorations.TEXT_GREEN  + ": 3 : Start systems" + Decorations.TEXT_RESET);
        System.out.println(Decorations.TEXT_PURPLE + ": 4 : return to USER menu" + Decorations.TEXT_RESET);
        System.out.println(Decorations.TEXT_GREEN  + ": 2 : return to LOGIN menu" + Decorations.TEXT_RESET);
        System.out.print(Decorations.TEXT_YELLOW  + ">>>: " + Decorations.TEXT_RESET);
        Main.temporaryString = Main.scanner.nextLine().trim();
        switch (Main.temporaryString) {
            case "1" -> {
                boolean performComputation = true;
                while (performComputation) {
                    System.out.println(
                            Decorations.TEXT_YELLOW +
                            " ┌─┐┌─┐┌┬┐┌─┐┬ ┬┌┬┐┌─┐┌┬┐┬┌─┐┌┐┌┌─┐\n" +
                            " │  │ ││││├─┘│ │ │ ├─┤ │ ││ ││││└─┐\n" +
                            " └─┘└─┘┴ ┴┴  └─┘ ┴ ┴ ┴ ┴ ┴└─┘┘└┘└─┘"   +
                            Decorations.TEXT_RESET);
                    System.out.println(Decorations.TEXT_GREEN + ": 1 : Compute factorial" + Decorations.TEXT_RESET);
                    System.out.println(Decorations.TEXT_GREEN + ": 2 : Compute Fibonacci" + Decorations.TEXT_RESET);
                    System.out.println(Decorations.TEXT_GREEN + ": 3 : Sum all numbers" + Decorations.TEXT_RESET);
                    System.out.println(Decorations.TEXT_GREEN + ": 4 : Sort a number" + Decorations.TEXT_RESET);
                    System.out.println(Decorations.TEXT_GREEN + ": 5 : Compute hypotenuse" + Decorations.TEXT_RESET);
                    System.out.println(Decorations.TEXT_GREEN + ": 6 : Start calculator" + Decorations.TEXT_RESET);
                    System.out.println(Decorations.TEXT_GREEN + ": 7 : Compute Collatz conjecture" + Decorations.TEXT_RESET);
                    System.out.println(Decorations.TEXT_GREEN + ": 8 : Centimeter Conversion" + Decorations.TEXT_RESET);
                    System.out.println(Decorations.TEXT_GREEN + ": 9 : Meter conversion" + Decorations.TEXT_RESET);
                    System.out.println(Decorations.TEXT_GREEN + ":10 : Quadrant analyzer" + Decorations.TEXT_RESET);
                    System.out.println(Decorations.TEXT_GREEN + ":11 : Compute days between two dates" + Decorations.TEXT_RESET);
                    System.out.println(Decorations.TEXT_GREEN + ":12 : Compute mean, median, mode, and standard deviation" + Decorations.TEXT_RESET);
                    System.out.println(Decorations.TEXT_GREEN + ":13 : Return to USER Selection" + Decorations.TEXT_RESET);
                    System.out.print(Decorations.TEXT_YELLOW  + ">>>: " + Decorations.TEXT_RESET);
                    Main.temporaryString = Main.scanner.nextLine().trim();
                    switch (Main.temporaryString) {
                        case "1" -> {
                            Factorial factorial = new Factorial();
                            computeFactorial = true;
                            while (computeFactorial) {
                                System.out.println(
                                        Decorations.TEXT_GREEN +
                                        " ┌─┐┌─┐┌─┐┌┬┐┌─┐┬─┐┬┌─┐┬    ┌─┐┌─┐┬  ┌─┐┬ ┬┬  ┌─┐┌┬┐┌─┐┬─┐\n" +
                                        " ├┤ ├─┤│   │ │ │├┬┘│├─┤│    │  ├─┤│  │  │ ││  ├─┤ │ │ │├┬┘\n" +
                                        " └  ┴ ┴└─┘ ┴ └─┘┴└─┴┴ ┴┴─┘  └─┘┴ ┴┴─┘└─┘└─┘┴─┘┴ ┴ ┴ └─┘┴└─"   +
                                        Decorations.TEXT_RESET);
                                System.out.println(Decorations.TEXT_PURPLE + "Enter a number" + Decorations.TEXT_RESET);
                                System.out.print(Decorations.TEXT_YELLOW  + ">>>: " + Decorations.TEXT_RESET);
                                Main.temporaryString = Main.scanner.nextLine().trim();
                                if (InputChecker.isByte(Main.temporaryString)) {
                                    byte number = Byte.parseByte(Main.temporaryString);
                                    factorial.setNumber(number).getFactorial();
                                    tryAgain("computeFactorial", "computations");
                                }
                                else {
                                    Loading.loadingInvalidChoice();
                                    Loading.dotLoading("short");
                                    Main.temporaryString = "";
                                }
                            }
                        }
                        case "2" -> {
                            Fibonacci fibonacci = new Fibonacci();
                            computeFibonacci = true;
                            while (computeFibonacci) {
                                System.out.println(Decorations.TEXT_GREEN + "HOW DO YOU WANT TO COMPUTE FIBONACCI?" + Decorations.TEXT_RESET);
                                System.out.println(Decorations.TEXT_PURPLE + ": 1 : Get the fibonacci at nth position" + Decorations.TEXT_RESET);
                                System.out.println(Decorations.TEXT_PURPLE + ": 2 : Get the fibonacci until nth position" + Decorations.TEXT_RESET);
                                System.out.print(Decorations.TEXT_YELLOW  + ">>>: " + Decorations.TEXT_RESET);
                                Main.temporaryString = Main.scanner.nextLine().trim();
                                switch (Main.temporaryString) {
                                    case "1" -> {
                                        System.out.println("""
                                            ┌─┐┬┌┐ ┌─┐┌┐┌┌─┐┌─┐┌─┐┬  ┌─┐┌─┐┬  ┌─┐┬ ┬┬  ┌─┐┌┬┐┌─┐┬─┐
                                            ├┤ │├┴┐│ ││││├─┤│  │  │  │  ├─┤│  │  │ ││  ├─┤ │ │ │├┬┘
                                            └  ┴└─┘└─┘┘└┘┴ ┴└─┘└─┘┴  └─┘┴ ┴┴─┘└─┘└─┘┴─┘┴ ┴ ┴ └─┘┴└─
                                        """);
                                        System.out.println("Enter a number");
                                        System.out.print(">>>: ");
                                        Main.temporaryString = Main.scanner.nextLine().trim();
                                        if (InputChecker.isByte(Main.temporaryString)) {
                                            byte fibonacciAtNthPosition = Byte.parseByte(Main.temporaryString);
                                            fibonacci.setNumber(fibonacciAtNthPosition).getFibonacciNumberAtNth();
                                            tryAgain("computeFibonacci", "computations");
                                        }
                                    }
                                    case "2" -> {
                                        System.out.println("""
                                            ┌─┐┬┌┐ ┌─┐┌┐┌┌─┐┌─┐┌─┐┬  ┌─┐┌─┐┬  ┌─┐┬ ┬┬  ┌─┐┌┬┐┌─┐┬─┐
                                            ├┤ │├┴┐│ ││││├─┤│  │  │  │  ├─┤│  │  │ ││  ├─┤ │ │ │├┬┘
                                            └  ┴└─┘└─┘┘└┘┴ ┴└─┘└─┘┴  └─┘┴ ┴┴─┘└─┘└─┘┴─┘┴ ┴ ┴ └─┘┴└─
                                        """);
                                        System.out.println("Enter a number");
                                        System.out.print(">>>: ");
                                        Main.temporaryString = Main.scanner.nextLine().trim();
                                        if (InputChecker.isByte(Main.temporaryString)) {
                                            byte fibonacciNumberUntilN = Byte.parseByte(Main.temporaryString);
                                            fibonacci.setNumber(fibonacciNumberUntilN).getFibonacciNumberUntilN();
                                            tryAgain("computeFibonacci", "computations");
                                        }
                                    }
                                    default -> {
                                        Loading.loadingInvalidChoice();
                                        Loading.dotLoading("short");
                                        Main.temporaryString = "";
                                    }
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
                                    tryAgain("computeSumOfAllNumbers", "computations");
                                }
                                else {
                                    Loading.loadingInvalidChoice();
                                    Loading.dotLoading("short");
                                    Main.temporaryString = "";
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
                                            tryAgain("sort", "computations");
                                        }
                                        else {
                                            Loading.loadingInvalidChoice();
                                            Loading.dotLoading("short");
                                            Main.temporaryString = "";
                                        }
                                    }
                                    case "2" -> {
                                        System.out.println("HOW MANY NUMBERS YOU WANT TO SORT?");
                                        System.out.print(">>>: ");
                                        Main.temporaryString = Main.scanner.nextLine().trim();
                                        if (InputChecker.isByte(Main.temporaryString)) {
                                            System.err.println("PLEASE ENTER ONLY NUMBERS IN THIS PROCESS");
                                            System.err.println("IF YOU ENTERED A CHARACTER OR A STRING\nTHE PROGRAM WILL AUTOMATICALLY INSERT 1.0 IN THE ARRAY");
                                            length = Byte.parseByte(Main.temporaryString);
                                            doubleArray = new double[length];
                                            for (int i = 0; i < doubleArray.length; i++) {
                                                System.out.println("ENTER DECIMAL NUMBERS");
                                                System.out.print(">>>: ");
                                                Main.temporaryString = Main.scanner.nextLine().trim();
                                                if (InputChecker.isDouble(Main.temporaryString)) {
                                                    doubleChoice = Double.parseDouble(Main.temporaryString);
                                                    doubleArray[i] = doubleChoice;
                                                }
                                                else {
                                                    doubleArray[i] = 1.0;
                                                }
                                            }
                                            QuickSort.sort(doubleArray); // sorting
                                            tryAgain("sort", "computations");
                                        }
                                        else {
                                            Loading.loadingInvalidChoice();
                                            Loading.dotLoading("short");
                                            Main.temporaryString = "";
                                        }
                                    }
                                }
                            }
                        }
                        case "5" -> {
                            computeHypotenuse = true;
                            while (computeHypotenuse) {
                                HypotenuseComputer.runComputeHypotenuse();
                                tryAgain("computeHypotenuse", "computations");
                            }
                        }
                        case "6" -> {
                            runCalculator = true;
                            while (runCalculator) {
                                Calculator calculator = new Calculator();
                                calculator.runCalculator();
                                tryAgain("calculator", "computations");
                            }
                        }
                        case "7" -> {
                            computeCollatzConjecture = true;
                            while (computeCollatzConjecture) {
                                Collatz.runCollatzConjecture();
                                tryAgain("collatz", "computations");
                            }
                        }
                        case "8" -> {
                            centimeterConversion = true;
                            while (centimeterConversion) {
                                CentimeterConversion.runCentimeterConversion();
                                tryAgain("centimeterConversion", "computations");
                            }
                        }
                        case "9" -> {
                            meterConversion = true;
                            while (meterConversion) {
                                MeterConversion.runMeterConversion();
                                tryAgain("meterConversion", "computations");
                            }
                        }
                        case "10"-> {
                            quadrantAnalyzer = true;
                            while (quadrantAnalyzer) {
                                QuadrantAnalyzer.run();
                                tryAgain("quadrantAnalyzer", "computations");
                            }
                        }
                        case "11" -> {
                            daysBetweenDates = true;
                            while (daysBetweenDates) {
                                System.out.println("WHAT TYPE OF COMPUTATION YOU WANT TO USE?");
                                System.out.println(": 1 : No leap year");
                                System.out.println(": 2 : With leap year");
                                System.out.print(">>>: ");
                                Main.temporaryString = Main.scanner.nextLine().trim();
                                switch (Main.temporaryString) {
                                    case "1" -> {
                                        RemainingDaysNoLearYear.run();
                                        tryAgain("daysBetweenDates", "computations");
                                    }
                                    case "2" -> {
                                        RemainingDaysWithLeapYear.run();
                                        tryAgain("daysBetweenDates", "computations");
                                    }
                                    default -> {
                                        Loading.loadingInvalidChoice();
                                        Loading.dotLoading("short");
                                        Main.temporaryString = "";
                                    }
                                }
                            }
                        }
                        case "12"-> {
                            double[] doubleArray;
                            byte length;
                            double doubleChoice;
                            meanMedianModeSd = true;
                            while (meanMedianModeSd) {
                                System.out.println("HOW MANY NUMBERS YOU WANT TO ADD IN THE LIST?");
                                System.out.print(">>>: ");
                                Main.temporaryString = Main.scanner.nextLine().trim();
                                if (InputChecker.isByte(Main.temporaryString)) {
                                    System.err.println("PLEASE ENTER ONLY NUMBERS IN THIS PROCESS");
                                    System.err.println("\nIF YOU ENTERED A CHARACTER OR A STRING\nTHE PROGRAM WILL AUTOMATICALLY INSERT 1.0 IN THE ARRAY");
                                    length = Byte.parseByte(Main.temporaryString);
                                    doubleArray = new double[length];
                                    for (int i = 0; i < doubleArray.length; i++) {
                                        System.out.println("\nENTER DECIMAL NUMBERS");
                                        System.out.print(">>>: ");
                                        Main.temporaryString = Main.scanner.nextLine().trim();
                                        if (InputChecker.isDouble(Main.temporaryString)) {
                                            doubleChoice = Double.parseDouble(Main.temporaryString);
                                            doubleArray[i] = doubleChoice;
                                        }
                                        else {
                                            doubleArray[i] = 1.0;
                                        }
                                    }
                                    MeanMedianModeStandardDeviation.computeMeanMedianModeStandardDeviation(doubleArray);
                                    tryAgain("meanMedianModeSd", "computations");
                                }
                                else {
                                    Loading.loadingInvalidChoice();
                                    Loading.dotLoading("short");
                                    Main.temporaryString = "";
                                }
                            }
                        }
                        case "13" -> {
                            System.out.print("RETURNING TO USER SELECTIONS");
                            Loading.dotLoading("short");
                            performComputation = false;
                            userSelections();
                        }
                        default -> {
                            Loading.loadingInvalidChoice();
                            Loading.dotLoading("short");
                            Main.temporaryString = "";
                        }
                    }
                }
            }
            case "2" -> {
                boolean playGames = true;
                while (playGames) {
                    System.out.println("""
                        ┌─┐┌─┐┌┬┐┌─┐┌─┐
                        │ ┬├─┤│││├┤ └─┐
                        └─┘┴ ┴┴ ┴└─┘└─┘
                    """);
                    System.out.println(": 1 : Play guessing games");
                    System.out.println(": 2 : Play head and tails");
                    System.out.println(": 3 : Play math problem generator");
                    System.out.println(": 4 : Play rock paper scissors");
                    System.out.println(": 5 : Play Tic Tac Toe");
                    System.out.println(": 6 : Return to USER Selection");
                    System.out.print(">>>: ");
                    Main.temporaryString = Main.scanner.nextLine().trim();
                    switch (Main.temporaryString) {
                        case "1" -> {
                            playGuessingGame = true;
                            while (playGuessingGame) {
                                System.out.println(": 1 : Play guess the word");
                                System.out.println(": 2 : Play simple guessing game");
                                System.out.print(">>>: ");
                                Main.temporaryString = Main.scanner.nextLine().trim();
                                switch (Main.temporaryString) {
                                    case "1" -> {
                                        GuessTheWord.runGuess();
                                        tryAgain("guessingGame", "games");
                                    }
                                    case "2" -> {
                                        MyFirstGuessingGame.run();
                                        tryAgain("guessingGame", "games");
                                    }
                                    default -> {
                                        Loading.loadingInvalidChoice();
                                        Loading.dotLoading("short");
                                        Main.temporaryString = "";
                                    }
                                }
                            }
                        }
                        case "2" -> {
                            playHeadAndTails = true;
                            while (playHeadAndTails) {
                                HeadAndTails.run();
                                tryAgain("headAndTails", "games");
                            }
                        }
                        case "3" -> {
                            MathProblemGenerator mathProblemGenerator = new MathProblemGenerator();
                            mathProblemGame = true;
                            while (mathProblemGame) {
                                mathProblemGenerator.run();
                                tryAgain("guessingGame", "games");
                            }
                        }
                        case "4" -> {
                            playRockPaperScissors = true;
                            while (playRockPaperScissors) {
                                System.out.println(": 1 : Play rock paper scissors (not OOP)");
                                System.out.println(": 2 : Play rock paper scissors (OOP)");
                                System.out.print(">>>: ");
                                Main.temporaryString = Main.scanner.nextLine().trim();
                                switch (Main.temporaryString) {
                                    case "1" -> {
                                        RockPaperScissors rockPaperScissors = new RockPaperScissors();
                                        rockPaperScissors.run();
                                        tryAgain("rockPaperScissors", "games");
                                    }
                                    case "2" -> {
                                        lib.games.rockPaperScissors.objectOriented.RockPaperScissors rockPaperScissors = new lib.games.rockPaperScissors.objectOriented.RockPaperScissors();
                                        rockPaperScissors.run();
                                        tryAgain("rockPaperScissors", "games");
                                    }
                                    default -> {
                                        Loading.loadingInvalidChoice();
                                        Loading.dotLoading("short");
                                        Main.temporaryString = "";
                                    }
                                }
                            }
                        }
                        case "5" -> {
                            playTicTacToe = true;
                            while (playTicTacToe) {
                                System.out.println(": 1 : Play simple tic tac toe");
                                System.out.println(": 2 : Play tic tac toe with minimax algorithm");
                                System.out.print(">>>: ");
                                Main.temporaryString = Main.scanner.nextLine().trim();
                                switch (Main.temporaryString) {
                                    case "1" -> {
                                        SimpleTicTacToeGame simpleTicTacToeGame = new SimpleTicTacToeGame();
                                        simpleTicTacToeGame.run();
                                        tryAgain("ticTacToe", "games");
                                    }
                                    case "2" -> {
                                        TicTacToe ticTacToe = new TicTacToe();
                                        ticTacToe.run();
                                        tryAgain("ticTacToe", "games");
                                    }
                                    default -> {
                                        Loading.loadingInvalidChoice();
                                        Loading.dotLoading("short");
                                        Main.temporaryString = "";
                                    }
                                }
                            }
                        }
                        case "6" -> {
                            System.out.print("RETURNING TO USER SELECTIONS");
                            Loading.dotLoading("short");
                            playGames = false;
                            userSelections();
                        }
                        default -> {
                            Loading.loadingInvalidChoice();
                            Loading.dotLoading("short");
                            Main.temporaryString = "";
                        }
                    }
                }
            }
            case "3" -> {
                runStudentManagementSystem = true;
                while (runStudentManagementSystem) {
                    MainActivity mainActivity = new MainActivity();
                    mainActivity.run();
                    tryAgain("studentManagementSystem", "systems");
                }
            }
            case "4" -> {
                Main.userLoggedIn = false;
                Main.loginCondition = false;
                Main.isAdmin = false;
                System.out.print("LOGGING OUT");
                Loading.dotLoading("long");
                System.out.println("SUCCESSFULLY LOGGED OUT");
                System.out.print("RETURNING TO USER MENU");
                Loading.dotLoading("short");
            }
            case "5" -> {
                resetReturningToLoginMenu();
                System.out.print("LOGGING OUT");
                Loading.dotLoading("long");
                System.out.print(Decorations.TEXT_GREEN  + "RETURNING TO LOGIN MENU" + Decorations.TEXT_RESET);
                Loading.dotLoading("short");
            }
            default -> {
                Loading.loadingInvalidChoice();
                Loading.dotLoading("short");
                Main.temporaryString = "";
                userSelections();
            }
        }
    }
    /**
     * Admin functionalities if the admin is logged in.
     * Enables the admin to view user accounts, remove user accounts, and view tickets of user who want to reset their pin.
     * @throws InterruptedException if the thread is interrupted during execution.
     */
    public void adminSelections() throws Exception {
        System.out.println("""
            ┌─┐┌┬┐┌┬┐┬┌┐┌  ┌─┐┌─┐┬  ┌─┐┌─┐┌┬┐┬┌─┐┌┐┌┌─┐
            ├─┤ │││││││││  └─┐├┤ │  ├┤ │   │ ││ ││││└─┐
            ┴ ┴─┴┘┴ ┴┴┘└┘  └─┘└─┘┴─┘└─┘└─┘ ┴ ┴└─┘┘└┘└─┘
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
                Decorations.pressEnterToContinue();
                Main.scanner.nextLine();
                Main.temporaryString = "";
                adminSelections();
            }
            case "2" -> {
                List<String> users = viewUsers();
                if (users.size() != 0) {
                    System.out.print("ENTER USER ACCOUNT YOU WANT TO REMOVE: ");
                    String name = Main.scanner.nextLine().trim();
                    if (!name.isEmpty()) {
                        File user = new File("src\\files\\accounts\\user\\" + name + "'s Folder"); // users folder
                        if (user.exists()) {
                            try {
                                FileUtils.deleteDirectory(new File(String.valueOf(user))); //deletes the whole user folder
                                System.out.printf("REMOVING THE ACCOUNT: [%s]", name);
                                Loading.dotLoading("long");
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
                    System.out.println(
                            Decorations.TEXT_RED +
                            " ┌┬┐┬ ┬┌─┐┬─┐┌─┐  ┌─┐┬─┐┌─┐  ┌┐┌┌─┐  ┬ ┬┌─┐┌─┐┬─┐  ┌─┐┌─┐┌─┐┌─┐┬ ┬┌┐┌┌┬┐┌─┐  ┬ ┌┐┌  ┌┬┐┬ ┬┌─┐  ┬  ┬┌─┐┌┬┐\n" +
                            "  │ ├─┤├┤ ├┬┘├┤   ├─┤├┬┘├┤   ││││ │  │ │└─┐├┤ ├┬┘  ├─┤│  │  │ ││ ││││ │ └─┐  │ │││   │ ├─┤├┤   │  │└─┐ │ \n" +
                            "  ┴ ┴ ┴└─┘┴└─└─┘  ┴ ┴┴└─└─┘  ┘└┘└─┘  └─┘└─┘└─┘┴└─  ┴ ┴└─┘└─┘└─┘└─┘┘└┘ ┴ └─┘  ┴ ┘└┘   ┴ ┴ ┴└─┘  ┴─┘┴└─┘ ┴ "   +
                            Decorations.TEXT_RESET);
                }
                System.out.print("RETURNING TO ADMIN SELECTION");
                Loading.dotLoading("short");
                Decorations.pressEnterToContinue();
                Main.scanner.nextLine();
                Main.temporaryString = "";
                adminSelections();
            }
            case "3" -> {
                Ticketing ticketing = new Ticketing();
                ticketing.editEligibility(Main.isAdmin);
            }
            case "4" -> {
                Main.adminLoggedIn = false;
                Main.loginCondition = false;
                Main.isAdmin = true;
                System.out.print(Decorations.TEXT_YELLOW + "LOGGING OUT" + Decorations.TEXT_RESET);
                Loading.dotLoading("long");
                System.out.println(Decorations.TEXT_BLUE + "SUCCESSFULLY LOGGED OUT" + Decorations.TEXT_RESET);
                System.out.print(Decorations.TEXT_PURPLE  + "RETURNING TO " + Main.account + Decorations.TEXT_YELLOW + "MENU" + Decorations.TEXT_RESET);
                Loading.dotLoading("short");
            }
            case "5" -> {
                resetReturningToLoginMenu();
                System.out.print(Decorations.TEXT_YELLOW + "LOGGING OUT" + Decorations.TEXT_RESET);
                Loading.dotLoading("long");
                System.out.println(Decorations.TEXT_BLUE + "SUCCESSFULLY LOGGED OUT" + Decorations.TEXT_RESET);
                System.out.print(Decorations.TEXT_GREEN  + "RETURNING TO LOGIN MENU" + Decorations.TEXT_RESET);
                Loading.dotLoading("short");
            }
            default -> {
                Loading.loadingInvalidChoice();
                Loading.dotLoading("short");
                Main.temporaryString = "";
                adminSelections();
            }
        }
    }
    private void listOfUsersAndPasswords() throws Exception {
        List<String> allUsers = viewUsers();
        if (allUsers.size() != 0) {
            SecretKey USERNAME;
            SecretKey PIN;
            System.out.println(
                    Decorations.TEXT_RED +
                    " ┬  ┬┌─┐┌┬┐  ┌─┐┌─┐  ┬ ┬┌─┐┌─┐┬─┐┌─┐\n" + Decorations.TEXT_GREEN +
                    " │  │└─┐ │   │ │├┤   │ │└─┐├┤ ├┬┘└─┐\n" + Decorations.TEXT_BLUE  +
                    " ┴─┘┴└─┘ ┴   └─┘└    └─┘└─┘└─┘┴└─└─┘"   +
                    Decorations.TEXT_RESET);
            for (int i = 0; i < allUsers.size(); i++) {
                File userUserNameKey = new File("src\\files\\accounts\\admin\\keys\\" + allUsers.get(i) + "\\userNameKey.txt");
                File userPinKey = new File("src\\files\\accounts\\admin\\keys\\" + allUsers.get(i) + "\\pinKey.txt");
                String keys = SecurityUtil.viewCredentials(userUserNameKey, userPinKey, Main.isAdmin);
                String[] userKeys = keys.split(" +");
                String userNameKey = userKeys[0];
                String pinKey =  userKeys[1];
                USERNAME = SecurityUtil.AES.loadFromKeyStore(userNameKey, "src\\files\\accounts\\user\\" + allUsers.get(i) + "\\credentials\\username.keystore");
                PIN = SecurityUtil.AES.loadFromKeyStore(pinKey, "src\\files\\accounts\\user\\" + allUsers.get(i) + "\\credentials\\password.keystore");
                cipher = Cipher.getInstance("AES");
                byte[] decryptedUsernameData = SecurityUtil.AES.encrypt(getUserName(), USERNAME, cipher);
                byte[] decryptedPinData = SecurityUtil.AES.encrypt(getPin(), PIN, cipher);
                String decryptedUsername = SecurityUtil.AES.decrypt(decryptedUsernameData, USERNAME, cipher);
                String decryptedPassword = SecurityUtil.AES.decrypt(decryptedPinData, PIN, cipher);
                System.out.printf("USER     [%d]: %s\n", ( i + 1 ), decryptedUsername);
                System.out.printf("PASSWORD [%d]: %s\n", ( i + 1 ), decryptedPassword);
            }
        }
        else {
            System.out.println(Decorations.TEXT_RED +
                    " ┌┬┐┬ ┬┌─┐┬─┐┌─┐  ┌─┐┬─┐┌─┐  ┌┐┌┌─┐  ┬ ┬┌─┐┌─┐┬─┐  ┌─┐┌─┐┌─┐┌─┐┬ ┬┌┐┌┌┬┐┌─┐  ┬ ┌┐┌  ┌┬┐┬ ┬┌─┐  ┬  ┬┌─┐┌┬┐\n" +
                    "  │ ├─┤├┤ ├┬┘├┤   ├─┤├┬┘├┤   ││││ │  │ │└─┐├┤ ├┬┘  ├─┤│  │  │ ││ ││││ │ └─┐  │ │││   │ ├─┤├┤   │  │└─┐ │ \n" +
                    "  ┴ ┴ ┴└─┘┴└─└─┘  ┴ ┴┴└─└─┘  ┘└┘└─┘  └─┘└─┘└─┘┴└─  ┴ ┴└─┘└─┘└─┘└─┘┘└┘ ┴ └─┘  ┴ ┘└┘   ┴ ┴ ┴└─┘  ┴─┘┴└─┘ ┴ "   +
                    Decorations.TEXT_RESET);
            System.out.print("RETURNING TO ADMIN SELECTION");
            Loading.dotLoading("short");
        }
    }

    /**
     * <p>Method that gets the users folder and put them in a {@code List<String>} of {@code String}.
     * </p>
     * @return the list of users as a {@code List<String>} of {@code String}.
     */
    protected List<String> viewUsers() {
        File usersDirectory = new File("src\\files\\accounts\\admin\\keys\\");
        FileFilter directoryFileFilter = File::isDirectory;
        File[] directoryList = usersDirectory.listFiles(directoryFileFilter);
        assert directoryList != null;
        List<String> users = new ArrayList<>(directoryList.length);
        for (File directory : directoryList) {
            users.add(directory.getName());
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
        String fileValue = "";
        try {
            Scanner checkTicket = new Scanner(ticketFile);
            fileValue = checkTicket.nextLine();
        } catch (FileNotFoundException ignored) {
        }
        return fileValue.equals("true");
    }
    /**
     *  Admin functionalities if the admin is logged in.
     *  Method that allows the user to create accounts.
     *  @throws IOException if input output process was interrupted
     *  @throws InterruptedException if the thread is interrupted during execution
     */
    private void createUserAccount() throws Exception {
        System.out.print("ENTER USERNAME: ");
        Main.temporaryString = Main.scanner.nextLine().trim();
        if (Main.temporaryString.matches("[a-zA-Z]+") || Main.temporaryString.matches("[a-zA-z0-9]+")) {
            setUserName(Main.temporaryString);
            File usersKeyFolder = new File("src\\files\\accounts\\admin\\key\\" + getUserName() + "\\");
            File userAccountFolder = new File ("src\\files\\accounts\\user\\" + getUserName() + "'s Folder\\");
            File credentialsFolder = new File ("src\\files\\accounts\\user\\" + getUserName() + "'s Folder\\credentials");
            File attemptsFolder = new File ("src\\files\\accounts\\user\\" + getUserName() + "'s Folder\\loginAttempts");
            if (userAccountFolder.mkdirs() && credentialsFolder.mkdirs() && attemptsFolder.mkdirs() && usersKeyFolder.mkdirs()) {
                key = SecurityUtil.AES.generateKey();
                cipher = Cipher.getInstance("AES");
                SecurityUtil.AES.storeToKeyStore(key, getUserName(), "src\\files\\accounts\\user\\" + getUserName() + "'s Folder\\credentials\\username.keystore");
                SecurityUtil.AES.encrypt(getUserName(), key, cipher);
                char[] oneTimePin = PinGenerator.generatePin();
                setPin(String.valueOf(oneTimePin));
                SecurityUtil.AES.storeToKeyStore(key, getPin(), "src\\files\\accounts\\user\\" + getUserName() + "'s Folder\\credentials\\password.keystore");
                SecurityUtil.AES.encrypt(getPin(), key, cipher);
                File userLoginAttempt = new File("src\\files\\accounts\\user\\" + getUserName() + "'s Folder\\loginAttempts\\remainingAttempts.txt");
                if (getPin().length() == 6) {
                    FileUtil.writeToATextFile("4", userLoginAttempt); // 4 login attempts, if the user did not follow instructions carefully
                }
                else {
                    FileUtil.writeToATextFile("6", userLoginAttempt); // 6 login attempts
                }
                File userUserNameKey = new File("src\\files\\accounts\\admin\\key\\" + getUserName() + "\\userNameKey.txt");
                File userPinKey = new File("src\\files\\accounts\\admin\\key\\" + getUserName() + "\\pinKey.txt");
                FileUtil.writeToATextFile(getUserName(), userUserNameKey);
                FileUtil.writeToATextFile(getPin(), userPinKey);
                SecurityUtil.encryptUserName(userUserNameKey, getUserName());
                SecurityUtil.encryptPin(userPinKey, getPin());
                System.out.print("CREATING YOUR ACCOUNT");
                Loading.dotLoading("long");
                System.out.println("SUCCESSFULLY CREATED (!)");
                System.out.println("=========================");
                System.out.print("YOUR PIN: ");
                for (char c : oneTimePin) {
                    System.out.print(c);
                }
                System.out.println("\n=========================");
                System.out.print("RETURNING TO LOGIN MENU");
                Loading.dotLoading("short");
                Decorations.pressEnterToContinue();
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
                System.out.print("\nRETURNING TO USER MENU");
                Loading.dotLoading("short");
            }
        }
    }
    /**
     *  Method that resets the pins of the user, if the user is eligible to change pin
     * @throws IOException if input output process was interrupted
     * @throws InterruptedException if the thread is interrupted during execution
     */
    private void resetPin() throws Exception {
        if (checkEligibility()) {
            boolean resettingPin = true;
            while (resettingPin) {
                SecretKey pin = SecurityUtil.AES.loadFromKeyStore(getPin(), "src\\files\\accounts\\user\\" + getUserName() + "'s Folder\\credentials\\password.keystore");
                cipher = Cipher.getInstance("AES");
                byte[] encryptedPinData = SecurityUtil.AES.encrypt(getPin(), pin, cipher);
                String decryptedPinString = SecurityUtil.AES.decrypt(encryptedPinData, pin, cipher);
                if (decryptedPinString.equals(getPin())) {
                    System.err.println("OLD PASSWORD CANNOT BE YOUR NEW PASSWORD (!)\n");
                }
                else {
                    key = SecurityUtil.AES.generateKey();
                    char[] oneTimePin = PinGenerator.generatePin();
                    setPin(String.valueOf(oneTimePin));
                    SecurityUtil.AES.storeToKeyStore(key, getPin(), "src\\files\\accounts\\user\\" + getUserName() + "'s Folder\\credentials\\password.keystore");
                    SecurityUtil.AES.encrypt(getPin(), key, cipher);
                    File updateAttempt = new File ("src\\files\\accounts\\user\\" + getUserName() + "'s Folder\\loginAttempts\\remainingAttempts.txt");
                    if (getPin().length() == 6) {
                        FileUtil.writeToATextFile("4", updateAttempt); // 4 login attempts, if the user did not follow instructions carefully
                    }
                    else {
                        FileUtil.writeToATextFile("6", updateAttempt); // 6 login attempts
                    }
                    System.out.print("CHANGING YOUR PIN");
                    Loading.dotLoading("long");
                    System.out.println("SUCCESSFULLY CHANGED YOUR PIN (!)");
                    System.out.println("=========================");
                    System.out.print("YOUR PIN: ");
                    for (char c : oneTimePin) {
                        System.out.print(c);
                    }
                    System.out.println("\n=========================");
                    System.out.print("RETURNING TO LOGIN MENU");
                    Loading.dotLoading("short");
                    System.out.println("=========================");
                    System.out.println("|PRESS ENTER TO CONTINUE|");
                    System.out.println("=========================");
                    Main.scanner.nextLine();
                    Main.userLoggedIn = false;
                    Main.loginCondition = true;
                    resettingPin = false;
                }
            }
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
    private static void tryAgain(String whichComputation, String whichSelection) throws InterruptedException {
        System.out.println("\nDO YOU WANT TO DO IT AGAIN? ");
        System.out.println(": 1 : Yes");
        System.out.println(": 2 : No");
        System.out.print(">>>: ");
        String choice = new Scanner(System.in).nextLine().trim();
        switch (choice) {
            case "1" -> {
                System.out.print("PROCEEDING");
                Loading.dotLoading("short");
            }
            case "2" -> {
                switch (whichSelection) {
                    case "computations" -> System.out.print("RETURNING TO COMPUTATION SELECTION");
                    case "games" -> System.out.print("RETURNING TO GAMES SELECTION");
                    case "systems" -> System.out.print("RETURNING TO SYSTEMS SELECTION");
                }
                Loading.dotLoading("short");
                switch (whichComputation) {
                    case "computeFactorial" -> computeFactorial = false;
                    case "computeFibonacci" -> computeFibonacci = false;
                    case "computeSumOfAllNumbers" -> computeSumOfAllNumbers = false;
                    case "sort" -> sort = false;
                    case "computeHypotenuse" -> computeHypotenuse = false;
                    case "calculator" -> runCalculator = false;
                    case "collatz" -> computeCollatzConjecture = false;
                    case "centimeterConversion" -> centimeterConversion = false;
                    case "meterConversion" -> meterConversion = false;
                    case "computeDaysBetweenDates" -> daysBetweenDates = false;
                    case "quadrantAnalyzer" -> quadrantAnalyzer = false;
                    case "meanMedianModeSd" -> meanMedianModeSd = false;
                    case "guessingGame" -> playGuessingGame = false;
                    case "headAndTails" -> playHeadAndTails = false;
                    case "mathProblemGenerator" -> mathProblemGame = false;
                    case "rockPaperScissors" -> playRockPaperScissors = false;
                    case "ticTacToe" -> playTicTacToe = false;
                    case "studentManagementSystem" -> runStudentManagementSystem = false;
                }
            }
            default -> System.err.println("PLEASE CHOOSE 1 or 2\n");
        }
    }
}
