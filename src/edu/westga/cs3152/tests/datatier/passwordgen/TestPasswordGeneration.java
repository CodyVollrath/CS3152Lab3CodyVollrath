package edu.westga.cs3152.tests.datatier.passwordgen;

import java.util.List;

import org.junit.jupiter.api.Test;

import edu.westga.cs3152.datatier.StarterPasswordGenerator;

class TestPasswordGeneration {

	@Test
	void testPasswordGen() {
		List<String> passwords = StarterPasswordGenerator.getStarterPasswords();
		for (String password : passwords) {
			System.out.println(password);
		}
	}

}
