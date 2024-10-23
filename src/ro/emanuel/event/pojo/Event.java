package ro.emanuel.event.pojo;

public class Event {

		
		private int id;
		private String brand;
		private String location;
		private double pret;
		public Event(int id, String brand, String location, double pret) {
			this.id = id;
			this.brand = brand;
			this.location = location;
			this.pret = pret;
		}
		public Event(String brand, String location, double pret) {
			this.brand = brand;
			this.location = location;
			this.pret = pret;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getBrand() {
			return brand;
		}
		public void setBrand(String brand) {
			this.brand = brand;
		}
		public String getLocation() {
			return location;
		}
		public void setLocation(String location) {
			this.location = location;
		}
		public double getPret() {
			return pret;
		}
		public void setPret(double pret) {
			this.pret = pret;
		}
		
}
