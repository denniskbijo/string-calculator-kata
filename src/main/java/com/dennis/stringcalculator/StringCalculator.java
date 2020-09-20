package com.dennis.stringcalculator;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * 
1. Create a simple String calculator with a method signature:
———————————————
int Add(string numbers)
———————————————
The method can take up to two numbers, separated by commas, and will return their sum. 
for example “” or “1” or “1,2” as inputs.
(for an empty string it will return 0) 
Hints:
——————
 - Start with the simplest test case of an empty string and move to one and two numbers
 - Remember to solve things as simply as possible so that you force yourself to write tests you did not think about
 - Remember to refactor after each passing test
———————————————————————————————
2. Allow the Add method to handle an unknown amount of numbers
————————————————————————————————
3. Allow the Add method to handle new lines between numbers (instead of commas).
the following input is ok: “1\n2,3” (will equal 6)
the following input is NOT ok: “1,\n” (not need to prove it - just clarifying)
——————————————————————————————-
4. Support different delimiters
to change a delimiter, the beginning of the string will contain a separate line that looks like this: “//[delimiter]\n[numbers…]” for example “//;\n1;2” should return three where the default delimiter is ‘;’ .
the first line is optional. all existing scenarios should still be supported
————————————————————————————————
5. Calling Add with a negative number will throw an exception “negatives not allowed” - and the negative that was passed. 
if there are multiple negatives, show all of them in the exception message.
————————————————————————————————
6. Numbers bigger than 1000 should be ignored, so adding 2 + 1001 = 2
————————————————————————————————
7. Delimiters can be of any length with the following format: “//[delimiter]\n” for example: “//[***]\n1***2***3” should return 6
————————————————————————————————
8. Allow multiple delimiters like this: “//[delim1][delim2]\n” for example “//[*][%]\n1*2%3” should return 6.
————————————————————————————————
9. make sure you can also handle multiple delimiters with length longer than one char
———————————————————————————————— 
 *
 */
public class StringCalculator {
	
	// Calculator Instance variables
	private String delimiter;
	private String numbers;
	

	public StringCalculator(String delimiter, String numbers) {
		super();
		this.delimiter = delimiter;
		this.numbers = numbers;
	}

	// Instance method to add numbers split by delimiter
	private int add() {
		return getNumberStream().sum();
	}

	private IntStream getNumberStream() {
		// Using stream to Split by comma or newline and add numbers
		return Arrays.stream(numbers.split(delimiter)).mapToInt(Integer::parseInt);
	}

	// Custom Methods
	public static int Add(String numbers) {
		int sum = 0;

		StringCalculator calculator = initCalculator(numbers);
		if (!numbers.isEmpty()) {
			sum = calculator.add();
		}
		return sum;
	}

	private static StringCalculator initCalculator(String numbers) {
		// Setting default delimiter
		String delimiter = ",|\n";
		StringCalculator calculator = new StringCalculator(delimiter, numbers);
		// Handle specified delimiter if any
		if (numbers.startsWith("//")) {
			// Split until delimiter
			String[] split = numbers.split("\n", 2);
			// Assign the remaining part as numbers
			// Assign the part after // as new delimiter
			calculator = new StringCalculator(split[0].substring(2), split[1]);

		}
		return calculator;
	}

}

