package framework;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Process {
    // TODO create a simple login program
    // TODO allow anyone to create a username and password
    // TODO store the password in a file
    // TODO when the user tries to login, check if the password matches the password stored in the file

    private String userName;
    private String pin;

    public String getUserName() {
        return userName;
    }
    public String getPin() {
        return userName;
    }
    public void setUserName() {

    }
    public void setPin() {

    }
    public void write(String writer, File file) throws IOException {

        FileWriter fw = new FileWriter(file);
        PrintWriter pw = new PrintWriter(fw);

        pw.println(writer);
        pw.close();

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
}
