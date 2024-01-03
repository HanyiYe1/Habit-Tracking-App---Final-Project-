package application;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AllHabitsController implements Initializable {
	
	@FXML
	private VBox vboxHabits;
	@FXML
	private VBox vboxCompleteButtons;	

	@FXML
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	
	
	public void switchToSceneMain(ActionEvent e) throws IOException {
		root = FXMLLoader.load(getClass().getResource("Main.fxml"));
		addSceneMain(e);
	}
	
	public void reInitialize() {
		try {
			vboxHabits.getChildren().clear();
			vboxCompleteButtons.getChildren().clear();
			
			
			Habits habit = new Habits();
			List<Habits> habitsToUse = habit.habits;
			for (int i = 0; i < habitsToUse.size(); i++) {
				FXMLLoader fxmlloader = new FXMLLoader();
				fxmlloader.setLocation(getClass().getResource("HabitsDesign.fxml"));
				//Add habits
				AnchorPane pane = fxmlloader.load();
				HabitsDesignController hdc = fxmlloader.getController();
				hdc.setData(habitsToUse.get(i));
				vboxHabits.getChildren().add(pane);
				
				FXMLLoader fxmlloader2 = new FXMLLoader();
				fxmlloader2.setLocation(getClass().getResource("CompleteButtonDesign.fxml"));
				AnchorPane pane2 = fxmlloader2.load();
				CompleteButtonDesignController cbdc = fxmlloader2.getController();
				cbdc.setButtonFor(habit.habits.get(i).getActivity());
				cbdc.startup(habit.habits.get(i).getStatus());
				vboxCompleteButtons.getChildren().add(pane2);
				
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		try {
			Habits habit = new Habits();
			List<Habits> habitsToUse = habit.habits;
			for (int i = 0; i < habitsToUse.size(); i++) {
				FXMLLoader fxmlloader = new FXMLLoader();
				fxmlloader.setLocation(getClass().getResource("HabitsDesign.fxml"));
				//Add habits
				AnchorPane pane = fxmlloader.load();
				HabitsDesignController hdc = fxmlloader.getController();
				hdc.setData(habitsToUse.get(i));
				hdc.setVBoxes(vboxHabits, vboxCompleteButtons);
				vboxHabits.getChildren().add(pane);
				
				
				FXMLLoader fxmlloader2 = new FXMLLoader();
				fxmlloader2.setLocation(getClass().getResource("CompleteButtonDesign.fxml"));
				AnchorPane pane2 = fxmlloader2.load();
				CompleteButtonDesignController cbdc = fxmlloader2.getController();
				cbdc.setButtonFor(habit.habits.get(i).getActivity());
				cbdc.startup(habit.habits.get(i).getStatus());
				vboxCompleteButtons.getChildren().add(pane2);
				
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void addSceneMain(ActionEvent event) {
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        String css = this.getClass().getResource("application.css").toExternalForm();
        // Set the stylesheet after the scene creation
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
	}
	
	
	public void toggleComplete() throws FileNotFoundException, IOException {
		Habits habit = new Habits();
		ArrayList<String> currentStatuses = new ArrayList<String>();
		int row = 0;
		for (int i = 0; i < habit.habits.size(); i++) {
			currentStatuses.add(habit.habits.get(i).getStatus());
		}
		
	}

}
