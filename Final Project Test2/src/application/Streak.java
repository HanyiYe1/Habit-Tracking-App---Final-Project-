package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class Streak {
	public static int streak = 0;
	public ArrayList<String> dates = new ArrayList<>();
	
	public Streak() throws FileNotFoundException, IOException {
		this.dates = getDates();
	}
	
	public void calculateStreak() throws FileNotFoundException, IOException {
		//dates = getDates();
		System.out.println(dates.toString());
		//Reverse the dates
		ArrayList<String> reversedDates = new ArrayList<String>();
		for (int i = dates.size() - 1; i >= 0; i--) {
            // Append the elements in reverse order
			reversedDates.add(dates.get(i));
        }
		//Check for consecutive dates
		int consecutiveDates = 0;
		for (int i = 0; i < reversedDates.size(); i++) {
			System.out.println("Index: " + (i + 1));
			if ( (i + 1) < reversedDates.size()) {
				System.out.println("Going in here");
				if (areConsecutive(reversedDates.get(i), reversedDates.get(i + 1))) {
					consecutiveDates++;
				}
				else {
					consecutiveDates++;
					break;
				}
			} else {
				consecutiveDates++;
			}
		}
		streak = consecutiveDates;
		System.out.println(streak);
	}
	
	private boolean areConsecutive(String dateString1, String dateString2) {
        // Define the date format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        // Parse strings into LocalDate objects
        LocalDate date1 = LocalDate.parse(dateString1, formatter);
        LocalDate date2 = LocalDate.parse(dateString2, formatter);

        // Check if the second date immediately follows the first date
        return date2.equals(date1.plusDays(1));
    }
	
	public void addDate(String date) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("bin/dates.txt", true))){
			writer.write(date);
			writer.newLine();
		}
		catch(IOException ex){
			ex.printStackTrace();
		}
	}
	
	public ArrayList<String> getDates() throws FileNotFoundException, IOException {
		try (BufferedReader br = new BufferedReader(new FileReader("bin/dates.txt"))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		    	// process the line.
		    	dates.add(line);
		    	System.out.println(dates);
		    }
		    return dates;
		}
	}
	
	public static void increaseStreak() {
		Streak.streak++;
	}
	
	public static int getStreak() {
		return streak;
	}

	public static void setStreak(int streak) {
		Streak.streak = streak;
	}
	
}
