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
		assertEquals(5, StringCalculator.Add("5"));
		assertEquals(45, StringCalculator.Add("45"));
	}

	@Test
	public void testSumNumberSeperatedByComma() {
		assertEquals(3, StringCalculator.Add("1,2"));
		assertEquals(5, StringCalculator.Add("1,4"));
		assertEquals(6, StringCalculator.Add("1,4,1"));
		assertEquals(11, StringCalculator.Add("1,4,6"));
	}

	@Test
	public void testSumNumberSeperatedByNewLine() {
		assertEquals(3, StringCalculator.Add("1\n2"));
		assertEquals(6, StringCalculator.Add("1\n4\n1"));
		assertEquals(44, StringCalculator.Add("1\n4\n6\n33"));
	}

	@Test
	public void testSumNumberSeperatedByCommaOrNewLine() {
		assertEquals(6, StringCalculator.Add("1,4\n1"));
		assertEquals(11, StringCalculator.Add("1\n4,6"));
	}

}
