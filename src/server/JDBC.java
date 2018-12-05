package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

public class JDBC {
	static Connection con;

	public static boolean checkDriver(String driver) {
		System.out.print("Sprawdzanie sterownika:");
		try {
			Class.forName(driver).newInstance();
			System.out.println(" Sterownik OK");
			return true;
		} catch (Exception e) {
			System.out.println(" B³¹d Steronika");
			return false;
		}
	}

	public static Connection getConnection(String kindOfDatabase, String adres, int port, String userName,
			String password) {

		Connection conn = null;
		Properties connectionProps = new Properties();
		connectionProps.put("user", userName);
		connectionProps.put("password", password);
		connectionProps.put("characterEncoding", "utf8");
		try {
			conn = DriverManager.getConnection(kindOfDatabase + adres + ":" + port + "/", connectionProps);
		} catch (SQLException e) {
			System.out.println("B³¹d po³¹czenia z baz¹ danych! " + e.getMessage() + ": " + e.getErrorCode());
			System.exit(2);
		}
		System.out.println("Poprawne po³¹czenie z baz¹ danych");
		return conn;
	}

	private static Statement createStatement(Connection connection) {
		try {
			return connection.createStatement();
		} catch (SQLException e) {
			System.out.println("B³¹d createStatement! " + e.getMessage() + ": " + e.getErrorCode());
			System.exit(3);
		}
		return null;
	}

	private static void closeConnection(Connection connection, Statement s) {
		System.out.print("\nZamykanie polaczenia z baz¹:");
		try {
			s.close();
			connection.close();
		} catch (SQLException e) {
			System.out.println("Bl¹d przy zamykaniu pol¹czenia z baz¹! " + e.getMessage() + ": " + e.getErrorCode());
			;
			System.exit(4);
		}
		System.out.print(" Udane zamkniêcie bazy");
	}

	/**
	 * Wykonanie kwerendy i przes³anie wyników do obiektu ResultSet
	 * 
	 * @param s
	 *            - Statement
	 * @param sql
	 *            - zapytanie
	 * @return wynik
	 */
	private static ResultSet executeQuery(Statement s, String sql) {
		try {
			return s.executeQuery(sql);
		} catch (SQLException e) {
			System.out.println("Zapytanie nie wykonane! " + e.getMessage() + ": " + e.getErrorCode());
		}
		return null;
	}

	private static int executeUpdate(Statement s, String sql) {
		try {
			return s.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("Zapytanie nie wykonane! " + e.getMessage() + ": " + e.getErrorCode());
		}
		return -1;
	}

	/**
	 * Wyœwietla dane uzyskane zapytaniem select
	 * 
	 * @param r
	 *            - wynik zapytania
	 */
	@SuppressWarnings("unused")
	private static void printDataFromQuery(ResultSet r) {
		ResultSetMetaData rsmd;
		try {
			rsmd = r.getMetaData();
			int numcols = rsmd.getColumnCount(); // pobieranie liczby kolumn
			// wyswietlanie nazw kolumn:
			for (int i = 1; i <= numcols; i++) {
				System.out.print("\t" + rsmd.getColumnLabel(i) + "\t|");
			}
			System.out.print("\n____________________________________________________________________________\n");
			/**
			 * r.next() - przejœcie do kolejnego rekordu (wiersza) otrzymanych wyników
			 */
			// wyswietlanie kolejnych rekordow:
			while (r.next()) {
				for (int i = 1; i <= numcols; i++) {
					Object obj = r.getObject(i);
					if (obj != null)
						System.out.print("\t" + obj.toString() + "\t|");
					else
						System.out.print("\t");
				}
				System.out.println();
			}
		} catch (SQLException e) {
			System.out.println("Bl¹d odczytu z bazy! " + e.getMessage() + ": " + e.getErrorCode());
		}
	}

	/**
	 * Metoda pobiera dane na podstawie nazwy kolumny
	 */
	public static void sqlGetDataByName(ResultSet r) {
		System.out.println("Pobieranie danych z wykorzystaniem nazw kolumn");
		try {
			ResultSetMetaData rsmd = r.getMetaData();
			int numcols = rsmd.getColumnCount();
			// Tytul tabeli z etykietami kolumn zestawow wynikow
			for (int i = 1; i <= numcols; i++) {
				System.out.print(rsmd.getColumnLabel(i) + "\t|\t");
			}
			System.out.print("\n____________________________________________________________________________\n");
			while (r.next()) {
				int size = r.getMetaData().getColumnCount();
				for (int i = 1; i <= size; i++) {
					switch (r.getMetaData().getColumnTypeName(i)) {
					case "INT":
						System.out.print(r.getInt(r.getMetaData().getColumnName(i)) + "\t|\t");
						break;
					case "DATE":
						System.out.print(r.getDate(r.getMetaData().getColumnName(i)) + "\t|\t");
						break;
					case "VARCHAR":
						System.out.print(r.getString(r.getMetaData().getColumnName(i)) + "\t|\t");
						break;
					default:
						System.out.print(r.getMetaData().getColumnTypeName(i));
					}
				}
				System.out.println();
			}
		} catch (SQLException e) {
			System.out.println("Bl¹d odczytu z bazy! " + e.getMessage() + ": " + e.getErrorCode());
		}
	}

	public static void init() {
		if (!checkDriver("com.mysql.jdbc.Driver"))
			System.exit(1);
		con = getConnection("jdbc:mysql://", "localhost", 3306, "root", "");
		Statement st = createStatement(con);
		if (executeUpdate(st, "USE test_zmuda;") == 0)
			System.out.println("Baza test_zmuda Wybrana");
		else {
			System.out.println("Baza nie istnieje! Tworzymy bazê: ");
			if (executeUpdate(st, "create Database test_zmuda DEFAULT CHARSET=utf8;") == 1)
				System.out.println("Baza test_zmuda utworzona");
			else {
				System.out.println("Baza test_zmuda nieutworzona!");
				System.exit(10);
			}
			if (executeUpdate(st, "USE test_zmuda;") == 0)
				System.out.println("Baza test_zmuda wybrana");
			else {
				System.out.println("Baza test_zmuda niewybrana!");
				System.exit(11);
			}
		}
		if (executeUpdate(st, // Uzupe³nione
				"CREATE TABLE pytania ( ID INT NOT NULL, Pytanie VARCHAR(105) NOT NULL, ODP VARCHAR(20) NOT NULL, A VARCHAR(20) NOT NULL, B VARCHAR(20) NOT NULL, C VARCHAR(20) NOT NULL, D VARCHAR(20) NOT NULL,  ODPDOBRE INT NOT NULL,  ODPZ£E INT NOT NULL, wyjaœnienie VARCHAR(190) NOT NULL, PRIMARY KEY (ID) );") == 0)
			System.out.println("Tabela pytania utworzona");
		else
			System.out.println("Tabela pytania nie utworzona!");
		try {
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void initPytania() {
		Statement st = createStatement(con);
		String sql; // Uzupe³nione
		sql = "INSERT INTO pytania VALUES(1, 'Która Reprezentacja by³a Pierwszym Mistrzem Œwiata?', 'Urugwaj', 'Urugwaj', 'Brazylia', 'Niemcy', 'Argentyna', 0, 0, 'Pierwszym Mistrzem Œwiata w 1930 roku by³ Urugwaj pokonuj¹c w finale Argentynê 4-2'),"
				+ "(2, 'Jaka Reprezentacja nigdy nie gra³a w finale mistrzostw Europy?', 'Anglia', 'Czechy', 'Anglia', 'Grecja', 'Belgia', 0, 0, 'Angli nigdy nie uda³o siê dojœæ do Fina³u Mistrzostw Europy. Czesi byli finalistami w 1996 roku, Belgowie w 1980 roku natomiast reprezentacjia Grecji wygra³a fina³ w 2004 roku.'),"
				+ "(3, 'Która Brytyjska dru¿yna siêga³a najczêœciej po Puchar Mistrzów Europy', 'Liverpool', 'Liverpool', 'Manchester United', 'Celtic', 'Chelsea', 0, 0, 'Po najwa¿niejsze Europejskie trofeum z brytyjskich dru¿yn najczêœciej siêga³ Liverpool a¿ 5 krotnie.(1977, 1978, 1981, 1984, 2005)'),"
				+ "(4, 'Która dru¿yna zdoby³a najwiêcej mistrzostw Polski w XXI wieku?', 'Wis³a Kraków', 'Legia Warszawa', 'Lech Poznañ', 'Wis³a Kraków', 'Górnik Zabrze', 0, 0, 'Najbardziej utytu³owanym zespo³em Polskim w XXI wieku pod wzglêdem mistrzostw Polski jest dru¿yna Wis³y Kraków. '),"
				+ "(5, 'Który Pi³karz ma najwiêcej strzelonych bramek w Mistrzostwach Europy?', 'Michael Platini', 'Thierry Henry', 'Cristiano Ronaldo', 'Michael Platini', 'Alan Shearer', 0, 0, 'Najlepszym strzelcem turniejów Mistrzostw Europy jest Michael Platini strzelaj¹c a¿ 9 bramek w zaledwie 5 meczach.'),"
				+ "(6, 'Która z wymienionych Reprezentacji wygrywa³a Mundial najczêœciej?', 'Niemcy', 'Hiszpania', 'Niemcy', 'Francja', 'Argentyna', 0, 0, 'Najwiêcej koñcowych triumfów w Mistrzostwach Œwiata z wymienionych dru¿yn maj¹ Niemcy - cztery zwyciêstwa. Przed nimi w Historii jest jednak Brazylia 5 zdobytych pucharów.'),"
				+ "(7, 'Który pi³karz ma najwiêcej strzelonych bramek w Mistrzostwach Europy w Reprezentacji Polski?', 'Jakub B³aszczykowski', 'Zbigniew Boniek', 'W³odzimierz Lubañski', 'Robert Lewandowski', 'Jakub B³aszczykowski', 0, 0, 'Najwiêcej bramek w turnieju Mistrzostw Europy dla Reprezentacji Polski zdoby³ Jakub B³aszczykowski(3 bramki)'),"
				+ "(8, 'Która z wymienionych dru¿yn najczêœciej wygrywa³a Superpuchar Europy?', 'FC Barcelona', 'Real Madryt', 'Manchester United', 'Juventus Turyn', 'FC Barcelona', 0, 0, 'Superpuchar Europy najczêœciej w historii wygrywa³a Barcelona piêciokrotnie, jest ona równie¿ dru¿yn¹, która najczêœciej przegrywa³a fina³ a¿ czterokrotnie. '),"
				+ "(9, 'Która Reprezentacja nigdy nie zdoby³a pucharu Mistrzostw Œwiata?', 'Holandia', 'Anglia', 'W³ochy', 'Holandia', 'Francja', 0, 0, 'Reprezentacjia Holandi nigdy nie zosta³a Mistrzem Œwiata mimo i¿ bra³a udzia³ w  3 fina³ach.'),"
				+ "(10, 'Która Reprezentacja  z wymienionych wygra³a najwiêcej Mistrzostw Europy?', 'Hiszpania', 'Hiszpania', 'Francja', 'Niemcy', 'Argentyna', 0, 0, 'Po trefoum w turnieju Mistrzostw Europy z wymienionych dru¿yn najwczêœciej siêga³a  reprezentacji Hiszpani(1964,2008,2012)');";
		executeUpdate(st, sql);
		try {
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void close() {
		Statement st = createStatement(con);
		closeConnection(con, st);
		try {
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void dodajDobre(int id) {

		Statement st = createStatement(con);
		try {
			executeUpdate(st, "UPDATE pytania SET ODPDOBRE=ODPDOBRE+1 WHERE id= " + id + ";");
			st.close();
			System.out.println("ODPDOBRE zedytowany id:" + id);
		} catch (SQLException e) {
			System.out.println("B³¹d edycji ODPDOBRE ");
			e.printStackTrace();
		}
	}

	public static void dodajZ³e(int id) {

		Statement st = createStatement(con);
		try {
			executeUpdate(st, "UPDATE pytania SET ODPZ£E=ODPZ£E+1 WHERE id= " + id + ";");
			st.close();
			System.out.println("ODPZ£E zedytowany id:" + id);
		} catch (SQLException e) {
			System.out.println("B³¹d edycji ODPZ£E ");
			e.printStackTrace();
		}
	}

	public static ArrayList<Pytanie> pobierzPytania() {
		System.out.println("Pobieram Pytania");
		Statement st = createStatement(con);
		ResultSet r = executeQuery(st, "SELECT * FROM pytania;");
		ArrayList<Pytanie> o = new ArrayList<Pytanie>();
		try {
			while (r.next())
				o.add(new Pytanie(r.getInt("ID"), r.getString("pytanie"), r.getString("oDP"), r.getString("a"),
						r.getString("b"), r.getString("c"), r.getString("d"), r.getInt("oDPDOBRE"), r.getInt("oDPZ£E"),
						r.getString("wyjaœnienie")));
			st.close();
			System.out.println("Pytania pobrane");
			return o;

		} catch (SQLException e) {
			System.out.println("B³¹d pobierania Pytania");
			e.printStackTrace();
		}
		return o;
	}

}
