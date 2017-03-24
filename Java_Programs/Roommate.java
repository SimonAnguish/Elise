import java.util.Calendar;
import java.text.*;

class Roommate {
	private int id;
	private String name;
	private Calendar bdate;
	private Double electric_owed;
	private Double gas_owed;
	private Double water_owed;
	private Double internet_owed;
	
	Roommate_ rm_ = new Roommate_();
	
	public Roommate() {
		Roommate r = rm_.add();
		this.id = r.getId();
		this.name = r.getName();
		this.bdate = r.getBirthday_unformatted();
		this.electric_owed = 0.0;
		this.gas_owed = 0.0;
		this.water_owed = 0.0;
		this.internet_owed = 0.0;
	}
	
	public Roommate(int id, String name, Calendar bdate, Double electric, Double gas, Double water, Double internet) {
		this.id = id;
		this.name = name;
		this.bdate = bdate;
		this.electric_owed = electric;
		this.gas_owed = gas;
		this.water_owed = water;
		this.internet_owed = internet;
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getBirthday_formatted() {
		int date = bdate.get(bdate.DAY_OF_MONTH);
		int month = bdate.get(bdate.MONTH);
		int year = bdate.get(bdate.YEAR);
		return month + "/" + date + "/" + year;
	}
	
	public Calendar getBirthday_unformatted() {
		return this.bdate;
	}
	
	/*
		Get the utilities owed, in order
		[Electric, Gas, Water, Internet]
	*/
	public Double[] getUtilitiesOwed() {
		Double[] owed = {electric_owed, gas_owed, water_owed, internet_owed};
		return owed;
	}
	
	public Boolean setName(String name) {
		try {
			rm_.update_name_by_id(name, this.id);
			this.name = name;
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}
}