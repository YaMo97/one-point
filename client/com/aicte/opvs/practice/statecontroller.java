package com.aicte.opvs.practice;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 
package menupractice;
*/
import com.jfoenix.controls.JFXComboBox;
import java.awt.Button;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author Bhavya Sapra
 */
public class statecontroller implements Initializable {
   
    @FXML
    private JFXComboBox<String> districtcombo;

    @FXML
    private JFXComboBox<String> universitycombo;

//    @FXML
//    private Button importbtn;

   

    @FXML
    private JFXComboBox<String> statecombo;

 

    @FXML
    private TableView<Integer> tableview1;

    @FXML
    void handlestatecombo(ActionEvent event) {
  List<String> list = new ArrayList<String>();
        list.add("Item A");
        list.add("Item B");
        list.add("Item C");
        list.add("Item D");
        list.add("Item E");
        list.add("Item F");
        ObservableList obList = FXCollections.observableList(list);
         districtcombo.getItems().clear();
        districtcombo.setItems(obList);
    }

    @FXML
    void handledistrictcombo(ActionEvent event) {
        List<String> list = new ArrayList<String>();
        list.add("Item A");
        list.add("Item B");
        list.add("Item C");
        list.add("Item D");
        list.add("Item E");
        list.add("Item F");
        ObservableList obList = FXCollections.observableList(list);
         universitycombo.getItems().clear();
       universitycombo.setItems(obList);

    }

//    @FXML
//   void handlecombo(ActionEvent event) {
//     System.out.println("Value pressed in combo ");
//
//}

//    @FXML
//    void handleButtonAction(ActionEvent event) {
//// if(event.getSource()==loginbutton){
////        //get reference to the button's stage         
////        stage=(Stage) loginbutton.getScene().getWindow();
////        //load up OTHER FXML document
////  root = FXMLLoader.load(getClass().getResource("first.fxml"));
////      }
//// Scene scene = new Scene(root);
////      stage.setScene(scene);
////      stage.show();
//
//        System.out.println("hello");
//
//
//
//
//    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        List<String> list = new ArrayList<String>();
        list.add("Item A");
        list.add("Item B");
        list.add("Item C");
        list.add("Item D");
        list.add("Item E");
        list.add("Item F");
        ObservableList obList = FXCollections.observableList(list);
        
         statecombo.getItems().clear();
        statecombo.setItems(obList);
        
         List<Integer> intValues = Arrays.asList(1, 2, 3, 4, 5, 1, 2, 3, 4, 5,1, 2, 3, 4, 5);
        List<String> stringValues = Arrays.asList("One", "Two", "Three", "Four", "Five","One", "Two", "Three", "Four", "Five","One", "Two", "Three", "Four", "Five");

       
        for (int i = 0; i < intValues.size() && i < stringValues.size(); i++) {
            tableview1.getItems().add(i);
        }

        TableColumn<Integer, Number> intColumn = new TableColumn<>("S.NO.");
        intColumn.setCellValueFactory(cellData -> {
            Integer rowIndex = cellData.getValue();
            return new ReadOnlyIntegerWrapper(intValues.get(rowIndex));
        });

        TableColumn<Integer, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(cellData -> {
            Integer rowIndex = cellData.getValue();
            return new ReadOnlyStringWrapper(stringValues.get(rowIndex));
        });
         TableColumn<Integer, String> addressColumn = new TableColumn<>("Address");
        addressColumn.setCellValueFactory(cellData -> {
            Integer rowIndex = cellData.getValue();
            return new ReadOnlyStringWrapper(stringValues.get(rowIndex));
        });
        
          TableColumn<Integer, String> contactColumn = new TableColumn<>("ContactDetails");
        contactColumn.setCellValueFactory(cellData -> {
            Integer rowIndex = cellData.getValue();
            return new ReadOnlyStringWrapper(stringValues.get(rowIndex));
        });
        
        tableview1.getColumns().add(intColumn);
        tableview1.getColumns().add(nameColumn);
        tableview1.getColumns().add(addressColumn);
        tableview1.getColumns().add(contactColumn);
        
        
        
        
    } 
    
   
  
}