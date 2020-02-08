package model.classes;

public class TypeOeuf {
	
	private int idTypeOeuf ;
	private String nomTf ;
	private double prix_alveole ;
	public TypeOeuf(int idTypeOeuf, String nomTf, double prix_alveole) {
		super();
		this.idTypeOeuf = idTypeOeuf;
		this.nomTf = nomTf;
		this.prix_alveole = prix_alveole;
	}
	public int getIdTypeOeuf() {
		return idTypeOeuf;
	}
	public void setIdTypeOeuf(int idTypeOeuf) {
		this.idTypeOeuf = idTypeOeuf;
	}
	public String getNomTf() {
		return nomTf;
	}
	public void setNomTf(String nomTf) {
		this.nomTf = nomTf;
	}
	public double getPrix_alveole() {
		return prix_alveole;
	}
	public void setPrix_alveole(double prix_alveole) {
		this.prix_alveole = prix_alveole;
	}
	
	

}
