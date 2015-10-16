package myTomorrow.test;

import myTomorrow.model.Person;
import junit.framework.TestCase;

/**
 * Tests for the Person Class
 * @author myTomorrowProject
 * @version 1.0.0
 */
public class PersonTest extends TestCase {
	/**
	 * Constructor of PersonTest.
	 * @param name
	 */
	public PersonTest(String name) {
		super(name); 
	}
	/**
	 * Cases for the test equals.
	 */
	public void testEquals(){
		Person person = new Person("Lacondemine","Virgil");
		boolean answer = person.equals(new Person("Leclerc","Julie"));
		assertEquals(answer, false);
		answer = person.equals(new Person("Virgil","Lacondemine"));
		assertEquals(answer, false);
		answer = person.equals(new Person("Lacondemine","Virgil"));
		assertEquals(answer, true);
	}
}
