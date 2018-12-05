package application;

import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class Pytania {
	static Wykres w1 = new Wykres();

	public static void Start(Stage primaryStage, BorderPane root, int k, int j) {

		////////// TEXT

		Text hello = new Text("Poprawnie Odpowiadziane");
		hello.setStyle("-fx-font-size: 20pt; -font-weight: bold;-fx-font-family: 'Calibri';");
		hello.setFill(Color.WHITE);
		hello.setLayoutX(50);
		hello.setLayoutY(35);
		Text hello2 = new Text("B��dnie Odpowiedziane");
		hello2.setStyle("-fx-font-size: 20pt; -font-weight: bold;-fx-font-family: 'Calibri';");
		hello2.setFill(Color.WHITE);
		hello2.setLayoutX(430);
		hello2.setLayoutY(35);

		Text Powr�t = new Text(" POWR�T ");
		Powr�t.setStyle("-fx-font-size: 20pt; -font-weight: bold;-fx-font-family: 'Berlin Sans FB Demi';");
		Powr�t.setFill(Color.WHITE);
		Powr�t.setLayoutX(35);
		Powr�t.setLayoutY(580);

		String Z = ""; // Zmienna String przyjmuj�ca pytania Dobrze odpowiedziane
		String Y = ""; // Zmienna String przyjmuj�ca pytania �le odpowiedziane
		int pom = 0;
		for (int i = 0; i < Start.Odpowiedzi.length; i++) {
			if (Start.Odpowiedzi[i] == 1) // 1 oznacz dobra odpowied
			{
				pom++;
				Z = Z + " NR." + (i + 1) + " : " + Menu.p.get(i).getPytanie() + " Odpowied�: " + Menu.p.get(i).getODP()
						+ "\n";
			} else {
				Y = Y + " NR." + (i + 1) + " : " + Menu.p.get(i).getPytanie() + " Odpowied�: " + Menu.p.get(i).getODP()
						+ "\n";
			}
		}

		Text Tekst = new Text(Z);
		Tekst.setWrappingWidth(350); // Zawijanie Wierszy po okre�lonej d�ugo�ci
		Tekst.setTextAlignment(TextAlignment.JUSTIFY);
		if (pom < 8)
			Tekst.setStyle("-fx-font-size: 13pt; -font-weight: bold;-fx-font-family: 'Arial Bold';");
		if (pom >= 8)
			Tekst.setStyle("-fx-font-size: 10pt; -font-weight: bold;-fx-font-family: 'Arial Bold';");
		Tekst.setFill(Color.GREEN);
		Tekst.setLayoutX(50);
		Tekst.setLayoutY(70);

		Text Tekst2 = new Text(Y);
		Tekst2.setWrappingWidth(350); // Zawijanie Wierszy po okre�lonej d�ugo�ci
		Tekst2.setTextAlignment(TextAlignment.JUSTIFY); // Justowanie Tekstu
		if (pom < 2)
			Tekst2.setStyle("-fx-font-size: 10pt; -font-weight: bold;-fx-font-family: 'Arial Bold';");
		if (pom >= 2)
			Tekst2.setStyle("-fx-font-size: 13pt; -font-weight: bold;-fx-font-family: 'Arial Bold';");
		Tekst2.setFill(Color.RED);
		Tekst2.setLayoutX(415);
		Tekst2.setLayoutY(70);

		//////////////////// RAMKI

		ImageView ramka4 = new ImageView("ramka.png");
		ramka4.setFitHeight(50);
		ramka4.setFitWidth(125);
		ramka4.setLayoutX(35);
		ramka4.setLayoutY(545);
		ramka4.setVisible(false);

		///////////// WYKONANIE

		Powr�t.setOnMouseClicked((MouseEvent e) -> { // Po kliknieciu wykonaj
			root.getChildren().clear();
			w1.Start(primaryStage, root, k, j); // Powr�t

		});

		Powr�t.setOnMouseExited((MouseEvent e) -> { // Po zjechaniu wykonaj
			ramka4.setVisible(false);
			Powr�t.setPickOnBounds(true);
		});
		Powr�t.setOnMouseEntered((MouseEvent e) -> { // Po najechaniu wykonaj
			ramka4.setVisible(true);
			Powr�t.setPickOnBounds(true);
		});

		root.getChildren().add(hello);
		root.getChildren().add(hello2);
		root.getChildren().add(Powr�t);
		root.getChildren().add(ramka4);
		root.getChildren().add(Tekst);
		root.getChildren().add(Tekst2);
	}
}
