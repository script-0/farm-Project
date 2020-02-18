Use scriptFarm;
-- ----Ajout de deux Aliment --------------------

/* INSERT INTO Aliment (nomAli,description,prix) VALUES 
("Mais","Le prix suivant correspond au prix d'un sac en FCFA", 10000) ,
("Cereale","Le prix suivant correspond au prix d'un sac en FCFA", 15000);
*/
-- -------------------------------------------------

-- -----Ajout d'un type D'employe -------
INSERT INTO typeEmploye VALUES (1,"System"),
                               (2,"User");

INSERT INTO TypeFournisseur VALUES (1,"Bande"),
                                   (2,"Oeuf"),
                                   (3,"Vaccin"),
                                   (4,"Aliment");

-- --------------Ajout de l'utilisateur System ------------
INSERT INTO Employes VALUES(null,"System","root","root",1,"694255463","EFoulan-Club France","bekolleisaac@gmail.com","/images/root.png");

-- ---------------Ajout Supplier--------------------- --