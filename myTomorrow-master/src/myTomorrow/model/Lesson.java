package myTomorrow.model;

import java.util.LinkedList;
import java.util.List;

public class Lesson extends ScheduledEvent {
	
	public static final int DEFAULT_MAX_PERS_NB = 3;

	public static final int DEFAULT_PERS_NB = 0;

	private final String title;

	private final int maxAttendeesNb;

	private int attendeesNb;

	private final List<Person> attendeesList;

	public Lesson(String title, TimeSlot timeSlot) {
		super(timeSlot);
		this.title = title;
		this.maxAttendeesNb = DEFAULT_MAX_PERS_NB;
		this.attendeesNb = DEFAULT_PERS_NB;
		this.attendeesList = new LinkedList<Person>();
	}

	public Lesson(String title, TimeSlot timeSlot, List<Person> attendees, int attendeesNb) {
		super(timeSlot);
		this.title = title;
		this.maxAttendeesNb = DEFAULT_MAX_PERS_NB;
		this.attendeesNb = DEFAULT_PERS_NB;
		this.attendeesList = attendees;
	}

	public String getTitle() {
		return this.title;
	}

	public int getMaxPersNb() {
		return this.maxAttendeesNb;
	}

	public int getAttendeesNb() {
		return this.attendeesNb;
	}

	public List<Person> getAttendeesList() {
		return this.attendeesList;
	}


	public boolean hasTheSameTitle(String title) {
		return this.title.equalsIgnoreCase(title);
	}


	public boolean hasFreePlace() {
		if (this.attendeesNb < DEFAULT_MAX_PERS_NB) {
			return true;
		}
		return false;
	}

	public void setAttendeesList(Person person) {
		if (this.attendeesNb < DEFAULT_MAX_PERS_NB){
			this.attendeesList.add(person);
			this.attendeesNb++;
		}
	}


	public int personIndex(Person person) {
		if (!this.attendeesList.isEmpty()) {
			int index = 0;
			Person currentPerson = this.attendeesList.get(index);
			while (index + 1 < this.attendeesList.size()
					&& !currentPerson.equals(person)) {
				index++;
				currentPerson = this.attendeesList.get(index);
			}
			if (currentPerson.equals(person))
				return index;
		}
		return -1;
	}

	/**
	 * Delete person in the lesson at the index put in parameter.
	 * 
	 * @param personIndex
	 */
	public void remove(int personIndex) {
		this.attendeesList.remove(personIndex);
		this.attendeesNb--;
	}

	/**
	 * String to add a in the file composed with persons of the lesson.
	 * 
	 * @return a string
	 */
	public String attendees() {
		StringBuilder str = new StringBuilder();
		for (Person person : this.attendeesList) {
			str.append(person.personInFile());
			str.append(";");
		}
		return str.toString();
	}

	/**
	 * Do a string with present persons in the lesson.
	 * 
	 * @return a string
	 */
	public String displayPersons() {
		StringBuilder str = new StringBuilder();
		str.append("<html> <body>");
		for (Person person : this.attendeesList) {
			str.append(person + "<br>");
		}
		str.append("</body></html>");
		return str.toString();
	}
}
