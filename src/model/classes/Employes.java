package model.classes;

public class Employes {
	
	private int idEm ;
	private String nom ;
	private String user ;
	private String login ;
	private String typeEm ;
	public Employes(int idEm, String nom, String user, String login, String typeEm) {
		super();
		this.idEm = idEm;
		this.nom = nom;
		this.user = user;
		this.login = login;
		this.typeEm = typeEm;
	}
	public int getIdEm() {
		return idEm;
	}
	public void setIdEm(int idEm) {
		this.idEm = idEm;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getTypeEm() {
		return typeEm;
	}
	public void setTypeEm(String typeEm) {
		this.typeEm = typeEm;
	}
	
	
	

}
