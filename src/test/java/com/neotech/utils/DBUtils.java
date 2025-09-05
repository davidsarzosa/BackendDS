package com.neotech.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DBUtils {

	private static Connection conn;
	private static ResultSet rs;
	private static Statement st;

	public static void getConnection() {
		ConfigsReader.readProperties(Constants.CONFIGURATION_FILEPATH);

		try {
			conn = DriverManager.getConnection(ConfigsReader.getProperty("dbUrl"),
					ConfigsReader.getProperty("dbUsername"), ConfigsReader.getProperty("dbPassword"));
		} catch (SQLException e) {
			System.out.println("Error while establishing connection to the database.");
			e.printStackTrace();
		}
	}

	public static List<Map<String, String>> storeDataFromDb(String query) {

		try {
			st = conn.createStatement();
			rs = st.executeQuery(query);

			ResultSetMetaData rsMetaData = rs.getMetaData();
			int columnCount = rsMetaData.getColumnCount();

			List<Map<String, String>> toReturn = new ArrayList<>();
			Map<String, String> map;

			while (rs.next()) {
				map = new LinkedHashMap<>();

				for (int i = 1; i <= columnCount; i++) {
					String columnName = rsMetaData.getColumnName(i);
					String columnValue = rs.getString(i);
					map.put(columnName, columnValue);
				}

				toReturn.add(map);
			}

			return toReturn;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static List<Map<String, String>> storeDataFromDb2(String query) {
		getConnection();
		List<Map<String, String>> data = storeDataFromDb(query);
		closeConnection();
		return data;
	}

	public static void closeConnection() {
		try {
			if (conn != null) {
				conn.close();
			}

			if (rs != null) {
				rs.close();
			}

			if (st != null) {
				st.close();
			}
		} catch (SQLException e) {
			System.out.println("Error while closing the connection.");
			e.printStackTrace();
		}
	}

}
