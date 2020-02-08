package model.classes;

public class Aliment {
	
	private int idAli ;
	private String nomAli ;
	private String description ;
	private double prix ;
	public Aliment(int idAli, String nomAli, String description, double prix) {
		super();
		this.idAli = idAli;
		this.nomAli = nomAli;
		this.description = description;
		this.prix = prix;
	}
	public int getIdAli() {
		return idAli;
	}
	public void setIdAli(int idAli) {
		this.idAli = idAli;
	}
	public String getNomAli() {
		return nomAli;
	}
	public void setNomAli(String nomAli) {
		this.nomAli = nomAli;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrix() {
		return prix;
	}
	public void setPrix(double prix) {
		this.prix = prix;
	}
	
	

}
