package Project.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.*;
import javafx.event.ActionEvent;

public class AssignActivityController {
	Connection conn;

	@FXML
	private TextField citizenIdText;

	@FXML
	private TextField activityIdText;

	@FXML
	private Label infoLabel;

	void printError(String msg) {
		infoLabel.setText("Error: " + msg);
	}

	void printInfo(String msg) {
		infoLabel.setText("Info: " + msg);
	}

	@FXML
	void assignActivityClicked(ActionEvent event) throws SQLException {
		int citizenId;
		int activityId;
		try {
			citizenId = Integer.parseInt(citizenIdText.getText());
			activityId = Integer.parseInt(activityIdText.getText());
		}
		catch (Exception ex) {
			printError("Proszę poprawnie wpisać id obywatela i aktywności");
			return;
		}

		PreparedStatement stmt = conn.prepareStatement(
				"SELECT praca_id FROM obywatel WHERE id = ?");

		stmt.setInt(1, citizenId);
		ResultSet rs = stmt.executeQuery();
		if (!rs.next()) {
			printError("Nie znaleziono obywatela");
			return;
		}

		stmt = conn.prepareStatement(
				"SELECT opis_aktywnosci FROM aktywnosc WHERE id = ?");
		stmt.setInt(1, activityId);
		rs = stmt.executeQuery();
		if (!rs.next()) {
			printError("Nie znaleziono aktywnosci");
			return;
		}
		String desc = rs.getString(1);

		stmt = conn.prepareStatement(
				"INSERT INTO obywatel_aktywnosc (" +
				"aktywnosc_id, obywatel_id) VALUES (?, ?)");
		stmt.setInt(1, activityId);
		stmt.setInt(2, citizenId);
		
		int result = stmt.executeUpdate();
		if (!(result > 0))
			printError("Nieznany błąd");
		else
			printInfo("Pomyślnie zapisano obywatela " + citizenId +
					" na " + desc);
	}

	public void updateData(Connection conn) {
		this.conn = conn;
	}

}

