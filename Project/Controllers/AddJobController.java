package Project.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.sql.*;
import javafx.event.ActionEvent;
import Project.Validator;

public class AddJobController {
	Connection conn;

	@FXML
	private TextField jobName;

	@FXML
	private ChoiceBox<String> ministryPicker;

	@FXML
	private TextArea jobDescriptionText;

	@FXML
	private TextField jobHeadId;

	@FXML
	private CheckBox volunteerJobCheck;

	@FXML
	private TextField startHour;

	@FXML
	private TextField finnishHour;

	@FXML
	private Label errorLabel;

	@FXML
	void addJob(ActionEvent event) throws SQLException {
		String name = jobName.getText();
		if (name.isEmpty()) {
			printError("Nazwa nie może być pusta");
			return;
		}
		PreparedStatement stmt = conn.prepareStatement(
				"SELECT * FROM Praca WHERE nazwa = ?");
		stmt.setString(1, name);
		ResultSet rs = stmt.executeQuery();

		if (rs.next()) {
			printError("Nazwa pracy musi być unikalna");
			return;
		}
		int headId = -1;
		if (!jobHeadId.getText().isEmpty()) {
			headId = Integer.parseInt(jobHeadId.getText());
		}
		boolean volunteer = volunteerJobCheck.isSelected();
		String desc = jobDescriptionText.getText();

		Time start;
		Time end;
		try {
			start = Validator.getTime(startHour.getText());
			end = Validator.getTime(finnishHour.getText());
		}
		catch (Exception ex) {
			printError("Zły format godziny rozpoczęcia lub końca pracy");
			return;
		}

		String ministry = ministryPicker.getValue();
		if (ministry == null) {
			printError("Nie wybrano ministerstwa");
			return;
		}

		stmt = conn.prepareStatement(
				"INSERT INTO Praca (nazwa, ministerstwo_id, " +
				"opis_obowiazkow, naczelnik_id, praca_spoleczna, " +
				"poczatek_pracy, koniec_pracy) " +
				"SELECT ?, m.id, ?, ?, ?, ?, ? FROM Ministerstwo m " +
				"WHERE m.nazwa_ministerstwa = ?");

		int iter = 0;
		stmt.setString(++iter, name);
		stmt.setString(++iter, jobDescriptionText.getText());
		if (headId < 0)
			stmt.setNull(++iter, Types.INTEGER);
		else 
			stmt.setInt(++iter, headId);
		stmt.setBoolean(++iter, volunteer);
		stmt.setTime(++iter, start);
		stmt.setTime(++iter, end);
		stmt.setString(++iter, ministryPicker.getValue());

		if (stmt.executeUpdate() == 1)
			errorLabel.setText("Poprawnie dodano stanowisko");
		else
			printError("Wystąpił nieznany błąd");
	}

	void printError(String error) {
		errorLabel.setText("Error: " + error);
	}

	public void updateData(Connection conn) throws SQLException {
		this.conn = conn;
		Statement stmt = conn.createStatement();

		ResultSet rs = stmt.executeQuery(
				"SELECT nazwa_ministerstwa FROM Ministerstwo");

		ministryPicker.getItems().clear();
		while (rs.next())
			ministryPicker.getItems().add(rs.getString(1));
	}

	@FXML
	void initialize() {

	}

}

