package controller;

import model.Account;
import repository.AccountRepository;

public class Main {
    public static void main(String[] args) {

        // FOR TESTING
        AccountRepository ac = new AccountRepository();
        Account acc1 = new Account(AccountRepository.searchMaxIndex());
        ac.save(acc1);
        Account acc2 = new Account(AccountRepository.searchMaxIndex());
        ac.save(acc2);
        Account acc3 = new Account(AccountRepository.searchMaxIndex());
        ac.save(acc3);
        Account acc4 = new Account(AccountRepository.searchMaxIndex());
        ac.save(acc4);

        ac.deleteById(2);
    }
}
