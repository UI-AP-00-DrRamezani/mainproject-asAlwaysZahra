package com.example.demo1.UI;

import com.example.demo1.Accounts.Admin.AdminManager;
import com.example.demo1.Accounts.Customer.CustomerManager;
import com.example.demo1.Accounts.Seller.SellerManager;
import com.example.demo1.Products.Digital.Digital;
import com.example.demo1.Products.Food.Food;
import com.example.demo1.Products.Garment.Garment;
import com.example.demo1.Products.Home.HomeThings;
import com.example.demo1.Products.Product.Product;
import com.example.demo1.Products.Product.ProductManager;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class HomeController {

    @FXML
    private TextField searchField;
    @FXML
    private ImageView digital;

    public void switchProductsPage(MouseEvent event) throws IOException {

        if (((ImageView) event.getSource()).getId().equalsIgnoreCase("digital"))
            new ProductsPage2(Digital.digitalCategory.getProducts()).start((Stage) searchField.getScene().getWindow());

        if (((ImageView) event.getSource()).getId().equalsIgnoreCase("food"))
            new ProductsPage2(Food.foodCategory.getProducts()).start((Stage) searchField.getScene().getWindow());

        if (((ImageView) event.getSource()).getId().equalsIgnoreCase("garment"))
            new ProductsPage2(Garment.garmentCategory.getProducts()).start((Stage) searchField.getScene().getWindow());

        if (((ImageView) event.getSource()).getId().equalsIgnoreCase("home"))
            new ProductsPage2(HomeThings.homeCategory.getProducts()).start((Stage) searchField.getScene().getWindow());
    }

    public void searchClick() throws IOException {
        String search = searchField.getText();
        System.out.println(search);
        ArrayList<Product> products = ProductManager.search(search, ProductManager.allCategory);
        new ProductsPage2(products).start((Stage) searchField.getScene().getWindow());
    }

    public void switchPanel() throws IOException {

        if (AdminManager.adminModel == null || CustomerManager.customerModel == null || SellerManager.sellerModel == null)
            new Login().start((Stage) digital.getScene().getWindow());

        else if (AdminManager.adminModel != null)
            new AdminPanel().start((Stage) digital.getScene().getWindow());

        else if (CustomerManager.customerModel != null)
            new CustomerPanel().start((Stage) digital.getScene().getWindow());

        else if (SellerManager.sellerModel != null)
            new SellerPanel().start((Stage) digital.getScene().getWindow());

    }

    public void switchCart() throws IOException {

        if (CustomerManager.customerModel == null)
            new Alert(Alert.AlertType.WARNING, "Please first login with a customer account.").show();

        else
            new Cart(CustomerManager.customerModel.getCart()).start(new Stage());
//        new Cart(ProductManager.allProducts).start((Stage) digital.getScene().getWindow());
    }

    public void switchFilterByPrice() throws Exception {
        new GetPriceLimits().start(new Stage());
    }

    public void switchFilterByBrand() throws Exception {
        new GetBrand().start(new Stage());
    }

    public void switchFilterByAvailability() throws Exception {
        new ProductsPage2(ProductManager.filterByAvailability(ProductManager.allCategory)).start((Stage) digital.getScene().getWindow());
    }

    public void switchFilterByPopularity() throws Exception {
        // sort according to avg rate
        ProductManager.allProducts.sort((o1, o2) -> Float.compare(o1.getAvgRate(), o2.getAvgRate()));
        new ProductsPage2(ProductManager.allProducts).start((Stage) digital.getScene().getWindow());
    }
}