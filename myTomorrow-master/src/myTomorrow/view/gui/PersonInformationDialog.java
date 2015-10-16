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

import myTomorrow.model.Person;

/**
 * Is the dialog window which ask for person informations.
 * 
 * @author myTomorrowProject
 * @version 1.0.0
 */
public class PersonInformationDialog extends JDialog implements ActionListener {
	private JTextField name, firstName;
	private JButton okButton, cancelButton;
	private Person personInput;

	/**
	 * SerialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Build a dialog window about person information.
	 */
	public PersonInformationDialog() {
		this.setModal(true);
		this.setTitle("Saisie");
		this.setSize(350, 140);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

		JPanel pan = new JPanel();
		pan.setBorder(BorderFactory
				.createTitledBorder("Informations de la personne"));
		pan.setLayout(new GridLayout(2, 2));
		this.name = new JTextField();
		JLabel nomLabel = new JLabel("Saisir un nom :");
		pan.add(nomLabel);
		pan.add(this.name);
		this.firstName = new JTextField();
		JLabel prenomLabel = new JLabel("Saisir un prenom :");
		pan.add(prenomLabel);
		pan.add(this.firstName);

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
		if (e.getSource() == this.okButton && this.name.getText().length() != 0
				&& this.firstName.getText().length() != 0) {
			this.personInput = new Person(this.name.getText(),
					this.firstName.getText());
			this.dispose();
		}
		if (e.getSource() == this.cancelButton) {
			this.dispose();
		}

	}

	/**
	 * Get the person we add.
	 * 
	 * @return Person
	 */
	public Person getPersonInput() {
		return this.personInput;
	}

}
