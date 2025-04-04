package com.lms.demofx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage stage) throws Exception {
     try{
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Fxml/Admin/Admin.fxml"));
//         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Fxml/Client/Client.fxml"));
         Scene scene = new Scene(fxmlLoader.load());
         stage.setScene(scene);
         stage.setTitle("Admin");
         stage.show();
     }catch (Exception e){
         System.out.println("error : " + e + "\nerror-message: " + e.getMessage());
     }
    }
}