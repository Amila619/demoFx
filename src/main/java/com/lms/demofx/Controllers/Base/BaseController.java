package com.lms.demofx.Controllers.Base;

import com.lms.demofx.Services.Database;
import com.lms.demofx.Utils.SceneHandler;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class BaseController implements Initializable {
    protected Parent root;
    private static int userId;
    protected int id;
    protected String sql;
    protected Connection conn;
    protected Statement st;
    protected PreparedStatement ps;
    protected ResultSet rs;
    protected InputStream is;
    protected FileOutputStream fos;
    protected BufferedImage bi;


    @FXML
    private Circle profilePic;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public static void setUserId(int id) {
        userId = id;
    }

    public static int getUserId() {
        return userId;
    }

    public void setProfilePic(Circle proPic) {
        try {
            URL imageUrl = getClass().getResource("/Images/User/dp.jpg");

            if (imageUrl == null) {
                imageUrl = getClass().getResource("/Images/User/temp.jpg");
            }

            Image image = new Image(imageUrl.toExternalForm());
            proPic.setFill(new ImagePattern(image));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setProfilePic(Circle proPic, byte[] imgData) {
        Image image;
        try {
            image = new Image(new ByteArrayInputStream(imgData));
        }catch (Exception e) {
            e.printStackTrace();
            image = new Image(getClass().getResource("/Images/User/temp.jpg").toExternalForm());
        }
        proPic.setFill(new ImagePattern(image));
    }

    public void getImageFromDatabase() {
        try {
            conn = Database.Conn();
            sql = "SELECT user_dp FROM users WHERE u_id=?";
            ps = conn.prepareStatement(sql);

            id = BaseController.getUserId();

            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                try {
                    byte[] imageData = rs.getBytes("user_dp");
                    setProfilePic(profilePic, imageData);
                    fos = new FileOutputStream("src/main/resources/Images/User/dp.jpg");
                    fos.write(imageData);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void saveImage(File selectedFile) {
        try {
            bi = ImageIO.read(selectedFile);
            String fileExtension = selectedFile.toString().split("\\.")[1];
            File outputfile = new File("src/main/resources/Images/Uploads/up." + fileExtension);
            ImageIO.write(bi, fileExtension, outputfile);
            is = new FileInputStream(new File("src/main/resources/Images/Uploads/up." + fileExtension));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    public void setProfilePicUpload(Circle profilePic) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
        );

        File selectedFile = fileChooser.showOpenDialog(profilePic.getScene().getWindow());
        if (selectedFile != null) {
            Image image = new Image(selectedFile.toURI().toString());
            profilePic.setFill(new ImagePattern(image));
            saveImage(selectedFile);
        }
    }


    public void loadSignUp(Node ob) throws IOException {
        FXMLLoader loader = SceneHandler.createLoader("/Fxml/Signup.fxml");
        root = loader.load();
        SceneHandler.switchScene(ob, root, "Signup");
    }

    public void loadLogin(Node ob) throws IOException {
        FXMLLoader loader = SceneHandler.createLoader("/Fxml/Login.fxml");
        root = loader.load();
        SceneHandler.switchScene(ob, root, "Login");
    }

    public void loadDashboard(Node ob) throws IOException {
        FXMLLoader loader = SceneHandler.createLoader("/Fxml/Dashboard/Dashboard.fxml");
        root = loader.load();
        SceneHandler.switchScene(ob, root, "Dashboard");
    }

}
