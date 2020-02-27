package repository;

import model.Account;
import model.AccountStatus;

import java.io.*;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

public class AccountRepository {
    private List<Account> accounts;

    public AccountRepository() {
        this.accounts = new LinkedList<>();
    }

    public void save(Account account) {
        try (FileWriter fw = new FileWriter("accounts.txt", true)) {
            fw.write(String.valueOf(account.getIdAccount()) + "\t" + account.getBalance().toString() + "\t" + account.getStatus().toString() + "\n");
            System.out.println("Saving complete!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Account> getAll() {
        List<Account> accounts = new LinkedList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("accounts.txt"))){
            String line;
            while ((line = reader.readLine()) != null) {
                String accountString[] = line.split("\t");
                accounts.add(new Account(Long.parseLong(accountString[0]), new BigDecimal(accountString[1]), AccountStatus.valueOf(accountString[2])));
                return accounts;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Account getById(long id) {
        try (BufferedReader reader = new BufferedReader(new FileReader("accounts.txt"))){
            String line;
            String[] accountString;
            String num = String.valueOf(id);
            while ((line = reader.readLine()) != null) {
                accountString = line.split("\t");
                if (accountString[0].equals(num)) {
                    return new Account(Long.parseLong(accountString[0]), new BigDecimal(accountString[1]), AccountStatus.valueOf(accountString[2]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(Account account) {
        List<Account> accounts = new LinkedList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("accounts.txt"))){
            String line;
            String[] accountString;
            while ((line = reader.readLine()) != null) {
                accountString = line.split("\t");
                if (accountString[0].equals(String.valueOf(account.getIdAccount()))) {
                    accounts.add(account);
                    continue;
                }
                accounts.add(new Account(Long.parseLong(accountString[0]), new BigDecimal(accountString[1]), AccountStatus.valueOf(accountString[2])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("accounts.txt"))){
            for (Account accountLine : accounts) {
                writer.write(accountLine.getIdAccount() + "\t" + accountLine.getBalance().toString() + "\t" + accountLine.getStatus().toString() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteById(long id) {
        List<Account> accounts = new LinkedList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("accounts.txt"))){
            String line;
            String[] accountString;
            while ((line = reader.readLine()) != null) {
                accountString = line.split("\t");
                if (accountString[0].equals(String.valueOf(id))) {
                    continue;
                }
                accounts.add(new Account(Long.parseLong(accountString[0]), new BigDecimal(accountString[1]), AccountStatus.valueOf(accountString[2])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("accounts.txt"))){
            for (Account accountLine : accounts) {
                writer.write(accountLine.getIdAccount() + "\t" + accountLine.getBalance().toString() + "\t" + accountLine.getStatus().toString() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
