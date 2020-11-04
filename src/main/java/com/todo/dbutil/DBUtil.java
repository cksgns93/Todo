package com.todo.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	private static final String DATABASE_URL = "";
	private static final String DATABASE_UESR = "";
	private static final String DATABASE_PASSWORD = "";
	private static final String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";

	static {
		try {
			Class.forName(DRIVER_CLASS);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(DATABASE_URL, DATABASE_UESR, DATABASE_PASSWORD);
	}
}
