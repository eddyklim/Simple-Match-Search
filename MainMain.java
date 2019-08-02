import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class MainMain {

	public static void main(String[] args) {

		Scanner keyboard = new Scanner(System.in);
		Scanner myFile = new Scanner("");
		try {
			myFile = new Scanner(new File("data2.txt"));
		} catch (Exception e) {
		}

		ArrayList<String> title = new ArrayList<>();
		Set<String> mkeywords = new HashSet<>();

		while (myFile.hasNextLine()) {
			title.add(myFile.nextLine());
			String[] temp = title.get(title.size() - 1).toLowerCase().split(" ");
			for (int i = 0; i < temp.length; i++) {
				mkeywords.add(temp[i]);
			}
		}

		String[] keywords = mkeywords.toArray(new String[0]);

		// This creates the array of keywords by title
		System.out.println(keywords.length + " x " + title.size());
		System.out.println("Creating matrix!...");
		int[][] arrayRep = new int[keywords.length][title.size()];

		// Searches for the keyword in the title
		for (int i = 0; i < title.size(); i++) {
			for (int j = 0; j < keywords.length; j++) {
				String[] titleWords = title.get(i).toLowerCase().split(" ");
				for (int k = 0; k < titleWords.length; k++) {
					if (titleWords[k].toLowerCase().equals(keywords[j].toLowerCase())) {
						arrayRep[j][i] = 1;
						break;
					} else {
						arrayRep[j][i] = 0;
					}
				}
			}
		}

		// This transposes a matrix
		int[][] arrayRepT = new int[title.size()][keywords.length];
		for (int i = 0; i < title.size(); i++) {
			for (int j = 0; j < keywords.length; j++) {
				arrayRepT[i][j] = arrayRep[j][i];
			}
		}

		do {
			System.out.print("Enter search terms: ");
			String mySearchTerm = keyboard.nextLine();
			String[] searchTerms = mySearchTerm.split(" ");

			int[] arrayRepKeywords = new int[keywords.length];

			for (int i = 0; i < keywords.length; i++) {
				for (int j = 0; j < searchTerms.length; j++) {
					if (keywords[i].toLowerCase().equals(searchTerms[j].toLowerCase())) {
						arrayRepKeywords[i] = 1;
					}
				}
			}

			// Multiplies key title matrix to the search matrix
			long startTime = System.nanoTime();
			int[] arrayRepResult = new int[title.size()];
			for (int i = 0; i < title.size(); i++) {
				int temp = 0;
				for (int j = 0; j < keywords.length; j++) {
					temp += (arrayRepT[i][j] * arrayRepKeywords[j]);
				}
				arrayRepResult[i] = temp;
			}
			long endTime = System.nanoTime();

			int bookCount = 0;
			// System.out.println("\n" + title.size() + " x " + 1);
			for (int i = 0; i < title.size(); i++) {
				if (arrayRepResult[i] > 0) {
					System.out.println((i + 1) + " " + arrayRepResult[i] + " " + title.get(i));
					bookCount++;
				}
			}
			System.out
					.println("Books found: " + bookCount + "\nSearch took " + (endTime - startTime) + " nano-seconds");
		} while (true);
	}
}

// // Visuals - prints out key x title table
// for (int j = 0; j < keywords.length; j++) {
// for (int i = 0; i < title.size(); i++) {
// System.out.print(arrayRep[j][i] + " ");
// }
// System.out.println("");
// }

// Print out transposed matrix
// System.out.println("\n" + title.size() + " x " + keywords.length);
// for (int i = 0; i < title.size(); i++) {
// for (int j = 0; j < keywords.length; j++) {
// System.out.print(arrayRepT[i][j] + " ");
// }
// System.out.println("");
// }

// System.out.println("\n" + keywords.length + " x " + 1);
// for (int i = 0; i < keywords.length; i++) {
// System.out.println(arrayRepKeywords[i]);
// }