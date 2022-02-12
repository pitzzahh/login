import lib.utilities.SecurityUtil;
import lib.utilities.misc.Decorations;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import java.io.File;
import java.io.FileNotFoundException;

public class Test {
    public static void main(String[] args) {
        System.out.println(
                Decorations.TEXT_YELLOW +
                        " ┌─┐┌─┐┌┬┐┌─┐┬ ┬┌┬┐┌─┐┌┬┐┬┌─┐┌┐┌┌─┐\n" +
                        " │  │ ││││├─┘│ │ │ ├─┤ │ ││ ││││└─┐\n" +
                        " └─┘└─┘┴ ┴┴  └─┘ ┴ ┴ ┴ ┴ ┴└─┘┘└┘└─┘");
        System.out.println(Decorations.TEXT_GREEN + ": 1 : Compute factorial");
        System.out.println(Decorations.TEXT_GREEN + ": 2 : Compute Fibonacci");
        System.out.println(Decorations.TEXT_GREEN + ": 3 : Sum all numbers");
        System.out.println(Decorations.TEXT_GREEN + ": 4 : Sort a number");
        System.out.println(Decorations.TEXT_GREEN + ": 5 : Compute hypotenuse");
        System.out.println(Decorations.TEXT_GREEN + ": 6 : Start calculator");
        System.out.println(Decorations.TEXT_GREEN + ": 7 : Compute Collatz conjecture");
        System.out.println(Decorations.TEXT_GREEN + ": 8 : Centimeter Conversion");
        System.out.println(Decorations.TEXT_GREEN + ": 9 : Meter conversion");
        System.out.println(Decorations.TEXT_GREEN + ":10 : Quadrant analyzer");
        System.out.println(Decorations.TEXT_GREEN + ":11 : Compute days between two dates");
        System.out.println(Decorations.TEXT_GREEN + ":12 : Compute mean, median, mode, and standard deviation");
        System.out.println(Decorations.TEXT_GREEN + ":13 : Return to USER Selection");
        System.out.print(Decorations.TEXT_YELLOW  + ">>>: ");
    }
}
