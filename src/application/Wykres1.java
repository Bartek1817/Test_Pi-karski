package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

//WYKRES PO TESCIE
public class Wykres1 extends Application {
	static Wykres w1 = new Wykres();
	static String Lista[] = new String[Start.Odpowiedzi.length];

	public static void Start(Stage primaryStage, BorderPane root, int k, int j) {

		CategoryAxis xAxis = new CategoryAxis();
		NumberAxis yAxis = new NumberAxis();
		BarChart<String, Number> bc = new BarChart<String, Number>(xAxis, yAxis);
		yAxis.setLabel("Iloœæ Odpowiedzi");
		XYChart.Series series1 = new XYChart.Series();
		series1.setName("Dobre Odpowiedzi");
		XYChart.Series series2 = new XYChart.Series();
		series2.setName("Z³e Odpowiedzi");

		for (int i = 0; i < Start.Odpowiedzi.length; i++) {
			Lista[i] = "Pytanie " + (i + 1);
			series1.getData().add(new XYChart.Data(Lista[i], Menu.p.get(i).getODPDOBRE())); //// Tworzenie Kolumn w
																							//// wykresie w zale¿noœci
																							//// od iloœci pytañ
			series2.getData().add(new XYChart.Data(Lista[i], Menu.p.get(i).getODPZ£E())); /// Nazwa ka¿dej kolumny
		}
		bc.getData().addAll(series1, series2);
		Scene scena = new Scene(bc, 600, 450, Color.WHITE);

		bc.setLayoutX(100);
		bc.setLayoutY(100);

		//////////// TEXT

		Text hello = new Text("Wykres Odpowiedzi");
		hello.setStyle("-fx-font-size: 40pt; -font-weight: bold;-fx-font-family: 'Berlin Sans FB Demi';");
		hello.setFill(Color.WHITE);
		hello.setLayoutX(170);
		hello.setLayoutY(80);

		Text Powrót = new Text(" POWRÓT ");
		Powrót.setStyle("-fx-font-size: 20pt; -font-weight: bold;-fx-font-family: 'Berlin Sans FB Demi';");
		Powrót.setFill(Color.WHITE);
		Powrót.setLayoutX(25);
		Powrót.setLayoutY(592);

		/////////// RAMKA

		ImageView ramka4 = new ImageView("ramka.png");
		ramka4.setFitHeight(50);
		ramka4.setFitWidth(125);
		ramka4.setLayoutX(25);
		ramka4.setLayoutY(558);
		ramka4.setVisible(false);

		/////////// WYKONANIE

		Powrót.setOnMouseExited((MouseEvent e) -> { // Po zjechaniu wykonaj
			ramka4.setVisible(false);
			Powrót.setPickOnBounds(true);
		});
		Powrót.setOnMouseEntered((MouseEvent e) -> { // Po najechaniu wykonaj
			ramka4.setVisible(true);
			Powrót.setPickOnBounds(true);
		});

		Powrót.setOnMouseClicked((MouseEvent e) -> { // Po kliknieciu wykonaj
			root.getChildren().clear();
			w1.Start(primaryStage, root, k, j);

		});

		root.getChildren().add(hello);
		root.getChildren().add(Powrót);
		root.getChildren().add(ramka4);
		root.getChildren().add(bc);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub

	}
}
