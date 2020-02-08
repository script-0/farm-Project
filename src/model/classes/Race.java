package model.classes;

public class Race {
	
	private int idRace ;
	private String nom ;
	private String description ;
	private double prix_race ;
	public Race(int idRace, String nom, String description, double prix_race) {
		super();
		this.idRace = idRace;
		this.nom = nom;
		this.description = description;
		this.prix_race = prix_race;
	}
	public int getIdRace() {
		return idRace;
	}
	public void setIdRace(int idRace) {
		this.idRace = idRace;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrix_race() {
		return prix_race;
	}
	public void setPrix_race(double prix_race) {
		this.prix_race = prix_race;
	}
	
	

}
