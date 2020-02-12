
DROP DATABASE IF EXISTS scriptFarm;

CREATE DATABASE scriptFarm CHARACTER SET'utf8';

USE scriptFarm;

CREATE TABLE Race(
	idRace INT UNSIGNED AUTO_INCREMENT,
	nom VARCHAR(15) NOT NULL,
	description TEXT NOT NULL,
	prix_race DECIMAL(7,2),
	PRIMARY KEY (idRace)
)
ENGINE=INNODB;
CREATE TABLE Bande(
	idBande INT UNSIGNED AUTO_INCREMENT,
	qte INT UNSIGNED NOT NULL,
	age INT UNSIGNED NOT NULL,
	race_id INT UNSIGNED,
	prix_achat DECIMAL(10,2),# prix d'achat du poussin
	prix_vente DECIMAL(10,2), # prix de vente à age mature
	dateDemarrage DATETIME,
	fourn_id INT UNSIGNED,
	bat_id INT UNSIGNED,
	PRIMARY KEY(idBande)
)
ENGINE=INNODB;

CREATE TABLE Fournisseur(
	idFourn INT UNSIGNED AUTO_INCREMENT,
	nomFourn VARCHAR(20) NOT NULL,
	adresse VARCHAR(20) NOT NULL,
	tel INT UNSIGNED NOT NULL,
	email VARCHAR(20),
	siteweb VARCHAR(20),
	typeFourn INT(1),
	PRIMARY KEY(idFourn),
	UNIQUE ind_uni_email (email)
)
ENGINE=INNODB;


CREATE TABLE Aliment(
	idAli INT UNSIGNED AUTO_INCREMENT,
	nomAli VARCHAR(15) NOT NULL,
	description TEXT NOT NULL,
	prix DECIMAL(8,2) NOT NULL,
	PRIMARY KEY(idAli),
	CHECK(prix >0)
)
ENGINE=INNODB;


CREATE TABLE Ration(
	idRation INT UNSIGNED AUTO_INCREMENT,
	ali_id INT UNSIGNED,
	bande_id INT UNSIGNED,
	dateRation DATETIME NOT NULL,
	qte DECIMAL(5,2) NOT NULL,
	eau DECIMAL(5,2) NOT NULL,
	PRIMARY KEY(idRation),
	UNIQUE ind_uni_ali_bande_id (ali_id,bande_id)
)
ENGINE=INNODB;


CREATE TABLE StockAliment(
	idStock INT UNSIGNED AUTO_INCREMENT,
	qte INT UNSIGNED NOT NULL,
	dateArrivage DATETIME,
	ali_id INT UNSIGNED,
	employe_id INT UNSIGNED,
	fourn_id INT UNSIGNED,
	PRIMARY KEY(idStock)
)
ENGINE=INNODB;


CREATE TABLE Vendu(
	idVente INT UNSIGNED,
	client_id INT UNSIGNED,
	bande_id INT UNSIGNED,
	dateVente TIMESTAMP DEFAULT now(),
	total_prix DECIMAL(8,2) NOT NULL,
	qte INT UNSIGNED,
	employe_id INT UNSIGNED,
	PRIMARY KEY(idVente),
	CHECK(total_prix>0),
	INDEX ind_datevente(dateVente),
	UNIQUE ind_uni_client_bande_id (client_id,bande_id)
)
ENGINE=INNODB;


CREATE TABLE Client(
	idClient INT UNSIGNED AUTO_INCREMENT,
	nom VARCHAR(15),
	adresse VARCHAR(15),
	tel INT UNSIGNED,
	PRIMARY KEY(idClient)
)
ENGINE=INNODB;


CREATE TABLE Batiment(
	idBat INT UNSIGNED AUTO_INCREMENT,
	surface DECIMAL(5,2) NOT NULL,
	nomBat VARCHAR(10) NOT NULL,
	PRIMARY KEY(idBat)
)
ENGINE=INNODB;


CREATE TABLE CollecteOeuf(
	idCollect INT UNSIGNED AUTO_INCREMENT,
	qte INT UNSIGNED NOT NULL,
	dateCollect DATETIME,
	incubation INT(1),
	bande_id INT UNSIGNED,
	prix_alveole DECIMAL(9,2),
	qteCasse INT UNSIGNED,
	typeOeuf_id INT UNSIGNED,
	PRIMARY KEY(idCollect),
	CHECK(incubation=0 OR incubation=1),
	INDEX ind_date_collect(dateCollect)

)
ENGINE=INNODB;

CREATE TABLE TypeOeuf(
	idTypeOeuf INT UNSIGNED AUTO_INCREMENT,
	nomTf VARCHAR(10),
	prix_alveole DECIMAL(9,2),
	PRIMARY KEY(idTypeOeuf)
)
ENGINE=INNODB;

CREATE TABLE VenduOeuf(
	idVenduOeuf INT UNSIGNED AUTO_INCREMENT,
	collect_id INT UNSIGNED,
	client_id INT UNSIGNED,
	dateVente DATETIME,
	total_prix DECIMAL(8,2),
	qte INT UNSIGNED NOT NULL,
	employe_id INT UNSIGNED,
	PRIMARY KEY(idVenduOeuf),
	CHECK(total_prix>0),
	INDEX ind_date_vente_oeuf (dateVente),
	UNIQUE ind_uni_collect_client_id (collect_id,client_id)
)
ENGINE=INNODB;


CREATE TABLE incubation(
	idInc INT UNSIGNED AUTO_INCREMENT,
	dateInc DATETIME,
	ProduirePoussin_id INT UNSIGNED,
	collect_id INT UNSIGNED,
	PRIMARY KEY(idInc),
	INDEX ind_date_inc (dateInc)
)
ENGINE=INNODB;

CREATE TABLE ProduirePoussin(
	idProduirePoussin INT UNSIGNED AUTO_INCREMENT,
	qte INT UNSIGNED NOT NULL,
	taux DECIMAL(4,2),
	incubation_id INT UNSIGNED,
	PRIMARY KEY(idProduirePoussin)
	
)
ENGINE=INNODB;


CREATE TABLE Maladie(
	idM INT UNSIGNED AUTO_INCREMENT,
	nomM VARCHAR(20) NOT NULL,
	descriptionTraitement TEXT ,
	descriptionMaladie TEXT ,
	duree VARCHAR(10),
	PRIMARY KEY(idM)
)
ENGINE=INNODB;

CREATE TABLE BandeMalade(
	idBandeMalade INT UNSIGNED AUTO_INCREMENT,
	maladie_id INT UNSIGNED,
	bande_id INT UNSIGNED,
	qteMalade INT UNSIGNED,
	qtePrise INT UNSIGNED,
	dateM DATETIME,
	totalMort INT UNSIGNED,
	PRIMARY KEY(idBandeMalade),
	UNIQUE ind_uni_bande_maladie_id (bande_id,maladie_id)
)
ENGINE=INNODB;

CREATE TABLE Vaccin(
	idVac INT UNSIGNED AUTO_INCREMENT,
	nomVac VARCHAR(15) NOT NULL,
	periode VARCHAR(15) NOT NULL,
	qteVac INT NOT NULL,
	qtePoule INT NOT NULL,
	description VARCHAR(200),
	prix DECIMAL(8,2),
	PRIMARY KEY(idVac)
)
ENGINE=INNODB;


CREATE TABLE BandeVaccine(
	idBandeVaccine INT UNSIGNED AUTO_INCREMENT,
	bande_id INT UNSIGNED,
	vaccin_id INT UNSIGNED,
	dateVac DATETIME,
	PRIMARY KEY(idBandeVaccine),
	UNIQUE ind_uni_vacc_datevac_id (bande_id,vaccin_id)
)
ENGINE=INNODB;


CREATE TABLE typeEmploye(
    id INT UNSIGNED AUTO_INCREMENT,
    nom VARCHAR(20),
    PRIMARY KEY (id),
    UNIQUE uni_nom (nom)
)
ENGINE=INNODB;


CREATE TABLE Employes (
	idEm INT UNSIGNED AUTO_INCREMENT,
	nom VARCHAR(15),
	username VARCHAR(15) ,
	login VARCHAR(20),
	typeEm INT UNSIGNED,
        tel VARCHAR(15) NOT NULL,
        adress VARCHAR(30) NOT NULL,
        email VARCHAR(30) NOT NULL,
        image VARCHAR(100) NOT NULL DEFAULT "/images/defaultProfile.png",
	PRIMARY KEY(idEm),
        UNIQUE ind_username (username)
)
ENGINE=INNODB;

ALTER TABLE Bande ADD CONSTRAINT fk_race_id FOREIGN KEY (race_id) REFERENCES RACE(idRace) ON DELETE SET NULL;
ALTER TABLE Bande ADD CONSTRAINT fk_bande_race_id FOREIGN KEY (fourn_id) REFERENCES Fournisseur(idFourn) ON DELETE SET NULL;
ALTER TABLE Bande ADD CONSTRAINT fk_bat_id FOREIGN KEY (bat_id) REFERENCES Batiment(idBat) ON DELETE SET NULL;
ALTER TABLE Ration ADD CONSTRAINT fk_ration_ali_id FOREIGN KEY (ali_id) REFERENCES Aliment(idAli) ON DELETE SET NULL;
ALTER TABLE Ration ADD CONSTRAINT fk_ration_bande_id FOREIGN KEY (bande_id) REFERENCES Bande(idBande) ON DELETE SET NULL;
ALTER TABLE StockAliment ADD CONSTRAINT fk_stock_ali_id FOREIGN KEY (ali_id) REFERENCES Aliment(idAli) ON DELETE SET NULL;
ALTER TABLE StockAliment ADD CONSTRAINT fk_stock_fourn_id FOREIGN KEY (fourn_id) REFERENCES Fournisseur(idFourn) ON DELETE SET NULL;
ALTER TABLE Vendu ADD CONSTRAINT fk_vendu_client_id FOREIGN KEY (client_id) REFERENCES Client(idClient) ON DELETE SET NULL;
ALTER TABLE Vendu ADD CONSTRAINT fk_vendu_bande_id FOREIGN KEY (bande_id) REFERENCES Bande(idBande) ON DELETE SET NULL;
ALTER TABLE CollecteOeuf ADD CONSTRAINT fk_collect_bande_id FOREIGN KEY (bande_id) REFERENCES Bande(idBande) ON DELETE SET NULL;
ALTER TABLE VenduOeuf ADD CONSTRAINT fk_venduoeuf_collect_id FOREIGN KEY (collect_id) REFERENCES CollecteOeuf(idCollect) ON DELETE SET NULL;
ALTER TABLE VenduOeuf ADD CONSTRAINT fk_venduoeuf_client_id FOREIGN KEY (client_id) REFERENCES Client(idClient) ON DELETE SET NULL;
ALTER table ProduirePoussin ADD CONSTRAINT fk_produirePoussin_incubation_id FOREIGN KEY(incubation_id) REFERENCES Incubation(idInc);
ALTER TABLE Incubation ADD CONSTRAINT fk_incubation_collect_id FOREIGN KEY (collect_id) REFERENCES CollecteOeuf(idCollect) ON DELETE SET NULL;
ALTER TABLE BandeMalade ADD CONSTRAINT fk_bandemalade_maladie_id FOREIGN KEY (maladie_id) REFERENCES Maladie(idM) ON DELETE SET NULL;
ALTER TABLE BandeMalade ADD CONSTRAINT fk_bandemalade_bande_id FOREIGN KEY (bande_id) REFERENCES Bande(idBande) ON DELETE SET NULL;
ALTER TABLE BandeVaccine ADD CONSTRAINT fk_bandevaccine_bande_id FOREIGN KEY (bande_id) REFERENCES Bande(idBande) ON DELETE SET NULL;
ALTER TABLE BandeVaccine ADD CONSTRAINT fk_bandevaccine_vaccin_id FOREIGN KEY (vaccin_id) REFERENCES Vaccin(idVac) ON DELETE SET NULL;
ALTER TABLE Vendu ADD CONSTRAINT fk_vendu_employe_id FOREIGN KEY (employe_id) REFERENCES Employes(idEm) ON DELETE SET NULL;
ALTER TABLE VenduOeuf ADD CONSTRAINT fk_venduoeuf_employe_id FOREIGN KEY (employe_id) REFERENCES Employes(idEm) ON DELETE SET NULL;
ALTER TABLE StockAliment ADD CONSTRAINT fk_stock_employe_id FOREIGN KEY (employe_id) REFERENCES Employes(idEm) ON DELETE SET NULL;
ALTER TABLE CollecteOeuf ADD CONSTRAINT fk_collecteOeuf_typeOeuf_id FOREIGN KEY (typeOeuf_id) REFERENCES TypeOeuf(idTypeOeuf) ON DELETE SET NULL;
ALTER TABLE Employes ADD CONSTRAINT fk_typeEmploye_id FOREIGN KEY (typeEm) REFERENCES typeEmploye(id) ON DELETE SET NULL;
