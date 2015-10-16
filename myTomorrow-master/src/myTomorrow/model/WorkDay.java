package myTomorrow.model;

import org.joda.time.DateTime;

public class WorkDay extends TimeSlot {

	public static final int START_HOUR_BY_DEFAULT = 8;

	public static final int END_HOUR_BY_DEFAULT = 18;

	public static final int START_TIME_AFTERNOON = 14;

	public static final int END_TIME_MORNING = 12;

	public WorkDay(int day, int month, int year) {
		super(new DateTime(year, month, day, START_HOUR_BY_DEFAULT, 0),
				new DateTime(year, month, day, END_HOUR_BY_DEFAULT, 0));
	}
	
	public TimeSlot getHalfDay(boolean isMorning){
		if (isMorning){
			return new TimeSlot(new DateTime(this.getStartTime().getYear(), 
					this.getStartTime().getMonthOfYear(), this.getStartTime().getDayOfMonth(), START_HOUR_BY_DEFAULT, 0)
			,new DateTime(this.getStartTime().getYear(), this.getStartTime().getMonthOfYear(), this.getStartTime().getDayOfMonth(), END_TIME_MORNING, 0));
		}
		return new TimeSlot(new DateTime(this.getStartTime().getYear(), 
				this.getStartTime().getMonthOfYear(), this.getStartTime().getDayOfMonth(), START_TIME_AFTERNOON, 0)
		,new DateTime(this.getStartTime().getYear(), this.getStartTime().getMonthOfYear(), this.getStartTime().getDayOfMonth(), END_HOUR_BY_DEFAULT, 0));
	}

}
