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
	private TextField txtHabitName;
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
		if (validateActivity() && validateTimeStart() && validateTimeLength() && validateFrequency() && validateDifficulty()) {
			lblError.setVisible(false);
			Habits habit = new Habits();
			habit.addHabit(habitInformations());
			System.out.println(habitInformations());
			
			root = FXMLLoader.load(getClass().getResource("Main.fxml"));
			addScene(e);
		}
		else {
			lblError.setVisible(true);
			errorMessage();
		}
	}
	
	private void errorMessage() {
		// TODO Auto-generated method stub
		if (!validateActivity()) {
			lblError.setText("Invalid Habit Name.");
		}
		else if (!validateTimeStart()) {
			lblError.setText("Invalid Start Time.");
		}
		else if (!validateTimeLength()) {
			lblError.setText("Invalid Time Length.");
		}
		else if (!validateFrequency()){
			lblError.setText("Invalid Frequency.");
		}
		else {
			lblError.setText("Invalid Difficulty Scale");
		}
		
	}

	public String habitInformations() {
		String text = "";
		//Activity
		text = text + txtHabitName.getText() + ",";
		//Time
		if (choiceBoxTime.getValue() == null) {
			text = text + txtCustomTime.getText() + choiceBoxAMPM.getValue() + ",";
		} else {
			text = text + choiceBoxTime.getValue() + ",";
		}
		//Time Length
		text = text + txtTimeAmount.getText() + choiceBoxHowLong.getValue() + ",";
		
		//How Often
		text = text + choiceBoxFrequency.getValue() + ",";
		
		//Difficulty
		text = text + txtDifficultyScale.getText();
		
		return text;
	}
	
	public boolean validateActivity() {
		String activity = txtHabitName.getText();
		if (activity.equals("")) {
			return false;
		}
		else {
			return true;
		}
	}
	
	public boolean validateTimeStart() {
		String timeStart = choiceBoxTime.getValue();
		//Choice box selected
		if (timeStart != null) {
			return true;
		}
		//Custom time checker
		else {
			timeStart = txtCustomTime.getText();
			String AMPM = choiceBoxAMPM.getValue();
			//selected am or pm
			if (AMPM != null) {
				//correct time length: #:## or ##:##
				if (timeStart.length() == 4 || timeStart.length() == 5) {
					String[] number = timeStart.split(":");
					//proper format
					if (number.length == 2) {
						try {
							//valid looking numbers
							int test = Integer.parseInt(number[0]);
							int test2 = Integer.parseInt(number[1]);
							if (test > 0 && test < 13 && test2 >= 0 && test2 < 60) {
								return true;
							}
							else {
								return false;
							}
						}
						catch (Exception e) {
							return false;
						}
					}
					//Improper format
					else {
						return false;
					}
				}
			}
			//Null choice for am or pm
			else {
				return false;
			}
		}
		System.out.println("Not working");
		return false;
	}
	
	public boolean validateTimeLength() {
		String timeLength = txtTimeAmount.getText();
		String timeType = choiceBoxHowLong.getValue();
		if (timeType != null && !timeLength.equals("")) {
			try {
				int test = Integer.parseInt(timeLength);
				return true;
			}
			catch (Exception e) {
				return false;
			}
		}
		else {
			return false;
		}
	}
	
	public boolean validateFrequency() {
		String frequency = choiceBoxFrequency.getValue();
		if (frequency != null) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean validateDifficulty() {
		String difficulty = txtDifficultyScale.getText();
		if (!difficulty.equals("")) {
			try {
				int test = Integer.parseInt(difficulty);
				if (test > 0 && test < 11) {
					return true;
				}
				else {
					return false;
				}
				
			}
			catch (Exception e) {
				return false;
			}
		}
		else {
			return false;
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
