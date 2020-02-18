/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Isaac
 */
public class DBConnection {
    private  static Connection DBConnection;
    public DBConnection(){
        launchConnection();
    }    
    
    private void launchConnection(){
        try{ 
                Class.forName("com.mysql.jdbc.Driver").newInstance();
            }catch (ClassNotFoundException ex)
            { 
                System.out.println("Problème au chargement"+ex.toString()); 
             } catch (InstantiationException | IllegalAccessException ex) {
                System.out.println("Error load database");
            }
            try { 
                   DBConnection = DriverManager.getConnection("jdbc:mysql://localhost/scriptFarm","root","password");
                   if(DBConnection.isValid(0))
                   {
                       System.out.println("Connection established");
                      //  Tools.showNotification("Connection to Server", "Server is up: Go to do", false);
                   }
                   else{
                       System.out.println("Connection failed");
                      //  Tools.showNotification("Connection to Server", "Connection failed. Retrying ...", true);
                   }
            } catch (SQLException ex) {
               System.out.println("Error load database");
            }
    }
    
    public Boolean closeConnection()
    {
        try {
            DBConnection.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public synchronized Connection getDBConnection() throws SQLException{
        if(DBConnection.isClosed())
        {
            launchConnection();
        }
        return DBConnection;
    }
    
}
