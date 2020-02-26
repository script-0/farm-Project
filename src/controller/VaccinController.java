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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import model.classes.Vaccin;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.Pane;
import model.classes.Fournisseurs;

/**
 * FXML Controller class
 *
 * @author Isaac
 */
public class VaccinController implements Initializable {
    
    //===================== La tableView et ses Colonnes ====================
    @FXML
    private TableView<Vaccin> tableView;

    @FXML
    private TableColumn<Vaccin, Integer> idColumn;

    @FXML
    private TableColumn<Vaccin, String> nameColumn;

    @FXML
    private TableColumn<Vaccin, String> periodColumn;

    @FXML
    private TableColumn<Vaccin, Integer> qtVaccinColumn;

    @FXML
    private TableColumn<Vaccin, Integer> qtPouleColumn;

    @FXML
    private TableColumn<Vaccin, String> descriptionColumn;

    @FXML
    private TableColumn<Vaccin, Integer> priceColumn;

    @FXML
    private Pane paneDetails;

    @FXML
    private Label label;

    //=========================Les Buttons ===========================
    @FXML
    private JFXButton add;

    @FXML
    private Pane modifyPane;

    @FXML
    private JFXButton modify;

    @FXML
    private JFXButton delete;

    @FXML
    private Pane confirmPane;

    @FXML
    private JFXButton confirm;

    @FXML
    private JFXButton cancel;

    //========================== Le formulaire =======================
    @FXML
    private JFXTextField id;

    @FXML
    private ImageView idImg;

    @FXML
    private JFXTextField name;

    @FXML
    private ImageView nameImg;

    @FXML
    private JFXTextField period;
    
    @FXML
    private JFXComboBox<String> periodFormat;

    @FXML
    private ImageView periodImg;

    @FXML
    private JFXTextField qtVaccin;

    @FXML
    private ImageView qtVaccinImg;

    @FXML
    private JFXTextField qtPoule;

    @FXML
    private ImageView qtPouleImg;

    @FXML
    private JFXTextField price;

    @FXML
    private ImageView priceImg;

    @FXML
    private JFXTextArea description;

    @FXML
    private ImageView descriptionImg;
    
     //====================== La connection a la BD utilisee =============
    Connection con;
    
    //========================== Les Tableuax utilises =====================
    ObservableList<Fournisseurs> listVaccin = FXCollections.observableArrayList();    

    //============================ Les DAO Utilises =======================
                /*****************(A remplir)****************/
    
    //=================================== Fonctions effectuant des traitements specifiques =========================
    
    public void initializePeriodFormat(){
        periodFormat.getItems().add("Year");
         periodFormat.getItems().add("Month");
          periodFormat.getItems().add("Day");
    }
    
    public void initializeTableView(){
        //id Column
        idColumn.setCellValueFactory(new PropertyValueFactory<>("idVac"));
        
        //name Column
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("nomVac"));
        
        //address Column
        periodColumn.setCellValueFactory(new PropertyValueFactory<>("periode"));
        
        //tel Column
        qtVaccinColumn.setCellValueFactory(new PropertyValueFactory<>("qteVac"));
       
        //email column
        qtPouleColumn.setCellValueFactory(new PropertyValueFactory<>("qtePoule"));

        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));    
 
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("prix"));
        
        TableViewSelectionModel model = tableView.getSelectionModel();
        model.setSelectionMode(SelectionMode.MULTIPLE);//On peut selectionner plusieurs entrees a la fois
        model.selectedItemProperty().addListener((ObservableValue obs, Object oldSelection, Object newSelection) -> {
            modify.setDisable((newSelection == null));
            delete.setDisable((newSelection == null));
        });
         
        tableView.setPlaceholder(new Label("No Vaccin in the database"));
    }
    
    //====================== Les listeners des differents buttons et Elements de notre vue =========================
     @FXML
    void verifyDescription(KeyEvent event) {

    }

    @FXML
    void verifyName(KeyEvent event) {

    }

    @FXML
    void verifyPeriod(KeyEvent event) {

    }
    
    @FXML
    void verifyPeriodFormat(ActionEvent event) {

    }

    @FXML
    void verifyPrice(KeyEvent event) {

    }

    @FXML
    void verifyQtPoule(KeyEvent event) {

    }

    @FXML
    void verifyQtVaccin(KeyEvent event) {

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
