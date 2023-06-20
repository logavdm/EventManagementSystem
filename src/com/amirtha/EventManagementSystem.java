package com.amirtha;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class EventManagementSystem extends Application {
	
	 private double x = 0;
	    private double y = 0;
	    
	@Override
	public void start(Stage stage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/com/amirtha/view/EventDashboard.fxml"));
	        Scene scene = new Scene(root);
	        
	        root.setOnMousePressed((MouseEvent event) ->{
	            x = event.getSceneX();
	            y = event.getSceneY();
	        });
	        
	        root.setOnMouseDragged((MouseEvent event) ->{
	            stage.setX(event.getScreenX() - x);
	            stage.setY(event.getScreenY() - y);
	            
	            stage.setOpacity(.8);
	        });
	        
	        root.setOnMouseReleased((MouseEvent event) ->{
	            stage.setOpacity(1);
	        });
	        
	        stage.initStyle(StageStyle.TRANSPARENT);
	        
	        stage.setScene(scene);
	        stage.show();
	        
		}catch (Exception e) {
			System.err.println("Application load failed....");
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
