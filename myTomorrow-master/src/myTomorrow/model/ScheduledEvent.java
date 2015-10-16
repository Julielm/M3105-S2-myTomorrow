package myTomorrow.model;

/**
 * Scheduled event.
 * 
 * @author myTomorrowProject
 * @version 1.0.0
 */
public class ScheduledEvent {
	/**
	 * Time slot of the scheduled event.
	 */
	private TimeSlot timeSlot;

	/**
	 * Constructor of a schedule event.
	 * 
	 * @param timeSlot
	 */
	public ScheduledEvent(TimeSlot timeSlot) {
		this.timeSlot = timeSlot;
	}

	/**
	 * Getter for the time slot.
	 * 
	 * @return the time slot
	 */
	public TimeSlot getTimeSlot() {
		return this.timeSlot;
	}

	/**
	 * Setter for the time slot.
	 * 
	 * @param time
	 */
	public void setTimeSlot(TimeSlot time) {
		this.getTimeSlot().setStartTime(time.getStartTime());
		this.getTimeSlot().setEndTime((time.getEndTime()));
	}

	/**
	 * Test if the scheduled event is before one put in parameter.
	 * 
	 * @param event
	 * @return a boolean
	 */
	public boolean isBefore(ScheduledEvent event) {
		return this.timeSlot.isBefore(event.timeSlot);
	}

}
