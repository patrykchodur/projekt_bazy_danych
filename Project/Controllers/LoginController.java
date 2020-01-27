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
import javafx.util.Pair;
import java.sql.*;

public class LoginController {
	private MainController mainController;

	@FXML
	private TextField nickField;

	@FXML
	private PasswordField passwordField;

	@FXML
	private void initialize() {

	}

	Connection getConnectionToDatabase() throws Exception {
		// login to sql

		Connection conn = DriverManager.getConnection(
				"jdbc:postgresql://pascal.fis.agh.edu.pl:5432/u7chodur", 
				"u7chodur", "7chodur");

		return conn;
	}

	int getCitizenIdFromLoginData(Connection conn) throws Exception {
		String login = nickField.getText();
		String password = passwordField.getText();

		PreparedStatement stmt = conn.prepareStatement(
				"SELECT o.id FROM Obywatel o " +
				"WHERE o.id = (SELECT id_obywatela FROM DaneLogowania " +
				"WHERE nick = ? AND haslo = ?)");
		stmt.setString(1, login);
		stmt.setString(2, password);

		ResultSet rs = stmt.executeQuery();
		if (!rs.next()) {
			return -1;
		}
		int id = rs.getInt(1);
		return id;
	}

	int getAccessLevel(int id, Connection conn) throws SQLException {
		// admin
		if (id < 1)
			return -1;
		if (id == 1)
			return 3;
		
		PreparedStatement stmt = conn.prepareStatement(
				"SELECT praca_id FROM obywatel " +
				"WHERE id = ?");
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		if (!rs.next()) {
			return -1;
		}
		// TODO return valid value
		return 1;
	}

	FXMLLoader getFXMLLoaderForPermissionLevel(int level) {
		switch (level) {
			case 1:
				return new FXMLLoader(LoginController.class.getResource(
						"../../scene_builder/left_menu_scene.fxml"));
			case 2:
				return new FXMLLoader(LoginController.class.getResource(
						"../../scene_builder/left_menu_thought_police_scene"));
			case 3:
				return new FXMLLoader(LoginController.class.getResource(
						"../../scene_builder/left_menu_extended_scene.fxml"));
			default:
				return new FXMLLoader(LoginController.class.getResource(
						"../../scene_builder/left_menu_scene.fxml"));
		}
	}



	@FXML
	void signinButtonClicked(ActionEvent event) throws Exception {
		Connection conn = getConnectionToDatabase();

		int id = getCitizenIdFromLoginData(conn);
		int level = getAccessLevel(id, conn);
		System.out.println("id: " + id + ", level: " + level);
		if (id < 0 || level < 0) {
			return;
		}
		FXMLLoader loader = getFXMLLoaderForPermissionLevel(level);


		AnchorPane leftMenuPane = (AnchorPane) loader.load();
		LeftMenuController leftMenuController = loader.getController();

		leftMenuController.setMainController(mainController);
		leftMenuController.setConnection(conn);
		leftMenuController.setId(id);
		mainController.setRootPane(leftMenuPane);
	}

	void setMainController(MainController toSet) {
		this.mainController = toSet;
	}

	MainController getMainController() {
		return mainController;
	}

}
