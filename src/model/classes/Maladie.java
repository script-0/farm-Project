package model.classes;

public class Maladie {
	
	private int idM ;
	private String nomM ;
	private String descriptionTraitement ;
	private String descriptionMaladie ;
	private String duree ;
	public Maladie(int idM, String nomM, String descriptionTraitement, String descriptionMaladie, String duree) {
		super();
		this.idM = idM;
		this.nomM = nomM;
		this.descriptionTraitement = descriptionTraitement;
		this.descriptionMaladie = descriptionMaladie;
		this.duree = duree;
	}
	public int getIdM() {
		return idM;
	}
	public void setIdM(int idM) {
		this.idM = idM;
	}
	public String getNomM() {
		return nomM;
	}
	public void setNomM(String nomM) {
		this.nomM = nomM;
	}
	public String getDescriptionTraitement() {
		return descriptionTraitement;
	}
	public void setDescriptionTraitement(String descriptionTraitement) {
		this.descriptionTraitement = descriptionTraitement;
	}
	public String getDescriptionMaladie() {
		return descriptionMaladie;
	}
	public void setDescriptionMaladie(String descriptionMaladie) {
		this.descriptionMaladie = descriptionMaladie;
	}
	public String getDuree() {
		return duree;
	}
	public void setDuree(String duree) {
		this.duree = duree;
	}
	
	
	
	

}
