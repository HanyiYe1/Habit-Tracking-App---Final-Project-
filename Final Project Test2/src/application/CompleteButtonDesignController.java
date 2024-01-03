package application;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class CompleteButtonDesignController {

	@FXML
    private Button btnComplete;
	
	private String buttonFor;
	
	public void setButtonFor(String buttonFor) {
		this.buttonFor = buttonFor;
	}
	
	public ArrayList<String> statuses = new ArrayList<String>();
	
	public void toggleComplete() throws FileNotFoundException, IOException {
		
		Habits habit = new Habits();
		int row = -1;
		for (int i = 0; i < habit.habits.size(); i++) {
			if (buttonFor.equals(habit.habits.get(i).getActivity())) {
				row = i;
			}
		}
		if (row != -1) {
			
			System.out.println("Found button " + buttonFor);
			String status = habit.habits.get(row).getStatus();
			
			if (status.equals("Complete")) {
				habit.habits.get(row).setStatus("Incomplete");
				System.out.println(habit.habits.get(row).getStatus());
				habit.clearHabits();
				for (Habits habitToUse : habit.habits) {
					habit.addHabit(habitToUse.toString());
				}
			}
			else {
				habit.habits.get(row).setStatus("Complete");
				System.out.println(habit.habits.get(row).getStatus());
				habit.clearHabits();
				for (Habits habitToUse : habit.habits) {
					habit.addHabit(habitToUse.toString());
				}
			}
		}
	}
}
