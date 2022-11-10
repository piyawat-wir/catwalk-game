/*
	Java Project 3 | Catwalk Game
	Created by:
		6313124 Chanawee Sateinteeraphap
		6313125 Chayakorn Jullanee
		6313132 Piyawat Wirotkitphaiboon
*/

package Main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileManager {
	
	public static void fileWriter(String fileName, String playerName, int score) throws IOException {
		FileWriter writer = new FileWriter(fileName,true);
		writer.write(String.format("%-50s:%-4d \n", playerName, score));
		writer.close();
	}
	
	public static void fileReader(String fileName) throws IOException {
		boolean opensuccess = false;

		while(!opensuccess) {
			try ( Scanner sc = new Scanner(new File(fileName)) ) {
				opensuccess = true;
				while (sc.hasNext()) {
					processLine(sc.nextLine());
				}
			}
			catch (FileNotFoundException e) { new FileWriter(fileName,true).close(); }
		}
	}
	
	private static void processLine(String line){
		try {
			String[] buffer = line.split(":", 0);

			String playerName = buffer[0].trim();
			int scorePoint = Integer.parseInt(buffer[1].trim());

			ScoreBoard.scoreList.add(new Score(playerName,scorePoint));
		}
		catch(Exception e) {
			System.err.println(e);
			System.err.println(line);
			System.err.println();
		}
	}
}
