package com.lms.demofx.Controllers.Dashboard;

import com.lms.demofx.Services.Database;
import com.lms.demofx.Utils.SceneHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import javafx.scene.input.MouseEvent;
import java.io.*;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    private Parent root;
    private String sql;
    private int userId;

    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;
    private InputStream is;
    private FileOutputStream fos;

    @FXML
    private BorderPane dashBoardPane;

    @FXML
    private Button displayButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button logoutButton;

    @FXML
    private Button insertButton;

    @FXML
    private Circle profilePic;

    @FXML
    private Button updateButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        navigate("/Fxml/Dashboard/Display.fxml");
    }

    @FXML
    private void navigateToDelete(ActionEvent event){
        navigate("/Fxml/Dashboard/Delete.fxml");
    }

    @FXML
    private void navigateToInsert(ActionEvent event) {
        navigate("/Fxml/Dashboard/Insert.fxml");
    }

    @FXML
    private void navigateToDisplay(ActionEvent event) {
        navigate("/Fxml/Dashboard/Display.fxml");
    }

    @FXML
    private void navigateToUpdate(ActionEvent event){
        navigate("/Fxml/Dashboard/Update.fxml");
    }

    @FXML
    private void navigateToUpdateProfile(MouseEvent event){
        navigate("/Fxml/Dashboard/UpdateProfile.fxml");
    }

    @FXML
    void navigateToLogin(ActionEvent event) throws IOException {
        FXMLLoader loader = SceneHandler.createLoader("/Fxml/Login.fxml");
        root = loader.load();

        SceneHandler.switchScene(logoutButton, root, "Login");
        // delete all images inside of user directory
    }

    @FXML
    private void navigate(String path) {
        try {
            root = FXMLLoader.load(getClass().getResource(path));
            dashBoardPane.setRight(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setProfilePic() {
        try {
            URL imageUrl = getClass().getResource("/Images/User/dp.jpg");

            if (imageUrl == null) {
                imageUrl = getClass().getResource("/Images/User/temp.jpg");
            }

            Image image = new Image(imageUrl.toExternalForm());
            profilePic.setFill(new ImagePattern(image));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setUserId(int id) {
        userId = id;
        getImageFromDatabase(userId, "src/main/resources/Images/User/dp.jpg");
    }

    private void getImageFromDatabase(int userId, String outputPath) {
        try {
             conn = Database.Conn();
             sql = "SELECT user_dp FROM users WHERE u_id=?";
             ps = conn.prepareStatement(sql);

            ps.setInt(1, userId);
             rs = ps.executeQuery();

            if (rs.next()) {
                 is = rs.getBinaryStream("user_dp");

                try {
                    fos = new FileOutputStream(outputPath);
                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = is.read(buffer)) != -1) {
                        fos.write(buffer, 0, bytesRead);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                is.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        setProfilePic();
    }
}