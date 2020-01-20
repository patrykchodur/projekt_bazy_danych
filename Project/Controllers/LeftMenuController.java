package Project.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import Project.Controllers.MainController;
import javafx.scene.layout.AnchorPane;

public class LeftMenuController {
	protected MainController mainController;

	@FXML
	protected AnchorPane rootPane;

	@FXML
	protected AnchorPane rightPane;

	void setMainController(MainController mainController) {
		this.mainController = mainController;
	}

	@FXML 
	void initialize() {
		this.mainController = null;
	}


	@FXML
	void displayActivities(ActionEvent event) {

	}

	@FXML
	void displayAddDenunciation(ActionEvent event) {

	}

	@FXML
	void displayCitizenData(ActionEvent event) {

	}

	@FXML
	void displayWorkSchedule(ActionEvent event) {

	}

	@FXML
	void logoutAndDisplayLogin(ActionEvent event) {
		mainController.launchLoginScreen();
	}

}

