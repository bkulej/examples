package pl.np.example.java;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.StreamSupport;

public class FilesExample extends Example {

	public static void main(String[] args) throws Exception {
		pathOperations();
		fileOutputInputStream();
		fileWriterReader();
		fileAttributes();
		fileOperatios();
		directoryOperations();
		fileSystemStorage();
	}

	private static void pathOperations() throws IOException {
		header("pathOperations");

		println("Paths.get", Paths.get("\\Windows\\Speech"));

		println("\"\\Windows\\Speech\".toString()", Paths.get("\\Windows\\Speech").toString());
		println("\"\\Windows\\Speech\".toUri()", Paths.get("\\Windows\\Speech").toUri());
		println("\"\\Windows\\Speech\".toAbsolutePath()", Paths.get("\\Windows\\Speech").toAbsolutePath());
		println("\"\\Windows\\Speech\".isAbsolute()", Paths.get("\\Windows\\Speech").isAbsolute());
		println("\"C:\\Windows\\Speech\".isAbsolute()", Paths.get("C:\\Windows\\Speech").isAbsolute());

		println("Symbolic Link - toRealPath()", Paths.get("\\Windows\\Speech").toRealPath());

		println("\"\\Windows\\Speech\".getFileName()", Paths.get("\\Windows\\Speech").getFileName());
		println("\"\\Windows\\Speech\".getParent()", Paths.get("\\Windows\\Speech").getParent());
		println("\"\\Windows\\Speech\".getRoot()", Paths.get("\\Windows\\Speech").getRoot());

		println("\"\\Windows\\Speech\".getNameCount()", Paths.get("\\Windows\\Speech").getNameCount());
		println("\"\\Windows\\Speech\".getName(0)", Paths.get("\\Windows\\Speech").getName(0));
		println("\"\\Windows\\Speech\".getName(1)", Paths.get("\\Windows\\Speech").getName(1));

		println("\"\\Windows\\Speech\\Common\\en-US\".subpath(1,3)",
				Paths.get("\\Windows\\Speech\\Common\\en-US").subpath(1, 3));
		println("\"\\Windows\\Speech\\Common\".startsWith(\\Windows)",
				Paths.get("\\Windows\\Speech\\Common").startsWith("\\Windows"));
		println("\"\\Windows\\Speech\\Common\".endsWith(Common)",
				Paths.get("\\Windows\\Speech\\Common").endsWith("Common"));

		println("\"\\Windows\\Speech\".resolve(Common)", Paths.get("\\Windows", "Speech").resolve("Common"));
		println("\"\\Windows\\Speech\\Common\".resolveSibling(Engines)",
				Paths.get("\\Windows\\Speech\\Common").resolveSibling("Engines"));
		println("\"\\Windows\\Speech\".relativize(\\Users)",
				Paths.get("\\Windows\\Speech").relativize(Paths.get("\\Users")));
		println("\"\\Windows\\..\\Windows\\Speech\\Common\".normalize()",
				Paths.get("\\Windows\\..\\Windows\\Speech\\Common").normalize());

		footer("pathOperations");
	}

	private static void fileOutputInputStream() throws IOException {
		header("fileOutputInputStream");

		final var path = Paths.get("./stream.txt");
		var string = "Data file content\nąśćżłóŁŚŃŻŹŁ\nFile end1\naaa";
		var data = string.getBytes();

		Files.write(path, data);
		println("Files.write bytes", string);

		Files.write(path, Arrays.asList(string.split("\\r?\\n")));
		println("Files.write collection", string);

		try (final var outputStream = Files.newOutputStream(path, StandardOpenOption.CREATE);
				final var bufferedOutputStream = new BufferedOutputStream(outputStream, 8192)) {
			bufferedOutputStream.write(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
		println("bufferedOutputStream.write", new String(data));

		try {
			data = Files.readAllBytes(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
		println("\nFiles.readAllBytes", new String(data));

		try (final var inputStream = Files.newInputStream(path)) {
			data = inputStream.readAllBytes();
		} catch (IOException e) {
			e.printStackTrace();
		}
		println("inputStream.readAllBytes", new String(data));

		println("bufferedInputStream.read");
		try (final var inputStream = Files.newInputStream(path);
				final var bufferedInputStream = new BufferedInputStream(inputStream, 8192)) {
			while (bufferedInputStream.available() > 0) {
				print(bufferedInputStream.read() + ",");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		println("\nbufferedInputStream.transferTo");
		try (final var inputStream = Files.newInputStream(path);
				final var bufferedInputStream = new BufferedInputStream(inputStream, 8192)) {
			bufferedInputStream.transferTo(System.out);
		} catch (IOException e) {
			e.printStackTrace();
		}

		println("\nFiles.copy to stream");
		Files.copy(path, System.out);

		Files.deleteIfExists(path);
		footer("fileOutputInputStream");
	}

	private static void fileWriterReader() throws IOException {
		header("fileWriterReader");

		var data = "Data file content\nąśćżłóŁŚŃŻŹŁ\nFile end\n";
		var path = Paths.get("./writer.txt");

		Files.writeString(path, data);
		println("Files.writeString", data);

		Files.write(path, Arrays.asList(data.split("\\r?\\n")));
		println("Files.write collection", data);

		try (final var writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8, StandardOpenOption.CREATE)) {
			writer.write(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
		println("writer.write", data);

		println("Files.readString");
		try {
			println(Files.readString(path));
		} catch (IOException e) {
			e.printStackTrace();
		}

		println("Files.readAllLines");
		try {
			Files.readAllLines(path).stream().forEach(FilesExample::println);
		} catch (IOException e) {
			e.printStackTrace();
		}

		println("\nFiles.lines");
		try (final var lines = Files.lines(path, StandardCharsets.UTF_8)) {
			lines.forEach(FilesExample::println);
		} catch (IOException e) {
			e.printStackTrace();
		}

		println("\nFiles.newBufferedReader");
		try (final var reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
			reader.lines().forEach(FilesExample::println);
		} catch (IOException e) {
			e.printStackTrace();
		}

		Files.deleteIfExists(path);
		footer("fileWriterReader");
	}

	private static void fileAttributes() throws IOException {
		header("fileAttributes");

		final var path = Paths.get("./source.txt");
		Files.deleteIfExists(path);
		Files.createFile(path);

		println("Files.isDirectory", Files.isDirectory(path));
		println("Files.isRegularFile", Files.isRegularFile(path));
		println("Files.isSymbolicLink", Files.isSymbolicLink(path));
		println("Files.isHidden", Files.isHidden(path));
		println("Files.isReadable", Files.isReadable(path));
		println("Files.isWritable", Files.isWritable(path));
		println("Files.getLastModifiedTime", Files.getLastModifiedTime(path).toInstant());
		println("Files.getOwner", Files.getOwner(path).getName());

		Files.setLastModifiedTime(path, FileTime.from(Instant.now().minus(10, ChronoUnit.HOURS)));
		println("Files.setLastModifiedTime", Files.getLastModifiedTime(path).toInstant());

		Files.setAttribute(path, "dos:hidden", true);
		println("Files.setAttribute(dos:hidden)", Files.isHidden(path));

		Files.setOwner(path, Files.getOwner(path));
		println("Files.setOwner", Files.getOwner(path).getName());

		final var attributes = Files.readAttributes(path, BasicFileAttributes.class);
		println("basicFileAttributes.isDirectory", attributes.isDirectory());
		println("basicFileAttributes.isRegularFile", attributes.isRegularFile());
		println("basicFileAttributes.isSymbolicLink", attributes.isSymbolicLink());
		println("basicFileAttributes.creationTime", attributes.creationTime());
		println("basicFileAttributes.lastAccessTime", attributes.lastAccessTime());
		println("basicFileAttributes.lastModifiedTime", attributes.lastModifiedTime());
		println("basicFileAttributes.size", attributes.size());

		Files.deleteIfExists(path);
		footer("fileAttributes");
	}

	private static void fileOperatios() throws IOException {
		header("fileOperatios");

		final var srcPath = Paths.get("./source.txt");
		final var dstPath = Paths.get("./destination.txt");

		Files.deleteIfExists(srcPath);
		Files.deleteIfExists(dstPath);

		Files.createFile(srcPath);
		println("Files.createFile", srcPath);

		var tmpPath = Files.createTempFile("prefix_", ".tst");
		println("Files.createTempFile", tmpPath);
		Files.delete(tmpPath);

		final var exists = Files.exists(srcPath);
		println("Files.exists", srcPath, exists);

		final var notExists = Files.notExists(dstPath);
		println("Files.notExists", dstPath, notExists);

		Files.copy(srcPath, dstPath);
		println("Files.copy", srcPath.getFileName(), dstPath.getFileName());

		final var sameFile = Files.isSameFile(srcPath, dstPath);
		println("Files.isSameFile", srcPath.getFileName(), dstPath.getFileName(), sameFile);

		Files.move(srcPath, dstPath, StandardCopyOption.REPLACE_EXISTING);
		println("Files.move", srcPath.getFileName(), dstPath.getFileName());

		Files.deleteIfExists(srcPath);
		println("Files.deleteIfExists", srcPath.getFileName());

		Files.walk(dstPath).sorted(Comparator.reverseOrder()).map(Path::toFile).forEach(File::delete);
		println("Files.delete not empty dir", dstPath.getFileName());

		footer("fileOperatios");
	}

	private static void directoryOperations() throws IOException {
		header("directoryOperations");

		var tmpDir = Paths.get("./my_dir");
		Files.createDirectories(tmpDir);
		println("Files.createDirectories", tmpDir);
		Files.delete(tmpDir);

		tmpDir = Files.createTempDirectory("prefix_");
		println("Files.createTempDirectory", tmpDir);
		Files.delete(tmpDir);

		final var dir = Paths.get("./src/");

		println("Files.find *.java", dir);
		Files.find(dir, Integer.MAX_VALUE, (path, attr) -> path.toFile().getName().endsWith(".java"))
				.forEach(Example::println);

		println("\nFiles.walk", dir);
		Files.walk(dir, Integer.MAX_VALUE).filter(Files::isRegularFile).forEach(Example::println);

		println("\nFiles.walkFileTree", dir);
		Files.walkFileTree(dir, Collections.emptySet(), 2, new FileVisitor<Path>() {
			@Override
			public FileVisitResult preVisitDirectory(Path path, BasicFileAttributes attrs) throws IOException {
				println("preVisitDirectory", path);
				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
				println("visitFile", path);
				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult visitFileFailed(Path path, IOException exc) throws IOException {
				println("visitFileFailed", path);
				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult postVisitDirectory(Path path, IOException exc) throws IOException {
				println("postVisitDirectory", path);
				return FileVisitResult.CONTINUE;
			}
		});

		Path srcPath = Path.of("src");
		Path destPath = Path.of("src_tmp");
		println("\nCopy directory", srcPath + " -> " + destPath);
		Files.walk(srcPath).forEachOrdered(currPath -> {
			try {
				Files.copy(currPath, destPath.resolve(srcPath.relativize(currPath)));
			} catch (IOException e) {
				throw new UncheckedIOException(e);
			}
		});

		println("Delete directory", destPath);
		Files.walk(destPath).sorted(Comparator.reverseOrder()).map(Path::toFile).forEach(File::delete);

		footer("directoryOperations");
	}

	private static void fileSystemStorage() throws IOException {
		header("fileSystemStorage");

		var fileSystem = FileSystems.getDefault();
		println("fileSystem.isOpen", fileSystem.isOpen());
		println("fileSystem.isReadOnly", fileSystem.isReadOnly());
		println("fileSystem.getSeparator", fileSystem.getSeparator());

		println("fileSystem.getFileStores");
		for (var fileStore : fileSystem.getFileStores()) {
			println("");
			println("fileStore", fileStore.toString());
			println("fileStore.getBlockSize", fileStore.getBlockSize());
			println("fileStore.getTotalSpace", fileStore.getTotalSpace());
			println("fileStore.getUnallocatedSpace", fileStore.getUnallocatedSpace());
			println("fileStore.getUsableSpace", fileStore.getUsableSpace());
			println("fileStore.type", fileStore.type());
		}

		println("\nfileSystem.getRootDirectories");
		StreamSupport.stream(fileSystem.getRootDirectories().spliterator(), false).forEach(Example::println);

		footer("fileSystemStorage");
	}

}
