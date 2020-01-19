package Project.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import Project.Controllers.MainController;

public class LoginController {
	private MainController mainController;

	@FXML
	private TextField nickField;

	@FXML
	private PasswordField passwordField;

	@FXML
	private void initialize() {

	}

	void setMainController(MainController toSet) {
		this.mainController = toSet;
	}

	MainController getMainController() {
		return mainController;
	}

}

