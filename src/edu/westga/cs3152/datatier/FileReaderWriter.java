package edu.westga.cs3152.datatier;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileReaderWriter {
	private FileReaderWriter() {}
	
	public static void writeOutputToFile(String summaryOutput, String filename) {
		try {
			FileOutputStream outputStream = new FileOutputStream(filename);
			byte[] strToBytes = summaryOutput.getBytes();
			outputStream.write(strToBytes);
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static ArrayList<String> readOutputFromFile(String filename) {
		ArrayList<String> fileData = new ArrayList<String>();
		try (Scanner scan = new Scanner(filename)) {
			String line = scan.nextLine().trim();
			fileData.add(line);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fileData;
	}
	
}