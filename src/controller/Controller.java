package controller;

import model.Account;
import model.AccountStatus;
import repository.AccountRepository;
import view.Viewer;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Controller {
    private int mainChoice;
    private int accountChoice;
    private int customerChoice;
    private int TransactionChoice;

    public void start() throws IOException {
        mainChoice = Viewer.viewMainMenu();
        switch (mainChoice) {
            case 1:
                controlAccount();
                break;
            case 2:
                controlCustomers();
                break;
            case 3:
                controlTransactions();
            case 0:
                return;
        }
    }

    public void controlAccount() throws IOException {
        while (true) {
            accountChoice = Viewer.viewAccountMenu();
            switch (accountChoice) {
                case 1:
                    Account account = new Account(AccountRepository.searchMaxIndex());
                    System.out.println("Account created:\nID: " + account.getId() + " Balance: " + account.getBalance() +
                            " Status: " + account.getStatus() + "\n");
                    AccountRepository.save(account);
                    break;
                case 2:
                    long id;
                    while (true) {
                        System.out.print("Enter ID account: ");
                        try {
                            Scanner sc = new Scanner(System.in);
                            id = sc.nextLong();
                            if (id > 0 && id < AccountRepository.searchMaxIndex()) break;
                            else System.out.println("ID not found");
                        } catch (InputMismatchException e) {
                            System.out.println("Wrong input format. Try again.");
                        }
                    }
                    System.out.println();
                    System.out.println(AccountRepository.getById(id));
                    System.out.println();
                    break;
                case 3:
                    long tempId;
                    System.out.println("Which account will be configured?");
                    while (true) {
                        System.out.print("Enter ID account: ");
                        try {
                            Scanner sc = new Scanner(System.in);
                            tempId = sc.nextLong();
                            if (tempId > 0 && tempId < AccountRepository.searchMaxIndex()) break;
                            else System.out.println("ID not found");
                        } catch (InputMismatchException e) {
                            System.out.println("Wrong input format. Try again.");
                        }
                    }
                    System.out.print("Enter new balance: ");
                    Scanner sc = new Scanner(System.in);
                    BigDecimal balance = new BigDecimal(sc.nextLine());

                    String status;
                    while (true) {
                        System.out.print("Enter new status (ACTIVE/DELETED/FINISHED) : ");
                        sc = new Scanner(System.in);
                        status = sc.nextLine();
                        if (status.equals(AccountStatus.ACTIVE.toString()) || status.equals(AccountStatus.DELETED.toString()) ||
                                status.equals(AccountStatus.FINISHED.toString())) break;
                    }
                    AccountRepository.update(new Account(tempId, balance, AccountStatus.valueOf(status)));
                    System.out.println("Update completed!");
                    break;
                case 4:
                    System.out.println("Which account will be deleted?");
                    while (true) {
                        System.out.print("Enter ID account: ");
                        try {
                            sc = new Scanner(System.in);
                            tempId = sc.nextLong();
                            if (tempId > 0 && tempId < AccountRepository.searchMaxIndex()) break;
                            else System.out.println("ID not found");
                        } catch (InputMismatchException e) {
                            System.out.println("Wrong input format. Try again.");
                        }
                    }
                    AccountRepository.deleteById(tempId);
                    System.out.println("Delete completed!");
                    break;
                case 0:
                    return;
            }
        }
    }

    public void controlCustomers () throws IOException {}

    public  void controlTransactions() throws IOException {}
}
