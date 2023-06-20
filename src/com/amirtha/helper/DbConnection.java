/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.amirtha.helper;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {

	private static Connection conn;

	public static Connection getConnection() {
		if (conn == null) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/eventmanagement", "root", "");
				conn = connect;
			} catch (Exception e) {
				System.err.println("Exception occured when getting db connection...");
			}
		}
		return conn;
	}

}
