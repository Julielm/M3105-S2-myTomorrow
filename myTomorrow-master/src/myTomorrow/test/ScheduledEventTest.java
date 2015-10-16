package myTomorrow.test;

import org.joda.time.DateTime;

import myTomorrow.model.ScheduledEvent;
import myTomorrow.model.TimeSlot;
import junit.framework.TestCase;

/**
 * Tests for the ScheduledEvent Class
 * @author myTomorrowProject
 * @version 1.0.0
 */
public class ScheduledEventTest extends TestCase {
	/**
	 * Constructor of ScheduledEventTest.
	 * @param name
	 */
	public ScheduledEventTest(String name) {
		super(name); 
	}
	
	/**
	 * Cases for the test isBefore.
	 */
	public void testIsBefore(){
		ScheduledEvent event = new ScheduledEvent(new TimeSlot(new DateTime(2015,06,10,8,0), new DateTime(2015,06,10,9,0)));
		ScheduledEvent event2 = new ScheduledEvent(new TimeSlot(new DateTime(2015,06,10,8,0), new DateTime(2015,06,10,9,0)));
		boolean answer = event.isBefore(event2);
		assertEquals(answer, false);
		event2 = new ScheduledEvent(new TimeSlot(new DateTime(2015,06,11,8,0), new DateTime(2015,06,11,9,0)));
		answer = event.isBefore(event2);
		assertEquals(answer, true);
		answer = event2.isBefore(event);
		assertEquals(answer, false);
	}
}
