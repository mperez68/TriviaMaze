package gametests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import javax.swing.JLabel;

import org.junit.jupiter.api.Test;

import game.Question;

class QuestionTests {

	private final JLabel tempQ = new JLabel("abc");
	private final JLabel tempAs[] = {new JLabel("1"), new JLabel("2"), new JLabel("3"), new JLabel("4")};
	
	private final String q1 = "Q1";
	private final String[] ansList1 = {"ANS1", "ANS2", "ANS3", "ANS4"};
	private final int correct1 = 1;
	
	private final String q1Out = q1 + "\r\n"
			+ "1) " + ansList1[0] + "\r\n"
			+ "2) " + ansList1[1] + "\r\n"
			+ "3) " + ansList1[2] + "\r\n"
			+ "4) " + ansList1[3] + "\r\n";
	
	private final String q2 = "Q2";
	private final String[] ansList2 = {"ANS5", "ANS6", "ANS7", "ANS8"};
	private final int correct2 = 4;

	@Test
	void testQuestion() {
		final Question testQ = new Question(q1, ansList1, correct1);

		final PrintStream temp = System.out;
		final ByteArrayOutputStream output = new ByteArrayOutputStream();
		System.setOut(new PrintStream(output));
		
		testQ.draw(tempQ, tempAs);

		System.setOut(temp);
		
		assertTrue(q1Out.equals(output.toString()), "Output not matching.");
	}

	@Test
	void testAttempt() {
		final Question testQ1 = new Question(q1, ansList1, correct1);
		final Question testQ2 = new Question(q2, ansList2, correct2);
		
		assertTrue(testQ1.attempt(correct1 + 1), "Input not matching answer. (1 == 1)");
		assertFalse(testQ1.attempt(correct1 + 2), "Input not matching answer. (4 != 1)");
		
		assertTrue(testQ2.attempt(correct2 + 1), "Input not matching answer. (4 == 4)");
		assertFalse(testQ2.attempt(correct2 - 1), "Input not matching answer. (1 != 4)");
	}

}
