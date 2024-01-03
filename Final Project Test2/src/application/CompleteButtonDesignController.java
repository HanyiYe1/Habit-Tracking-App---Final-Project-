package application;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class CompleteButtonDesignController {

	@FXML
    private Button btnComplete;
	
	@FXML
    private Label lblCompleted1;
	
	private String buttonFor;
	
	public void setButtonFor(String buttonFor) {
		this.buttonFor = buttonFor;
	}
		
	
	public void startup(String defaultStatus) {
		lblCompleted1.setText(defaultStatus);
	}
	
	public void toggleComplete() throws FileNotFoundException, IOException {
		Habits habit = new Habits();
		int row = -1;
		for (int i = 0; i < habit.habits.size(); i++) {
			if (buttonFor.equals(habit.habits.get(i).getActivity())) {
				row = i;
			}
		}
		if (row != -1) {
			String status = habit.habits.get(row).getStatus();
			
			if (status.equals("Complete")) {
				habit.habits.get(row).setStatus("Incomplete");
				habit.clearHabits();
				for (Habits habitToUse : habit.habits) {
					habit.addHabit(habitToUse.toString());
				}
			}
			else {
				habit.habits.get(row).setStatus("Complete");
				habit.clearHabits();
				for (Habits habitToUse : habit.habits) {
					habit.addHabit(habitToUse.toString());
				}
			}
			
			lblCompleted1.setText(habit.habits.get(row).getStatus());
		}
	}
}
