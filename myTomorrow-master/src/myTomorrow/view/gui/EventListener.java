package myTomorrow.view.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import myTomorrow.model.Appointment;
import myTomorrow.model.Lesson;
import myTomorrow.model.ScheduleManager;
import myTomorrow.model.ScheduledEvent;

/**
 * Listener for events' buttons.
 * 
 * @author myTomorrowProject
 * @version 1.0.0
 */
public class EventListener implements ActionListener {
	private ScheduledEvent event;
	private ScheduleManager application;

	/**
	 * Constructor of EventListener.
	 * 
	 * @param event
	 * @param manager 
	 */
	public EventListener(ScheduledEvent event, ScheduleManager manager) {
		this.event = event;
		this.application = manager;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (event instanceof Appointment) {
			JDialog informations = new AppointmentDialog(this.event,
					this.application);
			informations.setVisible(true);
		}
		if (event instanceof Lesson) {
			JDialog informations = new LessonDialog(this.event,
					this.application);
			informations.setVisible(true);
		}

	}

}
