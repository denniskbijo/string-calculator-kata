package com.dennis.stringcalculator;

import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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
		// Handle negative numbers in stream
		validatePositiveNumbers();
		return getNumberStream().sum();
	}

	// Custom Methods

	private void validatePositiveNumbers() {
		// Get all negative numbers as comma seperated
		String negativeNumbers = getNumberStream().filter(n -> n < 0).mapToObj(Integer::toString)
				.collect(Collectors.joining(","));
		if (!negativeNumbers.isEmpty()) {
			throw new IllegalArgumentException("negative number found: " + negativeNumbers);
		}
	}

	private IntStream getNumberStream() {
		if (numbers.isEmpty()) {
			return IntStream.empty();
		} else {
		// Using stream to Split by comma or newline and add numbers
			return Stream.of(numbers.split(delimiter))
					.mapToInt(Integer::parseInt)
					.map(n -> n > 1000 ? 0 : n);// Ignore Numbers greater than 1000
		}
	}


	public static int Add(String numbers) {
		return initCalculator(numbers).add();
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
			String delimiterPart = split[0];
			// Handles special characters in delimiter
			calculator = new StringCalculator(processDelimiter(delimiterPart), split[1]);

		}
		return calculator;
	}

	private static String processDelimiter(String delimiterPart) {
		// Assign the part after // as new delimiter
		String delimiter = delimiterPart.substring(2);
		if (delimiter.startsWith("[")) {
			// Removes brackets to get the delimiter
			delimiter = delimiter.substring(1, delimiter.length() - 1);
		}
		// Find delimiters separated by ][
		return Stream.of(delimiter.split("]\\["))
				.map(Pattern::quote)
				.collect(Collectors.joining("|"));// Distinguish delimiters by pipe
	}

}

