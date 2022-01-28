package pinGenerator;

import framework.Process;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class PinGenerator {
    // kung ilang haba yung pin
    static byte lengthOfPin;
    static final Process process = new Process();
    public static char[] generatePin() throws InterruptedException {
        // dito pipili ng random numbers,letters or mixed
        String numbers = "1234567890";
        String letters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String mixed = "abcdefghijklmnopqrstuvwxyz1234567890ABCDEFGHIJKLMNOPQRSTUVYXYZ";

        Scanner scanner = new Scanner(System.in);
        String response;
        String otpType = "";
        System.out.println("WHAT TYPE OF PIN YOU WANT TO USE?");
        System.out.println(": 1 : random numbers");
        System.out.println(": 2 : random letters");
        System.out.println(": 3 : choose your own pin");
        System.out.print(">>>: ");
        response = scanner.nextLine().trim();
        // dito i check if yung laman ng inputs it nandun sa char array na oneTimePin
        char[] oneTimePin = new char[0];
        if (response.equals("1") || response.equals("2")) {
            switch (response) {
                case "1" -> otpType = "NUMBERS";
                case "2" -> otpType = "LETTERS";
                default -> {
                    lengthOfPin = 6;
                    System.out.println("INVALID CHOICE (!)");
                    System.out.println("THE TYPE OF PIN WILL BE A MIXED OF LETTERS AND NUMBERS");
                    System.out.printf("YOUR PIN LENGTH WILL BE %d characters long\n", lengthOfPin);
                }
            }
            while (true) {
                System.out.println("HOW LONG YOU WANT YOUR PIN TO BE?");
                System.out.print(">>>: ");
                response = scanner.nextLine().trim();
                if (process.isNumber(response)) {
                    lengthOfPin = Byte.parseByte(response);
                    if (lengthOfPin >= 1 && lengthOfPin <= 3) {
                        System.out.println("PIN LENGTH IS TOO SHORT\nPLEASE TRY LONGER PIN LENGTH");
                    } else if (lengthOfPin >= 4 && lengthOfPin <= 6) {
                        break;
                    } else {
                        if (lengthOfPin >= 7) {
                            System.out.println("PIN LENGTH IS TOO LONG");
                        }
                        if (lengthOfPin == 0) {
                            System.out.println("PIN LENGTH CANNOT BE 0");
                        }
                    }
                } else {
                    lengthOfPin = 6;
                    System.out.println("YOUR PIN LENGTH SHOULD BE A NUMBER");
                    System.out.printf("YOUR PIN LENGTH WILL BE %d characters long\n", lengthOfPin);
                    break;
                }
            }
            Random random = new Random();
            // dito ilalagay yung mga generated numbers (di siya numbers actually mga characters lang)
            oneTimePin = new char[lengthOfPin];
            // dito maga loop lang na 4 na beses, it means apat na beses maga generate ng numbers
            switch (otpType) {
                case "NUMBERS" -> {
                    for (int i = 0; i < lengthOfPin; i++) {
                        // dito i store niya lang sa listahan yung 4 numbers na generated
                        oneTimePin[i] = numbers.charAt(random.nextInt(numbers.length()));
                    }
                }
                case "LETTERS" -> {
                    for (int j = 0; j < lengthOfPin; j++) {
                        // dito i store niya lang sa listahan yung 6 numbers na generated
                        oneTimePin[j] = letters.charAt(random.nextInt(letters.length()));
                    }
                }
                default -> {
                    for (int k = 0; k < lengthOfPin; k++) {
                        // dito i store niya lang sa listahan yung 10 numbers na generated
                        oneTimePin[k] = mixed.charAt(random.nextInt(mixed.length()));
                    }
                }
            }
            System.out.println("=========================");
            System.out.print("YOUR PIN: ");
            for (char c : oneTimePin) {
                System.out.print(c);
            }
        }
        else if (response.equals("3")) {
            System.out.print("ENTER YOUR OWN PIN: ");
            String pin = scanner.nextLine();
            oneTimePin = pin.toCharArray();
            TimeUnit.MILLISECONDS.sleep(400);
        }
        else {
            lengthOfPin = 6;
            System.out.println("INVALID CHOICE (!)");
            System.out.println("THE TYPE OF PIN WILL BE A MIXED OF LETTERS AND NUMBERS");
            System.out.printf("YOUR PIN LENGTH WILL BE %d characters long\n", lengthOfPin);
        }
        if (!response.equals("3")) {
            System.out.println("\n=========================");
            System.out.println("|PRESS ENTER TO CONTINUE|");
            System.out.println("=========================");
            scanner.nextLine();
        }
        return oneTimePin;
    }
}
