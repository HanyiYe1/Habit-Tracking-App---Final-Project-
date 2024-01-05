package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class Sort {
	public void sortByDifficulty(ArrayList<Habits> habitsToSort) {
        for (int j = 0; j < habitsToSort.size(); j++) {
            for (int i = j + 1; i < habitsToSort.size(); i++) {
                // comparing adjacent strings
                if ( Integer.parseInt(habitsToSort.get(j).getDifficulty()) > Integer.parseInt(habitsToSort.get(i).getDifficulty())) {
                    Collections.swap(habitsToSort, i, j);
                } 
            }
        }
	}
	
	public void sortByTime(ArrayList<Habits> habitsToSort) {
		for (int i = 0; i < habitsToSort.size() - 1; i++) {
            for (int j = 0; j < habitsToSort.size() - i - 1; j++) {
                // Compare adjacent dates and swap if needed
            	if (compareTimes(habitsToSort.get(j).getTimeStart(), habitsToSort.get(j + 1).getTimeStart()) > 0) {
                    // Swap elements
            		Collections.swap(habitsToSort, j, j + 1);
                }
            }
        }
	}
	
	private int compareTimes(String timeStart, String timeStart2) {
		// TODO Auto-generated method stub
		try {
            SimpleDateFormat format = new SimpleDateFormat("h:mma");
            Date date1 = format.parse(timeStart);
            Date date2 = format.parse(timeStart2);
            
            return date1.compareTo(date2);
        } catch (ParseException e) {
            // Handle the exception if parsing fails
            e.printStackTrace();
            return 0;
        }
	}
	
	public void reverseArrayList(ArrayList<Habits> habitsToSort) {
		Collections.reverse(habitsToSort);
	}
}
