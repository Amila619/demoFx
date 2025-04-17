package com.lms.demofx.Controllers.Dashboard;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ResourceBundle;

public class UpdateProfileController extends DashboardController {

    @FXML
    private Label cpswdLabel;

    @FXML
    private PasswordField cpswdTextField;

    @FXML
    private Label emailLabel;

    @FXML
    private Button fileUploadButton;

    @FXML
    private Label opswdLabel;

    @FXML
    private PasswordField opswdTextField;

    @FXML
    private Label pswdLabel;

    @FXML
    private PasswordField pswdTextField;

    @FXML
    private Button signupBtn;

    @FXML
    private Label uimageLabel;

    @FXML
    private TextField unTextField;

    @FXML
    private Circle userUploadProfilePic;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }


    @FXML
    private void handleUpdate(ActionEvent event) {

    }

    @FXML
    private void setProfilePicUpload(ActionEvent event) {

    }

}
