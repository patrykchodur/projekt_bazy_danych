package Project.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.sql.*;
import Project.Validator;
import javafx.event.ActionEvent;

public class AddDenunciationController {
	Connection conn;
	int id;

	@FXML
	private TextArea descriptionText;

	@FXML
	private TextField placeText;

	@FXML
	private TextField timeText;

	@FXML
	private Label infoLabel;

	@FXML
	void addDenunciation(ActionEvent event) throws SQLException {
		String description = descriptionText.getText();
		if (description.isEmpty()) {
			printError("Pole z opisem nie może być puste");
			return;
		}

		String place = placeText.getText();
		if (place.isEmpty()) {
			printError("Należy podać miejsce zdarzenia");
			return;
		}

		Timestamp date;
		try {
			date = Validator.getTimestamp(timeText.getText());
		}
		catch (Exception ex) {
			printError("Błędnie wypełniono czas zdarzenia");
			return;
		}

		PreparedStatement stmt = conn.prepareStatement(
				"INSERT INTO donosy (" +
				"obywatel_skladajacy, data_zdarzenia, " +
				"miejsce_zdarzenia, opis_zdarzenia) " +
				"VALUES (?, ?, ?, ?)");

		int iter = 0;
		stmt.setInt(++iter, id);
		stmt.setTimestamp(++iter, date);
		stmt.setString(++iter, place);
		stmt.setString(++iter, description);

		stmt.executeUpdate();

		printInfo("Pomyślnie dodano");

	}

	void printError(String msg) {
		infoLabel.setText("Error: " + msg);
	}

	void printInfo(String msg) {
		infoLabel.setText("Info: " + msg);
	}

	public void updateData(int id, Connection conn) {
		this.id = id;
		this.conn = conn;
	}


}

