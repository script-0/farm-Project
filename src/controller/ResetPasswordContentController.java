/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Tools;
import model.databaseUtils;
import model.mailSender;

/**
 * FXML Controller class
 *
 * @author Isaac
 */
public class ResetPasswordContentController implements Initializable {

    
      @FXML
    private JFXTextField username;

    @FXML
    private JFXTextField code;   
    @FXML
    private JFXButton ok;

    @FXML
    private ImageView imageUsername;

    @FXML
    private Label report;

    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXPasswordField confirmPassword;

    @FXML
    private JFXButton confirm;

    @FXML
    private JFXButton cancel;

    @FXML
    private ImageView imagePassword;
    
    @FXML
    private ImageView imageCode;
    
    @FXML
    private ImageView close;

    
    private String email;
    
    private String user;
    
    private databaseUtils utils;
    
    private String verificationCode;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        report.setText("Server is up: Go to do");
        disabled(true);        
        confirm.setDisable(true);
    }   
    
    
    public JFXButton getCancelButton()
    {
        return cancel;
    }
    
    public ImageView getCloseButton()
    {
        return close;
    }
    
    public void setUtils(databaseUtils utils)
    {
        this.utils = utils;
    }
    
    private void disabled(Boolean disable)
    {
        password.setDisable(disable);
        code.setDisable(disable);
        confirmPassword.setDisable(disable);
    }
    
    @FXML
    public void initReport()
    {
        report.setText("Server is up. Go to do");
        report.setId("report");
        imageUsername.setImage(null);
        disabled(true);
    }
    
    @FXML
    public void verifyPassword(){
        if(!(confirmPassword.getText().equals("")))
        {  
          if(password.getText().equals(confirmPassword.getText()))
          {
              imagePassword.setImage(new Image("/images/confirmNotification.png"));
              confirm.setDisable(false);
          }
          else{
              imagePassword.setImage(new Image("/images/errorNotification.png"));
              confirm.setDisable(true);
          }
        }
        else{
            if(password.getText().equals(""))
            {
                confirm.setDisable(true);
                imagePassword.setImage(null);
            }
        }
    }
    
    @FXML
    public void verifyCode(){
        if(code.getText().equals(""))
        {
              imageCode.setImage(null);     
        }
        else if(code.getText().equals(verificationCode))
        {  
          imageCode.setImage(new Image("/images/confirmNotification.png"));
        }
        else{
          imageCode.setImage(new Image("/images/errorNotification.png"));
        }
    }
    
    @FXML
    public void confirm(){
        if(utils.setPassword(user,password.getText()))
        {
           Tools.showNotification("Reset Password", "Password reseted successfully", Boolean.FALSE);
            confirm.setDisable(true);
            disabled(true);
            cancel.setId("valid");
            cancel.setText("OK");
       }
        else{
            Tools.showNotification("Reset Password", "Reset password failed.", Boolean.TRUE);
        }
    }
    
    @FXML
    public void cancel(){
        
    }
    
    private String getVerificationCode()
    {
        String c1,c2,c3,c4,c5;
        c1 = String.valueOf((int)(Math.random()*10));
        c2 = String.valueOf((int)(Math.random()*10));
        c3 = String.valueOf((int)(Math.random()*10));
        c4 = String.valueOf((int)(Math.random()*10));
        c5 = String.valueOf((int)(Math.random()*10));
        return c1+c2+c3+c4+c5;
    }
    
   @FXML
    public void okClicked()
    {
        user = username.getText();
        if(user.equals(""))
        {
            report.setId("reportFailed");
            report.setText("Please enter your username");
        }
        else{
            //Get email
            email = utils.getEmail(user);
            if(email.equals(""))
            {
                report.setId("reportFailed");
                report.setText("Username not found.");
                imageUsername.setImage(new Image("/images/errorNotification.png"));
            }
            else
            {
                imageUsername.setImage(new Image("/images/confirmNotification.png"));                
                report.setText("Reset Password");
                
                //Generate verification code
                verificationCode = getVerificationCode();
                //sendEmail
               /* Boolean result = mailSender.send(email,verificationCode);               
                if(result)
                {*/System.out.println("Code:"+verificationCode);
                    disabled(false);
               // }
             }
            
        }
    }
    
}
