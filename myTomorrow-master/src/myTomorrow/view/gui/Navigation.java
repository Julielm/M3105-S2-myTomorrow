package myTomorrow.view.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;
import myTomorrow.model.ScheduleManager;

/**
 * Create the navigation bar.
 * 
 * @author myTomorrow
 * @version 1.0.0
 */
public class Navigation extends JSplitPane implements ActionListener {
	/**
	 * SerialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Panel for the button.
	 */
	private JPanel buttons;

	/**
	 * Panel for the legend.
	 */
	private JPanel legend;

	/**
	 * Appointment button.
	 */
	private JButton appointment;

	/**
	 * Lesson button.
	 */
	private JButton lesson;

	/**
	 * Person button.
	 */
	private JButton person;

	/**
	 * Delete button.
	 */
	private JButton delete;

	/**
	 * Application associated with the navigation bar.
	 */
	private ScheduleManager application;

	/**
	 * Create the navigation bar.
	 */
	public Navigation() {
		this.setOrientation(VERTICAL_SPLIT);
		this.buttons = new JPanel();
		this.legend = new JPanel();
		this.setDividerLocation(500);
		this.setTopComponent(this.buttons);
		this.setBottomComponent(this.legend);
		this.setDividerSize(0);
		this.setEnabled(false);
		this.buttons.setLayout(new GridLayout(4, 1));
		this.appointment = new JButton("Ajouter RDV");
		this.appointment.addActionListener(this);
		this.buttons.add(this.appointment);
		this.lesson = new JButton("Ajouter Cours");
		this.lesson.addActionListener(this);
		this.buttons.add(this.lesson);
		this.person = new JButton(
				"<html><head> <style> p{ text-align : center}</style></head><body><p>Ajouter une personne <br>à un cours</p></body></html>");
		this.person.addActionListener(this);
		this.buttons.add(this.person);
		this.delete = new JButton("Supprimer");
		this.delete.addActionListener(this);
		this.buttons.add(this.delete);

		this.legend.setLayout(new GridLayout(2, 1));
		JLabel appointmentLegend = new JLabel(" RDV  ");
		appointmentLegend.setIcon(new ImageIcon("legendeRDV.png"));
		appointmentLegend.setHorizontalAlignment(SwingConstants.CENTER);
		this.legend.add(appointmentLegend);

		JLabel lessonLegend = new JLabel(" Cours");
		lessonLegend.setIcon(new ImageIcon("legendeCours.png"));
		lessonLegend.setHorizontalAlignment(SwingConstants.CENTER);
		this.legend.add(lessonLegend);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.appointment) {
			this.application.addAppointment();
		}
		if (e.getSource() == this.lesson) {
			this.application.addLesson();
		}
		if (e.getSource() == this.person) {
			this.application.addPersonToLesson();
		}
		if (e.getSource() == this.delete) {
			this.application.removeAppointmentOrPersonInLesson();
		}
	}

	/**
	 * Initialization of the application.
	 * 
	 * @param application
	 */
	public void initNavigation(ScheduleManager application) {
		this.application = application;

	}

}
