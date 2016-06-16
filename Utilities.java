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
		Connection c = null;
		Statement stmt = null;
		
		Scanner keyboard = new Scanner(System.in);
		System.out.printf("Month of Bill: ");
		String month = keyboard.nextLine();
		System.out.printf("\nAmount: ");
		Double amount = keyboard.nextDouble();
		System.out.printf("\nUtility Name: ");
		String company = keyboard.nextLine();
		
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
				System.out.println(rs.getString("name"));
			}
			System.out.println("Roommate Count: " + rc);
			
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
			String sql = "SELECT id, name FROM ROOMMATES;";
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				System.out.printf("%d: %s\n", rs.getInt("id"), rs.getString("name"));
			}
			
			stmt.close();
			c.commit();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}
	
	public void delete_by_id(int id) {
		Connection c = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:home.db");
			c.setAutoCommit(false);
			
			String sql = "DELETE FROM ROOMMATES WHERE ID = ?;";
			
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