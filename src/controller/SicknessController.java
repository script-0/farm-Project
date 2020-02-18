/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Isaac
 */
public class SicknessController implements Initializable {

    @FXML
    private TableView<?> tableView;

    @FXML
    private TableColumn<?, ?> idColumn;

    @FXML
    private TableColumn<?, ?> durationColumn;

    @FXML
    private TableColumn<?, ?> traitementColumn;

    @FXML
    private TableColumn<?, ?> sicknessColumn;

    @FXML
    private Pane paneDetails;

    @FXML
    private Pane confirmPane;

    @FXML
    private JFXButton confirm;

    @FXML
    private JFXButton cancel;

    @FXML
    private Pane modifyPane;

    @FXML
    private JFXButton modify;

    @FXML
    private JFXButton delete;

    @FXML
    private Label label;

    @FXML
    private JFXTextField id;

    @FXML
    private JFXTextField duree;

    @FXML
    private JFXComboBox<String> dureeComboBox;

    @FXML
    private JFXTextArea traitment;

    @FXML
    private JFXButton add;

    @FXML
    private JFXTextArea sickness;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initializeComboBox();
    }    
    
    public void initializeComboBox()
    {
        dureeComboBox = new JFXComboBox<>();
        dureeComboBox.getItems().addAll("Day","Week","Month","Year");
        dureeComboBox.setPromptText("Period");
    }
}
