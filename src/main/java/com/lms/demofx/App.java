package com.lms.demofx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage stage) throws Exception {
     try{
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Fxml/Login.fxml"));
         Scene scene = new Scene(fxmlLoader.load());
         stage.setScene(scene);
         stage.setTitle("Login");
         stage.show();
     }catch (Exception e){
         System.out.println("error : " + e + "\nerror-message: " + e.getMessage());
         e.printStackTrace();
     }
    }
}