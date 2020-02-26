/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import java.sql.Connection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Isaac
 * @param <T>
 */
public abstract class defaultController<T>{
    
    //===Utilise pour connaitre si on fait une modification d'in fournisseur ou si on en ajoute un ==
    String operation = "";
    
    //// =================================== Fonctions effectuant des traitements specifiques ===============================
    public void setConnection(Connection con){
        
    }
    
    /**Initialisation de la tableview**/
    public void initializeTableView(){
        
    }
    
    /** Desactive tous les champs du formulaire si value == true et les active sinon
     * @param value
     */
    public void disableFields(Boolean value){
        
    }
    
    /** Initialise tous les champs de notre formulaire en effacant ce qu'ils contiennent*/
    public void initFields(){
        
    }
    
    /** Charge les donnees d'un fournisseur( represente par l'argument data) dans les differents champs
     * @param data 
     **/
    public void loadData(T data){
        
    }
    
    public void initializeInterface(Boolean initialize){
        
    }
    
    public T getData(){
        return null;
    }
     
    public Boolean modifyData(T data){
        return null;
    }
    
     public void initializeId(){};
    
    /**Raccourci de System.out.println*/
    void print(Object value)
    {
        System.out.println(value);
    }
    
    
    //====================== Les listeners des differents buttons et Elements de notre vue =========================
    
    /** Fonction appelee lorsque le bouton "Add" est clicke */
    @FXML
    void addClicked(ActionEvent event) {
        initializeInterface(false);
        initFields();
        initializeId();
        operation = "ADD";
    }
    
    /** Fonction appelee lorsque le bouton "Modify" est clicke */
    @FXML
    void modifyClicked(ActionEvent event){
        initializeInterface(false);
        verifyAll();
        operation = "MODIFICATION";
    }
    
    /** Fonction appelee lorsque le bouton "Delete" est clicke */
    @FXML
    void deleteClicked(ActionEvent event) {}
    
    /** Fonction appelee lorsque le bouton "Confirm" est clicke */
    @FXML
    void confirmClicked(ActionEvent event) {}
    
    /** Elle est appelee lorsqu'on click sur le tableview. Charge les informations de la ligne selectionnee et la charge dans le formulaire**/
    @FXML
    void tableViewClicked(MouseEvent event) {}
    
     /** Fonction appelee lorsque le bouton "Cancel" est clicke */
    @FXML
    void cancelClicked(ActionEvent event) {
        initializeInterface(true);
        tableViewClicked(null);
    }
    
    //================= Fonctions qui verifient les informations entrees par l'utilisateur dans notre formulaire ============
    public Boolean verifyValue(String value, String regex, ImageView report,JFXButton confirm){
       if(value.equals("") || !value.matches(regex))
       {
            report.setImage(new Image("/images/errorNotification.png"));       
            confirm.setDisable(true);
            return false;
       }
       else
       {
           report.setImage(new Image("/images/confirmNotification.png")); 
           confirm.setDisable(false);
       }
       return true;
    }
    public void verifyAll(){}
}
