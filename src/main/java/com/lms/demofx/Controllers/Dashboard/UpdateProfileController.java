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

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UpdateProfileController extends DashboardController {

    private String username, npassword, cpassword, upassword;
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
        updateInputFields();
    }


    @FXML
    private void handleUpdate(ActionEvent event) throws IOException {
        username = unTextField.getText().trim();
        npassword = npswdTextField.getText().trim();
        cpassword = cpswdTextField.getText().trim();
        try{
            if (username.isEmpty()) {
                CustomUi.popUpErrorMessage("Fields cannot be empty!", "Update Error", Alert.AlertType.ERROR);
            }else{
                if(!npassword.equals(cpassword)) {
                    CustomUi.popUpErrorMessage("Passwords do not match", "Update Error", Alert.AlertType.WARNING);
                }else {
                    conn = Database.Conn();
                    sql = "UPDATE users SET user_email = ?, user_password = ?, user_dp = ? WHERE u_id = ?";
                    npassword = npassword.isEmpty() ? upassword : PasswordUtils.hashPassword(npassword);

                    ps = conn.prepareStatement(sql);
                    ps.setString(1, username);
                    ps.setString(2, npassword);
                    ps.setBlob(3, is);
                    ps.setInt(4, BaseController.getUserId());

                    int rowCount = ps.executeUpdate();
                    if (rowCount > 0) {
                        CustomUi.popUpErrorMessage("User Updated Successfully", "Update Success", Alert.AlertType.INFORMATION);
                        loadDashboard(signupBtn);
                    } else {
                        CustomUi.popUpErrorMessage("User Updated Failed", "Update Failed", Alert.AlertType.ERROR);
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println("Error in closing the Connection..."+ e.getMessage());
            }
        }
    }

    private void updateInputFields() {
        try{
                conn = Database.Conn();
                sql = "SELECT user_email, user_password, user_dp FROM users WHERE u_id = ?";

                ps = conn.prepareStatement(sql);
                ps.setInt(1, BaseController.getUserId());
                rs = ps.executeQuery();
                if (rs.next()) {
                    unTextField.setText(rs.getString("user_email"));
                    upassword = rs.getString("user_password");
                    is = rs.getBinaryStream("user_dp");
                    byte[] imgData = rs.getBytes("user_dp");
                    Image image = new Image(new ByteArrayInputStream(imgData));
                    userUploadProfilePic.setFill(new ImagePattern(image));
                }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println("Error in closing the Connection..."+ e.getMessage());
            }
        }
    }

    @FXML
    private void handleProfilePicUpload(ActionEvent event) {
        setProfilePicUpload(userUploadProfilePic);
    }


}
