package UserInterface;

import Accounts.Account;

public class Login {

    private Account account;
    public boolean loggedIn = false;

    public static void login(String user, String pass) {
        boolean findUser = false;
        boolean correctPass = false;
        for (Account acc: Account.allAccounts) {
            if (acc.getUsername().compareTo(user) == 0) {
                findUser = true;
                if (acc.getPassword().compareTo(pass) == 0) {
                    correctPass = true;
                    System.out.println("\nLogin Successfully...");
                    Menu.checkLogin.setAccount(acc);
                    Menu.checkLogin.loggedIn = true;
                    acc.showInfo();
                    acc.menu();
                    return;
                }
            }
        }
        if(!findUser)
            System.out.println("Your username could not be found! Please try again.");
        else if (!correctPass)
            System.out.println("Wrong password!");

        Menu.firstMenu();
    }

    // Setters and Getters
    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
