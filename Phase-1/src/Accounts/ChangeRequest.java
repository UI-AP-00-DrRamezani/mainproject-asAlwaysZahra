package Accounts;

import Accounts.Admin.AdminManager;

public class ChangeRequest {

    private Account oldInfo;
    private Account newInfo;

    public ChangeRequest(Account oldInfo, Account newInfo) {
        this.oldInfo = oldInfo;
        this.newInfo = newInfo;

        AdminManager.editInfoRequests.add(this);
    }

    // Getters and Setters ================================================

    public Account getOldInfo() {
        return oldInfo;
    }

    public void setOldInfo(Account oldInfo) {
        this.oldInfo = oldInfo;
    }

    public Account getNewInfo() {
        return newInfo;
    }

    public void setNewInfo(Account newInfo) {
        this.newInfo = newInfo;
    }
}