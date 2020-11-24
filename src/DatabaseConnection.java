import map.Question;
import java.util.ArrayList;
import javafx.util.Pair;
import java.sql.*;

/**
 * Singelton class that connects to a remote database and performs a MySQL query to retrieve
 * questions and answers for Trivia Maze.
 * @author Logan Crawford (crawfl5)
 * @date 11/23/2020
 * @version 1.0
 */
public class DatabaseConnection {

	/**
	 * Represents the pathto the remote database.
	 */
	final static String DRIVER = "";
	
	/**
	 * Represents the URL link to the database.
	 */
	final static String URL = "";

	/**
	 * Queries the database for all questions and their ID keys from Questions table in database.
	 * @return The questions and their keys retrieved from the database.
	 */
	public static ArrayList<Pair<String, Integer>> getQuestions() {
		ArrayList<Pair<String, Integer>> questions = new ArrayList<>();
		String question;
		int qid;
		try {
			Class.forName(DRIVER);
			Connection conn = DriverManager.getConnection(URL, "root", "");
			String query = "SELECT QUESTION, QID FROM QUESTIONS";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				question = rs.getString("QUESTION");
				qid = rs.getInt("QID");
				questions.add(new Pair<String, Integer>(question, qid));
			}
			
		} catch (Exception e) {
			System.err.println("Got an exception.");
			System.err.println(e.getMessage());
		}
		return questions;
	}
	
	/**
	 * Queries the database for all answers related to a specific question in the database.
	 * @param theQID The integer of the location of the door used to represent the primary key in the
	 * Questions table of the database.
	 * @return A list of up to 4 tuples, representing a mapping of answer strings to their truth value of 
	 * correctness (a 0 represents it is not the correct answer vs a 1 represents it is correct).
	 */
	public static ArrayList<Pair<String, Integer>> getAnswers(int theQID) {
		ArrayList<Pair<String, Integer>> answers = new ArrayList<>();
		String ansString;
		int ansInt;
		try {
			Class.forName(DRIVER);
			Connection conn = DriverManager.getConnection(URL, "root", "");
			String query = "SELECT ANSWER, CORRECT_FLAG FROM ANSWERS WHERE QID = " + theQID;
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				ansString = rs.getString("ANSWER");
				ansInt = rs.getInt("CORRECT_FLAG");
				answers.add(new Pair<String, Integer>(ansString, ansInt));
			}
		} catch (Exception e) {
			System.err.println("Got an exception.");
			System.err.println(e.getMessage());
		}
		return answers;
	}
}