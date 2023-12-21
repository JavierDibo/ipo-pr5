package ipo.appipo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainWindowController implements EtiquetaListener {

    private IdiomaController idiomaController;
    private Stage idiomaWindow;
    private AnnadirEtiquetaController annadirEtiquetaController;
    private Stage annadirEtiquetaWindow;


    private ErrorController errorController;
    private Stage errorWindow;

    LanguageManager languageManager = new LanguageManager();

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
    private Menu menu_menu_idioma;
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

    @FXML
    public void initialize() {
        boton_aceptar.getStyleClass().addAll("button-hover", "boton_aceptar");
        boton_rechazar.getStyleClass().addAll("button-hover", "boton_rechazar");
        boton_posponer.getStyleClass().addAll("button-hover", "boton_posponer");

        String languageCode = "es";
        languageManager.cargarIdioma(languageCode);
        cargarIdiomasEnMenu();

        cargarVentanaAnnadirEtiqueta();
        cargarVentanaError();

        cargar_textos();
    }

    private void cargarIdiomasEnMenu() {
        List<String> idiomasDisponibles = obtenerIdiomasDisponibles();
        for (String idioma : idiomasDisponibles) {
            MenuItem menuItem = new MenuItem(idioma);
            menuItem.setOnAction(event -> establecer_idioma(idioma));
            menu_menu_idioma.getItems().add(menuItem);
        }
    }

    private List<String> obtenerIdiomasDisponibles() {
        String rutaRelativa = "idiomas";
        File carpetaIdiomas = new File(rutaRelativa);
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

    void establecer_idioma(String languageCode) {

        languageManager.cargarIdioma(languageCode);
        cargar_textos();
    }

    @FXML
    private void abrir_ventana_idioma() {
        if (idiomaWindow != null) {
            idiomaWindow.showAndWait();
        }
    }

    private void cargarVentanaAnnadirEtiqueta() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ipo/appipo/annadir_etiqueta_window.fxml"));
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ipo/appipo/error_window.fxml"));
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

    @Override
    public void onNuevaEtiqueta(String etiqueta) {
        text_area_etiquetas.appendText(etiqueta + "\n");
    }

    private void cargar_textos() {

        // Titulos
        errorWindow.setTitle(languageManager.get_property("text.errorTitle"));
        annadirEtiquetaWindow.setTitle(languageManager.get_property("text.tagsTitle"));

        // Menus Principales y Submenus
        menu_menu_config.setText(languageManager.get_property("menu.configuration"));
        menu_menu_idioma.setText(languageManager.get_property("menu.language"));
        menu_menu_ayuda.setText(languageManager.get_property("menu.help"));
        menu_menu_acercade.setText(languageManager.get_property("menu.about"));
        menu_curriculum_modificar.setText(languageManager.get_property("menu.modify"));
        menu_curriculum_annadir.setText(languageManager.get_property("menu.add"));
        menu_curriculum_eliminar.setText(languageManager.get_property("menu.delete"));
        menu_curriculum_visualizar.setText(languageManager.get_property("menu.view"));
        menu_candidato_modificar.setText(languageManager.get_property("menu.modify"));
        menu_candidato_annadir.setText(languageManager.get_property("menu.add"));
        menu_candidato_eliminar.setText(languageManager.get_property("menu.delete"));
        menu_candidato_visualizar.setText(languageManager.get_property("menu.view"));
        menu_etiqueta_annadir.setText(languageManager.get_property("menu.add"));
        menu_etiqueta_eliminar.setText(languageManager.get_property("menu.delete"));
        menu_foto_modificar.setText(languageManager.get_property("menu.modify"));
        menu_foto_annadir.setText(languageManager.get_property("menu.add"));
        menu_foto_eliminar.setText(languageManager.get_property("menu.delete"));
        menu_foto_visualizar.setText(languageManager.get_property("menu.view"));
        menu_menu.setText(languageManager.get_property("menu.main"));
        menu_curriculum.setText(languageManager.get_property("menu.curriculum"));
        menu_foto.setText(languageManager.get_property("menu.photo"));
        menu_candidato.setText(languageManager.get_property("menu.candidate"));
        menu_etiqueta.setText(languageManager.get_property("menu.tag"));

        // Botones
        boton_aceptar.setText(languageManager.get_property("button.accept"));
        boton_rechazar.setText(languageManager.get_property("button.reject"));
        boton_posponer.setText(languageManager.get_property("button.postpone"));

        // TitledPane
        titledPaneEtiquetas.setText(languageManager.get_property("titledPane.labels"));
        titledPaneComentarios.setText(languageManager.get_property("titledPane.comments"));

        // Otros Textos
        campoBusqueda.setPromptText(languageManager.get_property("text.search"));
        textoPrivacidad.setText(languageManager.get_property("text.privacy"));
        textoSeguridad.setText(languageManager.get_property("text.security"));
        textoTerminos.setText(languageManager.get_property("text.terms"));
        textoContacto.setText(languageManager.get_property("text.contact"));
        textoCopyRight.setText(languageManager.get_property("text.copyRight"));
        comentariosTextArea.setText(languageManager.get_property("textArea.comments"));

        annadirEtiquetaController.actualizarTextos();
        errorController.actualizarTextos();
    }


    public LanguageManager getLanguageManager() {
        return languageManager;
    }

    public TextArea get_text_area_etiquetas() {
        return text_area_etiquetas;
    }

    public void set_text_area_etiquetas(TextArea text_area_etiquetas) {
        this.text_area_etiquetas = text_area_etiquetas;
    }
}
