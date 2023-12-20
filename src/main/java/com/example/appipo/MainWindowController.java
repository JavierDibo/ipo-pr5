package com.example.appipo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;

public class MainWindowController implements EtiquetaListener {

    private IdiomaController idiomaController;
    private Stage idiomaWindow;


    private AnnadirEtiquetaController annadirEtiquetaController;
    private Stage annadirEtiquetaWindow;


    private ErrorController errorController;
    private Stage errorWindow;

    protected String language;

    @FXML
    private Button boton_aceptar;
    @FXML
    private Button boton_rechazar;
    @FXML
    private Button boton_posponer;

    @FXML
    private TextArea text_area_etiquetas;

    /// Menu
    @FXML
    private MenuBar menu_bar;
    @FXML
    private MenuItem menu_menu;
    @FXML
    private MenuItem menu_curriculum;
    @FXML
    private MenuItem menu_foto;
    @FXML
    private MenuItem menu_candidato;
    @FXML
    private MenuItem menu_etiqueta;

    /// Menú
    @FXML
    private MenuItem menu_menu_config;
    @FXML
    private MenuItem menu_menu_idioma;
    @FXML
    private MenuItem menu_menu_ayuda;
    @FXML
    private MenuItem menu_menu_acercade;

    /// Curriculum
    @FXML
    private MenuItem menu_curriculum_modificar;
    @FXML
    private MenuItem menu_curriculum_annadir;
    @FXML
    private MenuItem menu_curriculum_eliminar;
    @FXML
    private MenuItem menu_curriculum_visualizar;

    /// Candidato
    @FXML
    private MenuItem menu_candidato_modificar;
    @FXML
    private MenuItem menu_candidato_annadir;
    @FXML
    private MenuItem menu_candidato_eliminar;
    @FXML
    private MenuItem menu_candidato_visualizar;

    /// Etiqueta
    @FXML
    private MenuItem menu_etiqueta_annadir;
    @FXML
    private MenuItem menu_etiqueta_eliminar;

    /// Foto
    @FXML
    private MenuItem menu_foto_modificar;
    @FXML
    private MenuItem menu_foto_annadir;
    @FXML
    private MenuItem menu_foto_eliminar;
    @FXML
    private MenuItem menu_foto_visualizar;

    /// Textos bottom page
    @FXML
    private Text textoPrivacidad;
    @FXML
    private Text textoSeguridad;
    @FXML
    private Text textoTerminos;
    @FXML
    private Text textoContacto;
    @FXML
    private Text textoCopyRight;

    /// Resto de objetos
    @FXML
    private TitledPane titledPaneEtiquetas;
    @FXML
    private TitledPane titledPaneComentarios;
    @FXML
    private TextField campoBusqueda;
    @FXML
    private TextArea comentariosTextArea;

    /// Resoucebundle
    private ResourceBundle resourceBundle;

    @FXML
    public void initialize() {
        boton_aceptar.getStyleClass().addAll("button-hover", "boton_aceptar");
        boton_rechazar.getStyleClass().addAll("button-hover", "boton_rechazar");
        boton_posponer.getStyleClass().addAll("button-hover", "boton_posponer");

        String languageCode = "es";
        Locale locale = new Locale(languageCode);
        language = languageCode;
        resourceBundle = ResourceBundle.getBundle("com.example.appipo.resources.mensajes", locale);

        cargarVentanaIdioma();
        cargarVentanaAnnadirEtiqueta();
        cargarVentanaError();

        cargar_textos();
    }

    private void cargarVentanaIdioma() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/appipo/idioma_window.fxml"));
            loader.setResources(resourceBundle);
            Parent root = loader.load();

            idiomaController = loader.getController();
            idiomaController.setMainWindowController(this);

            idiomaWindow = new Stage();
            idiomaWindow.setTitle(resourceBundle.getString("menu.language"));
            idiomaWindow.setResizable(false);
            idiomaWindow.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void abrir_ventana_idioma() {
        if (idiomaWindow != null) {
            idiomaWindow.showAndWait();
        }
    }

    private void cargarVentanaAnnadirEtiqueta() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/appipo/annadir_etiqueta_window.fxml"));
            Parent root = loader.load();

            annadirEtiquetaController = loader.getController();
            annadirEtiquetaController.setEtiquetaListener(this);
            annadirEtiquetaController.setMainWindowController(this);

            annadirEtiquetaWindow = new Stage();
            annadirEtiquetaWindow.setTitle("Añadir Etiqueta");
            annadirEtiquetaWindow.setScene(new Scene(root));
            annadirEtiquetaWindow.setResizable(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void pulsar_menu_etiquetas_annadir() {
        if (annadirEtiquetaWindow != null) {
            annadirEtiquetaWindow.showAndWait();
        }
    }

    private void cargarVentanaError() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/appipo/error_window.fxml"));
            Parent root = loader.load();

            errorController = loader.getController();
            errorController.setMainWindowController(this);

            errorWindow = new Stage();
            errorWindow.setResizable(false);
            errorWindow.initModality(Modality.NONE);
            errorWindow.setTitle("Mensaje de Error");
            errorWindow.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void abrir_ventana_error() {
        if (errorWindow != null) {
            errorWindow.showAndWait();
        }
    }

    @FXML
    void pulsar_boton_aceptar(ActionEvent event) {
        System.out.println("Boton aceptar click");
    }

    @FXML
    void pulsar_boton_rechazar(ActionEvent event) {
        System.out.println("Boton rechazar click");
    }

    @FXML
    void pulsar_boton_posponer(ActionEvent event) {
        System.out.println("Boton posponer click");
        abrir_ventana_error();
    }

    /*private void abrir_ventana_error() {
        try {
            // Cargar la vista FXML para la ventana de error
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/appipo/error_window.fxml"));
            Parent root = loader.load();

            errorController = loader.getController();

            // Crear y configurar el Stage para la ventana de error
            Stage ventanaError = new Stage();
            ventanaError.initModality(Modality.APPLICATION_MODAL); // Hace que la ventana sea modal
            ventanaError.setTitle("Mensaje de Error"); // Puedes usar un recurso localizado aquí también
            ventanaError.setScene(new Scene(root));
            ventanaError.showAndWait(); // Muestra la ventana y espera a que se cierre antes de volver a la ventana principal
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/


    @FXML
    void pulsar_menu_etiquetas_eliminar() {
        // Obtener el texto actual de la TextArea
        String texto = text_area_etiquetas.getText();

        // Dividir el texto en líneas
        String[] lineas = texto.split("\n");

        // Verificar si hay líneas para eliminar
        if (lineas.length > 1 || (lineas.length == 1 && !lineas[0].isEmpty())) {
            // Unir todas las líneas excepto la última
            String textoModificado = String.join("\n", Arrays.copyOf(lineas, lineas.length - 1));

            // Establecer el nuevo texto en la TextArea
            text_area_etiquetas.setText(textoModificado);
        } else {
            // Mostrar mensaje de error si no hay líneas para eliminar
            abrir_ventana_error();
            System.out.println("No hay más etiquetas para eliminar");
        }
    }

    @FXML
    /*private void pulsar_menu_etiquetas_annadir() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/appipo/annadir_etiqueta_window.fxml"));
            Parent root = loader.load();

            AnnadirEtiquetaController controller = loader.getController();
            annadirEtiquetaController = controller;
            controller.setEtiquetaListener(this);

            Stage stage = new Stage();
            stage.setTitle("Añadir Etiqueta");
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    @Override
    public void onNuevaEtiqueta(String etiqueta) {
        text_area_etiquetas.appendText(etiqueta + "\n");
    }

    @FXML
    /*private void abrir_ventana_idioma() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/appipo/idioma_window.fxml"));
            loader.setResources(resourceBundle); // Establecer el ResourceBundle
            Parent root = loader.load();

            IdiomaController controller = loader.getController();
            idiomaController = controller;
            controller.setResourceBundle(resourceBundle); // Pasa el ResourceBundle a IdiomaController
            controller.setMainWindowController(this); // Configuraciones adicionales

            Stage stage = new Stage();
            stage.setTitle(resourceBundle.getString("menu.language"));
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    void establecer_idioma(String languageCode) {
        Locale locale = new Locale(languageCode);
        language = languageCode;
        resourceBundle = ResourceBundle.getBundle("com.example.appipo.resources.mensajes", locale);
        cargar_textos();
    }

    public String getLanguage() {
        return language;
    }

    private void cargar_textos() {

        // Titulos
        idiomaWindow.setTitle(resourceBundle.getString("text.languageTitle"));
        errorWindow.setTitle(resourceBundle.getString("text.errorTitle"));
        annadirEtiquetaWindow.setTitle(resourceBundle.getString("text.tagsTitle"));

        // Menus Principales y Submenus
        menu_menu_config.setText(resourceBundle.getString("menu.configuration"));
        menu_menu_idioma.setText(resourceBundle.getString("menu.language"));
        menu_menu_ayuda.setText(resourceBundle.getString("menu.help"));
        menu_menu_acercade.setText(resourceBundle.getString("menu.about"));
        menu_curriculum_modificar.setText(resourceBundle.getString("menu.modify"));
        menu_curriculum_annadir.setText(resourceBundle.getString("menu.add"));
        menu_curriculum_eliminar.setText(resourceBundle.getString("menu.delete"));
        menu_curriculum_visualizar.setText(resourceBundle.getString("menu.view"));
        menu_candidato_modificar.setText(resourceBundle.getString("menu.modify"));
        menu_candidato_annadir.setText(resourceBundle.getString("menu.add"));
        menu_candidato_eliminar.setText(resourceBundle.getString("menu.delete"));
        menu_candidato_visualizar.setText(resourceBundle.getString("menu.view"));
        menu_etiqueta_annadir.setText(resourceBundle.getString("menu.add"));
        menu_etiqueta_eliminar.setText(resourceBundle.getString("menu.delete"));
        menu_foto_modificar.setText(resourceBundle.getString("menu.modify"));
        menu_foto_annadir.setText(resourceBundle.getString("menu.add"));
        menu_foto_eliminar.setText(resourceBundle.getString("menu.delete"));
        menu_foto_visualizar.setText(resourceBundle.getString("menu.view"));
        menu_menu.setText(resourceBundle.getString("menu.main"));
        menu_curriculum.setText(resourceBundle.getString("menu.curriculum"));
        menu_foto.setText(resourceBundle.getString("menu.photo"));
        menu_candidato.setText(resourceBundle.getString("menu.candidate"));
        menu_etiqueta.setText(resourceBundle.getString("menu.tag"));

        // Botones
        boton_aceptar.setText(resourceBundle.getString("button.accept"));
        boton_rechazar.setText(resourceBundle.getString("button.reject"));
        boton_posponer.setText(resourceBundle.getString("button.postpone"));

        // TitledPane
        titledPaneEtiquetas.setText(resourceBundle.getString("titledPane.labels"));
        titledPaneComentarios.setText(resourceBundle.getString("titledPane.comments"));

        // Otros Textos
        // Asegúrate de reemplazar estos IDs de campo con los correctos de tu aplicación
        campoBusqueda.setPromptText(resourceBundle.getString("text.search"));
        textoPrivacidad.setText(resourceBundle.getString("text.privacy"));
        textoSeguridad.setText(resourceBundle.getString("text.security"));
        textoTerminos.setText(resourceBundle.getString("text.terms"));
        textoContacto.setText(resourceBundle.getString("text.contact"));
        textoCopyRight.setText(resourceBundle.getString("text.copyRight"));
        comentariosTextArea.setText(resourceBundle.getString("textArea.comments"));

        idiomaController.actualizarTextos();
        annadirEtiquetaController.actualizarTextos();
        errorController.actualizarTextos();
    }

    public TextArea get_text_area_etiquetas() {
        return text_area_etiquetas;
    }

    public void set_text_area_etiquetas(TextArea text_area_etiquetas) {
        this.text_area_etiquetas = text_area_etiquetas;
    }

    public ResourceBundle getResourceBundle() {
        return resourceBundle;
    }
}
