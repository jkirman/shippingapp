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
		String[][] validInput = {{"12", "41", "23.5", "1244.0", "Canada"}};
		String[][] invalidInput = {
				{"", "41", "23.5", "1244.0", "Canada"},
				{"12", "", "23.5", "1244.0", "Canada"},
				{"12", "41", "", "1244.0", "Canada"},
				{"12", "41", "23.5", "", "Canada"},
				{"12", "41", "23.5", "1244.0", ""},
				{null, "41", "23.5", "1244.0", "Canada"},
				{"12", null, "23.5", "1244.0", "Canada"},
				{"12", "41", null, "1244.0", "Canada"},
				{"12", "41", "23.5", null, "Canada"},
				{"12", "41", "23.5", "1244.0", null}};
		
		// Test valid inputs
		for (int i = 0; i < validInput.length; i++) {
			assertEquals(true, s.checkInputs(validInput[i][0], validInput[i][1], validInput[i][2], validInput[i][3], validInput[i][4]));
		}
		
		// Test invalid inputs
		for (int i = 0; i < invalidInput.length; i++) {
			assertEquals(false, s.checkInputs(invalidInput[i][0], invalidInput[i][1], invalidInput[i][2], invalidInput[i][3], invalidInput[i][4]));
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
		double[] validWeights = {10};
		double[] invalidWeights = {2, 550};
		
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
	public void testParseDestination() {
		Destination[] destinations = {Destination.CAN, Destination.USA, Destination.ITL, null};
		String[][] testStrings = {
				{"Canada", "canada", " CANADA", "cANadA ", "can"},
				{"United States", "USA", "US", "usa ", "   united states of america"},
				{"International", "ITL", "itn'l", "itnl ", "  inTeRNaTionaL "},
				{"kjld", "ImkljdTL", "10039ditn'l", "itnl r", "  inT   eR NaT     ionaL ", "Canadax"}
		};
		
		// Test inputs
		for (int i = 0; i < destinations.length; i++) {
			for (int j = 0; j < testStrings[1].length; j++) {
				assertEquals("Failed on: \"" + testStrings[i][j] + "\"", destinations[i], s.parseDestination(testStrings[i][j]));
			}
		}
		
	}
	
	@Test
	public void testCalculateRate() {
		
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
				currentPrice = s.calculateRate(testWeights[i][j], Destination.CAN);
				assertEquals("Canadian weight " + testWeights[i][j] + " does not match to right price.",
						prices[1][i], currentPrice, 0);
			}
		}
		
		// Test US rates
		for (int i = 0; i < testWeights.length; i++) {
			for (int j = 0; j < testWeights[i].length; j++) {
				currentPrice = s.calculateRate(testWeights[i][j], Destination.USA);
				assertEquals("US weight " + testWeights[i][j] + " does not match to right price.",
						prices[2][i], currentPrice, 0);
			}
		}
		
		// Test Itn'l rates
		for (int i = 0; i < testWeights.length; i++) {
			for (int j = 0; j < testWeights[i].length; j++) {
				currentPrice = s.calculateRate(testWeights[i][j], Destination.ITL);
				assertEquals("International weight " + testWeights[i][j] + " does not match to right price.",
						prices[3][i], currentPrice, 0);
			}
		}
		
	}
	
	

}
