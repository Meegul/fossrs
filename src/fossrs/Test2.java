package fossrs;

import fossrs.account.*;

public class Test2 {
    public static void main(String[] args) {
        String accountName = args[0];
        OSRSAccount acc = new OSRSAccount(accountName);
        try {
            acc.downloadSkills();
        } catch (Exception e) {
            e.printStackTrace();
        }
        acc.printStats();
    }
}