package application;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

public class HabitsDesignController implements Initializable {

	private VBox vboxHabits;
	private VBox vboxCompleteButtons;
	
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
    
    public void setVBoxes(VBox vboxHabits, VBox vboxCompleteButtons) {
    	this.vboxHabits = vboxHabits;
    	this.vboxCompleteButtons = vboxCompleteButtons;
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
			
			vboxCompleteButtons.getChildren().remove(row);
			vboxHabits.getChildren().remove(row);	
		}
		else {
			System.out.println("Removal error.");
		}
    }
    
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
