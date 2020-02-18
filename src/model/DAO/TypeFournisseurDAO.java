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
import model.classes.TypeFournisseur;

/**
 *
 * @author Isaac
 */
public class TypeFournisseurDAO extends DAO<TypeFournisseur>{

    public TypeFournisseurDAO(Connection con) {
        super(con);
    }

    @Override
    public boolean create(TypeFournisseur a) {
        try {
		PreparedStatement stmt = this.c.prepareStatement("INSERT INTO TypeFournisseur VALUES ( ? , ?)" ) ;
		this.c.setAutoCommit(false) ;
		stmt.setInt(1, a.getId());
		stmt.setString( 2 , a.getName()) ;
		stmt.addBatch();
		stmt.executeBatch() ;
		this.c.commit();
		this.c.setAutoCommit(true);
	} catch (SQLException e) {
		if ( this.c != null ) {
                    try {
                        System.out.println("Operatio Echouée");
                        this.c.rollback();
                    } catch (SQLException ex) {
                        Logger.getLogger(TypeFournisseurDAO.class.getName()).log(Level.SEVERE, null, ex);
                    }
		}
                return false;
        }
        return true;
}

    @Override
    public boolean delete(TypeFournisseur a) {
       try {
            PreparedStatement stmt = this.c.prepareStatement("DELETE FROM TypeFourniseur WHERE idAli = ?") ;
            this.c.setAutoCommit(false) ;
            stmt.setInt(1, a.getId());
            stmt.addBatch();
            this.c.commit();
            this.c.setAutoCommit(true);
	} catch (SQLException e) {
            if ( this.c != null ) {
                try {
                    System.out.println("Operation Echouée");
                    this.c.rollback();
                } catch (SQLException ex) {
                    Logger.getLogger(TypeFournisseurDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return false;
	}
       return true;
    }

    @Override
    public boolean update(TypeFournisseur a) {
        //Update et retourne true si aucune est survenue. Dans le cas contraire retourner false
        return true;
    }

    @Override
    public List<TypeFournisseur> list() {
        ResultSet rlt;
		List<TypeFournisseur> value = new ArrayList<>() ;
		try {
			Statement stmt = this.c.createStatement() ;
			this.c.setAutoCommit(false) ;
			
			rlt = stmt.executeQuery("SELECT * FROM TypeFournisseur");
			while ( rlt.next() ) {
				value.add( new TypeFournisseur( rlt.getInt("id") , rlt.getString("nom"))) ;
			}
                        
			this.c.commit();
			this.c.setAutoCommit(true);
                        
		} catch (SQLException e) {
			if ( this.c != null ) {
                            try {
                                System.out.println("Operation Echouée");
                                this.c.rollback();
                            } catch (SQLException ex) {
                                Logger.getLogger(TypeFournisseurDAO.class.getName()).log(Level.SEVERE, null, ex);
                            }
			}
                        return null;
		}
		return value ;
    }

    public String getName(int id)
    {
        ResultSet rlt;
	String value = null ;
	try {
		Statement stmt = this.c.createStatement() ;
		rlt = stmt.executeQuery("SELECT nom FROM TypeFournisseur WHERE id = "+id+";");
		while ( rlt.next() ) {
                    value = rlt.getString("nom");
		}
                
	} catch (SQLException e) {
		if ( this.c != null ) {
                    try {
                            System.out.println("Operation Echouée");
                                this.c.rollback();
                       } catch (SQLException ex) {
                                Logger.getLogger(TypeFournisseurDAO.class.getName()).log(Level.SEVERE, null, ex);
                        }
		}
                return null;
	}
	return value ;
    }
}
