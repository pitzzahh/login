import lib.utilities.SecurityUtil;

import java.io.File;
import java.io.FileNotFoundException;

public class Test {
    public static void main(String[] args) throws FileNotFoundException {
        String credentials = SecurityUtil.viewCredentials(new File("src\\files\\accounts\\user\\pitzzahh's Folder\\credentials\\username.txt"), new File("src\\files\\accounts\\user\\pitzzahh's Folder\\credentials\\pin.txt"), true);
        String[] cred = credentials.split(" +");
        System.out.println("USERNAME: " + cred[0]);
        System.out.println("PASSWORD: " + cred[1]);
    }
}
