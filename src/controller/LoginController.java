/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXSpinner;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.LoginCredentials;
import model.Profils;
import model.Tools;
import model.databaseUtils;
import model.setupServer;

/**
 *
 * @author Isaac
 */
public class LoginController implements Initializable {
    
    @FXML
    private StackPane stackPane;
    
    @FXML
    private Pane progressPane;
    
    @FXML
    private Label progressLabel;
    
    @FXML
    private JFXSpinner progressSpinner;
    
    @FXML
    private JFXSpinner spinner;
    
    @FXML
    private JFXButton signAdminButton;

    @FXML
    private JFXTextField username;

    @FXML
    private JFXPasswordField passwordField;

    @FXML
    private Label report;

    @FXML
    private JFXButton passwordButton;

    @FXML
    private JFXButton signButton;

    @FXML
    private JFXButton exitButton;

    @FXML
    private Pane loginLeftPane;
    
    @FXML
    private JFXButton appSettings;
    
    @FXML
    private Label appName;
    
    @FXML
    private Label appDescription;
    
    @FXML
    private Label or;
    
    @FXML
    private Label or2;
    
    private databaseUtils utils;
    
    private ObservableList<LoginCredentials> users ;
    
    @FXML
    private JFXComboBox<String> language;
    
    ResourceBundle titres;
    
    Locale en = new Locale("en");
    
    Locale fr = new Locale("fr");    
    
    String lang = "En";
       
    @FXML
    private void handleButtonAction(ActionEvent event) {
    }
    
    @FXML
    public void initReport(){
        if(utils.getStateConnection())
        {
            report.setId("report");
            report.setText(titres.getString("reportGood"));
        }
        else{
            report.setText(titres.getString("reportFailed"));
            report.setId("reportFailed");
            utils.reloadConnection();
            report.setText(titres.getString("reportGood"));
            report.setId("report");            
        } 

    }
    
    void loadResource(String language){
        if(language.equalsIgnoreCase("Fr"))
        {   Tools.print("Lang = FR");
            titres = ResourceBundle.getBundle("languages/login",fr);
        }
        else{
            titres = ResourceBundle.getBundle("languages/login",en);
            Tools.print("Lang = En");
        }
        setResource();
    }
    
    void progressSpinner(Boolean value){
        spinner.setVisible(value);
    }
    
    void setResource(){        
        progressSpinner(true);
        appName.setText(titres.getString("appName"));
        appDescription.setText(titres.getString("appDescription"));
        appSettings.setText(titres.getString("appSettings"));
        username.setPromptText(titres.getString("username"));
        passwordField.setPromptText(titres.getString("passwordField"));
        passwordButton.setText(titres.getString("passwordButton"));
        signButton.setText(titres.getString("signButton"));
        exitButton.setText(titres.getString("exitButton"));
        signAdminButton.setText(titres.getString("signAdminButton"));
        if(report.getId().equalsIgnoreCase("report"))
            report.setText(titres.getString("reportGood"));
        else if(report.getId().equalsIgnoreCase("reportFailed"))
            report.setText(titres.getString("authentificationFailed"));
        else{}
        or.setText(titres.getString("or"));
        or2.setText(titres.getString("or"));
        progressSpinner(false);
    }
    
    @FXML
    void languageChanged(ActionEvent event){
            Tools.print("Language Changed");
            String v = language.getValue();
            if(!v.equalsIgnoreCase(lang)){
                loadResource(v);
                lang = v;
                
                
            }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
        language.getItems().addAll("En","Fr");
        language.setValue("En");
        loadResource("En");
        initializeDataBase();
    }   
    
    public void initializeDataBase(){
        final ExecutorService service;
        final Future<databaseUtils> task;
        service = Executors.newFixedThreadPool(1);
        task = service.submit(new setupServer(utils,report));
        
        try {
            utils = task.get();
            service.shutdownNow();
        } catch (InterruptedException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    public void loadUserDetails(){
        users = utils.getLoginCredentails();
    }
    
    @FXML
    public void passwordForget(){
        disableField(true);
        JFXDialogLayout content = new JFXDialogLayout();
        final JFXDialog  dialog = new JFXDialog(stackPane,content,JFXDialog.DialogTransition.CENTER);
        progressPane.setVisible(true);
      /*  Thread t = new Thread( new Runnable(){
            @Override
            public void run(){*/
       
        FXMLLoader loader;
        try{
            loader = new FXMLLoader(getClass().getResource("/view/resetPasswordContent.fxml"));
            Parent root = loader.load();
            ResetPasswordContentController con = null;
            con = loader.getController();
            if(con != null)
            {
                System.out.println("Loader ok");
            }
            BackgroundImage background = new BackgroundImage(new Image("/images/hen3.jpg"),
                                                                BackgroundRepeat.NO_REPEAT,
                                                                BackgroundRepeat.NO_REPEAT,
                                                                 BackgroundPosition.CENTER, 
                                            new BackgroundSize(100,100,true,true,true,true));
            content.setBackground(new Background(background));
            content.setBody((Node)root);
            con.setUtils(utils);
            con.getCancelButton().setOnAction((me)->{
                dialog.close();
            });
            con.getCloseButton().setOnMouseClicked((me)->{
                dialog.close();
            });
        }catch (IOException ex){
            content.setBody(new Text(titres.getString("loadingInterfaceFailed")));
            Tools.showNotification(titres.getString("resetPassword"), titres.getString("loadingInterfaceFailed"), Boolean.TRUE);
            JFXButton ok = new JFXButton("Ok");
            ok.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent me) {
                            dialog.close();
                    }
            });
            content.setActions(ok);
        }
        disableField(false);
        dialog.show();
        progressPane.setVisible(false);/*
             }
            }
        );
        t.start();*/
    }
    
    @FXML
    public void verifyAdmin(ActionEvent e){
        verifyInfo(true,e);
    }
    
    @FXML
    public void verifyUser(ActionEvent e){
         verifyInfo(false,e);
    }
    
    public void verifyInfo(Boolean isAdmin,ActionEvent e){
        String user = username.getText();
        String pass = passwordField.getText();
        //Les administrateur correspondent a typEm=1; les autre correspondent a -1
        int type = -1;
        if(isAdmin)
        {
            type = 1;
        }
        disableField(true);
        Profils userProfil = utils.findUser(new LoginCredentials(user,pass,type));
        if(userProfil !=null)
        {
                report.setText(titres.getString("loadingInterface"));               
                report.setId("report");
                
                Parent root;   
                FXMLLoader loader;
                AdminDashBoardController con;
                disableField(true);
                
                try {
                    loader = new FXMLLoader(getClass().getResource("/view/adminDashBoard.fxml"));
                    root = loader.load(); //Chargement du menu
                    
                    con = loader.getController();
                    con.initProfils(userProfil); //Initialise les parametres de l'utilisateur
                    con.setUtils(utils);
                    
                    con.loadContent(); // Chargement le contenu du DashBoard
                    
                    
                    Stage s = Tools.getStage(e);
                    s.setScene(new Scene(root));
                    s.centerOnScreen();
                    
                } catch (Exception ex) {
                    disableField(false);
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                    Tools.showNotification(titres.getString("loadDashboard"),titres.getString("loadingInterfaceFailed"), true);
                }
        }
        else
        {
            report.setText(titres.getString("authentificationFailed"));
            report.setId("reportFailed");
            disableField(false);
        }
    }
    
    @FXML
    public void close(ActionEvent e){
        utils.closeConnection();
        Tools.getStage(e).close();
    }
    
    @FXML
    public void disableField(Boolean disable){
        username.setDisable(disable);
        passwordField.setDisable(disable);
        signButton.setDisable(disable);
        signAdminButton.setDisable(disable);
        passwordButton.setDisable(disable);
    }
}
