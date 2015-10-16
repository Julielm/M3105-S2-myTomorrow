package myTomorrow.model;

import org.joda.time.DateTime;

/**
 * Day where a person can take appointment or lessons.
 * 
 * @author myTomorrowProject
 * @version 1.0.0
 */
public class Day extends TimeSlot {
	/**
	 * Start hour of the day.
	 */
	public static final int START_HOUR_BY_DEFAULT = 8;
	/**
	 * End hour of the day.
	 */
	public static final int END_HOUR_BY_DEFAULT = 18;

	/**
	 * Constructor of a day.
	 * 
	 * @param day
	 * @param month
	 * @param year
	 */
	public Day(int day, int month, int year) {
		super(new DateTime(year, month, day, START_HOUR_BY_DEFAULT, 0),
				new DateTime(year, month, day, END_HOUR_BY_DEFAULT, 0));
	}

}
