package com.lms.demofx;

import com.lms.demofx.Controllers.Base.BaseController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage stage) throws Exception {
     try{
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Fxml/Login.fxml"));
         Scene scene = new Scene(fxmlLoader.load());
         stage.setScene(scene);
         stage.setTitle("Login");
         stage.getIcons().add(new Image(getClass().getResource("/Images/logo.png").toExternalForm()));
         stage.show();
     }catch (Exception e){
         System.out.println("error : " + e + "\nerror-message: " + e.getMessage());
         e.printStackTrace();
     }
    }

    @Override
    public void stop() throws Exception {
        Thread.sleep(500);
        BaseController.emptyDirectoryFiles("src/main/resources/Images/Uploads/");
        super.stop();
    }
}