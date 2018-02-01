package com.aicte.opvs.practice;
import com.aicte.opvs.network.HttpGet;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 
package menupractice;
*/
import com.jfoenix.controls.JFXComboBox;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import org.json.JSONArray;
import org.json.JSONObject;

import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;


/**
 *
 * @author Bhavya Sapra
 */
public class FXMLDocumentController implements Initializable {
    
     
  @FXML
    private JFXComboBox<String> universitycombo;

    
     @FXML
    private AnchorPane anchorpaneSF;
     @FXML
    private Button AdminHome;

@FXML
    private AnchorPane anchorpaneParent;

    @FXML
    private JFXComboBox<String> collegecombo;

    @FXML
    private JFXComboBox<String> statecombo;
    
    @FXML
    private Button submitButton;
    
         @FXML
    private JFXComboBox<String> universitycombo1;

    

    @FXML
    private JFXComboBox<String> collegecombo1;

    @FXML
    private JFXComboBox<String> statecombo1;
    
       
          @FXML
    private MenuItem universitymenu;
             @FXML
    private MenuItem collegemenu;
                @FXML
    private MenuItem employeemenu;
       
                
       
    @FXML
    private AnchorPane universitypane;
         @FXML
    void handleuniversitymenu(ActionEvent event) throws IOException {
anchorpaneSF.setVisible(false);
 anchorpaneParent.getChildren().add(FXMLLoader.load(getClass().getResource("prac.fxml")));
    	
//anchorpaneParent.getChildren().add(AdminHome);
    }

    @FXML
    void handlecollegemenu(ActionEvent event) throws IOException {
anchorpaneSF.setVisible(false);
 anchorpaneParent.getChildren().add(FXMLLoader.load(getClass().getResource("statefxml.fxml")));
    }

    @FXML
    void handleemployeemenu(ActionEvent event) {

    }

    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        
    }
   // ObservableList<String> list=FXCollections.observableArrayList("smart","india","hackathon");

   
    
   @FXML
  private TableView<Integer> tableview1;

      @FXML
  private TableView<Integer> tableview11;
      
    @FXML
    void handlestatecombo(ActionEvent event) {
    	
//    	String state = "Andhra Pradesh";
//    	StringBuffer queryBuffer = new StringBuffer("?");
//    	queryBuffer.append("state=" + state);
    	String state = statecombo.getValue();
//    	String query = new String("&state=" + state);
    	
    	StringBuffer queryBuffer = new StringBuffer("1");
    	queryBuffer.append("&state=");
    	queryBuffer.append(state);

        System.out.println(queryBuffer.toString());
        // send selected state to server
        HttpGet.sendRequest("/FetchCollege", queryBuffer.toString());
    		
//    	System.out.println("query: " + query);
//    	HttpGet.sendRequest("/FetchCollege", query);
    	
    	JSONObject replyJSON = HttpGet.receiveResponse();
    	
    	System.out.println(replyJSON.toString());

    	List<String> list = new ArrayList<String>();
    	
    	if (replyJSON.getInt("errorCode") == 0) {
    		int i = 0;
    		
    		JSONArray jsonArray = replyJSON.getJSONArray("data");
    		while (!jsonArray.isNull(i)) {
    			list.add(jsonArray.getJSONObject(i).getString("university_name"));
    			i++;
    		}
    		
    	}
        ObservableList<String> obList = FXCollections.observableList(list);
         universitycombo.getItems().clear();
        universitycombo.setItems(obList);
        
         universitycombo1.getItems().clear();
        universitycombo1.setItems(obList);
System.out.println("Value pressed in combo "+statecombo.getValue());
    }

    @FXML
    void handleuniversitycombo(ActionEvent event) {
    	
    	String state = statecombo.getValue();
//    	String query = new String("&state=" + state);
    	
    	StringBuffer queryBuffer = new StringBuffer("1");
    	queryBuffer.append("&state=");
    	queryBuffer.append(state);
    	
    	String university = universitycombo.getValue();
    	queryBuffer.append("&university=");
    	queryBuffer.append(university);

        System.out.println(queryBuffer.toString());
        // send selected state to server
        HttpGet.sendRequest("/FetchCollege", queryBuffer.toString());
    		
//    	System.out.println("query: " + query);
//    	HttpGet.sendRequest("/FetchCollege", query);
    	
    	JSONObject replyJSON = new JSONObject();
    	replyJSON = HttpGet.receiveResponse();
    	
    	System.out.println(replyJSON.toString());

    	List<String> list = new ArrayList<String>();
    	
    	if (replyJSON.getInt("errorCode") == 0) {
    		int i = 0;
    		
    		JSONArray jsonArray = replyJSON.getJSONArray("data");
    		while (!jsonArray.isNull(i)) {
    			list.add(jsonArray.getJSONObject(i).getString("college_name"));
    			i++;
    		}
    		
    	}
        ObservableList<String> obList = FXCollections.observableList(list);
          collegecombo.getItems().clear();
        collegecombo.setItems(obList);
        
        collegecombo1.getItems().clear();
        collegecombo1.setItems(obList);
        
System.out.println("Value pressed in combo "+universitycombo.getValue());
    }

    @FXML
    void handlecollegecombo(ActionEvent event) {
        
System.out.println("Value pressed in combo "+collegecombo.getValue());
    }

   
    
    @FXML
    void submitButtonAction(ActionEvent event) {
        
//		Alert alert = new Alert(AlertType.INFORMATION, "Entries not found!", ButtonType.OK);
//		
//		alert.showAndWait();
		
		String state = statecombo.getValue();
//    	String query = new String("&state=" + state);
    	
    	StringBuffer queryBuffer = new StringBuffer("1");
    	queryBuffer.append("&state=");
    	queryBuffer.append(state);
    	
    	String university = universitycombo.getValue();
    	queryBuffer.append("&university=");
    	queryBuffer.append(university);
    	
    	String college = collegecombo.getValue();
    	queryBuffer.append("&college=");
    	queryBuffer.append(college);
    	
    	queryBuffer.append("&role=");
    	queryBuffer.append("STUDENT");
    	
    	queryBuffer.append("&offset=");
    	queryBuffer.append("0");
    	
    	queryBuffer.append("&rowCount=");
    	queryBuffer.append("25");
    	
    	queryBuffer.append("&course=");
    	queryBuffer.append("");

    	queryBuffer.append("&year=");
    	queryBuffer.append("");
    	
    	System.out.println(queryBuffer.toString());
        // send selected state to server
        HttpGet.sendRequest("/FetchBulkDetails", queryBuffer.toString());
    		
//    	System.out.println("query: " + query);
//    	HttpGet.sendRequest("/FetchCollege", query);
    	
    	JSONObject replyJSON = HttpGet.receiveResponse();
    	
    	System.out.println(replyJSON.toString());

//    	List<String> list = new ArrayList<String>();
//    	
//    	if (replyJSON.getInt("errorCode") == 0) {
//    		int i = 0;
//    		
//    		JSONArray jsonArray = replyJSON.getJSONArray("data");
//    		while (!jsonArray.isNull(i)) {
//    			list.add(jsonArray.getJSONObject(i).getString("college_name"));
//    			i++;
//    		}
//    		
//    	}
		
		Alert alert = new Alert(AlertType.INFORMATION, replyJSON.toString() , ButtonType.OK);
		
		alert.showAndWait();
    	
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
//        universitycombo.setItems(list);
//        coursecombo.setItems(list);
//
//        collegecombo.setItems(list);
//
//        statecombo.setItems(list);
//        yearcombo.setItems(list);

//universitypane.setVisible(false);

List<String> list = new ArrayList<String>();
        list.add("Andhra Pradesh");
        list.add("Assam");
        list.add("Delhi");
        list.add("Punjab");
        list.add("Tamil Nadu");
        ObservableList <String> obList = FXCollections.observableList(list);
//        universitycombo.getItems().clear();
//        universitycombo.setItems(obList);

//         coursecombo.getItems().clear();
//        coursecombo.setItems(obList);
        
         statecombo.getItems().clear();
         statecombo.setItems(obList);
        
//         collegecombo.getItems().clear();
//        collegecombo.setItems(obList);
//        
//        yearcombo.getItems().clear();
//        yearcombo.setItems(obList);
        
        //FACULTY'S COMBOBOXES
//        universitycombo1.getItems().clear();
//        universitycombo1.setItems(obList);
//
//         coursecombo1.getItems().clear();
//        coursecombo1.setItems(obList);
        
         statecombo1.getItems().clear();
         statecombo1.setItems(obList);
//        
//         collegecombo1.getItems().clear();
//        collegecombo1.setItems(obList);
//        
//        yearcombo1.getItems().clear();
//        yearcombo1.setItems(obList);

        //----------------tableview1  -----------------------------------------------------------
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


       //TABLEVIEW11   ---------------------------------------------
        for (int i = 0; i < intValues.size() && i < stringValues.size(); i++) {
            tableview11.getItems().add(i);
        }

        TableColumn<Integer, Number> intColumn1= new TableColumn<>("S.NO.");
        intColumn1.setCellValueFactory(cellData -> {
            Integer rowIndex = cellData.getValue();
            return new ReadOnlyIntegerWrapper(intValues.get(rowIndex));
        });

        TableColumn<Integer, String> nameColumn1 = new TableColumn<>("Name");
        nameColumn1.setCellValueFactory(cellData -> {
            Integer rowIndex = cellData.getValue();
            return new ReadOnlyStringWrapper(stringValues.get(rowIndex));
        });
         TableColumn<Integer, String> addressColumn1 = new TableColumn<>("Address");
        addressColumn1.setCellValueFactory(cellData -> {
            Integer rowIndex = cellData.getValue();
            return new ReadOnlyStringWrapper(stringValues.get(rowIndex));
        });
        
          TableColumn<Integer, String> contactColumn1 = new TableColumn<>("ContactDetails");
        contactColumn1.setCellValueFactory(cellData -> {
            Integer rowIndex = cellData.getValue();
            return new ReadOnlyStringWrapper(stringValues.get(rowIndex));
        });
        
        tableview11.getColumns().add(intColumn);
        tableview11.getColumns().add(nameColumn);
        tableview11.getColumns().add(addressColumn);
        tableview11.getColumns().add(contactColumn);
    }    
    
}
