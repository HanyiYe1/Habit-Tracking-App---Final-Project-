package application;
	
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
			Parent root2 = FXMLLoader.load(getClass().getResource("Habits.fxml"));
			Scene main = new Scene(root);
			Scene habit = new Scene(root2);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			String css = this.getClass().getResource("application.css").toExternalForm();
			main.getStylesheets().add(css);
			habit.getStylesheets().add(css);
			
			primaryStage.setScene(main);
			primaryStage.setTitle("Hanyi Ye's Habit Tracking App");
			primaryStage.show();
			primaryStage.setResizable(false);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		/*
		System.out.println("Doing");
		try {
	      File myObj = new File("bin/habits.txt");
	      Scanner myReader = new Scanner(myObj);
	      System.out.println(myObj);
	      while (myReader.hasNextLine()) {
	        String data = myReader.nextLine();
	        System.out.println(data);
	      }
	      myReader.close();
	    } catch (FileNotFoundException e) {
	      System.out.println("An error occurred.");
	      e.printStackTrace();
	    }
	    */
		launch(args);
		
	}
}
