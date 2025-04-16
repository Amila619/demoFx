package com.lms.demofx.Controllers.Dashboard;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class UpdateController extends DashboardController {

    @FXML
    private Label updateHeading;

    @FXML
    private ComboBox<?> productIdInput;

    @FXML
    private Label productIdInputLabel1;

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

    @FXML
    private Button updateProduct;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
