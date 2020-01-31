/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Isaac
 */
public class UserCredentials {
    private String name;
    private String username;
    private String login;
    private String typeEm;
    private String tel;
    private String adress;
    private String email;
    private String image;

    public UserCredentials(String name, String username, String login, String typeEm, String tel, String adress, String email, String image) {
        this.name = name;
        this.username = username;
        this.login = login;
        this.typeEm = typeEm;
        this.tel = tel;
        this.adress = adress;
        this.email = email;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
    
}
