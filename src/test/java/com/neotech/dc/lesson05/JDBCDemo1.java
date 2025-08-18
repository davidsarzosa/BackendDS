package com.neotech.dc.lesson05;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCDemo1 {

	public static String dbUserName = "user1";
	public static String dbPassword = "Neotech@123";

	// DB URL Format
	public static String dbUrl = "jdbc:mysql://hrm.neotechacademy.com:3306/LibraryMgmt";

	public static void main(String[] args) throws SQLException {

		Connection conn = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
		System.out.println("Connection established successfully! ");

		// create a SQL statement
		Statement st = conn.createStatement();

		// execute the sql statement
		ResultSet rs = st.executeQuery("Select * from book");

		System.out.println("--------------------");
		System.out.println(rs.toString());

		// how can I iterate through the result set ?
		rs.next(); // move the cursor to the first row 
		String bookName1= rs.getString("BookName");
		System.out.println(bookName1);
		
		rs.next();
		String bookName2 = rs.getString("BookName");
		System.out.println(bookName2);
		
		// lets iterate through all the rows 
		System.out.println("iterate through all the rows ");
		
		while(rs.next()) {
			// move the cursor to the next row 
			// get the value of the column "BookName"
			String bookName = rs.getString("BookName");
			System.out.println(bookName);
		}

		// close the connection
		rs.close();
		st.close();
		conn.close();

	}

}
