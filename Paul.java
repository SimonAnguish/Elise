import java.io.*;
import java.util.*;
import java.sql.*;

class Paul {
	public static void main(String[] args) {
		Connection c = null;
		Statement stmt = null;
		
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:home.db");
			c.setAutoCommit(false);
			
			stmt = c.createStatement();
			String sql = "CREATE TABLE IF NOT EXISTS ROOMMATES " + 
							"(ID INTEGER PRIMARY KEY AUTOINCREMENT, " + 
							"NAME TEXT NOT NULL, " + 
							"BIRTHDAY TEXT NOT NULL, " + 
							"FAVORITE_DRINK TEXT NOT NULL, " + 
							"ELECTRIC_OWED REAL NOT NULL, " + 
							"GAS_OWED REAL NOT NULL, " +
							"WATER_OWED REAL NOT NULL, " +
							"INTERNET_OWED REAL NOT NULL)";
			stmt.executeUpdate(sql);
			
//			sql = "INSERT INTO ROOMMATES (NAME, BIRTHDAY, FAVORITE_DRINK, ELECTRIC_OWED, GAS_OWED, WATER_OWED, INTERNET_OWED) " + 
//							"VALUES ('SIMON', 'APRIL 27TH', 'SCOTCH & SODA', 0.0, 0.0, 0.0, 0.0);";
//			stmt.executeUpdate(sql);
			
			stmt.close();
			c.commit();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		
//		Groceries g = new Groceries();
		Utilities u = new Utilities();
		u.list();
//		u.delete_by_id(5);
//		u.list();
	}
}