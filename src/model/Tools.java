/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 *
 * @author Isaac
 */
public class Tools {
    
    
    public static Stage getStage(ActionEvent e){
        return (Stage) ((Node)e.getSource()).getScene().getWindow();
    }
    
    /**Raccourci de System.out.println*/
    public static void print(Object value)
    {
        System.out.println(value);
    }
    
    public static void showNotification(String title,String text,Boolean isError,Pos position,float temp)
    {
        Image img;
            if(isError)
            {
                img = new Image("/images/errorNotification.png");
            }else
            {
                img = new Image("/images/confirmNotification.png");
            }
            Notifications notificationBuilder = Notifications.create()
                    .title(title)
                    .text(text)
                    .graphic(new ImageView(img))
                    .hideAfter(Duration.seconds(temp))
                    .position(position)
                    .onAction((ActionEvent e) -> {
                        System.out.println("Notifications Succeeded");
            });
            
           notificationBuilder.show();   
    }
    public static void showNotification(String title,String text,Boolean isError)
    {
             showNotification(title,text,isError,Pos.TOP_RIGHT,5);
    }    
    public static void showNotification(String title,String text,Boolean isError,Pos position)
    {
             showNotification(title,text,isError,position,5);
    }
}
