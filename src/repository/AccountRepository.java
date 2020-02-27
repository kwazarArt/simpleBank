package repository;

import model.Account;
import model.AccountStatus;

import java.io.*;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

public class AccountRepository {

    static final String  PATH_TO_ACCOUNT = "accounts.txt";

    public static void save(Account account) {
        try (FileWriter fw = new FileWriter(PATH_TO_ACCOUNT, true)) {
            fw.write(accountToString(account) + "\n");
            System.out.println("Saving complete!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Account> getAll() {
        List<Account> accounts = new LinkedList<>();
        List<String> accountString = readAccounts();
        for (int i = 0; i < accountString.size(); i++) {
            String line[] = accountString.get(i).split("\t");
            accounts.add(stringToAccount(line));
        }
        return accounts;
    }

    public static Account getById(long id) {
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

    public static void update(Account account) {
        List<String> accountString = readAccounts();
        for (int i = 0; i < accountString.size(); i++) {
            String line[] = accountString.get(i).split("\t");
            if (line[0].equals(String.valueOf(account.getId()))) {
                accountString.remove(i);
                accountString.add(i, accountToString(account));
            }
        }
        writeToRepo(accountString);
    }

    public static void deleteById(long id) {
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

    private static Account stringToAccount(String accountString[]) {
        return new Account(Long.parseLong(accountString[0]), new BigDecimal(accountString[1]), AccountStatus.valueOf(accountString[2]));
    }

    private static String accountToString(Account account) {
        return String.valueOf(account.getId()) + "\t" + account.getBalance().toString() + "\t" + account.getStatus().toString();
    }

    private static void writeToRepo(List<String> accounts) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PATH_TO_ACCOUNT))){
            for (String accountLine : accounts) {
                writer.write(accountLine + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<String> readAccounts() {
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

    public static long searchMaxIndex() {
        List<String> accountLines = readAccounts();

        return accountLines.size() + 1;
    }
}
