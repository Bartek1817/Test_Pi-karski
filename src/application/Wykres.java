package application;

import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Wykres {

	public void Start(Stage primaryStage, BorderPane root, int k, int j) {

		///////////////// TEXT
		Menu.p = Menu.getPytania(); // Na nowo pobrane, juz po odpowiedziach

		Text hello = new Text("TEST PI£KARSKI");
		hello.setStyle("-fx-font-size: 50pt; -font-weight: bold;-fx-font-family: 'Berlin Sans FB Demi';");
		hello.setFill(Color.WHITE);
		hello.setLayoutX(160);
		hello.setLayoutY(135);

		Text T1 = new Text("Podsumowanie, Twój Wynik to " + j + "/" + k + ".");
		T1.setStyle("-fx-font-size: 30pt; -font-weight: bold;-fx-font-family: 'Berlin Sans FB Demi';");
		T1.setFill(Color.WHITE);
		T1.setLayoutX(80);
		T1.setLayoutY(220);

		Text Wyjscie = new Text(" KONIEC ");
		Wyjscie.setStyle("-fx-font-size: 20pt; -font-weight: bold;-fx-font-family: 'Berlin Sans FB Demi';");
		Wyjscie.setFill(Color.WHITE);
		Wyjscie.setLayoutX(675);
		Wyjscie.setLayoutY(580);

		Text Wykres = new Text(" WYKRES ");
		Wykres.setStyle("-fx-font-size: 30pt; -font-weight: bold;-fx-font-family: 'Berlin Sans FB Demi';");
		Wykres.setFill(Color.WHITE);
		Wykres.setLayoutX(300);
		Wykres.setLayoutY(330);

		Text Pytania1 = new Text(" PYTANIA ");
		Pytania1.setStyle("-fx-font-size: 30pt; -font-weight: bold;-fx-font-family: 'Berlin Sans FB Demi';");
		Pytania1.setFill(Color.WHITE);
		Pytania1.setLayoutX(300);
		Pytania1.setLayoutY(440);

		Text New = new Text("OD NOWA");
		New.setStyle("-fx-font-size: 20pt; -font-weight: bold;-fx-font-family: 'Berlin Sans FB Demi';");
		New.setFill(Color.WHITE);
		New.setLayoutX(35);
		New.setLayoutY(580);
		New.setVisible(true);

		//////////////////// RAMKI

		ImageView ramka1 = new ImageView("ramka.png");
		ramka1.setFitHeight(70);
		ramka1.setFitWidth(175);
		ramka1.setLayoutX(300);
		ramka1.setLayoutY(285);
		ramka1.setVisible(false);

		ImageView ramka2 = new ImageView("ramka.png");
		ramka2.setFitHeight(70);
		ramka2.setFitWidth(195);
		ramka2.setLayoutX(300);
		ramka2.setLayoutY(395);
		ramka2.setVisible(false);

		ImageView ramka3 = new ImageView("ramka.png");
		ramka3.setFitHeight(50);
		ramka3.setFitWidth(125);
		ramka3.setLayoutX(667);
		ramka3.setLayoutY(545);
		ramka3.setVisible(false);

		ImageView ramka4 = new ImageView("ramka.png");
		ramka4.setFitHeight(50);
		ramka4.setFitWidth(135);
		ramka4.setLayoutX(35);
		ramka4.setLayoutY(545);
		ramka4.setVisible(false);

		///////////// WYKONANIE

		Wykres.setOnMouseExited((MouseEvent e) -> { // Po zjechaniu wykonaj
			ramka1.setVisible(false);
			Wykres.setPickOnBounds(true);
		});
		Wykres.setOnMouseEntered((MouseEvent e) -> { // Po najechaniu wykonaj
			ramka1.setVisible(true);
			Wykres.setPickOnBounds(true);
		});
		Wykres.setOnMouseClicked((MouseEvent e) -> { // Po kliknieciu wykonaj
			root.getChildren().clear();
			Wykres1.Start(primaryStage, root, k, j);
		});

		Pytania1.setOnMouseExited((MouseEvent e) -> {
			ramka2.setVisible(false);
			Pytania1.setPickOnBounds(true);
		});
		Pytania1.setOnMouseEntered((MouseEvent e) -> {
			ramka2.setVisible(true);
			Pytania1.setPickOnBounds(true);
		});
		Pytania1.setOnMouseClicked((MouseEvent e) -> {
			Pytania1.setPickOnBounds(true);
			root.getChildren().clear();
			Pytania.Start(primaryStage, root, k, j);
		});

		Wyjscie.setOnMouseExited((MouseEvent e) -> {
			ramka3.setVisible(false);
			Wyjscie.setPickOnBounds(true);
		});
		Wyjscie.setOnMouseEntered((MouseEvent e) -> {
			ramka3.setVisible(true);
			Wyjscie.setPickOnBounds(true);
		});

		Wyjscie.setOnMouseClicked((MouseEvent e) -> {
			System.exit(0);
		});

		New.setOnMouseClicked((MouseEvent e) -> { // Po kliknieciu wykonaj
			root.getChildren().clear();
			Menu.Startm(primaryStage, root);

		});

		New.setOnMouseExited((MouseEvent e) -> { // Po zjechaniu wykonaj
			ramka4.setVisible(false);
			New.setPickOnBounds(true);
		});
		New.setOnMouseEntered((MouseEvent e) -> { // Po najechaniu wykonaj
			ramka4.setVisible(true);
			New.setPickOnBounds(true);
		});

		root.getChildren().add(hello);
		root.getChildren().add(New);
		root.getChildren().add(Wykres);
		root.getChildren().add(Pytania1);
		root.getChildren().add(T1);
		root.getChildren().add(Wyjscie);
		root.getChildren().add(ramka1);
		root.getChildren().add(ramka2);
		root.getChildren().add(ramka3);
		root.getChildren().add(ramka4);
	}

}
