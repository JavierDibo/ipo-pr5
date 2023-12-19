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
    }

    public void setResourceBundle(ResourceBundle resourceBundle) {
        this.resourceBundle = resourceBundle;
    }

    @FXML
    public void initialize() {
        choice_box_idioma.getItems().addAll("Español", "Inglés");
        choice_box_idioma.setValue("Español");

        actualizarTextos();

        choice_box_idioma.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                cambiarIdioma(newValue);
            }
        });
    }

    private void cambiarIdioma(String idiomaSeleccionado) {
        String languageCode = "es";
        if (idiomaSeleccionado.equals("Inglés")) {
            languageCode = "en";
        }
        mainWindowController.establecer_idioma(languageCode);
    }

    private void actualizarTextos() {
        if (resourceBundle != null) {
            texto_idioma.setText(resourceBundle.getString("label.selectLanguage"));
        }
    }
}


