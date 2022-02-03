import mainActivity.Main;
import myCodes.myProjects.pinGenerator.PinGenerator;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Test {
    public static void writeToATextFile(String whatToWrite, File file) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
        bufferedWriter.write(whatToWrite);
        bufferedWriter.close();
    }
    public static void main(String[] args) throws InterruptedException, IOException {
        File userAccountFolder = new File ("src\\files\\accounts\\user\\" + "test" + "'s Folder\\");
        File credentialsFolder = new File ("src\\files\\accounts\\user\\" + "test" + "'s Folder\\credentials");
        File attemptsFolder = new File ("src\\files\\accounts\\user\\" + "test" + "'s Folder\\loginAttempts");
        boolean success = (userAccountFolder.mkdirs() && credentialsFolder.mkdirs() && attemptsFolder.mkdirs());
        if (success) {
            File userName = new File("src\\files\\accounts\\user\\" + "test" + "'s Folder\\credentials\\username.txt");
            File userPin = new File("src\\files\\accounts\\user\\" + "test" + "'s Folder\\credentials\\pin.txt");
            File userLoginAttempt = new File("src\\files\\accounts\\user\\" + "test" + "'s Folder\\loginAttempts\\remainingAttempts.txt");
            writeToATextFile("test", userName);
            writeToATextFile("test", userPin);
            char[] oneTimePin = PinGenerator.generatePin();
            String pin = String.valueOf(oneTimePin);
            if (pin.length() == 6) {
                writeToATextFile("4", userLoginAttempt); // 4 login attempts, if the user did not follow instructions carefully
            }
            else {
                writeToATextFile("6", userLoginAttempt); // 6 login attempts
            }
            System.out.print("CREATING YOUR ACCOUNT");
            System.out.println("SUCCESSFULLY CREATED (!)");
            System.out.println("=========================");
            System.out.print("YOUR PIN: ");
            for (char c : oneTimePin) {
                System.out.print(c);
            }
            System.out.println("\n=========================");
            System.out.print("RETURNING TO LOGIN MENU");
            System.out.println("=========================");
            System.out.println("|PRESS ENTER TO CONTINUE|");
            System.out.println("=========================");
            Main.scanner.nextLine();
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
            System.out.print("RETURNING TO USER MENU");
        }
    }
}
