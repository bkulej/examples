package pl.np.example.java;

import java.text.MessageFormat;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

public class LocaleExample extends Example {

	public static void main(String[] args) {
		localeInfo();
		numberFormat();
		currencyFormat();
		percentFormat();
		dateTimeFormat();
		loadResources();
	}

	private static void localeInfo() {
		header("localeInfo");

		println("available locales", Arrays.toString(Locale.getAvailableLocales()));
		println("iso languages", Arrays.toString(Locale.getISOLanguages()));
		println("iso cauntries", Arrays.toString(Locale.getISOCountries()));

		Locale defaultLocale = Locale.getDefault();
		println("default", defaultLocale);
		println("default display", defaultLocale.getDisplayName());

		println("language", defaultLocale.getLanguage());
		println("language display", defaultLocale.getDisplayLanguage());

		println("country", defaultLocale.getCountry());
		println("country display", defaultLocale.getDisplayCountry());

		Locale enLocale = Locale.forLanguageTag("en-EN");
		println("Locale.forLanguageTag(\"en-EN\")", enLocale);

		// java -Duser.language=en -Duser.country=EN MainClass
		Locale.setDefault(enLocale);
		println("set en_EN locale", Locale.getDefault());

		footer("localeInfo");
	}

	private static void numberFormat() {
		header("numberFormat");
		double value = 1234567.89;

		Locale.setDefault(Locale.forLanguageTag("pl-PL"));

		NumberFormat formatter = NumberFormat.getNumberInstance();
		println("number 1234567.89 formated pl_PL", formatter.format(value));

		Locale.setDefault(Locale.forLanguageTag("en-GB"));
		formatter = NumberFormat.getNumberInstance();
		println("number 1234567.89 formated en_GB", formatter.format(value));

		formatter = NumberFormat.getNumberInstance(Locale.GERMANY);
		println("number 1234567.89 formated GERMANY", formatter.format(value));

		formatter = NumberFormat.getNumberInstance(Locale.forLanguageTag("en-US"));
		println("number 1234567.89 formated en_US", formatter.format(value));

		footer("numberFormat");
	}

	private static void currencyFormat() {
		header("currencyFormat");
		double value = 1234567.89;

		Locale.setDefault(Locale.forLanguageTag("pl-PL"));

		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		println("currency 1234567.89 formated pl_PL", formatter.format(value));

		Locale.setDefault(Locale.forLanguageTag("en-GB"));
		formatter = NumberFormat.getCurrencyInstance();
		println("currency 1234567.89 formated en_GB", formatter.format(value));

		formatter = NumberFormat.getCurrencyInstance(Locale.GERMANY);
		println("currency 1234567.89 formated GERMANY", formatter.format(value));

		formatter = NumberFormat.getCurrencyInstance(Locale.forLanguageTag("en-US"));
		println("currency 1234567.89 formated en_US", formatter.format(value));

		footer("currencyFormat");
	}

	private static void percentFormat() {
		header("percentFormat");
		double value = 1234.56;

		Locale.setDefault(Locale.forLanguageTag("pl-PL"));

		NumberFormat formatter = NumberFormat.getPercentInstance();
		println("percent 1234.56 formated pl_PL", formatter.format(value));

		Locale.setDefault(Locale.forLanguageTag("en-GB"));
		formatter = NumberFormat.getPercentInstance();
		println("percent 1234.56 formated en_GB", formatter.format(value));

		formatter = NumberFormat.getPercentInstance(Locale.GERMANY);
		println("percent 1234.56 formated GERMANY", formatter.format(value));

		formatter = NumberFormat.getPercentInstance(Locale.forLanguageTag("en-US"));
		println("percent 1234.56 formated en_US", formatter.format(value));

		footer("percentFormat");
	}

	private static void dateTimeFormat() {
		header("dateTimeFormat");
		
		// FULL, LONG format only for ZonedDateTime
		
		Locale.setDefault(Locale.forLanguageTag("pl-PL"));
		
		ZonedDateTime zonedDateTime = ZonedDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL);
		println("zonedDateTime FULL formated pl_PL", zonedDateTime.format(formatter));
		
		formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG);
		println("zonedDateTime LONG formated pl_PL", zonedDateTime.format(formatter));

		LocalDateTime localDateTime = LocalDateTime.now();
		formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
		println("localDateTime MEDIUM formated pl_PL", localDateTime.format(formatter));
		
		formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);
		println("localDateTime SHORT formated pl_PL", localDateTime.format(formatter));

		Locale.setDefault(Locale.forLanguageTag("en-GB"));
		formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
		println("localDateTime formated en_GB", formatter.format(localDateTime));

		formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).localizedBy(Locale.GERMANY);
		println("localDateTime formated GERMANY", formatter.format(localDateTime));

		formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).localizedBy(Locale.forLanguageTag("en-US"));
		println("localDateTime formated en_US", formatter.format(localDateTime));

		footer("dateTimeFormat");
	}
	
	private static void loadResources() {
		header("loadResources");
		
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
		
		footer("loadResources");
	}

}
