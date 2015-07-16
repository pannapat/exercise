package cc.pannapat;
import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import cc.pannapat.RomanCalculator;

public class RomanCalculatorTest {

	private static RomanCalculator calculator;
	
	@BeforeClass
	public static void beforTest(){
		calculator = new RomanCalculator();
	}
	
	@Test
	public void testOnePlusOne(){
		assertEquals("II", calculator.plus("I","I"));
	}
	
	@Test
	public void testTwentyPlusTwo() {
		assertEquals("XXII", calculator.plus("XX","II"));
	}
	
	@Test
	public void testFourteenPlusSixty(){
		assertEquals("LXXIV", calculator.plus("XIV","LX"));
	}
	
	@Test
	public void testFivePludFive(){
		assertEquals("X", calculator.plus("V","V"));
	}
	
	@Test // VII+II=IX
	public void testSevenPlusTwo(){
		assertEquals("IX", calculator.plus("VII","II"));
	}
	
	@Test // XXIX+X=XXXIX
	public void testTwentyninePlusTen(){
		assertEquals("XXXIX", calculator.plus("XXIX","X"));
	}

}
