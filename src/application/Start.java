package application;

import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class Start {
	Wykres w1 = new Wykres();

	static int Odpowiedzi[] = new int[Menu.p.size()]; /// Bierz¹ce odpowiedzi

	public int pkt() // zdobyte punkty
	{
		int suma = 0;
		for (int i = 0; i < Odpowiedzi.length; i++) {
			suma += Odpowiedzi[i];
		}
		return suma;
	}

	public void Start(Stage primaryStage, BorderPane root, int pom) {

		///////////////////// TEXT

		Text hello = new Text("TEST PI£KARSKI");
		hello.setStyle("-fx-font-size: 50pt; -font-weight: bold;-fx-font-family: 'Berlin Sans FB Demi';");
		hello.setFill(Color.WHITE);
		hello.setLayoutX(160);
		hello.setLayoutY(135);

		int k = pom + 1;

		Text T1 = new Text("Pytanie numer: " + k);
		T1.setStyle("-fx-font-size: 30pt; -font-weight: bold;-fx-font-family: 'Berlin Sans FB Demi';");
		T1.setFill(Color.WHITE);
		T1.setLayoutX(120);
		T1.setLayoutY(220);

		Text T2 = new Text("Informacja do pytania numer: " + k);
		T2.setStyle("-fx-font-size: 30pt; -font-weight: bold;-fx-font-family: 'Berlin Sans FB Demi';");
		T2.setFill(Color.WHITE);
		T2.setLayoutX(120);
		T2.setLayoutY(200);
		T2.setVisible(false);

		Text Pytanie = new Text(Menu.p.get(pom).getPytanie());
		Pytanie.setFill(Color.WHITE);
		Pytanie.setWrappingWidth(550); // Zawijanie Wierszy po okreœlonej d³ugoœci
		Pytanie.setTextAlignment(TextAlignment.JUSTIFY); // Justowanie Tekstu
		Pytanie.setStyle("-fx-font-size: 20pt; -font-weight: bold;-fx-font-family: 'Arial Bold';");
		Pytanie.setLayoutX(120);
		Pytanie.setLayoutY(280);

		Text Wyjasnienie = new Text(Menu.p.get(pom).getWyjaœnienie());
		Wyjasnienie.setFill(Color.WHITE);
		Wyjasnienie.setWrappingWidth(515); // Zawijanie Wierszy po okreœlonej d³ugoœci
		Wyjasnienie.setTextAlignment(TextAlignment.JUSTIFY); // Justowanie Tekstu
		Wyjasnienie.setStyle("-fx-font-size: 18pt; -font-weight: bold;-fx-font-family: 'Arial Bold';");
		Wyjasnienie.setLayoutX(45);
		Wyjasnienie.setLayoutY(250);
		Wyjasnienie.setVisible(false);

		Text ODP1 = new Text("a) " + Menu.p.get(pom).getA());
		ODP1.setStyle("-fx-font-size: 20pt; -font-weight: bold;-fx-font-family: 'Arial Bold';");
		ODP1.setFill(Color.WHITE);
		ODP1.setLayoutX(105);
		ODP1.setLayoutY(450);

		Text ODP2 = new Text("b) " + Menu.p.get(pom).getB());
		ODP2.setStyle("-fx-font-size: 20pt; -font-weight: bold;-fx-font-family: 'Arial Bold';");
		ODP2.setFill(Color.WHITE);
		ODP2.setLayoutX(435);
		ODP2.setLayoutY(450);

		Text ODP3 = new Text("c) " + Menu.p.get(pom).getC());
		ODP3.setStyle("-fx-font-size: 20pt; -font-weight: bold;-fx-font-family: 'Arial Bold';");
		ODP3.setFill(Color.WHITE);
		ODP3.setLayoutX(105);
		ODP3.setLayoutY(550);

		Text ODP4 = new Text("d) " + Menu.p.get(pom).getD());
		ODP4.setStyle("-fx-font-size: 20pt; -font-weight: bold;-fx-font-family: 'Arial Bold';");
		ODP4.setFill(Color.WHITE);
		ODP4.setLayoutX(435);
		ODP4.setLayoutY(550);

		Text Dalej = new Text(" Dalej ");
		Dalej.setStyle("-fx-font-size: 30pt; -font-weight: bold;-fx-font-family: 'Berlin Sans FB Demi';");
		Dalej.setFill(Color.WHITE);
		Dalej.setLayoutX(630);
		Dalej.setLayoutY(300);
		Dalej.setVisible(false);

		///////////////// RAMKA

		ImageView ramka1 = new ImageView("ramka.png");
		ramka1.setFitHeight(90);
		ramka1.setFitWidth(310);
		ramka1.setLayoutX(85);
		ramka1.setLayoutY(400);
		ramka1.setVisible(false);

		ImageView ramka2 = new ImageView("ramka.png");
		ramka2.setFitHeight(90);
		ramka2.setFitWidth(310);
		ramka2.setLayoutX(415);
		ramka2.setLayoutY(400);
		ramka2.setVisible(false);

		ImageView ramka3 = new ImageView("ramka.png");
		ramka3.setFitHeight(90);
		ramka3.setFitWidth(310);
		ramka3.setLayoutX(85);
		ramka3.setLayoutY(500);
		ramka3.setVisible(false);

		ImageView ramka4 = new ImageView("ramka.png");
		ramka4.setFitHeight(90);
		ramka4.setFitWidth(310);
		ramka4.setLayoutX(415);
		ramka4.setLayoutY(500);
		ramka4.setVisible(false);

		ImageView ramka5 = new ImageView("ramka.png");
		ramka5.setFitHeight(80);
		ramka5.setFitWidth(150);
		ramka5.setLayoutX(615);
		ramka5.setLayoutY(250);
		ramka5.setVisible(false);

		////////////////////// DOBRA I Z£A ODPOWIED

		ImageView Dobry = new ImageView("Dobry.jpg");
		Dobry.setFitHeight(90);
		Dobry.setFitWidth(310);
		Dobry.setLayoutX(415);
		Dobry.setLayoutY(500);
		Dobry.setVisible(false);

		ImageView Z³y = new ImageView("Z³y.jpg");
		Z³y.setFitHeight(90);
		Z³y.setFitWidth(310);
		Z³y.setLayoutX(415);
		Z³y.setLayoutY(500);
		Z³y.setVisible(false);

		///////////// Odpowiedzi

		ODP1.setOnMouseClicked((MouseEvent e) -> { // Po kliknieciu wykonaj

			if (Z³y.isVisible() == false && Dobry.isVisible() == false) { /// Gdy jeszcze nie zaznaczono odpowiedzi
				if (Menu.p.get(pom).getODP().equals(Menu.p.get(pom).getA())) { /// Je¿eli odpowiedz dobra
					Dobry.setLayoutX(ramka1.getLayoutX());
					Dobry.setLayoutY(ramka1.getLayoutY());
					Dobry.setVisible(true);
				} else { // Je¿eli z³a
					Z³y.setLayoutX(ramka1.getLayoutX());
					Z³y.setLayoutY(ramka1.getLayoutY());
					Z³y.setVisible(true);
				}
				T1.setVisible(false);
				Pytanie.setVisible(false);
				Wyjasnienie.setVisible(true);
				T2.setVisible(true);
				Dalej.setVisible(true);
			}

		});

		ODP2.setOnMouseClicked((MouseEvent e) -> { // Po kliknieciu wykonaj

			if (Z³y.isVisible() == false && Dobry.isVisible() == false) {
				if (Menu.p.get(pom).getODP().equals(Menu.p.get(pom).getB())) {
					Dobry.setLayoutX(ramka2.getLayoutX());
					Dobry.setLayoutY(ramka2.getLayoutY());
					Dobry.setVisible(true);
				} else {
					Z³y.setLayoutX(ramka2.getLayoutX());
					Z³y.setLayoutY(ramka2.getLayoutY());
					Z³y.setVisible(true);
				}
				T1.setVisible(false);
				Pytanie.setVisible(false);
				Wyjasnienie.setVisible(true);
				T2.setVisible(true);
				Dalej.setVisible(true);

			}
		});

		ODP3.setOnMouseClicked((MouseEvent e) -> { // Po kliknieciu wykonaj
			if (Z³y.isVisible() == false && Dobry.isVisible() == false) {
				if (Menu.p.get(pom).getODP().equals(Menu.p.get(pom).getC())) {
					Dobry.setLayoutX(ramka3.getLayoutX());
					Dobry.setLayoutY(ramka3.getLayoutY());
					Dobry.setVisible(true);
				} else {
					Z³y.setLayoutX(ramka3.getLayoutX());
					Z³y.setLayoutY(ramka3.getLayoutY());
					Z³y.setVisible(true);
				}
				T1.setVisible(false);
				Pytanie.setVisible(false);
				Wyjasnienie.setVisible(true);
				T2.setVisible(true);
				Dalej.setVisible(true);
			}
		});

		ODP4.setOnMouseClicked((MouseEvent e) -> { // Po kliknieciu wykonaj
			if (Z³y.isVisible() == false && Dobry.isVisible() == false) {
				if (Menu.p.get(pom).getODP().equals(Menu.p.get(pom).getD())) {
					Dobry.setLayoutX(ramka4.getLayoutX());
					Dobry.setLayoutY(ramka4.getLayoutY());
					Dobry.setVisible(true);
				} else {
					Z³y.setLayoutX(ramka4.getLayoutX());
					Z³y.setLayoutY(ramka4.getLayoutY());
					Z³y.setVisible(true);
				}
				T1.setVisible(false);
				Pytanie.setVisible(false);
				Wyjasnienie.setVisible(true);
				T2.setVisible(true);
				Dalej.setVisible(true);
			}
		});

		Dalej.setOnMouseClicked((MouseEvent e) -> {

			if (k < Menu.p.size()) { // je¿eli s¹ jeszcze jakieœ pytania do koñca

				if (Dobry.isVisible() == true) {
					Start.Odpowiedzi[pom] = 1; // Dobra odpowiedz
					DodajDobra(pom);
					root.getChildren().clear();

					Start(primaryStage, root, k); // przechodzimy do nastêpnego pytania

				} else {
					Start.Odpowiedzi[pom] = 0; // Z³a odpowiedz
					DodajZ³a(pom);
					root.getChildren().clear();
					Start(primaryStage, root, k);

				}

			} else {
				if (Dobry.isVisible() == true) { // je¿eli nie to:
					Start.Odpowiedzi[pom] = 1; // Dobra odpowiedz
					DodajDobra(pom);
					root.getChildren().clear();
					w1.Start(primaryStage, root, k, pkt()); // Przechodzimy do podspumowania
				} else {
					Start.Odpowiedzi[pom] = 0; // Z³a odpowiedz
					DodajZ³a(pom);
					root.getChildren().clear();
					w1.Start(primaryStage, root, k, pkt());
				}

			}

		});

		////////////////// RAMKII

		ODP1.setOnMouseExited((MouseEvent e) -> {
			ramka1.setVisible(false);
			ODP1.setPickOnBounds(true);
		});
		ODP1.setOnMouseEntered((MouseEvent e) -> {
			ramka1.setVisible(true);
			ODP1.setPickOnBounds(true);
		});

		ODP2.setOnMouseExited((MouseEvent e) -> {
			ramka2.setVisible(false);
			ODP2.setPickOnBounds(true);
		});
		ODP2.setOnMouseEntered((MouseEvent e) -> {
			ramka2.setVisible(true);
			ODP2.setPickOnBounds(true);
		});

		ODP3.setOnMouseExited((MouseEvent e) -> {
			ramka3.setVisible(false);
			ODP3.setPickOnBounds(true);
		});
		ODP3.setOnMouseEntered((MouseEvent e) -> {
			ramka3.setVisible(true);
			ODP3.setPickOnBounds(true);
		});

		ODP4.setOnMouseExited((MouseEvent e) -> {
			ramka4.setVisible(false);
			ODP4.setPickOnBounds(true);
		});
		ODP4.setOnMouseEntered((MouseEvent e) -> {
			ramka4.setVisible(true);
			ODP4.setPickOnBounds(true);
		});

		Dalej.setOnMouseExited((MouseEvent e) -> {
			ramka5.setVisible(false);
			Dalej.setPickOnBounds(true);
		});
		Dalej.setOnMouseEntered((MouseEvent e) -> {
			ramka5.setVisible(true);
			Dalej.setPickOnBounds(true);
		});

		//////// ROOOT

		root.getChildren().add(Dobry);
		root.getChildren().add(Z³y);
		root.getChildren().add(hello);
		root.getChildren().add(T1);
		root.getChildren().add(Pytanie);
		root.getChildren().add(T2);
		root.getChildren().add(Wyjasnienie);
		root.getChildren().add(ODP1);
		root.getChildren().add(ODP2);
		root.getChildren().add(ODP3);
		root.getChildren().add(ODP4);
		root.getChildren().add(ramka1);
		root.getChildren().add(ramka2);
		root.getChildren().add(ramka3);
		root.getChildren().add(ramka4);
		root.getChildren().add(Dalej);
		root.getChildren().add(ramka5);

	}

	public void DodajDobra(int id) {
		try {
			int port = 754;

			Socket socket = new Socket("127.0.0.1", port);
			System.out.println("Dodaje Dobr¹ Odpowiedz");
			socket.setTcpNoDelay(true);
			OutputStream outputStream = socket.getOutputStream();
			ObjectOutputStream objOutputStream = new ObjectOutputStream(outputStream);
			objOutputStream.writeObject(Menu.p.get(id));
			System.out.println(Menu.p.get(id));
			objOutputStream.flush();
			socket.close();
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	public void DodajZ³a(int id) {
		try {
			int port = 755;

			Socket socket = new Socket("127.0.0.1", port);
			System.out.println("Dodaje Z³¹ Odpowiedz");
			socket.setTcpNoDelay(true);
			OutputStream outputStream = socket.getOutputStream();
			ObjectOutputStream objOutputStream = new ObjectOutputStream(outputStream);
			objOutputStream.writeObject(Menu.p.get(id));
			objOutputStream.flush();
			socket.close();
		} catch (Exception e) {
			System.err.println(e);
		}
	}
}
