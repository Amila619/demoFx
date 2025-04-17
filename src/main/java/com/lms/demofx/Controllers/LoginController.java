package com.lms.demofx.Controllers;

import com.lms.demofx.Utils.CustomUi;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import com.lms.demofx.Services.Database;
import com.lms.demofx.Utils.PasswordUtils;

public class LoginController extends BaseController {
    private String username, password, db_hash;
    private int db_uid;

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
    private PasswordField pswdTextField;

    @FXML
    private Button loginBtn;

    @FXML
    private Label navLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @FXML
    private void handleLogin(ActionEvent event) {
        username = unTextField.getText().trim();
        password = pswdTextField.getText().trim();

        try {
            conn = Database.Conn();
            sql = "SELECT u_id, user_password FROM users WHERE user_email=?";

            if (username.equals("") || password.equals("")) {
                CustomUi.popUpErrorMessage("Username and Password are Required", Alert.AlertType.WARNING);
            } else {
                ps = conn.prepareStatement(sql);
                ps.setString(1, username);
                rs = ps.executeQuery();

                if (rs.next()) {
                    db_uid = rs.getInt("u_id");
                    db_hash = rs.getString("user_password");

                    if (PasswordUtils.verifyPassword(password, db_hash)) {
                        userId = db_uid;
                        loadDashboard(loginBtn);
                    } else {
                        CustomUi.popUpErrorMessage("Invalid Password or Username", Alert.AlertType.ERROR);
                    }
                } else {
                    CustomUi.popUpErrorMessage("User Not Found", Alert.AlertType.ERROR);
                }
            }

        } catch (Exception  e) {
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
    private void handleLoadSignUp(MouseEvent event) throws IOException {
        loadSignUp(loginBtn);
    }
}
