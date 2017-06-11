package fossrs;

import fossrs.account.*;
import java.util.HashMap;

public class ListAndSaveAccounts {
    public static void main(String[] args) {
        HashMap<String, OSRSAccount> downloadedAccounts = new HashMap<String, OSRSAccount>();
        for (String userName : args) {
            OSRSAccount acc = new OSRSAccount(userName);
            try {
                acc.downloadSkills(true);
                downloadedAccounts.put(userName, acc);
            } catch (Exception e) {
                System.err.printf("Could not load %s\n", userName);
            }
        }
        AccountManager accounts;
        try {
            accounts = new AccountManager(downloadedAccounts);
            accounts.saveLocalAccounts();
            for (OSRSAccount acc : accounts.getAccounts().values()) {
                System.out.printf("Got account %s\n", acc.getUserName());
                acc.printStats();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}