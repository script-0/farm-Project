/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

/**
 *
 * @author Isaac
 */
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Tools;

public class PdfViewerController  implements Initializable{

    @FXML
    private Label titleLabel;

    @FXML
    private ImageView image;

    @FXML
    private JFXButton printButton;

    @FXML
    private JFXButton saveButton;

    @FXML
    private JFXButton closeButton;

    @FXML
    void closeClicked(ActionEvent event) {
        Tools.getStage(event).close();
    }

    @FXML
    void printClicked(ActionEvent event) {

    }

    @FXML
    void saveClicked(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
    public void loadImage(String value){
        image.setImage(new Image(value));
    }

    public void loadImage(Image img){
        image.setImage(img);
    }
}

