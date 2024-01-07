package application;

import java.io.FileNotFoundException;
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
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class allAchievementsController implements Initializable{

	@FXML
    private VBox vBoxAllMilestones;
	@FXML
    private Label lblDiscovered;

    @FXML
    private Label lblUndiscovered;

	
	
	@FXML
	private Parent root;
	private Scene scene;
	private Stage stage;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		try {
			loadAllAchievements();
			setDiscovered();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void setDiscovered() throws FileNotFoundException, IOException {
		Milestone milestone = new Milestone();
		int[] amountDiscovered = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		int undiscoveredCount = 0;
		for (Milestone milestoneToCheck : milestone.milestones) {
			switch (milestoneToCheck.getMilestonePlanetImg()) {
				case "TranquilPlanet":
					amountDiscovered[0]++;
					break;
				case "ProductivePlanet":
					amountDiscovered[1]++;
					break;
				case "PositivityPlanet":
					amountDiscovered[2]++;
					break;
				case "MindfulMoon":
					amountDiscovered[3]++;
					break;
				case "LearningPlanet":
					amountDiscovered[4]++;
					break;
				case "HarmoniousPlanet":
					amountDiscovered[5]++;
					break;
				case "GreenPlanet":
					amountDiscovered[6]++;
					break;
				case "GratitudePlanet":
					amountDiscovered[7]++;
					break;
				case "FitnessPlanet":
					amountDiscovered[8]++;
					break;
				case "CreativePlanet":
					amountDiscovered[9]++;
					break;
			}
		}
		for (int amount : amountDiscovered) {
			if (amount == 0) {
				undiscoveredCount++;
			}
		}
		lblUndiscovered.setText("Undiscovered: " + undiscoveredCount);
		lblDiscovered.setText("Discovered: " + (amountDiscovered.length - undiscoveredCount));
	}
	
	private void loadAllAchievements() throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		float amountInRow = (float) 5.00;
		Milestone milestone = new Milestone();
		int amountOfHBoxes = milestone.milestones.size();
		amountOfHBoxes = (int) Math.ceil(amountOfHBoxes/amountInRow);
		for (int i = 0; i < amountOfHBoxes; i++) {
			HBox hbox = new HBox(10);
			for (int j = 0; j < amountInRow; j++) {
				FXMLLoader fxmlloader = new FXMLLoader();
				fxmlloader.setLocation(getClass().getResource("spaceBanner.fxml"));
				//Add habits
				AnchorPane pane = fxmlloader.load();
				spaceBannerController sbc = fxmlloader.getController();
				if ((boolean) (j + i * amountInRow < milestone.milestones.size())) {
					sbc.setBannerFor(milestone.milestones.get((int) (j + i * amountInRow)));
					hbox.getChildren().add(pane);
				}
			}
			vBoxAllMilestones.getChildren().add(hbox);
		}
		
		
	}

	public void switchToSceneMain(ActionEvent e) throws IOException {
		root = FXMLLoader.load(getClass().getResource("Main.fxml"));
		addSceneMain(e);
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

}
