package game;


import java.util.Map;
import java.util.Scanner;
import java.util.HashMap;
import java.io.File;
import java.io.FileWriter;
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
		
		File dbStatus = new File("db_status.txt");
		try {
		Scanner fileReader = new Scanner(dbStatus);
		String cont = fileReader.next();
		fileReader.close();
		if (!cont.equals("TRUE")) {
		
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
												
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES ( 1, '<html>Which of the historical figures lived<br>between the years of 1815 and 1898?</html>');");
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES ( 2, '<html>Who was the first Western explorer to<br>reach China?</html>');");
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES ( 3, '<html>In 1971, Idi Amin led a military coup<br>in which country?</html>');");
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES ( 4, '<html>Prime Minister Golda Meir was the leader<br>in what country?</html>');");
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES ( 5, '<html>Which U.S. President established trade<br>between the U.S. and China during the Cold War?</html>');");
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES ( 6, '<html>What controversial structure went up in<br>1961<br>and came down in 1990?</html>');");
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES ( 7, '<html>What political work did<br>Karl Marx co-author?</html>');");
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES ( 8, '<html>What is the name of the famous battle in<br>which Napoleon Bonaparte was finally defeated?</html>');");
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES ( 9, '<html>The assassination of what political leader<br>helped trigger the start of World War 1?</html>');");
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES (10, '<html>Which leader was officially referred to<br>as the Dear Leader?</html>');");
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES (11, '<html>The battle of Hastings in 1066 was fought<br>in which country?</html>');");
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES (12, '<html>Which of these famous generals never lost<br>a battle?</html>');");
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES (13, '<html>Roughly how much per acre did the U.S. pay<br>Russia for the land that is now Alaska?</html>');");
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES (14, '<html>Which student of Aristotle became king of<br>Macedonia and conquered almost all of Persia?</html>');");
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES (15, '<html>Who was in the command module while<br>Neil Armstrong and Buzz Aldrin were on the moon?</html>');");
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES (16, '<html>Fame Roman slave and gladiator, Spartacus,<br>was born in which country?</html>');");
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES (17, '<html>Who is credited with sewing the first U.S. flag?</html>');");
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES (18, '<html>The Hundred Years War was fought<br>between which 2 countries?</html>');");
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES (19, '<html>Who replaced Boris Yeltsin as<br>President of Russia in 1999?</html>');");
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES (20, '<html>What was the first Dynasty in China?</html>');");
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES (21, '<html>Which great leader commited a treasonous act<br>and crossed the Rubicon River?</html>');");
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES (22, '<html>Who is credited with the immoral words:<br>Let them eat cake?</html>');");
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES (23, '<html>Where did the Industrial Revolution begin?</html>');");
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES (24, '<html>The inhabitants of Easter Island originated<br>from which culture?</html>');");
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES (25, '<html>Which of the following figures did not<br>live in the 6th century B.C.?</html>');");
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES (26, '<html>The departure of Soviet troops from<br>Czechoslovakia was part of which event?</html>');");
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES (27, '<html>The Incan Empire was located in which<br>modern country?</html>');");
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES (28, '<html>The Battle of the Bulge took place<br>in which war?</html>');");
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES (29, '<html>Before independece, what was Bangladesh called?</html>');");
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES (30, '<html>What was the Great Western Schism?</html>');");
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES (31, '<html>The first year of the Islamic calender<br>marks which of the following events?</html>');");
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES (32, '<html>Balboa was the first European to<br>do which of the following activities?</html>');");
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES (33, '<html>The invasion of the Goths in the<br>5th century led to the end of which empire?</html>');");
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES (34, '<html>Who was the first female Prime Minister<br>of Great Britian?</html>');");
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES (35, '<html>What charter was passed by King John<br>of England in 1215?</html>');");
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES (36, '<html>The Reign of Terror was a period during<br>which major social-political event?</html>');");
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES (37, '<html>Where were the first modern Olympics<br>held in 1896?</html>');");
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES (38, '<html>Who created the first successful<br>printing press?</html>');");
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES (39, '<html>The Protestant Reformation was<br>started by whom?</html>');");
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES (40, '<html>What culture did the Romans<br>inherit the aqueduct technology from?</html>');");
			
			// INSERT OR REPLACE answers into tables
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('Otto von Bismarck', 1, 1);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('Thomas Jefferson', 1, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('Grigori Rasputin', 1, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('Mohandas K. Ghandi', 1, 0);");
			
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('Ferdinand Magelian', 2, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('James Cook', 2, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('Sir Francis Drake', 2, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('Marco Polo', 2, 1);");
			
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('Papua New Guinea', 3, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('Angola', 3, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('Ghana', 3, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('Uganda', 3, 1);");
			
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('Pakistan', 4, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('Israel', 4, 1);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('Peru', 4, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('India', 4, 0);");
			
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('Jimmy Carter', 5, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('Ronald Reagan', 5, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('Gerald Ford', 5, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('Richard Nixon', 5, 1);");
			
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('Great Wall', 6, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('Jorba Laboratories', 6, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('Berlin Wall', 6, 1);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('John Wayce Gacys home', 6, 0);");
			
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('Mein Kampf', 7, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('The Wealth of Nations', 7, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('The Communist Manifesto', 7, 1);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('The Jungle', 7, 0);");
			
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('Battle of Rolica', 8, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('Battle of Waterloo', 8, 1);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('Battle of Stalingrad', 8, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('Battle of Hastings', 8, 0);");
			
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('Boutros Ghali', 9, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('Franz Ferdinand', 9, 1);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('Czar Nicholas II', 9, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('William McKinley', 9, 0);");
			
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('Vladimir Putin', 10, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('Kim Jong Il', 10, 1);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('Adolf Jitler', 10, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('Fidel Castro', 10, 0);");
			
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('Norway', 11, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('France', 11, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('Russia', 11, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('England', 11, 1);");
			
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('Julius Caesar', 12, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('Alexander the Great', 12, 1);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('Genghis Khan', 12, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('Douglas MacArthur', 12, 0);");
			
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('2 cents', 13, 1);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('50 cents', 13, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('$5.00', 13, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('$12.50', 13, 0);");
			
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('William of Normandy', 14, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('Alexander the Great', 14, 1);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('Attila the Hun', 14, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('Mark Anthony', 14, 0);");
			
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('John Glenn', 15, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('Eugene Cernan', 15, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('Michael Collins', 15, 1);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('Jim Lovell', 15, 0);");
			
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('Greece', 16, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('Italy', 16, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('Bulgaria', 16, 1);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('Albania', 16, 0);");
			
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('Molly Pitcher', 17, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('Betsy Ross', 17, 1);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('Abigail Adams', 17, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('Martha Washington', 17, 0);");
			
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('Italy and Greece', 18, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('Spain and England', 18, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('England and Germany', 18, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('England and France', 18, 1);");
			
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('Vladimir Putin', 19, 1);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('Dmitry Medvedev', 19, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('Mikhail Gorbachev', 19, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('Gennady Yanayev', 19, 0);");
			
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('Zhou Dynasty', 20, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('Qin Dynasty', 20, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('Xia Dynasty', 20, 1);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('Shang Dynasty', 20, 0);");
			
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('Alexander the Great', 21, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('Augustus Caesar', 21, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('Julius Caesar', 21, 1);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('Marcus Aurelius', 21, 0);");
			
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('Josephine de Beauharnais', 22, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('Marie Antoinette', 22, 1);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('King Louis XVI', 22, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('Maria Amalia', 22, 0);");
			
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('United States', 23, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('France', 23, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('China', 23, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('England', 23, 1);");
			
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('The Mayans', 24, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('The Celts', 24, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('The Polynesians', 24, 1);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('The Sardinians', 24, 0);");
			
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('Confucius', 25, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('Buddha', 25, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('Pythagoras', 25, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('Nero', 25, 1);");
			
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('Velvet Revolution', 26, 1);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('The October Revolution', 26, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('The Prague Spring', 26, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('The Cultural Revolution', 26, 0);");
			
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('Mexico', 27, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('Central America', 27, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('Peru', 27, 1);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('Brazil', 27, 0);");
			
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('World War I', 28, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('World War II', 28, 1);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('The Napoleonic Wars', 28, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('Vietnam', 28, 0);");
			
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('Ceylon', 29, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('East Pakistan', 29, 1);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('Bhutan', 29, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('Bangalore', 29, 0);");
			
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>Christianity split into<br>Catholicism and Protestantism</html>', 30, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>The great theological debate<br>over the Trinity</html>', 30, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>More than one pope claimed authority<br>over the Roman Catholic Church</html>', 30, 1);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>The Eastern Orthodox church<br>and the Western Roman church divided</html>', 30, 0);");
			
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('The birth of Muhammad', 31, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('The Quran is assembled into a single sacred text', 31, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('The division of the Sunnis and the Shiites', 31, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('Muhammads flight from Mecca to Medina', 31, 1);");
			
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('Encounter the Pacific Ocean', 32, 1);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('Circumnavigate the globe', 32, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('Sail around the Cape of Good Hope', 32, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('Encounter the Horn of Africa', 32, 0);");
			
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('The Byzantine', 33, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('The Holy Roman', 33, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('The Roman', 33, 1);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('The Ottoman', 33, 0);");
			
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('Florence Nightingale', 34, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('Margaret Thatcher', 34, 1);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('Queen Elizabeth II', 34, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('Jane Austen', 34, 0);");
			
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('Mayflower Compact', 35, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('Magna Carta', 35, 1);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('Stamp Act', 35, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('Quartering Act', 35, 0);");
			
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('American Revolution', 36, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('Cuban Revolution', 36, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('Haitian Revolution', 36, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('French Revolution', 36, 1);");
			
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('France', 37, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('Italy', 37, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('Greece', 37, 1);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('England', 37, 0);");
			
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('Johannes Gutenberg', 38, 1);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('Benjamin Franklin', 38, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('John Calvin', 38, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('Nicolaus Copernicus', 38, 0);");
			
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('John Calvin', 39, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('Francis Bacon', 39, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('Voltaire', 39, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('Martin Luther', 39, 1);");
			
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('The Greeks', 40, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('The Celts', 40, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('The Etruscans', 40, 1);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('The Carthiginians', 40, 0);");
			//}
		} catch (Exception e) {
			e.printStackTrace();
            System.exit(0);
		}
		FileWriter fw = new FileWriter("db_status.txt");
		fw.write("TRUE");
		fw.close();
		}
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