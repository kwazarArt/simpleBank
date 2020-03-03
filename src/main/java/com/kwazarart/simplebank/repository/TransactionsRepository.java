package main.java.com.kwazarart.simplebank.repository;

import main.java.com.kwazarart.simplebank.model.Account;
import main.java.com.kwazarart.simplebank.model.AccountStatus;
import main.java.com.kwazarart.simplebank.model.Transaction;
import main.java.com.kwazarart.simplebank.model.TransactionStatus;

import java.io.*;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class TransactionsRepository {

    static final String  PATH_TO_TRANSACTIONS = "src\\main\\resources\\files\\transactions.txt";
    public AccountRepository ar = new AccountRepository();

    public void save(Transaction transaction) {
        try (FileWriter fw = new FileWriter(PATH_TO_TRANSACTIONS, true)) {
            fw.write(transactionToString(transaction) + "\n");
            System.out.println("Saving complete!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Transaction> getAll() {
        List<Transaction> transactions = new LinkedList<>();
        List<String> transactionString = readTransacions();
        for (int i = 0; i < transactionString.size(); i++) {
            String line[] = transactionString.get(i).split("\t");
            transactions.add(stringToTransaction(line));
        }
        return transactions;
    }

    public List<Transaction> getTransactions(long idAccount)  {
        List<Transaction> listForOneId = new LinkedList<>();
        List<Transaction> listAllAccounts = null;
        listAllAccounts = getAll();
        for (Transaction transaction : listAllAccounts) {
            long id = transaction.getAccount().getId();
            if (id == idAccount)
                listForOneId.add(transaction);
        }
        return listForOneId;
    }

    public void update(Transaction transaction) {
        boolean checkChanges = false;
        List<String> transactionString = readTransacions();
        for (int i = 0; i < transactionString.size(); i++) {
            String line[] = transactionString.get(i).split("\t");
            if (line[1].equals(String.valueOf(transaction.getAccount().getId()))) {
                transactionString.remove(i);
                transactionString.add(i, transactionToString(transaction));
                checkChanges = true;
                break;
            }
        }
        if (checkChanges) {
            writeToRepo(transactionString);
        } else {
            System.out.println("ID not found");
        }
    }

    public void deleteById(long id, Date date) {
        List<String> transactionString = readTransacions();
        for (int i = 0; i < transactionString.size(); i++) {
            String line[] = transactionString.get(i).split("\t");
            if (line[1].equals(String.valueOf(id)) && line[4].equals(date)) {
                transactionString.remove(i);
                line[5] = "BLOCKED";
                transactionString.add(i, line[0] +
                        "\t" + line[1] +
                        "\t" + line[2] +
                        "\t" + line[3] +
                        "\t" + line[4] +
                        "\t" + line[5]);
            }
        }
        writeToRepo(transactionString);
    }

    private String transactionToString(Transaction transaction) {
        return transaction.getAmount() + "\t"
                + transaction.getAccount() + "\t"
                + transaction.getCreated() + "\t"
                + transaction.getStatus();
    }

    private Transaction stringToTransaction(String transactionString[]){

        SimpleDateFormat sdf  = new SimpleDateFormat("E MMM MM HH:mm:ss zzz YYYY", Locale.US);
        Date date = null;
        try {
            date = sdf.parse(transactionString[4]);
        } catch (ParseException e) {
            System.out.println("Wrong date!");
            e.printStackTrace();
        }
        return new Transaction(new BigDecimal(transactionString[0]),
                new Account(Long.parseLong(transactionString[1]),
                            new BigDecimal(transactionString[2]),
                            AccountStatus.valueOf(transactionString[3])),
                date,
                TransactionStatus.valueOf(transactionString[5]));
    }

    private List<String> readTransacions() {
        List<String> transactions = new LinkedList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(PATH_TO_TRANSACTIONS))){
            String line;
            while ((line = reader.readLine()) != null) {
                transactions.add(line);
            }
            return transactions;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void writeToRepo(List<String> transactions) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PATH_TO_TRANSACTIONS))){
            for (String accountLine : transactions) {
                writer.write(accountLine + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public AccountRepository getAr() {
        return ar;
    }

    public Set<Long> getAllIdAccount() {
        List<Transaction> listTransactions = getAll();
        Set<Long> listLong = new LinkedHashSet<>();
        for (Transaction transaction : listTransactions){
            listLong.add(transaction.getAccount().getId());
        }
        return listLong;
    }
}
