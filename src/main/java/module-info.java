module com.lms.demofx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires de.jensd.fx.glyphs.fontawesome;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;

    opens com.lms.demofx to javafx.fxml;
    exports com.lms.demofx;
    exports com.lms.demofx.Controllers;
    exports com.lms.demofx.Controllers.Admin;
    exports com.lms.demofx.Controllers.Client;
    exports com.lms.demofx.Models;
    exports com.lms.demofx.Views;

}