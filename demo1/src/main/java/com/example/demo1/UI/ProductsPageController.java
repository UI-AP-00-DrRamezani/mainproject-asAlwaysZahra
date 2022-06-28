package com.example.demo1.UI;

import com.example.demo1.Products.Product.Product;
import com.example.demo1.Products.Product.ProductManager;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class ProductsPageController {

    int page = 1;
    ArrayList<Product> products;

    @FXML
    GridPane grid;
    @FXML
    Label lbl_pgNumber;
    @FXML

    public void addProduct() {
        ObservableList<Node> innerGrids = grid.getChildren();
        products = new ArrayList<>(ProductManager.allProducts);
        int i = 0;
        int pCount = page * 9 - 9;
        for (Node node : innerGrids) {
            if (i == 9)
                break;

            Label l1 = new Label(products.get(pCount).getName());
            l1.setFont(new Font("Lato Black", 16));
            ((GridPane) node).add(l1, 1, 0);

            Label l2 = new Label(products.get(pCount).getBrand());
            l2.setFont(new Font("Lato Black", 16));
            ((GridPane) node).add(l2, 1, 1);

            Label l3 = new Label(String.valueOf(products.get(pCount).getPrice()));
            l3.setFont(new Font("Lato Black", 16));
            ((GridPane) node).add(l3, 1, 2);

            i++;
            pCount++;
        }
    }

    public void nextPage() {
//        page = Integer.parseInt(lbl_pgNumber.getText()) + 1;
        page++;
        lbl_pgNumber.setText(String.valueOf(page));
//        addProduct();
        clearPage();
        addProduct();
    }

    public void previousPage() {
//        page = Integer.parseInt(lbl_pgNumber.getText()) - 1;
        page--;
        if (page > 0) {
            lbl_pgNumber.setText(String.valueOf(page));
//            addProduct();
            clearPage();
            addProduct();
        }
    }

    public void clearPage() {
        GridPane inner;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                inner = (GridPane) getNode(grid, i, j);

                (inner).getChildren().remove(getNode(inner, 0, 1));
                (inner).getChildren().remove(getNode(inner, 1, 1));
                (inner).getChildren().remove(getNode(inner, 2, 1));
            }
        }
    }

    public void back() throws IOException {
        new Home().start((Stage) lbl_pgNumber.getScene().getWindow());
    }

    public Node getNode(GridPane grid, int row, int col) {
        System.out.println(grid.getChildren());
        for (Node node : grid.getChildren())
            if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row)
                return node;

        return null;
    }
}
