package myTomorrow.model;

/**
 * Person we add in a lesson or a appointment.
 * 
 * @author myTomorrowProject
 * @version 1.0.0
 */
public class Person {
	/** Person's name. */
	private final String name;
	/** Person's first name. */
	private final String firstName;

	/**
	 * Constructor of a person.
	 * 
	 * @param name
	 * @param firstName
	 */
	public Person(String name, String firstName) {
		this.name = name;
		this.firstName = firstName;
	}

	/**
	 * Getter for the name.
	 * 
	 * @return name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Getter for the firstName.
	 * 
	 * @return firstName
	 */
	public String getFirstName() {
		return this.firstName;
	}

	/**
	 * 
	 * Check if two persons are the same.
	 * 
	 * @param person
	 * 
	 * @return boolean
	 */
	public boolean equals(Person person) {
		return this.name.equalsIgnoreCase(person.name)
				&& this.firstName.equalsIgnoreCase(person.firstName);
	}

	@Override
	public int hashCode() {
		int hashcode = 0;
		for (int currentCharacter = 0; currentCharacter < this.name.length(); currentCharacter++)
			hashcode += (int) this.name.charAt(currentCharacter);
		for (int currentCharacter = 0; currentCharacter < this.firstName
				.length(); currentCharacter++)
			hashcode += (int) this.firstName.charAt(currentCharacter);
		return hashcode;
	}

	/**
	 * String to input in the file to save a person.
	 * 
	 * @return a string
	 */
	public String personInFile() {
		StringBuilder str = new StringBuilder();
		str.append(this.name);
		str.append("%");
		str.append(this.firstName);
		return str.toString();
	}

	@Override
	public String toString() {
		StringBuilder string = new StringBuilder();
		string.append(this.firstName);
		string.append(" ");
		string.append(this.name);
		return string.toString();
	}
}
