package shippingapp;

public class Shipper {
	
	Exception InvalidSizeError = new Exception();
	public enum Destination{ CAN, USA, ITL };
	
	public Shipper() {
		
	}
	
	public boolean checkSize(double[] dimensions) {
		return false;
	}
	
	public boolean checkWeight(double weight) {
		return false;
	}
	
	public Destination parseDestination(String destination) {
		return null;
	}
	
	public boolean checkInputs(String length, String width, String depth, String weight, String destination) {
		return false;
	}
	
	public double calculateRate(double weight, Destination dest) {
		return 0;
	}
	
}
