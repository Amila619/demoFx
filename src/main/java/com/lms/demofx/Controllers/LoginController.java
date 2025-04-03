package com.lms.demofx.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;


public class LoginController {

    @FXML
    private Label errorLabel;

    @FXML
    private Label headLogin;

    @FXML
    private Label email;

    @FXML
    private Label pswd;

    @FXML
    private ImageView BrandingImageView;

    @FXML
    private TextField unTextField;

    @FXML
    private TextField pswdTextField;

    @FXML
    private Button loginBtn;

    public void init(URL url, ResourceBundle resourceBundle) {
        File brandingFile = new File("images/1250.jpg");
        Image brandingImage = new Image(brandingFile.toURI().toString());
        BrandingImageView.setImage(brandingImage);
    }

    public void setErrorLabel(ActionEvent event) {
        errorLabel.setText("Invalid username or password");
    }



}