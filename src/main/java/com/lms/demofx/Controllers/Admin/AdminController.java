package com.lms.demofx.Controllers.Admin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class AdminController implements Initializable {

    private HashMap<String, Integer> subjectMarks;
    private ObservableList<PieChart.Data> pieChartData;

    @FXML
    private Label adUserLabel;

    @FXML
    private Circle adAvatar;

    @FXML
    private PieChart adPieChart;


    public void initialize(URL url, ResourceBundle resourceBundle) {
        setAdAvatar("/Images/bird_2.jpg");
        setAdUserLabel("John");
        initHashMap();
        setAdPieChart();
    }

    @FXML
    public void setAdUserLabel(String uname) {
        adUserLabel.setText("Welcome " + uname);
    }

    @FXML
    public void setAdAvatar(String path) {
        Image img = new Image(getClass().getResourceAsStream(path));
        adAvatar.setFill(new ImagePattern(img));
    }

    @FXML
    public void setAdPieChart() {
        pieChartData = FXCollections.observableArrayList();
        for (String subject : subjectMarks.keySet()) {
            pieChartData.add(new PieChart.Data(subject, subjectMarks.get(subject)));
        }

        adPieChart.setTitle("Subject List");
        adPieChart.setData(pieChartData);
    }

    @FXML
    public void initHashMap() {
        subjectMarks = new HashMap<String, Integer>();

        subjectMarks.put("English", 33);
        subjectMarks.put("Maths", 22);
        subjectMarks.put("Bio", 13);
        subjectMarks.put("Sinhala", 32);
    }

}
