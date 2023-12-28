package application;

public class Habits {
	private String activity;
	private String timeStart;
	private int timeLength;
	private String frequency;
	private String difficulty;
	
	public Habits(String activity, String timeStart, int timeLength, String frequency, String difficulty) {
		super();
		this.activity = activity;
		this.timeStart = timeStart;
		this.timeLength = timeLength;
		this.frequency = frequency;
		this.difficulty = difficulty;
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
	public int getTimeLength() {
		return timeLength;
	}
	public void setTimeLength(int timeLength) {
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
