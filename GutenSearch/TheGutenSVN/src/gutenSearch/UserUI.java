package gutenSearch;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.TextAttribute;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * This class builds our User GUI.
 * 
 * @author David Thompson and Nick Vetrano
 *
 */
public class UserUI {
	private JFrame frame;
	final String initialText = "Search here...";

	/**
	 * This method builds the main window pain and sets the various attributes
	 * and constraints.
	 * 
	 * @param pane
	 *            Accepts a Container object and builds upon it.
	 */
	public final void paneBuilder(final Container pane) {
		// All of the J components initialized.
		JButton clearButton;
		JButton quitButton;
		JButton searchButton;
		JLabel searchLabel;
		JLabel adminLabel;
		// Creates a GridBagLayout for our main window pane.
		pane.setLayout(new GridBagLayout());
		// Bag constraint object is created.
		GridBagConstraints constraint = new GridBagConstraints();

		// Label created and given constraints for our User GUI.
		searchLabel = new JLabel("Search Query");
		constraint.weightx = 0.5;
		constraint.gridx = 0;
		constraint.gridy = 0;
		constraint.insets = new Insets(5, 5, 5, 5);
		pane.add(searchLabel, constraint);

		// Text field created for our search words to be entered
		final JTextField searchField = new JTextField(20);
		// Sets the constraints and adds the search field to the main pane.
		constraint.gridx = 0;
		constraint.gridy = 1;
		searchField.setEditable(true);
		searchField.setText("");
		constraint.insets = new Insets(5, 5, 5, 5);
		pane.add(searchField, constraint);

		/**
		 * Search button is created, constrained, and added to the main pane.
		 */
		searchButton = new JButton("Search");
		searchButton.setPreferredSize(new Dimension(90, 25));
		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
			  
			  if (searchField.getText().trim().isEmpty()) {
			    JOptionPane.showMessageDialog(null, 
			        "Please enter a word(s) to search.",
              "Idiot", JOptionPane.INFORMATION_MESSAGE);
			 
			  } else if (!searchField.getText().isEmpty()) {
			    
			  Search search = new Search(searchField.getText());
			  
				ResultsPage.main(search.getResults());
        frame.dispose();
			  }
			  else 
			    JOptionPane.showMessageDialog(null, 
              "No results found.",
              "Sorry..", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		constraint.gridx = 1;
		constraint.gridy = 1;
		constraint.insets = new Insets(5, 5, 5, 5);
		pane.add(searchButton, constraint);

		/**
		 * Admin label is created. Given the color blue, underlined, given a
		 * mouse clicker listener, and added to the main pane.
		 */
		adminLabel = new JLabel("Admin");
		adminLabel.setForeground(Color.blue);
		Font font = adminLabel.getFont();
		Map attributes = font.getAttributes();
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		adminLabel.setFont(font.deriveFont(attributes));
		adminLabel.addMouseListener(new MouseListener() {
			/**
			 * Creates a method to react to mouse clicks.
			 */
			public void mouseClicked(final MouseEvent e) {
					frame.dispose();
					AdminGUI.main(new String[0]);
				}

			@Override
			public void mousePressed(final MouseEvent e) {
	
			}

			@Override
			public void mouseReleased(final MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(final MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(final MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		// Constraints are set and the Admin label is added to
		// the main pane.
		constraint.weightx = 0.5;
		constraint.gridx = 3;
		constraint.gridy = 0;
		constraint.insets = new Insets(5, 5, 5, 5);
		pane.add(adminLabel, constraint);

		/**
		 * Clear button is created, given a preferred dimension, given an action
		 * listener, constraints, and added to the main pane.
		 */
		clearButton = new JButton("Clear");
		clearButton.setPreferredSize(new Dimension(90, 25));
		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				searchField.setText("");
			}
		});
		// Constrained and added to the main pane.
		constraint.weightx = 0.5;
		constraint.gridx = 3;
		constraint.gridy = 1;
		constraint.insets = new Insets(5, 5, 5, 5);
		pane.add(clearButton, constraint);

		/**
		 * Quit button is created, given a preferred size, given an action
		 * listener, a constraint, and added to the main pane.
		 */
		quitButton = new JButton("Quit");
		quitButton.setPreferredSize(new Dimension(90, 25));
		quitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				frame.dispose();
			}
		});
		// Constrained and added to the main pane.
		constraint.weightx = 0.5;
		constraint.gridx = 3;
		constraint.gridy = 2;
		constraint.insets = new Insets(5, 5, 5, 5);
		pane.add(quitButton, constraint);
	}

	/**
	 * display method is used to create the main JFrame that all components get
	 * added to. Packs the frame, sets the location to the center of the screen,
	 * and sets visible.
	 */
	private void display() {
		frame = new JFrame("GutenSearch User");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);

		// Set up the content pane.
		paneBuilder(frame.getContentPane());

		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	/**
	 * Main method creates a UserUI object and calls its display method to
	 * create the UserUI GUI.
	 * 
	 * @param args
	 *            empty string array for the main method.
	 */
	public static void main(final String[] args) {
		new UserUI().display();
	}
}