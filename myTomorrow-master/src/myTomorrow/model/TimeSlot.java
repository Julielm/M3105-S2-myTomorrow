package myTomorrow.model;

import org.joda.time.DateTime;

/**
 * Appointment or a lesson's Time slot.
 * 
 * @author myTomorrowProject
 * @version 1.0.0
 */
public class TimeSlot {
	/** Start time of the task. */
	private DateTime startTime;
	/** End time of the task. */
	private DateTime endTime;

	/**
	 * Constructor of a time slot to initialize a time slot to null.
	 */
	public TimeSlot() {
		this.startTime = null;
		this.endTime = null;
	}

	/**
	 * Constructor of a time slot.
	 * 
	 * @param startTime
	 * @param endTime
	 */
	public TimeSlot(DateTime startTime, DateTime endTime) {
		this.startTime = startTime;
		this.endTime = endTime;

	}

	/**
	 * Set in the time put in parameters a predefined time.
	 * 
	 * @param timeToSet
	 * @param time
	 */
	public void setTime(DateTime timeToSet, DateTime time) {
		timeToSet = time;
	}

	/**
	 * Getter for the start time.
	 * 
	 * @return the startTime
	 */
	public DateTime getStartTime() {
		return this.startTime;
	}

	/**
	 * Getter for the endTime.
	 * 
	 * @return the endTime
	 */
	public DateTime getEndTime() {
		return this.endTime;
	}

	/**
	 * Set the start time with the time in parameter.
	 * 
	 * @param startTime
	 *            the startTime to set
	 */
	public void setStartTime(DateTime startTime) {
		this.startTime = startTime;
	}

	/**
	 * Set the end time with the time in parameter.
	 * 
	 * @param endTime
	 *            the endTime to set
	 */
	public void setEndTime(DateTime endTime) {
		this.endTime = endTime;
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("Le ");
		str.append(this.getStartTime().getDayOfMonth());
		str.append("/");
		str.append(this.getStartTime().getMonthOfYear());
		str.append("/");
		str.append(this.getStartTime().getYear());
		return str.toString();
	}

	/**
	 * Check if a timeslot is before an other.
	 * 
	 * @param timeslot
	 * @return a boolean
	 */
	public boolean isBefore(TimeSlot timeslot) {
		return this.startTime.isBefore(timeslot.startTime);
	}

	/**
	 * Display a time.
	 * 
	 * @param time
	 * @return a string
	 */
	public String toString(DateTime time) {
		StringBuilder str = new StringBuilder();
		str.append(time.getHourOfDay());
		str.append("h");
		int minutes = time.getMinuteOfHour();
		if (minutes < 10) {
			str.append("0" + minutes);
		} else
			str.append(time.getMinuteOfHour());
		return str.toString();
	}
}
