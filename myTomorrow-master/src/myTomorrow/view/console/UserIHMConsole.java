package myTomorrow.view.console;

import java.util.List;
import java.util.Scanner;
import org.joda.time.DateTime;
import myTomorrow.model.Answer;
import myTomorrow.model.Day;
import myTomorrow.model.Person;
import myTomorrow.model.ScheduleManager;
import myTomorrow.model.ScheduledEvent;
import myTomorrow.model.TimeSlot;
import myTomorrow.view.UserIHM;

/**
 * User's IHM.
 * 
 * @author myTomorrowProject
 * @version 1.0.0
 */
public class UserIHMConsole implements UserIHM {
	Scanner scanner = new Scanner(System.in);
	private ScheduleManager application;
	private List<ScheduledEvent> events;
	private List<String> days;


	@Override
	public Person askPersonInformations() {

		System.out.println("Entrez le nom de la personne");
		String name = scanner.nextLine();
		System.out.println("Entrez le prénom de la personne");
		String firstName = scanner.nextLine();
		return new Person(name, firstName);
	}

	@Override
	public Day askAvailableDay() {

		System.out.println("Entrez le jour disponible de la personne");
		System.out.println("Entrez la date : jour -->");
		String string = "";
		do {
			string = scanner.nextLine();
		} while (!this.isNumeric(string));
		int date = Integer.parseInt(string);

		System.out.println("mois -->");
		do {
			string = scanner.nextLine();
		} while (!this.isNumeric(string));
		int month = Integer.parseInt(string);

		System.out.println("année -->");
		do {
			string = scanner.nextLine();
		} while (!this.isNumeric(string));
		int year = Integer.parseInt(string);

		try {
			return new Day(date, month, year);
		} catch (Exception e) {
			System.err.println("Vous avez rentré une date invalide");
			this.askAvailableDay();
			return null;
		}
	}

	@Override
	public int askDurationOfEvent() {
		System.out.println("Entrez la duree du rendez-vous en minutes");
		String string = "";
		do {
			string = scanner.nextLine();
		} while (!this.isNumeric(string));
		int duration = Integer.parseInt(string);
		if (duration > 0)
			return duration;
		return askDurationOfEvent();
	}

	@Override
	public Answer suggestTimeSlot(TimeSlot timeSlot) {

		System.out.println(timeSlot.toString());
		System.out.println("Heure de début : "
				+ timeSlot.toString(timeSlot.getStartTime()));
		System.out.println("Heure de fin : "
				+ timeSlot.toString(timeSlot.getEndTime()));
		System.out.println("Ce créneau vous convient-il ?");
		System.out.println("1: Oui");
		System.out.println("2: Non");
		String answer = scanner.nextLine();
		if (answer.equals("1")) {
			return Answer.YES;
		}
		if (answer.equals("2"))
			return Answer.NO;
		return this.suggestTimeSlot(timeSlot);
	}

	@Override
	public void freeTimeSlotIsEmpty() {
		System.out.println("Il n'y a pas de créneaux libres ce jour.");

	}

	@Override
	public void userDontWantTheseFreeTimeSlots() {
		System.out.println("Il n'y a plus de créneaux.");

	}

	@Override
	public String askTitleOfTheLesson() {
		System.out.println("Entrez l'intitulé du cours -->");
		return scanner.nextLine();
	}

	/**
	 * Test if the string is numeric
	 * 
	 * @param string
	 * @return a boolean
	 */
	public boolean isNumeric(String string) {
		try {
			int value = Integer.parseInt(string);

		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public TimeSlot askAvailablePeriod() {
		System.out.println("Période disponible de la personne : ");
		System.out.println("Entrez la date de début de période : jour -->");
		String string = "";
		do {
			string = scanner.nextLine();
		} while (!this.isNumeric(string));
		int date = Integer.parseInt(string);

		System.out.println("mois -->");
		do {
			string = scanner.nextLine();
		} while (!this.isNumeric(string));
		int month = Integer.parseInt(string);

		System.out.println("année -->");
		do {
			string = scanner.nextLine();
		} while (!this.isNumeric(string));
		int year = Integer.parseInt(string);

		System.out.println("Entrez la date de fin de période : jour -->");
		string = "";
		do {
			string = scanner.nextLine();
		} while (!this.isNumeric(string));
		int date2 = Integer.parseInt(string);

		System.out.println("mois -->");
		do {
			string = scanner.nextLine();
		} while (!this.isNumeric(string));
		int month2 = Integer.parseInt(string);

		try {
			return new TimeSlot(new DateTime(year, month, date,
					Day.START_HOUR_BY_DEFAULT, 0), new DateTime(year, month2,
					date2, Day.END_HOUR_BY_DEFAULT, 0));
		} catch (Exception e) {
			System.err.println("Vous avez rentré une période invalide");
			this.askAvailablePeriod();
			return null;
		}
	}

	@Override
	public void lessonsInThePeriodIsEmpty() {
		System.out
				.println("Il n'y a pas de cours avec cet intitlé ayant une place libre dans cette période");
		System.out.println("Veuillez d'abord ajouter un cours");

	}

	@Override
	public DateTime inputDateOfEvent() {
		System.out.println("Entrez la date de l'évènement : jour -->");
		String string = "";
		do {
			string = scanner.nextLine();
		} while (!this.isNumeric(string));
		int date = Integer.parseInt(string);

		System.out.println("mois -->");
		do {
			string = scanner.nextLine();
		} while (!this.isNumeric(string));
		int month = Integer.parseInt(string);

		System.out.println("année -->");
		do {
			string = scanner.nextLine();
		} while (!this.isNumeric(string));
		int year = Integer.parseInt(string);

		System.out.println("Entrez l'heure de l'évènement : heure -->");
		string = "";
		do {
			string = scanner.nextLine();
		} while (!this.isNumeric(string));
		int hours = Integer.parseInt(string);

		System.out.println("minutes -->");
		do {
			string = scanner.nextLine();
		} while (!this.isNumeric(string));
		int minutes = Integer.parseInt(string);

		return new DateTime(year, month, date, hours, minutes);

	}

	@Override
	public void thePersonInputIsNTInLesson() {
		System.out
				.println("La personne que vous avez saisie n'est pas dans la leçon");

	}

	@Override
	public void updateCalendar() {
		// TODO Auto-generated method stub

	}

	@Override
	public void displayFinishedHandling(ScheduledEvent event) {
		System.out.println("L'évènement a bien été ajouté.");

	}

	@Override
	public void noEventAtThisDate() {
		System.out.println("Il n'y a pas d'évènement à cette date.");

	}

	@Override
	public void eventDeleted() {
		System.out.println("L'évènement a bien été supprimé");

	}

	@Override
	public void personDeleted() {
		System.out.println("La personne a bien été supprimée");

	}

	@Override
	public void initCalendar(List<ScheduledEvent> events, List<String> days,
			ScheduleManager application) {
		this.application = application;
		this.events = events;
		this.days = days;
		int choice = 0;
		while (choice != 5) {
			choice = this.showMenu();

			if (choice == 1) {
				this.application.addAppointment();
			}
			if (choice == 2) {
				this.application.addLesson();
			}
			if (choice == 3) {
				this.application.addPersonToLesson();
			}
			if (choice == 4) {
				this.application.removeAppointmentOrPersonInLesson();
			}
		}
		System.exit(0);

	}

	/**
	 * Show menu.
	 * 
	 * @return the choice of the user
	 */
	private int showMenu() {
		System.out.println("Que voulez-vous faire ? ");
		System.out.println("1- Ajouter un RDV");
		System.out.println("2- Ajouter un cours");
		System.out.println("3- Ajouter une personne à un cours");
		System.out.println("4- Supprimer");
		System.out.println("5- Quitter");
		String string = "";
		do {
			string = scanner.nextLine();
		} while (!this.isNumeric(string));
		int choice = Integer.parseInt(string);
		if (choice < 6 && choice > 0)
			return choice;
		return this.showMenu();

	}

	@Override
	public void personAdded() {
		System.out.println("La personne a été ajoutée au cours");

	}
}
