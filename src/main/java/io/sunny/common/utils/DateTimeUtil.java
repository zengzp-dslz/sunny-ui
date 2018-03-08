package io.sunny.common.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {
	private static final DateTimeFormatter timestamp = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
	private static final DateTimeFormatter yyyyMMddHHmmss = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
	private static final DateTimeFormatter yyyyMMdd = DateTimeFormatter.ofPattern("yyyyMMdd");
	private static final DateTimeFormatter HHmmss = DateTimeFormatter.ofPattern("HHmmss");
	private static final DateTimeFormatter chinese = DateTimeFormatter.ofPattern("yyyy年MM月dd日HH:mm:ss");

	private DateTimeUtil() {
	}

	public static String currentDate() {
		return LocalDate.now().format(yyyyMMdd);
	}

	public static String currentTime() {
		return LocalTime.now().format(HHmmss);
	}

	public static String currentDateTime() {
		return LocalDateTime.now().format(yyyyMMddHHmmss);
	}

	public static String currentDateTime(String pattern) {
		return LocalDateTime.now().format(DateTimeFormatter.ofPattern(pattern));
	}

	public static class DateTimeUnit {
		private String date;
		private String time;

		public DateTimeUnit(String date, String time) {
			this.date = date;
			this.time = time;
		}

		public String getDate() {
			return date;
		}

		public String getTime() {
			return time;
		}

		@Override
		public String toString() {
			return date + time;
		}
	}

	public static DateTimeUnit currentDateTimeUnit() {
		LocalDateTime dateTime = LocalDateTime.now();
		return new DateTimeUnit(dateTime.toLocalDate().format(yyyyMMdd), dateTime.toLocalTime().format(HHmmss));
	}

	public static boolean isShortDate(String date) {
		try {
			yyyyMMdd.parse(date);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public static boolean isShortTime(String time) {
		try {
			HHmmss.parse(time);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public static boolean isShortDateTime(String datetime) {
		try {
			yyyyMMddHHmmss.parse(datetime);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public static boolean isDateFormat(String date, String format) {
		try {
			DateTimeFormatter fmt = DateTimeFormatter.ofPattern(format);
			fmt.parse(date);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public static int daysBetween(String date1, String date2) {
		LocalDate d1 = LocalDate.parse(date1, yyyyMMdd);
		LocalDate d2 = LocalDate.parse(date2, yyyyMMdd);
		if (d2.isAfter(d1)) {
			return d1.until(d2).getDays();
		} else {
			return d2.until(d1).getDays();
		}
	}

	public static String computeDate(String date, long days) {
		return computeDate(date, days, "yyyyMMdd");
	}

	public static String computeDate(String date, long days, String outputFormat) {
		LocalDate localDate = LocalDate.parse(date, yyyyMMdd);
		localDate = localDate.plusDays(days);
		return localDate.format(DateTimeFormatter.ofPattern(outputFormat));
	}

	public static String computeDateTime(String dateTime, long days, long hours, long minuts, long seconds) {
		return computeDateTime(dateTime, days, hours, minuts, seconds, "yyyyMMddHHmmss");
	}

	public static String computeDateTime(String dateTime, long days, long hours, long minuts, long seconds,
			String outputFormat) {
		LocalDateTime localDateTime = LocalDateTime.parse(dateTime, yyyyMMddHHmmss);
		localDateTime = localDateTime.plusDays(days);
		localDateTime = localDateTime.plusHours(hours);
		localDateTime = localDateTime.plusMinutes(minuts);
		localDateTime = localDateTime.plusSeconds(seconds);
		return localDateTime.format(DateTimeFormatter.ofPattern(outputFormat));
	}

	public static String minusDateTime(String dateTime, long days, String outputFormat) {
		LocalDateTime localDateTime = LocalDateTime.parse(dateTime, yyyyMMddHHmmss);
		localDateTime = localDateTime.minusDays(days);
		return localDateTime.format(DateTimeFormatter.ofPattern(outputFormat));
	}

	public static boolean isDateTimeExpiredNow(String datetime) {
		return LocalDateTime.parse(datetime, yyyyMMddHHmmss).isBefore(LocalDateTime.now());
	}

	public static String timestamp() {
		return LocalDateTime.now().format(timestamp);
	}

	public static String chineseDateTime(String dateTime) {
		return LocalDateTime.parse(dateTime, yyyyMMddHHmmss).format(chinese);
	}

	public static String chineseDateTime() {
		return chineseDateTime(currentDateTime());
	}

	public static String unixTime2DateTime(String unixTime) {
		return LocalDateTime.ofInstant(Instant.ofEpochSecond(Long.parseLong(unixTime)), ZoneId.systemDefault())
				.format(yyyyMMddHHmmss);
	}
}
