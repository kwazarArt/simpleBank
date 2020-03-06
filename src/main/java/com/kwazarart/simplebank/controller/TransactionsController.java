package main.java.com.kwazarart.simplebank.controller;

import main.java.com.kwazarart.simplebank.model.Account;
import main.java.com.kwazarart.simplebank.model.Transaction;
import main.java.com.kwazarart.simplebank.repository.TransactionsRepository;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class TransactionsController {
    private TransactionsRepository tr = new TransactionsRepository();

    public void controlCreate(Transaction transaction) {
        tr.save(transaction);
    }

    public List<Transaction> controlPrinById(long id){
        return tr.getTransactions(id);
    }

    public void controlUpdate(Transaction transaction) {
        tr.update(transaction);
    }

    public void controlDelete(long id) {
        tr.deleteById(id);
    }

    public List<Transaction> getAllTransactions()  {
        return tr.getAll();
    }

    public Account getAccount(long id) {
        return tr.getAr().getById(id);
    }

    public Set<Long> getIdAccounts() {
        return tr.getAllIdAccount();
    }

}
