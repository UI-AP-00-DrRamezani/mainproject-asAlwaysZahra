package Accounts.Admin;

import Accounts.Account;

public class Admin extends Account {

    private static Admin admin = Admin.getAdmin("zahra", "masoumi", "email@email.com", "09123456789");

    private Admin(String firstName, String lastName, String email, String phoneNumber) {

        super("admin", firstName, lastName, email, phoneNumber, "admin");
        super.setAccountType("Admin");
    }

    public static Admin getAdmin(String firstName, String lastName, String email, String phoneNumber) {
        if (admin == null)
            admin = new Admin(firstName, lastName, email, phoneNumber);
        return admin;
    }

    public static Admin getAdmin() {
        return admin;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "name='" + super.getFirstName() + " " + super.getLastName() + '\'' +
                ", username='" + super.getUsername() + '\'' +
                ", password='" + super.getPassword() + '\'' +
                ", email='" + super.getEmail() + '\'' +
                ", phone='" + super.getPhoneNumber() + '\'' +
                '}';
    }
}
