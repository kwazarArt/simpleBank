package view;

import controller.AccountsController;
import model.Account;
import model.AccountStatus;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AccountView {
    AccountsController ac = new AccountsController();
    public void viewAccountMenu(){
        while (true) {
            String choice;
            int x;
            while (true) {
                System.out.println();
                System.out.println("1 - Create account");
                System.out.println("2 - Print account");
                System.out.println("3 - Update account");
                System.out.println("4 - Delete account");
                System.out.println("5 - Print all accounts");
                System.out.println("0 - Exit");
                System.out.print("Enter variant: ");
                Scanner sc = new Scanner(System.in);
                choice = sc.nextLine();
                System.out.println();
                if (choice.equals("0")) return;
                if (choice.equals("1") || choice.equals("2") || choice.equals("3") || choice.equals("4") || choice.equals("5")) {
                    x = Integer.parseInt(choice);
                    break;
                }
            }
            switch (x) {
                case 1:
                    viewCreateAccount();
                    break;
                case 2:
                    viewPrintAccount();
                    break;
                case 3:
                    viewUpdateAccount();
                    break;
                case 4:
                    viewDeleteAccount();
                    break;
                case 5:
                    viewAllAccount();
                    break;
            }
        }
    }

    public void viewCreateAccount() {
        ac.controlCreate(findAccount());
    }

    public void viewPrintAccount() {
        ac.controlPrintById(findId());
    }

    public void viewUpdateAccount() {
        ac.controlUpdate(findAccount());
    }

    public void viewDeleteAccount() {
        System.out.println("Which account will be deleted?");
        ac.controlDeelte(findId());
    }

    public void viewAllAccount() {
        ac.controlPrintAll();
    }

    private long findId() {
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

    private Account findAccount() {
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
        return new Account(0, balance, AccountStatus.valueOf(status));
    }
}
