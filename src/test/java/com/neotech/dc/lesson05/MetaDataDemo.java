package com.neotech.dc.lesson05;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

public class MetaDataDemo {

	public static String dbUsername = "user1";
	public static String dbPassword = "Neotech@123";
	public static String dbUrl = "jdbc:mysql://hrm.neotechacademy.com:3306/classicmodels";
	@Test
	public void dbMetaData() throws SQLException {
		Connection conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);

		// Get the metadata of the database
		DatabaseMetaData dbMetaData = conn.getMetaData();

		String driverName = dbMetaData.getDriverName();
		System.out.println(driverName);

		String dbVersion = dbMetaData.getDatabaseProductVersion();
		System.out.println(dbVersion);

		conn.close();
	}
	@Test
	public void rsMetaData() throws SQLException {
		Connection conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);

		// create a statement object
		Statement st = conn.createStatement();

		// execute a query
		ResultSet rs = st.executeQuery("Select * from employees\r\n" + "where employeeNumber > 1111");
		
		// we have the result set, lets get the medatada 
		ResultSetMetaData rsMetaData = rs.getMetaData();
		
		// what can I get from the ResultSetMetadaData ? 
		
		// I can get the number of columns
		int columnCount = rsMetaData.getColumnCount();
		System.out.println("Number of columns " + columnCount);
		
		// I can get the name of first column 
		String firstColumnName = rs.getMetaData().getColumnName(1);
		System.out.println("First column name: " + firstColumnName);
		
		// I can get the name of the first column 
		String thirdColumnName = rs.getMetaData().getColumnName(3);
		System.out.println("First column name: " + thirdColumnName);
		
		// I can get the types of the columns 
		for(int i =1; i <= columnCount; i ++) {
			String columnName = rsMetaData.getColumnName(i);
			String columnType = rsMetaData.getColumnTypeName(i);
			System.out.println("Column " + i + ": " + columnName + " - Type " + columnType);
		}
		
		// close the resources 
		rs.close();
		st.close();
		conn.close();
		
		
		
	}

}
