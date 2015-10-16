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

import myTomorrow.model.Answer;
import myTomorrow.model.TimeSlot;

/**
 * Is the dialog for the suggestion of a time slot.
 * 
 * @author myTomorrowProject
 * @version 1.0.0
 */
public class SuggestionOfTimeSlotDialog extends JDialog implements
		ActionListener {
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	/** Button yes. */
	private JButton yesButton;
	/** Button no. */
	private JButton noButton;
	/** Button cancel. */
	private JButton cancelButton;
	/** Answer of the suggestion. */
	private Answer answer;

	/**
	 * Constructor of SuggestionOfTimeSlotDialog.
	 * 
	 * @param timeSlot
	 */
	public SuggestionOfTimeSlotDialog(TimeSlot timeSlot) {
		this.setModal(true);
		this.setSize(400, 180);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

		JPanel pan = new JPanel();
		pan.setBorder(BorderFactory.createTitledBorder("Validation"));
		pan.setLayout(new GridLayout(3, 2));
		pan.add(new JLabel("Ce créneau vous convient-il ? "));
		pan.add(new JLabel(timeSlot.toString()));
		pan.add(new JLabel("Heure de début :"));
		pan.add(new JLabel(timeSlot.toString(timeSlot.getStartTime())));
		pan.add(new JLabel("Heure de fin : "));
		pan.add(new JLabel(timeSlot.toString(timeSlot.getEndTime())));

		JPanel control = new JPanel();
		this.yesButton = new JButton("Oui");
		this.yesButton.setPreferredSize(new Dimension(90, 30));
		control.add(this.yesButton);
		this.yesButton.addActionListener(this);

		this.noButton = new JButton("Non");
		this.noButton.setPreferredSize(new Dimension(90, 30));
		control.add(this.noButton);
		this.noButton.addActionListener(this);
		this.add(control);

		this.cancelButton = new JButton("Annuler");
		this.cancelButton.setPreferredSize(new Dimension(90, 30));
		control.add(this.cancelButton);
		this.cancelButton.addActionListener(this);
		this.add(control);

		JSplitPane split = new JSplitPane();
		split.setOrientation(JSplitPane.VERTICAL_SPLIT);
		split.setTopComponent(pan);
		split.setDividerLocation(100);
		split.setBottomComponent(control);
		split.setDividerSize(0);
		split.setEnabled(false);
		this.add(split);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.yesButton) {
			this.answer = Answer.YES;
			this.dispose();
		}
		if (e.getSource() == this.noButton) {
			this.answer = Answer.NO;
			this.dispose();
		}
		if (e.getSource() == this.cancelButton) {
			this.answer = Answer.CANCEL;
			this.dispose();
		}

	}

	/**
	 * Getter for the answer.
	 * 
	 * @return an answer
	 */
	public Answer getSuggestionInput() {
		return this.answer;
	}

}
