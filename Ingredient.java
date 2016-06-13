class Ingredient {
	private String name;
	private double amt;
	
	public Ingredient(String name, double amt) {
		this.name = name;
		this.amt = amt;
	}
	
	public String getName() {
		return this.name;
	}
	
	public double getAmount() {
		return this.amt;
	}
}