package com.lms.demofx.Controllers.Dashboard;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    private Parent root;

    @FXML
    private BorderPane dashBoardPane;

    @FXML
    private Button displayButton;

    @FXML
    private Button homeButton;

    @FXML
    private Button insertButton;

    @FXML
    private Circle profilePic;

    @FXML
    private Button updateButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        navigate("/Fxml/Dashboard/Home.fxml");
        setProfilePic();
    }

    @FXML
    private void navigateToHome(ActionEvent event){
        navigate("/Fxml/Dashboard/Home.fxml");
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
    private void navigate(String path) {
        try {
            root = FXMLLoader.load(getClass().getResource(path));
            dashBoardPane.setRight(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setProfilePic() {
        String path = getClass().getResource("/Images/User/dp.jpg").toExternalForm();
        Image image = new Image(path);
        profilePic.setFill(new ImagePattern(image));
    }
}