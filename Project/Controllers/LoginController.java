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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
	void signinButtonClicked(ActionEvent event) throws Exception {
		String login = nickField.getText();
		String password = passwordField.getText();
		// login to sql

		Connection conn = DriverManager.getConnection("jdbc:postgresql://pascal.fis.agh.edu.pl:5432/u7chodur", 
				"u7chodur", "7chodur");
		try (PreparedStatement stmt = conn.prepareStatement("SELECT o.id, o.czlonkowstwo_partii FROM Obywatel o WHERE o.id = (SELECT id_obywatela FROM DaneLogowania WHERE nick = ? AND haslo = ?)")) {


			AnchorPane leftMenuPane = null;
			LeftMenuController leftMenuController = null;

			boolean regularCitizen = true;
			if (regularCitizen) {
				// prepare data
				LeftMenuController leftMenu = null;
				try {
					FXMLLoader loader = new FXMLLoader(
							LoginController.class.getResource(
								"../../scene_builder/left_menu_thought_police_scene.fxml"));
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

	}

	void setMainController(MainController toSet) {
		this.mainController = toSet;
	}

	MainController getMainController() {
		return mainController;
	}

}

