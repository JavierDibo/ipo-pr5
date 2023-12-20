package com.example.appipo;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        // Carga dinámicamente los idiomas disponibles
        List<String> idiomasDisponibles = obtenerIdiomasDisponibles();
        choice_box_idioma.getItems().addAll(idiomasDisponibles);
        choice_box_idioma.setValue("es");

        actualizarTextos();

        choice_box_idioma.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                cambiarIdioma(newValue);
            }
        });
    }

    private List<String> obtenerIdiomasDisponibles() {
        File carpetaIdiomas = new File("C:\\Users\\Administrator\\IdeaProjects\\app-ipo\\src\\main\\resources\\com\\example\\appipo\\resources");
        File[] archivos = carpetaIdiomas.listFiles();
        List<String> idiomas = new ArrayList<>();
        Pattern patron = Pattern.compile("mensajes_(\\w+)\\.properties");

        assert archivos != null;
        for (File archivo : archivos) {
            Matcher coincidencia = patron.matcher(archivo.getName());
            if (coincidencia.find()) {
                idiomas.add(coincidencia.group(1));
            }
        }

        return idiomas;
    }

    @FXML
    public void initialize() {

    }

    private void cambiarIdioma(String idiomaSeleccionado) {
        /*String languageCode = "es";

        switch (idiomaSeleccionado) {
            case "English" -> languageCode = "en";
            case "Español" -> languageCode = "es";
            case "Français" -> languageCode = "fr";
        }

        mainWindowController.establecer_idioma(languageCode);*/

        mainWindowController.establecer_idioma(idiomaSeleccionado);
    }


    public void actualizarTextos() {
        resourceBundle = mainWindowController.getResourceBundle();
        texto_idioma.setText(resourceBundle.getString("label.selectLanguage"));
    }

    public void setTextoIdiomaLabel(String text) {
        texto_idioma.setText(text);
    }

}


