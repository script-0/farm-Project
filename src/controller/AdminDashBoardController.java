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
    
    private Node hasFocus;

    @FXML
    void loadBatchFlock(ActionEvent event) { //load batchFlockView.fxml
        loadSimpleContent("batchFlock");
        unsetFocus(hasFocus);
        hasFocus = batchFlock;
        setFocus(hasFocus);
    }

    @FXML
    void loadBreed(ActionEvent event) { //load breedView.fxml
        loadSimpleContent("breed");
        unsetFocus(hasFocus);
        hasFocus = breed;
        setFocus(hasFocus);
    }

    @FXML
    void loadBuilding(ActionEvent event) { //load buildingView.fxml
        loadSimpleContent("building");
        unsetFocus(hasFocus);
        hasFocus = building;
        setFocus(hasFocus);
    }

    @FXML
    void loadCustomers(ActionEvent event) {  //load customersView.fxml
        loadSimpleContent("customers");
        unsetFocus(hasFocus);
        hasFocus = customers;
        setFocus(hasFocus);
    }

    @FXML
    void loadDashboard(ActionEvent event) { //load adminDashBoardContent.fxml
        loadContent();
    }

    @FXML
    void loadEggPrediction(ActionEvent event) { //coming soon

    }

    @FXML
    void loadEggProd(ActionEvent event) { //load eggProdView.fxml
        loadSimpleContent("eggProd");
        unsetFocus(hasFocus);
        hasFocus = eggProd;
        setFocus(hasFocus);
    }

    @FXML
    void loadEggTypes(ActionEvent event) { //load eggTypeView.fxml
        loadSimpleContent("eggType");
        unsetFocus(hasFocus);
        hasFocus = eggTypes;
        setFocus(hasFocus);
    }

    @FXML
    void loadFood(ActionEvent event) { //load foodView.fxml
        loadSimpleContent("food");
        unsetFocus(hasFocus);
        hasFocus = food;
        setFocus(hasFocus);
    }

    @FXML
    void loadSick(ActionEvent event) { //load sicknessView.fxml
        loadSimpleContent("sickness");
        unsetFocus(hasFocus);
        hasFocus = sick;
        setFocus(hasFocus);
    }

    @FXML
    void loadSuppliers(ActionEvent event) { // load suppliersView.fxml
        loadSimpleContent("suppliers");
        unsetFocus(hasFocus);
        hasFocus = suppliers;
        setFocus(hasFocus);
    }

    @FXML
    void loadVaccination(ActionEvent event) { //laod vaccinView.fxml
        loadSimpleContent("vaccin");
        unsetFocus(hasFocus);
        hasFocus = vaccination;
        setFocus(hasFocus);
    }

    @FXML
    void loadVaccine(ActionEvent event) { //load VaccineView.fxml
        loadSimpleContent("vaccine");
        unsetFocus(hasFocus);
        hasFocus = vaccine;
        setFocus(hasFocus);
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
        if(!(hasFocus == null))
        {
            unsetFocus(hasFocus);
        }
        hasFocus = dashboard;
        setFocus(hasFocus);
    }
    
    public void unsetFocus(Node node)
    {
        node.getStyleClass().remove("optionSelected");
        node.getStyleClass().add("option");
    }
    
    public void setFocus(Node node)
    {
        node.getStyleClass().remove("option");
        node.getStyleClass().add("optionSelected");
    }
    
    public Boolean loadSimpleContent(String name)
    {
        Node content = null;
        FXMLLoader loader;
        try {
            loader = new FXMLLoader(getClass().getResource("/view/"+name+"View.fxml"));
            content = (Node) loader.load();
        } catch (IOException ex) {
            Logger.getLogger(AdminDashBoardController.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        borderPane.setCenter(content);
        return true;
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
