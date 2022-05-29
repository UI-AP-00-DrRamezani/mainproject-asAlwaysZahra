package Requests;

import Accounts.Account;
import Accounts.AdminPanel;
import Enums.Status;

public class ChangeRequest {
    private static int ID_COUNTER = 1;

    private int id;
    private Account oldAccount;
    private Account newAccount;
    private Status stat;

    public ChangeRequest(Account oldAccount, Account newAccount) {
        this.id = ID_COUNTER++;
        this.oldAccount = oldAccount;
        this.newAccount = newAccount;
        this.stat = Status.WAITING;

        AdminPanel.changeRequests.add(this);
    }

    public void printRequest () {

        System.out.println("OLD ACCOUNT INFORMATION");
        this.oldAccount.showInfo();

        System.out.println("|||||||||||||||||||||||||||||||||||");

        System.out.println("NEW ACCOUNT INFORMATION");
        this.oldAccount.showInfo();

        System.out.println("*"+this.getStat());
        System.out.println("-----------------------------------");

    }

    // setters and getters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Account getOldAccount() {
        return oldAccount;
    }

    public void setOldAccount(Account oldAccount) {
        this.oldAccount = oldAccount;
    }

    public Account getNewAccount() {
        return newAccount;
    }

    public void setNewAccount(Account newAccount) {
        this.newAccount = newAccount;
    }

    public Status getStat() {
        return stat;
    }

    public void setStat(Status stat) {
        this.stat = stat;
    }
}