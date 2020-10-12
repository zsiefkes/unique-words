import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/* Develop a program that can find the unique words in a file and list them together with the time it took to find the unique words. 
Implement two methods, one that uses an ArrayList and the other a Set to store each word.

Success! Finds 18793 words, in ~380ms for arraylist method, ~250ms for set method.

Test with various books (texts provided) and see if the time it takes is similar to the expected times.
If you have more time, find the best way to list all words sorted by number of times they are in the book.
*/

public class Main {
	
	// fields for storing unique words
	ArrayList<String> arrayListWords = new ArrayList<String>();
	ArrayList<String> uniqueWords = new ArrayList<String>();
	Set<String> setWords = new HashSet<String>();
	
	// program execution time
	long execTime;

	// constructor
	public Main() {
		scanFile();
//		listSetWords();
		listArrayListWords();
	}
	
	// find all unique words
	public void scanFile() {
		
		// hardcode filename for now
		String fileName = "MobyDick.txt";
		File file = new File(fileName);
		// check file exists
		if (file.exists()) {

			// start program execution timer
			final long startTime = System.currentTimeMillis();

			// try-catch block
			try {
				Scanner scan = new Scanner(file);
				
				while (scan.hasNext()) {
					// add words to the set
//					setWords.add(scan.next());
					
					// add words to arraylist, checking first if they are contained in there :/
					// wait ... don't we want to like, add them all, then sort the arraylist, and just ummm remove the duplicates? faster than checking each time, right. 'cause contains is O(n) yikes. don't wanna run that n times. kay
					arrayListWords.add(scan.next());
				}
				
				// cool, now sort them and remove the duplicates.
				Collections.sort(arrayListWords);
				String tmp;
				for (int i=0; i<arrayListWords.size(); i++) {
					tmp = arrayListWords.get(i);
					if (i == arrayListWords.size() - 1) {
						if (!arrayListWords.get(i - 1).equalsIgnoreCase(tmp)) {
							uniqueWords.add(tmp);
						}
					}
					if (!arrayListWords.get(i + 1).equalsIgnoreCase(tmp)) {
						uniqueWords.add(tmp);
					}
				}
				
				// close scanner
				scan.close();
				
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Error: " + e);
			}

			// compute program execution time
			final long endTime = System.currentTimeMillis();
			execTime = endTime - startTime;
		}
		
		
	}
	
	public void listSetWords() {
		// list all words from the set
		for (String w : setWords) {
			System.out.println(w);
		}
		System.out.println("Total words: " + setWords.size());
		System.out.println("Total execution time: " + execTime + " milliseconds");
	}

	public void listArrayListWords() {
		// list all words from the arraylist
		for (String w : uniqueWords) {
			System.out.println(w);
		}
		System.out.println("Total words: " + uniqueWords.size());
		System.out.println("Total execution time: " + execTime + " milliseconds");
	}

	public static void main(String[] args)  {
		new Main();
	}
}
