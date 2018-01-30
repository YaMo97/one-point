package com.aicte.opvs.views;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DashboardController {

    @FXML
    private JFXButton employeeButton;

    @FXML
    private JFXButton studentButton;

    @FXML
    private JFXButton collegeButton;

    @FXML
    private JFXButton universityButton;

    @FXML
    void studentButtonAction(ActionEvent event) {
    	// Open Faculty.fxml

    	Stage stage = (Stage) studentButton.getScene().getWindow();
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/com/aicte/opvs/fxml/Individual.fxml"));
			
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (Exception e){}
		
    }

    @FXML
    void employeeButtonAction(ActionEvent event) {
    	// Open Employee.fxml
    	
    	Stage stage = (Stage) employeeButton.getScene().getWindow();
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/com/aicte/opvs/fxml/Employee.fxml"));
			
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (Exception e){}

    }

    @FXML
    void collegeButtonAction(ActionEvent event) {
    	// Open College.fxml
    	
    	Stage stage = (Stage) collegeButton.getScene().getWindow();
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/com/aicte/opvs/fxml/College.fxml"));
			
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (Exception e){}

    }

    @FXML
    void universityButtonAction(ActionEvent event) {
    	// Open University.fxml

    	Stage stage = (Stage) universityButton.getScene().getWindow();
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/com/aicte/opvs/fxml/University.fxml"));
			
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (Exception e){}
    }

}
