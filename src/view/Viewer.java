package view;

import java.io.IOException;
import java.util.Scanner;

public class Viewer {
    public static int viewMainMenu() throws IOException {
        String choice;
        int x;
        while (true) {
            System.out.println("1 - Account");
            System.out.println("2 - Customer");
            System.out.println("3 - Transaction");
            System.out.println("0 - Exit");
            System.out.print("Enter variant: ");
            Scanner sc = new Scanner(System.in);
            choice = sc.nextLine();
            System.out.println();
            if (choice.equals("0")) return 0;
            if (choice.equals("1") || choice.equals("2") || choice.equals("3")) {
                x = Integer.parseInt(choice);
                break;
            }
        }
        return x;
    }

    public static int viewAccountMenu() {
        String choice;
        int x;
        while (true) {
            System.out.println();
            System.out.println("1 - Create account");
            System.out.println("2 - Print account");
            System.out.println("3 - Update account");
            System.out.println("4 - Delete account");
            System.out.println("0 - Exit");
            System.out.print("Enter variant: ");
            Scanner sc = new Scanner(System.in);
            choice = sc.nextLine();
            System.out.println();
            if (choice.equals("0")) return 0;
            if (choice.equals("1") || choice.equals("2") || choice.equals("3") || choice.equals("4")) {
                x = Integer.parseInt(choice);
                break;
            }
        }
        return x;
    }
}