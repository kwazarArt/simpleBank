package main.java.com.kwazarart.simplebank.controller;

import main.java.com.kwazarart.simplebank.model.Account;
import main.java.com.kwazarart.simplebank.model.AccountStatus;
import main.java.com.kwazarart.simplebank.model.Customer;
import main.java.com.kwazarart.simplebank.repository.AccountRepository;
import main.java.com.kwazarart.simplebank.repository.CustomerRepository;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class CustomersController {
    private CustomerRepository cr = new CustomerRepository();

    public void controlCreate(Customer customer) {
        cr.save(customer);
    }

    public void controlPrintById(long x) {
        System.out.println(cr.getById(x));
    }

    public void controlUpdate(Customer customer) {
        cr.update(customer);
    }

    public void controlDeelte(long x) {
        cr.deleteById(x);
    }

    public void controlPrintAll() {
        List<Customer> list = cr.getAll();
        for (Customer line : list) {
            System.out.println(line.getId() + "\t" + line.getFirstName() + "\t" + line.getSecondName() + "\t" + line.getAccount());
        }
    }

}
