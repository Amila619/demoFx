package com.lms.demofx.Controllers;

import com.lms.demofx.Services.Database;
import com.lms.demofx.Utils.CustomUi;
import com.lms.demofx.Utils.PasswordUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SignupController extends BaseController {

    private String username, password, cpassword;

    @FXML
    private ImageView BrandingImageView;

    @FXML
    private Label cpswdLabel;

    @FXML
    private PasswordField cpswdTextField;

    @FXML
    private Label emailLabel;

    @FXML
    private Label headLogin;

    @FXML
    private Label pswdLabel;

    @FXML
    private PasswordField pswdTextField;

    @FXML
    private Button signupBtn;

    @FXML
    private TextField unTextField;

    @FXML
    private Button fileUploadButton;

    @FXML
    private Circle userUploadProfilePic;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void handleSignup(ActionEvent event) {
        username = unTextField.getText().trim();
        password = pswdTextField.getText().trim();
        cpassword = cpswdTextField.getText().trim();

        try {
            conn = Database.Conn();
            sql = "INSERT INTO users (user_password, user_email, user_dp) values(?,?,?)";

            if (username.equals("") || password.equals("") || cpassword.equals("")) {
                CustomUi.popUpErrorMessage("All Fields are Required", Alert.AlertType.WARNING);
            } else if (!password.equals(cpassword)) {
                CustomUi.popUpErrorMessage("Passwords do not match", Alert.AlertType.WARNING);
            } else {
                password = PasswordUtils.hashPassword(password);

                ps = conn.prepareStatement(sql);
                ps.setString(1, password);
                ps.setString(2, username);
                ps.setBlob(3, is);

                int rowCount = ps.executeUpdate();
                if (rowCount > 0){
                    clearInputs();
                    CustomUi.popUpErrorMessage("User Added Successfully", Alert.AlertType.INFORMATION);
                }else{
                    CustomUi.popUpErrorMessage("User Added Failed", Alert.AlertType.ERROR);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println("Error in closing the Connection..." + e.getMessage());
            }
        }
    }

    @FXML
    private void handleProfilePicUpload(ActionEvent event) {
        setProfilePicUpload(userUploadProfilePic);
    }

    private void clearInputs(){
        unTextField.clear();
        pswdTextField.clear();
        cpswdTextField.clear();
        userUploadProfilePic.setFill(null);
    }

    @FXML
    private void handleLoadLogin(MouseEvent event) throws IOException {
        loadSignUp(signupBtn);
    }

}
