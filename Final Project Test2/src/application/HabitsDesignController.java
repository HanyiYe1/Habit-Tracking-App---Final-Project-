package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public class HabitsDesignController implements Initializable {

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

    
    public void setData(Habits habit) {
    	Image imageIcon = new Image(getClass().getResourceAsStream("/homeImages/list-check (1).png"));
    	imgIcon.setImage(imageIcon);
    	lblActivityName.setText(habit.getActivity());
    	lblDifficulty.setText("Difficulty: " + habit.getDifficulty());
    	lblFrequency.setText(habit.getFrequency());
    	lblTimeAmount.setText(habit.getTimeLength());
    	lblTimeStart.setText(habit.getTimeStart());
    	
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
