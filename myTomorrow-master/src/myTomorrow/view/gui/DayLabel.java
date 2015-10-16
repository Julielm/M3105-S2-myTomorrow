package myTomorrow.view.gui;

import javax.swing.JLabel;

/**
 * Is the label of a day.
 * 
 * @author myTomorrowProject
 * @version 1.0.0
 */
public class DayLabel extends JLabel {
	/**
	 * SerialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create label day with a day and a date.
	 * 
	 * @param label
	 * @param dayOfWeek
	 */
	public DayLabel(String label, int dayOfWeek) {
		this.setText(label);
		this.setBounds(58 + ((dayOfWeek - 1) * 133), 15,
				GraphicalEvent.EVENT_WIDTH, 30);

	}

}
