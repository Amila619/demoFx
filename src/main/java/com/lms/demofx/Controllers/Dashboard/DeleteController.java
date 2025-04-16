package com.lms.demofx.Controllers.Dashboard;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class DeleteController extends DashboardController {

    @FXML
    private Label deleteHeading;

    @FXML
    private Button deleteProduct;

    @FXML
    private ComboBox<?> productIdInput;

    @FXML
    private Label productIdInputLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


}
