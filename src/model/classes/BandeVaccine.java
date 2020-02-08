package model.classes;

import java.util.Date;

public class BandeVaccine {
	
	private int idBandeVaccine ;
	private int bande_id ;
	private int vaccin_id ;
	private Date dateVac ;
	public BandeVaccine(int idBandeVaccine, int bande_id, int vaccin_id, Date dateVac) {
		super();
		this.idBandeVaccine = idBandeVaccine;
		this.bande_id = bande_id;
		this.vaccin_id = vaccin_id;
		this.dateVac = dateVac;
	}
	public int getIdBandeVaccine() {
		return idBandeVaccine;
	}
	public void setIdBandeVaccine(int idBandeVaccine) {
		this.idBandeVaccine = idBandeVaccine;
	}
	public int getBande_id() {
		return bande_id;
	}
	public void setBande_id(int bande_id) {
		this.bande_id = bande_id;
	}
	public int getVaccin_id() {
		return vaccin_id;
	}
	public void setVaccin_id(int vaccin_id) {
		this.vaccin_id = vaccin_id;
	}
	public Date getDateVac() {
		return dateVac;
	}
	public void setDateVac(Date dateVac) {
		this.dateVac = dateVac;
	}
	
	

}
