package com.neotech.dc.lesson06;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.junit.Test;

// Homework 1: 

//Connect to classicmodels database
//Execute a query to get all information of customer with id 124
//Get the resultset metadata
//Print the number of columns
//Get all the column names and store them in an arraylist
//Print the Arraylist

public class Homework {

	public static String dbUsername = "user1";
	public static String dbPassword = "Neotech@123";
	public static String dbUrl = "jdbc:mysql://hrm.neotechacademy.com:3306/classicmodels";

	@Test
	public void rsMetaData() throws SQLException {
		Connection conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);

		// create a statement object
		Statement st = conn.createStatement();

		// execute a query
		ResultSet rs = st.executeQuery("Select * from customers\r\n" + "where customerNumber = 124");

		// we have the result set, lets get the medatada
		ResultSetMetaData rsMetaData = rs.getMetaData();

		// what can I get from the ResultSetMetadaData ?

		// I can get the number of columns
		int columnCount = rsMetaData.getColumnCount();
		System.out.println("Number of columns " + columnCount);
		
		// Lest create an ArrayList to store the column names 
		ArrayList<String> columnNames = new ArrayList<>();
		
		
		// I can get the types of the columns
		for (int i = 1; i <= columnCount; i++) {
			String columnName = rsMetaData.getColumnName(i);
			columnNames.add(columnName);
		}
		
		// print the ArrayList in the console 
		System.out.println("Column Names " + columnNames);
		
		// lets print the customer name and phone number 
		rs.next(); // move the cursor to the first row 
		System.out.println("Customer name: " + rs.getString("customerName"));
		System.out.println("Phone number: " + rs.getString("phone"));
		
		
		// close the resources
		rs.close();
		st.close();
		conn.close();

	}

}
