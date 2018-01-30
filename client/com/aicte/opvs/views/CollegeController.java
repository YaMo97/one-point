package com.aicte.opvs.views;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.json.JSONArray;
import org.json.JSONObject;

import com.aicte.opvs.network.HttpGet;
import com.jfoenix.controls.JFXButton;

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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class CollegeController implements Initializable{

    @FXML
    private TableView<Integer> collegeTable;

    @FXML
    private JFXButton submitButton;

    @FXML
    private ComboBox<String> universityComboBox;

    @FXML
    private ComboBox<String> stateComboBox;

    @FXML
    private ImageView homeImage;

    @FXML
    private JFXButton homeButton;

    @FXML
    void stateComboBoxAction(ActionEvent event) {

    	String state = stateComboBox.getValue();
    	
    	universityComboBox.getItems().clear();
    	
    	System.out.println("Value pressed in combo " + stateComboBox.getValue());

    	StringBuffer queryBuffer = new StringBuffer("1");
    	queryBuffer.append("&state=");
    	queryBuffer.append(state);

    	System.out.println(queryBuffer.toString());
    	
        // send selected state to server
        HttpGet.sendRequest("/FetchCollege", queryBuffer.toString());
        
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
        universityComboBox.getItems().clear();
        universityComboBox.setItems(obList);
    }

    @FXML
    void universityComboBoxAction(ActionEvent event) {
    	
    }

    @FXML
    void submitButtonAction(ActionEvent event) {
    	
    	String state = stateComboBox.getValue();
    	
    	StringBuffer queryBuffer = new StringBuffer("1");
    	queryBuffer.append("&state=");
    	queryBuffer.append(state);
    	
    	System.out.println("Value pressed in combo " + stateComboBox.getValue());
    	
    	String university = universityComboBox.getValue();
    	
    	System.out.println("Value pressed in combo " + universityComboBox.getValue());
    	
    	queryBuffer.append("&university=");
    	queryBuffer.append(university);

        System.out.println(queryBuffer.toString());
        
        // send selected state to server
        HttpGet.sendRequest("/FetchCollege", queryBuffer.toString());
        
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
//        ObservableList<String> obList = FXCollections.observableList(list);
        
    	
        for (int i = 0; i < list.size(); i++) {
            collegeTable.getItems().add(i);
        }
        
        TableColumn<Integer, Number> intColumn = new TableColumn<>("S.No.");
        intColumn.setCellValueFactory(cellData -> {
            Integer rowIndex = cellData.getValue();
            return new ReadOnlyIntegerWrapper(rowIndex);
        });
        
        TableColumn<Integer, String> nameColumn = new TableColumn<>("College Name");
        nameColumn.setCellValueFactory(cellData -> {
            Integer rowIndex = cellData.getValue();
            return new ReadOnlyStringWrapper(list.get(rowIndex));
        });
        
        collegeTable.getColumns().add(intColumn);
        collegeTable.getColumns().add(nameColumn);    	
    }

    @FXML
    void homeButtonAction(ActionEvent event) {
    	//this.dispose;
    	// Return to Dashboard
    	Stage stage = (Stage) homeButton.getScene().getWindow();
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/com/aicte/opvs/fxml/Dashboard.fxml"));
			
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (Exception e){}
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	
    	stateComboBox.getItems().clear();
    	universityComboBox.getItems().clear();

    	List<String> list = new ArrayList<String>();
        
        list.add("Andhra Pradesh");
        list.add("Arunachal Pradesh");
        list.add("Assam");
        list.add("Bihar");
        list.add("Chhattisgarh");
        list.add("Goa (GA)");
        list.add("Gujarat (GJ)");
        list.add("Haryana");
        list.add("Himachal Pradesh");
        list.add("Jammu and Kashmir");
        list.add("Jharkhand");
        list.add("Karnataka");
        list.add("Kerala");
        list.add("Madhya Pradesh");
        list.add("Maharashtra");
        list.add("Manipur");
        list.add("Meghalaya");
        list.add("Mizoram");
        list.add("Nagaland");
        list.add("Odisha");
        list.add("Punjab");
        list.add("Rajasthan");
        list.add("Sikkim");
        list.add("Tamil Nadu");
        list.add("Tripura");
        list.add("Uttar Pradesh");
        list.add("Uttarakhand");
        list.add("West Bengal");

        ObservableList<String> obList = FXCollections.observableList(list);
        
         stateComboBox.getItems().clear();
         universityComboBox.getItems().clear();
         stateComboBox.setItems(obList);
        
         
         // TableView
         //collegeTable = new TableView<Integer>();

    }

}
