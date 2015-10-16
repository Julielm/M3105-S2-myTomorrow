package myTomorrow.model;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import myTomorrow.view.UserIHM;
import org.joda.time.DateTime;

/**
 * Application.
 * 
 * @author myTomorrowProject
 * @version 1.0.0
 */
public class ScheduleManager {

	/**
	 * Constant for the morning.
	 */
	public static final boolean MORNING = true;
	/**
	 * Constant for the afternoon.
	 */
	public static final boolean AFTERNOON = false;
	/**
	 * List of the used time slots.
	 */
	private List<ScheduledEvent> events;

	/**
	 * User's IHM.
	 */
	private final UserIHM myIHM;
	/**
	 * A file's manager.
	 */
	private FileManagerOfEvents fileManagerOfEvents;

	/**
	 * Constructor of an application.
	 * 
	 * @param ihm
	 *            chosen ihm, console or GUI
	 * @param eventFile 
	 */
	public ScheduleManager(UserIHM ihm, File eventFile) {
		this.fileManagerOfEvents = new FileManagerOfEvents(eventFile);
		try {
			this.events = this.fileManagerOfEvents.readEvents();
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.myIHM = ihm;
	}

	/**
	 * Add an appointment.
	 */
	public void addAppointment() {
		Appointment appointment = this.inputAppointment();
		if (appointment != null) {
			Day availableDay = this.myIHM.askAvailableDay();
			if (availableDay != null) {
				int duration = this.myIHM.askDurationOfEvent();
				if (duration != 0) {
					List<TimeSlot> freeTimeSlot = this.searchTimeSlot(
							availableDay, duration);
					if (freeTimeSlot.isEmpty()) {
						this.myIHM.freeTimeSlotIsEmpty();
					} else {
						TimeSlot answer = this.askAnswer(freeTimeSlot);
						if (answer != null) {
							appointment.setTimeSlot(answer);
							addEventInASortList(appointment);
							this.myIHM.displayFinishedHandling(appointment);
							try {
								this.fileManagerOfEvents
										.writeEvents(this.events);
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		}
	}

	/**
	 * Add an event in the sort list of events.
	 * 
	 * @param event
	 */
	private void addEventInASortList(ScheduledEvent event) {
		int index = 0;
		while (index < this.events.size()
				&& this.events.get(index).isBefore(event)) {
			index++;
		}
		this.events.add(index, event);
	}

	/**
	 * Create a new appointment with no defined time slot.
	 * 
	 * @return an appointment
	 */
	private Appointment inputAppointment() {
		Person person = this.myIHM.askPersonInformations();
		if (person != null) {
			return new Appointment(person, new TimeSlot());
		}
		return null;
	}

	/**
	 * Research of a free time slot.
	 * 
	 * @param day
	 * @param duration
	 * @return a List of TimeSlot
	 */
	private List<TimeSlot> searchTimeSlot(Day day, int duration) {
		List<TimeSlot> freeTimeSlots = new LinkedList<TimeSlot>();
		freeTimeSlots.addAll(this.possibleEvents(day, duration, MORNING));
		freeTimeSlots.addAll(this.possibleEvents(day, duration, AFTERNOON));
		return freeTimeSlots;
	}

	/**
	 * Research possible events in the half day put in parameter.
	 * 
	 * @param day
	 * @param duration
	 * @param isMorning
	 * @return a list of TimeSlot
	 */
	private List<TimeSlot> possibleEvents(Day day, int duration,
			boolean isMorning) {
		List<ScheduledEvent> eventsOnSameDay = this
				.getAllEventsThatAreOnSameDay(day, isMorning);
		List<TimeSlot> freeTimeSlots = new LinkedList<TimeSlot>();
		if (eventsOnSameDay.isEmpty()) {
			// Case where all the morning is free.
			return this.GetAllTimeSlotsInTheDay(day, duration, isMorning);
		}
		freeTimeSlots = this.getAllFreeTimeSlotsInTheDay(eventsOnSameDay,
				duration, day, isMorning);
		return freeTimeSlots;
	}

	/**
	 * Get all time slots in a half day (put in parameter) when this half day is
	 * empty.
	 * 
	 * @param day
	 * @param duration
	 * @param isMorning
	 * @return a list of TimeSlot
	 */
	private List<TimeSlot> GetAllTimeSlotsInTheDay(Day day, int duration,
			boolean isMorning) {
		List<TimeSlot> list = new LinkedList<TimeSlot>();
		int hour = 14;
		DateTime startTime = new DateTime(day.getStartTime().getYear(), day
				.getStartTime().getMonthOfYear(), day.getStartTime()
				.getDayOfMonth(), hour, 0);
		if (isMorning) {
			hour = 12;
			startTime = day.getStartTime();
		}
		DateTime endTime = startTime.plusMinutes(duration);
		DateTime endOfHalfDay = day.getEndTime();
		if (isMorning) {
			endOfHalfDay = new DateTime(day.getStartTime().getYear(), day
					.getStartTime().getMonthOfYear(), day.getStartTime()
					.getDayOfMonth(), hour, 0);
		}
		while (endTime.isBefore(endOfHalfDay) || endTime.isEqual(endOfHalfDay)) {
			list.add(new TimeSlot(startTime, endTime));
			startTime = endTime;
			endTime = startTime.plusMinutes(duration);
		}
		return list;
	}

	/**
	 * Research of free time slots in a half day (put in parameter).
	 * 
	 * @param eventsOnSameDay
	 * @param duration
	 * @param day
	 * @return a list of time slots
	 */
	private List<TimeSlot> getAllFreeTimeSlotsInTheDay(
			List<ScheduledEvent> eventsOnSameDay, int duration, Day day,
			boolean isMorning) {
		List<TimeSlot> freeTimeSlots = new LinkedList<TimeSlot>();
		// Case of the first event in the list.
		DateTime dateOfTheEvent = eventsOnSameDay.get(0).getTimeSlot()
				.getStartTime();
		DateTime dateOfTheEventMinusDuration = dateOfTheEvent
				.minusMinutes(duration);

		DateTime date = new DateTime(day.getStartTime().getYear(), day
				.getStartTime().getMonthOfYear(), day.getStartTime()
				.getDayOfMonth(), 14, 0);
		if (isMorning) {
			date = day.getStartTime();
		}

		if (dateOfTheEventMinusDuration.isAfter(date)
				|| dateOfTheEventMinusDuration.isEqual(date)) {
			freeTimeSlots.add(new TimeSlot(dateOfTheEventMinusDuration,
					dateOfTheEvent));
		}

		// Case of the others.
		DateTime dateOfThePreviousEvent = new DateTime();
		for (int index = 1; index < eventsOnSameDay.size(); index++) {
			dateOfThePreviousEvent = eventsOnSameDay.get(index - 1)
					.getTimeSlot().getEndTime();
			dateOfTheEvent = eventsOnSameDay.get(index).getTimeSlot()
					.getStartTime();
			dateOfTheEventMinusDuration = dateOfTheEvent.minusMinutes(duration);

			if (dateOfTheEventMinusDuration.isAfter(dateOfThePreviousEvent)
					|| dateOfTheEventMinusDuration
							.isEqual(dateOfThePreviousEvent)) {
				freeTimeSlots.add(new TimeSlot(dateOfTheEventMinusDuration,
						dateOfTheEvent));
			}
		}

		// Case of the last.
		dateOfThePreviousEvent = eventsOnSameDay
				.get(eventsOnSameDay.size() - 1).getTimeSlot().getEndTime();
		DateTime dateOfTheEventPlusDuration = dateOfThePreviousEvent
				.plusMinutes(duration);
		date = day.getEndTime();
		if (isMorning) {
			date = new DateTime(day.getStartTime().getYear(), day
					.getStartTime().getMonthOfYear(), day.getStartTime()
					.getDayOfMonth(), 12, 0);
		}
		if (dateOfTheEventPlusDuration.isBefore(date)
				|| dateOfTheEventPlusDuration.isEqual(date)) {
			freeTimeSlots.add(new TimeSlot(dateOfThePreviousEvent,
					dateOfTheEventPlusDuration));
		}
		return freeTimeSlots;
	}

	/**
	 * Get all events already in the calendar that are on the half day put in
	 * parameter.
	 * 
	 * @param day
	 * @param isMorning
	 * @return a list of scheduled events
	 */
	private List<ScheduledEvent> getAllEventsThatAreOnSameDay(Day day,
			boolean isMorning) {
		List<ScheduledEvent> eventsOnSameDay = new LinkedList<ScheduledEvent>();
		DateTime dateOfTheEvent = new DateTime();
		DateTime dateOfTheDay = new DateTime();
		for (ScheduledEvent event : this.events) {
			dateOfTheEvent = event.getTimeSlot().getStartTime();
			dateOfTheDay = day.getStartTime();
			if (isMorning) {
				if ((dateOfTheEvent.dayOfMonth().get() == dateOfTheDay
						.dayOfMonth().get())
						&& (dateOfTheEvent.monthOfYear().get() == dateOfTheDay
								.monthOfYear().get())
						&& (dateOfTheEvent.year().get() == dateOfTheDay.year()
								.get())
						&& (dateOfTheEvent.hourOfDay().get() < 12)) {
					eventsOnSameDay.add(event);
				}
			} else {
				if ((dateOfTheEvent.dayOfMonth().get() == dateOfTheDay
						.dayOfMonth().get())
						&& (dateOfTheEvent.monthOfYear().get() == dateOfTheDay
								.monthOfYear().get())
						&& (dateOfTheEvent.year().get() == dateOfTheDay.year()
								.get())
						&& (dateOfTheEvent.hourOfDay().get() >= 14)) {
					eventsOnSameDay.add(event);
				}
			}

		}
		return eventsOnSameDay;
	}

	/**
	 * Add a lesson in the list of events.
	 */
	public void addLesson() {
		Lesson lesson = this.inputLesson();
		if (lesson != null) {
			Day availableDay = this.myIHM.askAvailableDay();
			if (availableDay != null) {
				int duration = this.myIHM.askDurationOfEvent();
				if (duration != 0) {
					List<TimeSlot> freeTimeSlot = this.searchTimeSlot(
							availableDay, duration);
					if (freeTimeSlot.isEmpty()) {
						this.myIHM.freeTimeSlotIsEmpty();
					} else {
						TimeSlot answer = this.askAnswer(freeTimeSlot);
						if (answer != null) {
							lesson.setTimeSlot(answer);
							addEventInASortList(lesson);
							this.myIHM.displayFinishedHandling(lesson);
							try {
								this.fileManagerOfEvents
										.writeEvents(this.events);
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		}

	}

	/**
	 * Ask to the user available time slots to choose one.
	 * 
	 * @param timeSlots
	 * @return a timeSlot
	 */
	private TimeSlot askAnswer(List<TimeSlot> timeSlots) {
		Answer answer = Answer.NO;
		int index = 0;
		while (answer != Answer.CANCEL && answer == Answer.NO
				&& index < timeSlots.size()) {
			answer = this.myIHM.suggestTimeSlot(timeSlots.get(index));
			index++;
		}
		if (answer == Answer.YES) {
			return timeSlots.get(index - 1);
		}
		if (answer == Answer.NO) {
			this.myIHM.userDontWantTheseFreeTimeSlots();
		}
		return null;
	}

	/**
	 * Create a new lesson with a no defined time slot.
	 * 
	 * @return a lesson
	 */
	private Lesson inputLesson() {
		String title = this.myIHM.askTitleOfTheLesson();
		if (title != null)
			return new Lesson(title, new TimeSlot());
		return null;
	}

	/**
	 * Add a person to a lesson.
	 */
	public void addPersonToLesson() {
		Person person = this.myIHM.askPersonInformations();
		if (person != null) {
			TimeSlot period = this.myIHM.askAvailablePeriod();
			if (period != null) {
				String title = this.myIHM.askTitleOfTheLesson();
				if (title != null) {
					List<TimeSlot> lessonsInThePeriod = this.LessonInAList(
							this.EventsInAPeriod(period), title, person);
					if (lessonsInThePeriod.isEmpty()) {
						this.myIHM.lessonsInThePeriodIsEmpty();
					} else {
						TimeSlot answer = this.askAnswer(lessonsInThePeriod);
						if (answer != null) {
							this.addPerson(answer, person);
							this.myIHM.personAdded();
							try {
								this.fileManagerOfEvents
										.writeEvents(this.events);
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}

		}
	}

	/**
	 * Add the person to the lesson which have the answer for time slot.
	 * 
	 * @param answer
	 * @param person
	 */
	public void addPerson(TimeSlot answer, Person person) {
		int index = 0;
		while (this.events.get(index).getTimeSlot() != answer) {
			index++;
		}
		Lesson lesson = (Lesson) this.events.get(index);
		lesson.setPersonList(person);
		this.events.set(index, lesson);
	}

	private List<TimeSlot> LessonInAList(List<ScheduledEvent> eventsInAList,String title, Person person) {
		List<TimeSlot> lessons = new LinkedList<TimeSlot>();
		for (ScheduledEvent event : eventsInAList) {
			if (event instanceof Lesson) {
				Lesson lesson = (Lesson) event;
				if (lesson.hasTheSameTitle(title)) {
					if (lesson.hasFreePlace()) {
						if (lesson.personIndex(person) < 0) {
							lessons.add(lesson.getTimeSlot());
						}
					}
				}
			}
		}
		return lessons;
	}

	private List<ScheduledEvent> EventsInAPeriod(TimeSlot period) {
		List<ScheduledEvent> eventsInThePeriod = new LinkedList<ScheduledEvent>();
		for (ScheduledEvent event : this.events) {
			DateTime dateOfCurrentEvent = event.getTimeSlot().getStartTime();
			if (dateOfCurrentEvent.isBefore(period.getStartTime())) {
				continue;
			}
			if (dateOfCurrentEvent.isAfter(period.getEndTime())) {
				continue;
			}
			eventsInThePeriod.add(event);
		}
		return eventsInThePeriod;
	}

	/**
	 * Remove an appointment or a person in a lesson.
	 */
	public void removeAppointmentOrPersonInLesson() {
		DateTime date = this.myIHM.inputDateOfEvent();
		if (date != null) {
			int index = this.searchEvent(date);
			System.out.println(index);
			if (index >= 0) {
				ScheduledEvent event = this.events.get(index);
				if (event instanceof Appointment) {
					this.removeAppointment(index);
					this.myIHM.eventDeleted();
					this.myIHM.displayFinishedHandling(event);
				} else
					this.removePersonInLesson(index);
				try {
					this.fileManagerOfEvents.writeEvents(this.events);
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else
				this.myIHM.noEventAtThisDate();
		}
	}

	/**
	 * Remove a person in a lesson.
	 * 
	 * @param index
	 */
	private void removePersonInLesson(int index) {
		Lesson lesson = (Lesson) this.events.get(index);
		if (lesson.getPersNb() != 0) {
			Person personToRemove = this.myIHM.askPersonInformations();
			int personIndex = lesson.personIndex(personToRemove);
			if (personIndex >= 0) {
				lesson.remove(personIndex);

				if (lesson.getPersNb() == 0) {
					this.events.remove(index);
					this.myIHM.eventDeleted();
					this.myIHM.displayFinishedHandling(lesson);

				} else {
					this.events.set(index, lesson);
					this.myIHM.personDeleted();
					this.myIHM.displayFinishedHandling(lesson);
				}
			} else
				this.myIHM.thePersonInputIsNTInLesson();
		} else {
			this.events.remove(index);
			this.myIHM.eventDeleted();
			this.myIHM.displayFinishedHandling(lesson);
		}

	}

	/**
	 * Remove an appointment.
	 * 
	 * @param index
	 */
	private void removeAppointment(int index) {
		this.events.remove(index);
	}

	/**
	 * Search if an event exists in the list of events.
	 * 
	 * @param dateOfEvent
	 * @return a integer (-1 if not found)
	 */
	private int searchEvent(DateTime dateOfEvent) {
		int index = 0;
		if (!this.events.isEmpty()) {
			DateTime currentEvent = this.events.get(index).getTimeSlot()
					.getStartTime();
			while (!currentEvent.isEqual(dateOfEvent)
					&& index + 1 < this.events.size()) {
				index++;
				currentEvent = this.events.get(index).getTimeSlot()
						.getStartTime();
			}
			if (currentEvent.isEqual(dateOfEvent)) {
				return index;
			}
		}
		return -1;
	}

	/**
	 * Remove the scheduled event put in parameter.
	 * 
	 * @param event
	 */
	public void remove(ScheduledEvent event) {
		this.events.remove(event);
		this.myIHM.updateCalendar();
		try {
			this.fileManagerOfEvents.writeEvents(this.events);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Getter for the events.
	 * 
	 * @return the events
	 */
	public LinkedList<ScheduledEvent> getEvents() {
		return (LinkedList<ScheduledEvent>) this.events;
	}

	/**
	 * Getter for the IHM.
	 * 
	 * @return the myIHM
	 */
	public UserIHM getMyIHM() {
		return this.myIHM;
	}
}
