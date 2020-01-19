package Project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;

import Project.Controllers.MainController;

public class Project extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		// init app
		FXMLLoader mainPaneLoader = new FXMLLoader();
		mainPaneLoader.setLocation(Project.class.getResource(
					"../scene_builder/main_pane.fxml"));
		StackPane mainPane = (StackPane) mainPaneLoader.load();

		MainController mainController = mainPaneLoader.getController();

		Scene scene = new Scene(mainPane);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	public static void main(String[] argv) {
		launch(argv);
	}
}


