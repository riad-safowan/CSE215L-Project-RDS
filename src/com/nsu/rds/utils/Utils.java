package src.com.nsu.rds.utils;

public class Utils {
    public static void printTitle(String text) {
        StringBuilder line = new StringBuilder();
        line.append("=".repeat(Math.max(0, (60 - text.length()) / 2)));
        System.out.println("\n" + line + " " + text + " " + ((text.length() % 2 == 1) ? line + "=" : line));
    }
}
