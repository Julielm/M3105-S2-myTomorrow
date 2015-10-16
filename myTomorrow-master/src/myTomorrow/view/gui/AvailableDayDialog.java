package myTomorrow.view.gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import org.joda.time.DateTime;

import myTomorrow.model.Day;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

/**
 * Windows which propose you an available day.
 * 
 * @author myTomorrowProject
 * @version 1.0.0
 */
public class AvailableDayDialog extends JDialog implements ActionListener {
	/**
	 * SerialVersionUID.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Ok button.
	 */
	private JButton okButton;

	/**
	 * Cancel button.
	 */
	private JButton cancelButton;
	/**
	 * Selected Date.
	 */
	private DateTime selectedDate;
	/**
	 * Available day.
	 */
	private Day availableDay;
	/**
	 * Date picker.
	 */
	private JDatePickerImpl datePicker;

	/**
	 * Available day window.
	 */
	public AvailableDayDialog() {
		this.setModal(true);
		this.setTitle("Saisie");
		this.setSize(350, 180);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

		JPanel pan = new JPanel();
		pan.setBorder(BorderFactory
				.createTitledBorder("Informations évènement"));

		JLabel label = new JLabel("Saisir le jour disponible :");
		pan.add(label);

		UtilDateModel model = new UtilDateModel();
		JDatePanelImpl datePanel = new JDatePanelImpl(model);
		this.datePicker = new JDatePickerImpl(datePanel);

		pan.add(this.datePicker);

		JPanel control = new JPanel();
		this.okButton = new JButton("Valider");
		this.okButton.setPreferredSize(new Dimension(90, 30));
		control.add(this.okButton);
		this.okButton.addActionListener(this);

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
		Date selectedValue = (Date) this.datePicker.getModel().getValue();
		if (e.getSource() == this.okButton && selectedValue != null
				&& !selectedValue.before(DateTime.now().minusDays(1).toDate())) {
			this.selectedDate = new DateTime(selectedValue.getTime());
			this.availableDay = new Day(this.selectedDate.getDayOfMonth(),
					this.selectedDate.getMonthOfYear(),
					this.selectedDate.getYear());
			this.dispose();
		}
		if (e.getSource() == this.cancelButton) {
			this.dispose();
		}

	}

	/**
	 * Getter for the available day.
	 * 
	 * @return the available day
	 */
	public Day getAvailableDay() {
		return this.availableDay;
	}

}
