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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.TextAttribute;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import javax.swing.Action;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.DefaultEditorKit;

/**
 * Sets up the GUI for the administrator window within GutenSearch.
 * 
 * @author David Thompson
 *
 */
public class AdminGUI {
  //All the various things needed building GUI.
  private JFrame frame;
  private String uploadText = "File path...";
  private String titleText = "Text title...";
  JButton clearButton, quitButton, uploadButton, browseButton;
  JLabel uploadLabel, instructLabel, fileListLabel;
  JLabel userLabel;
  final JTextArea text = new JTextArea(15,40);
  final JTextField titleField = new JTextField(20);
  final JTextField pathField = new JTextField(20);
  final DefaultListModel model = new DefaultListModel();
  JScrollPane scroll;
  JFileChooser chooser = new JFileChooser();
  BufferedWriter writer;
  GridBagConstraints constraint = new GridBagConstraints();
  private int i;
  
  
  /**
   * This method builds the admin window with various buttons and text fields
   * and constraints.
   * 
   * @param pane
   *            Accepts an incoming Container object to set up our admin GUI
   *            window.
   */
  public void buildPane(final Container pane) {
    /**
     * Creates a pop up menu for main text display. Allows for right click 
     * cut, copy, and paste functions.
     */
    JPopupMenu menu = new JPopupMenu();
    Action cut = new DefaultEditorKit.CutAction();
    cut.putValue(Action.NAME, "Cut");
    cut.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("control X"));
    menu.add(cut);

    Action copy = new DefaultEditorKit.CopyAction();
    copy.putValue(Action.NAME, "Copy");
    copy.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("control C"));
    menu.add(copy);

    Action paste = new DefaultEditorKit.PasteAction();
    paste.putValue(Action.NAME, "Paste");
    paste.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("control V"));
    menu.add(paste);

    /**
     * Sets up the main text area for data entry and display.
     */
    text.setComponentPopupMenu(menu);
    scroll = new JScrollPane(text);
    pane.setLayout(new GridBagLayout());
    text.setLineWrap(true);
    text.setWrapStyleWord(true);
    text.setEditable(true);
  
    titleField.setText(titleText);
    pathField.setText(uploadText);
    
    /**
     * Browse Button and event listener.
     * Creates the browse button for our GUI. Gives size constraints for the
     * button, and gives an action listener.
     */
    browseButton = new JButton("Browse...");
    browseButton.setPreferredSize(new Dimension(90, 25));
    browseButton.addActionListener(new ActionListener() {
      /**
       * Action event for browse button. Brings up a file chooser window
       * with extension filter of only accepting .txt files. Sets file
       * information locally.
       */
      public void actionPerformed(final ActionEvent e) {
        
        FileNameExtensionFilter filter = 
            new FileNameExtensionFilter("*.txt", "txt", "text");
        chooser.setFileFilter(filter);
        int result = chooser.showOpenDialog(null);

        if (result != JFileChooser.APPROVE_OPTION) {
          return;
        }

        File file = chooser.getSelectedFile();
        String fileDir = file.getAbsolutePath();
        try {
          FileReader reader = new FileReader(fileDir);
          BufferedReader br = new BufferedReader(reader);
          text.read(br, null);
          br.close();
          text.requestFocus();
        } catch (Exception e1) {
          JOptionPane.showMessageDialog(frame, 
              e1, "OK", JOptionPane.INFORMATION_MESSAGE);
        }
        String fileName = file.getName();
        titleField.setText(fileName);
        pathField.setText(fileDir);
      }
    });

    /**
     * Save As Button and event listener.
     * Upload button is created, preferred size is set, and action listener
     * is performed.
     */
    uploadButton = new JButton("Save As...");
    uploadButton.setPreferredSize(new Dimension(90, 25));
    uploadButton.addActionListener(new ActionListener() {
      /**
       * Upload button action event. Creates a LoadText object and sends
       * constructor the titleField text contents. Object is then given
       * text to the path of text file. And save method is called given
       * LoadText object? Needs testing.
       */
      public void actionPerformed(final ActionEvent e) {
        File myDir = new File("sourceFiles");
        myDir.mkdir();

        
        String fileName = JOptionPane.showInputDialog(frame, 
            "Save text file as:   \" *.txt \"");
        if (fileName != null){
        	
        if (fileName.trim().isEmpty()) {
          JOptionPane.showMessageDialog(null, 
              "Please enter a name for your file.",
              "File Name Missing", JOptionPane.INFORMATION_MESSAGE);
          fileName = JOptionPane.showInputDialog(frame, 
              "Save text file as:   \" *.txt \"");
        } else {
          try {
            writer = new BufferedWriter(new FileWriter("sourceFiles//"
                + fileName, false));    
            pathField.setText("sourceFiles\\" + fileName);    
            text.write(writer);
            writer.close(); 
          } catch (IOException event) {
            JOptionPane.showMessageDialog(null, "Error Occured");
            event.printStackTrace();
          }

          try {
            LoadText loader = new LoadText(fileName);
            loader.load(pathField.getText());
          } catch (FileNotFoundException e1) {
            JOptionPane.showMessageDialog(null, 
                "File was not uploaded successfully.",
                "File Upload Failed", JOptionPane.INFORMATION_MESSAGE);
          }
          
          JOptionPane.showMessageDialog(null, 
              "File has been saved successfully.",
              "File Saved", JOptionPane.INFORMATION_MESSAGE);

          model.addElement("sourceFiles\\" + fileName);
          pathField.setText("");
          titleField.setText("");
          text.setText("");
        }
        }
      }
    });
    
    /**
     * Clear Button.
     * Creates a JButton for the clear function. Removes all the text inside
     * the text fields.
     */
    clearButton = new JButton("Clear");
    clearButton.setPreferredSize(new Dimension(90, 25));
    clearButton.addActionListener(new ActionListener() {
      public void actionPerformed(final ActionEvent e) {
        pathField.setText("");
        titleField.setText("");
        text.setText("");
      }
    });
    
    /**
     * Quit Button.
     * Jbutton created for the quit button. Size and functionality given to
     * the button, then added to the GUI pane.
     */
    quitButton = new JButton("Quit");
    quitButton.setPreferredSize(new Dimension(90, 25));
    quitButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(final ActionEvent e) {
        frame.dispose();
      }
    });
    
    /**
     * User text label is created to switch between Admin and User GUI
     * windows. Text is given various attributes, including underlining and
     * coloring.
     */
    userLabel = new JLabel("User");
    Font font = userLabel.getFont();
    userLabel.setForeground(Color.blue);
    Map attributes = font.getAttributes();
    attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
    userLabel.setFont(font.deriveFont(attributes));
    UserLabel link = new UserLabel();
    userLabel.addMouseListener(link);
    
    /**
     * Creates a list of files from a source folder containing our files.
     */
    File folder = new File("sourceFiles//");
    File[] listOfFiles = folder.listFiles();
    
    for(int i = 0; i < listOfFiles.length; i++) {
      model.addElement(listOfFiles[i]);
    }

    /**
     * Creates a JList with the list of files obtained.
     */
    final JList listbox = new JList(model); 
    final JScrollPane pane2 = new JScrollPane(listbox,
        JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
        JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);   

    JMenuItem delete = new JMenuItem("Delete");
    final JPopupMenu menu2 = new JPopupMenu();
    menu2.add(delete);
    listbox.setComponentPopupMenu(menu2);
    
    delete.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent ae){
        model.removeElementAt(i);
      }
    });

    /**
     * Mouse listener for double clicking items in list to bring them 
     * up on display.
     */
    MouseListener mouseListener = new MouseAdapter() {
      
      public void mouseClicked(final MouseEvent mouseEvent) {
        
        JList theList = (JList) mouseEvent.getSource();
     
        if (mouseEvent.getClickCount() == 2) {
          int index = theList.locationToIndex(mouseEvent.getPoint());
          if (index >= 0) {
            Object o = theList.getModel().getElementAt(index);

            try {
              FileReader reader = new FileReader(o.toString());
              BufferedReader br = new BufferedReader(reader);
              text.read(br, null);
              br.close();
              text.requestFocus();
            } catch (Exception e1) {
              JOptionPane.showMessageDialog(frame, 
                  e1, "OK", JOptionPane.INFORMATION_MESSAGE);
            }
          }
        }
      }
    };
    listbox.addMouseListener(mouseListener);
    
    /**
     * Path Field text box.
     * Insert the pathField text field into GUI.
     */
    constraint.gridx = 0;
    constraint.gridy = 2;
    pathField.setEditable(false);
    constraint.insets = new Insets(5, 5, 5, 5);
    pane.add(pathField, constraint);
    
    /**
     * Title Field text box.
     * Insert the title field into GUI.
     */
    constraint.gridx = 0;
    constraint.gridy = 1;
    titleField.setEditable(false);
    constraint.insets = new Insets(5, 5, 5, 5);
    pane.add(titleField, constraint);
    
    /**
     * Upload label.
     * Insert upload label into GUI.
     */
    uploadLabel = new JLabel("File Upload");
    constraint.weightx = 0.5;
    constraint.gridx = 0;
    constraint.gridy = 0;
    constraint.insets = new Insets(5, 5, 5, 5);
    pane.add(uploadLabel, constraint);
    
    /**
     * Browse button.
     * Insert browse button into our GUI with some constraints.
     */
     constraint.gridx = 2;
     constraint.gridy = 1;
     constraint.insets = new Insets(5, 5, 5, 5);
     pane.add(browseButton, constraint);
     
    /**
     * Upload Button
     * Insert SaveAs button into our GUI.
     */
    constraint.gridx = 3;
    constraint.gridy = 1;
    constraint.insets = new Insets(5, 5, 5, 10);
    pane.add(uploadButton, constraint);
    
    /**
     * User window hyperlink.
     * Insert JLabel into GUI.
     */   
    constraint.weightx = 0.5;
    constraint.gridx = 3;
    constraint.gridy = 0;
    constraint.insets = new Insets(5, 60, 5, 5);
    pane.add(userLabel, constraint);
    
    /**
     * Clear button.
     * Insert the clear button onto the GUI with constraints.
     */
    constraint.weightx = 0.5;
    constraint.gridx = 2;
    constraint.gridy = 2;
    constraint.insets = new Insets(5, 5, 5, 5);
    pane.add(clearButton, constraint);   

    /**
     * Quit button.
     * Insert quit button into the GUI.
     */
    constraint.weightx = 0.5;
    constraint.gridx = 3;
    constraint.gridy = 7;
    constraint.insets = new Insets(5, 0, 10, 10);
    pane.add(quitButton, constraint);

    /**
     * Browse for or paste label.
     * Insert a browse JLabel into GUI.
     */
    instructLabel = new JLabel("Browse for file or paste text: ");
    constraint.weightx = 0.5;
    constraint.gridx = 0;
    constraint.gridy = 3;
    constraint.insets = new Insets(5, 5, 5, 5);
    pane.add(instructLabel, constraint);

    /**
     * Main text scroll pane.
     * Insert the text box and scroll pane into the gridboxlayout.
     */
    constraint.gridx = 0;
    constraint.gridy = 4;
    constraint.gridwidth = GridBagConstraints.REMAINDER;
    constraint.insets = new Insets(5, 5, 5, 5);
    pane.add(scroll, constraint);

    /**
     * File list label.
     * Insert the fileListLabel into GUI
     */
    fileListLabel = new JLabel("Uploaded texts: ");
    constraint.weightx = 0.5;
    constraint.gridx = 0;
    constraint.gridy = 5;
    constraint.insets = new Insets(5, 5, 5, 5);
    pane.add(fileListLabel, constraint);

    /**
     * JList pane.
     * Finally Insert the JList into our GUI
     */
    constraint.gridx = 0;
    constraint.gridy = 6;
    constraint.weightx = 1;
    constraint.weighty = 1;
    constraint.fill = GridBagConstraints.HORIZONTAL;
    pane.add(pane2, constraint);
  }

  /**
   * Private method that is called to initialize the new GUI frame, packs, and
   * sets visible.
   */
  private void display() {
    frame = new JFrame("GutenSearch Administrator");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setResizable(false);

    // Set up the content pane.
    buildPane(frame.getContentPane());
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }

  /**
   * Private inner class used for our clicking features inside our GUI. When
   * our User link is clicked, the main in User GUI is called.
   * 
   * @author David
   *
   */
  private class UserLabel extends MouseAdapter {
    @Override
    public void mouseClicked(final MouseEvent e) {
        frame.dispose();
        UserUI.main(new String[0]); 
    }
  }

  /**
   * Main method to call the display method to have our Admin GUI built.
   * 
   * @param args
   *            empty string array for the main.
   */
  public static void main(final String[] args) {
    // Creates an AdminGUI object and calls the display method.
    new AdminGUI().display();
  }
}