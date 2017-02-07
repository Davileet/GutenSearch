package gutenSearch;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

/**
 * This class builds our result GUI pane when a search result is expanded.
 * 
 * @author David Thompson
 *
 */
public class ResultGUI {
  JFrame resultFrame;
	JTextArea textArea;
	JButton returnButton;
	JLabel resultLabel;
	JPanel centerPanel, flowPanel;
	final int d1 = 130;
	final int d2 = 25;
	final int ta1 = 35, ta2 = 45;
	final int fontSize = 16;
	final int ten = 10;

	/**
	 * Method called to build the actual JFrame and JPanel.
	 * @param result a string.
	 */
	public ResultGUI(final String result) {
		// flowPanel is created to weight our buttons.
		JPanel border = new JPanel(new BorderLayout());
		ButtonPress press = new ButtonPress();
		flowPanel = new JPanel(new FlowLayout());

		// Center panel given a border layout.
		centerPanel = new JPanel();
		centerPanel.setLayout(new BorderLayout());

		// Sets the title for our top most JFrame.
		resultFrame = new JFrame("GutenSearch Expanded Result");
		resultFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// Return button is created, given size dimension, given action
		// listener and command, and finally added to flowPanel.
		returnButton = new JButton("Return to results");
		returnButton.setPreferredSize(new Dimension(d1, d2));
		returnButton.addActionListener(press);
		returnButton.setActionCommand("close");
		flowPanel.add(returnButton);

		// Sets title label on top of our text area.
		resultLabel = new JLabel("GutenSearch Result");
		resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel.add(resultLabel, BorderLayout.CENTER);
		Font font = new Font(null, Font.BOLD, fontSize);
		resultLabel.setFont(font);

		// Text area is created for the expanded result to display.
		textArea = new JTextArea(ta1, ta2);
		textArea.setText(result);
		textArea.setEditable(false);
		
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		
		JScrollPane scroll = new JScrollPane(textArea);

		// Main result frame is built and the components are added to the
		// corresponding layout positions.
		border.add(scroll);
		resultFrame.add(centerPanel, BorderLayout.NORTH);
		resultFrame.add(border, BorderLayout.CENTER);
		resultFrame.add(flowPanel, BorderLayout.SOUTH);
		border.setBorder(BorderFactory.createEmptyBorder(ten, ten, ten, ten));
	}

	/**
	 * display method that packs the result frame, sets it visible, and sets the
	 * frame relative to the center of the screen.
	 */
	private void display() {
		resultFrame.pack();
		resultFrame.setLocationRelativeTo(null);
		resultFrame.setVisible(true);
	}

	/**
	 * Main method that creates an ResultGUI object calling its display method
	 * creating the GUI.
	 * 
	 * @param args
	 *            empty string array for main method.
	 */
	public static void main(final String args) {
		new ResultGUI(args).display();
	}

	/**
	 * Private inner class acting as an action listener for our two buttons used
	 * in the program.
	 * 
	 * @author David
	 *
	 */
	private class ButtonPress implements ActionListener {
	  /**
	   * Action listener event.
	   * @param e event.
	   */
		public void actionPerformed(final ActionEvent e) {
				switch (e.getActionCommand()) {
				// Case when new search button is pressed.
				// Case for close button press.
				case "close":
					resultFrame.dispose();
          default:
            break;
				}
		}
	}
}
