package com.example.demo1.DataBase;

import com.example.demo1.Accounts.Account;
import com.example.demo1.Accounts.Admin.Admin;
import com.example.demo1.Accounts.Admin.AdminManager;
import com.example.demo1.Accounts.Customer.Customer;
import com.example.demo1.Accounts.Customer.CustomerManager;
import com.example.demo1.Accounts.Seller.Seller;
import com.example.demo1.Accounts.Seller.SellerManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class UsersTable {

    private static final String url = "jdbc:mysql://localhost/shopDataBase";
    private static final String user = "zahra";
    private static final String pass = "zahra";

    // Methods ---------------------------------------------------------------------
    public static void insert(Account acc) {

        try (Connection connection = DriverManager.getConnection(url, user, pass)) {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Statement s = connection.prepareStatement(acc.insertUser());
            s.execute(acc.insertUser());

            if (acc instanceof Customer) {

                String sql = String.format("INSERT INTO credits (username, credit) VALUES ('%s', %f);",
                        acc.getUsername(), ((Customer) acc).getCredit());
                s = connection.prepareStatement(sql);
                s.execute(sql);

            } else if (acc instanceof Seller) {

                String sql = String.format("INSERT INTO companies (username, company) VALUES ('%s', '%s');",
                        acc.getUsername(), ((Seller) acc).getCompany());
                s = connection.prepareStatement(sql);
                s.execute(sql);
            }

        } catch (Exception e) {
//            System.out.println(e.getMessage());//todo
            e.printStackTrace();
        }
    }

    // -----------------------------------------------------------------------------
    public static void update(Account acc, String oldUser) {

        try (Connection connection = DriverManager.getConnection(url, user, pass)) {

            Class.forName("com.mysql.cj.jdbc.Driver");

            String sql = acc.updateUser() + '\'' + oldUser + '\'';
            Statement s = connection.prepareStatement(sql);
            s.execute(sql);

        } catch (Exception e) {
            System.out.println(e.getMessage());//todo
        }
    }

    // -----------------------------------------------------------------------------
    public static void delete(Account acc) {

        try (Connection connection = DriverManager.getConnection(url, user, pass)) {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Statement s = connection.prepareStatement(acc.deleteUser());
            s.execute(acc.deleteUser());

        } catch (Exception e) {
            System.out.println(e.getMessage());//todo
        }
    }

    // -----------------------------------------------------------------------------
    public static void readUsers() {
        try (Connection connection = DriverManager.getConnection(url, user, pass)) {

            Class.forName("com.mysql.cj.jdbc.Driver");

            String sql = "SELECT * FROM users " +
                    "INNER JOIN emails ON users.username = emails.username " +
                    "INNER JOIN phonenumbers ON users.username = phonenumbers.username;";

            Statement s = connection.prepareStatement(sql);
            ResultSet rs = s.executeQuery(sql);

            while (rs.next()) {

                String type = rs.getString("type");
                String username = rs.getString("username");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String password = rs.getString("password");
                String email = rs.getString("email");
                String phoneNumber = rs.getString("phoneNumber");
//                System.out.println(type + " " + username + " " + firstName + " " + lastName + " " + password + " " + email + " " + phoneNumber);

                switch (type) {

                    case "Admin" -> AdminManager.allUsers.add(Admin.getAdmin(firstName, lastName, email, phoneNumber));

                    case "Seller" -> {
                        String sql2 = String.format("SELECT company FROM companies WHERE username=\"%s\";", username);
                        Statement s2 = connection.prepareStatement(sql2);
                        ResultSet rs2 = s2.executeQuery(sql2);

                        String company = "";
                        while (rs2.next())
                            company = rs2.getString("company");

//                        adminManager.acceptSeller(new Seller(username, firstName, lastName, email, phoneNumber, password, company));
                        Seller seller = new Seller(username, firstName, lastName, email, phoneNumber, password, company);
                        AdminManager.allUsers.add(seller);
                        SellerManager.allSellers.add(seller);
                    }

                    case "Customer" -> {
                        String sql2 = String.format("SELECT credit FROM credits WHERE username=\"%s\";", username);
                        Statement s2 = connection.prepareStatement(sql2);
                        ResultSet rs2 = s2.executeQuery(sql2);

                        double credit = 0;
                        while (rs2.next())
                            credit = rs2.getDouble("credit");

//                        CustomerManager.addCustomer(username, firstName, lastName, email, phoneNumber, password, credit);
                        Customer newCustomer = new Customer(username, firstName, lastName, email, phoneNumber, password, credit);
                        CustomerManager.allCustomers.add(newCustomer);
                        AdminManager.allUsers.add(newCustomer);
                    }
                }
            }

        } catch (Exception e) {

//            System.out.println(e.getMessage());// todo
            e.printStackTrace();
        }
    }
}
