package main.java.com.kwazarart.simplebank.model;

import java.math.BigDecimal;

public class Account {
    private BigDecimal balance;
    private AccountStatus status;
    private long id;


    public Account(long id) {
        this.balance = new BigDecimal("0");
        this.status = AccountStatus.ACTIVE;
        this.id =  id;
    }

    public Account(long id, BigDecimal balance, AccountStatus status) {
        this.id = id;
        this.balance = balance;
        this.status = status;
    }

    public BigDecimal getBalance() {
        return balance;
    }


    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return id + "\t" + balance + "\t" + status;
    }
}
