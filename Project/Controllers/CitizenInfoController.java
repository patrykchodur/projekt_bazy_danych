package Project.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import javafx.fxml.FXMLLoader;
import java.sql.*;

public class CitizenInfoController {

	@FXML
	private Label nameLabel;

	@FXML
	private Label surnameLabel;

	@FXML
	private Label citizenLabel;

	@FXML
	private Label birthDateLabel;

	@FXML
	private Label sexLabel;

	@FXML
	private Label partyMembershipLabel;

	public void updateData(int id, Connection conn) throws SQLException {
		citizenLabel.setText("Obywatel " + id);

		PreparedStatement stmt = conn.prepareStatement(
				"SELECT o.imie, o.nazwisko, o.data_urodzenia, " +
				"o.plec, o.czlonkowstwo_partii FROM obywatel o " +
				"WHERE o.id = ?");

		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();

		if (!rs.next()) {
			nameLabel.setText("Nieznaleziono");
			return;
		}

		int i = 0;
		nameLabel.setText("Imię: " + rs.getString(++i));
		surnameLabel.setText("Nazwisko: " + rs.getString(++i));
		birthDateLabel.setText("Data urodzenia: " + rs.getString(++i));
		sexLabel.setText("Płeć: " + rs.getString(++i));
		String tmp = rs.getString(++i);
		String membership;
		if (tmp.equals("wewnetrznaPartia"))
			membership = "Wewnętrzna Partia";
		else if (tmp.equals("zewnetrznaPartia"))
			membership = "Zewnętrzna Partia";
		else
			membership = "Poletariat";

		partyMembershipLabel.setText(membership);
	}
}
