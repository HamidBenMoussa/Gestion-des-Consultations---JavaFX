module ma.enset.gestionconsultationbdcc {
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires jdk.compiler;
    requires org.kordamp.bootstrapfx.core;
    requires javafx.controls;


    opens ma.enset.gestionconsultationbdcc.controllers to javafx.fxml;
    opens ma.enset.gestionconsultationbdcc.entities to javafx.base;
    exports ma.enset.gestionconsultationbdcc;
}