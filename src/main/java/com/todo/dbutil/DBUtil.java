package com.todo.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	private static final String DATABASE_URL = "jdbc:mysql://10.113.116.52:13306/user04?serverTimezone=Asia/Seoul&useSSL=false";
	private static final String DATABASE_UESR = "user04";
	private static final String DATABASE_PASSWORD = "user04";
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
