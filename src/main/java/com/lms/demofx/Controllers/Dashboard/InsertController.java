package com.lms.demofx.Controllers.Dashboard;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class InsertController extends DashboardController {

    @FXML
    private Button addProduct;

    @FXML
    private Label insertHeading;

    @FXML
    private TextField productNameInput;

    @FXML
    private Label productNameInputLabel;

    @FXML
    private TextField productPriceInput;

    @FXML
    private Label productPriceInputLabel;

    @FXML
    private TextField productQuantityInput;

    @FXML
    private Label productQuantityInputLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


}
