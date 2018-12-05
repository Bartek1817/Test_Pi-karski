package application;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import server.Pytanie;

public class Menu {
	static ArrayList<Pytanie> p = new ArrayList<Pytanie>();

	public static void Startm(Stage primaryStage, BorderPane root) {

		p.clear();
		p = getPytania();

		Start s1 = new Start();
		/////////////////// TEXT

		Text hello = new Text("TEST PI�KARSKI");
		hello.setStyle("-fx-font-size: 50pt; -font-weight: bold;-fx-font-family: 'Berlin Sans FB Demi';");
		hello.setFill(Color.WHITE);
		hello.setLayoutX(160);
		hello.setLayoutY(135);

		Text Rozwi�� = new Text("START           ");
		Rozwi��.setStyle("-fx-font-size: 40pt; -font-weight: bold;-fx-font-family: 'Berlin Sans FB Demi';");
		Rozwi��.setFill(Color.WHITE);
		Rozwi��.setLayoutX(230);
		Rozwi��.setLayoutY(275);

		Text Pomoc = new Text("      POMOC");
		Pomoc.setStyle("-fx-font-size: 40pt; -font-weight: bold;-fx-font-family: 'Berlin Sans FB Demi';");
		Pomoc.setFill(Color.WHITE);
		Pomoc.setLayoutX(300);
		Pomoc.setLayoutY(410);

		Text Wyjscie = new Text(" KONIEC ");
		Wyjscie.setStyle("-fx-font-size: 20pt; -font-weight: bold;-fx-font-family: 'Berlin Sans FB Demi';");
		Wyjscie.setFill(Color.WHITE);
		Wyjscie.setLayoutX(675);
		Wyjscie.setLayoutY(580);

		Text Powr�t = new Text(" POWR�T ");
		Powr�t.setStyle("-fx-font-size: 20pt; -font-weight: bold;-fx-font-family: 'Berlin Sans FB Demi';");
		Powr�t.setFill(Color.WHITE);
		Powr�t.setLayoutX(35);
		Powr�t.setLayoutY(580);
		Powr�t.setVisible(false);

		Text Wykres = new Text(" WYKRES ");
		Wykres.setStyle("-fx-font-size: 20pt; -font-weight: bold;-fx-font-family: 'Berlin Sans FB Demi';");
		Wykres.setFill(Color.WHITE);
		Wykres.setLayoutX(35);
		Wykres.setLayoutY(580);
		Wykres.setVisible(true);

		Text Tekst = new Text(" Aplikacja Test Pi�karski zosta�a stworzona "
				+ "jako projekt z przedmiotu programowanie " + "w j�zyku Java. Test sk�ada si� z 10 pyta� "
				+ "na, kt�r� odpowiada u�ytkownik, po ca�ym " + "te�cie wy�wietlany jest wynik oraz wykres "
				+ "obrazuj�cy histori� wcze�niejszych odpowiedzi " + "innych u�ytkownik�w.\n 	"
				+ "                                Aplikacj� opracowa� \n"
				+ "                                            Bart�omiej �muda");
		Tekst.setWrappingWidth(550); // Zawijanie Wierszy po okre�lonej d�ugo�ci
		Tekst.setTextAlignment(TextAlignment.JUSTIFY); // Justowanie Tekstu
		Tekst.setStyle("-fx-font-size: 20pt; -font-weight: bold;-fx-font-family: 'Arial Bold';");
		Tekst.setFill(Color.WHITE);
		Tekst.setLayoutX(120);
		Tekst.setLayoutY(220);
		Tekst.setVisible(false);

		//////////////// IMAGE VIEW

		ImageView pila = new ImageView("pila.png");
		pila.setFitHeight(125);
		pila.setFitWidth(125);
		pila.setLayoutX(430);
		pila.setLayoutY(198);

		ImageView pytanie = new ImageView("pytanie.png");
		pytanie.setFitHeight(150);
		pytanie.setFitWidth(150);
		pytanie.setLayoutX(215);
		pytanie.setLayoutY(340);

		//////////////// RAMKI
		ImageView ramka = new ImageView("ramka.png");
		ramka.setLayoutX(200);
		ramka.setLayoutY(340);
		ramka.setVisible(false);

		ImageView ramka2 = new ImageView("ramka.png");
		ramka2.setLayoutX(200);
		ramka2.setLayoutY(198);
		ramka2.setVisible(false);

		ImageView ramka3 = new ImageView("ramka.png");
		ramka3.setFitHeight(50);
		ramka3.setFitWidth(125);
		ramka3.setLayoutX(667);
		ramka3.setLayoutY(545);
		ramka3.setVisible(false);

		ImageView ramka4 = new ImageView("ramka.png");
		ramka4.setFitHeight(50);
		ramka4.setFitWidth(125);
		ramka4.setLayoutX(35);
		ramka4.setLayoutY(545);
		ramka4.setVisible(false);

		/////////// WYKONANIE

		pila.setOnMouseExited((MouseEvent e) -> { // Po zjechaniu wykonaj
			ramka2.setVisible(false);

		});
		pila.setOnMouseEntered((MouseEvent e) -> { // Po najechaniu wykonaj
			ramka2.setVisible(true);

		});

		pila.setOnMouseClicked((MouseEvent e) -> { // Po kliknieciu wykonaj
			root.getChildren().clear();
			s1.Start(primaryStage, root, 0);

		});

		Rozwi��.setOnMouseExited((MouseEvent e) -> { // Po zjechaniu wykonaj
			ramka2.setVisible(false);
			Rozwi��.setPickOnBounds(true);
		});

		Rozwi��.setOnMouseEntered((MouseEvent e) -> { // Po najechaniu wykonaj
			ramka2.setVisible(true);
			Rozwi��.setPickOnBounds(true);
		});

		Rozwi��.setOnMouseClicked((MouseEvent e) -> { // Po kliknieciu wykonaj
			root.getChildren().clear();

			s1.Start(primaryStage, root, 0);

		});

		pytanie.setOnMouseExited((MouseEvent e) -> { // Po zjechaniu wykonaj
			ramka.setVisible(false);
			pytanie.setPickOnBounds(true);
		});
		pytanie.setOnMouseEntered((MouseEvent e) -> { // Po najechaniu wykonaj
			ramka.setVisible(true);
			pytanie.setPickOnBounds(true);
		});

		pytanie.setOnMouseClicked((MouseEvent e) -> { // Po kliknieciu wykonaj
			pila.setVisible(false);
			Rozwi��.setVisible(false);
			Pomoc.setVisible(false);
			pytanie.setVisible(false);
			Powr�t.setVisible(true);
			Tekst.setVisible(true);
			Wykres.setVisible(false);
		});

		Pomoc.setOnMouseExited((MouseEvent e) -> { // Po zjechaniu wykonaj
			ramka.setVisible(false);

		});
		Pomoc.setOnMouseEntered((MouseEvent e) -> { // Po najechaniu wykonaj
			ramka.setVisible(true);

		});

		Pomoc.setOnMouseClicked((MouseEvent e) -> { // Po kliknieciu wykonaj
			pila.setVisible(false);
			Rozwi��.setVisible(false);
			Pomoc.setVisible(false);
			pytanie.setVisible(false);
			Powr�t.setVisible(true);
			Tekst.setVisible(true);
			Wykres.setVisible(false);
		});

		Wyjscie.setOnMouseExited((MouseEvent e) -> { // Po zjechaniu wykonaj
			ramka3.setVisible(false);
			Wyjscie.setPickOnBounds(true);
		});
		Wyjscie.setOnMouseEntered((MouseEvent e) -> { // Po najechaniu wykonaj
			ramka3.setVisible(true);
			Wyjscie.setPickOnBounds(true);
		});

		Wyjscie.setOnMouseClicked((MouseEvent e) -> { // Po kliknieciu wykonaj
			System.exit(0);
		});

		Powr�t.setOnMouseExited((MouseEvent e) -> { // Po zjechaniu wykonaj
			ramka4.setVisible(false);
			Powr�t.setPickOnBounds(true);
		});
		Powr�t.setOnMouseEntered((MouseEvent e) -> { // Po najechaniu wykonaj
			ramka4.setVisible(true);
			Powr�t.setPickOnBounds(true);
		});

		Powr�t.setOnMouseClicked((MouseEvent e) -> { // Po kliknieciu wykonaj
			pila.setVisible(true);
			Rozwi��.setVisible(true);
			Pomoc.setVisible(true);
			pytanie.setVisible(true);
			Powr�t.setVisible(false);
			Tekst.setVisible(false);
			Wykres.setVisible(true);

		});

		Wykres.setOnMouseClicked((MouseEvent e) -> { // Po kliknieciu wykonaj
			root.getChildren().clear();
			Wykres2.Start(primaryStage, root);
		});

		Wykres.setOnMouseExited((MouseEvent e) -> { // Po zjechaniu wykonaj
			ramka4.setVisible(false);
			Wykres.setPickOnBounds(true);
		});
		Wykres.setOnMouseEntered((MouseEvent e) -> { // Po najechaniu wykonaj
			ramka4.setVisible(true);
			Wykres.setPickOnBounds(true);
		});

		///////////// ROOT
		root.getChildren().add(pila);
		root.getChildren().add(pytanie);
		root.getChildren().add(hello);
		root.getChildren().add(Rozwi��);
		root.getChildren().add(Pomoc);
		root.getChildren().add(Wyjscie);
		root.getChildren().add(ramka);
		root.getChildren().add(ramka2);
		root.getChildren().add(ramka3);
		root.getChildren().add(ramka4);
		root.getChildren().add(Powr�t);
		root.getChildren().add(Tekst);
		root.getChildren().add(Wykres);

	}

	public static ArrayList<Pytanie> getPytania() {
		try {
			int port = 753;

			Socket socket = new Socket("127.0.0.1", port);
			PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));

			String str = "getPytania";		// Do serwera  Switch;

			socket.setTcpNoDelay(true);
			out.println(str);
			out.flush();

			System.out.println("Pobieranie Start");
			InputStream inputStream = socket.getInputStream();
			ObjectInputStream objInputStream = null;
			objInputStream = new ObjectInputStream(inputStream);
			ArrayList<Pytanie> p = (ArrayList<Pytanie>) objInputStream.readObject();
			System.out.println("Pobieranie Koniec");
			socket.close();
			return p;
		} catch (Exception e) {
			System.err.println(e);
			return null;
		}
	}
}
