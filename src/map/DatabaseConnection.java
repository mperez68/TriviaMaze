package map;


import java.util.Map;
import java.util.HashMap;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import org.sqlite.SQLiteDataSource;

/**
 * Singelton class that connects to a remote database and performs a SQLite query to retrieve
 * questions and answers for Trivia Maze.
 * @author Logan Crawford (crawfl5)
 * @date 11/27/2020
 * @version 2.0
 */
public class DatabaseConnection {

	/**
	 * Represents the URL link to the database.
	 */
	final static String URL = "jdbc:sqlite:questions_answers.db";
	
	public static void build() {
		SQLiteDataSource ds = null;
        try {
            ds = new SQLiteDataSource();
            ds.setUrl(URL);
        } catch ( Exception e ) {
            e.printStackTrace();
            System.exit(0);
        }
		try {
			Connection conn = ds.getConnection();
			Statement st = conn.createStatement();
			// build db tables
			st.executeUpdate("CREATE TABLE IF NOT EXISTS QUESTIONS("
												+ "QID INT NOT NULL,"
												+ "QUESTION VARCHAR(200) NOT NULL,"
												+ "PRIMARY KEY(QID),"
												+ "UNIQUE(QUESTION) );");
			st.executeUpdate("CREATE TABLE IF NOT EXISTS ANSWERS("
												+ "ANSWER VARCHAR(50) NOT NULL,"
												+ "QID INT NOT NULL,"
												+ "CORRECT_FLAG INT NOT NULL,"
												+ "PRIMARY KEY(ANSWER, QID),"
												+ "FOREIGN KEY (QID) REFERENCES QUESTIONS(QID) );");
												
			// insert questions into tables
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES ( 1, 'FILLER QUESTION  1');");
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES ( 2, 'FILLER QUESTION  2');");
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES ( 3, 'FILLER QUESTION  3');");
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES ( 4, 'FILLER QUESTION  4');");
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES ( 5, 'FILLER QUESTION  5');");
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES ( 6, 'FILLER QUESTION  6');");
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES ( 7, 'FILLER QUESTION  7');");
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES ( 8, 'FILLER QUESTION  8');");
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES ( 9, 'FILLER QUESTION  9');");
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES (10, 'FILLER QUESTION 10');");
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES (11, 'FILLER QUESTION 11');");
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES (12, 'FILLER QUESTION 12');");
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES (13, 'FILLER QUESTION 13');");
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES (14, 'FILLER QUESTION 14');");
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES (15, 'FILLER QUESTION 15');");
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES (16, 'FILLER QUESTION 16');");
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES (17, 'FILLER QUESTION 17');");
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES (18, 'FILLER QUESTION 18');");
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES (19, 'FILLER QUESTION 19');");
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES (20, 'FILLER QUESTION 20');");
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES (21, 'FILLER QUESTION 21');");
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES (22, 'FILLER QUESTION 22');");
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES (23, 'FILLER QUESTION 23');");
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES (24, 'FILLER QUESTION 24');");
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES (25, 'FILLER QUESTION 25');");
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES (26, 'FILLER QUESTION 26');");
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES (27, 'FILLER QUESTION 27');");
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES (28, 'FILLER QUESTION 28');");
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES (29, 'FILLER QUESTION 29');");
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES (30, 'FILLER QUESTION 30');");
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES (31, 'FILLER QUESTION 31');");
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES (32, 'FILLER QUESTION 32');");
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES (33, 'FILLER QUESTION 33');");
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES (34, 'FILLER QUESTION 34');");
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES (35, 'FILLER QUESTION 35');");
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES (36, 'FILLER QUESTION 36');");
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES (37, 'FILLER QUESTION 37');");
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES (38, 'FILLER QUESTION 38');");
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES (39, 'FILLER QUESTION 39');");
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES (40, 'FILLER QUESTION 40');");
			
			// INSERT OR REPLACE answers into tables
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('A', 1, 1);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('B', 1, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('C', 1, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('D', 1, 0);");
			
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('A', 2, 1);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('B', 2, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('C', 2, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('D', 2, 0);");
			
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('A', 3, 1);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('B', 3, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('C', 3, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('D', 3, 0);");
			
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('A', 4, 1);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('B', 4, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('C', 4, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('D', 4, 0);");
			
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('A', 5, 1);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('B', 5, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('C', 5, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('D', 5, 0);");
			
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('A', 6, 1);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('B', 6, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('C', 6, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('D', 6, 0);");
			
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('A', 7, 1);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('B', 7, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('C', 7, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('D', 7, 0);");
			
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('A', 8, 1);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('B', 8, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('C', 8, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('D', 8, 0);");
			
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('A', 9, 1);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('B', 9, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('C', 9, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('D', 9, 0);");
			
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('A', 10, 1);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('B', 10, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('C', 10, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('D', 10, 0);");
			
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('A', 11, 1);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('B', 11, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('C', 11, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('D', 11, 0);");
			
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('A', 12, 1);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('B', 12, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('C', 12, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('D', 12, 0);");
			
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('A', 13, 1);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('B', 13, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('C', 13, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('D', 13, 0);");
			
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('A', 14, 1);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('B', 14, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('C', 14, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('D', 14, 0);");
			
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('A', 15, 1);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('B', 15, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('C', 15, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('D', 15, 0);");
			
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('A', 16, 1);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('B', 16, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('C', 16, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('D', 16, 0);");
			
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('A', 17, 1);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('B', 17, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('C', 17, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('D', 17, 0);");
			
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('A', 18, 1);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('B', 18, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('C', 18, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('D', 18, 0);");
			
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('A', 19, 1);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('B', 19, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('C', 19, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('D', 19, 0);");
			
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('A', 20, 1);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('B', 20, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('C', 20, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('D', 20, 0);");
			
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('A', 21, 1);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('B', 21, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('C', 21, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('D', 21, 0);");
			
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('A', 22, 1);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('B', 22, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('C', 22, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('D', 22, 0);");
			
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('A', 23, 1);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('B', 23, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('C', 23, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('D', 23, 0);");
			
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('A', 24, 1);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('B', 24, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('C', 24, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('D', 24, 0);");
			
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('A', 25, 1);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('B', 25, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('C', 25, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('D', 25, 0);");
			
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('A', 26, 1);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('B', 26, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('C', 26, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('D', 26, 0);");
			
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('A', 27, 1);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('B', 27, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('C', 27, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('D', 27, 0);");
			
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('A', 28, 1);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('B', 28, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('C', 28, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('D', 28, 0);");
			
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('A', 29, 1);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('B', 29, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('C', 29, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('D', 29, 0);");
			
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('A', 30, 1);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('B', 30, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('C', 30, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('D', 30, 0);");
			
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('A', 31, 1);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('B', 31, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('C', 31, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('D', 31, 0);");
			
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('A', 32, 1);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('B', 32, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('C', 32, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('D', 32, 0);");
			
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('A', 33, 1);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('B', 33, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('C', 33, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('D', 33, 0);");
			
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('A', 34, 1);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('B', 34, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('C', 34, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('D', 34, 0);");
			
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('A', 35, 1);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('B', 35, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('C', 35, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('D', 35, 0);");
			
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('A', 36, 1);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('B', 36, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('C', 36, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('D', 36, 0);");
			
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('A', 37, 1);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('B', 37, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('C', 37, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('D', 37, 0);");
			
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('A', 38, 1);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('B', 38, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('C', 38, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('D', 38, 0);");
			
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('A', 39, 1);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('B', 39, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('C', 39, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('D', 39, 0);");
			
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('A', 40, 1);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('B', 40, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('C', 40, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('D', 40, 0);");
			
		} catch (Exception e) {
			e.printStackTrace();
            System.exit(0);
		}
	}

	/**
	 * Queries the database for all questions and their ID keys from Questions table in database.
	 * @return The questions and their keys retrieved from the database.
	 */
	public static Map<String, Integer> getQuestions() {
		Map<String, Integer> questions = new HashMap<>();
		String question;
		int qid;
		String query = "SELECT QUESTION, QID FROM QUESTIONS";
		SQLiteDataSource ds = null;
        try {
            ds = new SQLiteDataSource();
            ds.setUrl(URL);
        } catch ( Exception e ) {
            e.printStackTrace();
            System.exit(0);
        }
		try {
			Connection conn = ds.getConnection();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				question = rs.getString("QUESTION");
				qid = rs.getInt("QID");
				questions.put(question, qid);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
            System.exit(0);
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
	public static Map<String, Integer> getAnswers(int theQID) {
		Map<String, Integer> answers = new HashMap<>();
		String ansString;
		int ansInt;
		String query = "SELECT ANSWER, CORRECT_FLAG FROM ANSWERS WHERE QID = " + theQID;
		SQLiteDataSource ds = null;
        try {
            ds = new SQLiteDataSource();
            ds.setUrl(URL);
        } catch ( Exception e ) {
            e.printStackTrace();
            System.exit(0);
        }
		try {
			Connection conn = ds.getConnection();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				ansString = rs.getString("ANSWER");
				ansInt = rs.getInt("CORRECT_FLAG");
				answers.put(ansString, ansInt);
			}
		} catch (Exception e) {
			e.printStackTrace();
            System.exit(0);
		}
		return answers;
	}
}