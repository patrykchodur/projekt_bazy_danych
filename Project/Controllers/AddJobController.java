package Project.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.sql.*;
import javafx.event.ActionEvent;

public class AddJobController {

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
	void addJob(ActionEvent event) {

	}

	public void updateData(Connection conn) throws SQLException {
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

