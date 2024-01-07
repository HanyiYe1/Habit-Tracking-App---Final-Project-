package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ExplorePlanetsController implements Initializable {
	
	@FXML
	private Stage stage;
	private Scene scene;
	private Parent root;

    @FXML
    private Button btnContinue;

    @FXML
    private ImageView imgBackground;

    @FXML
    private ImageView imgPlanet;

    @FXML
    private Label lblDescription;

    @FXML
    private Label lblDiscovery;

    @FXML
    private Label lblPlanetName;
	@FXML
	private HBox tempImport;
	@FXML
	private MediaView cutscene;
	@FXML
	private Button btnSkipAnimation;
	@FXML
	private Rectangle blackout;
	private File file;
	private Media media;
	private MediaPlayer mediaPlayer;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			btnContinue.setVisible(false);
			imgBackground.setVisible(false);
			imgPlanet.setVisible(false);
			lblDescription.setVisible(false);
			lblDiscovery.setVisible(false);
			lblPlanetName.setVisible(false);
			
			
			addMilestone();
			file = new File("bin/ExploringPlanets/ExploringAnimation.mp4");
			media = new Media(file.toURI().toString());
			mediaPlayer = new MediaPlayer(media);
			cutscene.setMediaPlayer(mediaPlayer);
			mediaPlayer.setVolume(0.5);
			mediaPlayer.play();
			
			 // Create a scheduled executor service with a single thread
	        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
	        
	        // Define the delay in seconds
	        int delayInSeconds = 25;

	        // Schedule a task to run after the specified delay
	        executorService.schedule(() -> {
	            // Code to be executed after the specified delay
	        	blackoutScreen();
	        }, delayInSeconds, TimeUnit.SECONDS);

	        // Shutdown the executor service when it is no longer needed
	        executorService.shutdown();
	        
	        
	        
	        
	        
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void addMilestone() throws FileNotFoundException, IOException {
		String[] potentialDiscoveries = {"TranquilPlanet", "ProductivePlanet", "PositivityPlanet", "MindfulMoon", "LearningPlanet", "HarmoniousPlanet", "GreenPlanet", "GratitudePlanet", "FitnessPlanet", "CreativePlanet"};
		Milestone milestone = new Milestone();
		Random random = new Random();
        // Generate a random number from 0 to 9 (inclusive)
        int randomNumber = random.nextInt(10);
        milestone.milestones.add(new Milestone(potentialDiscoveries[randomNumber]));
        milestone.clearMilestones();
        for (Milestone milestoneToAdd : milestone.milestones) {
        	milestone.addMilestones(milestoneToAdd.toString());
        }
        
	}
	
	public void displayNewDiscovery() throws IOException {
		btnContinue.setVisible(true);
		imgBackground.setVisible(true);
		imgPlanet.setVisible(true);
		lblDescription.setVisible(true);
		lblDiscovery.setVisible(true);
		lblPlanetName.setVisible(true);
		btnSkipAnimation.setVisible(false);
		
		Milestone milestone = new Milestone();
		
		Platform.runLater(new Runnable() {
		    @Override
		    public void run() {
		        // do your GUI stuff here
		    	Milestone milestoneToUse = milestone.milestones.get(milestone.milestones.size() - 1);
				String planetImageName = "/achievementImages/" + milestoneToUse.getMilestonePlanetImg() + ".png";
				Image planetImage = new Image(getClass().getResourceAsStream(planetImageName));
				imgPlanet.setImage(planetImage);
				String planetName = milestoneToUse.getMilestonePlanetImg();
				System.out.println(planetName);
				switch (planetName) {
					case "TranquilPlanet":
						lblPlanetName.setText("Tranquilterra");
						lblDescription.setText("This serene planet rewards those who find peace within themselves.");
						break;
					case "ProductivePlanet":
						lblPlanetName.setText("Productopia");
						lblDescription.setText("This planet celebrates effective time management and task accomplishment.");
						break;
					case "PositivityPlanet":
						lblPlanetName.setText("Smilenox");
						lblDescription.setText("This planet thrives on smiles, laughter, and acts of kindness.");
						break;
					case "MindfulMoon":
						lblPlanetName.setText("Mindful Moon");
						lblDescription.setText("This tranquil celestial body celebrates mindfulness, meditation, and living in the present moment.");
						break;
					case "LearningPlanet":
						lblPlanetName.setText("Learnoria");
						lblDescription.setText("This planet values continuous learning and personal growth.");
						break;
					case "HarmoniousPlanet":
						lblPlanetName.setText("Harmony Haven");
						lblDescription.setText("This planet values positive social interactions and empathy.");
						break;
					case "GreenPlanet":
						lblPlanetName.setText("Greenhaven");
						lblDescription.setText("This eco-friendly planet rewards efforts to reduce, reuse, and recycle.");
						break;
					case "GratitudePlanet":
						lblPlanetName.setText("Gratituia");
						lblDescription.setText("This planet appreciates those who find joy in the simple things and express thankfulness.");
						break;
					case "FitnessPlanet":
						lblPlanetName.setText("Fitonia");
						lblDescription.setText("This planet celebrates a healthy and active lifestyle, motivating users to stay physically active.");
						break;
					case "CreativePlanet":
						lblPlanetName.setText("Creativia");
						lblDescription.setText("This planet encourages artistic expression and imaginative thinking.");
						break;
				}
		    }
		});
		
	}
	
	public void blackoutScreen() {
		ScheduledExecutorService executorService2 = Executors.newSingleThreadScheduledExecutor();
		FadeTransition fade = new FadeTransition();
		fade.setNode(blackout);
		fade.setDuration(Duration.millis(1000));
		fade.setCycleCount(1);
		fade.setInterpolator(Interpolator.LINEAR);
		fade.setFromValue(0);
		fade.setToValue(1);
		fade.play();
		executorService2.schedule(() -> {
            // Code to be executed after the specified delay
    		mediaPlayer.stop();
    		try {
				displayNewDiscovery();
				
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }, 1, TimeUnit.SECONDS);
		executorService2.shutdown();
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
