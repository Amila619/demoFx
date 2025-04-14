package com.lms.demofx.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import com.lms.demofx.Services.Database;
import com.lms.demofx.Utils.PasswordUtils;

public class LoginController implements Initializable {

    private String username, password, db_uid, db_hash, sql;
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @FXML
    private void handleLogin(ActionEvent event) {
        username = unTextField.getText().trim();
        password = pswdTextField.getText().trim();
        conn = Database.Conn();
        sql = "SELECT User_Id, Password FROM user WHERE Email=?";

        try {
            if (username.equals("") || password.equals("")) {
                popUpErrorMessage("Username or Password are Required");
            } else {
                ps = conn.prepareStatement(sql);
                ps.setString(1, username);
                rs = ps.executeQuery();

                if (rs.next()) {
                    db_uid = rs.getString("User_Id");
                    db_hash = rs.getString("Password");

                    if (PasswordUtils.verifyPassword(password, db_hash)) {
                        System.out.println("Successfully logged in");
                        onLogin(); // Optional: close login screen
                    } else {
                        popUpErrorMessage("Invalid Password or Username");
                    }
                } else {
                    popUpErrorMessage("User Not Found");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println("Error in closing the Connection..." + e.getMessage());
            }
        }
    }

    private void onLogin() {
        Stage loginStage = (Stage) loginBtn.getScene().getWindow();
        loginStage.close();
    }

    private void popUpErrorMessage(String error) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Login Error");
        alert.setHeaderText(error);
        alert.setContentText("Please try again.");

        // Load custom CSS
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(getClass().getResource("/Styles/styles.css").toExternalForm());
        alert.showAndWait();
    }
}
