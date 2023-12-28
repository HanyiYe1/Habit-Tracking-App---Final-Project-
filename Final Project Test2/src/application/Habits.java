package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Scanner;

public class Habits {
	private String activity;
	private String timeStart;
	private String timeLength;
	private String frequency;
	private String difficulty;
	private ArrayList<Habits> habits = new ArrayList<>();
	
	public Habits() throws FileNotFoundException, IOException {
		this.habits = this.getHabits();
		System.out.println(habits);
	}
	
	public Habits(String activity, String timeStart, String timeLength, String frequency, String difficulty) {
		super();
		this.activity = activity;
		this.timeStart = timeStart;
		this.timeLength = timeLength;
		this.frequency = frequency;
		this.difficulty = difficulty;
	}


	public ArrayList<Habits> getHabits() throws FileNotFoundException, IOException {
		try (BufferedReader br = new BufferedReader(new FileReader("bin/habits.txt"))) {
		    String line;
		    String[] info;
		    while ((line = br.readLine()) != null) {
		    	// process the line.
		    	info = line.split(",");
		    	habits.add(new Habits(info[0], info[1], info[2], info[3], info[4]));
		    }
		    return habits;
		}
	}
	
	
	public void addHabit(String habit) throws IOException {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("bin/habits.txt", true))){
			  writer.write(habit);
			  writer.newLine();
			}
			catch(IOException ex){
			  ex.printStackTrace();
			}
	}
	
	@Override
	public String toString() {
		String text = activity + "," + timeStart + "," + timeLength + "," + frequency + "," + difficulty;
		return text;
	}
	
	public String getActivity() {
		return activity;
	}
	
	public void setActivity(String activity) {
		this.activity = activity;
	}
	
	public String getTimeStart() {
		return timeStart;
	}
	
	public void setTimeStart(String timeStart) {
		this.timeStart = timeStart;
	}
	
	public String getTimeLength() {
		return timeLength;
	}
	
	public void setTimeLength(String timeLength) {
		this.timeLength = timeLength;
	}
	
	public String getFrequency() {
		return frequency;
	}
	
	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}
	
	public String getDifficulty() {
		return difficulty;
	}
	
	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}
	
}
