package gutenSearch;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;


import org.junit.Test;
/**
 * Search tester file used to test our search function.
 * @author Mikaila Williams
 *
 */
public final class SearchTest {
  /**
   * Test function 1.
   */
  @Test
  public void testConstructorCall() {
    new Search("test");
  }

  /**
   * Test function 2.
   */
  @Test
  public final void testGetResultsAndGetParaghraph() {
    writeTestFiles();
    Search search = new Search("Hello");
    ArrayList<String> expected = new ArrayList<String>();
    expected.add(String.format("Title: %s \tOrdinal #: %s \n\nParagraph Preview: \n\n%s",
        "testOne", "1", "Hello. "));
    expected.add(String.format("Title: %s \tOrdinal #: %s \n\nParagraph Preview: \n\n%s",
        "testTwo", "3", "Hello. "));
    assertTrue(expected.equals(search.getResults()));
  }
  
  /**
   * Test function 3.
   */
  @Test
  public final void testGetResultsAndGetParaghraph2words() {
    writeTestFiles();
    Search search = new Search("a test");
    ArrayList<String> expected = new ArrayList<String>();
    expected.add(String.format("Title: %s \tOrdinal #: %s \n\nParagraph Preview: \n\n%s",
        "testOne", "2", "This is a test. "));
    expected.add(String.format("Title: %s \tOrdinal #: %s \n\nParagraph Preview: \n\n%s",
        "testTwo", "1", "This is also a test. "));
    expected.add(String.format("Title: %s \tOrdinal #: %s \n\nParagraph Preview: \n\n%s",
        "testTwo", "2", "This Tests the search algorithm. "));
    assertTrue(expected.equals(search.getResults()));
  }

  /**
   * Helper test function.
   */
  public void writeTestFiles() {
    File GSSourceTexts = new File("GSSourceFiles");
    GSSourceTexts.mkdir();
    LoadText load1 = new LoadText("testOne");
    LoadText load2 = new LoadText("testTwo");
    try {
      PrintWriter print = new PrintWriter("testOne");
      print.println("Hello.\n\nThis is a test.\n\nfor theGutenSearch System.");
      print.close();
      print = new PrintWriter("testTwo");
      print.println("This is also a test.\n\n" 
          + "This Tests the search algorithm.\n\nHello.");
      print.close();
      load1.load("testOne");
      load2.load("testTwo");
    } catch (FileNotFoundException fnf) {
      fnf.printStackTrace();
    }
  }
}
