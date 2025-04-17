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
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class UpdateProfileController extends DashboardController {

    private String username, password, npassword, cpassword, sql;
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;
    private InputStream upImage;
    BufferedImage bi;


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
    private void setProfilePicUpload(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
        );

        File selectedFile = fileChooser.showOpenDialog(fileUploadButton.getScene().getWindow());
        if (selectedFile != null) {
            Image image = new Image(selectedFile.toURI().toString());
            userUploadProfilePic.setFill(new ImagePattern(image));
            saveImage(selectedFile);
        }
    }


    private void saveImage(File selectedFile){
        try {
            bi = ImageIO.read(selectedFile);
            String fileExtension = selectedFile.toString().split("\\.")[1];
            File outputfile = new File("src/main/resources/Images/Uploads/up." + fileExtension);
            ImageIO.write(bi, fileExtension, outputfile);
            upImage = new FileInputStream(new File("src/main/resources/Images/Uploads/up." + fileExtension));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
