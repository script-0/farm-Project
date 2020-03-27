/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Isaac
 */
public class databaseUtils {
    private static final DBConnection database = new DBConnection();
    public final String DEFAULT_IMAGE = "images/defaultProfile.png";
    private ResultSet rs;
    private PreparedStatement ps;
    private Connection connect;
    
    public databaseUtils(){}
    
    public void saveData(String query){
        try{
            connect = database.getDBConnection();
            ps = connect.prepareStatement(query);
            ps.executeQuery();    
        } catch (SQLException ex) {
            Logger.getLogger(databaseUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Boolean saveImageProfile(String user,String img){
        try {
            String query = "UPDATE Employes set image=\""+img+"\" where username=\""+user+"\";";
            connect = database.getDBConnection();
            ps = connect.prepareStatement(query);
            ps.executeUpdate();
            Tools.showNotification("Database Admin", "Updating of your profile image succeeded", Boolean.FALSE);
        } catch (SQLException ex) {
            Tools.print("Update of profil image of user"+user+" failed");
            Tools.showNotification("Database Admin", "Updating of your profile image failed.\n Try again later.", Boolean.TRUE);
            return false;
        }
        return true;
    }
    
    public Boolean saveUsername(String user,String newUser){
        try {
            String query = "UPDATE Employes SET username=\""+newUser+"\" WHERE username=\""+user+"\";";
            connect = database.getDBConnection();
            ps = connect.prepareStatement(query);
            ps.executeUpdate();
            Tools.showNotification("Database Admin", "Updating of your username succeeded", Boolean.FALSE);
        } catch (SQLException ex) {
            Tools.print("Update of usernmane of user"+user+" failed");
            Tools.showNotification("Database Admin", "Updating of your username failed.\n Try again later.", Boolean.TRUE);
            return false;
        }
        return true;
    }
    
    /* Return -2 si l'utilisateur n'est pas trouve
              -1 s'il est trouve mais les mots de passe ne correpondent pas
              0 s'il est trouve , les mots de passe correpondent mais pas le type d'emploi
              1 sinon
    */
    
    public ObservableList<UserCredentials> getUserCredentails()
    {
        String query = "select name, username ,login ,typeEm ,tel ,adress,email,image from Employes";
        ObservableList<UserCredentials> result = FXCollections.observableArrayList();
        ObservableList<String> typeEmploye = getTypeEmploye();
        
        try{
            connect = database.getDBConnection();
            ps = connect.prepareStatement(query);
            rs = ps.executeQuery();      
            while(rs.next())
            {
                result.add(new UserCredentials(rs.getString(1),
                                               rs.getString(2),
                                               rs.getString(3),
                                               typeEmploye.get(rs.getInt(4)),
                                               rs.getString(5),
                                               rs.getString(6),
                                               rs.getString(7),
                                               rs.getString(8)
                            ));
               //result.add(new UserCredentials(rs.getString(1),rs.getString(2),rs.getString(3),typeEmploye.get(rs.getInt(4) - 1),rs.getString(5),rs.getString(6)));
            }
            connect.commit();
        } catch (SQLException ex) {
            Logger.getLogger(databaseUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public Profils findUser(LoginCredentials details)
    {
        String query = "select username, typeEm , image, login from Employes where username='"+details.getUsername()+"'";// " and login='"+details.getPassword()+"'";
        Boolean passwordMatch = false;
        if(details.getType()!= -1)
        {
            query+= " and typeEm='"+details.getType()+"'";
        }
        query+=";";
        
        ObservableList<String> typeEm = getTypeEmploye();
        try {
            connect = database.getDBConnection();
            ps = connect.prepareStatement(query);
            rs = ps.executeQuery();
            ObservableList<Profils> result = FXCollections.observableArrayList();
            while(rs.next()){
                result.add(new Profils(rs.getString(1),typeEm.get(rs.getInt(2)-1),rs.getString(3)));
                if(rs.getString(4).equals(details.getPassword()))
                {
                    passwordMatch = true;
                }
            }
            if(!result.isEmpty())
            {
                if(passwordMatch )
                {
                    Tools.showNotification("User Informations", "Authentification succeeded", false);
                    return result.get(0);
                }else{
                    Tools.showNotification("User Informations", "Incorrect Password\nVerify your informations and try again", true);
                    return null;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(databaseUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        Tools.showNotification("User Informations", "User not found\nVerify your informations and try again", true);
        return null;
    }
    
    public Connection getConnection() throws SQLException
    {
       return database.getDBConnection();
    }
    
    public Boolean closeConnection()
    {
        return database.closeConnection();
    }
    
     public ObservableList<LoginCredentials> getLoginCredentails()
    {
        String query = "select username, login, typeEm from Employes;";
        ObservableList<LoginCredentials> result = FXCollections.observableArrayList();
        ObservableList<String> typeEmploye = getTypeEmploye();
        
        try{
            connect = database.getDBConnection();
            ps = connect.prepareStatement(query);
            rs = ps.executeQuery();      
            while(rs.next())
            {
                result.add(new LoginCredentials(rs.getString(1),rs.getString(2),rs.getInt(3)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(databaseUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
     public String getEmail(String username)
     {
         String query = "select email from Employes where username ='"+username+"';";
         String email = "";
         try{
            connect = database.getDBConnection();
            ps = connect.prepareStatement(query);
            rs = ps.executeQuery();      
            while(rs.next())
            {
               email = rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(databaseUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
         return email;
     }
     
     public Boolean setPassword(String user,String newPassword)
     {
         //
         return true;
     }
     
    public ObservableList<String> getTypeEmploye()
    {
        String query = "select nom from typeEmploye;";
        ObservableList<String> result = FXCollections.observableArrayList();
        try{
            connect = database.getDBConnection();
            ps = connect.prepareStatement(query);
            rs = ps.executeQuery();      
            while(rs.next())
            {
                result.add(rs.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(databaseUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public Boolean getStateConnection()
    {
        try {
            return database.getDBConnection().isValid(0);
        } catch (SQLException ex) {
            Logger.getLogger(databaseUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public Boolean reloadConnection()
    {
        try {
            connect = database.getDBConnection();
            if(connect.isValid(0))
            {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(databaseUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
