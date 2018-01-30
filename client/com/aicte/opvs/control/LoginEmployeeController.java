package com.aicte.opvs.control;

import org.json.JSONObject;

import com.aicte.opvs.network.HttpPost;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class LoginEmployeeController {

    @FXML
    private TextField passwordText;

    @FXML
    private Button loginButton;

    @FXML
    private TextField userNameText;

    @FXML
    private ImageView aicteImage;

    @FXML
    void loginButtonActionPerformed(ActionEvent event) {

    	System.out.println("\nLogin Button Action Performed:\n");
    	
    	String emailId = userNameText.getText();
    	if (emailId == null || emailId == "") {
    		Alert alert = new Alert(AlertType.CONFIRMATION, "Login ?", ButtonType.OK);
    		alert.showAndWait();
    		
    		return;
    	}
    	
    	String password = passwordText.getText();
    	if (password == null || password == "") {
    		Alert alert = new Alert(AlertType.CONFIRMATION, "Login ?", ButtonType.OK);
    		alert.showAndWait();
    		
    		return;
    	}
    	
    	//String role = "EMPLOYEE";
    	
    	JSONObject requestJSON = new JSONObject();
    	
    	requestJSON.put("emailId",emailId );
    	requestJSON.put("password",password);
    	
    	/*requestJSON.put("role", role);
    	requestJSON.put("loginId", userName);
    	requestJSON.put("passwordHash", password);st*/
    	
    	System.out.println("Sending requestJSON: " + requestJSON.toString());
    	
    	HttpPost.sendRequest("Login", requestJSON.toString());
    	
    	JSONObject responseJSON = HttpPost.receiveResponse();
    	
    	System.out.println("Reply Recieved responseJSON: " + responseJSON.toString());
    	
    	System.out.println(responseJSON.getString("status"));
		/*Alert alert = new Alert(AlertType.INFORMATION, "STATUS: " + responseJSON.getString("status") + "\nMESSAGE: " + responseJSON.getString("message"), ButtonType.OK);
		
		alert.showAndWait();*/
		
    	if (responseJSON.getInt("errorCode") == 0) {
    		
    		// close window
    		
    		// Goto Next Window
    		// 
    		Stage stage = (Stage) loginButton.getScene().getWindow();
    		try {
    			Parent root = FXMLLoader.load(getClass().getResource("/com/aicte/opvs/fxml/Dashboard.fxml"));
    			
    			Scene scene = new Scene(root);
    			stage.setScene(scene);
    			stage.show();
    		} catch (Exception e){}
    		
    	} else {
    		
    		System.out.println("Try Again!");
    	}

    }

}
