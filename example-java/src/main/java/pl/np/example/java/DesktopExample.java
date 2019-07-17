package pl.np.example.java;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Paths;

public class DesktopExample extends Example {

	public static void main(String[] args) throws IOException {
		operations();
	}

	private static void operations() throws IOException {
		header("operations");

		println("Desktop.isDesktopSupported", Desktop.isDesktopSupported());
		if (!Desktop.isDesktopSupported()) {
			return;
		}
		Desktop desktop = Desktop.getDesktop();

		println("desktop.edit supported", desktop.isSupported(Desktop.Action.EDIT));
		desktop.edit(Paths.get("pom.xml").toFile());

		println("desktop.browse supported", desktop.isSupported(Desktop.Action.BROWSE));
		if (Desktop.isDesktopSupported()) {
			desktop.browse(URI.create("http://www.onet.pl"));
		} else {
			Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler http:\\www.onet.pl");
		}

		println("desktop.open supported", desktop.isSupported(Desktop.Action.OPEN));
		desktop.open(Paths.get("src/main/resources/grafika.png").toFile());

		println("desktop.print supported", desktop.isSupported(Desktop.Action.PRINT));
		desktop.print(Paths.get("src/main/resources/grafika.png").toFile());

		println("desktop.mail supported", desktop.isSupported(Desktop.Action.MAIL));
		desktop.mail(URI.create("mailto:john@example.com?subject=Hello%20World"));

		footer("operations");
	}

}
