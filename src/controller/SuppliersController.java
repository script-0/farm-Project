/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import model.DAO.FournisseursDAO;
import model.DAO.TypeFournisseurDAO;
import model.classes.Fournisseurs;
import model.classes.TypeFournisseur;

/**
 * FXML Controller class
 *
 * @author Isaac
 */
public class SuppliersController implements Initializable {

    //===================== La tableView et ses Colonnes ====================
    @FXML
    private TableView<Fournisseurs> tableView;

    @FXML
    private TableColumn<Fournisseurs, Integer> idColumn;

    @FXML
    private TableColumn<Fournisseurs, String> nameColumn;

    @FXML
    private TableColumn<Fournisseurs, String> addressColumn;

    @FXML
    private TableColumn<Fournisseurs, String> telColumn;

    @FXML
    private TableColumn<Fournisseurs, String> emailColumn;

    @FXML
    private TableColumn<Fournisseurs, String> websiteColumn;

    @FXML
    private TableColumn<Fournisseurs, String> typeColumn;
    
    //====================Le Pane contenant le formulaire ================
    @FXML
    private Pane paneDetails;

    @FXML
    private Label labelDetails;
    
    //=========================Les Buttons ===========================
    @FXML
    private JFXButton add;

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
    
    //========================== Le formulaire =======================
    @FXML
    private JFXTextField id;

    @FXML
    private JFXTextField name;

    @FXML
    private ImageView nameImg;

    @FXML
    private JFXTextField tel;
    
    @FXML
    private ImageView telImg;
    
    @FXML
    private JFXComboBox<String> paysTel;
    
    @FXML
    private JFXTextField address;
    
    @FXML
    private ImageView addressImg;
    
    @FXML
    private JFXTextField email;

    @FXML
    private ImageView emailImg;
    
    @FXML
    private JFXTextField website;

    @FXML
    private ImageView websiteImg;   
    
    @FXML
    private JFXComboBox<String> type;
    
    @FXML
    private ImageView typeImg;

    
    //====================== La connection a la BD utilisee =============
    Connection con;
    
    //========================== Les Tableuax utilises =====================
    ObservableList<Fournisseurs> listSuppliers = FXCollections.observableArrayList();
    
    List<TypeFournisseur> typeFournisseur;
    
    ObservableList<Boolean> fieldValue = FXCollections.observableArrayList();//Utilise pour tester la validite des entrees de user dans notre formulaire
    
    
    //============================ Les DAO Utilises =======================
    TypeFournisseurDAO typeFournisseurUtils;           
    
    FournisseursDAO fournisseursUtils;
    
    //===Utilise pour connaitre si on fait une modification d'in fournisseur ou si on en ajoute un ==
    String operation = "";
    
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        id.setDisable(true);
        modify.setDisable(true);
        delete.setDisable(true);
        
        for(int i=0;i<6;i++)
        {
            fieldValue.add(false);
        }
        fieldValue.addListener(new ListChangeListener(){
            @Override
            public void onChanged(ListChangeListener.Change c) {
                confirm.setDisable(fieldValue.contains(false));
            }
            
        });
        
    }    
    
   //// =================================== Fonctions effectuant des traitements specifiques ===============================
    
    /** Recupere la connection et charge les informations utilisant la B.D. : les types de fournisseurs, la liste des fournisseurs,\.
     * @param con.
     */
    public void setConnection(Connection con){
        this.con = con;
        //Initialise le DAO de TypeFournisseur et charge la liste des types de fournisseurs
        typeFournisseurUtils  = new TypeFournisseurDAO(con);
        typeFournisseur = typeFournisseurUtils.list();
        
        //Initialise le DAO de Fournisseurs
        fournisseursUtils = new FournisseursDAO(con, typeFournisseur);  
        
        loadTypeFournisseur();//Ajoute les typeFournisseur a la comboBox
        
        initializeTableView();
        
        loadSuppliers();
    }
      
    /**Lire la BD et charger tous les types de fournisseurs dans la comboBox "type" **/
    public void loadTypeFournisseur(){
        typeFournisseur.forEach((e)->{
            type.getItems().addAll(e.getName());
        });
    }
    
    /**Initialisation de la tableview**/
    public void initializeTableView(){
        //id Column
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        
        //name Column
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        
        //address Column
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        
        //tel Column
        telColumn.setCellValueFactory(new PropertyValueFactory<>("tel"));
       
        //email column
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        websiteColumn.setCellValueFactory(new PropertyValueFactory<>("website"));    
 
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        
        TableViewSelectionModel model = tableView.getSelectionModel();
        model.setSelectionMode(SelectionMode.MULTIPLE);//On peut selectionner plusieurs entrees a la fois
        model.selectedItemProperty().addListener((ObservableValue obs, Object oldSelection, Object newSelection) -> {
            modify.setDisable((newSelection == null));
            delete.setDisable((newSelection == null));
        });
         
        tableView.setPlaceholder(new Label("No Supplier in the database"));
    }
    
    /** Lire la BD et charger tous les fournisseurs dans la tableView i.e. Les ajouter dans l'ArrayList listSuppliers*/
    public void loadSuppliers(){
        tableView.setItems(null);
        fournisseursUtils.list().forEach((e)->{
            listSuppliers.add(e);
        });
        tableView.setItems(listSuppliers);
        
    }
    
    /** Desactive tous les champs du formulaire si value == true et les active sinon
     * @param value
     */
    public void disableFields(Boolean value){
        address.setDisable(value);
        tel.setDisable(value);
        website.setDisable(value);
        email.setDisable(value);
        name.setDisable(value);
        type.setDisable(value);
    }
    
    /** Initialise tous les champs de notre formulaire en effacant ce qu'ils contiennent*/
    public void initFields(){
        id.setText("");
        name.setText("");
        email.setText("");
        website.setText("");
        tel.setText("");
        address.setText("");
        type.setValue("");
    }
    
    /** Charge les donnees d'un fournisseur( represente par l'argument data) dans les differents champs
     * @param data 
     **/
    public void loadData(Fournisseurs data){
        id.setText(String.valueOf(data.getId()));
        name.setText(data.getName());
        email.setText(data.getEmail());
        website.setText(data.getWebsite());
        tel.setText(data.getTel());
        address.setText(data.getAddress());
        type.setValue(data.getType());
    }
    
    /**Initialize l'interface si initialize == true (Fait l'inverse de tous ce qui suit si initialize == false)
     *  --active le bouton "Add"
     *  --Affiche les buttons "Modify" et "Delete" ( Ils sont contenus dans le Pane "modifyPane" s'est lui qu'on rend invisible)
     *  --Cache les buttons "Confirm" et "Cancel" ( Ils sont contenus dans le Pane "confirmPane" s'est lui qu'on rend invisible)
     *  --Desactive les differents champs de notre formulaire
     *  --Desactive la tableView
     *  --Les images a cote des text field sont retirees
     * @param initialize
    **/
    public void initializeInterface(Boolean initialize){
        add.setDisable(!initialize);
        modifyPane.setVisible(initialize);
        confirmPane.setVisible(!initialize);
        disableFields(initialize);
        tableView.setDisable(!initialize);
        
        nameImg.setImage(null);
        telImg.setImage(null);
        addressImg.setImage(null);
        emailImg.setImage(null);
        websiteImg.setImage(null);
        typeImg.setImage(null);
    }
    
    /** Creer et retourne un nouveau Fournisseur a partir des donnees entrees dans le formulaire
     * @return  
     **/        
    public Fournisseurs getData(){
        //Creer le nouveau fournisseur et le retourne
        Fournisseurs data = new Fournisseurs(Integer.valueOf(id.getText()),
                                             name.getText(),
                                             address.getText(),
                                             tel.getText().trim(),
                                             email.getText(),
                                             website.getText(),
                                             type.getValue()
                                );
        return data;
    }
    
   /** Modifie les donnees d'un fournisseurs avec les nouvelles donnees presentent dans l'argument data.
     * @param data
     * @return */
    public Boolean modifyData(Fournisseurs data){
        ObservableList<Fournisseurs> items = tableView.getSelectionModel().getSelectedItems();
        Fournisseurs e = items.get(0);
        e.setName(data.getName());
        e.setTel(data.getTel());
        e.setAddress(data.getAddress());
        e.setEmail(data.getEmail());
        e.setType(data.getType());
        e.setWebsite(data.getWebsite());
        tableView.refresh();
        return true;
    }
    
    /**Raccourci de System.out.println*/
    void print(Object value)
    {
        System.out.println(value);
    }
    
    //====================== Les listeners des differents buttons et Elements de notre vue =========================
    
    /** Fonction appelee lorsque le bouton "Add" est clicke */
    @FXML
    void addClicked(ActionEvent event) {
        modifyClicked(null);
        initFields();
        id.setText(String.valueOf(listSuppliers.size() + 1));
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
    void deleteClicked(ActionEvent event) {
            
         ObservableList<Fournisseurs> items = tableView.getSelectionModel().getSelectedItems();
         if(items == null)
         {
             //message d'erreur "Pas d'elements selectionnes
         }else{
              //**supprimer l'enregistrement selectionne
             //**Retirer a la tableView
             items.forEach((data) -> {
                 fournisseursUtils.delete(data); //System.out.println("Deleting to DB Successfully");
                 listSuppliers.remove(data);
             });
                //======Re-Initialiser l'interface
               initializeInterface(true);
         }
    }    
    
    /** Fonction appelee lorsque le bouton "Cancel" est clicke */
    @FXML
    void cancelClicked(ActionEvent event) {
        initializeInterface(true);
        tableViewClicked(null);
    }
    
    /** Fonction appelee lorsque le bouton "Confirm" est clicke */
    @FXML
    void confirmClicked(ActionEvent event) {
        
        Fournisseurs data = getData();
        //***Ajouter a la BD
        System.out.println("Adding to DB Successfully");
        if(operation.equals("ADD")){
            fournisseursUtils.create(data);
            //**Ajouter a la tableView
            listSuppliers.add(data);
            tableView.getSelectionModel().clearSelection();
            tableView.getSelectionModel().select(data);
        }
        else if (operation.equals("MODIFICATION"))
        {
            fournisseursUtils.update(data);
            //Modification dans la tableView
            modifyData(data);
        }
        else{
            print("Error --confirmClicked-- Line 426 SuppliersController ");
        }
        //======ReInitialiser l'interface
        initializeInterface(true);
        loadData(data);
    }
    
    /** Elle est appelee lorsqu'on click sur le tableview. Charge les informations de la ligne selectionnee et la charge dans le formulaire**/
    @FXML
    void tableViewClicked(MouseEvent event) {
        ObservableList<Fournisseurs> items = tableView.getSelectionModel().getSelectedItems();
        //Charge les informations du premier selectionne dans le formulaire
        print("Items unselected");
        if((items!=null))
        {
            print("Items selected");
            if(!items.isEmpty())
            {
                print("Items selected*2");
                loadData(items.get(0));
            }
        }        
        
    }
    
    
   //================= Fonctions qui verifient les informations entrees par l'utilisateur dans notre formulaire ============
    
    public static Boolean verifyValue(String value, String regex, ImageView report,JFXButton confirm){
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
    
    @FXML
    void verifyName(KeyEvent e) {
       //use regex here for testing tel
       String regex = "[\\w|\\s]*";
       String value = name.getText();
       fieldValue.set(0, verifyValue(value,regex,nameImg,confirm));
    }
    
    @FXML
    void verifyTel(KeyEvent e) {
       //use regex here for testing tel
       String regex = "^\\d+$";
       String value = tel.getText().replace(" ", "");
       fieldValue.set(1, verifyValue(value,regex,telImg,confirm));
    }
    
     @FXML
    void verifyAddress(KeyEvent e){
        String regex= "[\\w|\\s]*";
        String value = address.getText();
        fieldValue.set(2, verifyValue(value,regex,addressImg,confirm));
    }
    
    @FXML
    void verifyEmail(KeyEvent e){
        String regex= "\\w*@\\w*\\.\\w*";
        String value = email.getText();
        fieldValue.set(3, verifyValue(value,regex,emailImg,confirm));
    }
    
    @FXML
    void verifyWebsite(KeyEvent e){
        String regex= "\\w*\\.\\w*";
        String value = website.getText();
        fieldValue.set(4, verifyValue(value,regex,websiteImg,confirm));
    }
    
    @FXML
    void verifyType(MouseEvent e){
        String regex= "\\w*";
        String value = type.getValue();
        fieldValue.set(5, verifyValue(value,regex,typeImg,confirm));
    }  
    
    public void verifyAll()
    {
        verifyName(null);
        verifyTel(null);
        verifyAddress(null);
        verifyEmail(null);
        verifyWebsite(null);
        verifyType(null);
    }

}
