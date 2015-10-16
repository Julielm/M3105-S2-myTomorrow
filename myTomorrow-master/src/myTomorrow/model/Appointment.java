package myTomorrow.model;

/**
 * Appointment at person's home.
 * 
 * @author myTomorrowProject
 * @version 1.0.0
 */
public class Appointment extends ScheduledEvent {
	private final Person attendee;
	
	public Appointment(Person attendee, TimeSlot timeSlot) {
		super(timeSlot);
		this.attendee = attendee;
	}

	public Person getPerson() {
		return this.attendee;
	}
}
