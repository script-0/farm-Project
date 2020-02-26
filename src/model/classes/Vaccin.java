package model.classes;

public class Vaccin {

	 	private int idVac ;
	 	private String nomVac ;
	 	private String periode ;
	 	private int qteVac ;
	 	private int qtePoule ;
	 	private String description ;
	 	private double prix ;
		public Vaccin(int idVac, String nomVac, String periode, int qteVac, int qtePoule, String description,
				double prix) {
			super();
			this.idVac = idVac;
			this.nomVac = nomVac;
			this.periode = periode;
			this.qteVac = qteVac;
			this.qtePoule = qtePoule;
			this.description = description;
			this.prix = prix;
		}
		public int getIdVac() {
			return idVac;
		}
		public void setIdVac(int idVac) {
			this.idVac = idVac;
		}
		public String getNomVac() {
			return nomVac;
		}
		public void setNomVac(String nomVac) {
			this.nomVac = nomVac;
		}
		public String getPeriode() {
			return periode;
		}
		public void setPeriode(String periode) {
			this.periode = periode;
		}
		public int getQteVac() {
			return qteVac;
		}
		public void setQteVac(int qteVac) {
			this.qteVac = qteVac;
		}
		public int getQtePoule() {
			return qtePoule;
		}
		public void setQtePoule(int qtePoule) {
			this.qtePoule = qtePoule;
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
