package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class spaceBannerController  implements Initializable{
	@FXML
    private ImageView imgBackground;
	@FXML
	private ImageView imgPlanet;
	@FXML
	private Label lblPlanetName;
	
	public void setBannerFor(Milestone milestone) {
		String planetImageName = "/achievementImages/" + milestone.getMilestonePlanetImg() + ".png";
		Image planetImage = new Image(getClass().getResourceAsStream(planetImageName));
		imgPlanet.setImage(planetImage);
		setBannerName(milestone.getMilestonePlanetImg());
	}
	
	private void setBannerName(String planetName) {
		switch (planetName) {
			case "TranquilPlanet":
				lblPlanetName.setText("Tranquilterra");
				break;
			case "ProductivePlanet":
				lblPlanetName.setText("Productopia");
				break;
			case "PositivityPlanet":
				lblPlanetName.setText("Smilenox");
				break;
			case "MindfulMoon":
				lblPlanetName.setText("Mindful Moon");
				break;
			case "LearningPlanet":
				lblPlanetName.setText("Learnoria");
				break;
			case "HarmoniousPlanet":
				lblPlanetName.setText("Harmony Haven");
				break;
			case "GreenPlanet":
				lblPlanetName.setText("Greenhaven");
				break;
			case "GratitudePlanet":
				lblPlanetName.setText("Gratituia");
				break;
			case "FitnessPlanet":
				lblPlanetName.setText("Fitonia");
				break;
			case "CreativePlanet":
				lblPlanetName.setText("Creativia");
				break;
		}
	}
	
	private void clipSpaceImage() {
		// set a clip to apply rounded border to the original image.
        Rectangle clip = new Rectangle(
        		imgBackground.getFitWidth(), imgBackground.getFitHeight()
        );
        clip.setArcWidth(50);
        clip.setArcHeight(50);
        imgBackground.setClip(clip);

        // snapshot the rounded image.
        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(Color.TRANSPARENT);
        WritableImage image = imgBackground.snapshot(parameters, null);

        // remove the rounding clip so that our effect can show through.
        imgBackground.setClip(null);

        // store the rounded image in the imageView.
        imgBackground.setImage(image);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		clipSpaceImage();
	}
}
