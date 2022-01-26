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
    private String password;

    public String getUserName() {
        return userName;
    }
    public String getPassword() {
        return userName;
    }
    public void setUserName() {

    }
    public void setPassword() {

    }
    public static void write(String writer, File file , boolean t) throws IOException {

        FileWriter fw = new FileWriter(file, t);
        PrintWriter pw = new PrintWriter(fw);

        pw.println(writer);
        pw.close();

    }
}
