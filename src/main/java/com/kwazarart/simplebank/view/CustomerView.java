package main.java.com.kwazarart.simplebank.view;

import main.java.com.kwazarart.simplebank.controller.CustomersController;
import main.java.com.kwazarart.simplebank.model.Account;
import main.java.com.kwazarart.simplebank.model.AccountStatus;
import main.java.com.kwazarart.simplebank.model.Customer;
import main.java.com.kwazarart.simplebank.repository.CustomerRepository;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CustomerView {
    private CustomersController cc = new CustomersController();
    public void viewCustomerMenu(){
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

    public void viewCreateCustomer() {
        cc.controlCreate(newCustomer());
    }

    public void viewPrintCustomer() {
        cc.controlPrintById(findId());
    }

    public void viewUpdateCustomer() {
        cc.controlUpdate(newCustomer());
    }

    public void viewDeleteCustomer() {
        System.out.println("Which customer will be deleted?");
        cc.controlDeelte(findId());
    }

    public void viewAllCustomer() {
        cc.controlPrintAll();
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

    private Customer newCustomer() {
        System.out.print("Enter first name: ");
        String firstName = new Scanner(System.in).nextLine();
        System.out.println();
        System.out.print("Enter second name: ");
        String secondName = new Scanner(System.in).nextLine();
        Account account = new Account(0, new BigDecimal(0), AccountStatus.ACTIVE);
        return new Customer(findId(), firstName, secondName, account);
    }
}
