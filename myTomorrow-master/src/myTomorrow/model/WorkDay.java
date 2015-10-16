package myTomorrow.model;

import org.joda.time.DateTime;

public class WorkDay extends TimeSlot {

	public static final int START_HOUR_BY_DEFAULT = 8;

	public static final int END_HOUR_BY_DEFAULT = 18;

	public WorkDay(int day, int month, int year) {
		super(new DateTime(year, month, day, START_HOUR_BY_DEFAULT, 0),
				new DateTime(year, month, day, END_HOUR_BY_DEFAULT, 0));
	}

}
