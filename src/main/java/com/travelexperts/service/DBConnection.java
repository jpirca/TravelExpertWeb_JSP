package com.travelexperts.service;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	
	public static Connection getConnection() {
		Connection conn = null;
		
		try {
			// create our mysql database connection		
			//String myDriver = "com.mysql.jdbc.Driver";
			String myUrl = "jdbc:mysql://localhost:3306/travelexperts";
			// Class.forName(myDriver);
			conn = DriverManager.getConnection(myUrl, "root", "");
			return conn;
			} catch (Exception e) {
	        System.err.println("Got an exception! ");
	        System.err.println(e.getMessage());
	    }
		return conn;
	}

}