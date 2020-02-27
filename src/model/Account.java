package model;

import java.math.BigDecimal;

public class Account {
    private BigDecimal balance;
    private AccountStatus status;
    private long idAccount;
    private static long id = 1;

    public Account() {
        this.balance = new BigDecimal("0");
        this.status = AccountStatus.ACTIVE;
        this.idAccount = id;
        id++;
    }

    public Account(long idAccount, BigDecimal balance, AccountStatus status) {
        this.idAccount = idAccount;
        this.balance = balance;
        this.status = status;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public long getIdAccount() {
        return idAccount;
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

    public static long getId() {
        return id;
    }

    public static void setId(long id) {
        Account.id = id;
    }

    @Override
    public String toString() {
        return "Account{" +
                "balance=" + balance +
                ", status=" + status +
                ", idAccount=" + idAccount +
                '}';
    }
}
