package main.java.com.kwazarart.simplebank.view;

import main.java.com.kwazarart.simplebank.view.AccountView;
import main.java.com.kwazarart.simplebank.view.CustomerView;
import main.java.com.kwazarart.simplebank.view.TransactionsView;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

public class ConsoleHelper {
    private AccountView av = new AccountView();
    private CustomerView cv = new CustomerView();
    private TransactionsView tv = new TransactionsView();

    public void start() {
        while (true) {
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
                if (choice.equals("0")) return;
                if (choice.equals("1") || choice.equals("2") || choice.equals("3")) {
                    x = Integer.parseInt(choice);
                    break;
                }
            }
            switch (x) {
                case 1:
                    av.viewAccountMenu();
                    break;
                case 2:
                    cv.viewCustomerMenu();
                    break;
                case 3:
                    tv.viewTransactionsMenu();
                    break;
                case 0:
                    return;
            }
        }
    }
}
