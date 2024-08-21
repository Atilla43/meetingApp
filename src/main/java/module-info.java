module com.example.meetingapp {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.meetingapp to javafx.fxml;
    exports com.example.meetingapp;
}