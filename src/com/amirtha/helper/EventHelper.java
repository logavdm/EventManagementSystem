package com.amirtha.helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.amirtha.model.Event;
import com.amirtha.model.EventIO;
import com.amirtha.validation.EventsValidation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class EventHelper {
	
	private final EventsValidation validation;

	private static final String SELECT_ALL_EVENTS = "SELECT E.* FROM EVENTS E ORDER BY E.DATE_FROM";

	private static final String SELECT_ACTIVE_EVENTS = "SELECT E.* FROM EVENTS E WHERE E.DATE_FROM >= CURRENT_TIMESTAMP ORDER BY E.DATE_FROM";

	private static final String INSERT_NEW_EVENT="INSERT INTO EVENTS(EVENT_NAME,VENUE,DATE_FROM,DATE_TO,DESCRIPTION) VALUES(?,?,?,?,?)";
	
	private static final String CHECK_EVENT_EXISTS="SELECT COUNT(*) AS COUNT FROM EVENTS WHERE EVENT_ID=?";
	
	private static final String DELETE_EVENT_BY_ID = "DELETE FROM EVENTS WHERE EVENT_ID=?";

	public EventHelper() {
		this.validation=new EventsValidation();
	}
	
	
	public ObservableList<Event> getAllEvents() {
		ObservableList<Event> listEvents = FXCollections.observableArrayList();
				
		try {
			PreparedStatement prepare = DbConnection.getConnection().prepareStatement(SELECT_ALL_EVENTS);
			ResultSet rs = prepare.executeQuery();
			while (rs.next()) {
				listEvents.add(eventResultExtractor(rs));
			}

		} catch (Exception e) {
			System.err.println("Exception occured when retrive all events ...");
		}
		return listEvents;
	}
	

	public ObservableList<Event> getActiveEvents() {
		ObservableList<Event> listEvents = FXCollections.observableArrayList();
		
		try {
			PreparedStatement prepare = DbConnection.getConnection().prepareStatement(SELECT_ACTIVE_EVENTS);
			ResultSet rs = prepare.executeQuery();
			while (rs.next()) {
				listEvents.add(eventResultExtractor(rs));
			}

		} catch (Exception e) {
			System.err.println("Exception occured when retrive all events ...");
			e.printStackTrace();
		}
		return listEvents;
	}

	public Event eventResultExtractor(ResultSet rs) throws SQLException {
		Event e = new Event();
		e.setId(rs.getLong("event_id"));
		e.setName(rs.getString("event_name"));
		e.setVenue(rs.getString("venue"));
		e.setFromDate(rs.getTimestamp("date_from").toLocalDateTime().toLocalDate());
		e.setToDate(rs.getTimestamp("date_to").toLocalDateTime().toLocalDate());
		e.setFromTime(rs.getTimestamp("date_from").toLocalDateTime().toLocalTime());
		e.setToTime(rs.getTimestamp("date_to").toLocalDateTime().toLocalTime());
		e.setDescription(rs.getString("description"));
		e.setCreateTime(rs.getDate("created_at"));
		e.setUpdatedTime(rs.getDate("updated_at"));
		return e;
	}
	
	public boolean validateAndAddEvent(EventIO event) {
		boolean flag=false;
		if(this.validation.addEventValidation(event)) {
			Alert alert;
			if(addEvent(event)) {
				alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("New Event add success");
				alert.setHeaderText(null);
				alert.setContentText("New Event add success");
				alert.showAndWait();
				flag=true;
			}else {
				alert = new Alert(AlertType.WARNING);
				alert.setTitle("Event add Failed");
				alert.setHeaderText(null);
				alert.setContentText("Event add Failed");
				alert.showAndWait();
			}
		}
		return flag;
	}
	
	public boolean addEvent(EventIO event) {
		boolean flag=false;
		try {
			PreparedStatement prepare = DbConnection.getConnection().prepareStatement(INSERT_NEW_EVENT);
			prepare.setString(1, event.getName());
			prepare.setString(2, event.getVenue());
			prepare.setTimestamp(3, new Timestamp(event.getFromDate().getTime()));
			prepare.setTimestamp(4, new Timestamp(event.getToDate().getTime()));
			prepare.setString(5, event.getDescription());
			prepare.execute();
			flag=true;
		}catch (Exception e) {
			System.err.println("Exception occured when add new Event...");
		}
		return flag;
	}
	
	public boolean checkExists(Long id) {
		boolean flag=false;
		try {
			PreparedStatement prepare = DbConnection.getConnection().prepareStatement(CHECK_EVENT_EXISTS);
			prepare.setLong(1, id);
			ResultSet rs = prepare.executeQuery();
			rs.next();
			int count=rs.getInt("COUNT");
			if(count>0) {
				flag=true;
			}

		} catch (Exception e) {
			System.err.println("Exception occured when check events exists...");
			e.printStackTrace();
		}
		return flag;
	}
	
	public boolean deleteEvent(Long id) {
		boolean flag=false;
		try {
			PreparedStatement prepare = DbConnection.getConnection().prepareStatement(DELETE_EVENT_BY_ID);
			prepare.setLong(1, id);
			prepare.execute();
			flag=true;
		} catch (Exception e) {
			System.err.println("Exception occured when delete events...");
			e.printStackTrace();
		}
		return flag;
	}
	
	
	

}
