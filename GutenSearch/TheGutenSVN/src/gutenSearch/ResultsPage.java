package gutenSearch;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;

/**
 * This class builds the results page GUI that 
 * displays multiple search results. Each result can be
 * expanded to display full details individually.
 * 
 * @author Mikaila Williams and David Thompson
 *
 */
public class ResultsPage {
  // Creates out various J components for our GUI
  private JFrame frame;
  private JTextArea[] textArea;
  private JButton[] moreButton;
  private JButton quitButton, newSearch;
  final int ten = 10, five = 5;

  /**
   * This method builds the results GUI 
   * and sets the various attributes associated within.
   * @param results a string.
   */
  public ResultsPage(final ArrayList<String> results) {
    // Creates a new JFrame and sets general attributes.
    frame = new JFrame("GutenSearch Results");
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    frame.setResizable(true);

    // Creates a new JTextArea
    textArea = new JTextArea[50];

    // For each result i, creates a new JTextArea to display the
    // condensed result.
    for (int i = 0; i < results.size(); i++) {
      textArea[i] = new JTextArea((results.get(i).substring(0, results.get(i).indexOf("Paragraph"))
          + results.get(i).substring(results.get(i).indexOf("Paragraph"), 
              (results.get(i).length() / 3 + results.get(i).indexOf("Paragraph") )) + "..."), five, 30);
      textArea[i].setAlignmentX(JTextArea.RIGHT_ALIGNMENT);
      textArea[i].setLineWrap(true);
      textArea[i].setWrapStyleWord(true);
      textArea[i].setEditable(false);
      Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
      textArea[i].setBorder(border);
    }

    // Creates a JButton Array
    moreButton = new JButton[textArea.length];

    // For each result i, creates a new JButton corresponding the above
    // JTextArea. Button is used to expand result.
    for (int i = 0; i < results.size(); i++) {
      moreButton[i] = new JButton("Read More");
      final String temp = results.get(i);
      moreButton[i].addActionListener(new ActionListener()
      {
        public void actionPerformed(final ActionEvent e) {
          ResultGUI.main(temp);
          //frame.setVisible(false);
        }
      });
      moreButton[i].setPreferredSize(new Dimension(35, 15));
      moreButton[i].setAlignmentX(JButton.RIGHT_ALIGNMENT);
    }

    // Creates a JPanel for main result GUI window.
    JPanel resultsPane = new JPanel();
    resultsPane.setLayout(new BoxLayout(resultsPane, BoxLayout.Y_AXIS));
    resultsPane.setBorder(BorderFactory.createEmptyBorder(ten, ten, ten, ten));

    // For each result i, the text area and read more button are added
    // to the resultPane GUI window.
    for (int i = 0; i < results.size(); i++) {
      resultsPane.add(textArea[i]);
      resultsPane.add(Box.createRigidArea(new Dimension(five, five)));
      resultsPane.add(moreButton[i]);
      resultsPane.add(Box.createRigidArea(new Dimension(five, ten)));
    }

    // Sets up the quit and new search button with dimen. and listeners.
    quitButton = new JButton("Quit");
    quitButton.setPreferredSize(new Dimension(90, 25));
    quitButton.setAlignmentX(JButton.LEFT_ALIGNMENT);
    quitButton.addActionListener(new ActionListener()
    {
      public void actionPerformed(final ActionEvent e) {
        if (e.getSource() == quitButton) {
          frame.dispose();
        }
      }
    });
    // New search button created with dimensions and listener.
    newSearch = new JButton("New Search");
    newSearch.setPreferredSize(new Dimension(90, 25));
    newSearch.addActionListener(new ActionListener()
    {
      public void actionPerformed(final ActionEvent e) {
        if (e.getSource() == newSearch) {
          frame.dispose();
          UserUI.main(new String[0]);
        }
      }
    });
    // Creates a pane and adds the buttons to it.
    JPanel buttonPane = new JPanel();
    buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
    buttonPane.setBorder(BorderFactory.createEmptyBorder(ten, ten, ten, ten));
    buttonPane.add(quitButton);
    buttonPane.add(Box.createRigidArea(new Dimension(ten, 0)));
    buttonPane.add(newSearch);

    // Add the results pane and the button pane to our main GUI frame.
    frame.add(new JScrollPane(resultsPane, 
        JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
        JScrollPane.HORIZONTAL_SCROLLBAR_NEVER), BorderLayout.CENTER);
    frame.add(buttonPane, BorderLayout.PAGE_END);
  }

  /**
   * display method that sets our preferred frame size, 
   * packs it, sets the location to the center of
   * the screen, and makes visible.
   */
  private void display() {
    frame.setPreferredSize(new Dimension(500, 500));
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }

  /**
   * Main method called to build our display GUI window.
   * 
   * @param args
   *          empty string array for our main method
   */
  public static void main(final ArrayList<String> args) {
    new ResultsPage(args).display();
  }
}
