package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.SnapshotParameters;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Controller implements Initializable {
	
	@FXML 
	private ImageView imgSpace;
	
	public void test(ActionEvent e) {
		System.out.println("Test");
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
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
	}

}
