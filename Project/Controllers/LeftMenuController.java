package Project.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import Project.Controllers.MainController;
import javafx.scene.layout.AnchorPane;

import javafx.scene.layout.Pane;
import Project.Controllers.*;
import javafx.fxml.FXMLLoader;
import java.sql.Connection;

public class LeftMenuController {
	protected MainController mainController;
	protected Connection conn;
	protected int userId;

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

	void setId(int id) {
		this.userId = id;
	}

	@FXML 
	void initialize() {
		this.mainController = null;
	}


	@FXML
	void displayActivities(ActionEvent event) {
		ActivitiesScheduleController control = null;
		try {
			FXMLLoader loader = new FXMLLoader(
					LoginController.class.getResource(
						"../../scene_builder/activity_schedule_scene.fxml"));
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
	void displayAddDenunciation(ActionEvent event) {
		AddDenunciationController control = null;
		try {
			FXMLLoader loader = new FXMLLoader(
					LoginController.class.getResource(
						"../../scene_builder/add_denunciation_scene.fxml"));
			AnchorPane pane = (AnchorPane) loader.load();
			control = loader.getController();
			control.updateData(userId, conn);
			setRightPane(pane);
		}
		catch (Exception ex) {
			System.out.println(ex);
		}

	}

	protected void setRightPane(Pane pane) {
		rightPane.getChildren().clear();
		rightPane.getChildren().add(pane);
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
			control.updateData(userId, conn);
			setRightPane(pane);
		}
		catch (Exception ex) {
			System.out.println(ex);
		}
	}

	@FXML
	void displayWorkSchedule(ActionEvent event) {
		JobScheduleController control = null;
		try {
			FXMLLoader loader = new FXMLLoader(
					LoginController.class.getResource(
						"../../scene_builder/job_schedule_scene.fxml"));
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
	void logoutAndDisplayLogin(ActionEvent event) throws Exception {
		conn.close();
		mainController.launchLoginScreen();
	}

}

