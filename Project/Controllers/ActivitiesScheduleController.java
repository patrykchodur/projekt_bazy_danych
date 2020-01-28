package Project.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.sql.*;

public class ActivitiesScheduleController {

	@FXML
	private Label activitiesLabel;

	public void updateData(int id, Connection conn) throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = conn.prepareStatement(
					"SELECT a.opis_aktywnosci, a.obowiazkowa, " +
					"a.poczatek_aktywnosci, a.koniec_aktywnosci, " +
					"a.miejsce_aktywnosci FROM aktywnosc a WHERE " +
					"a.id IN (SELECT b.aktywnosc_id FROM " +
					"obywatel_aktywnosc b WHERE b.obywatel_id = ?)");
			stmt.setInt(1, id);

			rs = stmt.executeQuery();
		}
		catch (SQLException ex) {
			System.out.println(ex);
			activitiesLabel.setText("Brak aktywności do wyświetlenia");
			return;
		}

		if (!rs.next()) {
			activitiesLabel.setText("Brak aktywnosci do wyświetlenia");
			return;
		}

		int iter = 0;
		String desc = rs.getString(++iter);
		String obligatory = "Obowiązkowa: " + 
			(rs.getBoolean(++iter) ? "tak" : "nie");
		String start = rs.getString(++iter);
		String end = rs.getString(++iter);
		String place = rs.getString(++iter);

		String line = desc + ":\n" + obligatory +
			"\nPoczątek: " + start + "\nKoniec: " + end + "\n\n\n";

		while (rs.next()) {
			iter = 0;
			desc = rs.getString(++iter);
			obligatory = "Obowiązkowa: " + 
				(rs.getBoolean(++iter) ? "tak" : "nie");
			start = rs.getString(++iter);
			end = rs.getString(++iter);
			place = rs.getString(++iter);

			line = line + desc + ":\n" + obligatory +
				"\nPoczątek: " + start + "\nKoniec: " + end + "\n\n\n";
		}

		activitiesLabel.setText(line);




	}
}

