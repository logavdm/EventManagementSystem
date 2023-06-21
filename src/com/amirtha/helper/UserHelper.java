package com.amirtha.helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.amirtha.model.User;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class UserHelper {
	
	private static final String SELECT_ALL_NON_ADMIN_USERS = "SELECT * FROM USERS";
	
	private static final String COUNT_USER_BY_USERNAME="SELECT COUNT(ID) AS COUNT FROM USERS WHERE USERNAME=?";
	
	private static final String DELETE_USER_BY_USERNAME="DELETE FROM USERS WHERE USERNAME=?";
	
	private static final String INSERT_USER="INSERT INTO USERS(USERNAME,PASSWORD,ROLE,ENABLED) VALUES(?,?,?,?)";

	private static final String UPDATE_USER_PASSWPRD="UPDATE USERS SET PASSWORD=? WHERE USERNAME=?";

	
	public ObservableList<User> getNonAdminUsers(){
		
		ObservableList<User> listUsers = FXCollections.observableArrayList();
		
		try {
			PreparedStatement prepare = DbConnection.getConnection().prepareStatement(SELECT_ALL_NON_ADMIN_USERS);
			ResultSet rs = prepare.executeQuery();
			while (rs.next()) {
				User u = new User();
				u.setId(rs.getLong("id"));
				u.setUsername(rs.getString("username"));
				u.setPassword(rs.getString("password"));
				u.setRole(rs.getString("role"));
				u.setEnabled(rs.getBoolean("enabled"));
				u.setUpdatedAt(rs.getTimestamp("updated_at"));
				u.setCreatedAt(rs.getTimestamp("created_at"));
				listUsers.add(u);
			}
		} catch (Exception e) {
			System.err.println("Exception occured when retrive users ...");
		}
		return listUsers;
	}
	
	public boolean checkUserExists(String username) {
		boolean flag=false;
		try {
			PreparedStatement prepare = DbConnection.getConnection().prepareStatement(COUNT_USER_BY_USERNAME);
			prepare.setString(1, username);
			ResultSet rs = prepare.executeQuery();
			rs.next();
			if(rs.getInt("COUNT")>0) {
				flag=true;
			}
		}catch (Exception e) {
			System.err.println("Exception occured when count user by username ....");
		}
		return flag;
	}
	
	public boolean deleteUser(String username) {
		boolean flag=false;
		try {
			PreparedStatement prepare = DbConnection.getConnection().prepareStatement(DELETE_USER_BY_USERNAME);
			prepare.setString(1, username);
			prepare.execute();
			flag=true;
		}catch (Exception e) {
			System.err.println("Exception occured when delete the user...");
		}
		return flag;
	}
	
	public boolean addUser(User user) {
		boolean flag=false;
		try {
			
			PreparedStatement prepare = DbConnection.getConnection().prepareStatement(INSERT_USER);
			prepare.setString(1, user.getUsername());
			prepare.setString(2, user.getPassword());
			prepare.setString(3, "USER");
			prepare.setBoolean(4, true);
			prepare.execute();
			flag=true;
			
		}catch (Exception e) {
			System.err.println("Exception occured when add new user....");
			e.printStackTrace();
		}
		return flag;
	}
	
	public boolean changePassword(User user) {
		boolean flag=false;
		try {
			PreparedStatement prepare = DbConnection.getConnection().prepareStatement(UPDATE_USER_PASSWPRD);
			prepare.setString(1, user.getPassword());
			prepare.setString(2, user.getUsername());
			prepare.execute();
			flag=true;
		}catch (Exception e) {
			System.err.println("Exception occured when change the user password...");
		}
		return flag;
	}

}