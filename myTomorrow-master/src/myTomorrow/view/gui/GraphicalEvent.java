package myTomorrow.view.gui;

import javax.swing.JButton;

import org.joda.time.DateTime;
import org.joda.time.Duration;

import myTomorrow.model.ScheduledEvent;

/**
 * Create a graphical event associated with an event.
 * 
 * @author myTomorrow
 * @version 1.0.0
 */
public class GraphicalEvent extends JButton {
	/**
	 * SerialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Width of the graphical object.
	 */
	public static final int EVENT_WIDTH = 132;

	/**
	 * Event associated with the graphical object.
	 */
	private ScheduledEvent associatedEvent;

	/**
	 * Create the graphical object associated with the event.
	 * 
	 * @param event
	 */
	public GraphicalEvent(ScheduledEvent event) {
		this.associatedEvent = event;

		DateTime startTime = this.associatedEvent.getTimeSlot().getStartTime();
		int dayOfWeek = startTime.getDayOfWeek();
		int hours = startTime.getHourOfDay();
		int minutes = startTime.getMinuteOfHour();

		Duration durationOfEvent = new Duration(startTime, this.associatedEvent
				.getTimeSlot().getEndTime());

		this.setBounds(58 + ((dayOfWeek - 1) * 133), 38 + ((hours - 8) * 61)
				+ minutes, EVENT_WIDTH, durationOfEvent.toStandardMinutes()
				.getMinutes());

	}
}
