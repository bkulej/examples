package pl.np.example.java;

import java.lang.System.Logger;
import java.lang.System.Logger.Level;

public class SystemExample extends Example {

	public static void main(String[] args) {
		envirementAndProperties();
		logger();
	}

	private static void envirementAndProperties() {
		header("envirementAndProperties");

		println("System.getenv");
		System.getenv().entrySet().stream().forEach(Example::println);

		println("\nSystem.getProperties");
		System.getProperties().entrySet().stream().forEach(Example::println);

		println("");
		println("user.name", System.getProperty("user.name"));
		println("user.home", System.getProperty("user.home"));
		println("user.dir", System.getProperty("user.dir"));
		println("user.language", System.getProperty("user.language"));
		println("user.country", System.getProperty("user.country"));
		println("user.dir", System.getProperty("user.dir"));
		println("file.encoding", System.getProperty("file.encoding"));
		println("os.version", System.getProperty("os.version"));
		println("file.separator", System.getProperty("file.separator"));
		println("java.version", System.getProperty("java.version"));
		println("java.io.tmpdir", System.getProperty("java.io.tmpdir"));

		footer("envirementAndProperties");
	}

	@SuppressWarnings("unused")
	private static void jni() {
		header("jni");

		System.load("c:\\library.dll");
		System.loadLibrary("library");

		footer("jni");
	}

	private static void logger() {
		header("logger");

		Logger log = System.getLogger(SystemExample.class.getName());
		log.log(Level.INFO, "Test log");

		footer("logger");
	}

}
