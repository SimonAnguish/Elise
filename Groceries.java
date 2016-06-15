import java.sql.*;
import java.util.*;

class Groceries {
	public Groceries() {
		try {
			Connection c = null;
			Statement stmt = null;
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:kitchen.db");
//			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			String sql = "CREATE TABLE IF NOT EXISTS GROCERIES " +
									 "(ID INTEGER PRIMARY KEY     AUTOINCREMENT," +
									 " NAME           TEXT    NOT NULL, " + 
									 " COST         REAL)"; 
			stmt.executeUpdate(sql);
			stmt.close();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		
//		System.out.println("Table created successfully");
	}
	
	public void add() {
		Scanner keyboard = new Scanner(System.in);
		System.out.printf("Grocery: ");
		String g = keyboard.nextLine();
		System.out.printf("Price: $");
		Double p = keyboard.nextDouble();
		
		Connection c = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:kitchen.db");
			c.setAutoCommit(false);
//			System.out.println("Opened database successfully");
			
			String sql = "INSERT INTO GROCERIES (NAME, COST) " +
				"VALUES (?, ?);";
				
			pstmt = c.prepareStatement(sql);
			pstmt.setString(1, g);
			pstmt.setDouble(2, p);
			
			pstmt.executeUpdate();
			pstmt.close();
			c.commit();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
	}
	
	public void list() {
		Connection c = null;
		Statement stmt = null;
		
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:kitchen.db");
			c.setAutoCommit(false);
//			System.out.println("Opened database successfully");
			
			String sql = "SELECT * FROM GROCERIES;";
				
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String name = rs.getString("name");
				Double cost = rs.getDouble("cost");
				
				System.out.printf("%s: %.2f\n", name, cost);
			}
			System.out.println();
			stmt.close();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
	}
	
	public void remove() {
		this.list();
		System.out.println();
		Scanner keyboard = new Scanner(System.in);
		System.out.printf("Remove: ");
		String item = keyboard.nextLine();
		remove(item);
	}
	
	public void remove(String item) {
		Connection c = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:kitchen.db");
			c.setAutoCommit(false);
//			System.out.println("Opened database successfully");
			
			String sql = "DELETE FROM GROCERIES " +
				"WHERE name = ?;";
				
			pstmt = c.prepareStatement(sql);
			pstmt.setString(1, item);
			
			pstmt.executeUpdate();
			pstmt.close();
			c.commit();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
	}
	
//	public void priceOf() {
//		Scanner keyboard = new Scanner(System.in);
//		System.out.printf("Grocery: ");
//		String item = keyboard.nextLine();
//		priceOf(item);
//	}
//	
//	public void priceOf(String item) {
//		if (gl.containsKey(item)) {
//			System.out.printf("Price: $%.2f\n", gl.get(item));
//		} else {
//			System.out.printf("%s is not known\n", item);
//		}
//	}
}