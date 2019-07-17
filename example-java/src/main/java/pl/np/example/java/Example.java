package pl.np.example.java;

import java.util.Arrays;

public class Example {

	protected static void header(String exampleName) {
		System.out.println("-".repeat(20) + " " + exampleName + "\n");
	}

	protected static void println(String name, Object... args) {
		String params = Arrays.toString(args);
		System.out.println(name + "= " + params.substring(1, params.length()-1));
	}

	protected static void println(Object value) {
		System.out.println(value);
	}
	
	protected static void print(Object value) {
		System.out.print(value);
	}

	protected static void footer(String exampleName) {
		System.out.println(" ");
	}

}
