package com.example.appipo;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

import java.util.ResourceBundle;

public class IdiomaController {

    @FXML
    private ChoiceBox<String> choice_box_idioma;
    @FXML
    private Label texto_idioma;

    private MainWindowController mainWindowController;
    private ResourceBundle resourceBundle;

    public void setMainWindowController(MainWindowController controller) {
        this.mainWindowController = controller;
        resourceBundle = mainWindowController.getResourceBundle();
        choice_box_idioma.getItems().addAll("Español", "English", "Français");
        choice_box_idioma.setValue("Español");

        actualizarTextos();

        choice_box_idioma.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                cambiarIdioma(newValue);
            }
        });
    }

    @FXML
    public void initialize() {

    }

    private void cambiarIdioma(String idiomaSeleccionado) {
        String languageCode = "es";

        switch (idiomaSeleccionado) {
            case "English" -> languageCode = "en";
            case "Español" -> languageCode = "es";
            case "Français" -> languageCode = "fr";
        }

        mainWindowController.establecer_idioma(languageCode);
    }


    public void actualizarTextos() {
        resourceBundle = mainWindowController.getResourceBundle();
        texto_idioma.setText(resourceBundle.getString("label.selectLanguage"));
    }

    public void setTextoIdiomaLabel(String text) {
        texto_idioma.setText(text);
    }

}


