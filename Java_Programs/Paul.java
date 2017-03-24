import java.io.*;
import java.util.*;
import java.sql.*;

class Paul {
	public static void main(String[] args) {
//		Groceries g = new Groceries();
		Roommate r = new Roommate();
		Roommate[] rm_list = getRoommates();
		for (Roommate rm: rm_list) {
			System.out.println(rm.getName());
		}
	}
	
	private static Roommate[] getRoommates() {
		Connection c = null;
		Statement stmt = null;
		
		int id;
		String name;
		String[] tokens;
		Calendar bdate;
		Double electric;
		Double gas;
		Double water;
		Double internet;
		Roommate rm;
		Roommate[] rm_list = new Roommate[10];
		
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:home.db");
			
			stmt = c.createStatement();
			String sql = "SELECT * FROM ROOMMATES;";
			int i = 0;
			
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				id = rs.getInt("id");
				name = rs.getString("name");
				tokens = rs.getString("birthday").split("[/]");
				bdate = Calendar.getInstance();
				bdate.set(Integer.parseInt(tokens[2]), Integer.parseInt(tokens[1]), Integer.parseInt(tokens[0]));
				electric = rs.getDouble("electric_owed");
				gas = rs.getDouble("gas_owed");
				water = rs.getDouble("water_owed");
				internet = rs.getDouble("internet_owed");
				
				rm = new Roommate(id, name, bdate, electric, gas, water, internet);
				rm_list[i] = rm;
				i++;
			}
			
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		
		return rm_list;
	}
}