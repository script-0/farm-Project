package model.classes;

public class Batiment {
	
	private int idBat ;
	private double surface ;
	private String 	nomBat ;
	public Batiment(int idBat, double surface, String nomBat) {
		super();
		this.idBat = idBat;
		this.surface = surface;
		this.nomBat = nomBat;
	}
	public int getIdBat() {
		return idBat;
	}
	public void setIdBat(int idBat) {
		this.idBat = idBat;
	}
	public double getSurface() {
		return surface;
	}
	public void setSurface(double surface) {
		this.surface = surface;
	}
	public String getNomBat() {
		return nomBat;
	}
	public void setNomBat(String nomBat) {
		this.nomBat = nomBat;
	}
	
	

}
