package gutenSearch;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class performs the search function for the GutenSearch System.
 * 
 * @author Mikaila Williams
 * @version 12/2/15
 */
public class Search {
  /**
   * Javadoc comment.
   */
	private ArrayList<String> results;

	/**
	 * This constructor creates a new array list, breaks up the user-entered
	 * text into paragraphs, and then adds the paragraphs to the ArrayList.
	 * 
	 * @param args
	 * The user-entered text to be searched.
	 */
	public Search(String args) {
		// Temp Variables
		String paragraph = null;
		File[] allDocuments;
		Scanner scan;
		ArrayList<String> docArray = new ArrayList<>();
		File file = new File("sourceFiles//");
		
		String s = args.toString();
		String[] searchArray = s.split(" ");
		

		allDocuments = file.listFiles();
		
		//This for loop runs through all the files in the
		//directory and adds them to one big array list
		for (File temp : allDocuments) {
			try {
				scan = new Scanner(temp);
				paragraph = temp.getName() + "\n";
				while (scan.hasNext()) {
						paragraph += scan.next() + " ";
				}
				docArray.add(paragraph);
			} catch (FileNotFoundException fnf) {
				fnf.printStackTrace();
			}
		}
		//Enhanced for loop creates a string temp and for each temp
		//in the search array that is split by spaces. 
		for (String query : searchArray) {
			results = getParagraph(query, docArray);
		}
	}

	/**
	 * The getter method for the ArrayList of result strings.
	 *
	 * @return The ArrayList of results
	 */
	public final ArrayList<String> getResults() {
	  
	  
		return results;
	}

	/**
	 * Searches through the document for a specified 
	 * phrase and prints a result string in the format:
	 * Title: ... Ordinal #: ... Paragraph Text: ...
	 *
	 * @param phrase
	 *          The phrase entered by the user to be searched
	 * @return a formatted result string
	 */
	private ArrayList<String> getParagraph(String phrase, 
	    ArrayList<String> docArray) {
		ArrayList<String> result = new ArrayList<>();
		ArrayList<String> lowerCopy = new ArrayList<String>();
		phrase = phrase.toLowerCase();
		for (int i = 0; i < docArray.size(); i++)
		{
		  lowerCopy.add(i, (docArray.get(i).substring(0, docArray.get(i).indexOf("\n") + 1) + 
		      docArray.get(i).substring(docArray.get(i).indexOf("\n") + 1).toLowerCase()));
		}
		
		for (String paragraph: lowerCopy) {
		if (paragraph.substring(paragraph.indexOf("\n") + 1).contains(phrase)) {
		result.add(String.format("Title: %s \tOrdinal #: %s \n\nParagraph Preview: \n\n%s", 
						paragraph.substring(1, paragraph.indexOf("\n")),
						paragraph.substring(0, 1), 
						docArray.get(lowerCopy.indexOf(paragraph)).substring(paragraph.indexOf("\n") + 1)));
			}
		}
		return result;
	}
}
