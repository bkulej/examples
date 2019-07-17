package pl.np.example.java;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class ArraysExample extends Example {

	public static void main(String[] args) {
		convertToArray();
	}

	private static void convertToArray() {
		header("convertToArray");

		char[] charArray = "ABCDEFGHIJ\u03BB".toCharArray();
		println("\"ABCDEFGHIJ\".toCharArray()", Arrays.toString(charArray));

		int[] intArray = "1234567890\u03BB".chars().toArray();
		println("\"1234567890\".codePoints().toArray()", Arrays.toString(intArray));

		intArray = "0123456789ABCDEFGX+".chars()
				.filter(i -> (i >= '0' && i <= '9') || (i >= 'A' && i <= 'F'))
				.map(i -> (i >= '0' && i <= '9') ? (i - '0') : (i + 10 - 'A'))
				.toArray();
		
		println("\"0123456789ABCDEFGX+\" hex to int", Arrays.toString(intArray));

		footer("convertToArray");
	}
	

	@SuppressWarnings("unused")
	private static void sort() {
		header("sort()");
		String[] table = new String[] { "zenek", "ala", "grzesiek", "gienek" };
		println("befor sort", Arrays.asList(table));

		// Normal sort
		Arrays.sort(table);
		println("normal sort", Arrays.asList(table));

		// Reverse sort with Collections comparator
		Arrays.sort(table, Collections.reverseOrder());
		println("reverse sort", Arrays.asList(table));

		// Sort length by comparator
		Arrays.sort(table, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o1.length() - o2.length();
			}
		});
		println("table comparator sort", Arrays.asList(table));

		// Sort length by lambda java 8
		Arrays.sort(table, (o1, o2) -> o1.length() - o2.length());
		println("table lambda sort", Arrays.asList(table));

		footer("sort()");
	}

}
