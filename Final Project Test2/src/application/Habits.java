package application;

public class Habits {
	private String activity;
	private int amountTimes;
	private int amountDays;
	private int smallestUnitAmount;
	private String smallestUnitType;
	private String reminderTime;
	private boolean sameTimeForAllDays;
	private String[] daysOn;
	
	public Habits(String activity, int amountTimes, int amountDays, int smallestUnitAmount, String smallestUnitType, String reminderTime, boolean sameTimeAllDay, String[] daysOn) {
		this.activity = activity;
		this.amountTimes = amountTimes;
		this.amountDays = amountDays;
		this.smallestUnitAmount = smallestUnitAmount;
		this.smallestUnitType = smallestUnitType;
		this.reminderTime = reminderTime;
		this.sameTimeForAllDays = sameTimeAllDay;
		this.daysOn = daysOn;
	}
	
	
	
	public String getActivity() {
		return activity;
	}
	public void setActivity(String activity) {
		this.activity = activity;
	}
	public int getAmountTimes() {
		return amountTimes;
	}
	public void setAmountTimes(int amountTimes) {
		this.amountTimes = amountTimes;
	}
	public int getAmountDays() {
		return amountDays;
	}
	public void setAmountDays(int amountDays) {
		this.amountDays = amountDays;
	}
	public int getSmallestUnitAmount() {
		return smallestUnitAmount;
	}
	public void setSmallestUnitAmount(int smallestUnitAmount) {
		this.smallestUnitAmount = smallestUnitAmount;
	}
	public String getSmallestUnitType() {
		return smallestUnitType;
	}
	public void setSmallestUnitType(String smallestUnitType) {
		this.smallestUnitType = smallestUnitType;
	}
	public String getReminderTime() {
		return reminderTime;
	}
	public void setReminderTime(String reminderTime) {
		this.reminderTime = reminderTime;
	}
	public boolean isSameTimeForAllDays() {
		return sameTimeForAllDays;
	}
	public void setSameTimeForAllDays(boolean sameTimeForAllDays) {
		this.sameTimeForAllDays = sameTimeForAllDays;
	}
	public String[] getDaysOn() {
		return daysOn;
	}
	public void setDaysOn(String[] daysOn) {
		this.daysOn = daysOn;
	}
	
	
}
