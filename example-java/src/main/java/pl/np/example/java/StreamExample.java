package pl.np.example.java;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class StreamExample extends Example {

	public static void main(String[] args) {
		createStream();
		collectors();
	}

	private static void createStream() {
		header("createStream");

		final Stream<String> stringStream = Arrays.stream(new String[] { "ala", "gienek", "ziutek", "gosia" });
		println("stringStream", stringStream.sorted().collect(Collectors.joining("-")));

		final LongStream longStream = Arrays.stream(new long[] { 10L, 2L, 4L, 3L, -1L });
		println("longStream", longStream.average());

		final IntStream intStream = Arrays.stream(new int[] { 10, 2, 4, 3, -1 });
		println("intStream", intStream.filter(num -> num > 0).boxed().collect(Collectors.toList()));

		final DoubleStream doubleStream = Arrays.stream(new double[] { 10.6, 2.2, 4.3, 3, -1 });
		println("doubleStream", doubleStream.filter(num -> num > 0).sum());

		final var generated = Stream.generate(new Random()::nextInt).limit(10).collect(Collectors.toList());
		println("Stream.generate", generated);

		final var stream1 = Stream.of("str12", "str13", "str11");
		final var stream2 = Stream.of("str22", "str24", "str23", "str21");
		println("Stream.of");

		final var concated = Stream.concat(stream1, stream2).sorted().collect(Collectors.joining("-"));
		println("Stream.concat", concated);

		final var iterated1 = Stream.iterate("a", value -> value + "a").limit(10).collect(Collectors.joining("-"));
		println("Stream.iterate", iterated1);

		final var iterated2 = Stream.iterate(0, value -> value < 10, value -> value + 1).collect(Collectors.toList());
		println("Stream.iterate", iterated2);

		final Iterable<Path> iterable = FileSystems.getDefault().getRootDirectories();
		final var pathList = StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());
		println("StreamSupport.stream", pathList);
		
		println("\nMap to stream");
		Map.of("k1","v1", "k2", "v").entrySet().stream().forEach(Example::println);
		
		println("\nList to stream");
		List.of("e2","e3","e1").stream().sorted().forEach(Example::println);
		
		println("\nSet to stream");
		Set.of("e2","e3","e1").stream().sorted().forEach(Example::println);

		footer("createStream");
	}

	private static void collectors() {
		header("collectors");

		footer("collectors");
	}

}
