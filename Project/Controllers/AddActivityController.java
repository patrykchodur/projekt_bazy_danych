package Project.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

import java.sql.*;
import Project.Validator;
import javafx.event.ActionEvent;

public class AddActivityController {

	Connection conn;

	@FXML
	private TextArea activityDescriptionText;

	@FXML
	private CheckBox isObligatoryCheckBox;

	@FXML
	private TextField startTime;

	@FXML
	private TextField endTime;

	@FXML
	private TextField activityPlaceText;

	@FXML
	private Label infoLabel;

	void printError(String text) {
		infoLabel.setText("Error: " + text);
	}

	void printInfo(String text) {
		infoLabel.setText("Info: " + text);
	}

	@FXML
	void addActivity(ActionEvent event) throws SQLException {
		Timestamp start;
		Timestamp end;
		try {
			start = Validator.getTimestamp(startTime.getText());
			end = Validator.getTimestamp(endTime.getText());
		}
		catch (Exception ex) {
			printError("Zły format daty lub godziny");
			return;
		}

		PreparedStatement stmt = conn.prepareStatement(
				"INSERT INTO Aktywnosc (opis_aktywnosci, " +
				"obowiazkowa, poczatek_aktywnosci, " +
				"koniec_aktywnosci, miejsce_aktywnosci) " +
				"VALUES (?, ?, ?, ?, ?)");

		int iter = 0;
		stmt.setString(++iter, activityDescriptionText.getText());
		stmt.setBoolean(++iter, isObligatoryCheckBox.isSelected());
		stmt.setTimestamp(++iter, start);
		stmt.setTimestamp(++iter, end);
		stmt.setString(++iter, activityPlaceText.getText());

		stmt.executeUpdate();

		printInfo("Poprawnie dodano");

	}

	public void updateData(Connection conn) {
		this.conn = conn;
	}

}
