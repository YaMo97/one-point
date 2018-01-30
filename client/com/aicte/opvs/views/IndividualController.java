package com.aicte.opvs.views;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.json.JSONArray;
import org.json.JSONObject;

import com.aicte.opvs.network.HttpGet;
import com.jfoenix.controls.JFXButton;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;

public class IndividualController implements Initializable {

    @FXML
    private JFXButton submit;

    @FXML
    private ComboBox<String> collegecombo;

    @FXML
    private ComboBox<?> yearcombo;

    @FXML
    private ComboBox<?> collegecombo1;

    @FXML
    private ComboBox<?> yearcombo1;

    @FXML
    private TableView<?> tableview1;

    @FXML
    private ImageView home;

    @FXML
    private ComboBox<String> universitycombo;

    @FXML
    private ComboBox<?> coursecombo;

    @FXML
    private JFXButton submit1;

    @FXML
    private ComboBox<?> universitycombo1;

    @FXML
    private ComboBox<String> statecombo;

    @FXML
    private ComboBox<String> statecombo1;

    @FXML
    private TableView<?> tableview;

    @FXML
    private ComboBox<?> coursecombo1;

    @FXML
    void handlestatecombo(ActionEvent event) {
    	String state = statecombo.getValue();
    	
    	System.out.println("Value pressed in combo " + statecombo.getValue());

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
        
        universitycombo.getItems().clear();
        universitycombo.setItems(obList);
    }

    @FXML
    void handleuniversitycombo(ActionEvent event) {

    	String state = statecombo.getValue();
    	
    	StringBuffer queryBuffer = new StringBuffer("1");
    	queryBuffer.append("&state=");
    	queryBuffer.append(state);
    	
    	System.out.println("Value pressed in combo " + statecombo.getValue());
    	
    	String university = universitycombo.getValue();
    	
    	System.out.println("Value pressed in combo " + universitycombo.getValue());
    	
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
       ObservableList<String> obList = FXCollections.observableList(list);
        
       collegecombo.getItems().clear();
       collegecombo.setItems(obList);
    }

    @FXML
    void handlesubmit(ActionEvent event) {

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

    @FXML
    void handlecollegecombo(ActionEvent event) {

    }

    @FXML
    void handlecoursecombo(ActionEvent event) {

    }

    @FXML
    void handleyearcombo(ActionEvent event) {

    }

    @FXML
    void handlestatecombo1(ActionEvent event) {

    }

    @FXML
    void handleuniversitycombo1(ActionEvent event) {

    }

    @FXML
    void handlesubmit1(ActionEvent event) {

    }

    @FXML
    void handlecollegecombo1(ActionEvent event) {

    }

    @FXML
    void handlecoursecombo1(ActionEvent event) {

    }

    @FXML
    void handleyearcombo1(ActionEvent event) {

    }
    
    @Override
	public void initialize(URL url, ResourceBundle rb) {

    	statecombo.getItems().clear();
		statecombo1.getItems().clear();

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

		statecombo.getItems().clear();
		statecombo.setItems(obList);

		statecombo1.getItems().clear();
		statecombo1.setItems(obList);
    }

}
