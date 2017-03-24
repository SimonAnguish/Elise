import java.sql.*;
import java.util.Scanner;

class Utilities {
	public Utilities() {
		Connection c = null;
		Statement stmt = null;
		
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:home.db");
			
			stmt = c.createStatement();
			String sql = "CREATE TABLE IF NOT EXISTS UTILITIES " + 
							"(ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
							"MONTH TEXT NOT NULL, " + 
							"AMOUNT REAL NOT NULL, " + 
							"COMPANY TEXT NOT NULL, " + 
							"SPLIT_COUNT INT NOT NULL);";
			stmt.executeUpdate(sql);
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}
	
	public void add() {
		
		Scanner keyboard = new Scanner(System.in);
		
		// I have no idea why I need another Scanner
		// Someone please explain this makes no sense
		Scanner k = new Scanner(System.in);
		System.out.printf("Month of Bill: ");
		String month = keyboard.nextLine();
		System.out.printf("Amount: ");
		Double amount = keyboard.nextDouble();
		System.out.printf("Utility Name: ");
		
		// This line doesn't work with 'keyboard.nextLine();'
		// And I have no idea why
		String company = k.nextLine();
		add_utility(month, amount, company);
	}
	
	private void add_utility(String month, Double amount, String company) {
		Connection c = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
	
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:home.db");
			c.setAutoCommit(false);
			
			stmt = c.createStatement();
			String sql = "SELECT * FROM ROOMMATES;";
			ResultSet rs = stmt.executeQuery(sql);
			int rc = 0;
			while (rs.next()) {
				rc++;
			}
			
			sql = "INSERT INTO UTILITIES (MONTH, AMOUNT, COMPANY, SPLIT_COUNT) " +
					"VALUES (?, ?, ?, ?);";
					
			pstmt = c.prepareStatement(sql);
			pstmt.setString(1, month);
			pstmt.setDouble(2, amount);
			pstmt.setString(3, company);
			pstmt.setInt(4, rc);
			
			pstmt.executeUpdate();
			pstmt.close();
			stmt.close();	
			c.commit();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}
	
	public void list() {
		Connection c = null;
		Statement stmt = null;
		
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:home.db");
			c.setAutoCommit(false);
			
			stmt = c.createStatement();
			String sql = "SELECT id, company FROM UTILITIES;";
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				System.out.printf("%d: %s\n", rs.getInt("id"), rs.getString("company"));
			}
			
			stmt.close();
			c.commit();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}
	
	public void delete() {
		Scanner keyboard = new Scanner(System.in);
		list();
		System.out.printf("Identify utility to delete (by id): ");
		int id = keyboard.nextInt();
		delete_by_id(id);
	}
	
	private void delete_by_id(int id) {
		Connection c = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:home.db");
			c.setAutoCommit(false);
			
			String sql = "DELETE FROM UTILITIES WHERE ID = ?;";
			
			pstmt = c.prepareStatement(sql);
			pstmt.setInt(1, id);
			
			pstmt.executeUpdate();
			pstmt.close();
			c.commit();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}
}