package com.lms.demofx.Controllers;

import com.lms.demofx.Controllers.Dashboard.DashboardController;
import com.lms.demofx.Utils.CustomUi;
import com.lms.demofx.Utils.SceneHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import com.lms.demofx.Services.Database;
import com.lms.demofx.Utils.PasswordUtils;

public class LoginController implements Initializable {
    private Parent root;

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
                    db_uid = rs.getString("u_id");
                    db_hash = rs.getString("user_password");

                    if (PasswordUtils.verifyPassword(password, db_hash)) {
                        loadDashboard(db_uid);
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


    private void loadDashboard(String id) throws IOException {
        FXMLLoader loader = SceneHandler.createLoader("Dashboard.fxml");
        root = loader.load();

        DashboardController dbcontroller = loader.getController();
        dbcontroller.setUserId(id);

        SceneHandler.switchScene(loginBtn, root, "/Fxml/Dashboard/Dashboard.fxml");
    }

    @FXML
    private void loadSignUp(MouseEvent event) throws IOException {
        FXMLLoader loader = SceneHandler.createLoader("/Fxml/Signup.fxml");
        root = loader.load();

        SceneHandler.switchScene(loginBtn, root, "Signup");

    }
}
