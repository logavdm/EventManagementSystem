package com.amirtha.helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.amirtha.model.Event;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class EventHelper {

	private static final String SELECT_ALL_EVENTS = "SELECT E.*,D.DEPARTMENT_NAME FROM EVENTS E LEFT JOIN DEPARTMENT D ON D.DEP_ID=E.DEPT_ID ORDER BY E.DATE_FROM";

	//private static final String SELECT_ACTIVE_EVENTS = "SELECT E.*,D.DEPARTMENT_NAME FROM EVENTS E LEFT JOIN DEPARTMENT D ON D.DEP_ID=E.DEPT_ID ORDER BY E.DATE_FROM";
//
//	private static final String SELECT_EXPIRED_EVENTS = "";

	public ObservableList<Event> getAllEvents() {
		ObservableList<Event> listEvents = FXCollections.observableArrayList();
				
		try {
			PreparedStatement prepare = DbConnection.getConnection().prepareStatement(SELECT_ALL_EVENTS);
			ResultSet rs = prepare.executeQuery();
			while (rs.next()) {
				Event e = new Event();
				e.setId(rs.getLong("event_id"));
				e.setName(rs.getString("event_name"));
				e.setVenue(rs.getString("venue"));
				e.setFromDate(rs.getTimestamp("date_from"));
				e.setToDate(rs.getTimestamp("date_to"));
				e.setFromTime(rs.getString("time_from"));
				e.setToTime(rs.getString("time_to"));
				e.setDescription(rs.getString("description"));
				e.setDepartmentId(rs.getLong("dept_id"));
				e.setDepartmentName(rs.getString("department_name"));
				e.setCreateTime(rs.getDate("created_at"));
				e.setUpdatedTime(rs.getDate("updated_at"));
				listEvents.add(e);
			}

		} catch (Exception e) {
			System.err.println("Exception occured when retrive all events ...");
		}
		return listEvents;
	}
	

	public ObservableList<Event> getActiveEvents() {
		ObservableList<Event> listEvents = FXCollections.observableArrayList();
		
		try {
			PreparedStatement prepare = DbConnection.getConnection().prepareStatement(SELECT_ALL_EVENTS);
			ResultSet rs = prepare.executeQuery();
			while (rs.next()) {
				Event e = new Event();
				e.setId(rs.getLong("event_id"));
				e.setName(rs.getString("event_name"));
				e.setVenue(rs.getString("venue"));
				e.setFromDate(rs.getTimestamp("date_from"));
				e.setToDate(rs.getTimestamp("date_to"));
				e.setFromTime(rs.getString("time_from"));
				e.setToTime(rs.getString("time_to"));
				e.setDescription(rs.getString("description"));
				e.setDepartmentId(rs.getLong("dept_id"));
				e.setDepartmentName(rs.getString("department_name"));
				e.setCreateTime(rs.getDate("created_at"));
				e.setUpdatedTime(rs.getDate("updated_at"));
				listEvents.add(e);
			}

		} catch (Exception e) {
			System.err.println("Exception occured when retrive all events ...");
		}
		return listEvents;
	}

	public void getExpiredEvents() {

	}

}
