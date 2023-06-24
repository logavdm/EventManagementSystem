package com.amirtha.validation;

import java.util.Date;

import com.amirtha.model.EventIO;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class EventsValidation {
	
	public static final int EVENT_NAME_MIN_LENGTH=4;
	
	public static final int EVENT_NAME_MAX_LENGTH=100;
	
	public static final int EVENT_VENUE_MIN_LENGTH=4;
	
	public static final int EVENT_VENUE_MAX_LENGTH=100;
	
	public static final int EVENT_DESCRIPTION_MIN_LENGTH=4;
	
	public static final int EVENT_DESCRIPTION_MAX_LENGTH=255;
	
	
	public boolean addEventValidation(EventIO eve) {
		
		boolean flag=true;
		
		Alert alert;

		if (eve.getName().isBlank() || eve.getVenue().isBlank() || eve.getDescription().isBlank()) {
			alert = new Alert(AlertType.WARNING);
			alert.setTitle("Please provide event name and venue and description");
			alert.setHeaderText(null);
			alert.setContentText("Please provide event name and venue and description");
			alert.showAndWait();
			return false;
		} 
		
		
		if(eve.getName().length()<EVENT_NAME_MIN_LENGTH || eve.getName().length()>EVENT_NAME_MAX_LENGTH) {
			alert = new Alert(AlertType.WARNING);
			alert.setTitle("Event name must be greater than 4 and less than 100");
			alert.setHeaderText(null);
			alert.setContentText("Event name must be greater than 4 and less than 100");
			alert.showAndWait();
			return false;
		}
		
		if(eve.getVenue().length()<EVENT_VENUE_MIN_LENGTH || eve.getVenue().length()>EVENT_VENUE_MAX_LENGTH) {
			alert = new Alert(AlertType.WARNING);
			alert.setTitle("Venue Length must be greater than 4 and less than 100");
			alert.setHeaderText(null);
			alert.setContentText("Venue Length must be greater than 4 and less than 100");
			alert.showAndWait();
			return false;
		}
		
		if(eve.getDescription().length()<EVENT_DESCRIPTION_MIN_LENGTH|| eve.getDescription().length()>EVENT_DESCRIPTION_MAX_LENGTH) {
			alert = new Alert(AlertType.WARNING);
			alert.setTitle("Description must be greater than 4 and less than 255");
			alert.setHeaderText(null);
			alert.setContentText("Description must be greater than 4 and less than 255");
			alert.showAndWait();
			return false;
		}
		
		Date now=new Date();

		if(eve.getFromDate().compareTo(now)<0) {
			alert = new Alert(AlertType.WARNING);
			alert.setTitle("Event Start date must be greater than current date and time...");
			alert.setHeaderText(null);
			alert.setContentText("Event Start date must be greater than current date and time...");
			alert.showAndWait();
			return false;
		}
		
		if(eve.getToDate().compareTo(now)<0 || eve.getToDate().compareTo(eve.getFromDate()) <0 ) {
			alert = new Alert(AlertType.WARNING);
			alert.setTitle("Event End date and Time must be greater than evnet start date and time...");
			alert.setHeaderText(null);
			alert.setContentText("Event End date and Time must be greater than evnet start date and time...");
			alert.showAndWait();
			return false;
		}
		return flag;
	}

}
