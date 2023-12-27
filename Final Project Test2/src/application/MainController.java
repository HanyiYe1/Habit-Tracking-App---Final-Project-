package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class MainController implements Initializable {
	
	@FXML 
	private ImageView imgSpace;
	@FXML
	private Stage stage;
	private Scene scene;
	private Parent root2;	
	
	
	public void switchToSceneHabit(ActionEvent e) throws IOException {
		root2 = FXMLLoader.load(getClass().getResource("Habits.fxml"));
		addScene(e);
	}
	
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		clipSpaceImage();
		
	}
	
	public void clipSpaceImage() {
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

	private void addScene(ActionEvent event){
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root2);
        String css = this.getClass().getResource("application.css").toExternalForm();
        // Set the stylesheet after the scene creation
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
     }
}
