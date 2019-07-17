package pl.np.example.java;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class DateExample extends Example {

	public static void main(String[] args) throws Exception {
		dateCalendar();
		convertDateCalendar();
		dateTime();
		durationTime();
		periodDate();
		convertDateTime();
	}

	private static void dateCalendar() {
		header("dateCalendar");

		println("current date", new Date());

		Calendar calendar = new GregorianCalendar();
		println("calendar get date", calendar.getTime());

		println("calendar DAY_OF_MONTH", calendar.get(Calendar.DAY_OF_MONTH));
		// January = 0 !!!!!!!!!!!
		println("calendar MONTH", calendar.get(Calendar.MONTH) + 1);
		println("calendar YEAR", calendar.get(Calendar.YEAR));

		println("calendar HOUR_OF_DAY 24", calendar.get(Calendar.HOUR_OF_DAY));
		println("calendar MINUTE", calendar.get(Calendar.MINUTE));
		println("calendar SECOND", calendar.get(Calendar.SECOND));

		calendar.add(Calendar.DAY_OF_MONTH, 5);
		println("calendar add 5 DAY_OF_MONTH", calendar.getTime());

		calendar.add(Calendar.YEAR, -3);
		println("calendar sub 3 YEAR", calendar.getTime());

		calendar.set(Calendar.DAY_OF_MONTH, 8);
		println("calendar set 8 DAY_OF_MONTH", calendar.getTime());

		// Work only with Calendar !!!!!!
		println("calendar after now", calendar.after(new GregorianCalendar()));
		println("calendar before now", calendar.before(new GregorianCalendar()));

		footer("dateCalendar");
	}

	private static void convertDateCalendar() throws ParseException {
		header("convertDateCalendar");

		Calendar calendar = new GregorianCalendar(2012, 11, 22);
		Date date = calendar.getTime();
		println("convert Calendar to Date", date);
		calendar.setTime(date);
		println("set Date in Calendar", calendar.getTime());

		Timestamp timestamp = new Timestamp(date.getTime());
		println("convert Date to Timestamp", timestamp);
		date = new Date(timestamp.getTime());
		println("convert Timestamp to Date", timestamp);

		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL);
		println("convert locale pl_PL Date to FULL String", dateFormat.format(date));
		dateFormat = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);
		println("convert locale pl_PL Date to SHORT String", dateFormat.format(date));
		dateFormat = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM, Locale.ENGLISH);
		println("convert locale ENGLISH Date to MEDIUM String", dateFormat.format(date));

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		date = format.parse("2007-12-03 10:15:30");
		println("convert patern String to Date", date);
		String string = format.format(date);
		println("convert patern Date to String", string);

		timestamp = new Timestamp(format.parse("2007-12-03 10:15:30").getTime());
		println("convert patern String to Timestamp", timestamp);
		string = format.format(timestamp);
		println("convert patern Timestamp to String", string);

		footer("convertDateCalendar");
	}

	private static void dateTime() {
		header("dateTime");

		LocalDate localDate = LocalDate.now();
		LocalTime localTime = LocalTime.now();
		LocalDateTime localDateTime = LocalDateTime.now();
		ZonedDateTime zonedDateTime = ZonedDateTime.now();

		println("LocalDate.now()", localDate);
		println("LocalTime.now()", localTime);
		println("LocalDateTime.now()", localDateTime);
		println("ZonedDateTime.now()", zonedDateTime);

		println("LocalDate.of(2018, 1, 10)", LocalDate.of(2018, 1, 10));
		println("LocalTime.of(22, 11)", LocalTime.of(22, 11));
		println("LocalDateTime.of(2017, 11, 21, 15, 33, 35)", LocalDateTime.of(2017, 11, 21, 15, 33, 35));
		println("ZonedDateTime.of(2017, 11, 21, 15, 33, 35, 0, ZoneId.systemDefault()))",
				ZonedDateTime.of(2017, 11, 21, 15, 33, 35, 0, ZoneId.systemDefault()));

		println("LocalDate.parse", LocalDate.parse("2017-05-12"));
		println("LocalTime.parse", LocalTime.parse("21:11"));
		println("LocalDateTime.parse", LocalDateTime.parse("2007-12-03T10:15:30"));
		println("ZonedTime.parse", ZonedDateTime.parse("2017-11-21T15:33:35+01:00[Europe/Belgrade]"));

		footer("dateTime");
	}

	// Duration - measure machine-based TIME
	private static void durationTime() {
		header("durationTime");

		Instant start = Instant.now();
		Instant stop = start.plus(10, ChronoUnit.MINUTES).plus(Duration.ofHours(2)).plus(Period.ofDays(3));
		println("instant date start", start);
		println("instant date stop", stop);

		Duration duration = Duration.between(start, stop);
		println("duration in milis", duration.toMillis());
		println("duration in seconds", duration.toSeconds());
		println("duration in minutes", duration.toMinutes());
		println("duration in hours", duration.toHours());
		println("duration in days", duration.toDays());
		println("durations units", duration.getUnits());

		println("ChronoUnit.MILLIS beetwen", ChronoUnit.MILLIS.between(start, stop));

		footer("durationTime");
	}

	// Period - amount of time with DATE-based values (years, months, days)
	private static void periodDate() {
		header("periodDate");

		LocalDate start = LocalDate.now();
		LocalDate stop = start.plus(Period.ofDays(4)).minusDays(2);
		println("local date start", start);
		println("local date stop", stop);

		Period period = Period.between(start, stop);
		println("period in days", period.getDays());
		println("period in months", period.getMonths());
		println("period in years", period.getYears());
		println("period units", period.getUnits());

		footer("periodDate");
	}

	private static void convertDateTime() {
		header("convertDateTime");

		// FULL, LONG format only for ZonedDateTime

		LocalDateTime localDateTime = LocalDateTime.now();
		DateTimeFormatter shortDateTimeFormatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);
		DateTimeFormatter mediumDateTimeFormatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
		println("convert localized LocalDateTime to SHORT String", localDateTime.format(shortDateTimeFormatter));
		println("convert localized LocalDateTime to MEDIUM String", localDateTime.format(mediumDateTimeFormatter));

		ZonedDateTime zonedDateTime = ZonedDateTime.now();
		DateTimeFormatter longDateTimeFormatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG);
		DateTimeFormatter fullDateTimeFormatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL);
		println("convert localized ZonedDateTime to LONG String", zonedDateTime.format(longDateTimeFormatter));
		println("convert localized ZonedDateTime to FULL String", zonedDateTime.format(fullDateTimeFormatter));

		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		println("convert pattern String to LocalDate", LocalDate.parse("2017-05-12", dateFormatter));
		println("convert pattern String to LocalTime", LocalTime.parse("21:11", timeFormatter));
		println("convert pattern String to LocalDateTime",
				LocalDateTime.parse("2007-12-03 10:15:30", dateTimeFormatter));

		LocalDate localDate = LocalDate.now();
		LocalTime localTime = LocalTime.now();
		println("convert patern LocalDate to String", localDate.format(dateFormatter));
		println("convert patern LocalTime to String", localTime.format(timeFormatter));
		println("convert patern LocalDateTime to String", localDateTime.format(dateTimeFormatter));

		Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
		println("convert LocalDateTime to Date", localDateTime);

		localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		println("convert Date to LocalDateTime java 8", localDateTime);

		localDateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
		println("convert Date to LocalDateTime java 9", localDateTime);

		Timestamp timestamp = Timestamp.valueOf(localDateTime);
		println("convert Timestamp to LocalDateTime", timestamp);

		localDateTime = timestamp.toLocalDateTime();
		println("convert LocalDateTime to Timestamp", timestamp);

		footer("convertDateTime");
	}

}
