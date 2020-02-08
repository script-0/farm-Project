/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Isaac
 */
public class CustomersViewController implements Initializable {

    
     @FXML
    private TableView<?> tableView;

    @FXML
    private TableColumn<?, ?> nameColumn;

    @FXML
    private TableColumn<?, ?> telColumn;

    @FXML
    private TableColumn<?, ?> addressColumn;

    @FXML
    private Pane paneDetails;

    @FXML
    private JFXTextField name;

    @FXML
    private JFXTextField tel;

    @FXML
    private JFXTextField address;

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

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
