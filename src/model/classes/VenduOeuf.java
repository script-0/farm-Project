package model.classes;

import java.util.Date;

public class VenduOeuf {
	
	private int idVenduOeuf ;
	private int collecte_id ;
	private int client_id ;
	private Date dateVente ;
	private double total_prix ;
	private int qte ;
	private int employe_id ;
	public VenduOeuf(int idVenduOeuf, int collecte_id, int client_id, Date dateVente, double total_prix, int qte, int employe_id) {
		super();
		this.idVenduOeuf = idVenduOeuf;
		this.collecte_id = collecte_id;
		this.client_id = client_id;
		this.dateVente = dateVente;
		this.total_prix = total_prix;
		this.qte = qte;
		this.employe_id = employe_id;
	}
	public int getIdVenduOeuf() {
		return idVenduOeuf;
	}
	public void setIdVenduOeuf(int idVenduOeuf) {
		this.idVenduOeuf = idVenduOeuf;
	}
	public int getCollecte_id() {
		return collecte_id;
	}
	public void setCollecte_id(int collecte_id) {
		this.collecte_id = collecte_id;
	}
	public int getClient_id() {
		return client_id;
	}
	public void setClient_id(int client_id) {
		this.client_id = client_id;
	}
	public Date getDateVente() {
		return dateVente;
	}
	public void setDateVente(Date dateVente) {
		this.dateVente = dateVente;
	}
	public double getTotal_prix() {
		return total_prix;
	}
	public void setTotal_prix(double total_prix) {
		this.total_prix = total_prix;
	}
	public int getQte() {
		return qte;
	}
	public void setQte(int qte) {
		this.qte = qte;
	}
	public int getEmploye_id() {
		return employe_id;
	}
	public void setEmploye_id(int employe_id) {
		this.employe_id = employe_id;
	}
	
	
	
	

}
