package ro.emanuel.gift.pojo;

public class Gift {

	private int id;
	private String address;
	private String ocasion;
	private double price;
	
	
	public Gift(int id, String address, String ocasion, double price) {
		this.id = id;
		this.address = address;
		this.ocasion = ocasion;
		this.price = price;
	}


	public Gift(String address, String ocasion, double price) {
		this.address = address;
		this.ocasion = ocasion;
		this.price = price;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getOcasion() {
		return ocasion;
	}


	public void setOcasion(String ocasion) {
		this.ocasion = ocasion;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}
	
	
}
