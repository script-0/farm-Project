/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.itextpdf.text.DocumentException;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import model.DAO.DAOFactory;
import model.DAO.FournisseursDAO;
import model.DAO.TypeFournisseurDAO;
import model.PrinterClass.PdfViewer;
import model.PrinterClass.PrinterClass;
import model.classes.Fournisseurs;
import model.classes.TypeFournisseur;

/**
 * FXML Controller class
 *
 * @author Isaac
 */
public class SuppliersController extends defaultController<Fournisseurs> implements Initializable {

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
    
    @FXML
    private JFXComboBox<String> searchColumn;
    
    @FXML
    private TextField searchTextField;
    
    @FXML
    private JFXButton searchButton;
    
    @FXML
    private JFXButton stopSearchButton;
    
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
    
    FilteredList<Fournisseurs> filteredData;
    
    SortedList<Fournisseurs> sortedData ;
    
    int selectedSearchColumn = 1;/* 1 => Name
                                    2 => Address
                                    3 => Tel
                                    4 => Email
                                    5 => Website
                                    6 => Type
                                 */
    
    ArrayList<String> listColumnNames = new ArrayList<>();
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        loadColumnNames();
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
        
        searchColumn.getItems().addAll("Name","Address","Tel","Email","Website","Type");
        searchColumn.setValue("Name");
        
    }    
    
   //// =================================== Fonctions effectuant des traitements specifiques ===============================
    
    void loadColumnNames(){
        listColumnNames.add("id");
        listColumnNames.add("Name");
        listColumnNames.add("Address");
        listColumnNames.add("Tel");
        listColumnNames.add("Email");
        listColumnNames.add("Website");
        listColumnNames.add("Type");
    }
    
    @FXML
    public void print(){
        ArrayList<String> data = new ArrayList<>();
        sortedData.forEach((value)->{
            data.add(String.valueOf(value.getId()));
            data.add(value.getName());
            data.add(value.getAddress());
            data.add(value.getTel());
            data.add(value.getEmail());
            data.add(value.getWebsite());
            data.add(value.getType());
        });
       
        try {
             PrinterClass printer = new PrinterClass("List of Suppliers",listColumnNames,data);
             printer.printToPDF();
            // PdfViewer view = new PdfViewer(PrinterClass.DEFAULT_OUTPUT);
            // view.show();
        } catch (DocumentException | IOException ex) {
            Logger.getLogger(SuppliersController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /** Recupere la connection et charge les informations utilisant la B.D. : les types de fournisseurs, la liste des fournisseurs.
     * @param con.
     */
    @Override
    public void setConnection(Connection con){
        this.con = con;
        //Initialise le DAO de TypeFournisseur et charge la liste des types de fournisseurs
        typeFournisseurUtils  = DAOFactory.getTypeFournisseursDAO(con);
        typeFournisseur = typeFournisseurUtils.list();
        
        //Initialise le DAO de Fournisseurs
        fournisseursUtils = DAOFactory.getFourniseursDAO(con, typeFournisseur);  
        
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
    
    @Override
    public void initializeTableView(){
        //id Column
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
      //  idColumn.setCellValueFactory(cellData -> cellData.getValue().getId());
        
//name Column
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        //nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
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
         
        tableView.setPlaceholder(new Label("No Suppliers in the database"));
    }
    
    /** Lire la BD et charger tous les fournisseurs dans la tableView i.e. Les ajouter dans l'ArrayList listSuppliers*/
    public void loadSuppliers(){
        //Load Suppliers from Database
        tableView.setItems(null);
        fournisseursUtils.list().forEach((e)->{
            listSuppliers.add(e);
        });
        
        //Wrap the ObservableList in a FilteredList (initially display all data).
        filteredData = new FilteredList<>(listSuppliers,p->true);
        
        //Set the filter Predicate whenever the filter changes.
        searchTextField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            filteredData.setPredicate(person->{
                if(newValue == null || newValue.isEmpty()){ 
                    searchButton.setVisible(true);
                    stopSearchButton.setVisible(false);
                    return true;
                }
                
                searchButton.setVisible(false);
                stopSearchButton.setVisible(true);
                
                // Compare name of every suppliers with filter text.
                String lowerCaseFilter = newValue.toLowerCase();
                
                switch(searchColumn.getValue())
                {
                    case "Name":
                        if(person.getName().toLowerCase().contains(lowerCaseFilter)) return true;
                        break;
                    case "Address":
                        if(person.getAddress().toLowerCase().contains(lowerCaseFilter)) return true;
                        break;
                    case "Tel":
                        if(person.getTel().toLowerCase().contains(lowerCaseFilter)) return true;
                        break;
                    case "Website":
                        if(person.getWebsite().toLowerCase().contains(lowerCaseFilter)) return true;
                        break;
                    case "Email":
                        if(person.getEmail().toLowerCase().contains(lowerCaseFilter)) return true;
                        break;
                    case "Type":
                        if(person.getType().toLowerCase().contains(lowerCaseFilter)) return true;
                        break;
                    default:
                        break;
                }
                return false;
            });
        });
        
        // Wrap the FilteredList in a SortedList. 
        sortedData = new SortedList<>(filteredData);
        
        //Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(tableView.comparatorProperty());
        
        //Add sorted (and filtered) data to the table.
        tableView.setItems(sortedData);
        
        //Sauvegarde le plus grand Id pour les ajouts futurs
        maxId = listSuppliers.get(listSuppliers.size()-1).getId();
        
    }
    
    /** Desactive tous les champs du formulaire si value == true et les active sinon
     * @param value
     */
    @Override
    public void disableFields(Boolean value){
        address.setDisable(value);
        tel.setDisable(value);
        website.setDisable(value);
        email.setDisable(value);
        name.setDisable(value);
        type.setDisable(value);
    }
    
    /** Initialise tous les champs de notre formulaire en effacant ce qu'ils contiennent*/
    @Override
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
    @Override
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
    @Override
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
    @Override
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
    @Override
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
    
    @Override
    public void initializeId(){
        id.setText(String.valueOf(maxId + 1));
    }  
    
    //====================== Les listeners des differents buttons et Elements de notre vue =========================
    @FXML
    void initSearchPane(){
        searchTextField.clear();
        searchButton.setVisible(true);
        stopSearchButton.setVisible(false);
    }
    
    /** Fonction appelee lorsque le bouton "Add" est clicke */
    @Override
    @FXML
    void addClicked(ActionEvent event) {
        initializeInterface(false);
        initFields();
        initializeId();
        operation = "ADD";
    }
    
    /** Fonction appelee lorsque le bouton "Delete" est clicke */
    @Override
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
               tableViewClicked(null);
         }
    }    
    
    /** Fonction appelee lorsque le bouton "Confirm" est clicke */
    @Override
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
            maxId ++;
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
    @Override
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
            else{
                initFields();
            }
        }else{
                initFields();
            }     
        
    }
    
    
   //================= Fonctions qui verifient les informations entrees par l'utilisateur dans notre formulaire ============
    
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
        String regex= "[\\w|\\s|-]*";
        String value = address.getText();
        fieldValue.set(2, verifyValue(value,regex,addressImg,confirm));
    }
    
    @FXML
    void verifyEmail(KeyEvent e){
        String regex= "\\w*@\\w*\\.\\w+";
        String value = email.getText();
        fieldValue.set(3, verifyValue(value,regex,emailImg,confirm));
    }
    
    @FXML
    void verifyWebsite(KeyEvent e){
        String regex= "\\w*\\.\\w+";
        String value = website.getText();
        fieldValue.set(4, verifyValue(value,regex,websiteImg,confirm));
    }
    
    @FXML
    void verifyType(MouseEvent e){
        String regex= "\\w*";
        String value = type.getValue();
        fieldValue.set(5, verifyValue(value,regex,typeImg,confirm));
    }  
    
    @Override
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
