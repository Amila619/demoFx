package com.lms.demofx.Controllers.Dashboard;

import com.lms.demofx.Controllers.Base.BaseController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Circle;
import javafx.scene.input.MouseEvent;

import java.io.*;
import java.net.URL;
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
    private Button updateButton;

    @FXML
    protected Circle profilePic;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getImageFromDatabase(profilePic);
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
        loadLogin(logoutButton);
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