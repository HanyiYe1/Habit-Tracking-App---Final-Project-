package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class HabitsController implements Initializable{
	
	@FXML
	private Stage stage;
	@FXML
	private Scene scene;
	@FXML
	private Parent root;
	@FXML
	private ChoiceBox<String> choiceBoxTime;
	private String[] times = {"1:00AM", "2:00AM", "3:00AM", "4:00AM", "5:00AM", "6:00AM", "7:00AM", "8:00AM", "9:00AM", "10:00AM", "11:00AM", "12:00AM", "1:00PM", "2:00PM", "3:00PM", "4:00PM", "5:00PM", "6:00PM", "7:00PM", "8:00PM", "9:00PM", "10:00PM", "11:00PM", "12:00PM"};
	@FXML
	private ChoiceBox<String> choiceBoxAMPM;
	private String[] timePeriod = {"AM", "PM"};
	@FXML
	private TextField txtCustomTime;
	@FXML
	private TextField txtTimeAmount;
	@FXML
	private ChoiceBox<String> choiceBoxHowLong;
	private String[] timeLength = {"Seconds", "Minutes", "Hours"};
	@FXML
	private ChoiceBox<String> choiceBoxFrequency;
	private String[] timeFrequency = {"Daily", "Weekly", "Monthly"};
	@FXML
	private TextField txtDifficultyScale;
	@FXML
	private Label lblError;
	
	public void switchToSceneMain(ActionEvent e) throws IOException {
		root = FXMLLoader.load(getClass().getResource("Main.fxml"));
		addScene(e);
	}
	
	public void clearWhen(ActionEvent e) {
		choiceBoxTime.valueProperty().set(null);
		choiceBoxAMPM.valueProperty().set(null);
		txtCustomTime.setText("");
	}
	
	public void clearHowLong(ActionEvent e) {
		choiceBoxHowLong.valueProperty().set(null);
		txtTimeAmount.setText("");
	}
	
	public void clearHowOften(ActionEvent e) {
		choiceBoxFrequency.valueProperty().set(null);
	}
	
	public void addHabit(ActionEvent e) throws IOException {
		boolean validHabit = false;
		if (validHabit) {
			root = FXMLLoader.load(getClass().getResource("Main.fxml"));
			addScene(e);
		}
		else {
			
		}
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

	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		choiceBoxTime.getItems().addAll(times);
		choiceBoxAMPM.getItems().addAll(timePeriod);
		choiceBoxHowLong.getItems().addAll(timeLength);
		choiceBoxFrequency.getItems().addAll(timeFrequency);
		lblError.setVisible(false);
	}
}
