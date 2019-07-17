package pl.np.example.java;

import java.io.IOException;

public class RuntimeExample extends Example {

	public static void main(String[] args) throws IOException {
		runtime();
	}

	private static void runtime() {
		header("runtime");

		var runtime = Runtime.getRuntime();
		println("runtime.availableProcessors", runtime.availableProcessors());
		println("runtime.freeMemory", runtime.freeMemory());
		println("runtime.maxMemory", runtime.maxMemory());
		println("runtime.totalMemory", runtime.totalMemory());

		footer("runtime");
	}

	@SuppressWarnings("unused")
	private static void exec() throws IOException {
		header("exec");

		var runtime = Runtime.getRuntime();
		var process = runtime.exec("notepad.exe");
		println("process.info", process.info());
		println("process.pid", process.pid());
		println("process.isAlive", process.isAlive());

		footer("exec");
	}

	@SuppressWarnings("unused")
	private static void processBuilder() throws IOException {
		header("processBuilder");

		var builder = new ProcessBuilder("notepad.exe");
		builder.start();

		footer("processBuilder");
	}

}
