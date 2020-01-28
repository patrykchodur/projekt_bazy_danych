package Project.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.*;
import javafx.event.ActionEvent;

public class AssignJobController {
	Connection conn;

	@FXML
	private TextField citizenIdText;

	@FXML
	private TextField jobIdText;

	@FXML
	private Label infoLabel;

	void printError(String msg) {
		infoLabel.setText("Error: " + msg);
	}

	void printInfo(String msg) {
		infoLabel.setText("Info: " + msg);
	}

	@FXML
	void assignJobClicked(ActionEvent event) throws SQLException {
		int citizenId;
		int jobId;
		try {
			citizenId = Integer.parseInt(citizenIdText.getText());
			jobId = Integer.parseInt(jobIdText.getText());
		}
		catch (Exception ex) {
			printError("Proszę poprawnie wpisać id obywatela i pracy");
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
		int previousJobId = rs.getInt(1);
		String infoString = null;

		if (previousJobId > 0) {
			stmt = conn.prepareStatement(
					"Select nazwa FROM praca WHERE id = ?");
			stmt.setInt(1, previousJobId);

			rs = stmt.executeQuery();
			String previousJob;
			if (!rs.next()) 
				previousJob = "Nieznana";
			else
				previousJob = rs.getString(1);
			
			infoString = "Zmieniono pracę z " + previousJob + " na ";
		}
		else
			infoString = "Ustawiono pracę jako ";

		// check if new job exists and get name
		stmt = conn.prepareStatement(
				"Select nazwa FROM praca WHERE id = ?");
		stmt.setInt(1, jobId);

		rs = stmt.executeQuery();
		String newJob = null;
		if (!rs.next()) {
			printError("Podana praca nie istnieje");
			return;
		}
		else
			newJob = rs.getString(1);
		infoString = infoString + newJob;

		//actual update
		stmt = conn.prepareStatement(
				"UPDATE obywatel SET praca_id = ? WHERE id = ?");
		stmt.setInt(1, jobId);
		stmt.setInt(2, citizenId);

		int result = stmt.executeUpdate();
		if (!(result > 0)) {
			printError("Nieznany błąd");
		}

		printInfo(infoString);

	}

	public void updateData(Connection conn) {
		this.conn = conn;
	}

}

