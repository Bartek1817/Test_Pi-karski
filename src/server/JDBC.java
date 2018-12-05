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
			System.out.println(" B��d Steronika");
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
			System.out.println("B��d po��czenia z baz� danych! " + e.getMessage() + ": " + e.getErrorCode());
			System.exit(2);
		}
		System.out.println("Poprawne po��czenie z baz� danych");
		return conn;
	}

	private static Statement createStatement(Connection connection) {
		try {
			return connection.createStatement();
		} catch (SQLException e) {
			System.out.println("B��d createStatement! " + e.getMessage() + ": " + e.getErrorCode());
			System.exit(3);
		}
		return null;
	}

	private static void closeConnection(Connection connection, Statement s) {
		System.out.print("\nZamykanie polaczenia z baz�:");
		try {
			s.close();
			connection.close();
		} catch (SQLException e) {
			System.out.println("Bl�d przy zamykaniu pol�czenia z baz�! " + e.getMessage() + ": " + e.getErrorCode());
			;
			System.exit(4);
		}
		System.out.print(" Udane zamkni�cie bazy");
	}

	/**
	 * Wykonanie kwerendy i przes�anie wynik�w do obiektu ResultSet
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
	 * Wy�wietla dane uzyskane zapytaniem select
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
			 * r.next() - przej�cie do kolejnego rekordu (wiersza) otrzymanych wynik�w
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
			System.out.println("Bl�d odczytu z bazy! " + e.getMessage() + ": " + e.getErrorCode());
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
			System.out.println("Bl�d odczytu z bazy! " + e.getMessage() + ": " + e.getErrorCode());
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
			System.out.println("Baza nie istnieje! Tworzymy baz�: ");
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
		if (executeUpdate(st, // Uzupe�nione
				"CREATE TABLE pytania ( ID INT NOT NULL, Pytanie VARCHAR(105) NOT NULL, ODP VARCHAR(20) NOT NULL, A VARCHAR(20) NOT NULL, B VARCHAR(20) NOT NULL, C VARCHAR(20) NOT NULL, D VARCHAR(20) NOT NULL,  ODPDOBRE INT NOT NULL,  ODPZ�E INT NOT NULL, wyja�nienie VARCHAR(190) NOT NULL, PRIMARY KEY (ID) );") == 0)
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
		String sql; // Uzupe�nione
		sql = "INSERT INTO pytania VALUES(1, 'Kt�ra Reprezentacja by�a Pierwszym Mistrzem �wiata?', 'Urugwaj', 'Urugwaj', 'Brazylia', 'Niemcy', 'Argentyna', 0, 0, 'Pierwszym Mistrzem �wiata w 1930 roku by� Urugwaj pokonuj�c w finale Argentyn� 4-2'),"
				+ "(2, 'Jaka Reprezentacja nigdy nie gra�a w finale mistrzostw Europy?', 'Anglia', 'Czechy', 'Anglia', 'Grecja', 'Belgia', 0, 0, 'Angli nigdy nie uda�o si� doj�� do Fina�u Mistrzostw Europy. Czesi byli finalistami w 1996 roku, Belgowie w 1980 roku natomiast reprezentacjia Grecji wygra�a fina� w 2004 roku.'),"
				+ "(3, 'Kt�ra Brytyjska dru�yna si�ga�a najcz�ciej po Puchar Mistrz�w Europy', 'Liverpool', 'Liverpool', 'Manchester United', 'Celtic', 'Chelsea', 0, 0, 'Po najwa�niejsze Europejskie trofeum z brytyjskich dru�yn najcz�ciej si�ga� Liverpool a� 5 krotnie.(1977, 1978, 1981, 1984, 2005)'),"
				+ "(4, 'Kt�ra dru�yna zdoby�a najwi�cej mistrzostw Polski w XXI wieku?', 'Wis�a Krak�w', 'Legia Warszawa', 'Lech Pozna�', 'Wis�a Krak�w', 'G�rnik Zabrze', 0, 0, 'Najbardziej utytu�owanym zespo�em Polskim w XXI wieku pod wzgl�dem mistrzostw Polski jest dru�yna Wis�y Krak�w. '),"
				+ "(5, 'Kt�ry Pi�karz ma najwi�cej strzelonych bramek w Mistrzostwach Europy?', 'Michael Platini', 'Thierry Henry', 'Cristiano Ronaldo', 'Michael Platini', 'Alan Shearer', 0, 0, 'Najlepszym strzelcem turniej�w Mistrzostw Europy jest Michael Platini strzelaj�c a� 9 bramek w zaledwie 5 meczach.'),"
				+ "(6, 'Kt�ra z wymienionych Reprezentacji wygrywa�a Mundial najcz�ciej?', 'Niemcy', 'Hiszpania', 'Niemcy', 'Francja', 'Argentyna', 0, 0, 'Najwi�cej ko�cowych triumf�w w Mistrzostwach �wiata z wymienionych dru�yn maj� Niemcy - cztery zwyci�stwa. Przed nimi w Historii jest jednak Brazylia 5 zdobytych puchar�w.'),"
				+ "(7, 'Kt�ry pi�karz ma najwi�cej strzelonych bramek w Mistrzostwach Europy w Reprezentacji Polski?', 'Jakub B�aszczykowski', 'Zbigniew Boniek', 'W�odzimierz Luba�ski', 'Robert Lewandowski', 'Jakub B�aszczykowski', 0, 0, 'Najwi�cej bramek w turnieju Mistrzostw Europy dla Reprezentacji Polski zdoby� Jakub B�aszczykowski(3 bramki)'),"
				+ "(8, 'Kt�ra z wymienionych dru�yn najcz�ciej wygrywa�a Superpuchar Europy?', 'FC Barcelona', 'Real Madryt', 'Manchester United', 'Juventus Turyn', 'FC Barcelona', 0, 0, 'Superpuchar Europy najcz�ciej w historii wygrywa�a Barcelona pi�ciokrotnie, jest ona r�wnie� dru�yn�, kt�ra najcz�ciej przegrywa�a fina� a� czterokrotnie. '),"
				+ "(9, 'Kt�ra Reprezentacja nigdy nie zdoby�a pucharu Mistrzostw �wiata?', 'Holandia', 'Anglia', 'W�ochy', 'Holandia', 'Francja', 0, 0, 'Reprezentacjia Holandi nigdy nie zosta�a Mistrzem �wiata mimo i� bra�a udzia� w  3 fina�ach.'),"
				+ "(10, 'Kt�ra Reprezentacja  z wymienionych wygra�a najwi�cej Mistrzostw Europy?', 'Hiszpania', 'Hiszpania', 'Francja', 'Niemcy', 'Argentyna', 0, 0, 'Po trefoum w turnieju Mistrzostw Europy z wymienionych dru�yn najwcz�ciej si�ga�a  reprezentacji Hiszpani(1964,2008,2012)');";
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
			System.out.println("B��d edycji ODPDOBRE ");
			e.printStackTrace();
		}
	}

	public static void dodajZ�e(int id) {

		Statement st = createStatement(con);
		try {
			executeUpdate(st, "UPDATE pytania SET ODPZ�E=ODPZ�E+1 WHERE id= " + id + ";");
			st.close();
			System.out.println("ODPZ�E zedytowany id:" + id);
		} catch (SQLException e) {
			System.out.println("B��d edycji ODPZ�E ");
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
						r.getString("b"), r.getString("c"), r.getString("d"), r.getInt("oDPDOBRE"), r.getInt("oDPZ�E"),
						r.getString("wyja�nienie")));
			st.close();
			System.out.println("Pytania pobrane");
			return o;

		} catch (SQLException e) {
			System.out.println("B��d pobierania Pytania");
			e.printStackTrace();
		}
		return o;
	}

}
