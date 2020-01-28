package Project.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.*;
import javafx.event.ActionEvent;

public class CheckDenunciationController {
	Connection conn;

	@FXML
	private TextField denunciationId;

	@FXML
	private Label textField;

	@FXML
	void checkDenunciation(ActionEvent event) throws SQLException {
		int id;
		try {
			id = Integer.parseInt(denunciationId.getText());
		}
		catch (Exception ex) {
			textField.setText("Proszę prawidłowo wpisać numer donosu");
			return;
		}

		PreparedStatement stmt = conn.prepareStatement(
				"SELECT obywatel_skladajacy, data_zdarzenia, " +
				"miejsce_zdarzenia, opis_zdarzenia FROM donosy " +
				"WHERE id = ?");
		stmt.setInt(1, id);

		ResultSet rs = stmt.executeQuery();
		if (!rs.next()) {
			textField.setText("Nie znaleniono donosu");
			return;
		}

		int iter = 0;
		int citizenId = rs.getInt(++iter);
		String date = rs.getString(++iter);
		String place = rs.getString(++iter);
		String description = rs.getString(++iter);

		String text = "Obywatel składający: " + citizenId + "\n" +
			"Data: " + date + "\n" +
			"Miejsce: " + place + "\n" +
			"Opis zdarzenia: " + description;

		textField.setText(text);

	}

	public void updateData(Connection conn) {
		this.conn = conn;
	}


}

