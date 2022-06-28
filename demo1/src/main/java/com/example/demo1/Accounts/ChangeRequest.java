package com.example.demo1.Accounts;

import com.example.demo1.Accounts.Admin.AdminManager;

public class ChangeRequest {

    private final Account oldInfo;
    private final Account newInfo;

    public ChangeRequest(Account oldInfo, Account newInfo) {
        this.oldInfo = oldInfo;
        this.newInfo = newInfo;

        AdminManager.editInfoRequests.add(this);
    }

    // Getters and Setters ================================================

    public Account getOldInfo() {
        return oldInfo;
    }

    public Account getNewInfo() {
        return newInfo;
    }
}