package shippingapp;

/**
 * Handles the calculation of shipping rates for packages to various regions
 * @author Jeffrey Kirman, Jose Mastrangelo
 */
public class Shipper {
	
	Exception InvalidSizeError = new Exception();
	public enum Destination{ CAN, USA, ITL };
	
	/**
	 * Constructor
	 */
	public Shipper() {}
	
	/**
	 * Checks if the size of the entered package is an allowable size for shipment
	 * @param dimensions An array containing the dimensions of the package: (width, height, depth)
	 * @return True if the size is an allowable size, false otherwise
	 */
	public boolean checkSize(double[] dimensions) {
		return false;
	}
	
	/**
	 * Checks if the weight of the entered package is an allowable weight for shipment
	 * @param weight The weight of the package
	 * @return True if the weight is an allowable weight, false otherwise
	 */
	public boolean checkWeight(double weight) {
		return false;
	}
	
	/**
	 * Parses a string and converts it to a region identifier
	 * @param destination String of the name of the country/region destination (Canada, USA, or International)
	 * @return A Destination enumeration that identifies the region
	 */
	public Destination parseDestination(String destination) {
		return null;
	}
	
	/**
	 * Checks to see if any inputs are missing from the submission
	 * @param length
	 * @param width
	 * @param depth
	 * @param weight
	 * @param destination
	 * @return True if all inputs have been entered, false otherwise
	 */
	public boolean checkInputs(String length, String width, String depth, String weight, String destination) {
		return false;
	}
	
	/**
	 * Calculates the rate for which the package is shipped
	 * @param weight
	 * @param dest
	 * @return The calculated rate of the shipment
	 */
	public double calculateRate(double weight, Destination dest) {
		return 0;
	}
	
}
