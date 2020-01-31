/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
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
import javafx.stage.Stage;
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
    
    private databaseUtils utils;
    
    private ObservableList<LoginCredentials> users ;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
    }
    
    @FXML
    public void initReport()
    {
        if(utils.getStateConnection())
        {
            report.setId("report");
            report.setText("Server is up: Go to do");
        }
        else{
            report.setText("Server is Down: Retrying ...");
            report.setId("reportFailed");
            utils.reloadConnection();
            report.setText("Server is up: Go to do");
            report.setId("report");            
        } 

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializeDataBase();        
    }   
    
    public void initializeDataBase()
    {
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
    public void loadUserDetails()
    {
        users = utils.getLoginCredentails();
    }
    
    @FXML
    public void passwordForget()
    {
        JFXDialogLayout content = new JFXDialogLayout();
        //content.setHeading(new Text("Reset password"));
        FXMLLoader loader;
        final JFXDialog  dialog = new JFXDialog(stackPane,content,JFXDialog.DialogTransition.CENTER);
        try{
            loader = new FXMLLoader(getClass().getResource("/view/resetPasswordContent.fxml"));
            
            Parent root = loader.load();
            
            ResetPasswordContentController con = null;
            con = loader.getController(); 
            /*if(con == null)
            {
                System.out.println("Loader failed");
            }*/
                BackgroundImage background = new BackgroundImage(new Image("/images/hen3.jpg"),
                                                             BackgroundRepeat.NO_REPEAT,
                                                             BackgroundRepeat.NO_REPEAT,
                                                             BackgroundPosition.CENTER, 
                                                             new BackgroundSize(100,100,true,true,true,true)
                                                            );
            content.setBackground(new Background(background));
            content.setBody((Node)root);
            con.setUtils(utils);
            con.getCancelButton().setOnAction((me)->{
                dialog.close();
            });
            con.getCloseButton().setOnMouseClicked((me)->{
                dialog.close();
            });
            //content.setActions(con.getCancelButton());
         }catch (IOException ex) {
            content.setBody(new Text("Loading of interface failed. Try again later..."));
            Tools.showNotification("Reset Password", "Loading of interface failed", Boolean.TRUE);
            JFXButton ok = new JFXButton("Okay");
            ok.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent me) {
                    dialog.close();
                }
            });
            content.setActions(ok);
        }
        dialog.show();
    }
    
    @FXML
    public void verifyAdmin()
    {
        verifyInfo(true);
    }
    
    @FXML
    public void verifyUser()
    {
         verifyInfo(false);
    }
    
    public void verifyInfo(Boolean isAdmin)
    {
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
               report.setText("Loading Dashboard Interface ... ");               
               report.setId("report");
        }
        else
        {
            report.setText("Authentification Failed. Verify your informations ");
            report.setId("reportFailed");
            disableField(false);
        }
    }
    
    @FXML
    public void close(ActionEvent e)
    {
        utils.closeConnection();
        Stage stage =(Stage) ((Node)e.getSource()).getScene().getWindow();
        stage.close();
    }
    
    @FXML
    public void disableField(Boolean disable)
    {
        username.setDisable(disable);
        passwordField.setDisable(disable);
        signButton.setDisable(disable);
        signAdminButton.setDisable(disable);
       // exitButton.setDisable(disable);
        passwordButton.setDisable(disable);
    }
}
