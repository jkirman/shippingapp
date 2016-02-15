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
	public boolean checkInputs(String length, String width, String depth, String weight, Destination destination) throws Exception {
		throw new Exception("Not implemented");
	}
	
	/**
	 * Checks if the size of the entered package is an allowable size for shipment
	 * @param dimensions An array containing the dimensions of the package: (width, height, depth)
	 * @return True if the size is an allowable size, false otherwise
	 */
	public boolean checkSize(double[] dimensions) throws Exception {
		throw new Exception("Not implemented");
	}
	
	/**
	 * Checks if the weight of the entered package is an allowable weight for shipment
	 * @param weight The weight of the package
	 * @return True if the weight is an allowable weight, false otherwise
	 */
	public boolean checkWeight(double weight) throws Exception {
		throw new Exception("Not implemented");
	}
	
	/**
	 * Calculates the rate for which the package is shipped based on its weight
	 * @param weight
	 * @param dest
	 * @return The calculated rate of the shipment
	 */
	public double calculateRateByWeight(double weight, Destination dest) throws Exception {
		throw new Exception("Not implemented");
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
		throw new Exception("Not implemented");
	}
	
}