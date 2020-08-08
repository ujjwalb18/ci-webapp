package com.lti.dao.util;
import java.io.FileReader;
import java.sql.*;
import java.util.Properties;
public class ConnManager {

	public static Connection connect() throws Exception {
		 try {
	        	Properties dbprops= new Properties();
	        	dbprops.load(new FileReader("dev-db.properties"));
	        	Class.forName(dbprops.getProperty("driverName"));
	        	return DriverManager.getConnection(dbprops.getProperty("url"), dbprops.getProperty("user"), dbprops.getProperty("pass"));
		 }
		 catch(ClassNotFoundException | SQLException e) {        //ClassNotFoundException | SQLException
	            e.printStackTrace();
	            return null;
		 }
	}
}
