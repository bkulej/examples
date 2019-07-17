package pl.np.example.spring;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class ExampleApplication {

	private final Logger log = LoggerFactory.getLogger(ExampleApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ExampleApplication.class, args);
	}

	@Value("${example.spring.localhost.uri}")
	private String localhostUri;

	@EventListener({ ApplicationReadyEvent.class })
	public void runBrowser() {
		try {
			if (Desktop.isDesktopSupported()) {
				Desktop.getDesktop().browse(URI.create(localhostUri));
			} else {
				Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + localhostUri);
			}
			log.info("Browser opened on address {}", localhostUri);
		} catch (IOException e) {
			log.info("Cant open browser on address {}", localhostUri);
		}
	}
}
