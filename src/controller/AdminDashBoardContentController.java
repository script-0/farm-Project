/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

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
    private Label totalEgg;
    @FXML
    private Label bandeAVacc;

    @FXML
    private Label oeufIncube;

    @FXML
    private AreaChart<String, Number> cashGraph;
    
    @FXML
    private JFXButton refreshCash;

    @FXML
    private PieChart healthGraph;

    @FXML
    private JFXButton refreshHealth;
    
    @FXML
    private JFXRadioButton entree;

    @FXML
    private JFXRadioButton sortie;

    @FXML
    private JFXRadioButton benefice;

    @FXML
    private AreaChart<String, Number> clientGraph;
    
    @FXML
    private JFXButton refreshClient;
     
    @FXML
    private Label date2;

    @FXML
    private BarChart<String, Number> salesGraph;
    
    @FXML
    private JFXButton refreshSales;
    
    XYChart.Series<String,Number> entryChart = new XYChart.Series<>();
   
    XYChart.Series<String,Number> expensesChart = new XYChart.Series<>();
    
    XYChart.Series<String,Number> profitChart = new XYChart.Series<>();
    
    XYChart.Series<String,Number> clientChart = new XYChart.Series<>();
    
    XYChart.Series<String,Number> salesChart = new XYChart.Series<>();
    
    ObservableList<PieChart.Data> healthChartData;
   
    private String userName;
    
    public Connection con;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        entree.setSelected(true);
        sortie.setSelected(true);
        benefice.setSelected(true);
        
        setDate();
        setAllItems();
       // //cashGraph
                cashGraph.setTitle("Cash");
                cashGraph.getXAxis().setLabel("Semaine");
                cashGraph.getYAxis().setLabel("Somme( $ x500)");
                
                loadProfitData();
                cashGraph.getData().add(profitChart);
                                
                loadEntryData();
    		cashGraph.getData().add(entryChart);
                
                loadExpensesData();
    		cashGraph.getData().add(expensesChart);

       //Heatlth Graph
                 healthGraph.setTitle("Death Evolution");
                 healthChartData = healthGraph.getData();
                 
                 loadHealthData();
                
    	//ClientGraph
                clientGraph.getYAxis().setLabel("Nombre Clients");
                clientChart.setName("Nombre de client");
                
                loadClientData();
    		clientGraph.getData().add(clientChart);

    	//salesGraph
                salesGraph.getYAxis().setLabel("Somme gagnee($ x 5000)");
                salesChart.setName("Somme de ventes");
                
                loadSalesData();
    		salesGraph.getData().add(salesChart);
    }  
    
    void setDate(){
        //
       date.setText("Mar 14,2020 - Mar 21, 2020");
       date2.setText("Mar 14,2020 - Mar 21, 2020");
    }
    
    void setTotalEarning(){
        cash.setText("1 000 000");
    }
    
    void setTotalBande(){
        totalBande.setText("25 000");
    }
    
    void setTotalEgg(){
        totalEgg.setText("5 000");
    }
    
    void setBandeAVacciner(){
        bandeAVacc.setText("10 000");
    }
    
    void setOeufIncube(){
        oeufIncube.setText("7 000");
    }
    
    void setAllItems(){
        setTotalEarning();
        setTotalBande();
        setTotalEgg();
        setBandeAVacciner();
        setOeufIncube();
    }
    
    void loadHealthData(){
        healthChartData.addAll(new PieChart.Data("Ebola", 1000),
                               new PieChart.Data("Peste", 1500),
                               new PieChart.Data("Sida", 500),
                               new PieChart.Data("Covid 19", 1000));
    }
    
    void loadProfitData(){
            profitChart.setName("Benefices");
        /*// resultSet va contenir les jours et les profits du mois en cours
    		while(resultSet.next()){
    			profitChart.getData.add(new XYChart.Data<Number, Number>(resultSet.getInt(),result.getDouble()));
    		}*/
                profitChart.getData().add(new XYChart.Data<>("0",0));
                profitChart.getData().add(new XYChart.Data<>("1",-11));
                profitChart.getData().add(new XYChart.Data<>("2",-11));
                profitChart.getData().add(new XYChart.Data<>("3",10));
                profitChart.getData().add(new XYChart.Data<>("4",20));
                profitChart.getData().add(new XYChart.Data<>("5",-3));
                profitChart.getData().add(new XYChart.Data<>("6",10));
                profitChart.getData().add(new XYChart.Data<>("7",20));
                profitChart.getData().add(new XYChart.Data<>("8",13));
    }
    
    void loadEntryData(){
        entryChart.setName("Entrées");
        /*// resultSet va contenir les jours et les entree du mois en cours
    		while(resultSet.next()){
    			entryChart.getData.add(new XYChart.Data<Number, Number>(resultSet.getInt(),result.getDouble()));
    		}*/
                entryChart.getData().add(new XYChart.Data<>("0",2));
                entryChart.getData().add(new XYChart.Data<>("1",9));
                entryChart.getData().add(new XYChart.Data<>("2",11));
                entryChart.getData().add(new XYChart.Data<>("3",23));
                entryChart.getData().add(new XYChart.Data<>("4",20));
                entryChart.getData().add(new XYChart.Data<>("5",2));
                entryChart.getData().add(new XYChart.Data<>("6",25));
                entryChart.getData().add(new XYChart.Data<>("7",20));
                entryChart.getData().add(new XYChart.Data<>("8",20));
    }  
        
    public void loadExpensesData(){
        expensesChart.setName("Sorties");
        /*// resultSet va contenir les jours et les sortie du mois en cours
    		while(resultSet.next()){
    			expensesChart.getData.add(new XYChart.Data<Number, Number>(resultSet.getInt(),result.getDouble()));
    		}*/
                expensesChart.getData().add(new XYChart.Data<>("0",2));
                expensesChart.getData().add(new XYChart.Data<>("1",20));
                expensesChart.getData().add(new XYChart.Data<>("2",21));
                expensesChart.getData().add(new XYChart.Data<>("3",13));
                expensesChart.getData().add(new XYChart.Data<>("4",0));
                expensesChart.getData().add(new XYChart.Data<>("5",5));
                expensesChart.getData().add(new XYChart.Data<>("6",15));
                expensesChart.getData().add(new XYChart.Data<>("7",0));
                expensesChart.getData().add(new XYChart.Data<>("8",7));
    }
    
    public void loadClientData(){
        /*// resultSet va contenir les jours et les  du mois en cours
    		while(resultSet.next()){
    			clientChart.getData.add(new XYChart.Data<Number, Number>(resultSet.getInt(),result.getDouble()));
    		}*/
                clientChart.getData().add(new XYChart.Data<>("Jan",25));
                clientChart.getData().add(new XYChart.Data<>("Fev",30));
                clientChart.getData().add(new XYChart.Data<>("Mar",30));
                clientChart.getData().add(new XYChart.Data<>("Av",25));
                clientChart.getData().add(new XYChart.Data<>("May",20));
                clientChart.getData().add(new XYChart.Data<>("Jun",25));
                clientChart.getData().add(new XYChart.Data<>("Jul",15));
                clientChart.getData().add(new XYChart.Data<>("Au",20));
                clientChart.getData().add(new XYChart.Data<>("Sep",17));
    }
    
    public void loadSalesData(){
        /*// resultSet va contenir les jours et les ventes du mois en cours
    		while(resultSet.next()){
    			salesChart.getData.add(new XYChart.Data<Number, Number>(resultSet.getInt(),result.getDouble()));
    		}*/
                
                salesChart.getData().add(new XYChart.Data<>("Franc",2));
                salesChart.getData().add(new XYChart.Data<>("Mathieu",30));
                salesChart.getData().add(new XYChart.Data<>("George",21));
                salesChart.getData().add(new XYChart.Data<>("Shaguile",13));
                salesChart.getData().add(new XYChart.Data<>("Alex",0));
                salesChart.getData().add(new XYChart.Data<>("Indrid",5));
                salesChart.getData().add(new XYChart.Data<>("Loe",15));
                salesChart.getData().add(new XYChart.Data<>("Oreal",0));
                salesChart.getData().add(new XYChart.Data<>("Stephen",7));
    }
    
    public void initUserName(String user){
        userName = user;
        nameProfile2.setText(userName);        
    }
    
    public void setConnection(Connection con){
        this.con =con;
    }

    @FXML
    void refreshCash(ActionEvent event) {
         profitChart.getData().clear();
        loadProfitData();
        
        entryChart.getData().clear();
        loadEntryData();
        
        expensesChart.getData().clear();
        loadExpensesData();
    }

    @FXML
    void refreshClient(ActionEvent event) {
        clientChart.getData().clear();  
        loadClientData();
    }

    @FXML
    void refreshHealth(ActionEvent event) {
        healthGraph.getData().clear();
        loadHealthData();
    }

    @FXML
    void refreshSales(ActionEvent event) {
       salesChart.getData().clear();
       loadSalesData();
    }
    
    @FXML
    void earningButtonClicked(ActionEvent event) {
        if(benefice.isSelected()){
            if(!cashGraph.getData().contains(profitChart))
                cashGraph.getData().add(profitChart);
        }else{
            cashGraph.getData().remove(profitChart);
        }
         setColor();
    }
    
    @FXML
    void inComingButtonClicked(ActionEvent event) {
        if(entree.isSelected()){
            if(!cashGraph.getData().contains(entryChart))
    		cashGraph.getData().add(entryChart);
    	}else{
            cashGraph.getData().remove(entryChart);
        }
         setColor();
    }
    
    @FXML
    void outComingButtonClicked(ActionEvent event) {
        if(sortie.isSelected()){
            if(!cashGraph.getData().contains(expensesChart))
                cashGraph.getData().add(expensesChart);
        }else{
            cashGraph.getData().remove(expensesChart);
        }
        setColor();
    }
    
    void setColor(){
         ObservableList<XYChart.Series<String, Number>> tmp = cashGraph.getData();
         int j = tmp.indexOf(profitChart);
         setElementStyle(j,benefice);
         
         j = tmp.indexOf(entryChart);
         setElementStyle(j,entree);
         
         j = tmp.indexOf(expensesChart);
         setElementStyle(j,sortie);
         
    }
    
    void setElementStyle(int i , JFXRadioButton button){
         entree.setId("");
         sortie.setId("");
         benefice.setId("");
         switch (i) {
             case 0:
                 button.setId("firstElement");
                 break;
             case 1:
                 button.setId("secondElement");
                 break;
             default:                
                 break;
        }
    }
}
