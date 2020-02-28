package repository;

import model.Account;
import model.AccountStatus;

import java.io.*;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

public class AccountRepository {

    static final String  PATH_TO_ACCOUNT = "src\\main\\resources\\files\\accounts.txt";

    public void save(Account account) {
        account.setId(searchMaxIndex());
        try (FileWriter fw = new FileWriter(PATH_TO_ACCOUNT, true)) {
            fw.write(accountToString(account) + "\n");
            System.out.println("Saving complete!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Account> getAll() {
        List<Account> accounts = new LinkedList<>();
        List<String> accountString = readAccounts();
        for (int i = 0; i < accountString.size(); i++) {
            String line[] = accountString.get(i).split("\t");
            accounts.add(stringToAccount(line));
        }
        return accounts;
    }

    public Account getById(long id) {
        List<String> accountString = readAccounts();
        for (int i = 0; i < accountString.size(); i++) {
            String line[] = accountString.get(i).split("\t");
            if (line[0].equals(String.valueOf(id))) {
                return stringToAccount(line);
            }
        }
        System.out.println("Not found - ID");
        return null;
    }

    public void update(Account account) {
        boolean checkChanges = false;
        List<String> accountString = readAccounts();
        for (int i = 0; i < accountString.size(); i++) {
            String line[] = accountString.get(i).split("\t");
            if (line[0].equals(String.valueOf(account.getId()))) {
                accountString.remove(i);
                accountString.add(i, accountToString(account));
                checkChanges = true;
                break;
            }
        }
        if (checkChanges) {
            writeToRepo(accountString);
        } else {
            System.out.println("ID not found");
        }
    }

    public void deleteById(long id) {
        List<String> accountString = readAccounts();
        for (int i = 0; i < accountString.size(); i++) {
            String line[] = accountString.get(i).split("\t");
            if (line[0].equals(String.valueOf(id))) {
                accountString.remove(i);
                line[2] = "DELETED";
                accountString.add(i, line[0] + "\t" + line[1] + "\t" + line[2]);
            }
        }
        writeToRepo(accountString);
    }

    private Account stringToAccount(String accountString[]) {
        return new Account(Long.parseLong(accountString[0]), new BigDecimal(accountString[1]), AccountStatus.valueOf(accountString[2]));
    }

    private String accountToString(Account account) {
        return String.valueOf(account.getId()) + "\t" + account.getBalance().toString() + "\t" + account.getStatus().toString();
    }

    private void writeToRepo(List<String> accounts) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PATH_TO_ACCOUNT))){
            for (String accountLine : accounts) {
                writer.write(accountLine + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<String> readAccounts() {
        List<String> accounts = new LinkedList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(PATH_TO_ACCOUNT))){
            String line;
            while ((line = reader.readLine()) != null) {
                accounts.add(line);
            }
            return accounts;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public long searchMaxIndex() {
        List<String> accountLines = readAccounts();
        return accountLines.size() + 1;
    }
}
