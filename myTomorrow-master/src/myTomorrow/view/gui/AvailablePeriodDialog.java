package myTomorrow.view.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
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
import myTomorrow.model.TimeSlot;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

/**
 * Windows which propose you an available period.
 * 
 * @author myTomorrowProject
 * @version 1.0.0
 */
public class AvailablePeriodDialog extends JDialog implements ActionListener {

	/**
	 * SerialVersionUID.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * DatePicker.
	 */
	private JDatePickerImpl datePicker;
	/**
	 * DatePicker2.
	 */
	private JDatePickerImpl datePicker2;
	/**
	 * OkButton.
	 */
	private JButton okButton;
	/**
	 * CancelButton.
	 */
	private JButton cancelButton;
	/**
	 * SelectedDate.
	 */
	private DateTime selectedDate;
	/**
	 * SelectedDate2.
	 */
	private DateTime selectedDate2;
	/**
	 * AvailablePeriod.
	 */
	private TimeSlot availablePeriod;

	/**
	 * Available day window.
	 */
	public AvailablePeriodDialog() {
		this.setModal(true);
		this.setTitle("Saisie");
		this.setSize(450, 180);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

		JPanel pan = new JPanel();
		pan.setBorder(BorderFactory.createTitledBorder("Periode disponible"));
		pan.setLayout(new GridLayout(2, 2));
		JLabel label = new JLabel("Saisir le jour de début :");
		pan.add(label);

		UtilDateModel model = new UtilDateModel();
		JDatePanelImpl datePanel = new JDatePanelImpl(model);
		this.datePicker = new JDatePickerImpl(datePanel);

		pan.add(this.datePicker);

		JLabel label2 = new JLabel("Saisir le jour de fin :");
		pan.add(label2);

		UtilDateModel model2 = new UtilDateModel();
		JDatePanelImpl datePanel2 = new JDatePanelImpl(model2);
		this.datePicker2 = new JDatePickerImpl(datePanel2);

		pan.add(this.datePicker2);

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
		Date selectedValue2 = (Date) this.datePicker2.getModel().getValue();
		if (selectedValue != null && selectedValue2 != null) {
			this.selectedDate = new DateTime(selectedValue.getTime());
			this.selectedDate2 = new DateTime(selectedValue2.getTime());
			if (e.getSource() == this.okButton
					&& !selectedValue.before(DateTime.now().minusDays(1)
							.toDate())
					&& !selectedValue2.before(DateTime.now().minusDays(1)
							.toDate())
					&& (selectedValue.before(selectedValue2) || selectedValue
							.equals(selectedValue2))) {
				DateTime startDay = new DateTime(this.selectedDate.getYear(),
						this.selectedDate.getMonthOfYear(),
						this.selectedDate.getDayOfMonth(), 8, 0);
				DateTime endDay = new DateTime(this.selectedDate2.getYear(),
						this.selectedDate2.getMonthOfYear(),
						this.selectedDate2.getDayOfMonth(), 18, 0);
				this.availablePeriod = new TimeSlot(startDay, endDay);
				this.dispose();
			}
		}

		if (e.getSource() == this.cancelButton) {
			this.dispose();
		}

	}

	/**
	 * Getter for the available period.
	 * 
	 * @return the available period
	 */
	public TimeSlot getPeriod() {
		return this.availablePeriod;
	}

}
