package com.example.demo1.Accounts.Admin;

import com.example.demo1.Accounts.Account;

public class Admin extends Account {

    private static Admin admin = Admin.getAdmin("zahra", "masoumi", "email@email.com", "09123456789");

    private Admin(String firstName, String lastName, String email, String phoneNumber) {

        super("admin", firstName, lastName, email, phoneNumber, "admin");
        super.setAccountType("Admin");
    }

    public static Admin getAdmin(String firstName, String lastName, String email, String phoneNumber) {
        if (admin == null) {
            admin = new Admin(firstName, lastName, email, phoneNumber);
//            UsersTable.insert(admin);
        }
        return admin;
    }

    public static Admin getAdmin() {
        return admin;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "username='" + getUsername() + '\'' +
                ", firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", phoneNumber='" + getPhoneNumber() + '\'' +
                ", password='" + getPassword() + '\'' +
                '}';
    }
}
