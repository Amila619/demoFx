module com.lms.demofx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires de.jensd.fx.glyphs.fontawesome;
    requires java.sql;
    requires jbcrypt;
    requires io.github.cdimascio.dotenv.java;
    requires java.desktop;


    opens com.lms.demofx to javafx.fxml;
    opens com.lms.demofx.Controllers to javafx.fxml;
    opens com.lms.demofx.Controllers.Dashboard to javafx.fxml;
    opens com.lms.demofx.Models to javafx.fxml;
    opens com.lms.demofx.Utils to javafx.fxml;
    opens com.lms.demofx.Services to javafx.fxml;
    opens com.lms.demofx.Controllers.Base to javafx.fxml;

    exports com.lms.demofx;
    exports com.lms.demofx.Controllers;
    exports com.lms.demofx.Controllers.Dashboard;
    exports com.lms.demofx.Models;
    exports com.lms.demofx.Utils;
    exports com.lms.demofx.Services;
    exports com.lms.demofx.Controllers.Base;

}