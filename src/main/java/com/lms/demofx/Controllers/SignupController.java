package com.lms.demofx.Controllers;

import com.lms.demofx.Services.Database;
import com.lms.demofx.Utils.CustomUi;
import com.lms.demofx.Utils.PasswordUtils;
import com.lms.demofx.Utils.SceneHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SignupController implements Initializable {

    private Parent root;

    private String username, password, cpassword, sql;
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;
    private InputStream upImage;
    BufferedImage bi;

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

    @FXML
    private Label navLabel;

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
            sql = sql = "INSERT INTO users (user_password, user_email, user_dp) values(?,?,?)";

            if (username.equals("") || password.equals("") || cpassword.equals("")) {
                CustomUi.popUpErrorMessage("All Fields are Required", Alert.AlertType.WARNING);
            } else if (!password.equals(cpassword)) {
                CustomUi.popUpErrorMessage("Passwords do not match", Alert.AlertType.WARNING);
            } else {
                password = PasswordUtils.hashPassword(password);

                ps = conn.prepareStatement(sql);
                ps.setString(1, password);
                ps.setString(2, username);
                ps.setBlob(3, upImage);

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
    private void setProfilePicUpload(ActionEvent event){
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

    private void clearInputs(){
        unTextField.clear();
        pswdTextField.clear();
        cpswdTextField.clear();
        userUploadProfilePic.setFill(null);
    }

    @FXML
    private void loadLogin(MouseEvent event) throws IOException {
        FXMLLoader loader = SceneHandler.createLoader("/Fxml/Login.fxml");
        root = loader.load();

        SceneHandler.switchScene(signupBtn, root, "Login");

    }
}
