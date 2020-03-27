/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import model.Profils;
import model.Tools;
import model.databaseUtils;

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
    private JFXButton employees;

    @FXML
    private JFXButton customers;

    @FXML
    private JFXButton suppliers;

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
    private JFXButton batchFlock;

    @FXML
    private JFXButton eggProd;

    @FXML
    private JFXButton ration;

    @FXML
    private JFXButton stockAliment;

    @FXML
    private JFXButton venteOeuf;

    @FXML
    private JFXButton venteBande;

    @FXML
    private JFXButton incubation;

    @FXML
    private JFXButton poussinProduit;

    @FXML
    private JFXButton vaccination;

    @FXML
    private JFXButton bandeMalade;

    @FXML
    private JFXButton EggPrediction;
    
    
    @FXML
    private TitledPane staffTitledPane;
    
    @FXML
    private TitledPane farmTitledPane;
     
    @FXML
    private TitledPane bandeTitledPane;
    
    @FXML
    private TitledPane nutritionTitledPane;
    
    @FXML
    private TitledPane ventesTitledPane;
     
    @FXML
    private TitledPane incubationTitledPane;
    
    @FXML
    private TitledPane healthTitledPane;
    
    
    @FXML
    private JFXButton editProfileButton;
    
    @FXML
    private JFXButton logoutButton;
    
    @FXML
    private JFXButton confirmButton;
    
    @FXML
    private JFXTextField newName;
    
    @FXML
    private JFXButton moreOptionButton;
    
    private Profils userProfile;
    
    public databaseUtils utils;
    
    private Node hasFocus;
    
    private Node hasFocusTitledPane;
    
    
    
    public void setUtils(databaseUtils utils)
    {
        this.utils = utils;
    }
    
    @FXML
    void loadBatchFlock(ActionEvent event) { //load batchFlockView.fxml
        if(hasFocus!=batchFlock){
            loadSimpleContent("batchFlock");
            changeFocus(batchFlock);
        }
    }

    @FXML
    void loadBreed(ActionEvent event) { //load breedView.fxml
        if(hasFocus!=breed){
            loadSimpleContent("breed");
            changeFocus(breed);
        }
    }

    @FXML
    void loadBuilding(ActionEvent event) { //load buildingView.fxml
        if(hasFocus!=building){
            loadSimpleContent("building");
            changeFocus(building);
            changeTitledPaneFocus(farmTitledPane);
        }
    }

    @FXML
    void loadCustomers(ActionEvent event) {  //load customersView.fxml
        if(hasFocus!=customers){
            loadSimpleContent("customers");
            changeFocus(customers);
            changeTitledPaneFocus(staffTitledPane);
        }
    }

    @FXML
    void loadDashboard(ActionEvent event) { //load adminDashBoardContent.fxml
        if(hasFocus!=dashboard){
            loadContent();
        }
    }

    @FXML
    void loadEggPrediction(ActionEvent event) { //coming soon

    }

    @FXML
    void loadEggProd(ActionEvent event) { //load eggProdView.fxml
        if(hasFocus!=eggProd){
            loadSimpleContent("eggProd");
            changeFocus(eggProd);
        }
    }

    @FXML
    void loadEggTypes(ActionEvent event) { //load eggTypeView.fxml
       if(hasFocus!=eggTypes){ 
         loadSimpleContent("eggType");
        changeFocus(eggTypes);
       }
    }

    @FXML
    void loadFood(ActionEvent event) { //load foodView.fxml
        if(hasFocus!=food){
            loadSimpleContent("food");
            changeFocus(food);
        }
    }

    @FXML
    void loadSick(ActionEvent event) { //load sicknessView.fxml
        if(hasFocus!=sick){
          loadSimpleContent("sickness");
          changeFocus(sick);
        }
    }

    @FXML
    void loadSuppliers(ActionEvent event) { // load suppliersView.fxml
        
        if(hasFocus!=suppliers){
            SuppliersController con = (SuppliersController)loadSimpleContent("suppliers");
            try {
                con.setConnection(utils.getConnection());
            } catch (SQLException ex) {
               Logger.getLogger(AdminDashBoardController.class.getName()).log(Level.SEVERE, null, ex);
            }
            changeFocus(suppliers);
            changeTitledPaneFocus(staffTitledPane);
        }
    }

    @FXML
    void loadVaccination(ActionEvent event) { //laod vaccinView.fxml
        if(hasFocus!=vaccination){
            loadSimpleContent("vaccin");
            changeFocus(vaccination);
        }
    }

    @FXML
    void loadVaccine(ActionEvent event) { //load VaccineView.fxml
        if(hasFocus!=vaccine){
            loadSimpleContent("vaccine");
            changeFocus(vaccine);
        }
    }   

     @FXML
    void loadVenteBande(ActionEvent event) {
        if(hasFocus!=venteBande){
            loadSimpleContent("batchFlock");
            changeFocus(venteBande);
        }
    }

    @FXML
    void loadVenteOeuf(ActionEvent event) {
        if(hasFocus!=venteOeuf){
            //loadSimpleContent("vaccine");
            changeFocus(venteOeuf);
        }
    }

     @FXML
    void loadStockAliment(ActionEvent event) {
       if(hasFocus!=stockAliment){
        // loadSimpleContent("vaccine");
        changeFocus(stockAliment);
       }
    }
    
     @FXML
    void loadIncubation(ActionEvent event) {
        if(hasFocus!=incubation){
            //loadSimpleContent("vaccine");
            changeFocus(incubation);
        }
    }

    @FXML
    void loadPoussinProduit(ActionEvent event) {
        if(hasFocus!=poussinProduit){
            //loadSimpleContent("vaccine");
            changeFocus(poussinProduit);
        }
    }

     @FXML
    void loadEmployees(ActionEvent event) {
        if(hasFocus!=staffTitledPane){
            //loadSimpleContent("vaccine");
            changeFocus(employees);
            changeTitledPaneFocus(staffTitledPane);
        }
    }

    
    @FXML
    void loadRation(ActionEvent event) {
        if(hasFocus!=ration){
            //loadSimpleContent("vaccine");
            changeFocus(ration);
        }
    }

    @FXML
    void loadBandeMalade(ActionEvent event) {
        if(hasFocus!=bandeMalade){
            // loadSimpleContent("vaccine");
            changeFocus(bandeMalade);
        }
    }    
    
    public void loadContent()
    {
        Node content = null;
        FXMLLoader loader;
        try {
            loader = new FXMLLoader(getClass().getResource("/view/adminDashBoardContent.fxml"));
            content = (Node) loader.load();
            AdminDashBoardContentController con  = loader.getController();
            con.setConnection(utils.getConnection());
            con.initUserName(userProfile.getUsername());
        } catch (IOException ex) {
            Logger.getLogger(AdminDashBoardController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AdminDashBoardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        borderPane.setCenter(content);
        if(!(hasFocus == null))
        {
            unsetFocus(hasFocus);
        }
        hasFocus = dashboard;
        setFocus(hasFocus);
        unFocusTitledPane(hasFocusTitledPane);
    }
    
    public void changeFocus(JFXButton temp)
    {
        unsetFocus(hasFocus);
        hasFocus = temp;
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
    
    public void unFocusTitledPane(Node node)
    {
        if(node!=null)node.getStyleClass().remove("titled-Pane-Selected");
    }
    
    public void focusTitledPane(Node node)
    {
        if(node!=null) node.getStyleClass().add("titled-Pane-Selected");
    }
    
    public void changeTitledPaneFocus(TitledPane node){
        if(hasFocusTitledPane == null)
        {
            hasFocusTitledPane = node;
            focusTitledPane(hasFocusTitledPane);
           
        }else{
            if(!hasFocusTitledPane.equals(node)){
                unFocusTitledPane(hasFocusTitledPane);
                hasFocusTitledPane = node;
                focusTitledPane(hasFocusTitledPane);
            }
        }
    }
    
    public Object loadSimpleContent(String name)
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
        return loader.getController();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
       
    public void initProfils(Profils profil)
    {
        userProfile = profil;
        
        nameProfile.setText(userProfile.getUsername());
        imgProfile.setImage(new Image(userProfile.getImage()));
        typeEmp.setText(userProfile.getType());
    }
    
    @FXML
    void newImage(MouseEvent event) {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Select an Image");
        chooser.getExtensionFilters().addAll( new ExtensionFilter("Image Files","*.jpg","*.png",".gif"));
//        chooser.setInitialDirectory(new File(System.getProperty("cur.dir")));
        File file = chooser.showOpenDialog(  ((Node)(event.getSource())).getScene().getWindow() );
        if(file == null ){
            Tools.showNotification("File Chooser", "No file selected", Boolean.TRUE);
        }else{
            String relativePath = null;
            try{
                File current = new File(System.getProperty("user.dir")+"/src");
                try{
                    relativePath = current.toPath().relativize(file.toPath()).toString().replace("\\", "/");                   
                }catch(java.lang.IllegalArgumentException e){ //Cas ou les fichiers ne sont pas dans le meme disque
                    relativePath = file.toURI().toURL().toString();
                }finally{
                    Tools.print(relativePath);
                     //Save image in DataBase.
                     if(utils.saveImageProfile(userProfile.getUsername(), relativePath)){
                         //Charger l'image dans l'imageView
                         imgProfile.setImage(new Image(relativePath));
                     }                         
                }
            } catch (MalformedURLException ex) {
                Tools.showNotification("File Chooser", "Error occured. Try again later.", Boolean.TRUE);
            }
        }
    }
    
    @FXML
    void confirmButtonClicked(ActionEvent event) {
        String text = newName.getText();
        if(!text.equals("") && !text.equals(userProfile.getUsername())){
            if(utils.saveUsername(userProfile.getUsername(),text)){
                nameProfile.setText(text);
                userProfile.setUsername(text);
            }
        }
        initProfilePane(true);
    }

    @FXML
    void editProfileButtonClicked(ActionEvent event) {
        initProfilePane(false);
    }
    
    @FXML
    void moreOptionButtonClicked(ActionEvent event)
    {
         Tools.showNotification("System","Fonctionnalite pas prise en compte", Boolean.TRUE);
    }
    
    @FXML
    void logoutButtonClicked(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/login.fxml"));
            Stage s =Tools.getStage(event);
            s.setScene(new Scene(root));
            s.centerOnScreen();            
        } catch (IOException ex) {
            Tools.showNotification("Interface Loader","Loading of Logging interface failed", Boolean.TRUE);
        }        
    }
    
    void initProfilePane(boolean value){
        editProfileButton.setVisible(value);
        logoutButton.setVisible(value);
        confirmButton.setVisible(!value);
        nameProfile.setVisible(value);
        typeEmp.setVisible(value);
        imgProfile.setDisable(value);
        newName.setVisible(!value);
        newName.setText(nameProfile.getText());
        moreOptionButton.setVisible(!value);
    }

    
}
