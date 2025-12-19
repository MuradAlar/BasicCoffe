package UI;

import java.util.Scanner;

public class ConsoleUI {
    private static final Scanner sc = new Scanner(System.in);

    public static void print(String message) {
        System.out.println(message);
    }

    public static int readInt(String prompt, int max) {
        while (true) {
            try {
                System.out.print(prompt);
                int choice = Integer.parseInt(sc.nextLine());
                if (choice >= 1 && choice <= max) return choice;
            } catch (Exception ignore) {}
            System.out.println("Invalid input. Enter a number between 1 and " + max);
        }
    }

    public static String readString(String prompt) {
        System.out.print(prompt);
        return sc.nextLine();
    }
}