package com.example.demo1.DataBase;

import com.example.demo1.Accounts.Customer.Customer;
import com.example.demo1.Accounts.Customer.CustomerManager;
import com.example.demo1.Products.Product.Product;
import com.example.demo1.Products.Product.ProductManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class CartsTable {

    private static final String url = "jdbc:mysql://localhost/shopDataBase";
    private static final String user = "zahra";
    private static final String pass = "zahra";

    // Methods ---------------------------------------------------------------------
    public static void insert(List<Product> cart) {}

    // -----------------------------------------------------------------------------
    public static void readCarts() {
        try (Connection connection = DriverManager.getConnection(url, user, pass)) {

            Class.forName("com.mysql.cj.jdbc.Driver");

            for (Customer c : CustomerManager.allCustomers) {

                String sql = String.format("SELECT * FROM `carts` INNER JOIN `products` ON carts.productID=products.id WHERE carts.username='%s'", c.getUsername());
                Statement s = connection.prepareStatement(sql);
                ResultSet rs = s.executeQuery(sql);

                while (rs.next()) {
                    int id = rs.getInt("productID");
                    for (Product p : ProductManager.allProducts)
                        if (p.getId() == id) {
                            c.addToCart(p);
                            break;
                        }
                }
            }

        } catch (Exception e) {
            System.out.println("carts table: " + e.getMessage()); //todo
        }


    }
}
