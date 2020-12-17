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
			st.executeUpdate("DROP TABLE IF EXISTS QUESTIONS;");
			st.executeUpdate("DROP TABLE IF EXISTS ANSWERS;");
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
												
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES ( 1, '<html>Which of the historical figures lived <br/>between the years of 1815 and 1898?</html>');");
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES ( 2, '<html>Who was the first Western explorer to <br/>reach China?</html>');");
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES ( 3, '<html>In 1971, Idi Amin led a military coup <br/>in which country?</html>');");
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES ( 4, '<html>Prime Minister Golda Meir was the <br/>leader in what country?</html>');");
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES ( 5, '<html>Which U.S. President established trade <br/>between the U.S. and China during the <br/>Cold War?</html>');");
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES ( 6, '<html>What controversial structure went up in <br/>1961 and came down in 1990?</html>');");
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES ( 7, '<html>What political work did Karl Marx <br/>co-author?</html>');");
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES ( 8, '<html>What is the name of the famous battle <br/>in which Napoleon Bonaparte was <br/>finally defeated?</html>');");
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES ( 9, '<html>The assassination of what political <br/>leader helped trigger the start of <br/>World War 1?</html>');");
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES (10, '<html>Which leader was officially referred to <br/>as the Dear Leader?</html>');");
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES (11, '<html>The Battle of Hastings in 1066 was <br/>fought in which country?</html>');");
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES (12, '<html>Which of these famous generals never <br/>lost a battle?</html>');");
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES (13, '<html>Roughly how much per acre did the U.S. <br/>pay Russia for the land that is now <br/>Alaska?</html>');");
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES (14, '<html>Which student of Aristotle became king <br/>of Macedonia and conquered almost all <br/>of Persia?</html>');");
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES (15, '<html>Who was in the command module while <br/>Neil Armstrong and Buzz Aldrin were on <br/>the moon?</html>');");
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES (16, '<html>Famed Roman slave and gladiator, <br/>Spartacus, was born in which country?</html>');");
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES (17, '<html>Who is credited with sewing the first <br/>U.S. flag?</html>');");
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES (18, '<html>The Hundred Years War was fought <br/>between which 2 countries?</html>');");
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES (19, '<html>Who replaced Boris Yeltsin as President <br/>of Russia in 1999?</html>');");
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES (20, '<html>What was the first Dynasty in China?</html>');");
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES (21, '<html>Which great leader commited a <br/>treasonous act and crossed the Rubicon <br/>River?</html>');");
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES (22, '<html>Who is credited with the immortal <br/>words: Let them eat cake?</html>');");
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES (23, '<html>Where did the Industrial Revolution <br/>begin?</html>');");
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES (24, '<html>The inhabitants of Easter Island <br/>originated from which culture?</html>');");
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES (25, '<html>Which of the following figures did not <br/>live in the 6th century B.C.?</html>');");
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES (26, '<html>The departure of Soviet troops from <br/>Czechoslovakia was part of which event?</html>');");
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES (27, '<html>The Incan Empire was located in which <br/>modern country?</html>');");
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES (28, '<html>The Battle of the Bulge took place in <br/>which war?</html>');");
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES (29, '<html>Before independece, what was Bangladesh <br/>called?</html>');");
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES (30, '<html>What was the Great Western Schism?</html>');");
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES (31, '<html>The first year of the Islamic calender <br/>marks which of the following events?</html>');");
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES (32, '<html>Balboa was the first European to do <br/>which of the following activities?</html>');");
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES (33, '<html>The invasion of the Goths in the 5th <br/>century led to the end of which empire</html>?');");
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES (34, '<html>Who was the first female Prime Minister <br/>of Great Britian?</html>');");
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES (35, '<html>What charter was passed by King John of <br/>England in 1215?</html>');");
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES (36, '<html>The Reign of Terror was a period during <br/>which major social-political event?</html>');");
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES (37, '<html>Where were the first modern Olympics <br/>held in 1896?</html>');");
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES (38, '<html>Who created the first successful <br/>printing press?</html>');");
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES (39, '<html>The Protestant Reformation was started <br/>by whom?</html>');");
			st.executeUpdate("INSERT OR REPLACE INTO QUESTIONS VALUES (40, '<html>What culture did the Romans inherit the <br/>aqueduct technology from?</html>');");
			
			// INSERT OR REPLACE answers into tables
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>Otto von Bismarck</html>', 1, 1);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>Thomas Jefferson</html>', 1, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>Grigori Rasputin</html>', 1, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>Mohandas K. Ghandi</html>', 1, 0);");
			
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>Ferdinand Magelian</html>', 2, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>James Cook</html>', 2, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>Sir Francis Drake</html>', 2, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>Marco Polo</html>', 2, 1);");
			
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>Papua New Guinea</html>', 3, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>Angola</html>', 3, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>Ghana</html>', 3, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>Uganda</html>', 3, 1);");
			
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>Pakistan</html>', 4, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>Israel</html>', 4, 1);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>Peru</html>', 4, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>India</html>', 4, 0);");
			
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>Jimmy Carter</html>', 5, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>Ronald Reagan</html>', 5, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>Gerald Ford</html>', 5, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>Richard Nixon</html>', 5, 1);");
			
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>Great Wall</html>', 6, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>Jorba Laboratories</html>', 6, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>Berlin Wall</html>', 6, 1);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>John Wayce Gacys home</html>', 6, 0);");
			
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>Mein Kampf</html>', 7, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>The Wealth of Nations</html>', 7, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>The Communist Manifesto</html>', 7, 1);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>The Jungle</html>', 7, 0);");
			
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>Battle of Rolica</html>', 8, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>Battle of Waterloo</html>', 8, 1);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>Battle of Stalingrad</html>', 8, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>Battle of Hastings</html>', 8, 0);");
			
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>Boutros Ghali</html>', 9, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>Franz Ferdinand</html>', 9, 1);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>Czar Nicholas II</html>', 9, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>William McKinley</html>', 9, 0);");
			
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>Vladimir Putin</html>', 10, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>Kim Jong Il</html>', 10, 1);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>Adolf Hitler</html>', 10, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>Fidel Castro</html>', 10, 0);");
			
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>Norway</html>', 11, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>France</html>', 11, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>Russia</html>', 11, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>England</html>', 11, 1);");
			
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>Julius Caesar</html>', 12, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>Alexander the Great</html>', 12, 1);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>Genghis Khan</html>', 12, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>Douglas MacArthur</html>', 12, 0);");
			
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>2 cents</html>', 13, 1);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>50 cents</html>', 13, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>$5.00</html>', 13, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>$12.50</html>', 13, 0);");
			
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>William of Normandy</html>', 14, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>Alexander the Great</html>', 14, 1);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>Attila the Hun</html>', 14, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>Mark Anthony</html>', 14, 0);");
			
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>John Glenn</html>', 15, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>Eugene Cernan</html>', 15, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>Michael Collins</html>', 15, 1);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>Jim Lovell</html>', 15, 0);");
			
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>Greece</html>', 16, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>Italy</html>', 16, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>Bulgaria</html>', 16, 1);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>Albania</html>', 16, 0);");
			
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>Molly Pitcher</html>', 17, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>Betsy Ross</html>', 17, 1);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>Abigail Adams</html>', 17, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>Martha Washington</html>', 17, 0);");
			
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>Italy and Greece</html>', 18, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>Spain and England</html>', 18, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>England and Germany</html>', 18, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>England and France</html>', 18, 1);");
			
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>Vladimir Putin</html>', 19, 1);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>Dmitry Medvedev</html>', 19, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>Mikhail Gorbachev</html>', 19, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>Gennady Yanayev</html>', 19, 0);");
			
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>Zhou Dynasty</html>', 20, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>Qin Dynasty</html>', 20, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>Xia Dynasty</html>', 20, 1);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>Shang Dynasty</html>', 20, 0);");
			
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>Alexander the Great</html>', 21, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>Augustus Caesar</html>', 21, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>Julius Caesar</html>', 21, 1);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>Marcus Aurelius</html>', 21, 0);");
			
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>Josephine de Beauharnais</html>', 22, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>Marie Antoinette</html>', 22, 1);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>King Louis XVI</html>', 22, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>Maria Amalia</html>', 22, 0);");
			
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>United States</html>', 23, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>France</html>', 23, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>China</html>', 23, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>England</html>', 23, 1);");
			
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>The Mayans</html>', 24, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>The Celts</html>', 24, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>The Polynesians</html>', 24, 1);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>The Sardinians</html>', 24, 0);");
			
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>Confucius</html>', 25, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>Buddha</html>', 25, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>Pythagoras</html>', 25, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>Nero</html>', 25, 1);");
			
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>Velvet Revolution</html>', 26, 1);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>The October Revolution</html>', 26, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>The Prague Spring</html>', 26, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>The Cultural Revolution</html>', 26, 0);");
			
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>Mexico</html>', 27, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>Central America</html>', 27, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>Peru</html>', 27, 1);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>Brazil</html>', 27, 0);");
			
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>World War I</html>', 28, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>World War II</html>', 28, 1);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>The Napoleonic Wars</html>', 28, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>Vietnam</html>', 28, 0);");
			
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>Ceylon</html>', 29, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>East Pakistan</html>', 29, 1);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>Bhutan</html>', 29, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>Bangalore</html>', 29, 0);");
			
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>Christianity split into <br/>Catholicism and Protestantism</html>', 30, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>The great theological debate <br/>over the Trinity</html>', 30, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>More than one pope claimed <br/>authority over the Roman <br/>Catholic Church</html>', 30, 1);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>The Eastern Orthodox church <br/>and the Western Roman church <br/>divided</html>', 30, 0);");
			
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>The birth of Muhammad</html>', 31, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>The Quran is assembled into a <br/>single sacred text</html>', 31, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>The division of the Sunnis <br/>and the Shiites</html>', 31, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>Muhammads flight from Mecca <br/>to Medina</html>', 31, 1);");
			
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>Encounter the Pacific Ocean</html>', 32, 1);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>Circumnavigate the globe</html>', 32, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>Sail around the Cape of Good <br/>Hope</html>', 32, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>Encounter the Horn of Africa</html>', 32, 0);");
			
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>The Byzantine</html>', 33, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>The Holy Roman</html>', 33, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>The Roman</html>', 33, 1);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>The Ottoman</html>', 33, 0);");
			
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>Florence Nightingale</html>', 34, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>Margaret Thatcher</html>', 34, 1);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>Queen Elizabeth II</html>', 34, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>Jane Austen</html>', 34, 0);");
			
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>Mayflower Compact</html>', 35, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>Magna Carta</html>', 35, 1);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>Stamp Act</html>', 35, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>Quartering Act</html>', 35, 0);");
			
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>American Revolution</html>', 36, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>Cuban Revolution</html>', 36, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>Haitian Revolution</html>', 36, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>French Revolution</html>', 36, 1);");
			
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>France</html>', 37, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>Italy</html>', 37, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>Greece</html>', 37, 1);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>England</html>', 37, 0);");
			
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>Johannes Gutenberg</html>', 38, 1);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>Benjamin Franklin</html>', 38, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>John Calvin</html>', 38, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>Nicolaus Copernicus</html>', 38, 0);");
			
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>John Calvin</html>', 39, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>Francis Bacon</html>', 39, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>Voltaire</html>', 39, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>Martin Luther</html>', 39, 1);");
			
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>The Greeks</html>', 40, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>The Celts</html>', 40, 0);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>The Etruscans</html>', 40, 1);");
			st.executeUpdate("INSERT OR REPLACE INTO ANSWERS VALUES ('<html>The Carthiginians</html>', 40, 0);");
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