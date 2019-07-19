package pl.np.example.spring;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import pl.np.example.spring.util.DesktopUtil;

/**
 * 
 * @author bkulejewski
 *
 */
@SpringBootApplication
public class ExampleApplication {

	private final Logger log = LoggerFactory.getLogger(ExampleApplication.class);
	
	private boolean browserRun;
	private URI browserUri;
	

	public ExampleApplication(@Value("${application.browser.run}") boolean browserRun,
			@Value("${application.browser.uri}") URI browserUri) {
		super();
		this.browserRun = browserRun;
		this.browserUri = browserUri;
	}

	@EventListener({ ApplicationReadyEvent.class })
	public void runBrowser() {
		try {
			if (browserRun) {
				log.debug("Starting the browser with the uri '{}'", browserUri);
				DesktopUtil.openBrowser(browserUri);
			}
		} catch (Exception e) {
			log.info("Can't open browser with the uri '{}'", browserUri);
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(ExampleApplication.class, args);
	}
}
