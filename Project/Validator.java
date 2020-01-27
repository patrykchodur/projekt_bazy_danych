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

		return new Timestamp(year, month, day, hour, minutes, 0, 0);
	}

}
