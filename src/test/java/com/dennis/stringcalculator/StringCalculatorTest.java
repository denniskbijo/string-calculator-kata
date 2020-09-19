package com.dennis.stringcalculator;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Unit tests for simple StringCalculator.
 */
@RunWith(JUnit4.class)
public class StringCalculatorTest {

	@Test
	public void testSumsEmptyStringToZero() {
		assertEquals(0, StringCalculator.Add(""));
	}

	@Test
	public void testSumSingleNumberToItself() {
		assertEquals(1, StringCalculator.Add("1"));
	}

}
