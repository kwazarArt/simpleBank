package main.java.com.kwazarart.simplebank.controller;

import main.java.com.kwazarart.simplebank.model.Account;
import main.java.com.kwazarart.simplebank.repository.AccountRepository;

import java.util.List;

public class AccountsController {
    private AccountRepository ar = new AccountRepository();

    public void controlCreate(Account account) {
        ar.save(account);
    }

    public void controlPrintById(long x) {
        System.out.println(ar.getById(x));
    }

    public void controlUpdate(Account account) {
        ar.update(account);
    }

    public void controlDeelte(long x) {
        ar.deleteById(x);
    }

    public void controlPrintAll() {
        List<Account> list = ar.getAll();
        for (Account line : list) {
            System.out.println(line.getId() + "\t" + line.getBalance() + "\t" + line.getStatus());
        }
    }

}
