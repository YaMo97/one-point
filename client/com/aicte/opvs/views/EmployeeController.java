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

public class EmployeeController implements Initializable {

    @FXML
    private JFXButton submitButton;

    @FXML
    private TableView<?> employeeTable;

    @FXML
    private ComboBox<String> stateComboBox;

    @FXML
    private ImageView homeButton;

    @FXML
    void stateComboBoxAction(ActionEvent event) {

    }

    @FXML
    void submitButtonAction(ActionEvent event) {
    	// TODO
    	
    	String state = stateComboBox.getValue();
    	
    	StringBuffer queryBuffer = new StringBuffer("1");
    	queryBuffer.append("&role=");
    	queryBuffer.append("EMPLOYEE");
    	
    	queryBuffer.append("&offset=");
    	queryBuffer.append("0");
    	
    	queryBuffer.append("&rowCount=");
    	queryBuffer.append("25");
    	
    	System.out.println(queryBuffer.toString());
        
        // send selected state to server
        HttpGet.sendRequest("/FetchBulkDetails", queryBuffer.toString());
//        
    	JSONObject replyJSON = new JSONObject();
    	replyJSON = HttpGet.receiveResponse();
//    	
    	System.out.println(replyJSON.toString());
//
    	List<String> list = new ArrayList<String>();
//    	
    	if (replyJSON.getInt("errorCode") == 0) {
    		int i = 0;
//    		
    		JSONArray jsonArray = replyJSON.getJSONArray("data");
    		while (!jsonArray.isNull(i)) {
    			list.add(jsonArray.getJSONObject(i).toString());
    			i++;
 		}
    	}
       ObservableList<String> obList = FXCollections.observableList(list);

        for (int i = 0; i < list.size(); i++) {
//            employeeTable.getItems().add(i);
        }
//        
      TableColumn<Integer, Number> intColumn = new TableColumn<>("S.No.");
        intColumn.setCellValueFactory(cellData -> {
            Integer rowIndex = cellData.getValue();
            return new ReadOnlyIntegerWrapper(rowIndex);
        });
//        
        TableColumn<Integer, String> nameColumn = new TableColumn<>("Employee Name");
        nameColumn.setCellValueFactory(cellData -> {
            Integer rowIndex = cellData.getValue();
            return new ReadOnlyStringWrapper(list.get(rowIndex));
        });
////        
//        employeeTable.getColumns().add(intColumn);
//        employeeTable.getColumns().add(nameColumn);
    	
    	
      
    }

    @FXML
    void homeButtonAction(ActionEvent event) {

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
        stateComboBox.setItems(obList);
        
         
         // TableView
         employeeTable = new TableView<Integer>();

    }
}
