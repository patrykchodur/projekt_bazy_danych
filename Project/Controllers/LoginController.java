package Project.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javafx.fxml.FXMLLoader;
import Project.Controllers.MainController;
import javafx.event.ActionEvent;
import Project.Controllers.LeftMenuController;
import Project.Controllers.LeftMenuExtendedController;
import Project.Controllers.LeftMenuThoughtPoliceController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class LoginController {
	private MainController mainController;

	@FXML
	private TextField nickField;

	@FXML
	private PasswordField passwordField;

	@FXML
	private void initialize() {

	}

	@FXML
	void signinButtonClicked(ActionEvent event) {
		String login = nickField.getText();
		String password = passwordField.getText();
		// login to sql
		AnchorPane leftMenuPane = null;
		LeftMenuController leftMenuController = null;

		boolean regularCitizen = true;
		if (regularCitizen) {
			// prepare data
			LeftMenuController leftMenu = null;
			try {
				FXMLLoader loader = new FXMLLoader(
						LoginController.class.getResource(
							"../../scene_builder/left_menu_extended_scene.fxml"));
				leftMenuPane = (AnchorPane) loader.load();
				leftMenuController = loader.getController();
				leftMenuController.setMainController(mainController);
				mainController.setRootPane(leftMenuPane);
			}
			catch (Exception ex) {
				System.out.println(ex);
			}
		}
		// else

	}

	void setMainController(MainController toSet) {
		this.mainController = toSet;
	}

	MainController getMainController() {
		return mainController;
	}

}

