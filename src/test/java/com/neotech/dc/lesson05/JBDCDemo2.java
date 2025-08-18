package com.neotech.dc.lesson05;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class JBDCDemo2 {
	
	// Retrive all the book category names and store then in ArrayList 
	// Print the ArrayList in the console 
	
	public static String dbUsername = "user1";
	public static String dbPassword = "Neotech@123";
	
	// DB URL format: 
	public static String dbUrl = "jdbc:mysql://hrm.neotechacademy.com:3306/LibraryMgmt";
	
	public static void main(String[] args) throws SQLException {
		
	Connection conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
		
	// create a sql statement
	Statement st = conn.createStatement();
	
	// execute the sql statement 
	ResultSet rs = st.executeQuery("Select * from bookcategory");
	
	// Convert the ResultSet to ArrayList 
	ArrayList<String> bookCategories = new ArrayList<>();
	
	while(rs.next()) {
		// move the cursor to the next row 
		// get the value of the column "CategoryName"
		String categoryName = rs.getString("BookCategoryName");
		bookCategories.add(categoryName);
		
	}
	
	// print the ArrayList in the console
	System.out.println("Book Categories: " + bookCategories);
	
	// lest print the items in the ArrayList 
	for(String category : bookCategories) {
		System.out.println(category + " ");
	}
	
	// close the connection 
	rs.close();
	st.close();
	conn.close();
	
	
	
	
	
	
 		
		
	}
	
	

}
