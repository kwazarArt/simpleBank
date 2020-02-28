package view;

import controller.ControllerAccount;
import model.Account;
import model.AccountStatus;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ViewerAccount {


    public void viewAccountMenu() throws IOException {
        while (true) {
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
                if (choice.equals("0")) return;
                if (choice.equals("1") || choice.equals("2") || choice.equals("3") || choice.equals("4")) {
                    x = Integer.parseInt(choice);
                    break;
                }
            }
            ControllerAccount ca = new ControllerAccount();
            ca.controlAccount(x);
        }
    }

    public String[] viewCreateAccount() {
        BigDecimal balance;
        String status;
        while (true) {
            System.out.print("Enter new balance: ");
            Scanner sc = new Scanner(System.in);
            try {
                balance = new BigDecimal(sc.nextLine());
                break;
            } catch (Exception e) {
                System.out.print("Wrong input - " + e.getCause() + "\nTry again: ");
            }
        }
        while (true) {
            System.out.print("Enter new status (ACTIVE/DELETED/FINISHED) : ");
            Scanner sc = new Scanner(System.in);
            status = sc.nextLine();
            if (status.equals(AccountStatus.ACTIVE.toString()) || status.equals(AccountStatus.DELETED.toString()) ||
                    status.equals(AccountStatus.FINISHED.toString())) break;
            else System.out.print("Wrong status. Try again: ");
        }
        return new String[] { balance.toString(), status};
    }

    public long viewPrintAccount() {
        long id;
        while (true) {
            System.out.print("Enter ID account: ");
            try {
                Scanner sc = new Scanner(System.in);
                id = sc.nextLong();
                if (id > 0) break;
            } catch (InputMismatchException e) {
                System.out.println("Wrong input format. Try again.");
            }
        }
        return id;
    }

    public String[] viewUpdateAccount() {
        String accountData[] = new String[3];
        long id = viewPrintAccount();
        String balanceAndStatus[] = viewCreateAccount();
        accountData[0] = String.valueOf(id);
        accountData[1] = balanceAndStatus[0];
        accountData[2] = balanceAndStatus[1];
        return accountData;
    }

    public long viewDeleteAccount() {
        System.out.println("Which account will be deleted?");
        return viewPrintAccount();
    }
}
