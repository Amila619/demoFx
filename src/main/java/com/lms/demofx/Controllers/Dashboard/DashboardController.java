package com.lms.demofx.Controllers.Dashboard;

import com.lms.demofx.Controllers.BaseController;
import com.lms.demofx.Controllers.SignupController;
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

import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class DashboardController extends BaseController {
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
        getImageFromDatabase();
        setProfilePic(profilePic);
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
}