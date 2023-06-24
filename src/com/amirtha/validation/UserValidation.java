package com.amirtha.validation;

import com.amirtha.model.User;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class UserValidation {
	
	public static final int USERNAME_MIN_LENGTH=4;
	public static final int USERNAME_MAX_LENGTH=10;
	
	public static final int PASSWORD_MIN_LENGTH=4;
	public static final int PASSWORD_MAX_LENGTH=10;
	
	

	public boolean addUserValidation(User user) {
		
		boolean flag=true;
		Alert alert;
		
		if (user.getUsername().isBlank() || user.getPassword().isBlank()) {
			alert = new Alert(AlertType.WARNING);
			alert.setTitle("Please provide username and password");
			alert.setHeaderText(null);
			alert.setContentText("Please provide username and password");
			alert.showAndWait();
			return false;
		}
		if (user.getUsername().length() < USERNAME_MIN_LENGTH || user.getUsername().length() > USERNAME_MAX_LENGTH) {
			alert = new Alert(AlertType.WARNING);
			alert.setTitle("Username must be atleast "+USERNAME_MIN_LENGTH+" digit and less than "+USERNAME_MAX_LENGTH);
			alert.setHeaderText(null);
			alert.setContentText("Username must be atleast "+USERNAME_MIN_LENGTH+" digit and less than "+USERNAME_MAX_LENGTH);
			alert.showAndWait();
			return false;
		}

		if (user.getPassword().length() < PASSWORD_MIN_LENGTH || user.getPassword().length() > PASSWORD_MAX_LENGTH) {
			alert = new Alert(AlertType.WARNING);
			alert.setTitle("Password must be atleast "+PASSWORD_MIN_LENGTH+" digit and not greater than "+PASSWORD_MAX_LENGTH);
			alert.setHeaderText(null);
			alert.setContentText("Password must be atleast "+PASSWORD_MIN_LENGTH+" digit and not greater than "+PASSWORD_MAX_LENGTH);
			alert.showAndWait();
			return false;
		}
		return flag;
	}
}
