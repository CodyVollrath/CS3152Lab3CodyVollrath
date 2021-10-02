package edu.westga.cs3152.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

import org.mindrot.jbcrypt.BCrypt;

import edu.westga.cs3152.datatier.FileReaderWriter;
import edu.westga.cs3152.datatier.PasswordHashData;
import edu.westga.cs3152.datatier.StarterPasswordGenerator;
import edu.westga.cs3152.hashing.SimpleCrypt;

public class DecryptThePasswords {
	private final static String WORST_PASSWORDS_FILENAME = "500-worst-passwords.txt";
	private final static String PASSWORD_DATA_FILENAME = "passwordData.csv";
	private final static String PASSWORD_DATA_BCRYPT_FILENAME = "passwordDataBCrypt.csv";
	private static ArrayList<String> hashedPasswords;
	private static ArrayList<String> usernames;
	
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
		int index = 0;
		for (String hash : hashArry) {
			for (String password : worstPasswords) {
				if (crypt.checkPassword(password, hash)) {
					String username = usernames.get(index);
					summary += String.format("%s,%s\n", username, password);
					hashedPasswords.remove(hash);
					usernames.remove(username);
					index--;
					break;
				}
				//Check first: e
				if (crypt.checkPassword(password.replaceFirst("[eE]", "3"), hash)) {
					String username = usernames.get(index);
					summary += String.format("%s,%s\n", username, password.replaceFirst("[eE]", "3"));
					hashedPasswords.remove(hash);
					usernames.remove(username);
					index--;
					break;
				}
				//Check Last: e
				if (crypt.checkPassword(password.replaceAll("[eE]$", "3"), hash)) {
					String username = usernames.get(index);
					summary += String.format("%s,%s\n", username, password.replaceAll("[eE]$", "3"));
					hashedPasswords.remove(hash);
					usernames.remove(username);
					index--;
					break;
				}
				//Check all: e
				if (crypt.checkPassword(password.replaceAll("[eE]", "3"), hash)) {
					String username = usernames.get(index);
					summary += String.format("%s,%s\n", username, password.replaceAll("[eE]", "3"));
					hashedPasswords.remove(hash);
					usernames.remove(username);
					index--;
					break;
				}
				//Check first: i
				if (crypt.checkPassword(password.replaceFirst("[iI]", "1"), hash)) {
					String username = usernames.get(index);
					summary += String.format("%s,%s\n", username, password.replaceFirst("[iI]", "1"));
					hashedPasswords.remove(hash);
					usernames.remove(username);
					index--;
					break;
				}
				//Check last: i
				if (crypt.checkPassword(password.replaceAll("[iI]$", "1"), hash)) {
					String username = usernames.get(index);
					summary += String.format("%s,%s\n", username, password.replaceAll("[iI]$", "1"));
					hashedPasswords.remove(hash);
					usernames.remove(username);
					index--;
					break;
				}
				//Check all: i
				if (crypt.checkPassword(password.replaceAll("[iI]", "1"), hash)) {
					String username = usernames.get(index);
					summary += String.format("%s,%s\n", username, password.replaceAll("[iI]", "1"));
					hashedPasswords.remove(hash);
					usernames.remove(username);
					index--;
					break;
				}
				//Check first: o
				if (crypt.checkPassword(password.replaceFirst("[oO]", "0"), hash)) {
					String username = usernames.get(index);
					summary += String.format("%s,%s\n", username, password.replaceFirst("[oO]", "0"));
					hashedPasswords.remove(hash);
					usernames.remove(username);
					index--;
					break;
				}
				//Check last: o
				if (crypt.checkPassword(password.replaceAll("[oO]$", "0"), hash)) {
					String username = usernames.get(index);
					summary += String.format("%s,%s\n", username, password.replaceAll("[oO]$", "0"));
					hashedPasswords.remove(hash);
					usernames.remove(username);
					index--;
					break;
				}
				//Check All: o
				if (crypt.checkPassword(password.replaceAll("[oO]", "0"), hash)) {
					String username = usernames.get(index);
					summary += String.format("%s,%s\n", username, password.replaceAll("[oO]", "0"));
					hashedPasswords.remove(hash);
					usernames.remove(username);
					index--;
					break;
				}
				//Check first: s
				if (crypt.checkPassword(password.replaceFirst("[sS]", "5"), hash)) {
					String username = usernames.get(index);
					summary += String.format("%s,%s\n", username, password.replaceFirst("[sS]", "5"));
					hashedPasswords.remove(hash);
					usernames.remove(username);
					index--;
					break;
				}
				//Check last: s
				if (crypt.checkPassword(password.replaceAll("[sS]$", "5"), hash)) {
					String username = usernames.get(index);
					summary += String.format("%s,%s\n", username, password.replaceAll("[sS]$", "5"));
					hashedPasswords.remove(hash);
					usernames.remove(username);
					index--;
					break;
				}
				//Check All: s
				if (crypt.checkPassword(password.replaceAll("[sS]", "5"), hash)) {
					String username = usernames.get(index);
					summary += String.format("%s,%s\n", username, password.replaceAll("[sS]", "5"));
					hashedPasswords.remove(hash);
					usernames.remove(username);
					index--;
					break;
				}
				//Check All: e, i, o, s
				if (crypt.checkPassword(password.replaceAll("[sS]", "5").replaceAll("[eE]", "3").replaceAll("[oO]", "0").replaceAll("[iI]", "1"), hash)) {
					String username = usernames.get(index);
					summary += String.format("%s,%s\n", username, password.replaceAll("[sS]", "5").replaceAll("[eE]", "3").replaceAll("[oO]", "0").replaceAll("[iI]", "1"));
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
		PasswordHashData pwData = new PasswordHashData(PASSWORD_DATA_FILENAME);
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
}
