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
import javax.swing.JTextField;

/**
 * Dialog for the duration of event.
 * 
 * @author myTomorrow
 * @version 1.0.0
 */
public class DurationOfEventDialog extends JDialog implements ActionListener {
	private JTextField duration;
	private JButton okButton, cancelButton;
	private int durationInput;

	/**
	 * SerialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for the dialog to input the duration of the event.
	 */
	public DurationOfEventDialog() {
		this.setModal(true);
		this.setTitle("Saisie");
		this.setSize(350, 140);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

		JPanel pan = new JPanel();
		pan.setBorder(BorderFactory.createTitledBorder("Durée de l'evenement"));
		pan.setLayout(new GridLayout(2, 2));
		this.duration = new JTextField();
		JLabel nomLabel = new JLabel("Saisir une durée (en minutes) :");
		pan.add(nomLabel);
		pan.add(this.duration);

		JPanel control = new JPanel();
		this.okButton = new JButton("Valider");
		this.okButton.setPreferredSize(new Dimension(90, 30));
		control.add(this.okButton);
		this.okButton.addActionListener(this);

		this.cancelButton = new JButton("Annuler");
		this.cancelButton.setPreferredSize(new Dimension(90, 30));
		control.add(this.cancelButton);
		this.cancelButton.addActionListener(this);

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
		if (e.getSource() == this.okButton
				&& this.duration.getText().length() != 0) {
			if (this.isNumeric(this.duration.getText())) {
				this.durationInput = Integer.parseInt(this.duration.getText());
				if (this.durationInput > 0) {
					this.dispose();
				}
			}

		}
		if (e.getSource() == this.cancelButton) {
			this.durationInput = 0;
			this.dispose();
		}

	}

	/**
	 * Getter for the duration input.
	 * 
	 * @return a integer
	 */
	public int getDurationInput() {
		return this.durationInput;
	}

	/**
	 * Test if the string is numeric.
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

}
