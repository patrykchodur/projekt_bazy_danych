package Project.Controllers;

import javafx.scene.layout.AnchorPane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import Project.Controllers.*;
import javafx.fxml.FXMLLoader;

public class LeftMenuThoughtPoliceController extends LeftMenuController {

	@FXML
	void displayAddConversation(ActionEvent event) {

	}

	@FXML
	void displayAddThoughtCrime(ActionEvent event) {
		AddThoughtCrimeController control = null;
		try {
			FXMLLoader loader = new FXMLLoader(
					LoginController.class.getResource(
						"../../scene_builder/add_thought_crime_scene.fxml"));
			AnchorPane pane = (AnchorPane) loader.load();
			control = loader.getController();
			control.updateData(userId, conn);
			setRightPane(pane);
		}
		catch (Exception ex) {
			System.out.println(ex);
		}

	}

	@FXML
	void displayCheckCitizen(ActionEvent event) {
		CheckCitizenController control = null;
		try {
			FXMLLoader loader = new FXMLLoader(
					LoginController.class.getResource(
						"../../scene_builder/check_citizen_scene.fxml"));
			AnchorPane pane = (AnchorPane) loader.load();
			control = loader.getController();
			control.updateData(conn);
			setRightPane(pane);
		}
		catch (Exception ex) {
			System.out.println(ex);
		}
	}

	@FXML
	void displayFindCitizen(ActionEvent event) {
		FindCitizenController control = null;
		try {
			FXMLLoader loader = new FXMLLoader(
					LoginController.class.getResource(
						"../../scene_builder/find_citizen_scene.fxml"));
			AnchorPane pane = (AnchorPane) loader.load();
			control = loader.getController();
			control.updateData(conn);
			setRightPane(pane);
		}
		catch (Exception ex) {
			System.out.println(ex);
		}
	}

	@FXML
	void displayCheckDenunciation(ActionEvent event) {
		CheckDenunciationController control = null;
		try {
			FXMLLoader loader = new FXMLLoader(
					LoginController.class.getResource(
						"../../scene_builder/check_denunciation_scene.fxml"));
			AnchorPane pane = (AnchorPane) loader.load();
			control = loader.getController();
			control.updateData(conn);
			setRightPane(pane);
		}
		catch (Exception ex) {
			System.out.println(ex);
		}
	}
}

