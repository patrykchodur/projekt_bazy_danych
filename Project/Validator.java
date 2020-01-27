package Project;

import java.sql.*;

public class Validator {

	@SuppressWarnings("deprecation")
	static public Time getTime(String time) throws SQLException {
		String[] tab = time.split(":");

		int hour = Integer.parseInt(tab[0]);
		int minutes = Integer.parseInt(tab[1]);

		if (hour > 23 || hour < 0 || minutes > 59 || minutes < 0)
			throw new SQLException("Wrong time format");

		return new Time(hour, minutes, 0);
	}

	@SuppressWarnings("deprecation")
	static public Timestamp getTimestamp(String text) throws SQLException {
		String[] date_and_hour = text.split(" ");

		String[] tab = date_and_hour[1].split(":");

		int hour = Integer.parseInt(tab[0]);
		int minutes = Integer.parseInt(tab[1]);

		if (hour > 23 || hour < 0 || minutes > 59 || minutes < 0)
			throw new SQLException("Wrong time format");

		tab = date_and_hour[0].split("-");
		int day = Integer.parseInt(tab[0]);
		int month = Integer.parseInt(tab[1]);
		int year = Integer.parseInt(tab[2]);

		if (day > 31 || day < 1 ||
				month > 12 || month < 1 ||
				year < 1900 || year > 2500)
			throw new SQLException("Wrong date");

		return new Timestamp(year - 1900, month - 1, day, hour, minutes, 0, 0);
	}

	@SuppressWarnings("deprecation")
	static public Date getDate(String text) throws SQLException {
		String[] tab = text.split("-");
		if (tab.length != 3)
			throw new SQLException("Wrong date format");

		int day = Integer.parseInt(tab[0]);
		int month = Integer.parseInt(tab[1]);
		int year = Integer.parseInt(tab[2]);

		if (day > 31 || day < 1 ||
				month > 12 || month < 1 ||
				year < 1900 || year > 2500)
			throw new SQLException("Wrong date");

		return new Date(year - 1900, month - 1, day);
	}


}
