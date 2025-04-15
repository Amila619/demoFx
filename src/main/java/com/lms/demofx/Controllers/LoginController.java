package com.lms.demofx.Controllers;

import com.lms.demofx.Controllers.Dashboard.DashboardController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import com.lms.demofx.Services.Database;
import com.lms.demofx.Utils.PasswordUtils;

public class LoginController implements Initializable {

    private Stage stage;
    private Scene scene;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @FXML
    private void handleLogin(ActionEvent event) {
        username = unTextField.getText().trim();
        password = pswdTextField.getText().trim();
        conn = Database.Conn();
        sql = "SELECT u_id, user_password FROM users WHERE email=?";

        try {
            if (username.equals("") || password.equals("")) {
                popUpErrorMessage("Username and Password are Required");
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
                        popUpErrorMessage("Invalid Password or Username");
                    }
                } else {
                    popUpErrorMessage("User Not Found");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println("Error in closing the Connection..." + e.getMessage());
            }
        }
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

    private void loadDashboard(String id) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Dashboard/Dashboard.fxml"));
        root = loader.load();

        DashboardController dbcontroller = loader.getController();
        dbcontroller.setUserId(id);

        stage = (Stage) loginBtn.getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Dashboard");
        stage.setScene(scene);
    }
}
