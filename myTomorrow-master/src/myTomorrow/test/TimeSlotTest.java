package myTomorrow.test;

import myTomorrow.model.TimeSlot;

import org.joda.time.DateTime;

import junit.framework.TestCase;

/**
 * Tests for the TimeSlot Class
 * @author myTomorrowProject
 * @version 1.0.0
 */
public class TimeSlotTest extends TestCase {
	/**
	 * Constructor of TimeSlotTest.
	 * @param name
	 */
	public TimeSlotTest(String name) {
		super(name); 
	}
	
	/**
	 * Cases for the test isBefore.
	 */
	public void testIsBefore(){
		TimeSlot timeSlot = new TimeSlot(new DateTime(2015,06,10,8,0), new DateTime(2015,06,10,9,0));
		TimeSlot timeSlot2 = new TimeSlot(new DateTime(2015,06,10,8,0), new DateTime(2015,06,10,9,0));
		boolean answer = timeSlot.isBefore(timeSlot2);
		assertEquals(answer, false);
		timeSlot2 = new TimeSlot(new DateTime(2015,06,11,8,0), new DateTime(2015,06,11,9,0));
		answer = timeSlot.isBefore(timeSlot2);
		assertEquals(answer, true);
		answer = timeSlot2.isBefore(timeSlot);
		assertEquals(answer, false);
	}
}
