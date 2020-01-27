package Project.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.CheckBox;

import java.sql.*;
import javafx.event.ActionEvent;
import Project.Validator;

public class AddCitizenController {

	Connection conn;

	@FXML
	private TextField nameText;

	@FXML
	private TextField surnameText;

	@FXML
	private TextField birthDateText;

	@FXML
	private TextField deathDateText;

	@FXML
	private RadioButton manRadioButton;

	@FXML
	private RadioButton womanRadioButton;

	@FXML
	private ChoiceBox<String> partyMembershipChoiceBox;

	@FXML
	private CheckBox evaporizedCheck;

	@FXML
	private Label infoLabel;

	@FXML
	private TextField nickField;

	@FXML
	private TextField passwordField;

	void printError(String text) {
		infoLabel.setText("Error: " + text);
	}

	void printInfo(String text) {
		infoLabel.setText("Info: " + text);
	}

	@FXML
	void addCitizenButtonClicked(ActionEvent event) throws SQLException {
		String name = nameText.getText();
		if (name.isEmpty()) {
			printError("Imię nie może być puste");
			return;
		}
		String surname = surnameText.getText();
		if (surname.isEmpty()) {
			printError("Nazwisko nie może być puste");
			return;
		}
		String birthString = birthDateText.getText();
		if (birthString.isEmpty()) {
			printError("Brak daty urodzenia");
			return;
		}
		Date birthDate = null;
		Date deathDate = null;
		try {
			birthDate = Validator.getDate(birthString);

			if (!deathDateText.getText().isEmpty())
				deathDate = Validator.getDate(deathDateText.getText());
		}
		catch (Exception ex) {
			printError("Zły format daty");
			return;
		}
		if (!manRadioButton.isSelected() && !womanRadioButton.isSelected()) {
			printError("Należy wybrać płeć");
			return;
		}
		String sex = manRadioButton.isSelected() ? "M" : "W";

		String partyMembership = null;

		if (partyMembershipChoiceBox.getValue() == null) {
			printError("Należy wybrać członkostwo partii");
			return;
		}

		String partyMembershipFromBox = partyMembershipChoiceBox.getValue();
		if (partyMembershipFromBox.equals("Proletariusz"))
			partyMembership = "proletariusz";
		else if (partyMembershipFromBox.equals("Zewnętrzna Partia"))
			partyMembership = "zewnetrznaPartia";
		else if (partyMembershipFromBox.equals("Wewnętrzna Partia"))
			partyMembership = "wewnetrznaPartia";

		String nick = nickField.getText();
		String password = passwordField.getText();

		if (nick.isEmpty() || password.isEmpty()) {
			printError("Należy podać nick i hasło nowego obywatela");
			return;
		}

		PreparedStatement stmt = conn.prepareStatement(
				"INSERT INTO obywatel (" +
				"imie, nazwisko, data_urodzenia, plec, " +
				"czlonkowstwo_partii, data_smierci, nieobywatel) " +
				"VALUES (?, ?, ?, ?, ?::stopienczlonkowstwa, ?, ?) " +
				"RETURNING id", Statement.RETURN_GENERATED_KEYS);

		int iter = 0;
		stmt.setString(++iter, name);
		stmt.setString(++iter, surname);
		stmt.setDate(++iter, birthDate);
		stmt.setString(++iter, sex);
		stmt.setString(++iter, partyMembership);
		if (deathDate == null)
			stmt.setNull(++iter, Types.DATE);
		else
			stmt.setDate(++iter, deathDate);
		stmt.setBoolean(++iter, evaporizedCheck.isSelected());

		if (stmt.executeUpdate() != 1) {
			printError("Nieznany błąd połączenia z bazą");
			return;
		}

		ResultSet rs = stmt.getGeneratedKeys();

		rs.next();
		int id = rs.getInt(1);

		stmt = conn.prepareStatement(
				"INSERT INTO DaneLogowania ( " +
				"id_obywatela, nick, haslo) " +
				"SELECT ?, ?, ?");
		iter = 0;
		stmt.setInt(++iter, id);
		stmt.setString(++iter, nick);
		stmt.setString(++iter, password);

		if (stmt.executeUpdate() != 1) {
			printError("Nieznany błąd połączenia z bazą");
			return;
		}

		printInfo("Poprawnie dodano");
	}

	@FXML
	void manClicked(ActionEvent event) {
		womanRadioButton.setSelected(false);
	}

	@FXML
	void womanClicked(ActionEvent event) {
		manRadioButton.setSelected(false);
	}

	public void updateData(Connection conn) {
		this.conn = conn;

		partyMembershipChoiceBox.getItems().clear();
		partyMembershipChoiceBox.getItems().add("Proletariusz");
		partyMembershipChoiceBox.getItems().add("Zewnętrzna Partia");
		partyMembershipChoiceBox.getItems().add("Wewnętrzna Partia");
	}

}

