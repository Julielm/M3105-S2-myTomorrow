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
 * Is the dialog window which ask for lesson information.
 * 
 * @author myTomorrowProject
 * @version 1.0.0
 */

public class LessonInformationDialog extends JDialog implements ActionListener {
	/**
	 * SerialVersionUID.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Title of the dialog window.
	 */
	private JTextField title;
	/**
	 * Lesson title.
	 */
	private String lessonTitle;
	/**
	 * "OK" button.
	 */
	private JButton okButton;
	/**
	 * "Cancel" button.
	 */
	private JButton cancelButton;

	/**
	 * Build a dialog window.
	 */
	public LessonInformationDialog() {
		this.setModal(true);
		this.setTitle("Saisie");
		this.setSize(350, 140);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

		JPanel pan = new JPanel();
		pan.setBorder(BorderFactory.createTitledBorder("Informations du cours"));
		pan.setLayout(new GridLayout(1, 2));
		this.title = new JTextField();
		JLabel nomLabel = new JLabel("Saisir le titre du cours :");
		pan.add(nomLabel);
		pan.add(this.title);

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
		if (e.getSource() == this.okButton && this.title.getText().length() != 0) {
			this.lessonTitle = this.title.getText();
			this.dispose();
		}
		if (e.getSource() == this.cancelButton) {
			this.dispose();
		}

	}

	/**
	 * Get the lesson title.
	 * 
	 * @return string
	 */
	public String getTitleInput() {
		return this.lessonTitle;
	}

}
