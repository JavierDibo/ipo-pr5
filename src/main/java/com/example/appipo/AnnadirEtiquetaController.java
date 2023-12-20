package com.example.appipo;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.ResourceBundle;

public class AnnadirEtiquetaController {

    @FXML
    private TextField textFieldNuevaEtiqueta;
    private EtiquetaListener listener;

    private MainWindowController mainWindowController;

    @FXML
    private Label texto_label;
    @FXML
    private Button boton_annadir;

    private ResourceBundle resourceBundle;

    public void setMainWindowController(MainWindowController controller) {
        this.mainWindowController = controller;
        resourceBundle = mainWindowController.getResourceBundle();
    }

    public void setEtiquetaListener(EtiquetaListener listener) {
        this.listener = listener;
    }

    public void actualizarTextos() {
        resourceBundle = mainWindowController.getResourceBundle();
        texto_label.setText(resourceBundle.getString("label.addTag"));
        boton_annadir.setText(resourceBundle.getString("label.buttonTag"));
    }

    @FXML
    private void handleAddLabel() {
        String nuevaEtiqueta = textFieldNuevaEtiqueta.getText().trim();
        if (!nuevaEtiqueta.isEmpty()) {
            if (listener != null) {
                listener.onNuevaEtiqueta(nuevaEtiqueta);
            }
            textFieldNuevaEtiqueta.setText("");
        }
    }
}
