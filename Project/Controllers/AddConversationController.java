package Project.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.sql.*;
import javafx.event.ActionEvent;

import java.util.ArrayList;
import Project.Validator;

public class AddConversationController {
	Connection conn;


	@FXML
	private TextArea transcriptionField;

	@FXML
	private TextField startTimeField;

	@FXML
	private TextField endTimeField;

	@FXML
	private TextField levelField;

	@FXML
	private TextArea commentsField;

	@FXML
	private TextField participantsField;

	@FXML
	private Label infoLabel;

	void printError(String text) {
		infoLabel.setText("Error: " + text);
	}

	void printInfo(String text) {
		infoLabel.setText("Info: " + text);
	}

	@FXML
	void addConversation(ActionEvent event) throws SQLException {
		String transcription = transcriptionField.getText();
		if (transcription.isEmpty()) {
			printError("Należy wprowadzić transkrypcję");
			return;
		}

		Timestamp start;
		Timestamp end;
		try {
			start = Validator.getTimestamp(startTimeField.getText());
			end = Validator.getTimestamp(endTimeField.getText());
		}
		catch (Exception ex) {
			printError("Zły format daty lub godziny");
			return;
		}

		Integer level = null;
		if (!levelField.getText().isEmpty()) {
			try {
				level = Integer.parseInt(levelField.getText());
			}
			catch (Exception ex) {
				printError("Wprowadź poprawnie stopień niebezpieczeństwa");
				return;
			}
		}

		String comments = commentsField.getText();
		if (comments.isEmpty())
			comments = null;

		String participantsString = participantsField.getText();
		ArrayList<Integer> participants = new ArrayList<Integer>();

		if (participantsString.isEmpty())
			participants = null;
		else {
			String[] splitted = participantsString.split(", ");
			if (splitted.length == 1)
				splitted = splitted[0].split(" ");

			try {
				if (splitted.length == 1) {
					throw new Exception();
				}

				for (int iter = 0; iter < splitted.length; ++iter) {
					participants.add(Integer.parseInt(splitted[iter]));
				}
			}
			catch (Exception ex) {
				printError("Proszę poprawnie wprowadzić id uczestników");
				return;
			}
		}

		//check if participants exists
		if (participants != null && participants.size() != 0) {
			String statement = "SELECT COUNT(*) FROM obywatel WHERE id IN (?";
			for (int iter = 1; iter < participants.size(); ++iter) {
				statement = statement + ", ?";
			}
			statement = statement + ")";

			PreparedStatement stmt = conn.prepareStatement(statement);

			for (int iter = 0; iter < participants.size(); ++iter)
				stmt.setInt(iter + 1, participants.get(iter));

			ResultSet rs = stmt.executeQuery();
			rs.next();

			if (rs.getInt(1) != participants.size()) {
				printError("Część z podanych obywateli nie istnieje");
				return;
			}
		}


		PreparedStatement stmt = conn.prepareStatement(
				"INSERT INTO rozmowa (poczatek_rozmowy, koniec_rozmowy, " +
				"transkrypcja_rozmowy, stopien_niebezpieczenstwa, " +
				"uwagi_i_odstepstwa) VALUES (?, ?, ?, ?, ?) RETURNING id",
				Statement.RETURN_GENERATED_KEYS);

		int iter = 0;
		stmt.setTimestamp(++iter, start);
		stmt.setTimestamp(++iter, end);
		stmt.setString(++iter, transcription);
		if (level == null)
			stmt.setNull(++iter, Types.INTEGER);
		else
			stmt.setInt(++iter, level);
		stmt.setString(++iter, comments);

		if (stmt.executeUpdate() < 1) {
			printError("Nieznany błąd");
			return;
		}

		ResultSet rs = stmt.getGeneratedKeys();
		rs.next();
		int justAddedConversation = rs.getInt(1);

		if (participants == null || participants.size() == 0) {
			printInfo("Popranie dodano rozmowę");
			return;
		}

		String statement = "INSERT INTO obywatel_rozmowa (rozmowa_id, obywatel_id) " +
			"VALUES (?, ?)";
		for (iter = 1; iter < participants.size(); ++iter) {
			statement = statement + ", (?, ?)";
		}

		stmt = conn.prepareStatement(statement);

		for (iter = 0; iter < participants.size(); ++iter) {
			stmt.setInt(2 * iter + 1, justAddedConversation);
			stmt.setInt(2 * iter + 2, participants.get(iter));
		}

		int result = stmt.executeUpdate();

		if (result == participants.size())
			printInfo("Poprawnie dodano rozmowę i uczestników");
		else
			printError("Nieznany błąd podczas dodawania uczestników");



	}

	public void updateData(Connection conn) {
		this.conn = conn;
	}

}

