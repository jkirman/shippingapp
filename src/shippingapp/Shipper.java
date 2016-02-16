package shippingapp;

/**
 * Handles the calculation of shipping rates for packages to various regions
 * @author Jeffrey Kirman, Jose Mastrangelo
 */
public class Shipper {
	
	public static enum Destination{ CAN, USA, ITL };
	
	/**
	 * Constructor
	 */
	public Shipper() {}
	
	/**
	 * Checks to see if any inputs are missing from the submission
	 * @param length
	 * @param width
	 * @param depth
	 * @param weight
	 * @param destination
	 * @return True if all inputs have been entered, false otherwise
	 */
	public void checkInputs(String length, String width, String depth, String weight, Destination destination) throws Exception {
		try {
			Double.parseDouble(length);
		} catch (Exception e) { throw new ShippingException("Invalid length: " + length); }
		try {
			Double.parseDouble(width);
		} catch (Exception e) { throw new ShippingException("Invalid width: " + width); }
		try {
			Double.parseDouble(depth);
		} catch (Exception e) { throw new ShippingException("Invalid width: " + depth); }
		try {
			Double.parseDouble(weight);
		} catch (Exception e) { throw new ShippingException("Invalid width: " + weight); }
		if (destination == null) {throw new ShippingException("No destination.");}
	}
	
	/**
	 * Checks if the size of the entered package is an allowable size for shipment
	 * @param dimensions An array containing the dimensions of the package: (width, height, depth)
	 * @return True if the size is an allowable size, false otherwise
	 */
	public void checkSize(double[] dimensions) throws Exception {
		if (dimensions[0] < 140 || dimensions[0] > 380) {
			throw new ShippingException("Invalid length.");
		} else if (dimensions[1] < 90 || dimensions[1] > 270) {
			throw new ShippingException("Invalid width.");
		} else if (dimensions[2] < 1 || dimensions[2] > 20) {
			throw new ShippingException("Invalid depth.");
		}
	}
	
	/**
	 * Checks if the weight of the entered package is an allowable weight for shipment
	 * @param weight The weight of the package
	 * @return True if the weight is an allowable weight, false otherwise
	 */
	public void checkWeight(double weight) throws Exception {
		if (weight < 3 || weight > 500) {
			throw new ShippingException("Weight invalid.");
		}
	}
	
	/**
	 * Calculates the rate for which the package is shipped based on its weight
	 * @param weight
	 * @param dest
	 * @return The calculated rate of the shipment
	 */
	public double calculateRateByWeight(double weight, Destination dest) throws Exception {
		if (dest == Destination.CAN) {
			if (weight >= 3 && weight <= 30) { return 0.85; }
			else if (weight >= 31 && weight <= 50) { return 1.2; }
			else if (weight >= 51 && weight <= 100) { return 1.8; }
			else if (weight >= 101 && weight <= 200) { return 2.95; }
			else if (weight >= 201 && weight <= 300) { return 4.1; }
			else if (weight >= 301 && weight <= 400) { return 4.7; }
			else if (weight >= 401 && weight <= 500) { return 5.05; }
		} else if (dest == Destination.USA) {
			if (weight >= 3 && weight <= 30) { return 1.2; }
			else if (weight >= 31 && weight <= 50) { return 1.8; }
			else if (weight >= 51 && weight <= 100) { return 2.95; }
			else if (weight >= 101 && weight <= 200) { return 5.15; }
			else if (weight >= 201 && weight <= 500) { return 10.3; }
		} else if (dest == Destination.ITL) {
			if (weight >= 3 && weight <= 30) { return 2.5; }
			else if (weight >= 31 && weight <= 50) { return 3.6; }
			else if (weight >= 51 && weight <= 100) { return 5.9; }
			else if (weight >= 101 && weight <= 200) { return 10.3; }
			else if (weight >= 201 && weight <= 500) { return 20.6; }
		}
		throw new ShippingException("Invalid data");
	}
	
	/**
	 * Takes in the raw input and subsequently calculates the rate to ship
	 * @param length
	 * @param width
	 * @param depth
	 * @param weight
	 * @param destination
	 * @return The calculated rate of the shipment
	 */
	public double calculateRate(String length, String width, String depth, String weight, Destination destination) throws Exception {
		this.checkInputs(length, width, depth, weight, destination);
		this.checkSize(new double[] {Double.parseDouble(length), Double.parseDouble(width), Double.parseDouble(depth)});
		this.checkWeight(Double.parseDouble(weight));
		return this.calculateRateByWeight(Double.parseDouble(weight), destination);
	}
	
}