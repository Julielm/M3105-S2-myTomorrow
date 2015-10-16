package myTomorrow.test;

import myTomorrow.model.Lesson;
import myTomorrow.model.Person;
import junit.framework.TestCase;

/**
 * Tests for the Lesson Class
 * @author myTomorrowProject
 * @version 1.0.0
 */
public class LessonTest extends TestCase {
	/**
	 * Constructor of LessonTest.
	 * @param name
	 */
	public LessonTest(String name) {
		super(name); 
	}
	
	
	/**
	 * Cases for the test hasTheSameTitle.
	 */
	public void testHasTheSameTitle(){
		Lesson lesson = new Lesson("Base poo", null);
		boolean answer = lesson.hasTheSameTitle("base poo");
		assertEquals(answer, true);
		answer = lesson.hasTheSameTitle("BASE POO");
		assertEquals(answer, true);
		answer = lesson.hasTheSameTitle("Base POO");
		assertEquals(answer, true);
		answer = lesson.hasTheSameTitle("BASEPOO");
		assertEquals(answer, false);
		answer = lesson.hasTheSameTitle("POO");
		assertEquals(answer, false);
		answer = lesson.hasTheSameTitle("Coo");
		assertEquals(answer, false);
	}
	
	/**
	 * Cases for the test hasFreePlace.
	 */
	public void testHasFreePlace(){
		Lesson lesson = new Lesson("Projet", null);
		boolean answer = lesson.hasFreePlace();
		assertEquals(answer, true);
		lesson.setPersonList(new Person("Bourdon","Laetitia"));
		answer = lesson.hasFreePlace();
		assertEquals(answer, true);
		lesson.setPersonList(new Person("Montcarmel","Elodie"));
		answer = lesson.hasFreePlace();
		assertEquals(answer, true);
		lesson.setPersonList(new Person("Leprunier","Hugo"));
		answer = lesson.hasFreePlace();
		assertEquals(answer, false);
	}
	
	/**
	 * Cases for the test personIndex.
	 */
	public void testPersonIndex(){
		Lesson lesson = new Lesson("Projet", null);
		lesson.setPersonList(new Person("Bourdon","Laetitia"));
		lesson.setPersonList(new Person("Montcarmel","Elodie"));
		int answer = lesson.personIndex(new Person("Leprunier","Hugo"));
		assertEquals("Le test devrait renvoyer -1", answer, -1);
		answer = lesson.personIndex(new Person("Bourdon","Laetitia"));
		assertEquals("Le test devrait renvoyer 0", answer, 0);
		answer = lesson.personIndex(new Person("Montcarmel","Elodie"));
		assertEquals("Le test devrait renvoyer 1", answer, 1);
	}
	
}
