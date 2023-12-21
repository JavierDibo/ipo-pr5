module com.example.appipo {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens ipo.appipo to javafx.fxml;
    exports ipo.appipo;
}