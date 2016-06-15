import java.io.*;
import java.util.*;
import java.sql.*;

class Elise {
	public static void main(String[] args) {
		
		Groceries g = new Groceries();
		Scanner keyboard = new Scanner(System.in);
		String response;
		do {
			System.out.printf("(R)emove\n(A)dd\n(L)ist\n(Q)uit\n==============\n");
			response = keyboard.nextLine().toUpperCase();
			switch (response) {
				case "R": g.remove(); break;
				case "A": g.add(); break;
				case "L": g.list(); break;
				case "Q": System.exit(0); break;
			}
		} while (response != "Q");
	}
}