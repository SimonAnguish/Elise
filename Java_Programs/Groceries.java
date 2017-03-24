import java.sql.*;
import java.util.*;

class Groceries {
	public Groceries() {
		Connection c = null;
		Statement stmt = null;
		try {
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
		Scanner keyboard = new Scanner(System.in);
		String response;
		do {
			System.out.printf("\n(R)emove\n(A)dd\n(L)ist\n(P)rice of\n(Q)uit\n==============\n");
			response = keyboard.nextLine().toUpperCase();
			switch (response) {
				case "P": this.priceOf(); break;
				case "R": this.remove(); break;
				case "A": this.add(); break;
				case "L": this.list(); break;
				case "Q": System.exit(0); break;
			}
		} while (response != "Q");
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
			
			String sql = "DELETE FROM GROCERIES WHERE name = ?;";
				
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
	
	public void priceOf() {
		this.list();
		Scanner keyboard = new Scanner(System.in);
		System.out.printf("Grocery: ");
		String item = keyboard.nextLine();
		priceOf(item);
	}
	
	public void priceOf(String item) {
		Connection c = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:kitchen.db");
			c.setAutoCommit(false);
			
			String sql = "SELECT name, cost FROM GROCERIES WHERE name = ?;";
			pstmt = c.prepareStatement(sql);
			pstmt.setString(1, item);
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String name = rs.getString("name");
				Double cost = rs.getDouble("cost");
				
				System.out.printf("%s: %.2f\n",name, cost);
			}
			
			pstmt.close();
			
			c.commit();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}
}