package com.lms.demofx.Controllers.Dashboard;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ResourceBundle;

public class UpdateProfileController extends DashboardController {

    private String username, password, npassword, cpassword;

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
        Image image = new Image(getClass().getResourceAsStream("/Images/User/dp.jpg"));
        userUploadProfilePic.setFill(new ImagePattern(image));
    }


    @FXML
    private void handleUpdate(ActionEvent event) {
        super.getImageFromDatabase();
    }

    @FXML
    private void handleProfilePicUpload(ActionEvent event) {
        setProfilePicUpload(userUploadProfilePic);
    }

}
