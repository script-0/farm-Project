/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import model.Profils;

/**
 * FXML Controller class
 *
 * @author Isaac
 */
public class AdminDashBoardController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private BorderPane borderPane;

    @FXML
    private ImageView logoImage;

    @FXML
    private Label logoName;

    @FXML
    private Pane profilePane;

    @FXML
    private ImageView imgProfile;

    @FXML
    private Label nameProfile;

    @FXML
    private Label typeEmp;

    @FXML
    private JFXButton dashboard;

    @FXML
    private JFXButton building;

    @FXML
    private JFXButton breed;

    @FXML
    private JFXButton food;

    @FXML
    private JFXButton eggTypes;

    @FXML
    private JFXButton sick;

    @FXML
    private JFXButton vaccine;

    @FXML
    private JFXButton customers;

    @FXML
    private JFXButton suppliers;

    @FXML
    private JFXButton batchFlock;

    @FXML
    private JFXButton eggProd;

    @FXML
    private JFXButton vaccination;

    @FXML
    private JFXButton EggPrediction;
    
    private Profils userProfile;

    @FXML
    void loadBatchFlock(ActionEvent event) {

    }

    @FXML
    void loadBreed(ActionEvent event) {

    }

    @FXML
    void loadBuilding(ActionEvent event) {

    }

    @FXML
    void loadCustomers(ActionEvent event) {

    }

    @FXML
    void loadDashboard(ActionEvent event) {

    }

    @FXML
    void loadEggPrediction(ActionEvent event) {

    }

    @FXML
    void loadEggProd(ActionEvent event) {

    }

    @FXML
    void loadEggTypes(ActionEvent event) {

    }

    @FXML
    void loadFood(ActionEvent event) {

    }

    @FXML
    void loadSick(ActionEvent event) {

    }

    @FXML
    void loadSuppliers(ActionEvent event) {

    }

    @FXML
    void loadVaccination(ActionEvent event) {

    }

    @FXML
    void loadVaccine(ActionEvent event) {

    }

    public void loadContent()
    {
        Node content = null;
        FXMLLoader loader;
        try {
            loader = new FXMLLoader(getClass().getResource("/view/adminDashBoardContent.fxml"));
            content = (Node) loader.load();
            AdminDashBoardContentController con  = loader.getController();
            con.initUserName(userProfile.getUsername());
        } catch (IOException ex) {
            Logger.getLogger(AdminDashBoardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        borderPane.setCenter(content);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
       
    public void initProfils(Profils profil)
    {
        userProfile = profil;
        
        nameProfile.setText(userProfile.getUsername());
        //imgProfile.setImage(new Image(userProfile.getImage()));
        typeEmp.setText(userProfile.getType());
    }
    
}
