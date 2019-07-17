package pl.np.example.java;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipExample extends Example {

	public static void main(String[] args) throws IOException {
		header("main");
		Path srcPath = Path.of("src");
		Path destPath = Files.createTempDirectory("zip_");
		Path zipPath = Path.of(destPath.toString(), "test.zip");
		try {
			println("Zip to file", zipPath);
			zip(zipPath, srcPath);
			println("Unzip file", zipPath);
			unzip(zipPath, destPath);
		} finally {
			println("Delete tmp directory", destPath);
			Files.walk(destPath).sorted(Comparator.reverseOrder()).map(Path::toFile).forEach(File::delete);
		}
		footer("main");
	}

	private static void zip(final Path zipPath, final Path baseDir, final Path... subDirs) throws IOException {
		final Path[] tmpSubDirs = subDirs.length > 0 ? subDirs : new Path[] { Paths.get("") };
		try (final ZipOutputStream zipOutputStream = new ZipOutputStream(
				Files.newOutputStream(zipPath, StandardOpenOption.CREATE))) {
			for (Path subDir : tmpSubDirs) {
				Path srcDir = baseDir.resolve(subDir);
				for (Path path : Files.walk(srcDir).collect(Collectors.toList())) {
					String entryName = baseDir.relativize(path).toString();
					if (entryName.isEmpty()) {
						// Skip main directory
					} else if (Files.isDirectory(path)) {
						zipOutputStream.putNextEntry(new ZipEntry(entryName + "/"));
						zipOutputStream.closeEntry();
					} else {
						zipOutputStream.putNextEntry(new ZipEntry(entryName));
						Files.copy(path, zipOutputStream);
						zipOutputStream.closeEntry();
					}
				}
			}
		}
	}

	private static void unzip(final Path zipPath, final Path destPath) throws IOException {
		try (final ZipInputStream zipInputStream = new ZipInputStream(Files.newInputStream(zipPath))) {
			ZipEntry zipEntry = zipInputStream.getNextEntry();
			while (zipEntry != null) {
				Path filePath = Paths.get(destPath.toString(), zipEntry.getName());
				if (zipEntry.isDirectory()) {
					Files.createDirectories(filePath);
				} else {
					Files.copy(zipInputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
				}
				zipInputStream.closeEntry();
				zipEntry = zipInputStream.getNextEntry();
			}
		}
	}

}
