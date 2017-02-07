package gutenSearch;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * This class loads in text documents and indexes them.
 *
 * @author Mikaila Williams
 */
public class LoadText {
  // Variables
  private String name;
  private ArrayList<String> doc;

  /**
   * This constructor creates a new array list 
   * and sets the name for the class to the name that is
   * passed in.
   *
   * @param textName name of text.
   *          The name of the document being loaded in
   */
  public LoadText(final String textName) {
    this.name = textName;
    doc = new ArrayList<String>();
  }

  /**
   * This method takes the string name of the 
   * file and loads it. Each paragraph is an element in the
   * ArrayList.
   *
   * @param fileName
   *          The name of the file being loaded
   * @exception FileNotFoundException
   *              If the file passed cannot be opened or is not there
   */
  public final void load(final String fileName) throws FileNotFoundException {
    File file = new File(fileName);
    Scanner scan = new Scanner(file);
    String line;
    String paragraph = "";
    // If there is not a first line.
    if (!scan.hasNextLine()) {
      scan.close();
      throw new FileNotFoundException("The file is empty.");
    }
    line = scan.nextLine();
    while (line != null) {
      while (!(line.equals(""))) {
        paragraph = paragraph + line + "\n";
        if (scan.hasNext()) {
          line = scan.nextLine();
        } else {
          line = "";
        }
      }
      doc.add(paragraph);
      paragraph = "";
      if (scan.hasNext()) {
        line = scan.nextLine();
      } else {
        line = null;
      }
    }
    scan.close();
    save();
  }

  /**
   * This method takes the file object and loads it. 
   * Each paragraph is an element in the ArrayList.
   *
   * @param file
   *          The file object being loaded
   * @exception FileNotFoundException
   *              If the file passed cannot be opened or is not there
   */
  public final void load(final File file) throws FileNotFoundException {

    Scanner scan = new Scanner(file);
    String line;
    String paragraph = "";
    // If there is not a first line.
    if (!scan.hasNextLine()) {
      scan.close();
      throw new FileNotFoundException("The file is empty.");
    }
    line = scan.nextLine();
    while (line != null) {
      while (!(line.equals(""))) {
        paragraph = paragraph + line + "\n";
        if (scan.hasNext()) {
          line = scan.nextLine();
        } else {
          line = "";
        }
      }
      doc.add(paragraph);
      paragraph = "";
      if (scan.hasNext()) {
        line = scan.nextLine();
      } else {
        line = null;
      }
    }
    scan.close();
    save();
  }

  /**
   * The getter method for the name of the document.
   *
   * @return The variable name
   */
  public final String getName() {
    return name;
  }

  /**
   * The getter method for the name of the document.
   *
   * @return The variable name
   */
  public final ArrayList<String> getDoc() {
    return doc;
  }

  /**
   * This method saves the doc read in as 
   * separate paragraphs with the same document title and
   * paragraph ordinal number.
   *
   *          The name of the file the object is being saved as
   * @throws FileNotFoundException
   *           If the file cannot be opened
   */
  public final void save() throws FileNotFoundException {
    PrintWriter write = null;
    for (String temp : doc) {
      write = new PrintWriter("GSSourceTexts//" 
          + (doc.indexOf(temp) + 1) + name);
      write.println(temp);
      write.close();
    }
    
  }
}
