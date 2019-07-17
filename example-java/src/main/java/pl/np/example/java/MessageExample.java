package pl.np.example.java;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

public class MessageExample extends Example {

	public static void main(String[] args) {
		message();
		resources();
	}

	private static void message() {
		header("message");
		
		String template = "Simple template argInt={0} argStr={1} argDate={2}";
		println(MessageFormat.format(template, Integer.valueOf(10), "Test", new Date()));

		template = "Number standard format - {0, number} - {0, number, integer} - {0, number, currency} - {0, number, percent}";
		println(MessageFormat.format(template, Double.valueOf(1234.50)));

		template = "Date format - {0, date} - {0, date, short} - {0, date, medium} - {0, date, long} - {0, date, full}";
		println(MessageFormat.format(template, new Date()));
		
		template = "Time format - {0, time} - {0, time, short} - {0, time, medium} - {0, time, long} - {0, time, full}";
		println(MessageFormat.format(template, new Date()));
		
		template = "Number patern format - {0, number,#,##0.##}";
		println(MessageFormat.format(template, Double.valueOf(1234.534)));
		
		template = "Date patern format - {0, date, yyyy-MM-dd HH:mm}";
		println(MessageFormat.format(template, new Date()));
		
		template = "Choice patern format - {0} {0, choice, 0#plików|1#plik|1<pliki|4<plików}";
		println(MessageFormat.format(template, Integer.valueOf(0)));
		println(MessageFormat.format(template, Integer.valueOf(1)));
		println(MessageFormat.format(template, Integer.valueOf(2)));
		println(MessageFormat.format(template, Integer.valueOf(3)));
		println(MessageFormat.format(template, Integer.valueOf(4)));
		println(MessageFormat.format(template, Integer.valueOf(5)));
		println(MessageFormat.format(template, Integer.valueOf(6)));

		footer("message");
	}
	
	private static void resources() {
		header("resources");
		
		Locale.setDefault(Locale.forLanguageTag("pl-PL"));
		ResourceBundle bundle = ResourceBundle.getBundle("pl.np.learning.java.MessageExample");
		println("base bunddle name", bundle.getBaseBundleName());
		println("base bunddle locale", bundle.getLocale());				
		println("polish message1", MessageFormat.format(bundle.getString("message1"), new Date()));
		println("polish message2", Arrays.toString(bundle.getString("message2").split(",")));
		
		Locale.setDefault(Locale.forLanguageTag("en-GB"));
		bundle = ResourceBundle.getBundle("pl.np.learning.java.MessageExample");
		println("base bunddle name", bundle.getBaseBundleName());
		println("base bunddle locale", bundle.getLocale());
		println("polish message1", MessageFormat.format(bundle.getString("message1"), new Date()));
		println("polish message2", Arrays.toString(bundle.getString("message2").split(",")));
		
		footer("resources");
	}

}
