package com.example.appipo;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AnnadirEtiquetaController {

    @FXML
    private TextField textFieldNuevaEtiqueta;

    private EtiquetaListener listener;

    public void setEtiquetaListener(EtiquetaListener listener) {
        this.listener = listener;
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
