package ipo.appipo;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.util.ResourceBundle;

public class ErrorController {

    @FXML
    private Label mensajeErrorLabel;

    @FXML
    private Button botonAceptar;

    private MainWindowController mainWindowController;

    private LanguageManager languageManager;

    public void setMainWindowController(MainWindowController controller) {
        this.mainWindowController = controller;
        /*resourceBundle = mainWindowController.getResourceBundle();*/
    }

    public void initialize() {

    }

    public void actualizarTextos() {
        languageManager = mainWindowController.getLanguageManager();
        mensajeErrorLabel.setText(languageManager.get_property("label.errorMessage"));
        botonAceptar.setText(languageManager.get_property("label.buttonTag"));
    }

    // Método para configurar el mensaje de error
    public void setMensajeError(String mensaje) {
        mensajeErrorLabel.setText(mensaje);
    }

    // Evento del botón Aceptar
    @FXML
    private void onAceptarClicked() {
        // Cierra la ventana
        Stage stage = (Stage) botonAceptar.getScene().getWindow();
        stage.close();
    }
}
