package com.lms.demofx.Controllers.Dashboard;

import com.lms.demofx.Controllers.Base.BaseController;
import com.lms.demofx.Services.Database;
import com.lms.demofx.Utils.CustomUi;
import com.lms.demofx.Utils.PasswordUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.FileInputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class UpdateProfileController extends DashboardController {

    private String username, npassword, cpassword;
    @FXML
    private Label cpswdLabel;

    @FXML
    private PasswordField cpswdTextField;

    @FXML
    private Label emailLabel;

    @FXML
    private Button fileUploadButton;

    @FXML
    private PasswordField npswdTextField;

    @FXML
    private Label pswdLabel;

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
        setProfilePic(userUploadProfilePic);
    }


    @FXML
    private void handleUpdate(ActionEvent event) {
        username = unTextField.getText().trim();
        npassword = npswdTextField.getText().trim();
        cpassword = cpswdTextField.getText().trim();
        try{
            if (username.isEmpty() || npassword.isEmpty() || cpassword.isEmpty()) {
                CustomUi.popUpErrorMessage("Fields cannot be empty!", Alert.AlertType.ERROR);
            }else if(!npassword.equals(cpassword)){
                CustomUi.popUpErrorMessage("Passwords do not match", Alert.AlertType.WARNING);
            }else {
                conn = Database.Conn();
                sql = "UPDATE users SET user_email = ?, user_password = ?, user_dp = ? WHERE u_id = ?";
                npassword = PasswordUtils.hashPassword(npassword);
                is = new FileInputStream("src/main/resources/Images/Uploads/up.jpg");

                ps = conn.prepareStatement(sql);
                ps.setString(1, username);
                ps.setString(2, npassword);
                ps.setBlob(3, is);
                ps.setInt(4, BaseController.getUserId());

                int rowCount = ps.executeUpdate();
                if (rowCount > 0) {
                    clearInputs();
                    CustomUi.popUpErrorMessage("User Updated Successfully", Alert.AlertType.INFORMATION);
                } else {
                    CustomUi.popUpErrorMessage("User Updated Failed", Alert.AlertType.ERROR);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        getImageFromDatabase();
    }

    @FXML
    private void handleProfilePicUpload(ActionEvent event) {
        setProfilePicUpload(userUploadProfilePic);
    }

    private void clearInputs(){
        unTextField.setText("");
        cpswdTextField.setText("");
        npswdTextField.setText("");
    }
}
