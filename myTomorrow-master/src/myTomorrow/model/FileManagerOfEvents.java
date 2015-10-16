package myTomorrow.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import org.joda.time.DateTime;

/**
 * File's manager of events to save and read all the events.
 * 
 * @author myTomorrowProject
 * @version 1.0.0
 */
public class FileManagerOfEvents {
	/**
	 * File where the events are saved.
	 */
	private final File eventFile;

	/**
	 * Constructor of the file's manager.
	 * 
	 * @param nameOfFile
	 */
	public FileManagerOfEvents(File nameOfFile) {
		this.eventFile = nameOfFile;
	}

	/**
	 * Add events to the file.
	 * 
	 * @param events
	 * @throws IOException
	 */
	public void writeEvents(List<ScheduledEvent> events) throws IOException {
		try (FileWriter fw = new FileWriter(this.eventFile)) {
			if (!events.isEmpty()) {
				for (ScheduledEvent event : events) {
					if (event instanceof Appointment)
						fw.write(String.format("%s,%s,%s/", event.getTimeSlot()
								.getStartTime(), event.getTimeSlot()
								.getEndTime(), ((Appointment) event)
								.getPerson().personInFile()));
					if (event instanceof Lesson)
						if (!((Lesson) event).persons().isEmpty()) {
							fw.write(String.format("%s,%s,%s,%s/", event
									.getTimeSlot().getStartTime(), event
									.getTimeSlot().getEndTime(),
									((Lesson) event).getTitle(),
									((Lesson) event).persons()));
						} else {
							fw.write(String.format("%s,%s,%s,%s/", event
									.getTimeSlot().getStartTime(), event
									.getTimeSlot().getEndTime(),
									((Lesson) event).getTitle(), "vide"));
						}
				}

			}
		}
	}

	/**
	 * Read events in the file and store them into a list.
	 * 
	 * @return a list of events
	 * @throws IOException
	 */
	public List<ScheduledEvent> readEvents() throws IOException {
		List<ScheduledEvent> events = new LinkedList<ScheduledEvent>();
		try (BufferedReader entry = new BufferedReader(new FileReader(
				this.eventFile))) {
			String readText = entry.readLine();
			if (readText != null) {
				String[] eventsString = readText.split("/");
				for (int index = 0; index < eventsString.length; index++) {
					String[] string = eventsString[index].split(",");
					String startTime = string[0];
					String endTime = string[1];
					TimeSlot timeSlot = new TimeSlot(new DateTime(startTime),
							new DateTime(endTime));
					if (string.length == 3) {
						String[] person = string[2].split("%");
						String name = person[0];
						String firstName = person[1];
						events.add(new Appointment(new Person(name, firstName),
								timeSlot));
					}
					if (string.length == 4) {
						String title = string[2];
						if (!string[3].equals("vide")) {
							String[] persons = string[3].split(";");
							List<Person> personsInTheLesson = new LinkedList<Person>();
							for (int position = 0; position < persons.length; position++) {
								String[] person = persons[position].split("%");
								String name = person[0];
								String firstName = person[1];
								personsInTheLesson.add(new Person(name,
										firstName));
							}
							if (personsInTheLesson.size() <= 3) {
								events.add(new Lesson(title, timeSlot,
										personsInTheLesson, personsInTheLesson
												.size()));
							}
						} else
							events.add(new Lesson(title, timeSlot));
					}
				}
			}
		}
		return events;
	}
}
