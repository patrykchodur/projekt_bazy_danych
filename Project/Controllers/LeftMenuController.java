package Project.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import Project.Controllers.MainController;

public class LeftMenuController {
	MainController mainController;

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

