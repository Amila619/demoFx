package com.lms.demofx.Utils;

import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;

public class CustomUi {
    public static void popUpErrorMessage(String error, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle("Login Error");
        alert.setHeaderText(error);

        // Load custom CSS
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(CustomUi.class.getResource("/Styles/styles.css").toExternalForm());
        alert.showAndWait();
    }

}
