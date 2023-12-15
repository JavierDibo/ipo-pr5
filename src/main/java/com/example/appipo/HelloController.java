package com.example.appipo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class HelloController {

    @FXML
    private Button boton_aceptar;

    @FXML
    void print_sout(ActionEvent event) {
        System.out.println("Boton aceptar");
    }

}