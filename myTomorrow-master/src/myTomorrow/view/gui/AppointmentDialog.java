package myTomorrow.view.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import org.joda.time.DateTime;
import myTomorrow.model.Appointment;
import myTomorrow.model.ScheduleManager;
import myTomorrow.model.ScheduledEvent;

/**
 * JDialog for appointement's informations.
 * 
 * @author myTomorrowProject
 * @version 1.0.0
 */
public class AppointmentDialog extends JDialog implements ActionListener {
	/**
	 * Serial version ID.
	 */
	private static final long serialVersionUID = 1L;

	/** Button ok. */
	private ScheduleManager application;
	private ScheduledEvent event;
	private JButton okButton;
	private JButton deleteButton;

	/**
	 * Constructor of apointementDialog.
	 * 
	 * @param event
	 * @param application 
	 */
	public AppointmentDialog(ScheduledEvent event, ScheduleManager application) {
		this.application = application;
		this.event = event;

		this.setModal(true);
		this.setTitle("Information");
		this.setSize(500, 180);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

		JPanel pan = new JPanel();
		pan.setBorder(BorderFactory.createTitledBorder("Informations du RDV"));
		pan.setLayout(new GridLayout(5, 2));
		pan.add(new JLabel("Nom : "));
		pan.add(new JLabel(((Appointment) event).getPerson().getName()));
		pan.add(new JLabel("Prénom : "));
		pan.add(new JLabel(((Appointment) event).getPerson().getFirstName()));
		pan.add(new JLabel("Date : "));
		DateTime startDate = event.getTimeSlot().getStartTime();
		DateTime endDate = event.getTimeSlot().getEndTime();
		pan.add(new JLabel(startDate.getDayOfMonth() + "/"
				+ startDate.getMonthOfYear() + "/" + startDate.getYear()));
		pan.add(new JLabel("Heure de début :"));
		pan.add(new JLabel(event.getTimeSlot().toString(startDate)));
		pan.add(new JLabel("Heure de fin :"));
		pan.add(new JLabel(event.getTimeSlot().toString(endDate)));

		JPanel control = new JPanel();
		this.okButton = new JButton("Ok");
		this.okButton.setPreferredSize(new Dimension(110, 30));
		control.add(this.okButton);
		this.okButton.addActionListener(this);

		this.deleteButton = new JButton("Supprimer");
		this.deleteButton.setPreferredSize(new Dimension(110, 30));
		control.add(this.deleteButton);
		this.deleteButton.addActionListener(this);

		JSplitPane split = new JSplitPane();
		split.setOrientation(JSplitPane.VERTICAL_SPLIT);
		split.setTopComponent(pan);
		split.setBottomComponent(control);
		split.setDividerSize(0);
		split.setEnabled(false);
		this.add(split);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.okButton) {
			this.dispose();
		}
		if (e.getSource() == this.deleteButton) {
			this.application.remove(event);
			this.dispose();

		}

	}
}
