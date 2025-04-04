package com.lms.demofx.Controllers.Admin;

import com.lms.demofx.Models.User;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.shape.Circle;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

import java.net.URL;
import java.util.ResourceBundle;


public class AdminController implements Initializable {

    @FXML
    private Circle adAvatar;

    @FXML
    private Label adUserLabel;

    @FXML
    private TableView<User> userTable;

    @FXML
    private TableColumn<User, Integer> age;

    @FXML
    private TableColumn<User, String> name;

    @FXML
    private TableColumn<User, String> province;


    public void initialize(URL url, ResourceBundle resourceBundle) {
        setAdAvatar("/Images/1250.jpg");
        setAdUserLabel();
        setTable();
    }

    private ObservableList<User> setTableData() {
        User u1 = new User("Jagath", 22, "Sabaragamuwa");
        User u2 = new User("Perera", 32, "Southern");
        return FXCollections.<User>observableArrayList(u1, u2);
    }

    @FXML
    private void setAdUserLabel() {
        adUserLabel.setText("Admin");
    }

    @FXML
    private void setAdAvatar(String path) {
        Image img = new Image(getClass().getResourceAsStream(path));
        adAvatar.setFill(new ImagePattern(img));
    }

    @FXML
    private void setTable() {
        name.setCellValueFactory(new PropertyValueFactory<User, String>("name"));
        age.setCellValueFactory(new PropertyValueFactory<User, Integer>("age"));
        province.setCellValueFactory(new PropertyValueFactory<User, String>("province"));

        userTable.setItems(setTableData());
    }
}
