/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DAO;

import java.sql.Connection;
import java.util.List;

/**
 *
 * @author Isaac
 * @param <T>
 */

public abstract class DAO<T>{
    public Connection c = null ;
    
    public DAO(Connection con){
        c = con;
    }
    
    /**
    * Méthode de création
    * @param obj
    * @return boolean
    */
    public abstract boolean create(T obj);
   
    /**
    * Méthode pour effacer
    * @param obj
    * @return boolean
    */
    public abstract boolean delete(T obj);
    
    /**
    * Méthode de mise à jour
    * @param obj
    * @return boolean
    */
    public abstract boolean update(T obj);
    
    /**
    * Méthode de recherche des informations
    * @param id
    * @return T*/
    //public abstract T find(int id);
    
    public abstract List<T> list();

}
