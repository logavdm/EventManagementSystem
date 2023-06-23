package com.amirtha.helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.amirtha.model.Event;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class EventHelper {

	private static final String SELECT_ALL_EVENTS = "SELECT E.*,D.DEPARTMENT_NAME FROM EVENTS E LEFT JOIN DEPARTMENT D ON D.DEP_ID=E.DEPT_ID ORDER BY E.DATE_FROM";

	private static final String SELECT_ACTIVE_EVENTS = "SELECT E.*,D.DEPARTMENT_NAME FROM EVENTS E LEFT JOIN DEPARTMENT D ON D.DEP_ID=E.DEPT_ID WHERE E.DATE_FROM >= CURRENT_TIMESTAMP ORDER BY E.DATE_FROM";

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
	

}
