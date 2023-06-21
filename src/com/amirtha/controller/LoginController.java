package com.amirtha.controller;

import java.io.IOException;
import java.lang.ModuleLayer.Controller;
import java.net.URL;
import java.util.ResourceBundle;

import com.amirtha.helper.UserHelper;
import com.amirtha.model.User;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class LoginController implements Initializable {
    
    @FXML
    private AnchorPane main_form;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Button loginBtn;

    @FXML
    private Button close;
    
    private UserHelper userHelper;
    
    
    private double x = 0;
	private double y = 0;
    

    public void loginAdmin() throws IOException{
    	
    	String username=this.username.getText();
    	String password=this.username.getText();
    	
    	Alert alert =null;
    	
    	if(username.isBlank() || password.isBlank()) {
    		alert = new Alert(AlertType.WARNING);
			alert.setTitle("Please enter username and password");
			alert.setHeaderText(null);
			alert.setContentText("Please enter username and password...");
			alert.showAndWait();
			return;
    	}
    	
    	if(username.length()<4 || username.length()>10) {
    		alert = new Alert(AlertType.WARNING);
			alert.setTitle("Username must be greater than 4 digit and less than 10");
			alert.setHeaderText(null);
			alert.setContentText("Username must be greater than 4 digit and less than 10..");
			alert.showAndWait();
			return;
    	}
    	
    	if(password.length()<4 || password.length()>10) {
    		alert = new Alert(AlertType.WARNING);
			alert.setTitle("password must be greater than 4 digit and less than 10");
			alert.setHeaderText(null);
			alert.setContentText("password must be greater than 4 digit and less than 10..");
			alert.showAndWait();
			return;
    	}

    	User user=this.userHelper.loginUser(username, password);
    	
    	if(user==null) {
    		alert = new Alert(AlertType.ERROR);
			alert.setTitle("Invalid username or password");
			alert.setHeaderText(null);
			alert.setContentText("Invalid username or password");
			alert.showAndWait();
			return;
    	}
    	
    	if(user!=null) {
    		alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Login Success");
			alert.setHeaderText(null);
			alert.setContentText("Login success");
			alert.showAndWait();
			
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/amirtha/view/EventDashboard.fxml"));     

			Parent root = (Parent)fxmlLoader.load();  
			EventDashboardController controller=fxmlLoader.getController();
			controller.initData(user);
			
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            
            root.setOnMousePressed((MouseEvent event) ->{
                x = event.getSceneX();
                y = event.getSceneY();
            });
            
            root.setOnMouseDragged((MouseEvent event) ->{
                stage.setX(event.getScreenX() - x);
                stage.setY(event.getScreenY() - y);
            });
            
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(scene);
            stage.show();
        
            loginBtn.getScene().getWindow().hide();	
    	}
    }
    
    public void close(){
        System.exit(0);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    	if(this.userHelper==null) {
    		this.userHelper=new UserHelper();
    	}
    	
    }    
    
}
