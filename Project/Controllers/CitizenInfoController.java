package Project.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import javafx.fxml.FXMLLoader;

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

	public void updateData() {
		partyMembershipLabel.setText("Partia Wewnętrzna");

	}

}

