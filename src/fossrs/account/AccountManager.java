package fossrs.account;

import java.util.HashMap;
import java.util.ArrayList;
import java.io.*;
import fossrs.account.*;

public class AccountManager {
    private HashMap<String, OSRSAccount> accounts;
    
    public AccountManager() {
        accounts = new HashMap<String, OSRSAccount>();
        try {
            loadLocalAccounts();
        } catch (Exception e) {
            System.err.println("Could not load accounts");
            e.printStackTrace();
        }
    }

    //Does not overwrite by default
    public AccountManager(HashMap<String, OSRSAccount> newAccounts) {
        this(newAccounts, false);
    }
    //Loads accounts provided, overwriting old ones is specified
    public AccountManager(HashMap<String, OSRSAccount> newAccounts, boolean overwrite) {
        accounts = new HashMap<String, OSRSAccount>(newAccounts);
        try {
            loadLocalAccounts(overwrite);
        } catch (Exception e) {
            System.err.println("Could not load accounts");
            e.printStackTrace();
        }
    }

    public void addAccount(OSRSAccount account) {
        accounts.put(account.getUserName(), account);
    }

    public OSRSAccount getAccount(String userName) {
        return accounts.get(userName);
    }

    public HashMap<String, OSRSAccount> getAccounts() { return accounts; }

    public void saveLocalAccounts() throws IOException {
        for (String userName : accounts.keySet()) {
            OSRSAccount account = accounts.get(userName);
            ObjectOutputStream oos = null;
            FileOutputStream fout = null;
            try {
                fout = new FileOutputStream(String.format("saved/%s", userName), false);
                oos = new ObjectOutputStream(fout);
                oos.writeObject(account);
                System.out.println(String.format("Saved %s!", account.getUserName()));
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (oos != null) {
                    oos.close();
                }
                if (fout != null) {
                    fout.close();
                }
            }
        }
    }

    //Does not overwrite by default
    public void loadLocalAccounts() throws IOException {
        loadLocalAccounts(false);
    }

    public void loadLocalAccounts(boolean overwrite) throws IOException {
        FileInputStream fs = null;
        ObjectInputStream ois = null;
        File accountsFolder = new File("saved");
        File[] savedAccounts = accountsFolder.listFiles();
        for (File savedAccountFile : savedAccounts) {
            try {
            fs = new FileInputStream(savedAccountFile);
            ois = new ObjectInputStream(fs);
            OSRSAccount acc = (OSRSAccount) ois.readObject();
            if (acc != null) {
                if (overwrite) {
                    accounts.put(acc.getUserName(), acc);
                } else {
                    OSRSAccount savedAccount = accounts.get(acc.getUserName());
                    //If there already was an account with this name
                    if (savedAccount == null) {
                        accounts.put(acc.getUserName(), acc);
                    } else {
                        savedAccount.loadStatsFrom(savedAccount);
                    }
                }
                System.out.printf("Loaded %s\n", acc.getUserName());
            }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (fs != null) {
                    fs.close();
                }
                if (ois != null) {
                    ois.close();
                }
            }
        }
    }
}