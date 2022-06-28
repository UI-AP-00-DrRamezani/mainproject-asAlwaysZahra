package com.example.demo1.DataBase;

import com.example.demo1.Products.Digital.Laptop;
import com.example.demo1.Products.Digital.Mobile;
import com.example.demo1.Products.Food.Food;
import com.example.demo1.Products.Garment.Clothe;
import com.example.demo1.Products.Garment.Shoes;
import com.example.demo1.Products.Home.Refrigerator;
import com.example.demo1.Products.Home.Stove;
import com.example.demo1.Products.Home.TV;
import com.example.demo1.Products.Product.Product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class ProductsTable {

    private static final String url = "jdbc:mysql://localhost/shopDataBase";
    private static final String user = "zahra";
    private static final String pass = "zahra";

    // Methods ---------------------------------------------------------------------
    public static void insert(Product product) {

        try (Connection connection = DriverManager.getConnection(url, user, pass)) {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Statement s = connection.prepareStatement(product.insertProduct());
            s.execute(product.insertProduct());

            if (product instanceof Laptop) {
                Statement s2 = connection.prepareStatement(((Laptop) product).insertLaptop());
                s2.execute(((Laptop) product).insertLaptop());

            } else if (product instanceof Mobile) {
                Statement s2 = connection.prepareStatement(((Mobile) product).insertMobile());
                s2.execute(((Mobile) product).insertMobile());

            } else if (product instanceof Food) {
                Statement s2 = connection.prepareStatement(((Food) product).insertFood());
                s2.execute(((Food) product).insertFood());

            } else if (product instanceof Clothe) {
                Statement s2 = connection.prepareStatement(((Clothe) product).insertClothe());
                s2.execute(((Clothe) product).insertClothe());

            } else if (product instanceof Shoes) {
                Statement s2 = connection.prepareStatement(((Shoes) product).insertShoes());
                s2.execute(((Shoes) product).insertShoes());

            } else if (product instanceof Refrigerator) {
                Statement s2 = connection.prepareStatement(((Refrigerator) product).insertRefrigerator());
                s2.execute(((Refrigerator) product).insertRefrigerator());

            } else if (product instanceof Stove) {
                Statement s2 = connection.prepareStatement(((Stove) product).insertStove());
                s2.execute(((Stove) product).insertStove());

            } else if (product instanceof TV) {
                Statement s2 = connection.prepareStatement(((TV) product).insertTV());
                s2.execute(((TV) product).insertTV());
            }


        } catch (Exception e) {
            System.out.println(e.getMessage());//todo
        }
    }

    // -----------------------------------------------------------------------------
    public static void readProducts() {
        Laptop.readFromDB(url, user, pass);
        Mobile.readFromDB(url, user, pass);
        Food.readFromDB(url, user, pass);
        Clothe.readFromDB(url, user, pass);
        Shoes.readFromDB(url, user, pass);
        Refrigerator.readFromDB(url, user, pass);
        Stove.readFromDB(url, user, pass);
        TV.readFromDB(url, user, pass);
    }

    // -----------------------------------------------------------------------------
    public static void update(Product product) {

        try (Connection connection = DriverManager.getConnection(url, user, pass)) {

            Class.forName("com.mysql.cj.jdbc.Driver");

            String sql = product.updateProduct();
            Statement s = connection.prepareStatement(sql);
            s.execute(sql);

        } catch (Exception e) {
            System.out.println("products table: "+e.getMessage());//todo
        }
    }

    // -----------------------------------------------------------------------------
    public static void delete(Product product) {

        try (Connection connection = DriverManager.getConnection(url, user, pass)) {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Statement s = connection.prepareStatement(product.deleteProduct());
            s.execute(product.deleteProduct());

        } catch (Exception e) {
            System.out.println(e.getMessage());//todo
        }
    }
}
