package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HabitsController {
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	public void switchToSceneMain(ActionEvent e) throws IOException {
		root = FXMLLoader.load(getClass().getResource("Main.fxml"));
		addScene(e);
	}
	
	public void addHabit(ActionEvent e) throws IOException {
		root = FXMLLoader.load(getClass().getResource("Main.fxml"));
		addScene(e);
	}
	
	private void addScene(ActionEvent event){
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        String css = this.getClass().getResource("application.css").toExternalForm();
        // Set the stylesheet after the scene creation
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
     }
}
