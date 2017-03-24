import java.sql.*;
import java.util.*;

class Roommate_ {
	public Roommate_() {
		Connection c = null;
		Statement stmt = null;
		
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:home.db");
			
			stmt = c.createStatement();
			String sql = "CREATE TABLE IF NOT EXISTS ROOMMATES " + 
							"(ID INTEGER PRIMARY KEY AUTOINCREMENT, " + 
							"NAME TEXT NOT NULL, " + 
							"BIRTHDAY TEXT NOT NULL, " + 
//							"FAVORITE_DRINK TEXT NOT NULL, " + 
							"ELECTRIC_OWED REAL NOT NULL, " + 
							"GAS_OWED REAL NOT NULL, " +
							"WATER_OWED REAL NOT NULL, " +
							"INTERNET_OWED REAL NOT NULL)";
			stmt.executeUpdate(sql);
			
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}
	
	public Roommate add() {
		Scanner keyboard = new Scanner(System.in);
		System.out.printf("Adding new user:\nName: ");
		String name = keyboard.nextLine();
		System.out.printf("Birthday (mm/dd/yyyy): ");
		String bdate = keyboard.nextLine();
		int id = add_user(name, bdate);
		
		String[] tokens = bdate.split("[/]");
		Calendar c = Calendar.getInstance();
		c.set(Integer.parseInt(tokens[2]), Integer.parseInt(tokens[1]), Integer.parseInt(tokens[0]));
		return new Roommate(id, name, c, 0.0, 0.0, 0.0, 0.0);
	}
	
	private int add_user(String name, String bdate) {
		Connection c = null;
		PreparedStatement pstmt = null;
//		Statement stmt = null;
		int id = -1;
		
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:home.db");
			c.setAutoCommit(false);
			
			String sql = "INSERT INTO ROOMMATES (NAME, BIRTHDAY, ELECTRIC_OWED, GAS_OWED, WATER_OWED, INTERNET_OWED) " + 
							"VALUES (?, ?, 0.0, 0.0, 0.0, 0.0);";
							
			pstmt = c.prepareStatement(sql);
			
			pstmt.setString(1, name);
			pstmt.setString(2, bdate);
							
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				id = rs.getInt(1);
			}
			pstmt.close();
//			stmt.close();
			c.commit();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		
		return id;
	}
	
	public void delete() {
		Scanner keyboard = new Scanner(System.in);
		System.out.printf("Identify user to delete (by id): ");
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
	
	public void update_name_by_id(String name, int id) {
		Connection c = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:home.db");
			c.setAutoCommit(false);
			
			String sql = "UPDATE TABLE ROOMMATES SET name=? WHERE id = ?;";
			pstmt = c.prepareStatement(sql);
			
			pstmt.setString(1, name);
			pstmt.setInt(2, id);
			
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