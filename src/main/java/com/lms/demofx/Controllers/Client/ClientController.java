package com.lms.demofx.Controllers.Client;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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

public class ClientController implements Initializable {

    private HashMap<String, Integer> subjectMarks;

    @FXML
    private Label clUserLabel;

    @FXML
    private Circle clAvatar;

    @FXML
    private PieChart clPieChart;


    public void initialize(URL url, ResourceBundle resourceBundle) {
        setcladAvatar("/Images/bird_2.jpg");
        setclUserLabel("John");
        setPieChartData();
        setclPieChart();
    }

    @FXML
    private void setclUserLabel(String uname) {
        clUserLabel.setText("Welcome " + uname);
    }

    @FXML
    private void setcladAvatar(String path) {
        Image img = new Image(getClass().getResourceAsStream(path));
        clAvatar.setFill(new ImagePattern(img));
    }

    @FXML
    private void setclPieChart() {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        for (String subject : subjectMarks.keySet()) {
            pieChartData.add(new PieChart.Data(subject, subjectMarks.get(subject)));
        }

        clPieChart.setTitle("Subject List");
        clPieChart.setData(pieChartData);
    } 

    @FXML
    private void setPieChartData() {
        subjectMarks = new HashMap<String, Integer>();

        subjectMarks.put("English", 33);
        subjectMarks.put("Maths", 22);
        subjectMarks.put("Bio", 13);
        subjectMarks.put("Sinhala", 32);
    }

}
