package pl.np.example.java;

import java.util.Arrays;
import java.util.Base64;
import java.util.stream.Collectors;

public class StringExample extends Example {

	public static void main(String[] args) {
		trimStrip();
		compare();
		convertArrayString();
		convertBase64();
	}

	private static void trimStrip() {
		header("trimStrip()");
		String data = "   sdsd dfdf fdfd   \u2000";
		println("trim", "|" + data.trim() + "|");
		println("strip", "|" + data.strip() + "|");
		println("stripLeading", "|" + data.stripLeading() + "|");
		println("stripTrailing", "|" + data.stripTrailing() + "|");
		footer("trimSrtip()");
	}

	private static void compare() {
		header("compare()");
		println("compareToIgnoreCase", "ąaśćb".compareToIgnoreCase("ąaŚĆ"));
		println("equalsIgnoreCase", "ąaść".equalsIgnoreCase("ąaŚĆ"));
		footer("compare()");
	}

	private static void convertArrayString() {
		header("convertArrayString");
		
		byte[] bytes = "Any data string\n ąŚĆŃŻŹĆęńółŁę\n finish.data".getBytes();
		println("string.getBytes() -> new String(bytes)", new String(bytes));
		
		String[] lines = "Any data string\n ąŚĆŃŻŹĆęńółŁę\n finish.data".split("\\r?\\n");
		println("string -> lines", Arrays.asList(lines));
		
		String[] stringTable = new String[] { "zenek", "ala", "grzesiek", "jadwiga" };

		String result = Arrays.toString(stringTable);
		println("Arrays.toString(stringTable)", result);

		result = String.join(";", stringTable);
		println("String.join(\";\", stringTable)", result);

		int[] intTable = new int[] { 10, 2, 6, 4 };

		result = Arrays.toString(intTable);
		println("Arrays.toString(intTable)", result);

		result = Arrays.stream(intTable).mapToObj(i -> Integer.toString(i)).collect(Collectors.joining(";"));
		println("Arrays.stream(intTable).mapToObj(i -> Integer.toString(i)).collect(Collectors.joining(\";\")", result);

		result = Arrays.stream(intTable).mapToObj(Integer::toString).collect(Collectors.joining(";"));
		println("Arrays.stream(intTable).mapToObj(Integer::toString).collect(Collectors.joining(\";\"))", result);

		result = Arrays.stream(intTable).boxed().map(i -> i.toString()).collect(Collectors.joining(";"));
		println("Arrays.stream(intTable).boxed().map(i -> i.toString()).collect(Collectors.joining(\";\"))", result);

		int[][] deepIntTable = new int[][] { { 10, 2 }, { 2, 6 }, { 4, 3 } };
		println("Arrays.deepToString(deepIntTable)", Arrays.deepToString(deepIntTable));

		footer("convertArrayToString");
	}
	
	private static void convertBase64() {
		header("convertByteString");
		
		String string = new String("Anydatastring");
		
		byte[] bytes = Base64.getUrlDecoder().decode(string);
		println("Base64.getEncoder().encodeToString(string)", bytes);
		
		string = Base64.getEncoder().encodeToString(bytes);
		println("Base64.getEncoder().encodeToString(bytes)", string);

		footer("convertByteString");
	}

}
