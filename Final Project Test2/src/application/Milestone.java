package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Milestone {
	String milestonePlanetImg;
	static boolean exploredToday = false;
	
	public static boolean isExploredToday() {
		return exploredToday;
	}

	public void setExploredToday() throws FileNotFoundException, IOException {
		Streak streak = new Streak();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

		if (streak.dates.size() >= 1) {
			String date = streak.dates.get(0);
			LocalDate otherDate = LocalDate.parse(date, formatter);
	        // Get today's date
	        LocalDate today = LocalDate.now();
	        
	        if (!today.equals(otherDate)) {
	        	Milestone.exploredToday = false;
	        }
	        else {
	        	Milestone.exploredToday = true;
	        }
		}
		else {
			Milestone.exploredToday = false;
		}
	}

	public String getMilestonePlanetImg() {
		return milestonePlanetImg;
	}

	public void setMilestonePlanetImg(String milestonePlanetImg) {
		this.milestonePlanetImg = milestonePlanetImg;
	}

	public ArrayList<Milestone> milestones = new ArrayList<>();
	
	
	public Milestone() throws FileNotFoundException, IOException {
		this.milestones = getMilestones();
	}
	
	public Milestone(String milestonePlanetImg) {
		this.milestonePlanetImg = milestonePlanetImg;
	}
	
	public ArrayList<Milestone> getMilestones() throws FileNotFoundException, IOException {
		try (BufferedReader br = new BufferedReader(new FileReader("bin/milestones.txt"))) {
		    String line;
		    String[] info;
		    while ((line = br.readLine()) != null) {
		    	// process the line.
		    	milestones.add(new Milestone(line));
		    }
		    return milestones;
		}
	}
	
	public void clearMilestones() throws FileNotFoundException {
		PrintWriter pw = new PrintWriter("bin/milestones.txt");
		pw.close();
	}
	
	public void addMilestones(String milestoneInfo) throws IOException {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("bin/milestones.txt", true))){
			writer.write(milestoneInfo);
			writer.newLine();
		}
		catch(IOException ex){
			ex.printStackTrace();
		}
	}
	
	@Override
	public String toString() {
		String text = milestonePlanetImg;
		return text;
	}
}
