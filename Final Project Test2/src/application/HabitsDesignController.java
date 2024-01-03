package application;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

public class HabitsDesignController {

	private VBox vboxHabits;
	private VBox vboxCompleteButtons;
	
	@FXML
    private Button btnComplete;
	
	@FXML
    private Label lblCompleted1;
	
	@FXML
    private ImageView imgIcon;

    @FXML
    private Label lblActivityName;

    @FXML
    private Label lblDifficulty;

    @FXML
    private Label lblFrequency;

    @FXML
    private Label lblTimeAmount;

    @FXML
    private Label lblTimeStart;

    @FXML
    private Rectangle rectHabit;

    private String activity;
    private String buttonFor;

    public void setActivity(String activity) {
    	this.activity = activity;
    }
    
    public void setData(Habits habit) {
    	Image imageIcon = new Image(getClass().getResourceAsStream("/homeImages/list-check (1).png"));
    	imgIcon.setImage(imageIcon);
    	lblActivityName.setText(habit.getActivity());
    	activity = habit.getActivity();
    	lblDifficulty.setText("Difficulty: " + habit.getDifficulty());
    	lblFrequency.setText(habit.getFrequency());
    	lblTimeAmount.setText(habit.getTimeLength());
    	lblTimeStart.setText(habit.getTimeStart());
    }
    
    public void setVBoxes(VBox vboxHabits) {
    	this.vboxHabits = vboxHabits;
    }
    
    public void removeHabit() throws FileNotFoundException, IOException {
    	Habits habit = new Habits();
    	
		int row = -1;
		for (int i = 0; i < habit.habits.size(); i++) {
			if (activity.equals(habit.habits.get(i).getActivity())) {
				row = i;
			}
		}
		if (row != -1) {
			habit.habits.remove(row);
			habit.clearHabits();
			for (Habits habitToUse : habit.habits) {
				habit.addHabit(habitToUse.toString());
			}
			
			vboxHabits.getChildren().remove(row);	
		}
		else {
			System.out.println("Removal error.");
		}
    }
    
	
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
