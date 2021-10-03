package edu.westga.cs3152.datatier;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Used for file IO
 * @author Cody Vollrath
 * @version Fall 2021
 */
public final class FileReaderWriter {
	
	/**
	 * Writes a string to a file
	 * @param summaryOutput the string to be written to the file
	 * @param filename the name of the file that will get the string
	 */
	public static void writeOutputToFile(String summaryOutput, String filename) {
		try {
			FileOutputStream outputStream = new FileOutputStream(filename);
			byte[] strToBytes = summaryOutput.getBytes();
			outputStream.write(strToBytes);
			outputStream.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * Gets data from a file with each line in the form of a list
	 * @param filename the name of the file
	 * @return a list of the lines in the file
	 */
	public static ArrayList<String> readOutputFromFile(String filename) {
		ArrayList<String> fileData = new ArrayList<String>();
		File file = new File(filename);
		try (Scanner scan = new Scanner(file)) {
			while (scan.hasNext()) {
				String line = scan.nextLine().trim();
				fileData.add(line);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return fileData;
	}
}