/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.classes.Fournisseurs;
import model.classes.TypeFournisseur;

/**
 *
 * @author Isaac
 */
public class FournisseursDAO extends DAO<Fournisseurs>{

    List<String> typeFournisseur  = new ArrayList<>();
    public FournisseursDAO(Connection con, List<TypeFournisseur> typeFournisseur) {
        super(con);
        typeFournisseur.forEach((e)->{
            this.typeFournisseur.add(e.getName());
        });
    }

    @Override
    public boolean create(Fournisseurs a) {
        try {
		PreparedStatement stmt = this.c.prepareStatement("INSERT INTO Fournisseur VALUES ( ? , ? , ? , ? , ?, ?, ? )" ) ;
		this.c.setAutoCommit(false) ;
			
		stmt.setInt(1, a.getId());
		stmt.setString( 2 , a.getName()) ;
		stmt.setString(3, a.getAddress());
		stmt.setString(4 ,a.getTel());
                stmt.setString(5 , a.getEmail());
                stmt.setString(6 , a.getWebsite());
                stmt.setInt(7, typeFournisseur.indexOf(a.getType()) + 1); //On ajoute 1 parceque les index dans les List commencent a 0 or les id commence a 1
		
                stmt.execute();
		this.c.commit();
		this.c.setAutoCommit(true);
        } catch (SQLException e) {
            if ( this.c != null ) {
                try {
                        System.out.println("Operation Echouée : DAOFournisseur Line 49 --ADD--");
                        this.c.rollback();
                    } catch (SQLException ex) {
                                Logger.getLogger(FournisseursDAO.class.getName()).log(Level.SEVERE, null, ex);
                            }
	    }
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(Fournisseurs a) {
        try {
		PreparedStatement stmt = this.c.prepareStatement("DELETE FROM Fournisseur WHERE id = ? ") ;
		this.c.setAutoCommit(false) ;
		stmt.setInt(1, a.getId());
		stmt.addBatch();
                stmt.executeBatch() ;
		this.c.commit();
		this.c.setAutoCommit(true);
	} catch (SQLException e) {
		if ( this.c != null ) {
                    try {
                         System.out.println("Operation Echouée : DAOFournisseur Line 73 -- DELETE --");
                        this.c.rollback();
                    } catch (SQLException ex) {
                        Logger.getLogger(FournisseursDAO.class.getName()).log(Level.SEVERE, null, ex);
                    }
		}
                return false;
	}
        return true;
    }

    @Override
    public boolean update(Fournisseurs a) {
        try {
		PreparedStatement stmt = this.c.prepareStatement("UPDATE Fournisseur SET nom = ? ,adresse = ? ,tel = ? ,email = ? ,siteweb = ?, type = ? WHERE id = ?" );
		this.c.setAutoCommit(false) ;                
		
		stmt.setString( 1 , a.getName()) ;
		stmt.setString(2, a.getAddress());
		stmt.setString(3 ,a.getTel());
                stmt.setString(4 , a.getEmail());
                stmt.setString(5 , a.getWebsite());
                stmt.setInt(6, typeFournisseur.indexOf(a.getType()) + 1); //On ajoute 1 parceque les index dans les List commencent a 0 or les id commence a 1
		stmt.setInt(7, a.getId());
                
                stmt.execute();
		this.c.commit();
		this.c.setAutoCommit(true);
        } catch (SQLException e) {
            if ( this.c != null ) {
                try {
                        System.out.println("Operation Echouée : DAOFournisseur Line 109 --UPDATE--");
                        Logger.getLogger(FournisseursDAO.class.getName()).log(Level.SEVERE, null, e);
                        this.c.rollback();
                    } catch (SQLException ex) {
                                Logger.getLogger(FournisseursDAO.class.getName()).log(Level.SEVERE, null, ex);
                            }
	    }
            return false;
        }
        return true;
    }

    @Override
    public List<Fournisseurs> list() {
        ResultSet rlt ;
	List<Fournisseurs> value = new ArrayList<>() ;
	try {
		Statement stmt = this.c.createStatement() ;
		rlt = stmt.executeQuery("SELECT * FROM Fournisseur");
		while ( rlt.next() ) {
			value.add( new Fournisseurs( rlt.getInt("id") , 
                                                        rlt.getString("nom") , 
                                                        rlt.getString("adresse") , 
                                                        rlt.getString("tel") , 
                                                        rlt.getString("email"),
                                                        rlt.getString("siteweb"),
                                                        typeFournisseur.get( rlt.getInt("type") -1))
                                    ) ;
		}
                
	} catch (SQLException e) {
		if ( this.c != null ) {
                        System.out.println("Operation Echouée FournisseurDAO line 108 -- LIST --");
                        Logger.getLogger(FournisseursDAO.class.getName()).log(Level.SEVERE, null, e);
		}
            return null;
	}
	return value;
    }

}
