package gutenSearch;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.junit.Test;
/**
 * Tester of loading text files.
 * @author Mikaila Williams
 *
 */
public class LoadTextTest {
  /**
   * String name tester.
   */
  @Test
  public final void testLoadTextStringName() {
    try {
      PrintWriter write = new PrintWriter("test");
      write.print("This is a test\n");
      write.print("to see if LoadText\n");
      write.println();
      write.print("can separate out\n");
      write.print("paragraphs\n");
      write.close();
      ArrayList<String> expected = new ArrayList<>();
      expected.add("This is a test\nto see if LoadText\n");
      expected.add("can separate out\nparagraphs\n");
      LoadText test = new LoadText("testing");
      test.load("test");
      assertTrue(expected.equals(test.getDoc()));
    } catch (FileNotFoundException fnf) {
      System.out.println(fnf.getMessage());
    }
  }
  /**
   * File path tester.
   */
  @Test
  public final void testLoadTextFilePath() {
    try {
      File file = new File("test");
      PrintWriter write = new PrintWriter(file);
      write.print("This is a test\n");
      write.print("to see if LoadText\n");
      write.println();
      write.print("can separate out\n");
      write.print("paragraphs\n");
      write.close();
      ArrayList<String> expected = new ArrayList<>();
      expected.add("This is a test\nto see if LoadText\n");
      expected.add("can separate out\nparagraphs\n");
      LoadText test = new LoadText("testing");
      test.load(file);
      assertTrue(expected.equals(test.getDoc()));
    } catch (FileNotFoundException fnf) {
      System.out.println(fnf.getMessage());
    }
  }
  /**
   * file loader test.
   */
  @Test
  public final void testEmptyFileForLoadTextFilePath() {
    File file = new File("test");
    LoadText test = new LoadText("testing");
    try {
      test.load(file);
    } catch (FileNotFoundException fnf) {
      assertTrue(true);
    }
  }
  /**
   * load file from string name.
   */
  @Test
  public final void testEmptyFileForLoadTextStringName() {
    try {
      PrintWriter write = new PrintWriter("test");
      write.close();
      LoadText test = new LoadText("testing");
      test.load("test");
    } catch (FileNotFoundException fnf) {
      assertTrue(true);
    }
  }
  /**
   * gets the file name.
   */
  @Test
  public final void testGetName() {
    LoadText test = new LoadText("test");
    assertEquals("test", test.getName());
  }
  /**
   * test getting the document.
   */
  @Test
  public final void testGetDoc() {
    LoadText test = new LoadText("test");
    ArrayList<String> expected = new ArrayList<>();
    assertTrue(expected.equals(test.getDoc()));
  }
  /**
   * test the calling constructor.
   */
  @Test
  public final void testCallConstructor() {
    new LoadText("name");
  }

}

