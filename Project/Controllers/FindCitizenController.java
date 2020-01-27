package Project.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javafx.event.ActionEvent;
import java.sql.*;

public class FindCitizenController {
	Connection conn;

	@FXML
	private TextField nameField;

	@FXML
	private TextField surnameField;

	@FXML
	private Label resultsLabel;

	@FXML
	void searchButton(ActionEvent event) throws SQLException {
		String name = nameField.getText();
		String surname = surnameField.getText();

		PreparedStatement stmt = conn.prepareStatement(
				"SELECT id, imie, nazwisko FROM " +
				"Obywatel WHERE imie LIKE ? AND " +
				"nazwisko LIKE ?");
		int iter = 0;
		stmt.setString(++iter, "%" + name + "%");
		stmt.setString(++iter, "%" + surname + "%");

		ResultSet rs = stmt.executeQuery();
		
		String result = "";

		if (!rs.next())
			result = "Nie znaleziono";
		else {
			result += "id:\t" + rs.getInt(1);
			result += ", imię:\t" + rs.getString(2);
			result += ", nazwisko:\t" + rs.getString(3) + "\n";
			while (rs.next()) {
				result += "id:\t" + rs.getInt(1);
				result += ", imię:\t" + rs.getString(2);
				result += ", nazwisko:\t" + rs.getString(3) + "\n";
			}
		}
		resultsLabel.setText(result);

	}

	public void updateData(Connection conn) {
		this.conn = conn;
	}

}

