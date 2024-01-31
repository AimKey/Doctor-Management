package Common;

import java.util.ArrayList;
import java.util.Scanner;

public class Library {
    // Only choose what you need to avoid redundancy
    public int getInt(String td) {
        Scanner sc = new Scanner(System.in);
        System.out.print(td + ": ");
        return Integer.parseInt(sc.nextLine());
    }

    public int getInt(String td, String errorMsg) {
        Scanner sc = new Scanner(System.in);
        System.out.print(td + ": ");
        String s = sc.nextLine();
        if (s.length() == 0) {
            return -1;
        }
        while (!s.matches("[0-9]+")) {
            System.out.println("\u001B[31m" + errorMsg + "\u001B[0m");
            s = getString(td);
        }
        return Integer.parseInt(s);
    }

    public String getString(String td) {
        Scanner sc = new Scanner(System.in);
        System.out.print(td + ": ");
        return sc.nextLine();
    }

    public String getString(String td, String errorMsg) {
        Scanner sc = new Scanner(System.in);
        System.out.print(td + ": ");
        String s = sc.nextLine();
        if (s.length() == 0) {
            return "";
        }
        while (!s.matches("[A-Za-z ]+")) {
            System.out.println("\u001B[31m" + errorMsg + "\u001B[0m");
            s = getString(td);
        }
        return s;
    }

    public void printErr(String msg) {
        System.out.println("\u001B[31m" + msg + "\u001B[0m");
    }
}
