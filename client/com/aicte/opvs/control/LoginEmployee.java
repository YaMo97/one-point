package com.aicte.opvs.control;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class LoginEmployee extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root=FXMLLoader.load(getClass().getResource("/com/aicte/opvs/control/LoginEmployee.fxml"));
			//BorderPane root = new BorderPane();
			Scene scene = new Scene(root);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			System.out.println("LoginEmployee Caught: " + e);
		}
	}
	
	public static void main(String[] args) {
		launch(args);
		//Application.launch(Main.class, new String[0]);
	}
}
