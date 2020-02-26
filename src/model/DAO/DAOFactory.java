/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DAO;

import java.sql.Connection;
import java.util.List;
import model.classes.TypeFournisseur;

/**
 *
 * @author Isaac
 */
public class DAOFactory {
    
    public static FournisseursDAO getFourniseursDAO(Connection con, List<TypeFournisseur> typeFournisseur)
    {
        return new FournisseursDAO(con, typeFournisseur);
    }
    
    public static TypeFournisseurDAO getTypeFournisseursDAO(Connection con){
        return new TypeFournisseurDAO(con);
    }
    
    
    
}
