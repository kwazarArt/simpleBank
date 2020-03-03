package main.java.com.kwazarart.simplebank.model;

import java.math.BigDecimal;
import java.util.Date;

public class Transaction {
    private BigDecimal amount;
    private Account account;
    private Date created;
    private TransactionStatus status;

    public Transaction() {
    }

    public Transaction(BigDecimal amount, Account account, Date created, TransactionStatus status) {
        this.amount = amount;
        this.account = account;
        this.created = created;
        this.status = status;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return  amount + "\t"
                + account + "\t"
                + created + "\t"
                + status;
    }
}
