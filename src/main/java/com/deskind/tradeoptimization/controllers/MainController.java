/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deskind.tradeoptimization.controllers;

import java.awt.BorderLayout;
import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Popup;



public class MainController implements Initializable {

    @FXML
    private Button chooseFilesBtn;

    @FXML
    static List<File> handleChooseFilesBtn(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        List<File> list = fileChooser.showOpenMultipleDialog(new Popup());
        System.out.println("The list size is " + list.size());
        return list;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
