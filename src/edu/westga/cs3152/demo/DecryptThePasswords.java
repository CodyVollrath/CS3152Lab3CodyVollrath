package edu.westga.cs3152.demo;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Map;

import edu.westga.cs3152.datatier.FileReaderWriter;
import edu.westga.cs3152.datatier.PasswordHashData;
import edu.westga.cs3152.datatier.StarterPasswordGenerator;
import edu.westga.cs3152.hashing.SimpleCrypt;

/**
 * Decrypts the passwords and puts them into a file
 * @author Cody Vollrath
 * @version Fall 2021
 */
public class DecryptThePasswords {
	private static final String WORST_PASSWORDS_FILENAME = "500-worst-passwords.txt";
	private static final String PASSWORD_DATA_FILENAME = "passwordData.csv";
	private static final Map<String, String> LETTER_NUMBER_SUBS = Map.ofEntries(
			new AbstractMap.SimpleEntry<String, String>("e", "3"),
			new AbstractMap.SimpleEntry<String, String>("i", "1"),
			new AbstractMap.SimpleEntry<String, String>("o", "0"),
			new AbstractMap.SimpleEntry<String, String>("s", "5")
			);
	private static ArrayList<String> hashedPasswords;
	private static ArrayList<String> usernames;
	private static LinkedHashSet<String> passwordPermutations = new LinkedHashSet<String>();

	/**
	 * Decrypts passwords
	 * 
	 * @param args - not used
	 */
	public static void main(String[] args) {
		System.out.println("Cracking Worst Passwords...");
		String summary = crackWorstPasswords();
		System.out.println("Finished! Now Cracking starter passwords this may take time...");
		summary += crackStarterPasswords();
		System.out.print("Output: \n" + summary);
		FileReaderWriter.writeOutputToFile(summary, "crackedPasswords.csv");
	}
	
	private static String crackWorstPasswords() {
		String summary = "";
		PasswordHashData pwData = new PasswordHashData(PASSWORD_DATA_FILENAME);
		SimpleCrypt crypt = new SimpleCrypt();
		ArrayList<String> worstPasswords = FileReaderWriter.readOutputFromFile(WORST_PASSWORDS_FILENAME);
		String[] hashArry = pwData.getHashes();
		hashedPasswords = new ArrayList<String>(hashArry.length);
		hashedPasswords.addAll(Arrays.asList(hashArry));
		String[] usernamesArry = pwData.getUsernames();
		usernames = new ArrayList<String>(usernamesArry.length);
		usernames.addAll(Arrays.asList(usernamesArry));
		for (String password : worstPasswords) {
			checkLetterMutations(password, 0);
		}
		int index = 0;
		for (String hash : hashArry) {
			for (String password : passwordPermutations) {
				if (crypt.checkPassword(password, hash)) {
					String username = usernames.get(index);
					summary += String.format("%s,%s\n", username, password);
					hashedPasswords.remove(hash);
					usernames.remove(username);
					index--;
					break;
				}
			}
			index++;
		}
		return summary;
	}
	
	private static String crackStarterPasswords() {
		String summary = "";
		SimpleCrypt crypt = new SimpleCrypt();
		ArrayList<String> starterPasswords = StarterPasswordGenerator.getStarterPasswords();
		int index = 0;
		for (String hash : hashedPasswords) {
			for (String password : starterPasswords) {
				if (crypt.checkPassword(password, hash)) {
					String username = usernames.get(index);
					summary += String.format("%s,%s\n", username, password);
					break;
				}
			}
			index++;
		}
		return summary;
	}
	
	private static void checkLetterMutations(String password, int currIndex) {
		passwordPermutations.add(password);
		StringBuffer currentPasswordMutation = new StringBuffer(password);
		for (int index = currIndex; index < password.length(); index++) {
			char currentChar = password.charAt(index);
			if (LETTER_NUMBER_SUBS.containsKey(String.valueOf(currentChar))) {
				char replacementChar = LETTER_NUMBER_SUBS.get(String.valueOf(currentChar)).charAt(0);
				checkLetterMutations(currentPasswordMutation.toString(), currIndex + 1);
				currentPasswordMutation.setCharAt(index, replacementChar);
				checkLetterMutations(currentPasswordMutation.toString(), currIndex + 1);
				passwordPermutations.add(currentPasswordMutation.toString());
			}
		}
	}
}
