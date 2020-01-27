package Project.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.*;
import javafx.event.ActionEvent;

public class CheckCitizenController {
	Connection conn;

	@FXML
	private TextField idField;

	@FXML
	private Label nameLabel;

	@FXML
	private Label surnameLabel;

	@FXML
	private Label birthDateLabel;

	@FXML
	private Label sexLabel;

	@FXML
	private Label membershipLabel;

	@FXML
	private Label citizenRatingLabel;

	@FXML
	private Label deathDateLabel;

	@FXML
	private Label evaporizedLabel;

	@FXML
	private Label jobLabel;

	@FXML
	void checkCitizen(ActionEvent event) throws SQLException {
		int id;
		try {
			id = Integer.parseInt(idField.getText());
		}
		catch (Exception ex) {
			return;
		}

		PreparedStatement stmt = conn.prepareStatement(
				"SELECT imie, nazwisko, data_urodzenia, " +
				"plec, data_smierci, czlonkowstwo_partii, " +
				"ocena_obywatela, nieobywatel, praca_id FROM " +
				"Obywatel WHERE id = ?");
		stmt.setInt(1, id);
		
		ResultSet rs = stmt.executeQuery();

		if (!rs.next())
			return;

		int iter = 0;
		nameLabel.setText("Imię: " + rs.getString(++iter));
		surnameLabel.setText("Nazwisko: " + rs.getString(++iter));
		birthDateLabel.setText("Data urodzenia: " + rs.getDate(++iter));
		sexLabel.setText("Płeć: " + rs.getString(++iter));
		String deathString = null;
		Date deathDate = rs.getDate(++iter);
		if (deathDate == null)
			deathString = "Obywatel żyje";
		else
			deathString = "Data śmierci: " + deathDate;
		deathDateLabel.setText(deathString);
		String membershipString = rs.getString(++iter);
		if (membershipString.equals("proletariusz")) {
			membershipString = "Proletariusz";
		}
		else if (membershipString.equals("zewnetrznaPartia")) {
			membershipString = "Zewnętrzna Partia";
		}
		else if (membershipString.equals("wewnetrznaPartia")) {
			membershipString = "Wewnętrzna Partia";
		}
		membershipLabel.setText(membershipString);
		citizenRatingLabel.setText("Ocena obywatela: " + rs.getInt(++iter));
		evaporizedLabel.setText("Ewaporyzowany: " + (rs.getBoolean(++iter)
				? "tak" : "nie"));
		
		int job_id = rs.getInt(++iter);

		stmt = conn.prepareStatement(
				"SELECT nazwa FROM praca WHERE id = ?");

		stmt.setInt(1, job_id);

		rs = stmt.executeQuery();
		if (!rs.next())
			jobLabel.setText("Stanowisko: brak");
		else
			jobLabel.setText("Stanowisko: " + rs.getString(1));

	}

	public void updateData(Connection conn) {
		this.conn = conn;
	}

}

