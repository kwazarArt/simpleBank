package model;

import java.math.BigDecimal;
import java.util.Date;

public class Transactions {
    private BigDecimal amount;
    private Account account;
    private Date created;
    private TransactionStatus status;
}
