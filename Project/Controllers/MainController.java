package Project.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.AnchorPane;

import javafx.scene.layout.Pane;
import Project.Controllers.LoginController;

public class MainController {

	@FXML
	private StackPane mainStackPane;

	@FXML
	private void initialize() {
		FXMLLoader loginLoader = new FXMLLoader(
				MainController.class.getResource(
					"../../scene_builder/login_scene.fxml"));
		AnchorPane loginPane = null;
		try {
			loginPane = (AnchorPane) loginLoader.load();
		}
		catch (Exception ex) {
			System.out.println(ex);
		}

		LoginController loginController = loginLoader.getController();
		loginController.setMainController(this);
		setScreen(loginPane);
	}

	public void setScreen(Pane pane) {
		mainStackPane.getChildren().add(pane);
	}


}

