package Project.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import Project.Controllers.*;
import javafx.fxml.*;
import javafx.scene.layout.AnchorPane;

public class LeftMenuExtendedController extends LeftMenuController {


	@FXML
	void displayAddActivity(ActionEvent event) {
		AddActivityController control = null;
		try {
			FXMLLoader loader = new FXMLLoader(
					LoginController.class.getResource(
						"../../scene_builder/add_activity_scene.fxml"));
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
	void displayAddWork(ActionEvent event) {
		AddJobController control = null;
		try {
			FXMLLoader loader = new FXMLLoader(
					LoginController.class.getResource(
						"../../scene_builder/add_job_scene.fxml"));
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
	void displayAddWorkHours(ActionEvent event) {

	}

	@FXML
	void displayCheckCitizen(ActionEvent event) {

	}

	@FXML
	void displaySignCitizenToActivity(ActionEvent event) {

	}

	@FXML
	void displayAddCitizen(ActionEvent event) {

	}
}

