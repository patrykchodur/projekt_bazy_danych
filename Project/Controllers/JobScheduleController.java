package Project.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.sql.*;
import java.time.LocalTime;

public class JobScheduleController {

	@FXML
	private Label jobScheduleText;

	public void updateData(int id, Connection conn) throws SQLException {
		String text;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(
					"SELECT p.nazwa, p.opis_obowiazkow, " +
					"p.poczatek_pracy, p.koniec_pracy, " +
					"p.dni_tygodnia FROM praca p WHERE " +
					"p.id = (SELECT o.praca_id FROM obywatel o " +
					"WHERE o.id = ?)");
			stmt.setInt(1, id);

			rs = stmt.executeQuery();
		}
		catch (SQLException ex) {
			jobScheduleText.setText("Obywatel nie posiada pracy");
			return;
		}

		if (!rs.next()) {
			jobScheduleText.setText("Obywatel nie posiada pracy");
			return;
		}

		int iter = 0;
		String jobName = rs.getString(++iter);
		String desc = rs.getString(++iter);
		String start = rs.getString(++iter);
		String end = rs.getString(++iter);
		int daysOfTheWeek = rs.getInt(++iter);


		String time = start + " - " + end;
		text = "Praca: " + jobName + ", opis: " + desc + "\n";
		iter = 1;
		text = text + "Poniedziałek: " + 
			((daysOfTheWeek & iter) > 0 ? time : "-") +
			"\n";
		iter = iter << 1;
		text = text + "Wtorek: " + 
			((daysOfTheWeek & iter) > 0 ? time : "-") +
			"\n";
		iter = iter << 1;
		text = text + "Środa: " + 
			((daysOfTheWeek & iter) > 0 ? time : "-") +
			"\n";
		iter = iter << 1;
		text = text + "Czwartek: " + 
			((daysOfTheWeek & iter) > 0 ? time : "-") +
			"\n";
		iter = iter << 1;
		text = text + "Piątek: " + 
			((daysOfTheWeek & iter) > 0 ? time : "-") +
			"\n";
		iter = iter << 1;
		text = text + "Sobota: " + 
			((daysOfTheWeek & iter) > 0 ? time : "-") +
			"\n";
		iter = iter << 1;
		text = text + "Niedziela: " + 
			((daysOfTheWeek & iter) > 0 ? time : "-") +
			"\n";

		jobScheduleText.setText(text);

	}

}

