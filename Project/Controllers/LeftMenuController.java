package Project.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import Project.Controllers.MainController;
import javafx.scene.layout.AnchorPane;

import Project.Controllers.CitizenInfoController;
import javafx.fxml.FXMLLoader;
import java.sql.Connection;

public class LeftMenuController {
	protected MainController mainController;
	protected Connection conn;

	@FXML
	protected AnchorPane rootPane;

	@FXML
	protected AnchorPane rightPane;

	void setMainController(MainController mainController) {
		this.mainController = mainController;
	}

	void setConnection(Connection conn) {
		this.conn = conn;
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
		CitizenInfoController control = null;
		try {
			FXMLLoader loader = new FXMLLoader(
					LoginController.class.getResource(
						"../../scene_builder/citizen_info_scene.fxml"));
			AnchorPane pane = (AnchorPane) loader.load();
			control = loader.getController();
			control.updateData();
			rightPane.getChildren().clear();
			rightPane.getChildren().add(pane);
		}
		catch (Exception ex) {
			System.out.println(ex);
		}
	}

	@FXML
	void displayWorkSchedule(ActionEvent event) {

	}

	@FXML
	void logoutAndDisplayLogin(ActionEvent event) throws Exception {
		conn.close();
		mainController.launchLoginScreen();
	}

}

