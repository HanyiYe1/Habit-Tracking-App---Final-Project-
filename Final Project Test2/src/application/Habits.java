package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Habits {
	private String status;
	private String activity;
	private String timeStart;
	private String timeLength;
	private String frequency;
	private String difficulty;
	private static int percentComplete;
	private static int streak = 0;
	public ArrayList<Habits> habits = new ArrayList<>();
	
	public Habits() throws FileNotFoundException, IOException {
		this.habits = this.getHabits();
		//System.out.println(habits);
		//System.out.println(habits.size());
	}
	
	public Habits(String status, String activity, String timeStart, String timeLength, String frequency, String difficulty) {
		super();
		this.status = status;
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
		    	habits.add(new Habits(info[0], info[1], info[2], info[3], info[4], info[5]));
		    }
		    return habits;
		}
	}
	
	public void clearHabits() throws FileNotFoundException {
		PrintWriter pw = new PrintWriter("bin/habits.txt");
		pw.close();
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
		String text = status + "," + activity + "," + timeStart + "," + timeLength + "," + frequency + "," + difficulty;
		return text;
	}
	
	public static int getPercentComplete() {
		return percentComplete;
	}

	public static void setPercentComplete(int percentComplete) {
		Habits.percentComplete = percentComplete;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public static int getStreak() {
		return streak;
	}

	public static void setStreak(int streak) {
		Habits.streak = streak;
	}
	
}
