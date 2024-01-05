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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class AllHabitsController implements Initializable {
	
	@FXML
	private VBox vboxHabits;
	@FXML
	private VBox vboxCompleteButtons;	

	@FXML
	private ChoiceBox<String> choiceBoxSortOrder;
	private String[] sortOrder = {"Ascending", "Descending"};
	 
    @FXML
    private ChoiceBox<String> choiceBoxSortType;
    private String[] sortType = {"Newest", "Difficulty", "Start Time"};

    @FXML
    private Label lblAllHabits;

    @FXML
    private Label lblError;

    @FXML
    private TextField txtSearchHabit;
	
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
    
	
	@FXML
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	
	
	public void searchActivity() throws FileNotFoundException, IOException {
		String activitySearch = txtSearchHabit.getText().toUpperCase();
		Habits habitFound = null;
		Habits habit = new Habits();
		for (Habits habitToCheck : habit.habits) {
			if (habitToCheck.getActivity().toUpperCase().equals(activitySearch)) {
				habitFound = habitToCheck;
			}
		}
		if (habitFound == null) {
			lblError.setText("No habit found.");
			lblError.setVisible(true);
			imgIcon.setVisible(false);
			lblActivityName.setVisible(false);
			lblDifficulty.setVisible(false);
			lblFrequency.setVisible(false);
			lblTimeAmount.setVisible(false);
			lblTimeStart.setVisible(false);
			rectHabit.setVisible(false);
		}
		else {
			lblError.setVisible(false);
			imgIcon.setVisible(true);
			lblActivityName.setVisible(true);
			lblActivityName.setText(habitFound.getActivity());
			lblDifficulty.setVisible(true);
			lblDifficulty.setText("Difficulty: " + habitFound.getDifficulty());
			lblFrequency.setVisible(true);
			lblFrequency.setText(habitFound.getFrequency());
			lblTimeAmount.setVisible(true);
			lblTimeAmount.setText(habitFound.getTimeLength());
			lblTimeStart.setVisible(true);
			lblTimeStart.setText(habitFound.getTimeStart());
			rectHabit.setVisible(true);
		}
	}
	
	public void sortBy() throws FileNotFoundException, IOException {
		boolean reversed = false;
		String sortType = choiceBoxSortType.getValue();
		//System.out.println(sortType);
		String sortOrder = choiceBoxSortOrder.getValue();
		//Invalid sort type
		if (sortType == null) {
			lblError.setText("Invalid sort type.");
			lblError.setVisible(true);
		}
		//Valid sort type
		else {
			lblError.setVisible(false);
			//Invalid sort order
			if (sortOrder == null) {
				lblError.setText("Invalid sort order.");
				lblError.setVisible(true);
			}
			//Valid sort order
			else {
				Habits habit = new Habits();
				Sort sort = new Sort();
				@SuppressWarnings("unchecked")
				ArrayList<Habits> sortedHabits = (ArrayList<Habits>) habit.habits.clone();
				switch (sortType) {
					case "Newest":
						if (sortOrder.equals("Descending")) {
							sort.reverseArrayList(sortedHabits);
						}
						break;
						
					case "Difficulty": 
						sort.sortByDifficulty(sortedHabits);
						
						if (sortOrder.equals("Descending")) {
							System.out.println("Here");
							sort.reverseArrayList(sortedHabits);
						}
						break;
					case "Start Time":
						sort.sortByTime(sortedHabits);
						if (sortOrder.equals("Descending")) {
							sort.reverseArrayList(sortedHabits);
						}
						break;
				}
				System.out.println(sortedHabits.toString());
				reInitialize(sortedHabits);
			}
		}
		
		
	}
	
	public void switchToSceneMain(ActionEvent e) throws IOException {
		root = FXMLLoader.load(getClass().getResource("Main.fxml"));
		addSceneMain(e);
	}
	
	
	private void reInitialize(ArrayList<Habits> orderedHabits) throws IOException {
		vboxHabits.getChildren().clear();
		List<Habits> habitsToUse = (List<Habits>) orderedHabits.clone();
		for (int i = 0; i < habitsToUse.size(); i++) {
			FXMLLoader fxmlloader = new FXMLLoader();
			fxmlloader.setLocation(getClass().getResource("HabitsDesign.fxml"));
			//Add habits
			AnchorPane pane = fxmlloader.load();
			HabitsDesignController hdc = fxmlloader.getController();
			hdc.setData(habitsToUse.get(i));
			hdc.setVBoxes(vboxHabits);
			hdc.setButtonFor(orderedHabits.get(i).getActivity());
			hdc.startup(orderedHabits.get(i).getStatus());
			vboxHabits.getChildren().add(pane);

		}
		lblError.setVisible(false);
		
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
				hdc.setVBoxes(vboxHabits);
				hdc.setButtonFor(habit.habits.get(i).getActivity());
				hdc.startup(habit.habits.get(i).getStatus());
				vboxHabits.getChildren().add(pane);

			}
			
			choiceBoxSortOrder.getItems().addAll(sortOrder);
			choiceBoxSortType.getItems().addAll(sortType);
			lblError.setVisible(false);
			
			imgIcon.setVisible(false);
			lblActivityName.setVisible(false);
			lblDifficulty.setVisible(false);
			lblFrequency.setVisible(false);
			lblTimeAmount.setVisible(false);
			lblTimeStart.setVisible(false);
			rectHabit.setVisible(false);
			
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
