/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import static java.lang.Thread.sleep;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Label;

/**
 *
 * @author Isaac
 */
public class setupServer implements Callable<databaseUtils>{
    databaseUtils utils;
    Label report;
    
    public setupServer(databaseUtils utils,Label report)
    {
        this.utils = utils;
        this.report =report;
    }
    
    @Override
    public databaseUtils call(){
        utils = new databaseUtils();
        if(utils.getStateConnection())
        {
            report.setId("report");
            report.setText("Server is up: Go to do");
        }
        else{
            report.setText("Server is Down: Retrying ...");
            report.setId("reportFailed");
            
            while(!utils.reloadConnection()){
                report.setText("Server is Down: Retrying ...");
            }
            report.setText("Server is up: Go to do");
            report.setId("report");
        } 
        return this.utils;
    }
}
