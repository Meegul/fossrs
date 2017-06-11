package fossrs;

import fossrs.account.*;
import java.util.HashMap;

public class ShowSavedAccountsHistories {
    public static void main(String[] args) {
        try {
            AccountManager accounts = new AccountManager();
            for (OSRSAccount acc : accounts.getAccounts().values()) {
                System.out.printf("Got account %s\n", acc.getUserName());
                acc.printHistory();
                acc.downloadSkills(false);
            }
            accounts.saveLocalAccounts();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}