package controller;

import model.Account;
import model.AccountStatus;
import repository.AccountRepository;
import view.ViewerAccount;

import java.math.BigDecimal;

public class ControllerAccount {
    private AccountRepository ar = new AccountRepository();

    public void controlAccount(int x) {
        ViewerAccount va = new ViewerAccount();
        switch (x) {
            case 1:
                String balanceAndStatusForCreate[] = va.viewCreateAccount();
                ar.save(new Account(ar.searchMaxIndex(),
                        new BigDecimal(balanceAndStatusForCreate[0]),
                        AccountStatus.valueOf(balanceAndStatusForCreate[1])));
                break;
            case 2:
                long idForPrint = va.viewPrintAccount();
                System.out.println(ar.getById(idForPrint));
                break;
            case 3:
                String accountData[] = va.viewUpdateAccount();
                ar.update(new Account(Long.parseLong(accountData[0]),
                        new BigDecimal(accountData[1]),
                        AccountStatus.valueOf(accountData[2])));
                break;
            case 4:
                long idForDelete = va.viewDeleteAccount();
                ar.deleteById(idForDelete);
                break;
        }
    }
}
