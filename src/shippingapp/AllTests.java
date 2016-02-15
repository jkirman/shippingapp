package shippingapp;

import static org.junit.Assert.*;

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
	public void testCheckInputs() {
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
			assertEquals(true, s.checkInputs(validInputSt[i][0], validInputSt[i][1], validInputSt[i][2], validInputSt[i][3], validInputDe[i]));
		}
		
		// Test invalid inputs
		for (int i = 0; i < invalidInputSt.length; i++) {
			assertEquals(false, s.checkInputs(invalidInputSt[i][0], invalidInputSt[i][1], invalidInputSt[i][2], invalidInputSt[i][3], invalidInputDe[i]));
		}
		
	}
	
	@Test
	public void testCheckSize() {
		
		double[][] validDimensions = {
				{150, 100, 5},
				{250, 200, 12}};
		
		double[][] invalidDimensions = {
				{100, 100, 5},
				{150, 80, 5},
				{150, 100, 0},
				{400, 200, 10},
				{300, 300, 5},
				{250, 250, 25}};
		
		// Test valid dimensions
		for (int i = 0; i < validDimensions.length; i++) {
			assertEquals(true, s.checkSize(validDimensions[i]));
		}
		
		// Test invalid dimensions
		for (int i = 0; i < invalidDimensions.length; i++) {
			assertEquals(false, s.checkSize(invalidDimensions[i]));
		}
	}
	
	@Test
	public void testCheckWeight() {
		double[] validWeights = {5, 10, 500};
		double[] invalidWeights = {0, 550};
		
		// Test valid weights
		for (int i = 0; i < validWeights.length; i++) {
			assertEquals(true, s.checkWeight(validWeights[i]));
		}
		
		// Test invalid weights
		for (int i = 0; i < invalidWeights.length; i++) {
			assertEquals(false, s.checkWeight(invalidWeights[i]));
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
				currentPrice = s.calculateRateByWeight(testWeights[i][j], Destination.CAN);
				assertEquals("Canadian weight " + testWeights[i][j] + " does not match to right price.",
						prices[1][i], currentPrice, 0);
			}
		}
		
		// Test US rates
		for (int i = 0; i < testWeights.length; i++) {
			for (int j = 0; j < testWeights[i].length; j++) {
				currentPrice = s.calculateRateByWeight(testWeights[i][j], Destination.USA);
				assertEquals("US weight " + testWeights[i][j] + " does not match to right price.",
						prices[2][i], currentPrice, 0);
			}
		}
		
		// Test Itn'l rates
		for (int i = 0; i < testWeights.length; i++) {
			for (int j = 0; j < testWeights[i].length; j++) {
				currentPrice = s.calculateRateByWeight(testWeights[i][j], Destination.ITL);
				assertEquals("International weight " + testWeights[i][j] + " does not match to right price.",
						prices[3][i], currentPrice, 0);
			}
		}
		
	}
	
	// TODO write this test
	@Test
	public void testCalculateRate() {
		
	}
	

}
