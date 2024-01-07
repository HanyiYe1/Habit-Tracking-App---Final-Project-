package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class MainController implements Initializable {
	
	@FXML
	private HBox hBoxMilestones1;
	@FXML
	private HBox hBoxMilestones2;
	@FXML
	private Button btnComplete1;
	@FXML
	private Button btnComplete2;
	
	@FXML
	private Rectangle rectHabit1;
	
	@FXML
	private Rectangle rectHabit2;
	
	@FXML
	private Rectangle rectCompleted1;
	
	@FXML
	private Rectangle rectCompleted2;
	
	@FXML
	private ImageView imgIcon1;
	
	@FXML
	private ImageView imgIcon2;
	@FXML
	private Label lblTimeStart1;
	@FXML
	private Label lblTimeStart2;
	@FXML
	private Label lblActivityName1;
	@FXML
	private Label lblActivityName2;
	@FXML
	private Label lblTimeAmount1;
	@FXML
	private Label lblTimeAmount2;
	@FXML
	private Label lblFrequency1;
	@FXML
	private Label lblFrequency2;
	@FXML
	private Label lblDifficulty1;
	@FXML
	private Label lblDifficulty2;	
	@FXML
	private Label lblCompleted1;
	@FXML
	private Label lblCompleted2;
	@FXML
	private Label lblPercentComplete;
	@FXML
	private Label lblDate;
	@FXML
	private Label lblStreakNumber;
	@FXML
	private Label lblDay;
	
	@FXML 
	private ImageView imgSpace;
	@FXML
	private ImageView imgUndiscovered1;
	@FXML
	private ImageView imgUndiscovered2;
	@FXML
	private ImageView imgUndiscovered3;
	@FXML
	private ImageView imgUndiscovered4;
	@FXML
	private ImageView imgUndiscovered5;
	@FXML
	private ImageView imgUndiscovered6;
	@FXML
    private Rectangle AchievementRectangle1;

    @FXML
    private Rectangle AchievementRectangle2;

    @FXML
    private Rectangle AchievementRectangle3;

    @FXML
    private Rectangle AchievementRectangle4;

    @FXML
    private Rectangle AchievementRectangle5;

    @FXML
    private Rectangle AchievementRectangle6;
	
	@FXML
	private Stage stage;
	private Scene scene;
	private Parent root;
	private Parent root2;	
	private Parent root3;
	private Parent root4;
	@FXML
	private Button btnMoreHabits;
	
	public void switchToSceneAllHabits(ActionEvent e) throws IOException {
		root3 = FXMLLoader.load(getClass().getResource("AllHabits.fxml"));
		addSceneAllHabit(e);
	}
	
	public void switchToSceneHabit(ActionEvent e) throws IOException {
		root2 = FXMLLoader.load(getClass().getResource("Habits.fxml"));
		addSceneAddHabit(e);
	}
	
	public void switchToSceneAllAchievements(ActionEvent e) throws IOException {
		root4 = FXMLLoader.load(getClass().getResource("allAchievements.fxml"));
		addSceneAllAchievement(e);
	}
	
	public void setComplete1(ActionEvent e) throws FileNotFoundException, IOException {
		Habits habit = new Habits();
		String completedHabit = habit.habits.get(0).toString();
		habit.habits.get(0).setStatus("Complete");
		habit.clearHabits();
		for (int i = 0; i < habit.habits.size(); i++) {
			habit.addHabit(habit.habits.get(i).toString());
		}
		setPercentageComplete();
		displayHabit1(habit.habits);
	}
	
	public void setComplete2(ActionEvent e) throws FileNotFoundException, IOException {
		Habits habit = new Habits();
		String completedHabit = habit.habits.get(1).toString();
		habit.habits.get(1).setStatus("Complete");
		habit.clearHabits();
		for (int i = 0; i < habit.habits.size(); i++) {
			habit.addHabit(habit.habits.get(i).toString());
		}
		setPercentageComplete();
		displayHabit2(habit.habits);
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			clipSpaceImage();
			Habits habit = new Habits();
			
			//habit.resetHabits();
			setMilestones();
			setDate();
			displayNoHabits();
			setPercentageComplete();
			setStreak();
			
			
			if (habit.habits.size() >= 2) {
				displayHabit1(habit.habits);
				displayHabit2(habit.habits);
			}
			else if (habit.habits.size() == 1) {
				displayHabit1(habit.habits);
			}
			
			
		//Error catching not important	
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	private void setMilestones() throws FileNotFoundException, IOException {
		ImageView[] imagesUndiscovered = {imgUndiscovered1, imgUndiscovered2, imgUndiscovered3, imgUndiscovered4, imgUndiscovered5, imgUndiscovered6};
		Rectangle[] achievementRectangles = {AchievementRectangle1, AchievementRectangle2, AchievementRectangle3, AchievementRectangle4, AchievementRectangle5, AchievementRectangle6};
		// TODO Auto-generated method stub
		Milestone milestone = new Milestone();
		List<Milestone> milestoneToUse = milestone.milestones;
		if (milestoneToUse.size() > 3) {
			for (int i = 0; i < 3; i++) {
				FXMLLoader fxmlloader = new FXMLLoader();
				fxmlloader.setLocation(getClass().getResource("spaceBanner.fxml"));
				//Add habits
				AnchorPane pane = fxmlloader.load();
				spaceBannerController sbc = fxmlloader.getController();
				sbc.setBannerFor(milestoneToUse.get(i));
				hBoxMilestones1.getChildren().add(pane);
				imagesUndiscovered[i].setVisible(false);
				achievementRectangles[i].setVisible(false);
			}
			
			if (milestoneToUse.size() > 6) {
				for (int i = 3; i < 6; i++) {
					FXMLLoader fxmlloader = new FXMLLoader();
					fxmlloader.setLocation(getClass().getResource("spaceBanner.fxml"));
					//Add habits
					AnchorPane pane = fxmlloader.load();
					spaceBannerController sbc = fxmlloader.getController();
					sbc.setBannerFor(milestoneToUse.get(i));
					hBoxMilestones2.getChildren().add(pane);
					imagesUndiscovered[i].setVisible(false);
					achievementRectangles[i].setVisible(false);
				}
			}
			else {
				for (int i = 3; i < milestone.milestones.size(); i++) {
					FXMLLoader fxmlloader = new FXMLLoader();
					fxmlloader.setLocation(getClass().getResource("spaceBanner.fxml"));
					//Add habits
					AnchorPane pane = fxmlloader.load();
					spaceBannerController sbc = fxmlloader.getController();
					sbc.setBannerFor(milestoneToUse.get(i));
					hBoxMilestones2.getChildren().add(pane);
					imagesUndiscovered[i].setVisible(false);
					achievementRectangles[i].setVisible(false);
				}
			}
		}
		else {
			for (int i = 0; i < milestoneToUse.size(); i++) {
				FXMLLoader fxmlloader = new FXMLLoader();
				fxmlloader.setLocation(getClass().getResource("spaceBanner.fxml"));
				//Add habits
				AnchorPane pane = fxmlloader.load();
				spaceBannerController sbc = fxmlloader.getController();
				sbc.setBannerFor(milestoneToUse.get(i));
				hBoxMilestones1.getChildren().add(pane);
				imagesUndiscovered[i].setVisible(false);
				achievementRectangles[i].setVisible(false);
			}
		}
	}

	private void setDate() {
		//Set date as in "yyyy/MM/dd"
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");  
		LocalDateTime now = LocalDateTime.now();
		lblDate.setText(dtf.format(now));
		//Set day of week as in mon tue wed thur fri sat sun
		Date date=new Date(); // today
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		String dayWeekText = new SimpleDateFormat("EEEE").format(date); //TUESDAY
		lblDay.setText(dayWeekText);
	}
	
	private void setPercentageComplete() throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		Habits habit = new Habits();
		String[] checker;
		double amountCompleted = 0;
		for (int i = 0; i < habit.habits.size(); i++) {
			checker = habit.habits.get(i).toString().split(",");
			if (checker[0].equals("Complete")) {
				amountCompleted++;
			}
		}
		//System.out.println(amountCompleted);
		//System.out.println(habit.habits.size());
		int percent = (int) Math.round(amountCompleted/habit.habits.size() * 100);
		if (habit.habits.size() == 0) {
			lblPercentComplete.setText("0%");
			System.out.println("Going here");
		}
		else {
			System.out.println(percent);
			Habits.setPercentComplete(percent);
			lblPercentComplete.setText(Integer.toString(percent) + "%");
		}
		
		if (percent == 100) {
			Streak streak = new Streak();
			if (streak.dates.contains(lblDate.getText())) {
				//Do nothing since the streak has been added.
				System.out.println("Going into here");
				setStreak();
			}
			else {
				streak.addDate(lblDate.getText());
				Streak.increaseStreak();
				
				setStreak();
			}
		}
	}
	
	private void setStreak() throws FileNotFoundException, IOException {
		Streak streak = new Streak();
		streak.calculateStreak();
		System.out.println("Streak: " + Streak.streak);
		lblStreakNumber.setText(Integer.toString(Streak.streak));
		
	}

	private void displayHabit2(ArrayList<Habits> habits) {
		// TODO Auto-generated method stub
		
		if (habits.get(1).getStatus().equals("Complete")) {
			rectCompleted2.setVisible(true);
			lblCompleted2.setVisible(true);
		}
		else {
			rectCompleted2.setVisible(false);
			lblCompleted2.setVisible(false);
		}
		
		rectHabit2.setVisible(true);
		imgIcon2.setVisible(true);
		btnComplete2.setVisible(true);
		
		lblTimeStart2.setVisible(true);
		lblTimeStart2.setText(habits.get(1).getTimeStart());
		
		lblActivityName2.setVisible(true);
		lblActivityName2.setText(habits.get(1).getActivity());
		
		lblTimeAmount2.setVisible(true);
		lblTimeAmount2.setText(habits.get(1).getTimeLength());
		
		lblFrequency2.setVisible(true);
		lblFrequency2.setText(habits.get(1).getFrequency());
		
		lblDifficulty2.setVisible(true);
		lblDifficulty2.setText("Difficulty: " + habits.get(1).getDifficulty());
	}

	private void displayHabit1(ArrayList<Habits> habits) {
		if (habits.get(0).getStatus().equals("Complete")) {
			rectCompleted1.setVisible(true);
			lblCompleted1.setVisible(true);
		}
		else {
			rectCompleted1.setVisible(false);
			lblCompleted1.setVisible(false);
		}
		
	
		rectHabit1.setVisible(true);
		imgIcon1.setVisible(true);
		btnComplete1.setVisible(true);

		lblTimeStart1.setVisible(true);
		lblTimeStart1.setText(habits.get(0).getTimeStart());
		
		lblActivityName1.setVisible(true);
		lblActivityName1.setText(habits.get(0).getActivity());
		
		lblTimeAmount1.setVisible(true);
		lblTimeAmount1.setText(habits.get(0).getTimeLength());
		
		lblFrequency1.setVisible(true);
		lblFrequency1.setText(habits.get(0).getFrequency());
		
		lblDifficulty1.setVisible(true);
		lblDifficulty1.setText("Difficulty: " + habits.get(0).getDifficulty());		
	}
	
	private void displayNoHabits() {
		rectCompleted1.setVisible(false);
		rectHabit1.setVisible(false);
		btnComplete1.setVisible(false);
		imgIcon1.setVisible(false);
		lblTimeStart1.setVisible(false);
		lblActivityName1.setVisible(false);
		lblTimeAmount1.setVisible(false);
		lblFrequency1.setVisible(false);
		lblDifficulty1.setVisible(false);
		lblCompleted1.setVisible(false);
		
		rectHabit2.setVisible(false);
		rectCompleted2.setVisible(false);
		btnComplete2.setVisible(false);
		imgIcon2.setVisible(false);
		lblTimeStart2.setVisible(false);
		lblActivityName2.setVisible(false);
		lblTimeAmount2.setVisible(false);
		lblFrequency2.setVisible(false);
		lblDifficulty2.setVisible(false);
		lblCompleted2.setVisible(false);

	}
	
	private void clipSpaceImage() {
		// set a clip to apply rounded border to the original image.
        Rectangle clip = new Rectangle(
        	imgSpace.getFitWidth(), imgSpace.getFitHeight()
        );
        clip.setArcWidth(50);
        clip.setArcHeight(50);
        imgSpace.setClip(clip);

        // snapshot the rounded image.
        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(Color.TRANSPARENT);
        WritableImage image = imgSpace.snapshot(parameters, null);

        // remove the rounding clip so that our effect can show through.
        imgSpace.setClip(null);

        imgSpace.setOpacity(0.75);
        // store the rounded image in the imageView.
        imgSpace.setImage(image);
        //-----------------------------------------------------------------------------
        Rectangle clip2 = new Rectangle(
    		imgUndiscovered1.getFitWidth(), imgUndiscovered1.getFitHeight()
        );
    	clip2.setArcWidth(50);
    	clip2.setArcHeight(50);
    	imgUndiscovered1.setClip(clip2);

        // snapshot the rounded image.
        SnapshotParameters parameters2 = new SnapshotParameters();
        parameters2.setFill(Color.TRANSPARENT);
        WritableImage image2 = imgUndiscovered1.snapshot(parameters2, null);

        // remove the rounding clip so that our effect can show through.
        imgUndiscovered1.setClip(null);
        // store the rounded image in the imageView.
        imgUndiscovered1.setImage(image2);
        //-----------------------------------------------------------------------------
        Rectangle clip3 = new Rectangle(
    		imgUndiscovered2.getFitWidth(), imgUndiscovered2.getFitHeight()
        );
        clip3.setArcWidth(50);
        clip3.setArcHeight(50);
    	imgUndiscovered2.setClip(clip3);

        // snapshot the rounded image.
        SnapshotParameters parameters3 = new SnapshotParameters();
        parameters3.setFill(Color.TRANSPARENT);
        WritableImage image3 = imgUndiscovered2.snapshot(parameters3, null);

        // remove the rounding clip so that our effect can show through.
        imgUndiscovered2.setClip(null);
        // store the rounded image in the imageView.
        imgUndiscovered2.setImage(image3);
        //-----------------------------------------------------------------------------
        Rectangle clip4 = new Rectangle(
    		imgUndiscovered3.getFitWidth(), imgUndiscovered3.getFitHeight()
        );
        clip4.setArcWidth(50);
        clip4.setArcHeight(50);
        imgUndiscovered3.setClip(clip4);

        // snapshot the rounded image.
        SnapshotParameters parameters4 = new SnapshotParameters();
        parameters4.setFill(Color.TRANSPARENT);
        WritableImage image4 = imgUndiscovered3.snapshot(parameters4, null);

        // remove the rounding clip so that our effect can show through.
        imgUndiscovered3.setClip(null);
        // store the rounded image in the imageView.
        imgUndiscovered3.setImage(image4);
        //-----------------------------------------------------------------------------
        Rectangle clip5 = new Rectangle(
    		imgUndiscovered4.getFitWidth(), imgUndiscovered4.getFitHeight()
        );
        clip5.setArcWidth(50);
        clip5.setArcHeight(50);
        imgUndiscovered4.setClip(clip5);

        // snapshot the rounded image.
        SnapshotParameters parameters5 = new SnapshotParameters();
        parameters5.setFill(Color.TRANSPARENT);
        WritableImage image5 = imgUndiscovered4.snapshot(parameters5, null);

        // remove the rounding clip so that our effect can show through.
        imgUndiscovered4.setClip(null);
        // store the rounded image in the imageView.
        imgUndiscovered4.setImage(image5);
        //-----------------------------------------------------------------------------
        Rectangle clip6 = new Rectangle(
    		imgUndiscovered5.getFitWidth(), imgUndiscovered5.getFitHeight()
        );
        clip6.setArcWidth(50);
        clip6.setArcHeight(50);
        imgUndiscovered5.setClip(clip6);

        // snapshot the rounded image.
        SnapshotParameters parameters6 = new SnapshotParameters();
        parameters6.setFill(Color.TRANSPARENT);
        WritableImage image6 = imgUndiscovered5.snapshot(parameters6, null);

        // remove the rounding clip so that our effect can show through.
        imgUndiscovered5.setClip(null);
        // store the rounded image in the imageView.
        imgUndiscovered5.setImage(image6);
        //-----------------------------------------------------------------------------
        Rectangle clip7 = new Rectangle(
    		imgUndiscovered6.getFitWidth(), imgUndiscovered6.getFitHeight()
        );
        clip7.setArcWidth(50);
        clip7.setArcHeight(50);
        imgUndiscovered6.setClip(clip7);

        // snapshot the rounded image.
        SnapshotParameters parameters7 = new SnapshotParameters();
        parameters7.setFill(Color.TRANSPARENT);
        WritableImage image7 = imgUndiscovered6.snapshot(parameters7, null);

        // remove the rounding clip so that our effect can show through.
        imgUndiscovered6.setClip(null);
        // store the rounded image in the imageView.
        imgUndiscovered6.setImage(image7);
	}

	private void addSceneAllAchievement(ActionEvent event) {
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root4);
        String css = this.getClass().getResource("application.css").toExternalForm();
        // Set the stylesheet after the scene creation
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
	}
	
	private void addSceneAddHabit(ActionEvent event){
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root2);
        String css = this.getClass().getResource("application.css").toExternalForm();
        // Set the stylesheet after the scene creation
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
	}
	private void addSceneAllHabit(ActionEvent event) {
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root3);
        String css = this.getClass().getResource("application.css").toExternalForm();
        // Set the stylesheet after the scene creation
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
	}
}
