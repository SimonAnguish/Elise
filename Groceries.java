import java.sql.*;
import java.util.*;

class Groceries {
	public Groceries() {
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:kitchen.db");
			System.out.println("Opened database successfully");

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
		
		System.out.println("Table created successfully");
	}
	
	public void add() {
		Scanner keyboard = new Scanner(System.in);
		System.out.printf("Grocery: ");
		String g = keyboard.nextLine();
		System.out.printf("Price: $");
		Double p = keyboard.nextDouble();
//			System.out.println(rs);

//			sql = "INSERT INTO COMPANY (NAME, COST) " +
//				"VALUES (?, ?);";
//				
//			PreparedStatement pstmt = c.prepareStatement(sql);
//			pstmt.setString(1, g);
//			pstmt.setDouble(2, p);
//
//			System.out.println(pstmt.executeQuery());
//			pstmt.close();
//			c.commit();
//			
	}
	
//	public void list() {
//		for (String names: gl.keySet()) {
//			System.out.printf("%s, $%.2f\n", names, gl.get(names));
//		}
//	}
//	
//	public void remove() {
//		Scanner keyboard = new Scanner(System.in);
//		System.out.printf("Remove: ");
//		String item = keyboard.nextLine();
//		remove(item);
//	}
//	
//	public void remove(String item) {
//		if (gl.containsKey(item)) {
//			gl.remove(item);
//		} else {
//			System.out.printf("%s is not known\n", item);
//		}
//	}
//	
//	public void update() {
//		try {
//			FileWriter write = new FileWriter(groceries_path, false);
//			PrintWriter print_line = new PrintWriter(write);
//		
//			for (String g: gl.keySet()) {
//				print_line.printf("%s,%f\n", g, gl.get(g));
//			}
//			
//			print_line.close();
//		} catch (IOException e) {
//			System.out.printf("Cannot open file");
//		}
//	}
//	
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