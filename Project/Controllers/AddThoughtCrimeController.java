package Project.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.sql.*;
import javafx.event.ActionEvent;

public class AddThoughtCrimeController {
	Connection conn;
	int policemanId;

	@FXML
	private TextField idField;

	@FXML
	private TextField levelField;

	@FXML
	private TextField denunciationIdField;

	@FXML
	private TextArea descriptionField;

	@FXML
	private Label infoLabel;

	public void updateData(int id, Connection conn) {
		this.policemanId = id;
		this.conn = conn;
	}
	@FXML
	void addThoughtCrime(ActionEvent event) throws SQLException {
		int citizenId;
		try {
			citizenId = Integer.parseInt(idField.getText());
		}
		catch (Exception ex) {
			printError("Wprowadź poprawny numer obywatela");
			return;
		}
		Integer level;
		try {
			if (levelField.getText().isEmpty())
				level = null;
			else {
				level = Integer.parseInt(levelField.getText());
				if (level < 0 || level > 10)
					throw new Exception();
			}
		}
		catch (Exception ex) {
			printError("Wprowadź poprawny poziom niebezpieczeństwa");
			return;
		}
		Integer denunciationId;
		try {
			if (denunciationIdField.getText().isEmpty())
				denunciationId = null;
			else {
				denunciationId = Integer.parseInt(
						denunciationIdField.getText());
				if (denunciationId < 1)
					throw new Exception();
			}
		}
		catch (Exception ex) {
			printError("Wprowadź poprawny numer donosu");
			return;
		}

		String desc = descriptionField.getText();
		if (desc.isEmpty()) {
			printError("Opis został pusty");
			return;
		}

		PreparedStatement stmt = conn.prepareStatement(
				"INSERT INTO myslozbrodnie (" +
				"obywatel_id, stopien_niebezpieczenstwa, " +
				"powiazany_donos, funkcjonariusz_id, opis) " +
				"VALUES (?, ?, ?, ?, ?)");

		int iter = 0;
		stmt.setInt(++iter, citizenId);
		if (level == null)
			stmt.setNull(++iter, Types.INTEGER);
		else
			stmt.setInt(++iter, level);
		if (denunciationId == null)
			stmt.setNull(++iter, Types.INTEGER);
		else
			stmt.setInt(++iter, denunciationId);
		stmt.setInt(++iter, policemanId);
		stmt.setString(++iter, desc);

		int result;
		try {
			result = stmt.executeUpdate();
		}
		catch (Exception ex) {
			printError("Obywatel nie istnieje");
			return;
		}
		if (result < 1) {
			printError("Nieznany błąd");
			return;
		}

		printInfo("Poprawnie dodano");
	}

	void printError(String msg) {
		infoLabel.setText("Error: " + msg);
	}

	void printInfo(String msg) {
		infoLabel.setText("Info: " + msg);
	}

}

