import java.util.HashMap;
import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

class Groceries {
	private String groceries_path = "./local-data/groceries.csv";
	private File f = new File(groceries_path);
	
	private HashMap<String,Double> gl = new HashMap();
	
	public Groceries() {
		// Read the file
		try {
			Scanner scanner = new Scanner(f);
			while (scanner.hasNext()) {
				String next = scanner.nextLine();
				gl.put(next.split(",")[0], Double.parseDouble(next.split(",")[1]));
			}
		} catch (FileNotFoundException e) {
			try {
				f.createNewFile();
			} catch(IOException e1) {
				System.out.printf("IO Exception\n");
			}
		}
	}
	
	public Groceries(File f) {}
	
	public void add() {
		Scanner keyboard = new Scanner(System.in);
		System.out.printf("Grocery: ");
		String g = keyboard.nextLine();
		System.out.printf("Price: $");
		Double p = keyboard.nextDouble();
		
		gl.put(g, p);
	}
	
	public void list() {
		for (String names: gl.keySet()) {
			System.out.printf("%s, $%.2f\n", names, gl.get(names));
		}
	}
	
	public void remove() {
		Scanner keyboard = new Scanner(System.in);
		System.out.printf("Remove: ");
		String item = keyboard.nextLine();
		remove(item);
	}
	
	public void remove(String item) {
		if (gl.containsKey(item)) {
			gl.remove(item);
		} else {
			System.out.printf("%s is not known\n", item);
		}
	}
	
	public void update() {
		try {
			FileWriter write = new FileWriter(groceries_path, false);
			PrintWriter print_line = new PrintWriter(write);
		
			for (String g: gl.keySet()) {
				print_line.printf("%s,%f\n", g, gl.get(g));
			}
			
			print_line.close();
		} catch (IOException e) {
			System.out.printf("Cannot open file");
		}
	}
	
	public void priceOf() {
		Scanner keyboard = new Scanner(System.in);
		System.out.printf("Grocery: ");
		String item = keyboard.nextLine();
		priceOf(item);
	}
	
	public void priceOf(String item) {
		if (gl.containsKey(item)) {
			System.out.printf("Price: $%.2f\n", gl.get(item));
		} else {
			System.out.printf("%s is not known\n", item);
		}
	}
}