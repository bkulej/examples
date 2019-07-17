package pl.np.example.java;

import java.util.List;
import java.util.Random;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LambdaExample extends Example {

	public static void main(String[] args) {
		functionalInterface();
		functionReference();
		variableRange();
		streams();
	}

	interface MyInterface {
		String test(int i);
	}

	public static void functionalInterface() {
		header("functionalInterface");

		MyInterface myInterface = (integer) -> Integer.toString(integer);
		println("myInterface.test(1234)", myInterface.test(123456789));

		UnaryOperator<String> unaryOperator = (string) -> string.substring(2);
		println("unaryOperator", unaryOperator.apply("unaryValue"));

		BinaryOperator<String> binaryOperator = (string1, string2) -> string1 + " " + string2;
		println("binaryOperator", binaryOperator.apply("stringValue1", "stringValue2"));

		Consumer<String> consumer = (string) -> System.out.println(string);
		consumer.accept("consumerValue");

		Supplier<String> supplier = () -> "supplierValue";
		println("supplier", supplier.get());

		Function<Integer, String> function = (integer) -> Integer.toString(integer);
		println("function", function.apply(123456));

		Predicate<String> predicate = (string) -> string.isEmpty();
		println("predicate", predicate.test("1234"));

		footer("functionalInterface");
	}

	public static void functionReference() {
		header("functionReference");

		Consumer<String> consumer = System.out::println;
		consumer.accept("consumer System.out::println");

		Function<Integer, String> function = Integer::toUnsignedString;
		println("function", function.apply(123456));
		
		Supplier<Integer> supplier = new Random()::nextInt;
		println("supplier", supplier.get());

		footer("functionReference");
	}

	public static void variableRange() {
		header("variableRange");

		final int i = 100000000;
		Function<Integer, String> function = (integer) -> Integer.toString(integer + i);
		println("function", function.apply(123456));

		footer("variableRange");
	}

	public static void streams() {
		header("streams");

		List<String> list = Stream.of(2, 2, 3, 3, 5, 5, 7, 8).distinct().map(String::valueOf).collect(Collectors.toList());
		println("function distinct", list);
		
		footer("streams");

	}

}
