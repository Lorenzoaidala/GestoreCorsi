package it.polito.tdp.corsi.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
	
	public static Connection getConnection() throws SQLException{
		String jbdcURL = "jdbc:mysql://localhost/iscritticorsi?user=root&password=Lollolol98";
		return DriverManager.getConnection(jbdcURL);
	}

}
