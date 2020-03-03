package main.java.com.kwazarart.simplebank.repository;

import main.java.com.kwazarart.simplebank.model.Account;
import main.java.com.kwazarart.simplebank.model.AccountStatus;
import main.java.com.kwazarart.simplebank.model.Customer;

import java.io.*;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

public class CustomerRepository {
    static final String  PATH_TO_CUSTOMERS = "src\\main\\resources\\files\\customers.txt";
    public AccountRepository ar = new AccountRepository();

    public void save(Customer customer) {
        customer.setId(searchMaxIndex());
        try (FileWriter fw = new FileWriter(PATH_TO_CUSTOMERS, true) ){
            fw.write(customerToString(customer) + "\n");
            System.out.println("Saving complete!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Customer getById(long id) {
        List<String> customersString = readCustomers();
        for (int i = 0; i < customersString.size(); i++) {
            String line[] = customersString.get(i).split("\t");
            if (line[0].equals(String.valueOf(id))) {
                return stringToCustomer(line);
            }
        }
        System.out.println("Not found - ID");
        return null;
    }

    public List<Customer> getAll() {
        List<Customer> customers = new LinkedList<>();
        List<String> customersString = readCustomers();
        for (int i = 0; i < customersString.size(); i++) {
            String line[] = customersString.get(i).split("\t");
            customers.add(stringToCustomer(line));
        }
        return customers;
    }

    public void update(Customer customer) {
        boolean checkChanges = false;
        List<String> customersString = readCustomers();
        for (int i = 0; i < customersString.size(); i++) {
            String line[] = customersString.get(i).split("\t");
            if (line[0].equals(String.valueOf(customer.getId()))) {
                customersString.remove(i);
                customersString.add(i, customerToString(customer));
                checkChanges = true;
                break;
            }
        }
        if (checkChanges) {
            writeToRepo(customersString);
        } else {
            System.out.println("ID not found");
        }
    }

    public void deleteById(long id) {
        List<String> customerString = readCustomers();
        for (int i = 0; i < customerString.size(); i++) {
            String line[] = customerString.get(i).split("\t");
            if (line[0].equals(String.valueOf(id))) {
                customerString.remove(i);
                line[3] = null;
                line[4] = null;
                line[5] = null;
                customerString.add(i, line[0] +
                                "\t" + line[1] +
                                "\t" + line[2] +
                                "\t" + line[3] +
                                "\t" + line[4] +
                                "\t" + line[5]);
            }
        }
        writeToRepo(customerString);
    }

    private void writeToRepo(List<String> customers) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PATH_TO_CUSTOMERS))){
            for (String customerLine : customers) {
                writer.write(customerLine + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Customer stringToCustomer(String customerString[]) {
        if (customerString[3].equals("null") || customerString[4].equals("null") || customerString[5].equals("null"))
            return new Customer(Long.parseLong(customerString[0]),
                    customerString[1],
                    customerString[2], null);
        return new Customer(Long.parseLong(customerString[0]),
                customerString[1],
                customerString[2],
                new Account(Long.parseLong(customerString[3]),
                        new BigDecimal(customerString[4]),
                        AccountStatus.valueOf(customerString[5])));
    }

    private String customerToString(Customer customer) {
        return String.valueOf(customer.getId()) +
                "\t" + customer.getFirstName() +
                "\t" + customer.getSecondName() +
                "\t" + customer.getAccount().toString();
    }

    private List<String> readCustomers() {
        List<String> customers = new LinkedList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(PATH_TO_CUSTOMERS))){
            String line;
            while ((line = reader.readLine()) != null) {
                customers.add(line);
            }
            return customers;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public long searchMaxIndex() {
        List<String> customersLines = readCustomers();
        return customersLines.size() + 1;
    }

    public AccountRepository getAr() {
        return ar;
    }

    public List<Long> getAllIdAccount() {
        return ar.getIdAccounts();
    }
}
