package com.example.appipo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

public class IPOapp extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        Locale locale = new Locale("es"); // Para español
        ResourceBundle bundle = ResourceBundle.getBundle("com/example/appipo/resources/mensajes", locale);

        FXMLLoader fxmlLoader = new FXMLLoader(IPOapp.class.getResource("main_window.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 960, 640);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("resources/style.css")).toExternalForm());
        stage.setTitle("CurricuLUM v1.0");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}