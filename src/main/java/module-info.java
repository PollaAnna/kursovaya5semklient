module com.example.kursovayaclient1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires javafx.graphics;
    requires okhttp;

    opens com.example.kursovayaclient1 to javafx.fxml;
    exports com.example.kursovayaclient1;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    requires com.almasb.fxgl.all;

    opens com.example.kursovayaclient1.login to javafx.fxml;

    exports com.example.kursovayaclient1.login to javafx.fxml;
}