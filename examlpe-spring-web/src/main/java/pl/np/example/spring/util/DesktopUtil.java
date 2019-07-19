package pl.np.example.spring.util;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;

/**
 * 
 * @author bkulejewski
 *
 */
public class DesktopUtil {

	/**
	 * 
	 * @param uri
	 * @throws IOException
	 */
	public static void openBrowser(URI uri) throws IOException {
		if (uri == null) {
			throw new IllegalArgumentException("Uri can't be null");
		}
		if (Desktop.isDesktopSupported()) {
			Desktop.getDesktop().browse(uri);
		} else {
			Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + uri);
		}
	}

}
