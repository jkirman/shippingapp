package shippingapp;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import shippingapp.Shipper.Destination;

public class AllTests {
	
	Shipper s;
	
	@Before
	public void setUp() throws Exception {
		s = new Shipper();
	}
	
	@Test
	public void testCheckInputs(){
		String[][] validInputSt = {{"12", "41", "23.5", "1244.0"}};
		Destination[] validInputDe = { Destination.CAN };
		
		String[][] invalidInputSt = {
				{"", "41", "23.5", "1244.0"},
				{"12", "", "23.5", "1244.0"},
				{"12", "41", "", "1244.0"},
				{"12", "41", "23.5", ""},
				{"12", "41", "23.5", "1244.0"},
				{null, "41", "23.5", "1244.0"},
				{"12", null, "23.5", "1244.0"},
				{"12", "41", null, "1244.0"},
				{"12", "41", "23.5", null}};
		Destination[] invalidInputDe = {
				Destination.CAN, Destination.CAN, Destination.CAN, Destination.CAN,
				null, Destination.CAN, Destination.CAN, Destination.CAN,
				Destination.CAN};
		
		// Test valid inputs
		for (int i = 0; i < validInputSt.length; i++) {
			try {
				s.checkInputs(validInputSt[i][0], validInputSt[i][1], validInputSt[i][2], validInputSt[i][3], validInputDe[i]);
			} catch (Exception e) {
				throw new AssertionError(e.getMessage());
			}
			
		}
		
		// Test invalid inputs
		for (int i = 0; i < invalidInputSt.length; i++) {
			try {			
				s.checkInputs(invalidInputSt[i][0], invalidInputSt[i][1], invalidInputSt[i][2], invalidInputSt[i][3], invalidInputDe[i]);
				throw new AssertionError("Exception expected for invalid inputs: " + Arrays.toString(invalidInputSt[i]) + ", " + invalidInputDe[i].toString());
			} catch (ShippingException e) {} catch (Exception e) {
				throw new AssertionError(e.getMessage());
			}
		}
		
	}
	
	@Test
	public void testCheckSize() {
		
		double[][] validDimensions = {
				{150, 100, 5},
				{250, 200, 20}};
		
		double[][] invalidDimensions = {
				{100, 100, 5},
				{150, 80, 5},
				{150, 100, 0},
				{400, 200, 10},
				{300, 300, 5},
				{250, 250, 25}};
		
		// Test valid dimensions
		for (int i = 0; i < validDimensions.length; i++) {
			try {
				s.checkSize(validDimensions[i]);
			} catch (Exception e) {
				throw new AssertionError(e.getMessage());
			}
		}
		
		// Test invalid dimensions
		for (int i = 0; i < invalidDimensions.length; i++) {
			try {
				s.checkSize(invalidDimensions[i]);
				throw new AssertionError("Exception expected for invalid inputs: " + Arrays.toString(invalidDimensions[i]));
			} catch (ShippingException e) {} catch (Exception e) {
				throw new AssertionError(e.getMessage());
			}
		}
	}
	
	@Test
	public void testCheckWeight() {
		double[] validWeights = {3, 10, 500};
		double[] invalidWeights = {0, 550};
		
		// Test valid weights
		for (int i = 0; i < validWeights.length; i++) {
			try {
				s.checkWeight(validWeights[i]);
			} catch (Exception e) {
				throw new AssertionError(e.getMessage());
			}
		}
		
		// Test invalid weights
		for (int i = 0; i < invalidWeights.length; i++) {
			try {
				s.checkWeight(invalidWeights[i]);
				throw new AssertionError("Exception expected for invalid inputs: " + invalidWeights[i]);
			} catch (ShippingException e) {} catch (Exception e) {
				throw new AssertionError(e.getMessage());
			}
		}
	}
	
	@Test
	public void testCalculateRateByWeight() {
		
		// Prices for different weights in increasing order of weight
		double[][] prices = {
				{0.85, 1.2, 1.8, 2.95, 4.1, 4.7, 5.05}, // Canada
				{1.2, 1.8, 2.95, 5.15, 10.3, 10.3, 10.3}, // US
				{2.5, 3.6, 5.9, 10.3, 20.6, 20.6, 20.6} // Itn'l
				};
		double[][] testWeights = {{3, 30}, {31, 50}, {51, 100}, {101, 200}, {201, 300}, {301, 400}, {401, 500}};
		
		double currentPrice;
		
		// Test Canadian rates
		for (int i = 0; i < testWeights.length; i++) {
			for (int j = 0; j < testWeights[i].length; j++) {
				try {
					currentPrice = s.calculateRateByWeight(testWeights[i][j], Destination.CAN);
				} catch (Exception e) {
					throw new AssertionError(e.getMessage());
				}
				assertEquals("Canadian weight " + testWeights[i][j] + " does not match to right price.",
						prices[0][i], currentPrice, 0);
			}
		}
		
		// Test US rates
		for (int i = 0; i < testWeights.length; i++) {
			for (int j = 0; j < testWeights[i].length; j++) {
				try {
					currentPrice = s.calculateRateByWeight(testWeights[i][j], Destination.USA);
				} catch (Exception e) {
					throw new AssertionError(e.getMessage());
				}
				assertEquals("US weight " + testWeights[i][j] + " does not match to right price.",
						prices[1][i], currentPrice, 0);
			}
		}
		
		// Test Itn'l rates
		for (int i = 0; i < testWeights.length; i++) {
			for (int j = 0; j < testWeights[i].length; j++) {
				try {
					currentPrice = s.calculateRateByWeight(testWeights[i][j], Destination.ITL);
				} catch (Exception e) {
					throw new AssertionError(e.getMessage());
				}
				assertEquals("International weight " + testWeights[i][j] + " does not match to right price.",
						prices[2][i], currentPrice, 0);
			}
		}
		
	}
	
	@Test
	public void testValidCalculateRate() {
		
		Destination dest = Destination.CAN;
		String[][] validTestsSt = {{"150", "100", "5", "10"}, {"150", "100", "5", "40"}, {"250", "200", "12", "250"}, {"250", "200", "12", "500"}};
		double[] result = {0.85, 1.2, 4.1, 5.05};

		for (int i = 0; i < validTestsSt.length; i++) {
			try {
				assertEquals("Wrong value.", result[i], s.calculateRate(validTestsSt[i][0], validTestsSt[i][1], validTestsSt[i][2], validTestsSt[i][3], dest), 0);
			} catch (Exception e) {
				throw new AssertionError(e.getMessage());
			}
		}
		
	}
	
	@Test
	public void testInvalidCalculateRate() {
		Destination dest = Destination.CAN;
		String[][] invalidTestsSt = {{"100", "100", "5", "5"}, {"150", "80", "5", "5"},
				{"150", "100", "0", "5"}, {"150", "100", "1", "2"}, {"400", "200", "10", "10"},
				{"300", "300", "5", "5"}, {"250", "250", "25", "10"}, {"380", "270", "20", "550"},
				{"150", "100", "10", ""}, {"150", "100", "5", "ten"}, {"", "100", "5", "10"}, {"150", "", "5", "10"}};
		
		for (int i = 0; i < invalidTestsSt.length; i++) {
			try {
				s.calculateRate(invalidTestsSt[i][0], invalidTestsSt[i][1], invalidTestsSt[i][2], invalidTestsSt[i][3], dest);
				throw new AssertionError("Exception expected for invalid inputs: " + Arrays.toString(invalidTestsSt[i]) + ", " + dest.toString());
			} catch (ShippingException e) {} catch (Exception e) {
				throw new AssertionError(e.getMessage());
			}
		}
		
	}
	

}
