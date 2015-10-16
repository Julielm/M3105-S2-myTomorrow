package myTomorrow.model;

/**
 * Appointment at person's home.
 * 
 * @author myTomorrowProject
 * @version 1.0.0
 */
public class Appointment extends ScheduledEvent {
	/** Person who takes the appointment. */
	private final Person person;

	/**
	 * Constructor of an appointment.
	 * 
	 * @param person
	 * @param timeSlot
	 */
	public Appointment(Person person, TimeSlot timeSlot) {
		super(timeSlot);
		this.person = person;
	}

	/**
	 * Getter for the person.
	 * 
	 * @return the person
	 */
	public Person getPerson() {
		return this.person;
	}

}
