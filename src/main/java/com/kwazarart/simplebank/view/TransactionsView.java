package main.java.com.kwazarart.simplebank.view;

import main.java.com.kwazarart.simplebank.controller.TransactionsController;
import main.java.com.kwazarart.simplebank.model.Account;
import main.java.com.kwazarart.simplebank.model.Customer;
import main.java.com.kwazarart.simplebank.model.Transaction;
import main.java.com.kwazarart.simplebank.model.TransactionStatus;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class TransactionsView {
    TransactionsController tc = new TransactionsController();

    public void viewTransactionsMenu() {
        while (true) {
            String choice;
            int x;
            while (true) {
                System.out.println();
                System.out.println("1 - Create transaction");
                System.out.println("2 - Print transaction");
                System.out.println("3 - Update transaction");
                System.out.println("4 - Delete transaction");
                System.out.println("5 - Print all transactions");
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
                    viewCreateTransaction();
                    break;
                case 2:
                    viewPrintTransaction();
                    break;
                case 3:
                    viewUpdateTransaction();
                    break;
                case 4:
                    viewDeleteTransaction();
                    break;
                case 5:
                    viewAllTransactions();
                    break;
            }
        }
    }

    private void viewCreateTransaction() {
        tc.controlCreate(newTransaction());
    }

    private void viewPrintTransaction() {
        System.out.print("Transaction. ");
        long id = findId();
        List<Transaction> list = tc.controlPrinById(id);
        for (Transaction transaction : list) {
            System.out.println(transaction);
        }
    }

    private void viewUpdateTransaction() {
        System.out.print("Transactions. ");
        long id;
        Set<Long> listIdAccount = tc.getIdAccounts();
        System.out.print("Choice ID account. ");
        Transaction transaction;
        do{
            transaction = newTransaction();
        } while (!listIdAccount.contains(transaction.getAccount().getId()));
        transaction.setStatus(TransactionStatus.RETURNED);
        tc.controlUpdate(transaction);
    }

    private void viewDeleteTransaction() {
        tc.controlDelete(findId());
    }

    private void viewAllTransactions() {
        List<Transaction> list = tc.getAllTransactions();
        for (Transaction transaction : list)
            System.out.println(transaction);
    }

    private long findId() {
        long id;
        while (true) {
            System.out.print("Enter ID: ");
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

    private Transaction newTransaction() {
        Transaction transaction = new Transaction();
        BigDecimal amount;
        while (true) {
            System.out.print("Enter new balance: ");
            Scanner sc = new Scanner(System.in);
            try {
                amount = new BigDecimal(sc.nextLine());
                break;
            } catch (Exception e) {
                System.out.print("Wrong input - " + e.getCause() + "\nTry again: ");
            }
        }
        Set<Long> listIdAccount = tc.getIdAccounts();
        System.out.println("Current ID account:");
        for (Long id : listIdAccount) {
            System.out.println("\t" + id);
        }

        long id = 0;
        System.out.print("Choice ID account. ");
        do{
            id = findId();
        } while (!listIdAccount.contains(id));
        Account account = tc.getAccount(id);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        String strDate = sdf.format(new Date());
        Date date = null;
        try {
            date = sdf.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(date);
        transaction.setAmount(amount);
        transaction.setAccount(account);
        transaction.setCreated(date);
        transaction.setStatus(TransactionStatus.REVIEW);

        return transaction;
    }

}
