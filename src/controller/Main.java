package controller;

import model.Account;
import repository.AccountRepository;

public class Main {
    public static void main(String[] args) {

        // FOR TESTING
        Account acc1 = new Account();
        Account acc2 = new Account();
        Account acc3 = new Account();
        Account acc4 = new Account();


        AccountRepository ac = new AccountRepository();
        ac.save(acc1);
        ac.save(acc2);
        ac.save(acc3);
        ac.save(acc4);

        ac.getAll();
        System.out.println();
        ac.getById(acc2.getIdAccount());
        ac.update(acc2);
    }
}
