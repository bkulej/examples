package pl.np.example.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CollectionExample extends Example {

	public static void main(String[] args) {
		
		list();
		
		List<Integer> x = Arrays.asList(2, 3, 5, 3, 1, 7);
		x.sort((a, b) -> b - a);
		// TODO test
		// Collections.list​(Enumeration<T> e)
	}

	@SuppressWarnings("unused")
	private static void list() {
		header("list");

		var muttableList = Arrays.asList(new String[] { "zenek", "ala", "grzesiek", null});
		muttableList = Arrays.asList("zenek", "grzesiek", "fiedia", null);
		println("MUTTABLE, NULL - Arrays.asList", muttableList);

		var immutableList = List.of(new String[] { "zenek", "ala", "grzesiek"});
		println("NOT MUTTABLE, NOT NULL - List.of", muttableList);

		var arrayList = new ArrayList<>();
		

		footer("list");
	}

}
