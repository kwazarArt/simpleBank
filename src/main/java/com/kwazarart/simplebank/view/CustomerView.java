package main.java.com.kwazarart.simplebank.view;

import main.java.com.kwazarart.simplebank.controller.CustomersController;
import main.java.com.kwazarart.simplebank.model.Account;
import main.java.com.kwazarart.simplebank.model.AccountStatus;
import main.java.com.kwazarart.simplebank.model.Customer;
import main.java.com.kwazarart.simplebank.model.Transaction;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

class CustomerView {
    private CustomersController cc = new CustomersController();
    void viewCustomerMenu(){
        while (true) {
            String choice;
            int x;
            while (true) {
                System.out.println();
                System.out.println("1 - Create customer");
                System.out.println("2 - Print customer");
                System.out.println("3 - Update customer");
                System.out.println("4 - Delete customer");
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
                    viewCreateCustomer();
                    break;
                case 2:
                    viewPrintCustomer();
                    break;
                case 3:
                    viewUpdateCustomer();
                    break;
                case 4:
                    viewDeleteCustomer();
                    break;
                case 5:
                    viewAllCustomer();
                    break;
            }
        }
    }

    private void viewCreateCustomer() {
        cc.controlCreate(newCustomer());
    }

    private void viewPrintCustomer() {
        System.out.println(cc.controlPrintById(findId()));
    }

    private void viewUpdateCustomer() {
        System.out.print("Customer. ");
        long id = findId();
        Customer customer = newCustomer();
        customer.setId(id);
        cc.controlUpdate(customer);
    }

    private void viewDeleteCustomer() {
        System.out.println("Which customer will be deleted?");
        cc.controlDeelte(findId());
    }

    private void viewAllCustomer() {
        List<Customer> list = cc.controlPrintAll();
        for (Customer line : list) {
            System.out.println(line.getId() + "\t" + line.getFirstName() + "\t" + line.getSecondName() + "\t" + line.getAccount().getId());
        }
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

    private Customer newCustomer() {
        System.out.print("Enter first name: ");
        String firstName = new Scanner(System.in).nextLine();
        System.out.println();
        System.out.print("Enter second name: ");
        String secondName = new Scanner(System.in).nextLine();
        List<Long> listIdAccount = cc.getIdAccounts();
        System.out.println("Current ID account:");
        for (Long id : listIdAccount) {
            System.out.println("\t" + id);
        }

        long id = 0;
        System.out.print("Choice ID account. ");
        do{
            id = findId();
        } while (!listIdAccount.contains(id));

        Account account = cc.getAccount(id);
        return new Customer(0, firstName, secondName, account);
    }
}
