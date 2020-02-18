/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXRadioButton;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import model.Profils;

/**
 *
 * @author Isaac
 */
public class AdminDashBoardContentController implements Initializable {
     @FXML
    private Pane centerPane;

    @FXML
    private Pane mailPane;

    @FXML
    private ImageView unreadedMail;

    @FXML
    private Label textNotification;

    @FXML
    private Pane notificationPane;

    @FXML
    private ImageView imgNotification;

    @FXML
    private Label nameProfile2;

    @FXML
    private Label date;

    @FXML
    private Label cash;

    @FXML
    private Label totalBande;

    @FXML
    private Label bandeAVacc;

    @FXML
    private Label OeufIncub;

    @FXML
    private AreaChart<?, ?> cashGraph;

    @FXML
    private LineChart<?, ?> healthGraph;

    @FXML
    private JFXRadioButton entree;

    @FXML
    private JFXRadioButton sortie;

    @FXML
    private JFXRadioButton benefice;

    @FXML
    private AreaChart<?, ?> clientGraph;

    @FXML
    private Label date2;

    @FXML
    private BarChart<?, ?> salesGraph;
    
    private String userName;
    
    public Connection con;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       //
    }  
    public void initUserName(String user)
    {
        userName = user;
        nameProfile2.setText(userName);        
    }
    
    public void setConnection(Connection con)
    {
        this.con =con;
    }
}
